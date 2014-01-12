/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Helper;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.Lib.ComboItem;
import OrganizedIn.Lib.Inventory;
import OrganizedIn.UI.SearchPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class HelperSearch {

    public static void loadTableParts(String parentSerialNo, SearchPanel frame) {
        String p_SerialNo = parentSerialNo;

        Inventory i = new Inventory();
        i.getInventoryDetails(p_SerialNo);


        Vector<String> header = new Vector<>();
        header.add("Product Serial No");
        header.add("Name");
        header.add("Brand");
        header.add("Model");


        Vector<String> data1 = new Vector<>();
        data1.add(i.getSerialNo());
        data1.add(i.getName());
        data1.add(i.getBrand());
        data1.add(i.getModel());


        Vector<Vector<String>> data2 = new Vector<>();
        data2.add(data1);
        frame.browseParts_tbl.setModel(new DefaultTableModel(data2, header));

    }

    public static void getLocationDetails(SearchPanel frame) {

        String SerialNo = ComboItem.getValue(frame.serialNO_cmb.getSelectedItem());
        Inventory dao = new Inventory();
        String[] array1 = new String[5];
        dao.setLocationDetails(SerialNo, array1);
        String user = array1[0];
        String location = array1[1];


        frame.user_txt.setText(user);
        frame.location_txt.setText(location);
    }

    public static void getParts(SearchPanel panel, String parent) {
        panel.browseParts_tbl.removeAll();

        String parserial = ComboItem.getValue(panel.parts_cmb.getSelectedItem());

        DefaultTableModel inventory = new DefaultTableModel();
        inventory.addColumn("Serial No");
        inventory.addColumn("Type");
        inventory.addColumn("Brand");
        inventory.addColumn("Model");
        inventory.addColumn("Company Name");
        inventory.addColumn("Address");
        inventory.addColumn("Ram");
        inventory.addColumn("Precessor");
        inventory.addColumn("Hard Disk");
        inventory.addColumn("Printer Type");
        inventory.addColumn("Front");
        inventory.addColumn("Ups");

        System.out.println(parserial);
        String query = "SELECT * FROM Inventory i, supplier s, branch b WHERE i.supplierid = s.supplierid AND i.branchid = b.branchid AND parentserialno = '" + parserial + "'";

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            //  stmt.setString(1, parserial);
            try (ResultSet result = stmt.executeQuery()) {

                while (result.next()) {
                    String serialNo = result.getString("SERIALNO");
                    String type = result.getString("TYPE");
                    String brand = result.getString("BRAND");
                    String model = result.getString("MODEL");
                    String supplier = result.getString("COMPANYNAME");
                    String branch = result.getString("ADDRESS");
                    String ram = result.getString("RAM");
                    String processor = result.getString("PROCESSOR");
                    String hard = result.getString("HARDDISK");
                    String printer_type = result.getString("PRINTER_TYPE");
                    String front = result.getString("FRONT");
                    String ups = result.getString("UPS");

                    Inventory inven = new Inventory();

                    inventory.addRow(inven.createTableRow(serialNo, type, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups));//.addElement(inven);
                }
            }
            panel.browseParts_tbl.setModel(inventory);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static void fillComboBoxes(SearchPanel frame) {

        String query1 = "SELECT DISTINCT parentserialno FROM INVENTORY WHERE parentserialno IS NOT NULL";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {

            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("parentserialno"), rs.getString("parentserialno"));
                frame.parts_cmb.addItem(cbi);
            }

        } catch (SQLException ex) {
            System.out.println(ex + "Function: fillComboBoxes in Class: HelperSearch");
        }

        query1 = "SELECT * FROM SUPPLIER";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {

            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("SUPPLIERID"), rs.getString("COMPANYNAME"));
                //frame.Inven_ManagePcSupNameCbx.addItem(cbi);

            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(ex + "Function: fillComboBoxes in Class: HelperGlobal");
        }

        query1 = "SELECT * FROM BRANCH";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {


            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("BRANCHID"), rs.getString("ADDRESS"));
                frame.branch_cmb.addItem(cbi);


            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(ex + "Function: fillComboBoxes in Class: HelperGlobal");
        }

        query1 = "SELECT DISTINCT TYPE FROM INVENTORY where type is not NULL";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {


            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("TYPE"), rs.getString("TYPE"));
                frame.type_cmb.addItem(cbi);


            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(ex + "Function: fillComboBoxes in Class: HelperGlobal");
        }

        query1 = "SELECT  DISTINCT BRAND FROM INVENTORY where BRAND is not NULL";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {


            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("BRAND"), rs.getString("BRAND"));
                frame.brand_cmb.addItem(cbi);


            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(ex + "Function: fillComboBoxes in Class: HelperGlobal");
        }
    }
}
