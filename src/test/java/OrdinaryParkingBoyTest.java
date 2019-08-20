import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrdinaryParkingBoyTest {
  private OrdinaryParkingBoy stupidParkingBoy;
  private ParkingLot parkingLot;

  @Before
  public void setUp() {
    Area area = new Area( 2);
    parkingLot = new ParkingLot(createAreas(area));
    stupidParkingBoy = new OrdinaryParkingBoy(parkingLot);
  }

  @Test
  public void shouldReturnATicketWhenParkingACar() {
    Car car = new Car();
    Ticket ticket = stupidParkingBoy.park(car);

    assertNotNull(ticket);
  }

  @Test
  public void shouldPickUpACarWithATicket() {
    Car expected = new Car();
    Ticket ticket = stupidParkingBoy.park(expected);
    Car actual = stupidParkingBoy.pickUp(ticket);

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenPickUpACarWithIllegalTicket() {
    stupidParkingBoy.pickUp(new Ticket());
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenParkOneTimeButPickUpTwoTimes() {
    Ticket ticket = stupidParkingBoy.park(new Car());
    stupidParkingBoy.pickUp(ticket);
    stupidParkingBoy.pickUp(ticket);
  }

  @Test(expected = NoParkingSpaceLeftException.class)
  public void shouldThrowExceptionWhenParkingLotHasNoCapacity() {
    Area area = new Area( 0);
    parkingLot = new ParkingLot(createAreas(area));
    stupidParkingBoy = new OrdinaryParkingBoy(parkingLot);
    Car car = new Car();
    stupidParkingBoy.park(car);
  }

  @Test
  public void shouldParkCarInAreaBWhenAreaAHasNoSpace() {
    Area areaA = new Area(0);
    Area areaB = new Area(2);

    parkingLot = new ParkingLot(createAreas(areaA, areaB));
    stupidParkingBoy = new OrdinaryParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = stupidParkingBoy.park(expected);
    Car actual = areaB.pickUp(ticket);

    assertEquals(actual, expected);
  }

  private List<Area> createAreas(Area... areas) {
    ArrayList<Area> list = new ArrayList<>(areas.length);
    Collections.addAll(list, areas);
    return list;
  }
}
