package main.test;

import main.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService();
    }
    

    @ParameterizedTest
    @ValueSource(strings = {"ghaith@example.com", "mohammed@domain.com", "admin@company.org"})
    @DisplayName("Test Valid Email")
    void testValidEmail(String email) {
        assertEquals(true, userService.isValidEmail(email));
    }
    
    @Test
    @DisplayName("Test Invalid Email")
    @Disabled("This test is currently disabled because we are not validating email format.")
    void testInvalidEmail() {
        assertEquals(false, userService.isValidEmail("invalid-email"));
    }
    @Test
    @Timeout(value = 2)
    @DisplayName("Test Authentication")
    void testAuthentication() {
        assertEquals(true, userService.authenticate("admin", "1234"));
    }
   
    
    
    
    
}
