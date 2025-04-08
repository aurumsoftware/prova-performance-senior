import org.aurum.Calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

	@Test
	public void test() {
		double diff = new Calculator().calculateProportionalDifferencePrice(100, 300, "2025-04-15", "MONTHLY");
		Assertions.assertEquals(90, diff);
	}
}
