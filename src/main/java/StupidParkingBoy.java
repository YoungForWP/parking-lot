import java.math.BigDecimal;
import java.util.function.Function;

public class StupidParkingBoy extends AbstractParkingBoy {

  StupidParkingBoy(ParkingLot parkingLot) {
    super(parkingLot);
  }

  @Override
  public Function<Area, BigDecimal> getSortMethod() {
    return Area::getCapacity;
  }
}
