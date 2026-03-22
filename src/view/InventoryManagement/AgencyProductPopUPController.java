package view.InventoryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import service.AgencyProductServices;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AgencyProduct;
import util.systemAlerts.AlertPopUp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AgencyProductPopUPController implements Initializable {

    @FXML
    private TextField SearchTextBox;

    @FXML
    private TextField pIDTextBox;

    @FXML
    private TextField pWeightTextBox;

    @FXML
    private TextField pNameTextBox;

    @FXML
    private TableView<AgencyProduct> AgencyProductTable;

    @FXML
    private TableColumn<AgencyProduct, String> APIDColumn;

    @FXML
    private TableColumn<AgencyProduct, String> APNameColumn;

    @FXML
    private TableColumn<AgencyProduct, Integer> APUnitsColumn;

    @FXML
    private TableColumn<AgencyProduct, String> APWeightColumn;

    @FXML
    private TableColumn<AgencyProduct, String> APAddedDateColumn;

    @FXML
    private TableColumn<AgencyProduct, String> APDiscontinueAlertDateColumn;

    @FXML
    private Button SIAPCloseButton;

    @FXML
    private Button selectProductButton;

    private static AgencyProduct selectedProduct;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectProductButton.setDisable(true);
        loadData();
        searchTable();
    }

    /**
     * Load agency products to table
     */
    private void loadData() {

        AgencyProductServices agencyProductServices = new AgencyProductServices();

        ObservableList<AgencyProduct> agencyProductData = agencyProductServices.loadData();

        APIDColumn.setCellValueFactory(new PropertyValueFactory<>("aPID"));
        APNameColumn.setCellValueFactory(new PropertyValueFactory<>("aPName"));
        APUnitsColumn.setCellValueFactory(new PropertyValueFactory<>("aPTotalUnits"));
        APWeightColumn.setCellValueFactory(new PropertyValueFactory<>("aPWeightOfUnit"));
        APAddedDateColumn.setCellValueFactory(new PropertyValueFactory<>("aPADate"));
        APDiscontinueAlertDateColumn.setCellValueFactory(new PropertyValueFactory<>("aPDADate"));

        AgencyProductTable.setItems(agencyProductData);
    }

    /**
     * Load selected row data to fields
     */
    @FXML
    void loadSelectedData(MouseEvent event) {
        try {
            selectedProduct = AgencyProductTable.getSelectionModel().getSelectedItem();

            if (selectedProduct != null) {
                selectProductButton.setDisable(false);

                pIDTextBox.setText(selectedProduct.getaPID());
                pNameTextBox.setText(selectedProduct.getaPName());
                pWeightTextBox.setText(selectedProduct.getaPWeightOfUnit());
            }
        } catch (Exception ex) {
            AlertPopUp.generalError(ex);
        }
    }

    @FXML
    public void returnSelectedProduct() throws IOException {
        boolean resultVal = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BottleCapRegister.fxml"));
        Parent root = (Parent) loader.load();
        BottleCapRegisterController bottleCapRegisterController = loader.getController();
        resultVal = bottleCapRegisterController.setSelectedProduct(selectedProduct);
        if(resultVal){
            closeStage();
        }
    }

    void closeStage(){
        Stage stage = (Stage) SIAPCloseButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Close popup
     */
    @FXML
    void closeButton(ActionEvent event) {
        closeStage();
    }

    /**
     * Search functionality
     */
    private void searchTable() {
        AgencyProductServices agencyProductServices = new AgencyProductServices();
        SortedList<AgencyProduct> sortedData = agencyProductServices.searchTable(SearchTextBox);
        sortedData.comparatorProperty().bind(AgencyProductTable.comparatorProperty());
        AgencyProductTable.setItems(sortedData);
    }
}