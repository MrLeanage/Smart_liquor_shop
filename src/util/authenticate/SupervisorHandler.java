package util.authenticate;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.systemAlerts.AlertPopUp;

import java.io.IOException;

public class SupervisorHandler {
    public SupervisorHandler() {
    }
    public void loadItemWithdraw(ActionEvent actionEvent){

        try {
            AnchorPane home_page = (AnchorPane) FXMLLoader.load(getClass().getResource("/view/InventoryManagement/ItemWithdraw1.fxml"));

            Scene scene = new Scene(home_page);
            Stage app=(Stage)((Node) actionEvent.getSource()).getScene().getWindow();
            app.setScene(scene);
            app.show();
        } catch (IOException ex) {
            AlertPopUp.generalError(ex);
        }
    }
    public void loadNewOrders(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/OrderManagement/NewOrders.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadOnGoingOrders(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/OrderManagement/OnGoingOrders.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadCompletedOrders(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/OrderManagement/CompletedOrders.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadCancelledOrders(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/OrderManagement/CancelledOrders.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadOrderMenu(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/OrderManagement/OrdersMenuSupervisor.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
}
