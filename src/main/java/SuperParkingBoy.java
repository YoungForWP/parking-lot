import java.math.BigDecimal;
import java.util.function.Function;

public class SuperParkingBoy extends AbstractParkingBoy {

  SuperParkingBoy(ParkingLot parkingLot) {
    super(parkingLot);
  }

  @Override
  public Function<Area, BigDecimal> getSortMethod() {
    return Area::getVacancyRate;
  }
}
