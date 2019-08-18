import java.math.BigDecimal;
import java.util.function.Function;

public class CleverParkingBoy extends ParkingBoy {

  public CleverParkingBoy(ParkingLot parkingLot) {
    super(parkingLot);
  }

  @Override
  public Function<Area, BigDecimal> getSortMethod() {
    return Area::getVacancyRate;
  }
}
