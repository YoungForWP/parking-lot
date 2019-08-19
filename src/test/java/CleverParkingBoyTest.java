import exception.IllegalTicketException;
import exception.NoParkingSpaceLeftException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CleverParkingBoyTest {
  private CleverParkingBoy cleverParkingBoy;
  private ParkingLot parkingLot;

  @Before
  public void setUp() {
    Area area = new Area( 2);
    parkingLot = new ParkingLot(createAreas(area));
    cleverParkingBoy = new CleverParkingBoy(parkingLot);
  }

  @Test
  public void shouldReturnATicketWhenParkingACar() {
    Car car = new Car();
    Ticket ticket = cleverParkingBoy.park(car);

    assertNotNull(ticket);
  }

  @Test
  public void shouldPickUpACarWithATicket() {
    Car expected = new Car();
    Ticket ticket = cleverParkingBoy.park(expected);
    Car actual = cleverParkingBoy.pickUp(ticket);

    assertEquals(expected, actual);
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenPickUpACarWithIllegalTicket() {
    cleverParkingBoy.pickUp(new Ticket());
  }

  @Test(expected = IllegalTicketException.class)
  public void shouldThrowExceptionWhenParkOneTimeButPickUpTwoTimes() {
    Ticket ticket = cleverParkingBoy.park(new Car());
    cleverParkingBoy.pickUp(ticket);
    cleverParkingBoy.pickUp(ticket);
  }

  @Test(expected = NoParkingSpaceLeftException.class)
  public void shouldThrowExceptionWhenParkingLotHasNoCapacity() {
    Area area = new Area( 0);
    parkingLot = new ParkingLot(createAreas(area));
    cleverParkingBoy = new CleverParkingBoy(parkingLot);
    Car car = new Car();
    cleverParkingBoy.park(car);
  }

  @Test
  public void shouldParkCarInAreaBWhenAreaAHasNoSpace() {
    Area areaA = new Area(0);
    Area areaB = new Area(2);

    parkingLot = new ParkingLot(createAreas(areaA, areaB));
    cleverParkingBoy = new CleverParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = cleverParkingBoy.park(expected);
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
    cleverParkingBoy = new CleverParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = cleverParkingBoy.park(expected);
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
    cleverParkingBoy = new CleverParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = cleverParkingBoy.park(expected);
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
    cleverParkingBoy = new CleverParkingBoy(parkingLot);

    Car expected = new Car();
    Ticket ticket = cleverParkingBoy.park(expected);
    Car actual = areaB.pickUp(ticket);

    assertEquals(actual, expected);

  }

  private List<Area> createAreas(Area... areas) {
    ArrayList<Area> list = new ArrayList<>(areas.length);
    Collections.addAll(list, areas);
    return list;
  }
}
