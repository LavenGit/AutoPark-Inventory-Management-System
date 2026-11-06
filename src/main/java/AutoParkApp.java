import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

public class AutoParkApp extends Application{
    private AutoPark model;

    public AutoParkApp() { model = AutoPark.createPark(); }

    public void start(Stage primaryStage){
        Pane aPane = new Pane();

        AutoParkView view = new AutoParkView();
        aPane.getChildren().add(view);

        view.update(model);

        view.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getInvList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getAddButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                Item x = view.getInvList().getSelectionModel().getSelectedItem();
                model.addToCart(x);
                view.update(model);
            }
        });

        view.getRemoveButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                String x = view.getCurrList().getSelectionModel().getSelectedItem();
                model.removeFromCart(x);
                view.update(model);
            }
        });

        view.getCompleteButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.cashOut();
                view.update(model);
            }
        });

        view.getResetButton().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model = AutoPark.createPark();
                view.update(model);
            }
        });

        primaryStage.setTitle( model.getName()+" - Sales and Inventory " );
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
