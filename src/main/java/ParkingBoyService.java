import exception.NoParkingSpaceLeftException;

public class ParkingBoyService extends ParkingLotService {

  private ParkingLot parkingLot;

  public ParkingBoyService(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }

  @Override
  Ticket park(Car car) {
    if (parkingLot.getCapacity() <= 0) {
      throw new NoParkingSpaceLeftException();
    }
    return super.park(car);
  }
}
