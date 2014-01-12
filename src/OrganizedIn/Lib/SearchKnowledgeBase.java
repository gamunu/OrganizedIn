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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author DELL
 */
public class SearchKnowledgeBase {
    
    
   
    public ArrayList getItemName(){
    ArrayList ItemNameList = new ArrayList();
    Connection con = null;

    try{
    //Connect to th DB
         con = DataAccess.getConnection();
          Statement stmt = con.createStatement();
    //Select the ClassType
        String query = "SELECT DISTINCT itemName FROM imanualnew";

        ResultSet rs = stmt.executeQuery(query);
        ItemNameList = new ArrayList();

        while (rs.next()) {
        String ItemName = rs.getString(1);

        ItemNameList.add(ItemName);
    }
    }
    catch (SQLException sqlException)
    {

    }
    finally
    {
    //Close the db connection
       // DataAccess.closeConnection(con);
    }
    return ItemNameList;
  
    
    }
    
    public ArrayList getBrandName(){
    ArrayList BrandNameList = new ArrayList();
    Connection con = null;

    try{
    //Connect to th DB
         con = DataAccess.getConnection();
          Statement stmt = con.createStatement();
    //Select the ClassType
        String query = "SELECT DISTINCT brand FROM imanualnew";

        ResultSet rs = stmt.executeQuery(query);
        BrandNameList = new ArrayList();

        while (rs.next()) {
        String brandName = rs.getString(1);

        BrandNameList.add(brandName);
    }
    }
    catch (SQLException sqlException)
    {

    }
    finally
    {
    //Close the db connection
       // DataAccess.closeConnection(con);
    }
    return BrandNameList;
  
    
    }
    
    
     public ArrayList getModelName(){
    ArrayList ModelNameList = new ArrayList();
    Connection con = null;

    try{
    //Connect to th DB
         con = DataAccess.getConnection();
          Statement stmt = con.createStatement();
    //Select the ClassType
        String query = "SELECT DISTINCT model FROM imanualnew";

        ResultSet rs = stmt.executeQuery(query);
        ModelNameList = new ArrayList();

        while (rs.next()) {
        String modelName = rs.getString(1);

        ModelNameList.add(modelName);
    }
    }
    catch (SQLException sqlException)
    {

    }
    finally
    {
    //Close the db connection
       // DataAccess.closeConnection(con);
    }
    return ModelNameList;
  
    
    }
    
    
     public ResultSet getAnnouncementsBytyping(String subject){
        Connection con = DataAccess.getConnection();
        ResultSet rs = null;
        try{
            Statement stmt = con.createStatement();            
            String query = "select subject, targetGroup, Announcement from iannouncement WHERE subject LIKE '"+subject+"%' ";
            rs=stmt.executeQuery(query);
        }
        
        catch (SQLException ex){}
        
        return  rs;
    }
     
     public Vector searchManuals(Manuals m){

        String item = m.getItemName();
        String brand = m.getBrand();
        String model= m.getModel();
        String query = null;


if (!item.equals("NULL") && !brand.equals("NULL") && !model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew WHERE itemName='" + item + "' AND brand='" + brand + "' AND model='" + model + "'";
        } else if (item.equals("NULL") && brand.equals("NULL") && model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew";
        } else if (!item.equals("NULL") && brand.equals("NULL") && model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew WHERE itemName='" + item + "'";
        } else if (item.equals("NULL") && !brand.equals("NULL") && model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew WHERE brand='" + brand + "'";
       } else if (item.equals("NULL") && brand.equals("NULL") && !model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew WHERE model='" + model + "'";
        } else if (!item.equals("NULL") && !brand.equals("NULL") && model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew WHERE itemName='" + item + "' AND brand='" + brand + "'";
        } else if (!item.equals("NULL") && brand.equals("NULL") && !model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew WHERE itemName='" + item + "' AND model='" + model+ "'";
      } else if (item.equals("NULL") && !brand.equals("NULL") && !model.equals("NULL")) {
            query = "SELECT itemName,brand,model,ManualName FROM imanualnew WHERE model='" + model+ "' AND  brand='" + brand + "'";
       }

	Vector<Vector<String>> manual = new Vector<Vector<String>>();

        try (Connection con = DataAccess.getConnection();
                Statement stmt = (Statement) con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vector<String> manualload = new Vector<>();
                manualload.add(rs.getString(1));
                manualload.add(rs.getString(2));
                manualload.add(rs.getString(3));
                manualload.add(rs.getString(4));
                manual.add(manualload);
            }
        } catch (SQLException e) {
            System.err.println(e);

        }
        return manual;
    }
}




     





 

    
