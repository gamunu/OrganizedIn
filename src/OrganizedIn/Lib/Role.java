/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Data.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author gamunu
 */
public class Role {
private static Logger logger = LogManager.getLogger(DataAccess.class.getName());
    protected ArrayList<String> permissions;
    public String rolename;

    public Role() {
        this.permissions = new ArrayList<>();
    }

    //return a role object with associated permissions
    public static Role getRoleWithPerms(int roleid) {
        Role role = new Role();
        String query = "SELECT t2.permdesc FROM ROLEPERM t1 JOIN PERMISSIONS t2 ON t1.permid = t2.permid WHERE t1.roleid = ?";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, roleid);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    role.permissions.add(rs.getString("permdesc"));
                }
            }
            role.rolename = Role.getRole(roleid);
        } catch (SQLException e) {
             logger.error(e + "Function:getRoleWithPerms in Class:Role");
        }
        return role;
    }
    
    public static String getRole(int roleid){
        String result = null;
        String query = "SELECT rolename FROM ROLES WHERE roleid = ?";
         try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, roleid);
            try (ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    result = rs.getString("rolename");
                }
            }
        } catch (SQLException e) {
            logger.error(e + "Function:getRole in Class:Role");
        }
        return (null==result ? "": result);
    }

    // check if a permission is set
    public boolean hasPerm(String permission) {
        return permissions.contains(permission);
    }
}
