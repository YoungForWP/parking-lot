import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.valueOf;

public class Area implements Parking {
  private int capacity;
  private Map<Ticket, Car> cars = new HashMap<>();

  public Area(int capacity) {
    this.capacity = capacity;
  }

  @Override
  public Ticket park(Car car) {
    if (!hasSpaceToPark()) {
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

  BigDecimal getCapacity() {
    return BigDecimal.valueOf(capacity - cars.size());
  }

  BigDecimal getVacancyRate() {
    return getCapacity().divide(valueOf(capacity));
  }

  boolean hasSpaceToPark() {
    return getCapacity().intValue() > 0;
  }

  Map<Ticket, Car> getCars() {
    return cars;
  }
}
