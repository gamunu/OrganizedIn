/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.UI.InventoryPanel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Inventory {

    private String serialNo;
    private String type;
    private String name;
    private String brand;
    private String model;
    private String supplier;
    private String branch;
    private String ram;
    private String processor;
    private String hard;
    private String printer_type;
    private String front;
    private String ups;
    private String user;
    private String position;

    public Inventory(String serialNo, String type, String brand, String model, String supplier, String branch, String ram, String processor, String hard, String printer_type, String front, String ups, String user, String position) {
        this.serialNo = serialNo;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.supplier = supplier;
        this.branch = branch;
        this.ram = ram;
        this.processor = processor;
        this.hard = hard;
        this.printer_type = printer_type;
        this.front = front;
        this.ups = ups;
        this.user = user;
        this.position = position;
    }

    public Inventory(String type, String brand, String branch) {
        this.type = type;
        this.brand = brand;
        this.branch = branch;
    }

    public Inventory() {
    }

    public String[] createTableRow(String serialNo, String type, String brand, String model, String supplier, String branch, String ram, String processor, String hard, String printer_type, String front, String ups) {
        String[] rowAr = {serialNo, type, brand, model, supplier, branch, ram, processor, hard, printer_type, front, ups};
        return rowAr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getName() {
        return name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getHard() {
        return hard;
    }

    public void setHard(String hard) {
        this.hard = hard;
    }

    public String getPrinter_type() {
        return printer_type;
    }

    public void setPrinter_type(String printer_type) {
        this.printer_type = printer_type;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    /**
     *
     * @param serialNo
     * @return
     */
    public String addInventory(String serialNo, String name, String type, String brand, String model, String supplier, String branch, String ram, String processor, String hard, String printer_type, String front, String ups) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Inventory(serialNo, name, type, brand, model,supplierid, branchid,ram,processor, harddisk, printer_type, front,ups) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new String[] {"serialNo"})) {
            pstmt.setString(1, serialNo);
            pstmt.setString(2, name);
            pstmt.setString(3, type);
            pstmt.setString(4, brand);
            pstmt.setString(5, model);
            pstmt.setString(6, supplier);
            pstmt.setString(7, branch);
            
            if (ram != null && !ram.isEmpty()) {
                pstmt.setString(8, ram);
            } else {
                pstmt.setNull(8, Types.NULL);
            }

            if (processor != null && !processor.isEmpty()) {
                pstmt.setString(9, processor);
            } else {
                pstmt.setNull(9, Types.NULL);
            }

            if (hard != null && !hard.isEmpty()) {
                pstmt.setString(10, hard);
            } else {
                pstmt.setNull(10, Types.NULL);
            }

            if (printer_type != null && !printer_type.isEmpty()) {
                pstmt.setString(11, printer_type);
            } else {
                pstmt.setNull(11, Types.NULL);
            }

            if (front != null && !front.isEmpty()) {
                pstmt.setString(12, front);
            } else {
                pstmt.setNull(12, Types.NULL);
            }

            if (ups != null && !ups.isEmpty()) {
                pstmt.setString(13, ups);
            } else {
                pstmt.setNull(13, Types.NULL);
            }
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean sqlDeleteInventory(String serialNo) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE Inventory SET status='Unavailable' WHERE serialNo = ?")) {
            stmt.setString(1, serialNo);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean SqlDeleteInventory() {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM Inventory WHERE serialNo= ?")) {
            stmt.setString(1, this.serialNo);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
                                    
    public boolean SqlUpdateInventory(String serialNo, String type, String name, String brand, String model, String supplier, String branch, String ram, String processor, String hard, String printer_type, String front, String ups) {
        String query = "UPDATE INVENTORY SET type = ?,name= ?, brand = ?, model = ?,supplierid = ?, branchid = ?, ram= ?,processor=?, harddisk = ?, printer_type = ?, front = ?,ups = ? WHERE serialNo = ?";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, serialNo);
            stmt.setString(2, type);
            stmt.setString(3, name);
            stmt.setString(4, brand);
            stmt.setString(5, model);
            stmt.setString(6, supplier);
            stmt.setString(7, branch);

            if (ram != null && !ram.isEmpty()) {
                stmt.setString(8, ram);
            } else {
                stmt.setNull(8, Types.NULL);
            }

            if (processor != null && !processor.isEmpty()) {
                stmt.setString(9, processor);
            } else {
                stmt.setNull(9, Types.NULL);
            }

            if (hard != null && !hard.isEmpty()) {
                stmt.setString(10, hard);
            } else {
                stmt.setNull(10, Types.NULL);
            }

            if (printer_type != null && !printer_type.isEmpty()) {
                stmt.setString(11, printer_type);
            } else {
                stmt.setNull(11, Types.NULL);
            }

            if (front != null && !front.isEmpty()) {
                stmt.setString(12, front);
            } else {
                stmt.setNull(12, Types.NULL);
            }

            if (ups != null && !ups.isEmpty()) {
                stmt.setString(13, ups);
            } else {
                stmt.setNull(13, Types.NULL);
            }
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Vector getInventoryDetails() {

        Vector<Vector<String>> inventoryDetailsVector = null;

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT serialNo, type, brand, model, supplier, branch" + "FROM Inventory");
                ResultSet rs = stmt.executeQuery();) {

            inventoryDetailsVector = new Vector<>();

            while (rs.next()) {
                Vector<String> inventory = new Vector<>();
                inventory.add(rs.getString(1)); //serialNo
                inventory.add(rs.getString(2)); //type
                inventory.add(rs.getString(3)); //brand
                inventory.add(rs.getString(4)); //model
                inventory.add(rs.getString(5)); //supplier
                inventory.add(rs.getString(6)); //branch
                inventoryDetailsVector.add(inventory);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        }
        //finally close connection
        return inventoryDetailsVector;
    }

    public void setDetails(String SerilaNo, String array[]) {
        String query1 = "SELECT * FROM INVENTORY where SERIALNO = '" + SerilaNo + "' ";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {
            while (rs.next()) {
                array[0] = rs.getString("TYPE");
                // this.setUser(rs.getString("USER"));
                array[1] = rs.getString("BRANCHID");
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(this, "Invalid serial no !");
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");

        }
    }

    public String addUser(String serialNo, String user, String position) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO Inventory(serialNo, username,position) VALUES(?,?,?)", new String[] {"serialno"})) {
            pstmt.setString(1, serialNo);
            pstmt.setString(2, user);
            pstmt.setString(3, position);

           pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public boolean addUserInventory() {
        boolean result;
        String supplier_id = null;
        try {
            Connection dbConn = DataAccess.getConnection();
            //Get supplier id
            Statement stmt1 = (Statement) dbConn.createStatement();
            String query = "SELECT serialNo FROM Inventory WHERE serialNo= '" + this.getSerialNo() + "'";
            ResultSet rs = stmt1.executeQuery(query);
            while (rs.next()) {
                serialNo = rs.getString("SERIALNO");
            }

            Statement stmt = (Statement) dbConn.createStatement();
            String query1 = "INSERT INTO Inventory(USER,POSITION) values('" + this.getUser() + "', '"
                    + this.getPosition() + "');";
            int val = stmt.executeUpdate(query1);
            if (val == 1) {
                result = true;
            } else {
                result = false;
            }
        } // end try
        catch (SQLException ex) {
            System.out.println("Function:addUserInsert in Class: User data insert faild+");
            result = false;
        }
        return result; //will return true if SUCCESFULLY user details
    }

    public String addParts(String ParentserialNo, String type, String serial) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO Inventory(Parentserialno, type,serialno) VALUES(?,?,?)", new String[] {"serialno"})) {
            pstmt.setString(1, ParentserialNo);
            pstmt.setString(2, type);
            pstmt.setString(3, serial);

            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void setUserDetails(String P_SerilaNo, String array[]) {
        String query1 = "SELECT * FROM INVENTORY where parentserialno= '" + P_SerilaNo + "' ";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {
            while (rs.next()) {
                array[0] = rs.getString("username");
                // this.setUser(rs.getString("USER"));
                array[1] = rs.getString("BRANCHID");
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(this, "Invalid serial no !");
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");

        }
    }

    public Inventory getInventoryDetails(String p_SerialNo) {

        String query1 = "SELECT * FROM INVENTORY  WHERE parentserialno = '" + p_SerialNo + "'";

        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            if (colCount > 0) {
                while (rs.next()) {
                    this.setSerialNo(rs.getString("SERIALNO"));
                    this.setName(rs.getString("NAME"));
                    this.setBrand(rs.getString("BRAND"));
                    this.setModel(rs.getString("MODEL"));
                }
            }
        } catch (SQLException e) {
            //JOptionPane.showMessageDialog(this, "Invalid serial no !");            
            System.err.println(e + "\nFunction:getInventoryDetails in Class:Inventory couldn't get inventory details");
            return null;
        }
        return this;
    }

    public ArrayList getParentSerialNo() {

        ArrayList serialnoList = null;
        Connection dbConn = null;
        try {

            dbConn = DataAccess.getConnection();
            Statement stmt = dbConn.createStatement();


            String query = "SELECT DISTINCT parentserialno FROM INVENTORY";

            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);

            serialnoList = new ArrayList();

            while (rs.next()) {
                String serialNo = rs.getString(1);
                System.out.println(serialNo);
                serialnoList.add(serialNo);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed at Inventory");
        }
        return serialnoList;
    }

    public void setLocationDetails(String SerilaNo, String array[]) {
        String query1 = "SELECT * FROM INVENTORY where SERIALNO = '" + SerilaNo + "' ";
        
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)){
            while (rs.next()) {
                array[0] = rs.getString("username");
                array[1] = rs.getString("branchid");
                
                
            }
        } catch (SQLException e) {
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");
        }
    }
     public void setBranchDetails(String SerilaNo, String array[]) {
         
         String query2="SELECT ADDRESS  FROM BRANCH b,INVENTORY i where i.branchid=b.branchid and i.serialno='" + SerilaNo + "'";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query2)){
            while (rs.next()) {
                array[0] = rs.getString("address");
               // array[1] = rs.getString("branchid");
                
                
            }
        } catch (SQLException e) {
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");
        }
    }
    
     public void setLDetails(String SerilaNo, String array[]) {
        String query1 = "SELECT * FROM INVENTORY where SERIALNO = '" + SerilaNo + "' ";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {
            while (rs.next()) {
                array[0] = rs.getString("USERNAME");
                // this.setUser(rs.getString("USER"));
                array[1] = rs.getString("BRANCHID");
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(this, "Invalid serial no !");
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");

        }
    }
     
     public void setDetailsInventory(String SerilaNo, String array[]) {
        String query1 = "SELECT * FROM INVENTORY where SERIALNO = '" + SerilaNo + "' ";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {
            while (rs.next()) {
                array[0] = rs.getString("NAME");
                array[1] = rs.getString("BRAND");
                array[2] = rs.getString("MODEL");
                array[3] = rs.getString("PROCESSOR");
                array[4] = rs.getString("RAM");
                array[5] = rs.getString("HARDDISK");
                array[6] = rs.getString("SUPPLIERID");
                array[7] = rs.getString("BRANCHID");
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(this, "Invalid serial no !");
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");

        }
    }
     
     public void setDetailsInventoryPrinter(String SerilaNo, String array[]) {
        String query1 = "SELECT * FROM INVENTORY where SERIALNO = '" + SerilaNo + "' ";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {
            while (rs.next()) {
                array[0] = rs.getString("NAME");
                array[1] = rs.getString("BRAND");
                array[2] = rs.getString("MODEL");
                array[3] = rs.getString("PRINTER_TYPE");
                array[4] = rs.getString("UPS");
               // array[5] = rs.getString("HARDDISK");
                array[5] = rs.getString("SUPPLIERID");
                array[6] = rs.getString("BRANCHID");
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(this, "Invalid serial no !");
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");

        }
    }
     
      public void setDetailsInventoryOther(String SerilaNo, String array[]) {
        String query1 = "SELECT * FROM INVENTORY where SERIALNO = '" + SerilaNo + "' ";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {
            while (rs.next()) {
                array[0] = rs.getString("NAME");
                array[1] = rs.getString("BRAND");
                array[2] = rs.getString("MODEL");
                array[3] = rs.getString("SUPPLIERID");
                array[4] = rs.getString("BRANCHID");
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(this, "Invalid serial no !");
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");

        }
    }
       public String setSupplierLDetails(String SerilaNo) {
        String query1 = "SELECT DISTINCT companyname  FROM SUPPLIER c,INVENTORY i where c.supplierid=i.supplierid and i.SERIALNO = '" + SerilaNo + "' ";
        String company=null;
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {
            if (rs.next()) {
                //ComboItem cbi = new ComboItem(rs.getString("BRANCHID"), rs.getString("ADDRESS"));
                // frame.Inven_ManagePcSupNameCbx.addItem(cbi);
                // this.setUser(rs.getString("USER"));
               company= rs.getString(1);
            }
        } catch (SQLException e) {
            // JOptionPane.showMessageDialog(this, "Invalid serial no !");
            System.err.println(e + "Function:setDetails in Class:Inventory couldn't set details");

        }
        return company;
    }
}
