package util.utility;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JRViewer;

import util.dbConnect.DBConnection;
import util.systemAlerts.AlertPopUp;

import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.util.*;


public class PrintReport extends JFrame{
    public void printBill(Integer billNo) {
        Connection conn = DBConnection.getDBConnection();// Database Connection
        try {
            //HashMap parameter = new HashMap();
            HashMap<String, Object> parameter = new HashMap<>();
            parameter.put("billNo",billNo);

            JasperDesign jd = JRXmlLoader.load(new File("").getAbsolutePath()+"/src/view/JRXMLReports/Bill.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, parameter, conn);
            JRViewer viewer = new JRViewer(JasperPrint);
            //viewer.setOpaque(true);
            viewer.setOpaque(true);
            viewer.setVisible(true);

            this.add(viewer);
            this.setSize(600,800); // Frame size
            this.setVisible(true);

        } catch (Exception e) {
            AlertPopUp.generalError(e);
            e.printStackTrace();
        }

    }

    public void printBottleCapBarcodes(List<String> bottleCapCodes) {
        try {
            // Convert list of Strings to list of beans
            List<Map<String, Object>> barcodeList = new ArrayList<>();
            for (String code : bottleCapCodes) {
                Map<String, Object> row = new HashMap<>();
                row.put("barcodeValue", code);
                barcodeList.add(row);
            }

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(barcodeList);

            HashMap<String, Object> parameters = new HashMap<>();
            // Add any parameters you need
            parameters.put("ReportTitle", "Bottle Cap Barcodes");

            JasperDesign jd = JRXmlLoader.load(new File("").getAbsolutePath() + "/src/view/JRXMLReports/PrintBottleCapBarcodes.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jd);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            JRViewer viewer = new JRViewer(jasperPrint);
            viewer.setOpaque(true);
            viewer.setVisible(true);

            this.add(viewer);
            this.setSize(600, 800);
            this.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
            AlertPopUp.generalError(e);
        }
    }
}
