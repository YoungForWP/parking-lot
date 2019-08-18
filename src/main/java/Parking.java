public interface Parking {
  Ticket park(Car car);

  Car pickUp(Ticket ticket);
}
