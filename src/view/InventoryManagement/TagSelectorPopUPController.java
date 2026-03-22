package view.InventoryManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.BottleCap;
import service.BottleCapServices;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class TagSelectorPopUPController implements Initializable {

    @FXML
    private TextField SearchTextBox;

    @FXML
    private TableView<BottleCap> tagSelectionTable;

    @FXML
    private TableColumn<BottleCap, String> tagIdColumn;

    @FXML
    private TableColumn<BottleCap, String> availabilityColumn;

    @FXML
    private TableColumn<BottleCap, String> allocationStatusColumn;

    @FXML
    private TableColumn<BottleCap, String> actionColumn;

    @FXML
    private TableView<BottleCap> tagSelectionCart;

    @FXML
    private TableColumn<BottleCap, String> sCTagIdColumn;

    @FXML
    private TableColumn<BottleCap, String> tCAllocationStatusColumn;

    @FXML
    private TableColumn<BottleCap, String> tCActionColumn;

    @FXML
    private Button tagSelectionCloseButton;

    private BottleCapRegisterController bottleCapRegisterController;
    private final BottleCapServices bottleCapServices = new BottleCapServices();

    private final ObservableList<BottleCap> availableList = FXCollections.observableArrayList();
    private final ObservableList<BottleCap> cartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadRootWindowController();
        loadAvailableBottleCaps();
        setupTables();
        setupSearch();
    }

    private void loadRootWindowController() {
        bottleCapRegisterController = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BottleCapRegister.fxml"));
            Parent root = (Parent) loader.load();
            bottleCapRegisterController = loader.getController();
            cartList.setAll(bottleCapRegisterController.getCurrentSelectedBottleCaps());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    // ================= SETUP TABLES =================
    private void setupTables() {
        tagIdColumn.setCellValueFactory(new PropertyValueFactory<>("bCTagId"));
        availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("bCAvailabilityStatus"));
        allocationStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bCAllocationStatus"));

        actionColumn.setCellFactory(param -> new TableCell<BottleCap, String>() {
            private final Button addBtn = new Button("Add");

            {
                addBtn.setOnAction(event -> {
                    BottleCap data = getTableView().getItems().get(getIndex());

                    availableList.remove(data);
                    cartList.add(data);

                    tagSelectionCart.refresh();
                    tagSelectionTable.refresh();
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : addBtn);
            }
        });

        // 🔹 Cart table
        tagSelectionCart.setItems(cartList);

        sCTagIdColumn.setCellValueFactory(new PropertyValueFactory<>("bCTagId"));
        tCAllocationStatusColumn.setCellValueFactory(new PropertyValueFactory<>("bCAllocationStatus"));

        tCActionColumn.setCellFactory(param -> new TableCell<BottleCap, String>() {
            private final Button removeBtn = new Button("Remove");

            {
                removeBtn.setOnAction(event -> {
                    BottleCap data = getTableView().getItems().get(getIndex());

                    cartList.remove(data);
                    availableList.add(data);

                    tagSelectionCart.refresh();
                    tagSelectionTable.refresh();
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : removeBtn);
            }
        });

        tagSelectionTable.setItems(availableList);
    }

    // ================= LOAD DATA =================
    private void loadAvailableBottleCaps() {

        ObservableList<BottleCap> allData = bottleCapServices.loadData();

        availableList.setAll(
                allData.stream()
                        .filter(bottleCap ->
                                "NOT ALLOCATED".equals(bottleCap.getBCAllocationStatus()) &&
                                        "AVAILABLE".equals(bottleCap.getBCAvailabilityStatus())
                        )
                        .collect(Collectors.toList())
        );

        if(!cartList.isEmpty()){
            availableList.removeIf(cap ->
                    cartList.stream()
                            .anyMatch(cart -> Objects.equals(cart.getIntegerBCTagId(), cap.getIntegerBCTagId()))
            );
        }
    }

    // ================= SEARCH =================
    private void setupSearch() {

        SearchTextBox.textProperty().addListener((obs, oldVal, newVal) -> {

            if (newVal == null || newVal.isEmpty()) {
                tagSelectionTable.setItems(availableList);
                return;
            }

            ObservableList<BottleCap> filtered = availableList.stream()
                    .filter(bottleCap ->
                            bottleCap.getBCTagId().toLowerCase().contains(newVal.toLowerCase())
                    )
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            tagSelectionTable.setItems(filtered);
        });
    }

    // ================= ACTIONS =================

    @FXML
    void clearCart(ActionEvent event) {
        if(!cartList.isEmpty()) {
            availableList.addAll(cartList);
        }
        cartList.clear();
    }

    @FXML
    void closeButton(ActionEvent event) {
        closeStage();
    }

    @FXML
    void returnTagSelection(ActionEvent event) throws IOException {
        if(bottleCapRegisterController != null && bottleCapRegisterController.setSelectedBottleTags(cartList)){
            closeStage();
        }
    }

    void closeStage(){
        Stage stage = (Stage) tagSelectionCloseButton.getScene().getWindow();
        stage.close();
    }
}