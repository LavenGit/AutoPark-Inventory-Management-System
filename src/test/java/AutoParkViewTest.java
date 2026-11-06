
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class AutoParkViewTest {

    private static void runOnFxThread(Runnable action)
            throws Exception {

        CountDownLatch latch = new CountDownLatch(1);
        AtomicReference<Throwable> error =
                new AtomicReference<>();

        Platform.runLater(() -> {
            try {
                action.run();
            } catch (Throwable t) {
                error.set(t);
            } finally {
                latch.countDown();
            }
        });

        assertTrue(latch.await(10, TimeUnit.SECONDS),
                "JavaFX operation timed out");

        if (error.get() != null) {
            throw new AssertionError(
                    "JavaFX operation failed", error.get());
        }
    }

    @BeforeAll
    static void initializeJavaFX() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        try {
            Platform.startup(latch::countDown);
            assertTrue(latch.await(10, TimeUnit.SECONDS));
        } catch (IllegalStateException alreadyStarted) {
            // JavaFX was already initialized
        }

        Platform.setImplicitExit(false);
    }

    @AfterAll
    static void finishTests() {
        Platform.exit();
    }

    // Test 1: Initial inventory is displayed
    @Test
    void testInitialInventoryDisplay() throws Exception {
        runOnFxThread(() -> {
            AutoPark model = AutoPark.createPark();
            AutoParkView view = new AutoParkView();

            view.update(model);

            assertEquals(10,
                    view.getInvList().getItems().size());
            assertTrue(view.getCurrList().getItems().isEmpty());
        });
    }

    // Test 2: Buttons are initially disabled
    @Test
    void testInitialButtonStates() throws Exception {
        runOnFxThread(() -> {
            AutoPark model = AutoPark.createPark();
            AutoParkView view = new AutoParkView();

            view.update(model);

            assertTrue(view.getAddButton().isDisabled());
            assertTrue(view.getRemoveButton().isDisabled());
            assertTrue(view.getCompleteButton().isDisabled());
            assertFalse(view.getResetButton().isDisabled());
        });
    }

    // Test 3: Selecting an item enables Add to Cart
    @Test
    void testSelectingInventoryItem() throws Exception {
        runOnFxThread(() -> {
            AutoPark model = AutoPark.createPark();
            AutoParkView view = new AutoParkView();

            view.update(model);

            view.getInvList()
                    .getSelectionModel().select(0);

            view.update(model);

            assertFalse(view.getAddButton().isDisabled());
        });
    }

    // Test 4: Adding an item updates the cart display
    @Test
    void testCartDisplayAfterAddingItem() throws Exception {
        runOnFxThread(() -> {
            AutoPark model = AutoPark.createPark();
            AutoParkView view = new AutoParkView();

            view.update(model);

            Item item = model.getStock().get(0);
            model.addToCart(item);
            view.update(model);

            assertEquals(1,
                    view.getCurrList().getItems().size());

            assertFalse(view.getCompleteButton().isDisabled());
            assertFalse(view.getRemoveButton().isDisabled());
        });
    }

    // Test 5: Removing an item updates the cart display
    @Test
    void testCartDisplayAfterRemovingItem() throws Exception {
        runOnFxThread(() -> {
            AutoPark model = AutoPark.createPark();
            AutoParkView view = new AutoParkView();

            Item item = model.getStock().get(0);

            model.addToCart(item);
            view.update(model);

            String entry =
                    view.getCurrList().getItems().get(0);

            model.removeFromCart(entry);
            view.update(model);

            assertTrue(view.getCurrList().getItems().isEmpty());
            assertTrue(view.getCompleteButton().isDisabled());
            assertTrue(view.getRemoveButton().isDisabled());
        });
    }

    // Test 6: Completing a sale updates the interface
    @Test
    void testDisplayAfterCompletingSale() throws Exception {
        runOnFxThread(() -> {
            AutoPark model = AutoPark.createPark();
            AutoParkView view = new AutoParkView();

            Item item = model.getStock().get(0);
            model.addToCart(item);

            model.cashOut();
            view.update(model);

            assertTrue(view.getCurrList().getItems().isEmpty());
            assertTrue(view.getCompleteButton().isDisabled());

            TextField salesField = view.getChildren()
                    .stream()
                    .filter(node -> node instanceof TextField)
                    .map(node -> (TextField) node)
                    .findFirst()
                    .orElseThrow();

            assertEquals("1", salesField.getText());
        });
    }
}
