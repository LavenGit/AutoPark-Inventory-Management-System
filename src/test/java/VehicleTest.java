
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    private Vehicle vehicle;

    // Concrete subclass for testing abstract Vehicle
    private static class TestVehicle extends Vehicle {

        public TestVehicle(String make, String model,
                           int year, double price, int quantity) {
            super(make, model, year, price, quantity);
        }

        @Override
        public String toString() {
            return getYear() + " " + getMake() + " " + getModel();
        }
    }

    @BeforeEach
    void setUp() {
        vehicle = new TestVehicle(
                "Toyota", "Camry", 2024, 25000.0, 5
        );
    }

    // Test 1: Verify vehicle make
    @Test
    void testVehicleMake() {
        assertEquals("Toyota", vehicle.getMake());
    }

    // Test 2: Verify vehicle model
    @Test
    void testVehicleModel() {
        assertEquals("Camry", vehicle.getModel());
    }

    // Test 3: Verify manufacturing year
    @Test
    void testVehicleYear() {
        assertEquals(2024, vehicle.getYear());
    }

    // Test 4: Verify inherited price
    @Test
    void testVehiclePrice() {
        assertEquals(25000.0, vehicle.getPrice(), 0.001);
    }

    // Test 5: Verify inherited inventory quantity
    @Test
    void testVehicleInventory() {
        assertEquals(5, vehicle.getInvQuantity());
    }
}
