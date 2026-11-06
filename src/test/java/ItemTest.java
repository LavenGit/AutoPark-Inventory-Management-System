
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    private Item item;

    // Concrete subclass used only for testing
    private static class TestItem extends Item {

        public TestItem(double price, int quantity) {
            super(price, quantity);
        }

        @Override
        public String toString() {
            return "Test Item";
        }
    }

    @BeforeEach
    void setUp() {
        item = new TestItem(100.0, 10);
    }

    // Test 1: Check initial price
    @Test
    void testInitialPrice() {
        assertEquals(100.0, item.getPrice(), 0.001);
    }

    // Test 2: Check initial inventory
    @Test
    void testInitialInventory() {
        assertEquals(10, item.getInvQuantity());
        assertEquals(0, item.getSoldQuantity());
        assertEquals(0, item.getCartQuantity());
    }

    // Test 3: Increase inventory quantity
    @Test
    void testIncreaseInventory() {
        item.setInvQuantity(5);

        assertEquals(15, item.getInvQuantity());
    }

    // Test 4: Decrease inventory quantity
    @Test
    void testDecreaseInventory() {
        item.setInvQuantity(-3);

        assertEquals(7, item.getInvQuantity());
    }

    // Test 5: Sell items successfully
    @Test
    void testSellUnits() {
        double revenue = item.sellUnits(3);

        assertEquals(300.0, revenue, 0.001);
        assertEquals(7, item.getInvQuantity());
        assertEquals(3, item.getSoldQuantity());
    }

    // Test 6: Prevent selling more than available
    @Test
    void testSellMoreThanAvailable() {
        double revenue = item.sellUnits(15);

        assertEquals(0.0, revenue, 0.001);
        assertEquals(10, item.getInvQuantity());
        assertEquals(0, item.getSoldQuantity());
    }

    // Test 7: Reject zero or negative sales
    @Test
    void testInvalidSaleAmount() {
        assertEquals(0.0, item.sellUnits(0), 0.001);
        assertEquals(0.0, item.sellUnits(-2), 0.001);

        assertEquals(10, item.getInvQuantity());
        assertEquals(0, item.getSoldQuantity());
    }
}

