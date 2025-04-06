package main.test;

import main.code.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    static void setupAll() {
        System.out.println("  Calculator Tests Start");
    }

    @BeforeEach
    void setup() {
        calculator = new Calculator();
        System.out.println(" Setup complete");
    }

    @Test
    @Order(1)
    @DisplayName(" Test Add ")
    void testadd() {
        assertEquals(27, calculator.add(2, 3, 5,9,8));
        assertEquals(0, calculator.add());
    }

    @Test
    @Order(2)
    @DisplayName("Test Division by Zero")
    void testDivisionByZero() {
        Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0));
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({"6,720", "0,1", "1,1", "4,24"})
    @Order(3)
    @DisplayName("Test Factorial with Multiple Inputs")
    void testFactorial(int input, int expected) {
        assertEquals(expected, calculator.factorial(input));
    }

    @Test
    @Order(4)
    @Timeout(value = 1005, unit = TimeUnit.MILLISECONDS)  
    @DisplayName("Test Timeout with Delay")
    void testTimeout() throws InterruptedException {
        Thread.sleep(1000);
       
        assertDoesNotThrow(() -> calculator.add(10,13,14));
    }

    @AfterEach
    void Completed() {
        System.out.println("Test Completed");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println(" Calculator Tests Finished.");
    }
}