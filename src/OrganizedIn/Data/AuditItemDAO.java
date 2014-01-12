/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Data;

import OrganizedIn.Audit.AuditItem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Dell
 */
public class AuditItemDAO {

    public AuditItemDAO() {
    }

    public boolean addAudit(AuditItem ai) {
        boolean result = false;
        Connection dbConn = null;
        try {
            dbConn = DataAccess.getConnection();
            Statement stmt = (Statement) dbConn.createStatement();

            String query = "INSERT INTO AUDITENTRY (auditno ,branch, serialNo,Name,brand,model,status) "
                    + "VALUES('" + ai.getAuditID() + "','" + ai.getBranch() + "','" + ai.getSCode() + "','" + ai.getName() + "','" + ai.getBrand() + "','" + ai.getModel() + "','" + ai.getStatus() + "' )";

            int val = stmt.executeUpdate(query);
            if (val == 1) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Insert query failed");
            result = false;
        }
        return result;
    }

    public Vector getAuditItemDetails(String AuditID, String Type) {

        Vector<Vector<String>> AuditList = null;
        java.sql.Connection dbConn = null;

        try {
            dbConn = DataAccess.getConnection();
            java.sql.Statement stmt = dbConn.createStatement();

            String query = "select auditno ,branch, serialNo,Name,brand,model,status from audititems where auditno LIKE '" + AuditID + "%' and status LIKE '" + Type + "%'";

            ResultSet rs = stmt.executeQuery(query);
            AuditList = new Vector<Vector<String>>();

            while (rs.next()) {
                Vector<String> itemDetails = new Vector<String>();
                itemDetails.add(rs.getString(1));
                itemDetails.add(rs.getString(2));
                itemDetails.add(rs.getString(3));
                itemDetails.add(rs.getString(4));
                itemDetails.add(rs.getString(5));
                itemDetails.add(rs.getString(6));
                itemDetails.add(rs.getString(7));

                AuditList.add(itemDetails);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        }
        return AuditList;
    }
}
