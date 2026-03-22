package util.query;

import util.utility.Constant;

public class BottleCapQueries {
    public static final String LOAD_DATA_QUERY = "SELECT * FROM bottlecap";
    public static final String LOAD_SPECIFIC_DATA_QUERY = "SELECT * FROM bottlecap WHERE bCId = ?";
    public static final String LOAD_SPECIFIC_BY_TAG_ID_DATA_QUERY = "SELECT * FROM bottlecap WHERE bCTagId = ?";
    public static final String INSERT_DATA_QUERY = "INSERT INTO bottlecap (bCTagId, bCSpecs, bCAvailabilityStatus, bCAllocationStatus) VALUES( ?, ?, ?, ?)";
    public static final String UPDATE_AVAILABILITY_AND_SPECS_BY_ID_QUERY = "UPDATE bottlecap SET bCSpecs = ?, bCAvailabilityStatus = ? WHERE bCId = ?";
    public static final String UPDATE_ALLOCATION_BY_ID_QUERY = "UPDATE bottlecap SET bCAllocationStatus = ? WHERE bCId = ?";
    public static final String UPDATE_ALLOCATION_BY_TAG_ID_QUERY = "UPDATE bottlecap SET bCAllocationStatus = ? WHERE bCTagId = ?";
    public static final String DELETE_DATA_BY_ID_QUERY = "DELETE FROM bottlecap WHERE bCId = ? ";
    public static final String GET_ALL_TAG_IDS_COUNT_QUERY = "SELECT COUNT(*) FROM bottlecap WHERE bCTagId = ?";

    public static final String CREATE_TABLE_IF_NOT_EXISTS = "CREATE TABLE IF NOT EXISTS " + Constant.DATABASE_NAME + ".`bottlecap` (\n" +
            "  `bCId` INT NOT NULL AUTO_INCREMENT,\n" +
            "  `bCTagId` VARCHAR(45) NOT NULL,\n" +
            "  `bCSpecs` VARCHAR(45) NULL,\n" +
            "  `bCRegisteredDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
            "  `bCAvailabilityStatus` VARCHAR(45) NOT NULL,\n" +
            "  `bCAllocationStatus` VARCHAR(45) NOT NULL,\n" +
            "  PRIMARY KEY (`bCId`),\n" +
            "  UNIQUE INDEX `bCTagId_UNIQUE` (`bCTagId` ASC) VISIBLE);\n";

}
