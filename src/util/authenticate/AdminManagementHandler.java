package util.authenticate;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import util.systemAlerts.AlertPopUp;

import java.io.IOException;

public class AdminManagementHandler {
    public AdminManagementHandler() {
    }
    public void loadBottleCapRegister(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/InventoryManagement/BottleCapRegister.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }

    public void loadBottleCapManager(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/InventoryManagement/BottleCapManager.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadAgencyProduct(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/InventoryManagement/AgencyProduct.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadSupplier(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/SupplierManagement/Supplier.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
    public void loadSystemUsers(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/UserManagement/SystemUsers.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
}
