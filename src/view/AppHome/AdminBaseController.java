package view.AppHome;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import util.authenticate.*;
import util.loader.Loader;
import util.systemAlerts.AlertPopUp;
import util.utility.UtilityMethod;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminBaseController implements Initializable {

    @FXML
    private Circle Circle1;

    @FXML
    private Circle Circle2;

    @FXML
    private Circle Circle3;

    @FXML
    private Circle Circle4;

    @FXML
    private Circle Circle5;

    @FXML
    private Circle Circle6;

    @FXML
    private Circle Circle7;

    @FXML
    private Circle Circle8;

    @FXML
    private AnchorPane LoadingAnchorPane;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private MenuButton OptionMenuButton;

    @FXML
    private Label sessionUser;

    @FXML
    private Label systemDateLabel;

    @FXML
    private Label systemTimeLabel;


    private AdminManagementHandler adminManagementHandler = new AdminManagementHandler();
    private CashierHandler cashierHandler = new CashierHandler();
    private SupervisorHandler supervisorHandler = new SupervisorHandler();
    private InventoryHandler inventoryHandler = new InventoryHandler();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setGeneralizationInfo();
    }

    private void setGeneralizationInfo(){
        try{
            Loader loader = new Loader();
            loader.startUpAnimation(Circle1, Circle2, Circle3, Circle4, Circle5, Circle6, Circle7, Circle8, LoadingAnchorPane);
        }catch(Exception exception){
            exception.printStackTrace();
        }
        sessionUser.setText(UserAuthentication.getAuthenticatedSession().getuName());
        AdminManagementHandler adminManagementHandler = new AdminManagementHandler();
        adminManagementHandler.loadAgencyProduct(rootpane);
        UtilityMethod.getClock(systemTimeLabel);
        UtilityMethod.getCalendar(systemDateLabel);
    }
    @FXML
    private void logoutSession(ActionEvent event){
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.endAuthenticatedSession(OptionMenuButton);
    }
    @FXML
    private void adminPanel(ActionEvent event){
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.getAdminMenu(OptionMenuButton);
    }
    @FXML
    private void cashierPanel(ActionEvent event){
        UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.getCashierMenu(OptionMenuButton);
    }

    /**
     * Inventory Navigation Functions
     */
    @FXML
    void loadAgencyProducts(ActionEvent event) {
        AdminManagementHandler adminManagementHandler = new AdminManagementHandler();
        adminManagementHandler.loadAgencyProduct(rootpane);
    }

    @FXML
    void loadBottleCapRegister(ActionEvent event) {
        AdminManagementHandler adminManagementHandler = new AdminManagementHandler();
        adminManagementHandler.loadBottleCapRegister(rootpane);
    }

    @FXML
    void loadBottleCapManager(ActionEvent event) {
        AdminManagementHandler adminManagementHandler = new AdminManagementHandler();
        adminManagementHandler.loadBottleCapManager(rootpane);
    }
    /**
     * Supplier Navigation Functions
     */
    @FXML
    void loadSuppliers(ActionEvent event) {
        AdminManagementHandler adminManagementHandler = new AdminManagementHandler();
        adminManagementHandler.loadSupplier(rootpane);
    }
    /**
     * User Navigation Functions
     */
    @FXML
    void loadSystemUsers(ActionEvent event) {
        System.out.println("called");
        AdminManagementHandler adminManagementHandler = new AdminManagementHandler();
        adminManagementHandler.loadSystemUsers(rootpane);
    }

    @FXML
    void closeProgram(ActionEvent actionEvent){
        AlertPopUp.exitConfirmation();
    }
}
