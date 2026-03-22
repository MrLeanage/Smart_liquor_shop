package view.InventoryManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.AgencyProduct;
import model.BottleCap;
import service.AgencyProductServices;
import service.BottleCapServices;
import util.systemAlerts.AlertPopUp;
import util.utility.PrintReport;
import util.utility.UtilityMethod;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class BottleCapManagerController implements Initializable {
    @FXML
    private TableView<BottleCap> bottleCapTable;

    @FXML
    private TableColumn<BottleCap, Boolean> selectionColumn;

    @FXML
    private TableColumn<BottleCap, String> bCTagIdColumn;

    @FXML
    private TableColumn<BottleCap, String> bCSpecsColumn;

    @FXML
    private TableColumn<BottleCap, String> bCRegisteredDateColumn;

    @FXML
    private TableColumn<BottleCap, String> bCAvailabilityStatusColumn;

    @FXML
    private TableColumn<BottleCap, String> bCAllocationStatusColumn;

    @FXML
    private TextField SearchTextBox;

    @FXML
    private TextField tagIdTextField;

    @FXML
    private TextField tagSpecsTextField;

    @FXML
    private TextField tagRegisteredDateTextField;

    @FXML
    private ChoiceBox<String> bCAvailabilityStatusChoiceBox;

    @FXML
    private ChoiceBox<String> bCAllocationStatusChoiceBox;

    @FXML
    private Label tagIdValidationLabel;

    private BottleCap selectedBottleCapModelData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init();
        loadData();
    }

    @FXML
    private void addData(ActionEvent event) throws Exception{
        if(!tagIdTextField.getText().isEmpty()){
            BottleCap bottleCap = new BottleCap();
            bottleCap.setBCTagId(tagIdTextField.getText());
            bottleCap.setBCSpecs(tagSpecsTextField.getText());
            bottleCap.setBCAvailabilityStatus(bCAvailabilityStatusChoiceBox.getValue());
            bottleCap.setBCAllocationStatus(bCAllocationStatusChoiceBox.getValue());

            BottleCapServices bottleCapServices = new BottleCapServices();
            boolean result = bottleCapServices.insertData(bottleCap);

            if (result) {
                refreshTable();
            }
        } else {
            tagIdValidationLabel.setVisible(true);
            tagIdValidationLabel.setText("Please enter/generate a tag ID");
        }

    }

    @FXML
    private void updateSpecsAndAvailability(ActionEvent event) throws Exception {
        if (selectedBottleCapModelData == null) {
            AlertPopUp.updateFailed("Please select a Bottle Cap to update.");
            return;
        }

        // Update values from fields
        selectedBottleCapModelData.setBCSpecs(tagSpecsTextField.getText());
        selectedBottleCapModelData.setBCAvailabilityStatus(bCAvailabilityStatusChoiceBox.getValue());

        BottleCapServices services = new BottleCapServices();
        boolean updatedSpecs = services.updateSpecsAndAvailability(selectedBottleCapModelData);
        if(updatedSpecs){
            refreshTable();
        }
    }

    @FXML
    private void deleteData(ActionEvent event) throws Exception {
        if (selectedBottleCapModelData == null) {
            AlertPopUp.selectRowToDelete("Please select a Bottle Cap to delete.");
            return;
        }

        if (selectedBottleCapModelData.getBCAllocationStatus().equals("ALLOCATED")) {
            AlertPopUp.selectRowToDelete("Cannot delete an allocated tag!");
            return;
        }
        Optional<ButtonType> confirmationType = AlertPopUp.deleteConfirmation("Are you sure you want to delete this Bottle Cap?");

        if (confirmationType.get() == ButtonType.OK) {
            BottleCapServices services = new BottleCapServices();
            boolean deleted = services.deleteData(selectedBottleCapModelData.getBCId());
            if (deleted) {
                refreshTable();
            }
        }
    }

    @FXML
    private void clearFields(ActionEvent event) throws Exception{
        clearFields();
    }

    @FXML
    private void generateTagId(ActionEvent event) throws Exception{
        BottleCapServices bottleCapServices = new BottleCapServices();
        String tagId = bottleCapServices.generateUniqueTagId();
        tagIdTextField.setText(UtilityMethod.addPrefix("BC-", tagId));
    }

    @FXML
    private void setSelectedData(MouseEvent event) throws Exception{
        try{
            if (event.getTarget() instanceof CheckBox || event.getTarget() instanceof StackPane) {
                return;
            }

            selectedBottleCapModelData = bottleCapTable.getSelectionModel().getSelectedItem();
            if (selectedBottleCapModelData == null) return;

            tagIdTextField.setText(selectedBottleCapModelData.getBCTagId());
            tagSpecsTextField.setText(selectedBottleCapModelData.getBCSpecs());
            tagRegisteredDateTextField.setText(selectedBottleCapModelData.getBCRegisteredDate());
            bCAllocationStatusChoiceBox.setValue(selectedBottleCapModelData.getBCAllocationStatus());
            bCAvailabilityStatusChoiceBox.setValue(selectedBottleCapModelData.getBCAvailabilityStatus());


            if(selectedBottleCapModelData.getBCAllocationStatus().equals("ALLOCATED")){
                bCAvailabilityStatusChoiceBox.setDisable(true);
            } else {
                bCAvailabilityStatusChoiceBox.setDisable(false);
            }
            bCAllocationStatusChoiceBox.setDisable(true);
            clearLabels();
        } catch (Exception ignored){
        }
    }

    @FXML
    private void printSelectedTags(ActionEvent event){

        List<String> selectedCapTags = new ArrayList<>();

        for (BottleCap cap : bottleCapTable.getItems()) {
            if (cap.isItemSelected()) {
                selectedCapTags.add(cap.getIntegerBCTagId().toString());
            }
        }

        PrintReport printReport = new PrintReport();
        printReport.printBottleCapBarcodes(selectedCapTags);
    }

    private void init(){
        ObservableList<String> bCAllocationStatusChoiceBoxItemList = FXCollections.observableArrayList("ALLOCATED", "NOT ALLOCATED");
        bCAllocationStatusChoiceBox.setItems(bCAllocationStatusChoiceBoxItemList);
        bCAllocationStatusChoiceBox.setValue("NOT ALLOCATED");

        ObservableList<String> bCAvailabilityStatusChoiceBoxItemList = FXCollections.observableArrayList("AVAILABLE", "NOT AVAILABLE");
        bCAvailabilityStatusChoiceBox.setItems(bCAvailabilityStatusChoiceBoxItemList);
        bCAvailabilityStatusChoiceBox.setValue("AVAILABLE");

        tagIdTextField.setEditable(false);
        tagRegisteredDateTextField.setEditable(false);

        bCAllocationStatusChoiceBox.setDisable(false);
        bCAvailabilityStatusChoiceBox.setDisable(false);

        bottleCapTable.setEditable(true);
        selectionColumn.setEditable(true);
    }

    private void loadData() {
        //getting data from main LoginController
        BottleCapServices bottleCapServices = new BottleCapServices();

        ObservableList<BottleCap> bottleCaps;
        bottleCaps = bottleCapServices.loadData();

        //Setting cell value factory to table view
        selectionColumn.setCellValueFactory(cellData ->
                cellData.getValue().itemSelectedProperty()
        );
        selectionColumn.setCellFactory(tc -> {
            CheckBoxTableCell<BottleCap, Boolean> cell = new CheckBoxTableCell<>();
            cell.setEditable(true);
            return cell;
        });
        selectionColumn.setEditable(true);

        bCTagIdColumn.setCellValueFactory(new PropertyValueFactory<>("bCTagId"));
        bCSpecsColumn.setCellValueFactory(new PropertyValueFactory<>("bCSpecs"));
        bCRegisteredDateColumn.setCellValueFactory(new PropertyValueFactory<>("bCRegisteredDate"));
        bCAvailabilityStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bCAvailabilityStatus"));
        bCAllocationStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bCAllocationStatus"));

        bottleCapTable.setItems(null);
        bottleCapTable.setItems(bottleCaps);

        searchTable(bottleCaps);
    }

    public void refreshTable() throws Exception{
        clearFields();
        clearLabels();
        init();
        loadData();
    }

    private void clearLabels() {
        tagIdValidationLabel.setText("");
        tagIdValidationLabel.setVisible(false);
    }

    private void clearFields() {
        tagIdTextField.clear();
        tagSpecsTextField.clear();
        tagRegisteredDateTextField.clear();
        bottleCapTable.getSelectionModel().clearSelection();

        for (BottleCap cap : bottleCapTable.getItems()) {
            cap.setItemSelected(false);
        }

        bCAvailabilityStatusChoiceBox.setDisable(false);
        bCAllocationStatusChoiceBox.setDisable(false);
        selectedBottleCapModelData = null;
    }

    public void searchTable(ObservableList<BottleCap> bottleCaps){
        BottleCapServices bottleCapServices = new BottleCapServices();
        SortedList<BottleCap> sortedData = bottleCapServices.searchTable(SearchTextBox, bottleCaps);
        //binding the SortedList to TableView
        sortedData.comparatorProperty().bind(bottleCapTable.comparatorProperty());
        //adding sorted and filtered data to the table
        bottleCapTable.setItems(sortedData);
    }
}
