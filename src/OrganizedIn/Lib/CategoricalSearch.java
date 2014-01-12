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
import java.util.Vector;

/**
 *
 * @author User
 */
public class CategoricalSearch {

    public Vector searchCategory(Inventory i) {

        String brand = i.getBrand();
        String branch = i.getBranch();
        String type = i.getType();
        String query = null;
        if (!brand.equals("NULL") && !branch.equals("NULL") && !type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY WHERE BRAND='" + brand + "' AND TYPE='" + type + "' AND BRANCHID=(SELECT BRANCHID FROM BRANCH WHERE ADDRESS='" + branch + "')";
        } else if (brand.equals("NULL") && branch.equals("NULL") && type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY";
        } else if (!brand.equals("NULL") && branch.equals("NULL") && type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY WHERE BRAND='" + brand + "'";
        } else if (brand.equals("NULL") && !branch.equals("NULL") && type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY WHERE BRANCHID=(SELECT BRANCHID FROM BRANCH WHERE ADDRESS='" + branch + "')";
        } else if (brand.equals("NULL") && branch.equals("NULL") && !type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY WHERE TYPE='" + type + "'";
        } else if (!brand.equals("NULL") && !branch.equals("NULL") && type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY WHERE BRAND='" + brand + "' AND BRANCHID=(SELECT BRANCHID FROM BRANCH WHERE ADDRESS='" + branch + "')";
        } else if (!brand.equals("NULL") && branch.equals("NULL") && !type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY WHERE BRAND='" + brand + "' AND TYPE='" + type + "'";
        } else if (brand.equals("NULL") && !branch.equals("NULL") && !type.equals("NULL")) {
            query = "SELECT SERIALNO,NAME,STATUS,USERNAME FROM INVENTORY WHERE TYPE='" + type + "' AND BRANCHID=(SELECT BRANCHID FROM BRANCH WHERE ADDRESS='" + branch + "')";
        }
        Vector<Vector<String>> inventory = new Vector<Vector<String>>();

        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Vector<String> item = new Vector<>();
                item.add(rs.getString(1));
                item.add(rs.getString(2));
                item.add(rs.getString(3));
                item.add(rs.getString(4));
                inventory.add(item);
            }
        } catch (SQLException e) {
            System.err.println(e);

        }
        return inventory;
    }
}
