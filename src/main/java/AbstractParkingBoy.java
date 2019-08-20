import exception.IllegalTicketException;

abstract class AbstractParkingBoy {

  ParkingLot parkingLot;

  AbstractParkingBoy(ParkingLot parkingLot) {
    this.parkingLot = parkingLot;
  }

  abstract Ticket park(Car car);

  Car pickUp(Ticket ticket) {
    return parkingLot.getAreas()
        .stream()
        .filter(area -> area.getCars().containsKey(ticket))
        .findFirst()
        .map(area -> area.pickUp(ticket))
        .orElseThrow(IllegalTicketException::new);
  }
}
