/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Data.DataAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Manuals {
    
    private String manualId;
    private String itemName;
    private String brand;
    private String model;
    private String manualName;
    private String filePath;
    private String date;

    public Manuals(String manualId, String itemName, String brand, String model, String manualName, String filePath, String date) {
        this.manualId = manualId;
        this.itemName = itemName;
        this.brand = brand;
        this.model = model;
        this.manualName = manualName;
        this.filePath = filePath;
        this.date = date;
    }

    public Manuals(String itemName, String brand, String model) {
        this.itemName = itemName;
        this.brand = brand;
        this.model = model;
    }

    public Manuals() {
    }

    public String getManualId() {
        return manualId;
    }

    public void setManualId(String manualId) {
        this.manualId = manualId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getManualName() {
        return manualName;
    }

    public void setManualName(String manualName) {
        this.manualName = manualName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
   
    

   
    
public Boolean addManual(String manId, String itmName, String brand, String model,String manualName,String filePath, String modDt) {
        boolean result = false;
        Connection dbcon = null;


        try {


           
            DataAccess DataAccess = new DataAccess();
            Connection con = DataAccess.getConnection();

            String query = "Insert into imanualnew(componentId, itemName, brand, model,ManualName, filePath, modDate) values ('" + manId + "', '" + itmName + "', '" + brand + "', '" + model + "','"+manualName+"','"+filePath+"', '" + modDt + "');";

            Statement stmt = con.createStatement();
           

            int value1 = stmt.executeUpdate(query);
            if (value1 == 1) {
                JOptionPane.showMessageDialog(null, "Successfully Inserted");
            } else {
                JOptionPane.showMessageDialog(null, "Insertion Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return result;
    }
    
    
     public boolean deleteManual(String manualId) {




        Connection con = DataAccess.getConnection();

        try {
            Statement stmt = con.createStatement();

            String query = "UPDATE imanualnew set mstatus='Unvailable'  WHERE componentId='" + manualId + "' ";

            int value1 = stmt.executeUpdate(query);

            if (value1 == 1) {
                JOptionPane.showMessageDialog(null, "Successfully Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Deletion Error");
            }

            return true;
        } catch (SQLException ex) {
        }

        return false;
}
    
     public boolean updateManual(String manId, String itmName, String brand, String model,String manualName, String filePath,String modDt){
     
     Connection con = DataAccess.getConnection();
        
        try{
            Statement stmt = con.createStatement();
            
            String query = "UPDATE imanualnew SET itemName='"+itmName+"' , brand='"+brand+"' , model='"+model+"',ManualName='"+manualName+"', filePath='"+filePath+"',modDate = '"+modDt+"' where componentId='"+manId+"' ";
            
            int value=stmt.executeUpdate(query);
            
            if (value==1){
            
                JOptionPane.showMessageDialog(null, "Successfully Updated");
            }
            else{
            JOptionPane.showMessageDialog(null, "Error in updating");
            
            
         }
            
            return true;
        }
        
        catch(SQLException ex){
        
        }

     
     return false;
     
     }
    
     
     public boolean searchManual(String manualId, String Array[]){
         Connection con = DataAccess.getConnection();
        
         
         try{
             
         Statement stmt = con.createStatement();
         String query = "Select * from imanualnew where componentId = '"+manualId+"'";
         
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                   
                   Array[0] = rs.getString("itemName");
                   Array[1] =rs.getString("brand");
                   Array[2]  = rs.getString("model");
                   Array[3]= rs.getString("ManualName");
                   Array[4] = rs.getString("filePath");
                   Array[5] =rs.getString("modDate");
                   
                 }
         }catch(Exception ex){
                   //JOptionPane.showMessageDialog(null, "Invalid Announcement ID, Searching failed");
                   
               }
         
         return false;
           }
           
          
}
