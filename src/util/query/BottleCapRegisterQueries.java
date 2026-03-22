package util.query;

import util.utility.Constant;

public class BottleCapRegisterQueries {

    // Create table if not exists (fixed foreign key types and syntax)
    public static final String CREATE_TABLE_IF_NOT_EXIST =
            "CREATE TABLE IF NOT EXISTS " + Constant.DATABASE_NAME + ".`tag_register` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `productId` INT NOT NULL,\n" +
                    "  `tagId` VARCHAR(45) NOT NULL,\n" +
                    "  `createdDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,\n" +
                    "  `registeredStatus` VARCHAR(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `tagId_UNIQUE` (`tagId`),\n" +
                    "  CONSTRAINT `fk_product` FOREIGN KEY (`productId`) REFERENCES " + Constant.DATABASE_NAME + ".`agencyproduct`(`APID`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");";
    // Insert a new tag registration
    public static final String INSERT_TAG_REGISTER =
            "INSERT INTO tag_register (productId, tagId, registeredStatus) VALUES (?, ?, ?)";

    // Update tag registration (e.g., change registeredStatus)
    public static final String UPDATE_TAG_REGISTER =
            "UPDATE tag_register SET productId = ?, tagId = ?, registeredStatus = ? WHERE id = ?";

    // Unregister a tag (delete)
    public static final String UNREGISTER_TAG =
            "UPDATE tag_register SET registeredStatus = ? WHERE id = ?";

    // Load all registered products with tags
    public static final String LOAD_REGISTERED_TAGS =
            "SELECT tr.id, ap.APID, ap.APName, ap.APWeightOfUnit, tr.tagId, tr.registeredStatus, tr.createdDate " +
                    "FROM tag_register tr " +
                    "INNER JOIN agencyproduct ap ON tr.productId = ap.APID " +
                    "ORDER BY tr.createdDate ASC";

    // Optional: check if tagId exists
    public static final String CHECK_TAG_EXISTS =
            "SELECT COUNT(*) FROM tag_register WHERE tagId = ?";

    // Load available tags that are not registered
    public static final String LOAD_AVAILABLE_TAGS_QUERY =
            "SELECT * " +
                    "FROM bottlecap " +
                    "WHERE bCAllocationStatus = 'NOT ALLOCATED' AND bCAvailabilityStatus = 'AVAILABLE';";
}