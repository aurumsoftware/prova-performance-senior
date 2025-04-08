import org.aurum.Calculator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun test() {
        val result = Calculator().calculateProportionalDifferencePrice(100.0, 300.0, "2025-04-15", "MONTHLY")
        Assertions.assertEquals(90.0, result)
    }
}