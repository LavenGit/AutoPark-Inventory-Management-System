import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;
public class AutoParkView extends Pane{
    private Label label3;
    private ListView<String> CurrList;
    private ListView<Item> popList, InvList;
    private Button resetButton, addButton, removeButton, completeButton;

    private TextField SalesField, revenueField, saleField;


    public ListView<Item> getInvList(){ return InvList; }
    public ListView<String> getCurrList(){ return CurrList; }
    public Button getResetButton(){ return resetButton; }
    public Button getAddButton(){ return addButton; }
    public Button getRemoveButton(){ return removeButton; }
    public Button getCompleteButton(){ return completeButton; }

    public AutoParkView(){

        // the labels
        Label label1 = new Label("Park Summary:");
        label1.relocate(600, 20);
        Label label2 = new Label("Park Inventory:");
        label2.relocate(50, 20);
        label3 = new Label("Current Cart:");
        label3.relocate(310, 20);
        Label label4 = new Label("# Sales:");
        label4.relocate(595, 55);
        Label label5 = new Label("Revenue:");
        label5.relocate(595, 88);
        Label label6 = new Label("$ / Sale:");
        label6.relocate(595,120);
        Label label7 = new Label("Most Popular Items");
        label7.relocate(590, 140);


        // the lists
        popList = new ListView<Item>();
        popList.relocate(590,160);
        popList.setPrefSize(170,170);// popular

        InvList = new ListView<Item>();
        InvList.relocate(30,40);//Park Inventory
        InvList.setPrefSize(275,290);

        CurrList = new ListView<String>();// Current cart
        CurrList.relocate(310,40);
        CurrList.setPrefSize(275,290);

        //Buttons

        resetButton = new Button("Reset Stock");
        resetButton.relocate(620, 340);
        resetButton.setPrefSize(120,20);

        addButton = new Button("Add to Cart");
        addButton.relocate(105,340);
        addButton.setPrefSize(120,20);
        addButton.setDisable(true);

        removeButton = new Button("Remove Items");
        removeButton.relocate(325,340);
        removeButton.setPrefSize(120,20);
        removeButton.setDisable(true);

        completeButton = new Button("Complete Sale");
        completeButton.relocate(450,340);
        completeButton.setPrefSize(120,20);
        completeButton.setDisable(true);

        // the text fields
        SalesField = new TextField();
        SalesField.relocate(675,55);
        SalesField.setPrefSize(100,25);
        SalesField.setEditable(false);
        revenueField = new TextField();
        revenueField.relocate(675,85);
        revenueField.setPrefSize(100,25);
        revenueField.setEditable(false);
        saleField = new TextField();
        saleField.relocate(675,115);
        saleField.setPrefSize(100,25);
        saleField.setEditable(false);

        getChildren().addAll(label1,label2,label3,label7,label4,label5,label6,popList,InvList,CurrList,resetButton,addButton,removeButton,completeButton,SalesField,revenueField,saleField);
        setPrefSize(800, 400);
    }

    public void update(AutoPark model){
        List<String> cart = new ArrayList<String>();
        List<Item> stock = model.getStock();
        List<Item> popularItems = model.getPopularItems();
        for(Item i : model.getCurCart().keySet()){
            cart.add(model.getCurCart().get(i)+ " X " + i );
        }
        InvList.setItems(FXCollections.observableArrayList(stock));
        CurrList.setItems(FXCollections.observableArrayList(cart));
        if(popularItems.size()<=0){
            List<Item> x = new ArrayList<Item>();
            x.add(stock.get(0));
            x.add(stock.get(1));
            x.add(stock.get(2));
            popList.setItems(FXCollections.observableArrayList(x));
        }
        else{
            List<Item> x = new ArrayList<Item>();
            if(popularItems.size()==1){
                x.add(popularItems.get(0));
            }
            else if(popularItems.size()==2){
                x.add(popularItems.get(0));
                x.add(popularItems.get(1));
            }
            else{
                x.add(popularItems.get(0));
                x.add(popularItems.get(1));
                x.add(popularItems.get(2));
            }
            popList.setItems(FXCollections.observableArrayList(x));
        }
        //mList.setItems(FXCollections.observableArrayList(popularItems));
        SalesField.setText(model.getNumSales() + "");
        double numRevFeild = model.getRevenue();
        String textRevFeild = String.format("%.2f", numRevFeild);
        revenueField.setText(textRevFeild);
        double numSalesField = model.getSalesField();
        String textSalesField = String.format("%.2f", numSalesField);
        if(numSalesField>0){
            saleField.setText(textSalesField);
        }
        else{
            saleField.setText("N/A");
        }
        label3.setText("Current Cart ($" + model.cartPrice() + "0):");

        Item jj = getInvList().getSelectionModel().getSelectedItem();
        if(jj != null){
            getAddButton().setDisable(false);
        }
        if(jj == null){
            getAddButton().setDisable(true);
        }

        if(model.getCurCart().size()>0){
            getCompleteButton().setDisable(false);
            getRemoveButton().setDisable(false);
        }
        if(model.getCurCart().size()<=0){
            getCompleteButton().setDisable(true);
            getRemoveButton().setDisable(true);
        }
    }
}














