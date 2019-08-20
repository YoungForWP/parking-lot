import exception.NoParkingSpaceLeftException;

public class OrdinaryParkingBoy extends AbstractParkingBoy {

  OrdinaryParkingBoy(ParkingLot parkingLot) {
    super(parkingLot);
  }

  @Override
  public Ticket park(Car car) {
    return super.parkingLot.getAreas()
        .stream()
        .filter(Area::hasSpaceToPark)
        .findFirst()
        .map(item -> item.park(car))
        .orElseThrow(NoParkingSpaceLeftException::new);
  }
}
