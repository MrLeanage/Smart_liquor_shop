package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import model.BottleCap;
import model.BottleCapRegister;
import util.dbConnect.DBConnection;
import util.query.BottleCapRegisterQueries;
import util.systemAlerts.AlertPopUp;
import util.utility.UtilityMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BottleCapRegisterServices {

    public BottleCapRegisterServices() {
        createTableIfNotExist();
    }

    private void createTableIfNotExist() {
        try {
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement ps = conn.prepareStatement(BottleCapRegisterQueries.CREATE_TABLE_IF_NOT_EXIST);
            ps.execute();
        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
        }
    }

    // Register multiple products with tags
    public boolean registerProductsWithTags(ObservableList<BottleCapRegister> tagRegisters) {
        boolean resultVal = false;
        try {
            Connection conn = DBConnection.getDBConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(BottleCapRegisterQueries.INSERT_TAG_REGISTER);
            List<Integer> bottleCapTagIds = new ArrayList<>();
            for (BottleCapRegister br : tagRegisters) {
                bottleCapTagIds.add(br.getIntegerTagId());
                ps.setInt(1, br.getIntegerProductId());
                ps.setString(2, String.valueOf(br.getIntegerTagId()));
                ps.setString(3, "REGISTERED");
                ps.addBatch();
            }

            int[] results = ps.executeBatch();

            boolean allSuccess = true;
            for (int res : results) {
                if (res == PreparedStatement.EXECUTE_FAILED) {
                    allSuccess = false;
                    break;
                }
            }

            if (allSuccess) {
                BottleCapServices bottleCapServices = new BottleCapServices();
                boolean allocationStatus = bottleCapServices.updateAllocationByTagIds(bottleCapTagIds, "ALLOCATED");
                if (allocationStatus) {
                    conn.commit();
                    resultVal = true;
                } else {
                    conn.rollback();
                }
            } else {
                conn.rollback();
            }

            resultVal = true;

        } catch (SQLException ex) {
            AlertPopUp.insertionFailed(ex, "Tag Registration");
        }
        return resultVal;
    }

    // Update a product tag registration
    public boolean updateProductWithTag(BottleCapRegister br, int id) {
        boolean resultVal = false;
        try {
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement ps = conn.prepareStatement(BottleCapRegisterQueries.UPDATE_TAG_REGISTER);

            ps.setInt(1, Integer.parseInt(br.getAgencyProductId()));
            ps.setString(2, br.getTagId());
            ps.setString(3, br.getTagAllocationStatus());
            ps.setInt(4, id);

            ps.executeUpdate();
            resultVal = true;

        } catch (SQLException ex) {
            AlertPopUp.updateFailed(ex, "Tag Registration");
        }
        return resultVal;
    }

    // Unregister a product tag
    public boolean unRegisterProductWithTag(BottleCapRegister bottleCapRegister) {
        boolean resultVal = false;
        try {
            Connection conn = DBConnection.getDBConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps = conn.prepareStatement(BottleCapRegisterQueries.UNREGISTER_TAG);

            ps.setString(1, "UN-REGISTERED");
            ps.setInt(2, bottleCapRegister.getId());
            int updateStatus = ps.executeUpdate();

            if(updateStatus == PreparedStatement.RETURN_GENERATED_KEYS) {
                BottleCapServices bottleCapServices = new BottleCapServices();
                List<Integer> bottleCapIds = Collections.singletonList(bottleCapRegister.getIntegerTagId());
                boolean updateAllocationStatus = bottleCapServices.updateAllocationByTagIds(bottleCapIds, "NOT ALLOCATED");
                if(updateAllocationStatus) {
                    conn.commit();
                    resultVal = true;
                } else {
                    conn.rollback();
                }
            }

        } catch (SQLException ex) {
            AlertPopUp.deleteFailed(ex, "Tag Registration");
        }
        return resultVal;
    }

    public ObservableList<BottleCap> loadAvailableTags() {
        ObservableList<BottleCap> availableTags = FXCollections.observableArrayList();
        try {
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement ps = conn.prepareStatement(BottleCapRegisterQueries.LOAD_AVAILABLE_TAGS_QUERY);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                availableTags.add(new BottleCap(rs.getInt(1), rs.getString(2),rs.getString(3), UtilityMethod.formatDateTimeStamp(rs.getTimestamp(4)), rs.getString(5),rs.getString(6)));

            }
        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
        }

        return availableTags;
    }

    // Load all registered products with tags
    public ObservableList<BottleCapRegister> loadRegisteredProductsWithTags() {
        ObservableList<BottleCapRegister> tagRegisters = FXCollections.observableArrayList();
        try {
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement ps = conn.prepareStatement(BottleCapRegisterQueries.LOAD_REGISTERED_TAGS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BottleCapRegister br = new BottleCapRegister(
                        rs.getInt(1),
                        String.valueOf(rs.getInt("APID")),        // agencyProductId
                        rs.getString("APName"),                   // agencyProductName
                        rs.getString("APWeightOfUnit"),           // agencyProductVolumeSpec
                        rs.getString("tagId"),                    // tagId
                        rs.getString("APWeightOfUnit"),           // tagSpecs, using product volume
                        rs.getString("registeredStatus"),
                        rs.getString("createdDate")// created date
                );
                tagRegisters.add(br);
            }

        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
        }
        return tagRegisters;
    }


    public SortedList<BottleCapRegister> searchTable(ObservableList<BottleCapRegister> bottleCapRegistersData, TextField searchTextField) {

        // Wrap the ObservableList in a FilteredList (initially display all data)
        FilteredList<BottleCapRegister> filteredData = new FilteredList<>(bottleCapRegistersData, b -> true);

        // Add listener to the search TextField
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(register -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Display all if search field is empty
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Compare search text with each field
                if (register.getAgencyProductId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (register.getAgencyProductName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (register.getAgencyProductVolumeSpec().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (register.getTagId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (register.getTagSpecs().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (register.getTagAllocationStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false; // No match
                }
            });
        });

        // Wrap the FilteredList in a SortedList
        return new SortedList<>(filteredData);
    }
}