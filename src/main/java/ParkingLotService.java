import java.util.HashMap;
import java.util.Map;

public class ParkingLotService {

  private Map<Ticket, Car> cars = new HashMap<>();

  public Ticket park(Car car) {
    Ticket ticket = new Ticket();
    cars.put(ticket, car);
    return ticket;
  }

  public Car pickUp(Ticket ticket) {
    return cars.get(ticket);
  }
}
