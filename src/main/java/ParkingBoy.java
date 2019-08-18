import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;

import java.util.Comparator;

public class ParkingBoy implements Parking {

  private ParkingLot parkingLot;

  public ParkingBoy(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }

  @Override
  public Ticket park(Car car) {
    return parkingLot.getAreas()
        .stream()
        .max(Comparator.comparing(Area::getCapacity))
        .map(item -> item.park(car))
        .orElseThrow(NoParkingSpaceLeftException::new);
  }

  @Override
  public Car pickUp(Ticket ticket) {
    return parkingLot.getAreas()
        .stream()
        .filter(area -> area.getCars().containsKey(ticket))
        .findFirst()
        .map(area -> area.pickUp(ticket))
        .orElseThrow(IllegalTicketException::new);
  }
}
