/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Data;


import OrganizedIn.Lib.Audit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Dell
 */
public class AuditDAO {

    public AuditDAO() {
    }
   
    public boolean addAudit(Audit a) {
        boolean result = false;
        Connection dbConn = null;
        try {
            dbConn = DataAccess.getConnection();
            Statement stmt = (Statement) dbConn.createStatement();

            String query = "INSERT INTO AUDITENTRY (auditno,auditsdate,auditedate,counductby,note) "
                    + "VALUES('" + a.getAudNo() + "','" + a.getStDate() + "','" + a.getEnDate() + "','" + a.getAudNames() + "','" + a.getNotes() + "' )";

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

    //update
    public boolean updateAudit(Audit a) {
        boolean result = false;
        Connection dbConn = null;

        try {
            dbConn = DataAccess.getConnection();
            Statement stmt = (Statement) dbConn.createStatement();

            String query = "UPDATE AUDITENTRY SET auditsdate='" + a.getStDate() + "',auditedate='" + a.getEnDate() + "',counductby='" + a.getAudNames() + "',note='" + a.getNotes() + "'  where auditno='" + a.getAudNo() + "'";

            int val = stmt.executeUpdate(query);

            if (val == 1) {
                result = true;
            } else {
                result = false;
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Update query failed");
            result = false;
        }
        return result;
    }

    public Vector getAuditDetails(String AuditID) {

        Vector<Vector<String>> AuditList = null;
        java.sql.Connection dbConn = null;

        try {
            dbConn = DataAccess.getConnection();
            java.sql.Statement stmt = dbConn.createStatement();

            String query = "select auditno,auditsdate,auditedate,counductby,note from AUDITENTRY where auditno LIKE '" + AuditID + "%'";

            ResultSet rs = stmt.executeQuery(query);
            AuditList = new Vector<Vector<String>>();

            while (rs.next()) {
                Vector<String> itemDetails = new Vector<String>();
                itemDetails.add(rs.getString(1));
                itemDetails.add(rs.getString(2));
                itemDetails.add(rs.getString(3));
                itemDetails.add(rs.getString(4));
                itemDetails.add(rs.getString(5));

                AuditList.add(itemDetails);
            }

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed");
        }
        return AuditList;
    }

    //Delete an Audit
    public Boolean deleteAudit(String audNo) {
        Boolean ans = false;
        try {
            Connection conn = DataAccess.getConnection();
            java.sql.Statement smt = conn.createStatement();
            String query = "delete from AUDITENTRY where auditno = '" + audNo + "'";
            int result = smt.executeUpdate(query);
            if (result == 1) {
                ans = true;
            } else {
                ans = false;
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
            ans = false;
        }
        return ans;
    }
    public ArrayList getAuditDetailsByID(String AuditID) {
        String query = null;
        ArrayList temp = new ArrayList();

        Connection dbConn = null;
        query="select auditno,auditsdate,auditedate,counductby,note from AUDITENTRY where auditno ='" + AuditID + "%'";

        try {
            dbConn = DataAccess.getConnection();
            java.sql.Statement stmt = dbConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                temp.add(rs.getString(1));
                temp.add(rs.getString(2));
                temp.add(rs.getString(3));
                temp.add(rs.getString(4));
                temp.add(rs.getString(5));
            }
            System.out.println(temp);

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed1");
        }
        return temp;
    }
    
    public ArrayList getAuditIDS() {
        String query = null;
        ArrayList temp = new ArrayList();

        Connection dbConn = null;
        query="select auditno from AUDITENTRY";

        try {
            dbConn = DataAccess.getConnection();
            java.sql.Statement stmt = dbConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                temp.add(rs.getString(1));
            }
            System.out.println(temp);

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed1");
        }
        return temp;
    }
    
    public ArrayList getBranches() {
        String query = null;
        ArrayList temp = new ArrayList();

        Connection dbConn = null;
        query="select address from BRANCH";

        try {
            dbConn = DataAccess.getConnection();
            java.sql.Statement stmt = dbConn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                temp.add(rs.getString(1));
            }
            System.out.println(temp);

        } catch (SQLException sQLException) {
            System.out.println(sQLException + "-----------Select query failed1");
        }
        return temp;
    }
}
