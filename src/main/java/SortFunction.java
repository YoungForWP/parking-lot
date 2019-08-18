import java.math.BigDecimal;
import java.util.function.Function;

public interface SortFunction {
  Function<Area, BigDecimal> getSortMethod();
}
