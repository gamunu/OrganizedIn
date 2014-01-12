/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Data.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author DELL
 */
public class Warranty {
    //triggered private String warrantyId ;

    private String dateOfPurchase;
    private double duration;
    private String expiryDate;
    private String productSerialNo;
    private String type;
    private String supplier;

    public Warranty() {
    }

    public Warranty(String dateOfPurchase, double duration, String expieryDate, String productId, String type, String supplier) {
        this.dateOfPurchase = dateOfPurchase;
        this.duration = duration;
        this.expiryDate = expieryDate;
        this.productSerialNo = productId;
        this.type = type;
        this.supplier = supplier;
    }

    /**
     * @return the dateOfPurchase
     */
    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    /**
     * @param dateOfPurchase the dateOfPurchase to set
     */
    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    /**
     * @return the duration
     */
    public double getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the productId
     */
    public String getProductSerialNo() {
        return productSerialNo;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductSerialNo(String productSerialNo) {
        this.productSerialNo = productSerialNo;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the supplier
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public boolean addWarranty() {

        boolean result;

        try {

            Connection dbConn = DataAccess.getConnection();
            String query1 = "INSERT INTO warranty(DATEOFPURCHASE, EXPIRYDATE, SERIALNO, PRODUCTTYPE) values(TO_DATE('" + this.dateOfPurchase + "', 'yyyy-MM-dd'), TO_DATE('" + this.expiryDate + "', 'yyyy-MM-dd'),'" + this.productSerialNo + "','" + this.type + "' )";
            Statement stmt = (Statement) dbConn.createStatement();
            int val = stmt.executeUpdate(query1);
            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } // end try
        catch (SQLException ex) {
            System.err.println(ex + "\nFunction:addWarrantyInsert in Class: Warranty data insert faild+");
            result = false;
        }
        return result; //will return true if SUCCESFULLY added warranty details

    } //addWarranty

    public void setDetails(String productSerilaNo, String[] array) {

        String query1 = "SELECT * FROM INVENTORY I, SUPPLIER S where I.SERIALNO = '" + productSerilaNo + "'  AND I.SUPPLIERID = S.SUPPLIERID";

        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {


            while (rs.next()) {
                array[0] = rs.getString("TYPE");
                array[1] = rs.getString("COMPANYNAME");
                array[2] = rs.getDate("DATEPURCHASED").toString();
            }
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.err.println(ex + "Error occured");
        }

    }

// Get warranty details from the DB
    public Warranty getWarrantyDetails(String productSerialNo) {

        String query1 = "select S.companyname from SUPPLIER S,INVENTORY I,WARRANTY W where W.serialno='" + productSerialNo + "' and W.serialno=I.serialno and I.supplierid=S.supplierid";


        try {
            Connection dbConn = DataAccess.getConnection();
            Statement stmt = (Statement) dbConn.createStatement();
            ResultSet rs1 = stmt.executeQuery(query1);

            while (rs1.next()) {
                System.out.println("in rs1");
                System.out.println(rs1.getString("COMPANYNAME"));
                this.setSupplier(rs1.getString("COMPANYNAME"));

            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(this, "Invalid serial no !");            
            System.err.println(e + "\nFunction:getWarrantyDetails in Class:Warranty couldn't get warranty details");
            return null;
        }


        String query2 = "SELECT * FROM warranty  WHERE serialNo = '" + productSerialNo + "' ";

        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs2 = stmt.executeQuery(query2)) {


            while (rs2.next()) {
                System.out.println("in rs2");


                DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate1 = df2.format(rs2.getDate("EXPIRYDATE"));
                String formattedDate2 = df2.format(rs2.getDate("DATEOFPURCHASE"));
                this.setDateOfPurchase(formattedDate2);
                this.setExpiryDate(formattedDate1);
                this.setProductSerialNo(rs2.getString("SERIALNO"));
                this.setType(rs2.getString("PRODUCTTYPE"));


            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(this, "Invalid serial no !");            
            System.err.println(e + "\nFunction:getWarrantyDetails in Class:Warranty couldn't get warranty details");
            return null;
        }


        System.out.println(this.dateOfPurchase);
        System.out.println(this.expiryDate);
        System.out.println(this.productSerialNo);
        System.out.println(this.supplier);
        System.out.println(this.type);





        return this;
    }

    public boolean updateWarranty(Warranty w, String serialNo) {
        boolean result;
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement()) {
            String query = "UPDATE Warranty SET   duration = '" + w.getDuration()
                    + "' , expiryDate = TO_DATE('" + w.getExpiryDate()
                    + "', 'YYYY-MM-DD') WHERE  serialNo = '" + serialNo + "'";

            int val = stmt.executeUpdate(query);

            if (val > 0) { //changed
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(sQLException + " Error Occured ");

            result = false;
        }
        return result;
    }

    public boolean deleteWarranty(String productSerialNo) {
        boolean result = false;
        String query = "delete from Warranty where serialNo = '" + productSerialNo + "'";

        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement()) {
            System.out.println(productSerialNo);
            if (stmt.executeUpdate(query) > 0) {
                System.out.println("Successfully Deleted");
                result = true;
            }
        } catch (SQLException sQLException) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(sQLException + " Error Occured ");

            result = false;
        }
        return result;
    }

    //This method tracks warranty expirations
    public Vector<String> checkWarrantyExpiration() {

        Vector<String> serialNumbers = new Vector<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //get current date time with Date()
        Date systemDate = new Date();
        String sysdate = dateFormat.format(systemDate);
        System.out.println(sysdate);


        try {
            Connection conn = DataAccess.getConnection();
            PreparedStatement stmt = conn.prepareStatement("Select serialno from Warranty Where expirydate<=to_date('" + sysdate + "','YYYY-MM-DD')");
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                System.out.println("IN");
                serialNumbers.add(rs.getString("SERIALNO"));
                System.out.println("-------------------" + rs.getString("SERIALNO"));
            }


        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");

        }



        return serialNumbers;
    }

    public String[] getSupplier(String serialNo) {

        String result[]=new String[3];
        String query = "select S.* from Supplier S,warranty W where W.supplierid=S.supplierid and W.serialno='" + serialNo + "'";

        try {
            Connection dbConn = DataAccess.getConnection();
            Statement stmt = (Statement) dbConn.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);

            while (rs1.next()) {
                result[0]=(rs1.getString("COMPANYNAME"));
                result[1]=(rs1.getString("PHONE"));
                result[2]=(rs1.getString("EMAIL"));
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(this, "Invalid serial no !");            
            System.err.println(e + "\nFunction:getWarrantyDetails in Class:Warranty couldn't get warranty details");
            return result;
        }


        return result;

    }

public static String getProductType(String serialNo){
    String result=null;
    String query="select producttype from Warranty where serialno='"+serialNo+"'";

    try {
            Connection dbConn = DataAccess.getConnection();
            Statement stmt = (Statement) dbConn.createStatement();
            ResultSet rs1 = stmt.executeQuery(query);

            while (rs1.next()) {
                result=(rs1.getString("PRODUCTTYPE"));
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(this, "Invalid serial no !");            
            System.err.println(e + "\nFunction:getWarrantyDetails in Class:Warranty couldn't get warranty details");
        }

    return result;
    
}


}
