package util.authenticate;


import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import util.systemAlerts.AlertPopUp;

import java.io.IOException;

public class CashierHandler {

    public CashierHandler() {

    }
    public void loadBilling(AnchorPane rootpane){
        try{
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/view/FinanceManagement/Billing.fxml"));
            rootpane.getChildren().setAll(pane);
        }catch(IOException ex){
            AlertPopUp.generalError(ex);
        }

    }
}

