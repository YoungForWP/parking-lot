import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotServiceTest {

  private ParkingLotService parkingLotService;

  @Before
  public void setUp() {
   parkingLotService = new ParkingLotService();
  }

  @Test
  public void shouldReturnATicketWhenParkingACar() {
    Car car = new Car();
    Ticket ticket = parkingLotService.park(car);

    assertNotNull(ticket);
  }

  @Test
  public void shouldPickUpACarWithATicket() {
    Car expected = new Car();
    Ticket ticket = parkingLotService.park(expected);
    Car actual = parkingLotService.pickUp(ticket);

    assertEquals(expected, actual);
  }
}
