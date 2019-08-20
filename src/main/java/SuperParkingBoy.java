import exception.NoParkingSpaceLeftException;

import java.util.Comparator;

public class SuperParkingBoy extends AbstractParkingBoy {

  SuperParkingBoy(ParkingLot parkingLot) {
    super(parkingLot);
  }

  @Override
  public Ticket park(Car car) {
    return parkingLot.getAreas()
        .stream()
        .max(Comparator.comparing(Area::getVacancyRate))
        .map(item -> item.park(car))
        .orElseThrow(NoParkingSpaceLeftException::new);
  }

}
