package main.test;

import main.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
 @Execution(value=ExecutionMode.CONCURRENT)
class ProductTest {
    private Product product;

    @BeforeEach
    void setup() {
        product = new Product("iphone", 3000);
    }

    @Test
    @DisplayName("Test Discount Application")
    void testDiscount() {
        product.applyDiscount(10);
        assertEquals(2700, product.getFinalPrice());
    }

    @Test
    @DisplayName("Test Invalid Discount")
    void testInvalidDiscount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-5));
        assertEquals("Invalid discount", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 20,18})  
    @DisplayName("Test Discount with Multiple Values")
    @Timeout(value = 2) 
    void testDiscountWithMultipleValues(int discount) {
        double expectedPrice = product.getPrice() * (1 - discount / 100.0);
        product.applyDiscount(discount);
        assertEquals(expectedPrice, product.getFinalPrice(), "The final price after applying discount is incorrect");
    }
}
