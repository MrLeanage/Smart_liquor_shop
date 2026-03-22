package view.InventoryManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AgencyProduct;
import model.BottleCap;
import model.BottleCapRegister;
import service.BottleCapRegisterServices;
import util.systemAlerts.AlertPopUp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class BottleCapRegisterController implements Initializable {

    @FXML
    private AnchorPane rootpane;

    @FXML
    private TextField SearchTextBox;

    @FXML
    private TableView<BottleCapRegister> bottleRegisterTable;

    @FXML
    private TableColumn<BottleCapRegister, String> productIdColumn;

    @FXML
    private TableColumn<BottleCapRegister, String> productNameColumn;

    @FXML
    private TableColumn<BottleCapRegister, String> volumeColumn;

    @FXML
    private TableColumn<BottleCapRegister, String> tagIdColumn;

    @FXML
    private TableColumn<BottleCapRegister, String> tagRegisteredColumn;

    @FXML
    private TableColumn<BottleCapRegister, String> allocationColumn;

    @FXML
    private TableView<BottleCap> tagSelectionCart;

    @FXML
    private TableColumn<BottleCap, String> sCTagIdColumn;

    @FXML
    private TableColumn<BottleCap, String> tCAllocationStatusColumn;

    @FXML
    private TableColumn<BottleCap, String> tCActionColumn;

    @FXML
    private ComboBox<String> registrationStatusSortComboBox;

    @FXML
    private TextField availabletagCountTextField;

    @FXML
    private TextField productNameTextField;

    @FXML
    private TextField productIdTextField;

    @FXML
    private TextField selectedTagCountTextField;

    @FXML
    private Label productSelectionValidationLabel;

    @FXML
    private Label attachTagSelectionValidationLabel;

    private BottleCapRegisterServices bottleCapRegisterServices;

    private ObservableList<BottleCap> availableTags;

    private static AgencyProduct selectedAgencyProduct;

    private static final ObservableList<BottleCap> selectedBottleCaps = FXCollections.observableArrayList();

    private ObservableList<BottleCapRegister> selectedTagsForProducts;

    private BottleCapRegister selectedBottleCapRegister;

    private ObservableList<BottleCapRegister> registeredTags = FXCollections.observableArrayList();

    private final ObservableList<BottleCapRegister> filteredRegisteredTags = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bottleCapRegisterServices = new BottleCapRegisterServices();
        setupTables();

        ObservableList<String> registrationStatusItemList = FXCollections.observableArrayList("ALL","REGISTERED", "UN-REGISTERED");
        registrationStatusSortComboBox.setItems(registrationStatusItemList);
        registrationStatusSortComboBox.setValue("REGISTERED");

        loadAvailableTags();
        loadRegisteredProductsWithTags();
        clearValidationLabels();
    }

    @FXML
    public void refreshByAllocationStatus(ActionEvent event) {
        filteredRegisteredTags.clear();
        filteredRegisteredTags.addAll(filterBottleCapRecords(registeredTags));
        bottleRegisterTable.refresh();
    }

    private void clearValidationLabels() {
        productSelectionValidationLabel.setVisible(false);
        attachTagSelectionValidationLabel.setVisible(false);
    }

    // Load available tags that are not yet attached to any product
    private void loadAvailableTags() {
        availableTags = bottleCapRegisterServices.loadAvailableTags();
        availabletagCountTextField.setText(String.valueOf(availableTags.size()));
    }

    // Load all registered products with tags
    private void loadRegisteredProductsWithTags() {
        registeredTags = bottleCapRegisterServices.loadRegisteredProductsWithTags();

        filteredRegisteredTags.clear();
        filteredRegisteredTags.addAll(filterBottleCapRecords(registeredTags));
        bottleRegisterTable.refresh();
    }

    private void setupTables(){

        bottleRegisterTable.setItems(filteredRegisteredTags);
        bottleRegisterTable.refresh();

        // Setup columns
        productIdColumn.setCellValueFactory(cellData -> cellData.getValue().agencyProductIdProperty());
        productNameColumn.setCellValueFactory(cellData -> cellData.getValue().agencyProductNameProperty());
        volumeColumn.setCellValueFactory(cellData -> cellData.getValue().agencyProductVolumeSpecProperty());
        tagIdColumn.setCellValueFactory(cellData -> cellData.getValue().tagIdProperty());
        tagRegisteredColumn.setCellValueFactory(cellData -> cellData.getValue().createdDateProperty());
        allocationColumn.setCellValueFactory(cellData -> cellData.getValue().tagAllocationStatusProperty());

        // Enable search
        SortedList<BottleCapRegister> sortedData = bottleCapRegisterServices.searchTable(filteredRegisteredTags, SearchTextBox);
        sortedData.comparatorProperty().bind(bottleRegisterTable.comparatorProperty());
        bottleRegisterTable.setItems(sortedData);
        bottleRegisterTable.refresh();

        sCTagIdColumn.setCellValueFactory(new PropertyValueFactory<>("bCTagId"));
        tCAllocationStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bCAllocationStatus"));

        tagSelectionCart.setItems(selectedBottleCaps);
        tagSelectionCart.refresh();
    }

    private ObservableList<BottleCapRegister> filterBottleCapRecords(ObservableList<BottleCapRegister> registeredTags){
        if(registrationStatusSortComboBox.getValue().equals("ALL")) {
            return registeredTags;
        } else {
            return registeredTags
                    .stream()
                    .filter(bottleCapRegister -> registrationStatusSortComboBox.getValue().equals(bottleCapRegister.getTagAllocationStatus()))
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));
        }
    }

    @FXML
    void register(ActionEvent event) {
        clearValidationLabels();

        if (productIdTextField.getText().isEmpty()) {
            productSelectionValidationLabel.setVisible(true);
            productSelectionValidationLabel.setText("Please select a product first.");
            return;
        }

        if (selectedBottleCaps.isEmpty()) {
            attachTagSelectionValidationLabel.setVisible(true);
            attachTagSelectionValidationLabel.setText("No available tags to attach.");
            return;
        }

        selectedTagsForProducts = FXCollections.observableArrayList();
        for (int i = 0; i < selectedBottleCaps.size(); i++) {
            BottleCap cap = selectedBottleCaps.get(i);
            BottleCapRegister register = new BottleCapRegister(
                    0,
                    productIdTextField.getText(),
                    productNameTextField.getText(),
                    "Weight Spec",
                    String.valueOf(cap.getIntegerBCTagId()),
                    cap.getBCSpecs(),
                    cap.getBCAvailabilityStatus(),
                    ""
            );
            selectedTagsForProducts.add(register);
        }
        boolean result = bottleCapRegisterServices.registerProductsWithTags(selectedTagsForProducts);
        if (result) {
            AlertPopUp.insertSuccessfully("Tags attached to product");
            clearFields();
            loadAvailableTags();
            loadRegisteredProductsWithTags();
            clearFields();
        }
    }

    @FXML
    void unRegisterTag(ActionEvent event) {
        if (selectedBottleCapRegister == null) {
            AlertPopUp.generalError("Please select a tag to unregister.");
            return;
        }

        boolean result = bottleCapRegisterServices.unRegisterProductWithTag(selectedBottleCapRegister);
        if (result) {
            AlertPopUp.generalSuccessPopup("Tag unregistered successfully.");
            clearFields();
            loadAvailableTags();
            loadRegisteredProductsWithTags();

        }
    }


    @FXML
    private void browseProduct(ActionEvent event){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AgencyProductPopUP.fxml"));

        try{
            loader.load();

        }catch (IOException ex){
            Logger.getLogger(AgencyProductPopUPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AgencyProductPopUPController agencyProductPopUPController = loader.getController();

        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setResizable(false);
        stage.showAndWait();
        try{
            productIdTextField.setText(selectedAgencyProduct.getaPID());
            productNameTextField.setText(selectedAgencyProduct.getaPName());
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    private void openTagSelectionWindow(ActionEvent event){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("TagSelectorPopUp.fxml"));

        try{
            loader.load();

        }catch (IOException ex){
            Logger.getLogger(TagSelectorPopUPController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TagSelectorPopUPController tagSelectorPopUPController = loader.getController();

        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setResizable(false);
        stage.showAndWait();
        try{
            selectedTagCountTextField.setText(String.valueOf(selectedBottleCaps.size()));
            availabletagCountTextField.setText(String.valueOf(availableTags.size() - selectedBottleCaps.size()));
            tagSelectionCart.setItems(selectedBottleCaps);
            tagSelectionCart.refresh();
        }catch(NullPointerException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void setSelectedData(MouseEvent event) {
        selectedBottleCapRegister = bottleRegisterTable.getSelectionModel().getSelectedItem();
        if (selectedBottleCapRegister == null) return;

        productIdTextField.setText(selectedBottleCapRegister.getAgencyProductId());
        productNameTextField.setText(selectedBottleCapRegister.getAgencyProductName());
    }

    @FXML
    void clearFields(ActionEvent event) {
        clearFields();
    }

    private void clearFields(){
        productIdTextField.clear();
        productNameTextField.clear();
        selectedTagCountTextField.clear();
        clearValidationLabels();
        selectedAgencyProduct = null;
        selectedTagsForProducts = null;
        selectedBottleCapRegister = null;
        selectedBottleCaps.clear();
    }

    public boolean setSelectedProduct(AgencyProduct agencyProduct){
        try{
            selectedAgencyProduct = agencyProduct;
            return true;
        } catch (NullPointerException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean setSelectedBottleTags(ObservableList<BottleCap> bottleCaps){
        try{
            selectedBottleCaps.setAll(bottleCaps);
            return true;
        } catch (NullPointerException ex){
            ex.printStackTrace();
            return false;
        }
    }

    public ObservableList<BottleCap> getCurrentSelectedBottleCaps(){
        return selectedBottleCaps;
    }
}