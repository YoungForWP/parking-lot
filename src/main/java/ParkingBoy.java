import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;

public class ParkingBoy implements Parking {

  private ParkingLot parkingLot;

  public ParkingBoy(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }

  @Override
  public Ticket park(Car car) {
    return parkingLot.getAreas()
        .stream()
        .filter(item -> item.getCapacity() > 0)
        .findFirst()
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
