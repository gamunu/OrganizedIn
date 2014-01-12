/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Helper;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.Lib.ComboItem;
import OrganizedIn.Lib.Inventory;
import OrganizedIn.UI.InventoryPanel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gamunu
 */
public class HelperInventory {
    //Locd inventory items to inventory table

    public static void loadInventoryTable(InventoryPanel frame) {
        frame.Inven_InvenTlbl.removeAll();
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

        try (Connection conn = DataAccess.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery("SELECT * FROM Inventory i, supplier s, branch b WHERE i.supplierid = s.supplierid AND i.branchid = b.branchid")) {

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

            frame.Inven_InvenTlbl.setModel(inventory);
        } catch (SQLException ex) {
        }
    }

    //Add inventory pc
    public static void addPCToInventory(InventoryPanel frame) {
        String serialNo = ComboItem.getValue(frame.Inven_ManagePCSerialNoCbx.getSelectedItem());
        String type = "PC";
        String name = frame.Inven_ManagePcNameTxt.getText();
        String brand = frame.Inven_ManagePcBrandTxt.getText();
        String model = frame.Inven_ManagePcModelTxt.getText();

        String supplier = ComboItem.getValue(frame.Inven_ManagePcSupNameCbx.getSelectedItem());//Inven_ManagePcSupNameTxt.getText();
        String branch = ComboItem.getValue(frame.Inven_ManagePcBranNameCbx.getSelectedItem());//Inven_ManagePcBranNameTxt.getText();
        String ram = frame.Inven_ManagePcRamTxt.getText();
        String processor = frame.Inven_ManagePcProceTxt.getText();
        String hard = frame.Inven_ManagePcHardTxt.getText();
        String printer_type = null;
        String front = null;
        String ups = null;

        Inventory inven = new Inventory();
        String it = inven.addInventory(serialNo, name, type, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups);
        System.err.println(it);
        if (it != null) {
            JOptionPane.showMessageDialog(frame, "Insertion Sccessfull");
            HelperInventory.loadInventoryTable(frame);
            frame.Inven_ManagePcNameTxt.setText("");
            frame.Inven_ManagePcBrandTxt.setText("");
            frame.Inven_ManagePcModelTxt.setText("");
            frame.Inven_ManagePcRamTxt.setText("");
            frame.Inven_ManagePcProceTxt.setText("");
            frame.Inven_ManagePcRamTxt.setText("");
            frame.Inven_ManagePcHardTxt.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Insertion Faild");
        }

    }

    //addLaptop to inventory
    public static void addLaptopToInventory(InventoryPanel frame) {

        String serialNo = ComboItem.getValue(frame.Inven_ManageLapSerialNoCbx.getSelectedItem());
        String type = "LAPTOP";
        String name = frame.Inven_ManageLapNameTxt.getText();
        String brand = frame.Inven_ManageLapBrandTxt.getText();
        String model = frame.Inven_ManageLapModelTxt.getText();
        String supplier = ComboItem.getValue(frame.Inven_ManageLapSupNameCbx.getSelectedItem());//Inven_ManagePcSupNameTxt.getText();
        String branch = ComboItem.getValue(frame.Inven_ManageLapBranNameCbx.getSelectedItem());//Inven_ManagePcBranNameTxt.getText();
        String ram = frame.Inven_ManageLapRamTxt.getText();
        String processor = frame.Inven_ManageLapProceTxt.getText();
        String hard = frame.Inven_ManageLapHardTxt.getText();
        String printer_type = null;
        String front = null;
        String ups = null;

        Inventory inven = new Inventory();
        String it = inven.addInventory(serialNo, name, type, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups);
        System.err.println(it);
        if (it != null) {
            JOptionPane.showMessageDialog(frame, "Insertion Sccessfull");
            HelperInventory.loadInventoryTable(frame);
        } else {
            JOptionPane.showMessageDialog(frame, "Insertion Faild");
        }
    }

    public static void addPrinterToInventory(InventoryPanel frame) {

        String serialNo = ComboItem.getValue(frame.Inven_ManagePrinSerialNoCbx.getSelectedItem());
        String type = "PRINTER";
        String name = frame.Inven_ManagePrinNameTxt.getText();
        String brand = frame.Inven_ManagePrinBrandTxt.getText();
        String model = frame.Inven_ManagePrinModelTxt.getText();
        String supplier = ComboItem.getValue(frame.Inven_ManagePrinSupNameCbx.getSelectedItem());//Inven_ManagePcSupNameTxt.getText();
        String branch = ComboItem.getValue(frame.Inven_ManagePriBranNameCbx.getSelectedItem());
        String ram = null;
        String processor = null;
        String hard = null;
        String printer_type = ComboItem.getValue(frame.Inven_ManagePrinTypeCbx.getSelectedItem());
        String front = ComboItem.getValue(frame.Inven_ManagePrinFrontCbx.getSelectedItem());
        String ups = frame.Inven_ManagePrinUpsTxt.getText();

        Inventory inven = new Inventory();
        String it = inven.addInventory(serialNo, name, type, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups);
        System.err.println(it);
        if (it != null) {
            JOptionPane.showMessageDialog(frame, "Insertion Sccessfull");
            HelperInventory.loadInventoryTable(frame);
        } else {
            JOptionPane.showMessageDialog(frame, "Insertion Faild");
        }
    }

    public static void deleteInventoryItem(InventoryPanel frame) {
        int row = frame.Inven_InvenTlbl.getSelectedRow();
        String serialno = frame.Inven_InvenTlbl.getValueAt(row, 0).toString();

        Inventory inven = new Inventory();
        Boolean success = inven.sqlDeleteInventory(serialno);
        //Refresh inventory table
        if (success == true) {
            JOptionPane.showMessageDialog(frame, "Deletion Sccessfull");
            HelperInventory.loadInventoryTable(frame);
        } else {
            JOptionPane.showMessageDialog(frame, "Deletion Faild");
        }
    }

    public static void UpdateInventoryPC(InventoryPanel frame) {
        String serialNo = ComboItem.getValue(frame.Inven_ManagePCSerialNoCbx.getSelectedItem());
        String type = "PC";
        String name = frame.Inven_ManagePcNameTxt.getText();
        String brand = frame.Inven_ManagePcBrandTxt.getText();
        String model = frame.Inven_ManagePcModelTxt.getText();
        String supplier = ComboItem.getValue(frame.Inven_ManagePcSupNameCbx.getSelectedItem());//Inven_ManagePcSupNameTxt.getText();
        String branch = ComboItem.getValue(frame.Inven_ManagePcBranNameCbx.getSelectedItem());
        String ram = frame.Inven_ManagePcRamTxt.getText();
        String processor = frame.Inven_ManagePcProceTxt.getText();
        String hard = frame.Inven_ManagePcHardTxt.getText();
        String printer_type = null;
        String front = null;
        String ups = null;

        Inventory inven = new Inventory();
        Boolean it = inven.SqlUpdateInventory(serialNo, type, name, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups);
        System.err.println(it);
        if (it == true) {
            JOptionPane.showMessageDialog(frame, "Sccessfully updated");
            HelperInventory.loadInventoryTable(frame);
        } else {
            JOptionPane.showMessageDialog(frame, "Update Faild");
        }
    }

    /**
     *
     * @param frame
     */
    public static void UpdateInventoryPrinter(InventoryPanel frame) {
        String serialNo = ComboItem.getValue(frame.Inven_ManagePrinSerialNoCbx.getSelectedItem());
        String type = "Printer";
        String name = frame.Inven_ManagePrinNameTxt.getText();
        String brand = frame.Inven_ManagePrinBrandTxt.getText();
        String model = frame.Inven_ManagePrinModelTxt.getText();
        String supplier = ComboItem.getValue(frame.Inven_ManagePrinSupNameCbx.getSelectedItem());//Inven_ManagePcSupNameTxt.getText();
        String branch = ComboItem.getValue(frame.Inven_ManagePriBranNameCbx.getSelectedItem());
        String ram = null;
        String processor = null;
        String hard = null;
        String printer_type = ComboItem.getValue(frame.Inven_ManagePrinTypeCbx.getSelectedItem());
        String front = ComboItem.getValue(frame.Inven_ManagePrinFrontCbx.getSelectedItem());
        String ups = frame.Inven_ManagePrinUpsTxt.getText();

        Inventory inven1 = new Inventory();
        Boolean it1 = inven1.SqlUpdateInventory(serialNo, type, name, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups);
        System.err.println(it1);
        if (it1 == true) {
            JOptionPane.showMessageDialog(frame, "Sccessfully updated");
            HelperInventory.loadInventoryTable(frame);
        } else {
            JOptionPane.showMessageDialog(frame, "Update Faild");
        }
    }

    public static void UpdateInventoryOther(InventoryPanel frame) {
        String serialNo = ComboItem.getValue(frame.Inven_ManageOtherSerialNoCbx.getSelectedItem());
        String type = "Other";
        String name = frame.Inven_ManageOtherNameTxt.getText();
        String brand = frame.Inven_ManageOtherBrandTxt.getText();
        String model = frame.Inven_ManageOtherModelTxt.getText();
        String supplier = ComboItem.getValue(frame.Inven_ManageOthSupNameCbx.getSelectedItem());//Inven_ManagePcSupNameTxt.getText();
        String branch = ComboItem.getValue(frame.Inven_ManageOthBranNameCbx.getSelectedItem());
        String ram = null;
        String processor = null;
        String hard = null;
        String printer_type = null;
        String front = null;
        String ups = null;

        Inventory inven = new Inventory();
        Boolean it = inven.SqlUpdateInventory(serialNo, type, name, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups);
        System.err.println(it);
        if (it == true) {
            JOptionPane.showMessageDialog(frame, "Sccessfully updated");
            HelperInventory.loadInventoryTable(frame);
        } else {
            JOptionPane.showMessageDialog(frame, "Update Faild");
        }
    }

    public static void getInventoryDetails(InventoryPanel frame) {

        String SerialNo = ComboItem.getValue(frame.Inven_AssgnUsrSerialNoCbx.getSelectedItem());
        Inventory dao = new Inventory();
        String[] array1 = new String[5];
        dao.setDetails(SerialNo, array1);
        String type = array1[0];
        String name = array1[1];

        System.out.println(type);
        frame.type_txt.setText(type);
    }

    public static void addUser(InventoryPanel frame) {
        String SerialNo = ComboItem.getValue(frame.Inven_AssgnUsrSerialNoCbx.getSelectedItem());
        String user = frame.user_txt.getText();
        String position = frame.position_txt.getText();
        Inventory inven = new Inventory();
        String it = inven.addUser(SerialNo, user, position);
        System.err.println(it);
        if (it != null) {
            JOptionPane.showMessageDialog(frame, "Insertion Sccessfull");
            HelperInventory.loadInventoryTable(frame);
            frame.type_txt.setText("");
            frame.user_txt.setText("");
            frame.position_txt.setText("");
        } else {
            JOptionPane.showMessageDialog(frame, "Insertion Faild");
        }
    }
}
