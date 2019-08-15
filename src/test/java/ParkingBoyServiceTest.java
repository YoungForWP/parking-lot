import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingBoyServiceTest {
  private ParkingBoyService parkingBoyService;
  private ParkingLot parkingLot;

  @Before
  public void setUp() {
    parkingLot = new ParkingLot(1);
    parkingBoyService = new ParkingBoyService(parkingLot);
  }

  @Test
  public void shouldReturnATicketWhenParkingACar() {
    Car car = new Car();
    Ticket ticket = parkingBoyService.park(car);

    assertNotNull(ticket);
  }

  @Test
  public void shouldPickUpACarWithATicket() {
    Car expected = new Car();
    Ticket ticket = parkingBoyService.park(expected);
    Car actual = parkingBoyService.pickUp(ticket);

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenPickUpACarWithIllegalTicket() {
    parkingBoyService.pickUp(new Ticket());
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenParkOneTimeButPickUpTwoTimes() {
    Ticket ticket = parkingBoyService.park(new Car());
    parkingBoyService.pickUp(ticket);
    parkingBoyService.pickUp(ticket);
  }

  @Test(expected = NoParkingSpaceLeftException.class)
  public void shouldThrowExceptionWhenParkingLotHasNoCapacity() {
    parkingLot = new ParkingLot(0);
    parkingBoyService = new ParkingBoyService(parkingLot);
    Car car = new Car();
    parkingBoyService.park(car);
  }
}
