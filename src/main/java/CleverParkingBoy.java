import exception.NoParkingSpaceLeftException;

import java.util.Comparator;

public class CleverParkingBoy extends AbstractParkingBoy {

  CleverParkingBoy(ParkingLot parkingLot) {
    super(parkingLot);
  }

  @Override
  public Ticket park(Car car) {
    return parkingLot.getAreas()
        .stream()
        .max(Comparator.comparing(Area::getCapacity))
        .map(item -> item.park(car))
        .orElseThrow(NoParkingSpaceLeftException::new);
  }

}
