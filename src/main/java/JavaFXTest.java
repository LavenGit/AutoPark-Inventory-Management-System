
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

    @Override
    public void start(Stage stage) {
        Button button = new Button("JavaFX Works!");

        button.setOnAction(e ->
                System.out.println("Button clicked!")
        );

        Scene scene = new Scene(button, 400, 300);

        stage.setTitle("JavaFX Test");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
