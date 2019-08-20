import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.Function;

public abstract class AbstractParkingBoy implements Parking {

  protected ParkingLot parkingLot;

  AbstractParkingBoy(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }

  abstract Function<Area, BigDecimal> getSortMethod();

  @Override
  public Ticket park(Car car) {
    return parkingLot.getAreas()
        .stream()
        .max(Comparator.comparing(this.getSortMethod()))
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
