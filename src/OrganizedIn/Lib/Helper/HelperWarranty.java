package OrganizedIn.Lib.Helper;

import OrganizedIn.Lib.Warranty;
import OrganizedIn.UI.MainInterface;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;

public class HelperWarranty {

    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(HelperWarranty.class.getName());
    //View warranty table mouse clicked event

    public static void selectRow(MainInterface frame) {

        //retrieving the selected row index
        int row = frame.War_ViewwarTbl.getSelectedRow();

        //if a single row is selected from the table, take each cell values into the controls
        if (frame.War_ViewwarTbl.getRowSelectionAllowed()) {

            try {
                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date expDate = df2.parse(frame.War_ViewwarTbl.getValueAt(row, 4).toString());
                System.out.println("---------------------------------------------------------" + expDate);
                frame.jDateChooser3.setDate(expDate);
            } catch (ParseException ex) {
                Logger.getLogger(HelperWarranty.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
    }

    //Warranty view load table methord
    public static boolean loadTable(String productSerialNo, MainInterface frame) {
        boolean result = false;
        String SerialNo = productSerialNo;

        Warranty w = new Warranty();//Warranty object 'w' holds particuar warranty details now.
        w.getWarrantyDetails(productSerialNo); // will set attributes in the Warranty.java class


        Vector<String> header = new Vector<String>();
        header.add("Product Serial No");
        header.add("Product Type");
        header.add("Supplier");
        header.add("Date Purchased");
        header.add("Expiery Date");

        Vector<String> data1 = new Vector<String>();
        data1.add(w.getProductSerialNo());
        data1.add(w.getType());
        data1.add(w.getSupplier());
        data1.add(w.getDateOfPurchase());
        data1.add(w.getExpiryDate());

        Vector<Vector<String>> data2 = new Vector<Vector<String>>();
        data2.add(data1);
        frame.War_ViewwarTbl.setModel(new javax.swing.table.DefaultTableModel(data2, header));
        //jScrollPane1.setViewportView(jTable_warrantyDetails);        
        try {
            String serialNo = frame.War_ViewwarTbl.getValueAt(0, 0).toString();
            System.out.println(serialNo);
            if (!serialNo.equalsIgnoreCase("")) {

                result = true;
                System.out.println(result);
                return result;

            } else {
                result = false;
                System.out.println(result);
                return result;
            }
        } catch (Exception ex) {
            result = false;
            System.out.println(result);
            return result;
        }
    }

    public static void warrantyUpdate(MainInterface frame) {
        String serialNo = frame.War_ViewwarSerialNoTxt.getText();
        Warranty dao = new Warranty();

//   System.out.println(jTable_warrantyDetails.getValueAt(row, 1).toString());
        if (serialNo.equals("") || frame.jDateChooser3.getDate().equals(null)) {
            JOptionPane.showMessageDialog(frame, "Please enter required fields to Update");
        } else {
            //Create a warranty Object
            Warranty w = new Warranty();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String expDate = sdf.format(frame.jDateChooser3.getDate());
            w.setExpiryDate(expDate);

            w.setProductSerialNo(serialNo);
            System.out.println(w.getType());
            //Save to the database
            boolean result = dao.updateWarranty(w, serialNo);
            if (result == true) {
                JOptionPane.showMessageDialog(frame, "Updation Successful");  //newly added  
                HelperWarranty.loadTable(w.getProductSerialNo(), frame);
                frame.jDateChooser3.setDate(null);

            } else {   //newly added
                JOptionPane.showMessageDialog(frame, "Updation failed");  //newly added  
            }
        }//else
    }

    public static void warrantyDelete(MainInterface frame) {
        // TODO add your handling code here:
        Warranty dao = new Warranty();
        //retrieving the selected row index
        int row = frame.War_ViewwarTbl.getSelectedRow();
        //if a single row is selected from the table, take each cell values into the controls


        boolean result = false;

        if (frame.War_ViewwarTbl.getRowSelectionAllowed()) {
            String productSerialNo = frame.War_ViewwarTbl.getValueAt(row, 0).toString();
            result = dao.deleteWarranty(productSerialNo);
        }

        if (result == true) {
            JOptionPane.showMessageDialog(frame, "Successfuly Deleted");
            frame.jDateChooser3.setDate(null);
            HelperWarranty.loadTable("PC", frame);
            //  refreshtable(frame);
        } else {
            JOptionPane.showMessageDialog(frame, "Deletion Failed");
        }
    }

    public static void addNewWarranty(MainInterface frame) {
        // TODO add your handling code here:    
        if (frame.jDateChooser2.getDate().toString().equalsIgnoreCase("") || frame.War_newwarDOPTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Please insert required fields!");
        } //auto increment     String wId = jTextField_wId.getText();
        else {
            //Create a warranty Object
            Warranty w = new Warranty();

            try {
                String dateOfPurch = frame.War_newwarDOPTxt.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String expDate = sdf.format(frame.jDateChooser2.getDate());
                String productSerialNo = frame.War_newwarSerialNoTxt.getText();
                String type = frame.War_newwarTypeTxt.getText();


                // setting Warranty.java attributes(encapsulation)
                w.setDateOfPurchase(dateOfPurch);
                w.setExpiryDate(expDate);
                w.setProductSerialNo(productSerialNo);
                w.setType(type);


            } catch (Exception ex) {

                JOptionPane.showMessageDialog(frame, "Not a valid enrty!");
            }



            boolean result = w.addWarranty();

            if (result == true) {
                JOptionPane.showMessageDialog(frame, "Succesfully Inserted");
                frame.War_newwarDOPTxt.setText("");
                frame.jDateChooser2.setDate(null);
                frame.War_newwarSerialNoTxt.setText("");
                frame.War_newwarTypeTxt.setText("");


            } else {
                JOptionPane.showMessageDialog(frame, "Insertion Failure");
            }


        }
    }

    public static void getWarrantyDetails(MainInterface frame) {
        if (frame.War_newwarSerialNoTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Please insert product serial No");
        } else {
            try {
                String productSerialNo = frame.War_newwarSerialNoTxt.getText();
                Warranty dao = new Warranty();
                String[] array1 = new String[5];
                dao.setDetails(productSerialNo, array1);
                String type = array1[0];
                String name = array1[1];

                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date myDate = df2.parse(array1[2]);
                String dateofpurchase = df2.format(myDate);
                System.out.println(type);
                System.out.println(name);
                System.out.println(dateofpurchase);


                frame.War_newwarTypeTxt.setText(type);
                frame.War_newwarDOPTxt.setText(dateofpurchase);
            } catch (ParseException ex) {
                logger.error(ex.getMessage());
            }

        }
    }

    //Tracks warranty expirations
    public static Vector<String> WarrantyExpiration() {
        Vector<String> serialNumbers;

        Warranty w = new Warranty();
        serialNumbers = w.checkWarrantyExpiration();

        return serialNumbers;
    }

    //Warranty view load table methord
    public static void loadNotificationsTable(Vector<String> productSerialNo, MainInterface frame) {

        Vector<String> header = new Vector<>();
        header.add("Product Serial No");
        header.add("Product Name");
        header.add("Expiery Date");
        header.add("Supplier");
        header.add("Warranty Duration");


        Vector<Vector<String>> data2 = new Vector<>();
        for (int i = 0; i < productSerialNo.size(); i++) {
            Warranty w = new Warranty();//Warranty object 'w' holds particuar warranty details now.
            w = w.getWarrantyDetails(productSerialNo.get(i)); // will set attributes in the Warranty.java class
            System.out.println("---------------" + productSerialNo.get(i));
            Vector<String> data1 = new Vector<>();
            data1.add(w.getProductSerialNo());
            data1.add(w.getType());
            data1.add(w.getExpiryDate());
            data1.add(w.getSupplier());
            data1.add(String.valueOf(w.getDuration()));
            data2.add(data1);
        }



        frame.War_ViewNotificationsTbl.setModel(new javax.swing.table.DefaultTableModel(data2, header));
        //jScrollPane1.setViewportView(jTable_warrantyDetails);        
    }

    public static void NotificationsloadCaller(Vector<String> serialNumbers, MainInterface frame) {
        if (!serialNumbers.isEmpty()) {
            System.out.println("--------------" + serialNumbers.size());
            HelperWarranty.loadNotificationsTable(serialNumbers, frame);
        }

    }

    public static void setDate(MainInterface frame) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        java.util.Date systemDate = new java.util.Date();
        String sysdate = dateFormat.format(systemDate);
        System.out.println(sysdate);
        frame.jLabel122.setText(sysdate);

    }

    public static void refreshtable(MainInterface frame) {
//        Vector<String> header = new Vector<String>();
        //      header.add("Product Serial No");
        //    header.add("Product Name");
        //  header.add("Expiery Date");
        //  header.add("Supplier");
        //  header.add("Warranty Duration");
        //Vector<Vector<String>> data2 = new Vector<Vector<String>>();
        //  Vector<String> data1 = new Vector<String>();
        //data1.add("");
        //data1.add("");
        //data1.add("");
        //data1.add("");
        // data1.add("");
        // data2.add(data1);
        //     frame.War_ViewNotificationsTbl.setModel(new javax.swing.table.DefaultTableModel(data2, header));
        //    HelperWarranty.loadTable("PC", frame);
    }

    public static void displayNotifications(MainInterface frame) {
        if (frame.War_ViewNotificationsTbl.getRowCount() > 0) { // if the notifications table is filled( not refreshed) then display the notification
            frame.jLabel7.setText("You have unattended notifications");
        } else {  //if the notifications table is empty
            frame.jLabel7.setVisible(false);
        }
    }

    public static void setSelectedProduct(MainInterface frame) {

        //retrieving the selected row index
        int row = frame.War_ViewNotificationsTbl.getSelectedRow();

        //if a single row is selected from the table, take each cell values into the controls
        if (frame.War_ViewNotificationsTbl.getRowSelectionAllowed()) {
            //Get updated values and store                     
            String productSerialNo = frame.War_ViewNotificationsTbl.getValueAt(row, 0).toString();
            frame.jTextField87.setText(productSerialNo);
        }

    }

    public static void setSupplier(MainInterface frame, String serialNo) {
        if (frame.jTextField87.getText().equals("")) {
            JOptionPane.showMessageDialog(frame, "Please select a product");
        } else {
            Warranty w = new Warranty();
            String[] result = new String[3];
            result = w.getSupplier(serialNo);
            frame.jTextField95.setText(result[0]);
            frame.jTextField96.setText(result[1]);
            frame.jTextField97.setText(result[2]);
        }
    }

    public static void navigateToMaintenace(MainInterface frame) {

        if (frame.jTextField87.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(frame, "Select a product in order to add to maintenance");
        } else {
            frame.MserialTxt.setText(frame.jTextField87.getText());
            frame.MtypeTxt.setText(Warranty.getProductType(frame.jTextField87.getText()));

        }
    }
}