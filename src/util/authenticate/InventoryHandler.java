package util.authenticate;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import util.systemAlerts.AlertPopUp;

import java.io.IOException;

public class InventoryHandler {
    public InventoryHandler() {
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
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/InventoryManagement/Supplier.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }
    }
}
