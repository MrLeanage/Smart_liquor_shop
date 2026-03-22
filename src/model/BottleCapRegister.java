package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.utility.UtilityMethod;

public class BottleCapRegister {

    private Integer id; // Auto-increment DB primary key, not shown in UI

    private StringProperty agencyProductId = null;
    private StringProperty agencyProductName = null;
    private StringProperty agencyProductVolumeSpec = null;
    private StringProperty tagId = null;
    private StringProperty tagSpecs = null;
    private StringProperty tagAllocationStatus = null;
    private StringProperty createdDate = null;

    // Default constructor
    public BottleCapRegister() {}

    // Constructor without createdDate
    public BottleCapRegister(Integer id, String agencyProductId, String agencyProductName, String agencyProductVolumeSpec,
                             String tagId, String tagSpecs, String tagAllocationStatus, String createdDate) {
        this.id = id;
        this.agencyProductId = new SimpleStringProperty(agencyProductId);
        this.agencyProductName = new SimpleStringProperty(agencyProductName);
        this.agencyProductVolumeSpec = new SimpleStringProperty(agencyProductVolumeSpec);
        this.tagId = new SimpleStringProperty(UtilityMethod.addPrefix("BC-", String.valueOf(tagId)));
        this.tagSpecs = new SimpleStringProperty(tagSpecs);
        this.tagAllocationStatus = new SimpleStringProperty(tagAllocationStatus);
        this.createdDate = new SimpleStringProperty(createdDate);
    }

    // ------------------------
    // Getters & Setters
    // ------------------------

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgencyProductId() {
        return agencyProductId.get();
    }

    public Integer getIntegerProductId(){
        return UtilityMethod.seperateID(agencyProductId.get());
    }

    public void setAgencyProductId(String agencyProductId) {
        this.agencyProductId = new SimpleStringProperty(agencyProductId);
    }

    public StringProperty agencyProductIdProperty() {
        return agencyProductId;
    }

    public String getAgencyProductName() {
        return agencyProductName.get();
    }

    public void setAgencyProductName(String agencyProductName) {
        this.agencyProductName = new SimpleStringProperty(agencyProductName);
    }

    public StringProperty agencyProductNameProperty() {
        return agencyProductName;
    }

    public String getAgencyProductVolumeSpec() {
        return agencyProductVolumeSpec.get();
    }

    public void setAgencyProductVolumeSpec(String agencyProductVolumeSpec) {
        this.agencyProductVolumeSpec = new SimpleStringProperty(agencyProductVolumeSpec);
    }

    public StringProperty agencyProductVolumeSpecProperty() {
        return agencyProductVolumeSpec;
    }

    public String getTagId() {
        return tagId.get();
    }

    public Integer getIntegerTagId() {
        return UtilityMethod.seperateID(tagId.get());
    }

    public void setTagId(String tagId) {
        this.tagId = new SimpleStringProperty(tagId);
    }

    public StringProperty tagIdProperty() {
        return tagId;
    }

    public String getTagSpecs() {
        return tagSpecs.get();
    }

    public void setTagSpecs(String tagSpecs) {
        this.tagSpecs = new SimpleStringProperty(tagSpecs);
    }

    public StringProperty tagSpecsProperty() {
        return tagSpecs;
    }

    public String getTagAllocationStatus() {
        return tagAllocationStatus.get();
    }

    public void setTagAllocationStatus(String tagAllocationStatus) {
        this.tagAllocationStatus = new SimpleStringProperty(tagAllocationStatus);
    }

    public StringProperty tagAllocationStatusProperty() {
        return tagAllocationStatus;
    }

    public String getCreatedDate() {
        return createdDate.get();
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = new SimpleStringProperty(createdDate);
    }

    public StringProperty createdDateProperty() {
        return createdDate;
    }
}