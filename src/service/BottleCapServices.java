package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TextField;
import model.BottleCap;
import util.dbConnect.DBConnection;
import util.query.BottleCapQueries;
import util.systemAlerts.AlertPopUp;
import util.utility.UtilityMethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class BottleCapServices {
    private ObservableList<BottleCap> bottleCapData;

    public BottleCapServices () {
        createTableIfNotExist();
    }

    private void createTableIfNotExist() {
        try {
            Connection conn = DBConnection.getDBConnection();
            PreparedStatement ps = conn.prepareStatement(BottleCapQueries.CREATE_TABLE_IF_NOT_EXISTS);
            ps.execute();
        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
        }
    }

    public  ObservableList<BottleCap> loadData(){
        try {
            Connection conn = DBConnection.getDBConnection();
            bottleCapData = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery(BottleCapQueries.LOAD_DATA_QUERY);

            while (rs.next()) {
                bottleCapData.add(new BottleCap(rs.getInt(1), rs.getString(2),rs.getString(3), UtilityMethod.formatDateTimeStamp(rs.getTimestamp(4)), rs.getString(5),rs.getString(6)));
            }
        } catch (SQLException ex) {
            AlertPopUp.sqlQueryError(ex);
        }
        return bottleCapData;
    }

    public BottleCap loadSpecificData(int id){
        BottleCap bottleCapData = new BottleCap();
        PreparedStatement psLoadBottleCap = null;
        ResultSet rsLoadBottleCap = null;
        Connection conn = DBConnection.getDBConnection();
        try{

            psLoadBottleCap = conn.prepareStatement(BottleCapQueries.LOAD_SPECIFIC_DATA_QUERY);
            psLoadBottleCap.setInt(1, id);
            rsLoadBottleCap = psLoadBottleCap.executeQuery();

            while (rsLoadBottleCap.next()){
                bottleCapData = new BottleCap(rsLoadBottleCap.getInt(1), rsLoadBottleCap.getString(2),rsLoadBottleCap.getString(3),rsLoadBottleCap.getString(4),rsLoadBottleCap.getString(5),rsLoadBottleCap.getString(6));
            }
        }catch (SQLException ex){
            AlertPopUp.sqlQueryError(ex);
        }

        return bottleCapData;
    }

    public BottleCap loadSpecificDataByTagId(int tagId){
        BottleCap bottleCapData = new BottleCap();
        PreparedStatement psLoadBottleCap = null;
        ResultSet rsLoadBottleCap = null;
        Connection conn = DBConnection.getDBConnection();
        try{

            psLoadBottleCap = conn.prepareStatement(BottleCapQueries.LOAD_SPECIFIC_BY_TAG_ID_DATA_QUERY);
            psLoadBottleCap.setInt(1, tagId);
            rsLoadBottleCap = psLoadBottleCap.executeQuery();

            while (rsLoadBottleCap.next()){
                bottleCapData = new BottleCap(rsLoadBottleCap.getInt(1), rsLoadBottleCap.getString(2),rsLoadBottleCap.getString(3),rsLoadBottleCap.getString(4),rsLoadBottleCap.getString(5),rsLoadBottleCap.getString(6));
            }
        }catch (SQLException ex){
            AlertPopUp.sqlQueryError(ex);
        }

        return bottleCapData;
    }

    public boolean insertData(BottleCap bottleCap) throws  Exception{
        PreparedStatement ps = null;
        boolean resultval = false;
        Connection conn = DBConnection.getDBConnection();
        try {

            ps = conn.prepareStatement(BottleCapQueries.INSERT_DATA_QUERY);
            ps.setInt(1,bottleCap.getIntegerBCTagId());
            ps.setString(2,bottleCap.getBCSpecs());
            ps.setString(3, bottleCap.getBCAvailabilityStatus());
            ps.setString(4, bottleCap.getBCAllocationStatus());

            ps.execute();
            AlertPopUp.insertSuccessfully("Bottle Cap Information");
            //supplierViewController.refreshTable();
            resultval = true;


        } catch (SQLException ex) {
            AlertPopUp.insertionFailed(ex, "Bottle Cap Information");
        }
        return resultval;
    }

    public boolean updateAllocation(BottleCap bottleCap) throws Exception {
        PreparedStatement ps = null;
        boolean resultVal = false;
        Connection conn = DBConnection.getDBConnection();
        try {

            ps = conn.prepareStatement(BottleCapQueries.UPDATE_ALLOCATION_BY_ID_QUERY);
            ps.setString(1,bottleCap.getBCAllocationStatus());
            ps.setInt(9,bottleCap.getBCId());

            ps.execute();
            AlertPopUp.updateSuccesfully("Bottle Cap Information");
            resultVal = true;

        } catch (SQLException ex) {
            AlertPopUp.updateFailed(ex, "Bottle Cap Information");

        }
        return resultVal;
    }

    public boolean updateAllocation(List<Integer> bottleCapIds, String allocationStatus) {
        PreparedStatement ps = null;
        boolean resultVal = false;
        Connection conn = DBConnection.getDBConnection();
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(BottleCapQueries.UPDATE_ALLOCATION_BY_ID_QUERY);

            for (Integer capId : bottleCapIds) {
                ps.setString(1,allocationStatus);
                ps.setInt(2, capId);
                ps.addBatch();
            }

            int[] results = ps.executeBatch();

            boolean allSuccess = true;
            for (int res : results) {
                if (res != PreparedStatement.RETURN_GENERATED_KEYS) {
                    allSuccess = false;
                    break;
                }
            }

            if (allSuccess) {
                conn.commit();

                resultVal = true;
            } else {
                conn.rollback();
            }

        } catch (SQLException ex) {
            AlertPopUp.updateFailed(ex, "Bottle Cap Information");
        }
        return resultVal;
    }

    public boolean updateAllocationByTagIds(List<Integer> tagIds, String allocationStatus) {
        PreparedStatement ps = null;
        boolean resultVal = false;
        Connection conn = DBConnection.getDBConnection();
        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(BottleCapQueries.UPDATE_ALLOCATION_BY_TAG_ID_QUERY);

            for (Integer capId : tagIds) {
                ps.setString(1,allocationStatus);
                ps.setInt(2, capId);
                ps.addBatch();
            }

            int[] results = ps.executeBatch();

            boolean allSuccess = true;
            for (int res : results) {
                if (res != PreparedStatement.RETURN_GENERATED_KEYS) {
                    allSuccess = false;
                    break;
                }
            }

            if (allSuccess) {
                conn.commit();

                resultVal = true;
            } else {
                conn.rollback();
            }

        } catch (SQLException ex) {
            AlertPopUp.updateFailed(ex, "Bottle Cap Information");
        }
        return resultVal;
    }

    public boolean updateSpecsAndAvailability(BottleCap bottleCap) throws Exception {
        PreparedStatement ps = null;
        boolean resultVal = false;
        Connection conn = DBConnection.getDBConnection();
        try {

            ps = conn.prepareStatement(BottleCapQueries.UPDATE_AVAILABILITY_AND_SPECS_BY_ID_QUERY);
            ps.setString(1,bottleCap.getBCSpecs());
            ps.setString(2,bottleCap.getBCAvailabilityStatus());
            ps.setInt(3,bottleCap.getBCId());

            ps.execute();
            AlertPopUp.updateSuccesfully("Bottle Cap Information");
            resultVal = true;

        } catch (SQLException ex) {
            AlertPopUp.updateFailed(ex, "Bottle Cap Information");

        }
        return resultVal;
    }

    public Boolean deleteData(int id) throws SQLException {
        boolean resultVal = false;
        Connection conn = DBConnection.getDBConnection();
        PreparedStatement ps = null;
        try{
            BottleCap bottleCap = loadSpecificData(id);
            if(!bottleCap.getBCAllocationStatus().equals("ALLOCATED")) {
                ps = conn.prepareStatement(BottleCapQueries.DELETE_DATA_BY_ID_QUERY);
                ps.setInt(1, id);

                ps.executeUpdate();
                AlertPopUp.deleteSuccessful("Bottle Cap Information");
                resultVal = true;
            } else {
                AlertPopUp.generalError("Bottle Cap already assigned to a bottle. Cannot perform delete action");
            }
        }catch (Exception ex) {
            AlertPopUp.deleteFailed(ex, "Bottle Cap Information");
        }
        return resultVal;
    }

    public String generateUniqueTagId() throws Exception {
        Random random = new Random();
        String tagId;

        while (true) {
            int number = 100000 + random.nextInt(900000);
            tagId = String.valueOf(number);

            Connection conn = DBConnection.getDBConnection();
            PreparedStatement ps = conn.prepareStatement(BottleCapQueries.GET_ALL_TAG_IDS_COUNT_QUERY);
            ps.setString(1, tagId);

            ResultSet rs = ps.executeQuery();
            rs.next();

            if (rs.getInt(1) == 0) {
                break;
            }
        }
        return tagId;
    }

    public SortedList<BottleCap> searchTable(TextField searchTextField, ObservableList<BottleCap> bottleCapData){
        //Wrap the ObservableList in a filtered List (initially display all data)
        FilteredList<BottleCap> filteredData = new FilteredList<>(bottleCapData, b -> true);

        searchTextField.textProperty().addListener((observable,oldValue,newValue) ->{
            filteredData.setPredicate(bottleCap -> {
                //if filter text is empty display all data
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                //comparing search text with table columns one by one
                String lowerCaseFilter = newValue.toLowerCase();

                if(bottleCap.getBCTagId().toLowerCase().contains(lowerCaseFilter)){
                    //return if filter matches data
                    return true;
                }else if(bottleCap.getBCSpecs().toLowerCase().contains(lowerCaseFilter)){
                    //return if filter matches data
                    return true;
                }else if(bottleCap.getBCRegisteredDate().toLowerCase().contains(lowerCaseFilter)){
                    //return if filter matches data
                    return true;
                }else if(bottleCap.getBCAllocationStatus().toLowerCase().contains(lowerCaseFilter)){
                    //return if filter matches data
                    return true;
                }else if(String.valueOf(bottleCap.getBCAvailabilityStatus()).toLowerCase().contains(lowerCaseFilter)){
                    //return if filter matches data
                    return true;
                }else{
                    //have no matchings
                    return false;
                }
            });
        });
        //wrapping the FilteredList in a SortedList
        return new SortedList<>(filteredData);
    }

}
