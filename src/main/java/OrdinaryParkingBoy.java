import exception.NoParkingSpaceLeftException;

import java.math.BigDecimal;
import java.util.function.Function;

public class OrdinaryParkingBoy extends AbstractParkingBoy {

  OrdinaryParkingBoy(ParkingLot parkingLot) {
    super(parkingLot);
  }

  @Override
  Function<Area, BigDecimal> getSortMethod() {
    return null;
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
