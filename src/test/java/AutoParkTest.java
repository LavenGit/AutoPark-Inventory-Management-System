import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutoParkTest {

    private AutoPark park;

    @BeforeEach
    void setUp() {
        park = AutoPark.createPark();
    }

    @Test
    void testParkName() {
        assertEquals("VroomVille Vehicle Haven", park.getName());
    }

    @Test
    void testInitialInventory() {
        assertEquals(10, park.getStock().size());
    }

    @Test
    void testInitialRevenue() {
        assertEquals(0.0, park.getRevenue(), 0.001);
    }

    @Test
    void testInitialSales() {
        assertEquals(0, park.getNumSales());
    }

    @Test
    void testAddToCart() {
        Item item = park.getStock().get(0);
        int originalQuantity = item.getInvQuantity();

        boolean result = park.addToCart(item);

        assertTrue(result);
        assertEquals(1, park.getCurCart().get(item));
        assertEquals(originalQuantity - 1,
                item.getInvQuantity());
    }

    @Test
    void testCartPrice() {
        Item item = park.getStock().get(0);
        double expectedPrice = item.getPrice();

        park.addToCart(item);

        assertEquals(expectedPrice, park.cartPrice(), 0.001);
    }

    @Test
    void testRemoveFromCart() {
        Item item = park.getStock().get(0);
        int originalQuantity = item.getInvQuantity();

        park.addToCart(item);

        String cartEntry = "1 X " + item;
        boolean result = park.removeFromCart(cartEntry);

        assertTrue(result);
        assertTrue(park.getCurCart().isEmpty());
        assertEquals(originalQuantity,
                item.getInvQuantity());
    }

    @Test
    void testCashOut() {
        Item item = park.getStock().get(0);
        double expectedRevenue = item.getPrice();

        park.addToCart(item);
        park.cashOut();

        assertEquals(expectedRevenue,
                park.getRevenue(), 0.001);
        assertEquals(1, park.getNumSales());
        assertTrue(park.getCurCart().isEmpty());
    }

    @Test
    void testPopularItems() {
        Item item = park.getStock().get(0);

        park.addToCart(item);
        park.cashOut();

        assertTrue(park.getPopularItems().contains(item));
        assertEquals(1, item.getSoldQuantity());
    }

    @Test
    void testRemoveNonexistentItem() {
        // Attempt to remove an item that is not in the cart
        boolean result = park.removeFromCart("Invalid Item");

        // Removing an invalid item should fail safely
        assertFalse(result);
        assertTrue(park.getCurCart().isEmpty());
    }

    @Test
    void testAddOutOfStockItem() {
        Item item = park.getStock().get(0);

        // Reduce inventory to zero
        int quantity = item.getInvQuantity();
        item.setInvQuantity(-quantity);

        // Attempt to add an unavailable item
        boolean result = park.addToCart(item);

        assertFalse(result);
        assertFalse(park.getCurCart().containsKey(item));
        assertEquals(0, item.getInvQuantity());
    }

    @Test
    void testAddMultipleQuantities() {
        Item item = park.getStock().get(0);
        int originalQuantity = item.getInvQuantity();

        // Add the same item three times
        assertTrue(park.addToCart(item));
        assertTrue(park.addToCart(item));
        assertTrue(park.addToCart(item));

        assertEquals(3, park.getCurCart().get(item));
        assertEquals(originalQuantity - 3,
                item.getInvQuantity());
        assertEquals(item.getPrice() * 3,
                park.cartPrice(), 0.001);
    }

    @Test
    void testRemoveMultipleQuantities() {
        Item item = park.getStock().get(0);
        int originalQuantity = item.getInvQuantity();

        park.addToCart(item);
        park.addToCart(item);
        park.addToCart(item);

        // Remove one unit from the cart
        boolean result = park.removeFromCart("3 X " + item);

        assertTrue(result);
        assertEquals(2, park.getCurCart().get(item));
        assertEquals(originalQuantity - 2,
                item.getInvQuantity());
    }

    @Test
    void testMultipleItemCartPrice() {
        Item firstItem = park.getStock().get(0);
        Item secondItem = park.getStock().get(1);

        park.addToCart(firstItem);
        park.addToCart(secondItem);

        double expectedTotal =
                firstItem.getPrice() + secondItem.getPrice();

        assertEquals(expectedTotal,
                park.cartPrice(), 0.001);
        assertEquals(2, park.getCurCart().size());
    }

    @Test
    void testMultipleSales() {
        Item firstItem = park.getStock().get(0);
        Item secondItem = park.getStock().get(1);

        // First sale
        park.addToCart(firstItem);
        double firstSale = park.cartPrice();
        park.cashOut();

        // Second sale
        park.addToCart(secondItem);
        double secondSale = park.cartPrice();
        park.cashOut();

        double expectedRevenue = firstSale + secondSale;
        double expectedAverage = expectedRevenue / 2;

        assertEquals(2, park.getNumSales());
        assertEquals(expectedRevenue,
                park.getRevenue(), 0.001);
        assertEquals(expectedAverage,
                park.getSalesField(), 0.001);
        assertTrue(park.getCurCart().isEmpty());
    }

}
