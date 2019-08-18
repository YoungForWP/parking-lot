import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;

import java.util.HashMap;
import java.util.Map;

public class Area implements Parking {
  private int capacity;
  private Map<Ticket, Car> cars = new HashMap<>();

  public Area(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Ticket park(Car car) {
    if (getCapacity() <= 0) {
      throw new NoParkingSpaceLeftException();
    }
    Ticket ticket = new Ticket();
    cars.put(ticket, car);
    return ticket;
  }

  @Override
  public Car pickUp(Ticket ticket) {
    if (!cars.containsKey(ticket)) {
      throw new IllegalTicketException();
    }
    return cars.remove(ticket);
  }

  public int getCapacity() {
    return capacity - cars.size();
  }

  public Map<Ticket, Car> getCars() {
    return cars;
  }
}
