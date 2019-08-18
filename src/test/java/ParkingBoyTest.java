import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingBoyTest {
  private ParkingBoy parkingBoy;
  private ParkingLot parkingLot;

  @Before
  public void setUp() {
    Area area = new Area( 2);
    parkingLot = new ParkingLot(createAreas(area));
    parkingBoy = new ParkingBoy(parkingLot);
  }

  @Test
  public void shouldReturnATicketWhenParkingACar() {
    Car car = new Car();
    Ticket ticket = parkingBoy.park(car);

    assertNotNull(ticket);
  }

  @Test
  public void shouldPickUpACarWithATicket() {
    Car expected = new Car();
    Ticket ticket = parkingBoy.park(expected);
    Car actual = parkingBoy.pickUp(ticket);

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenPickUpACarWithIllegalTicket() {
    parkingBoy.pickUp(new Ticket());
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenParkOneTimeButPickUpTwoTimes() {
    Ticket ticket = parkingBoy.park(new Car());
    parkingBoy.pickUp(ticket);
    parkingBoy.pickUp(ticket);
  }

  @Test(expected = NoParkingSpaceLeftException.class)
  public void shouldThrowExceptionWhenParkingLotHasNoCapacity() {
    Area area = new Area( 0);
    parkingLot = new ParkingLot(createAreas(area));
    parkingBoy = new ParkingBoy(parkingLot);
    Car car = new Car();
    parkingBoy.park(car);
  }

  @Test
  public void shouldParkCarInAreaBWhenAreaAHasNoSpace() {
    Area areaA = new Area(0);
    Area areaB = new Area(2);

    parkingLot = new ParkingLot(createAreas(areaA, areaB));
    parkingBoy = new ParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = parkingBoy.park(expected);
    Car actual = areaB.pickUp(ticket);

    assertEquals(actual, expected);
  }

  @Test
  public void shouldParkInAreaBWhenAreaBLeftMoreThanAreaA() {
    Area areaA = new Area(5);
    Area areaB = new Area(5);

    areaA.park(new Car());
    areaA.park(new Car());
    areaB.park(new Car());

    parkingLot = new ParkingLot(createAreas(areaA, areaB));
    parkingBoy = new ParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = parkingBoy.park(expected);
    Car actual = areaB.pickUp(ticket);

    assertEquals(actual, expected);
  }

  @Test
  public void shouldParkInAreaAWhenAreaALeftIsEqualToAreaB() {
    Area areaA = new Area(5);
    Area areaB = new Area(5);

    areaA.park(new Car());
    areaB.park(new Car());

    parkingLot = new ParkingLot(createAreas(areaA, areaB));
    parkingBoy = new ParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = parkingBoy.park(expected);
    Car actual = areaA.pickUp(ticket);

    assertEquals(actual, expected);

  }

  @Test
  public void shouldParkInAreaBWhenAreaALeftLessThanAreaBAndAreaBLeftEqualToAreaC() {
    Area areaA = new Area(5);
    Area areaB = new Area(5);
    Area areaC = new Area(5);

    areaA.park(new Car());
    areaA.park(new Car());
    areaB.park(new Car());
    areaC.park(new Car());

    parkingLot = new ParkingLot(createAreas(areaA, areaB, areaC));
    parkingBoy = new ParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = parkingBoy.park(expected);
    Car actual = areaB.pickUp(ticket);

    assertEquals(actual, expected);

  }

  private List<Area> createAreas(Area... areas) {
    ArrayList<Area> list = new ArrayList<>(areas.length);
    Collections.addAll(list, areas);
    return list;
  }
}
