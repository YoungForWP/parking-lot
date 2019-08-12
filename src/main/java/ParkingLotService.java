import exception.IllegalTicketException;

import java.util.HashMap;
import java.util.Map;

class ParkingLotService {

  private Map<Ticket, Car> cars = new HashMap<>();

  Ticket park(Car car) {
    Ticket ticket = new Ticket();
    cars.put(ticket, car);
    return ticket;
  }

  Car pickUp(Ticket ticket) {
    if (!cars.containsKey(ticket)) {
      throw new IllegalTicketException();
    }
    return cars.remove(ticket);
  }
}
