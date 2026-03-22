package util.systemAlerts;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertPopUp {

    // Database connection error
    public static void connectionError(Exception ex){
        ex.printStackTrace(); // Print error to console
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Database Connection Error!..");
        msg.setHeaderText(null);
        msg.setContentText("Database Connection Failed, Exception code in: " + ex);
        msg.showAndWait();
    }

    // Record not found in DB
    public static void sqlRecordNotFound(String text){
        System.err.println("Database Record not Found for " + text);
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Database Record not Found!..");
        msg.setHeaderText(null);
        msg.setContentText("Database Record not Found for " + text);
        msg.showAndWait();
    }

    // SQL query execution error
    public static void sqlQueryError(Exception ex){
        ex.printStackTrace();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("SQL Query Error!..");
        msg.setHeaderText(null);
        msg.setContentText("Query Execution Failed, Exception in: " + ex);
        msg.showAndWait();
    }

    // Session end confirmation (no console needed)
    public static Optional<ButtonType> sessionEndConfirmation(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Do you want to Logout?");
        alert.setHeaderText(null);
        alert.setContentText(text);
        return alert.showAndWait();
    }

    // Exit confirmation (no console needed)
    public static void exitConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Your Action");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to Close Application? Make sure you have no unsaved Data..");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.isPresent() && action.get().equals(ButtonType.OK)){
            Platform.exit();
        }
    }

    // Insert successful (no console needed)
    public static void insertSuccessfully(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successful..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Added Successfully..");
        msg.showAndWait();
    }

    // General success popup (no console needed)
    public static void generalSuccessPopup(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successful..");
        msg.setHeaderText(null);
        msg.setContentText(text);
        msg.showAndWait();
    }

    // Empty insertion failed
    public static void emptyInsertionFailed(String text){
        System.err.println("No Records Found: " + text);
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("No Records Found");
        msg.setHeaderText(null);
        msg.setContentText(text);
        msg.showAndWait();
    }

    // Insertion failed with exception
    public static void insertionFailed(Exception ex, String text){
        ex.printStackTrace();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occurred!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Added, Try Again!..SQL Exception found in: " + ex);
        msg.showAndWait();
    }

    // Update successful (no console needed)
    public static void updateSuccesfully(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successful..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Updated Successfully..");
        msg.showAndWait();
    }

    // Update failed (text only)
    public static void updateFailed(String text){
        System.err.println(text + " not Updated.");
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occurred!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Updated, Try Again!..");
        msg.showAndWait();
    }

    // Update failed with exception
    public static void updateFailed(Exception ex, String text){
        ex.printStackTrace();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occurred!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Updated, Try Again!..SQL Exception found in: " + ex);
        msg.showAndWait();
    }

    // Select row prompt (no console needed)
    public static void selectRow(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Please Select..");
        msg.setHeaderText(null);
        msg.setContentText("Please Select a " + text);
        msg.showAndWait();
    }

    // Select row to update prompt (no console needed)
    public static void selectRowToUpdate(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Please Select..");
        msg.setHeaderText(null);
        msg.setContentText("Please Select a " + text + " record to Update..");
        msg.showAndWait();
    }

    // Delete successful (no console needed)
    public static void deleteSuccessful(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Successful..");
        msg.setHeaderText(null);
        msg.setContentText(text + " Deleted Successfully..");
        msg.showAndWait();
    }

    // Delete failed with exception
    public static void deleteFailed(Exception ex, String text){
        ex.printStackTrace();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occurred!..");
        msg.setHeaderText(null);
        msg.setContentText(text + " not Deleted, Try Again!..SQL Exception found in: " + ex);
        msg.showAndWait();
    }

    // Delete confirmation (no console needed)
    public static Optional<ButtonType> deleteConfirmation(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Your Request to Delete");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to delete selected " + text + "??...");
        return alert.showAndWait();
    }

    // Select row to delete prompt (no console needed)
    public static void selectRowToDelete(String text){
        Alert msg = new Alert(Alert.AlertType.INFORMATION);
        msg.setTitle("Please Select..");
        msg.setHeaderText(null);
        msg.setContentText("Please Select a " + text + " record to Delete..");
        msg.showAndWait();
    }

    // General error with exception
    public static void generalError(Exception ex){
        ex.printStackTrace();
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occurred!..");
        msg.setHeaderText(null);
        msg.setContentText("Error Occurred, Try Again!.. Exception: " + ex);
        msg.showAndWait();
    }

    // General error with message
    public static void generalError(String text){
        System.err.println("General Error: " + text);
        Alert msg = new Alert(Alert.AlertType.ERROR);
        msg.setTitle("Error Occurred!..");
        msg.setHeaderText(null);
        msg.setContentText("Error Occurred While Completing " + text + " request, Try Again!..");
        msg.showAndWait();
    }

    // No record found warning
    public static void noRecordFound(String text){
        Alert msg = new Alert(Alert.AlertType.WARNING);
        msg.setTitle("No Records Found");
        msg.setHeaderText(null);
        msg.setContentText("No " + text + " records found for Your Request");
        msg.showAndWait();
    }
}