/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Data;

import OrganizedIn.Lib.Crypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Config {

    private static Logger logger = LogManager.getLogger(Config.class.getName());

    /**
     * Configuration: Database Connection, Uses java Preferences API
     *
     * @return Database Data Source
     */
    public static String getDbDatasource() {
        Preferences prefs = Preferences.userRoot().node("organizedin").node("database");
        return prefs.get("connectionurl", "");
    }

    /**
     * Configuration: Database Connection, Uses java Preferences API
     *
     * @param ds Data Source String Ex: jdbc:mysql:
     * @//localhost/_database_name-
     */
    public static void setDbDatasource(String ds) {
        try {
            Preferences prefs = Preferences.userRoot().node("organizedin").node("database");
            prefs.put("connectionurl", ds);
            prefs.flush();
        } catch (BackingStoreException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Configuration: Database Connection, Uses java Preferences API
     *
     * @param ds Database username, String
     */
    public static void setDbUsername(String du) {
        try {
            Preferences prefs = Preferences.userRoot().node("organizedin").node("database");
            prefs.put("username", du);
            prefs.flush();
        } catch (BackingStoreException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Configuration: Database Connection, Uses java Preferences API
     *
     * @param ds Database password, String
     */
    public static void setDbPassword(String dp) {
        try {
            Preferences prefs = Preferences.userRoot().node("organizedin").node("database");
            Crypt crypt = new Crypt();
            prefs.put("password", crypt.encrypt(dp));
            prefs.flush();
        } catch (BackingStoreException ex) {
            logger.error(ex.getMessage());
        }
    }

    /**
     * Configuration: Database Connection, Uses java Preferences API
     *
     * @return Database username
     */
    public static String getDbUsername() {
        Preferences prefs = Preferences.userRoot().node("organizedin").node("database");

        return prefs.get("username", "");
    }

    /**
     * Configuration: Database Connection, Uses java Preferences API
     *
     * @return Database password
     */
    public static String getDbPassword() {
        Preferences prefs = Preferences.userRoot().node("organizedin").node("database");
        Crypt crypt = new Crypt();
        String passwod = crypt.decrypt(prefs.get("password", "we"));
        return passwod;
    }

    /**
     * Update and store email server hostname
     *
     * @param hostname the email server hostname
     * @return return true if update was successful else returns false
     */
    public static Boolean setEmailHost(String hostname) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE SETTING SET svalue = ? WHERE sname = ?")) {
            stmt.setString(1, hostname);
            stmt.setString(2, "hostname");
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    /**
     *      *
     * @return the email host
     */
    public static String getEmailHost() {
        String query = "SELECT svalue FROM SETTING WHERE sname = ?";
        String rtnvalue = "";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "hostname");
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    rtnvalue = result.getString("svalue");
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return rtnvalue;
    }

    /**
     * Encrypt and store email server password to the database
     *
     * @param password the email server password
     * @return return true if update was successful else return false
     */
    public static Boolean setEmailPassword(String password) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE SETTING SET svalue = ? WHERE sname = ?")) {
            Crypt crypt = new Crypt();
            stmt.setString(1, crypt.encrypt(password));
            stmt.setString(2, "emailpassword");
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }


    /**
     * Return decrypted password
     *
     * @return the email server password
     */
    public static String getEmailPassword() {
        String query = "SELECT svalue FROM SETTING WHERE sname = ?";
        String rtnvalue = "";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "emailpassword");
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    Crypt crypt = new Crypt();
                    rtnvalue = crypt.decrypt(result.getString("svalue"));
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return rtnvalue;
    }

    /**
     * Store email server username to the database
     *
     * @param username the username of the email server
     * @return true if update was successful els it returns false
     */
    public static Boolean setEmailUsername(String username) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE SETTING SET svalue = ? WHERE sname = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, "emailusername");
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    /**
     *
     * @return the email server username
     */
    public static String getEmailUsername() {
        String query = "SELECT svalue FROM SETTING WHERE sname = ?";
        String rtnvalue = "";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, "emailusername");
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    rtnvalue = result.getString("svalue");
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return rtnvalue;
    }
}
