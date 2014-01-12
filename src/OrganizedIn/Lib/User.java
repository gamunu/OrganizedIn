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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author gamunu
 */
public class User {

    private static Logger logger = LogManager.getLogger(DataAccess.class.getName());
    public Role role;
    protected String username;
    protected int userid;
    protected String password;
    protected String email;

    public String getUsername() {
        return username;
    }

    public int getUserId() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public User() {
    }

    public static void setSystemUser(String usrname) {
        OrgSystem.User = User.getByUsername(usrname);
    }

    public static User getByUsername(String username) {
        User privUser = new User();
        String query = "SELECT * FROM USERS WHERE username = ?";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    privUser.username = rs.getString("username");
                    privUser.password = rs.getString("password");
                    privUser.email = rs.getString("email");
                    privUser.userid = rs.getInt("userid");
                    privUser.initRole();
                }
            }
        } catch (SQLException e) {
            logger.error(e + "Function:getByUsername in Class:User");
        }
        return privUser;
    }

    public static String getEmail(int userid) {
        String email = "";
        String query = "SELECT email FROM USERS WHERE userid = ?";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, userid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (SQLException e) {
            logger.error(e + "Function:getByUsername in Class:User");
        }
        return email;
    }

    // populate role with their associated permissions
    protected void initRole() {
        this.role = new Role();
        String query = "SELECT t2.roleid, t1.rolename FROM ROLES t1 JOIN USERS t2 ON t1.roleid = t2.roleid WHERE t2.userid = ?";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.userid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    role = Role.getRoleWithPerms(rs.getInt("roleid"));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage() + "Function: initRole Class:User");
        }
    }

    //check if user has a specific privilege
    public boolean hasPrivilege(String privilege) {
        if (role.hasPerm(privilege)) {
            return true;
        }
        return false;
    }

    public static boolean isValidUser(String username, String password) {
        boolean rturn = false;
        String sql = "SELECT password FROM USERS WHERE username= ?";
        try (Connection con1 = DataAccess.getConnection();
                PreparedStatement st1 = (PreparedStatement) con1.prepareStatement(sql)) {
            st1.setString(1, username);
            try (ResultSet rs = st1.executeQuery()) {
                if (rs.next()) {
                    if (password.equals(rs.getString("password"))) {
                        rturn = true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage() + "Function: isValidUser Class:User");
        }
        return rturn;
    }

    public static boolean insertRole(String rolename) {
        boolean rturn = false;
        String sql = "INSERT INTO ROLES(rolename) VALUES(?)";
        try (Connection con1 = DataAccess.getConnection();
                PreparedStatement st1 = (PreparedStatement) con1.prepareStatement(sql)) {
            st1.setString(1, rolename);
            if (st1.executeUpdate() > 0) {
                rturn = true;
            }
        } catch (Exception e) {
            logger.error(e.getMessage() + "Function: insertRole Class:User");
        }
        return rturn;
    }
    //  public static boolean deleteRole(int userid){
    //  }
}