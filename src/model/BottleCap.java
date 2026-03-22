package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.utility.UtilityMethod;

public class BottleCap {
    private int bCId = 0;
    private BooleanProperty itemSelected = new SimpleBooleanProperty(false);;
    private StringProperty bCTagId = null;
    private StringProperty bCSpecs = null;
    private StringProperty bCRegisteredDate = null;
    private StringProperty bCAvailabilityStatus = null;
    private StringProperty bCAllocationStatus = null;

    public BottleCap() {}

    public BottleCap(int bCId, String bCTagId, String bCSpecs, String bCRegisteredDate, String bCAvailabilityStatus, String bCAllocationStatus) {
        this.bCId = bCId;
        this.bCTagId = new SimpleStringProperty(UtilityMethod.addPrefix("BC-", String.valueOf(bCTagId)));
        this.bCSpecs = new SimpleStringProperty(bCSpecs);
        this.bCRegisteredDate = new SimpleStringProperty(bCRegisteredDate);
        this.bCAvailabilityStatus = new SimpleStringProperty(bCAvailabilityStatus);
        this.bCAllocationStatus = new  SimpleStringProperty(bCAllocationStatus);
    }

    public BooleanProperty itemSelectedProperty() {
        return itemSelected;
    }

    public boolean isItemSelected() {
        return itemSelected.get();
    }

    public void setItemSelected(boolean value) {
        itemSelected.set(value);
    }

    public int getBCId() {
        return bCId;
    }

    public void setBCId(int bCId) {
        this.bCId = bCId;
    }

    public Integer getIntegerBCTagId(){
        return UtilityMethod.seperateID(bCTagId.get());
    }

    public String getBCTagId(){
        return bCTagId.get();
    }

    public void setBCTagId(String bCTagId){
        this.bCTagId = new SimpleStringProperty(bCTagId);
    }

    public StringProperty bCTagIdProperty(){
        return bCTagId;
    }

    public String getBCSpecs(){
        return bCSpecs.get();
    }

    public void setBCSpecs(String bCSpecs){
        this.bCSpecs = new SimpleStringProperty(bCSpecs);
    }

    public StringProperty getBCSpecsProperty(){
        return bCSpecs;
    }

    public String getBCRegisteredDate(){
        return bCRegisteredDate.get();
    }

    public void setBCRegisteredDate(String bCRegisteredDate){
        this.bCRegisteredDate = new SimpleStringProperty(bCRegisteredDate);
    }

    public StringProperty bCRegisteredDateProperty(){
        return bCRegisteredDate;
    }

    public String getBCAvailabilityStatus(){
        return bCAvailabilityStatus.get();
    }

    public void setBCAvailabilityStatus(String bCAvailabilityStatus){
        this.bCAvailabilityStatus = new SimpleStringProperty(bCAvailabilityStatus);
    }

    public StringProperty bCAvailabilityStatusProperty(){
        return bCAvailabilityStatus;
    }

    public String getBCAllocationStatus(){
        return bCAllocationStatus.get();
    }

    public void setBCAllocationStatus(String bCAllocationStatus){
        this.bCAllocationStatus = new SimpleStringProperty(bCAllocationStatus);
    }

    public StringProperty bCAllocationStatusProperty(){
        return bCAllocationStatus;
    }
}
