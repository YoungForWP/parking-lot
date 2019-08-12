import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ParkingLotServiceTest {

  @Test
  public void shouldReturnATicketWhenParkingACar() {
    ParkingLotService parkingLotService = new ParkingLotService();

    Car car = new Car();
    Ticket ticket = parkingLotService.park(car);

    assertNotNull(ticket);
  }
}
