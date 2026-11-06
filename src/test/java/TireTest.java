
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TireTest {

    private Tire tire;

    @BeforeEach
    void setUp() {
        tire = new Tire(18, 225, true, 150.0, 10);
    }

    // Test 1: Verify tire dimensions
    @Test
    void testTireDimensions() {
        assertEquals(18, tire.wheelDiameter);
        assertEquals(225, tire.sectionWidth);
    }

    // Test 2: Verify passenger tire type
    @Test
    void testPassengerTire() {
        assertTrue(tire.passengerTire);
        assertTrue(tire.toString().contains("Passenger tire"));
    }

    // Test 3: Verify front tire type
    @Test
    void testFrontTire() {
        Tire frontTire = new Tire(20, 245, false, 200.0, 5);

        assertFalse(frontTire.passengerTire);
        assertTrue(frontTire.toString().contains("Front Tire"));
    }

    // Test 4: Verify initial price and inventory
    @Test
    void testInitialPriceAndInventory() {
        assertEquals(150.0, tire.getPrice(), 0.001);
        assertEquals(10, tire.getInvQuantity());
        assertEquals(0, tire.getSoldQuantity());
    }

    // Test 5: Verify tire sales
    @Test
    void testSellTires() {
        double revenue = tire.sellUnits(3);

        assertEquals(450.0, revenue, 0.001);
        assertEquals(7, tire.getInvQuantity());
        assertEquals(3, tire.getSoldQuantity());
    }

    // Test 6: Verify toString updates after sales
    @Test
    void testToStringAfterSale() {
        tire.sellUnits(2);

        String description = tire.toString();

        assertTrue(description.contains("Passenger tire"));
        assertTrue(description.contains("18\" wheel diameter"));
        assertTrue(description.contains("225 mm. section width"));
        assertTrue(description.contains("150.00"));
        assertTrue(description.contains("8 in stock"));
        assertTrue(description.contains("2 sold"));
    }
}
