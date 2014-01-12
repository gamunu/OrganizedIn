/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Data.Config;
import OrganizedIn.Data.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author gamunu
 */
public class OrgMail {

    private static Logger logger = LogManager.getLogger(OrgMail.class.getName());

    /**
     *
     * @param toEmail the receivers email address
     * @param fromEmail the senders email address
     * @param body
     * @param subject
     * @return
     */
    public static boolean sendMail(String toEmail, String body, String subject) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(Config.getEmailHost());
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(Config.getEmailUsername(), Config.getEmailPassword()));
            email.setSSLOnConnect(true);
            email.setFrom(Config.getEmailUsername());
            email.setSubject(subject);
            email.setMsg(body);
            email.addTo(toEmail);
            email.send();
            return true;
        } catch (EmailException ex) {
            logger.error(ex.getMessage());
        }
        return false;

    }

    /**
     *
     * @param subject the email message subject
     * @param message the email message body/message
     * @param userfrom senders email address
     * @param userto receivers email address
     * @return
     */
    public static int addMail(String subject, String message, String userto) {
        int result = 0;
        String query = "INSERT INTO ORGEMAIL(subject,message,userfrom,userto) VALUES(?,?,?,?)";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query, new String[]{"emailid"})) {

            pstmt.setString(1, subject);
            pstmt.setString(1, message);
            pstmt.setString(1, Config.getEmailUsername());
            pstmt.setString(1, userto);

            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    public static boolean deleteEmail(int emailid) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM ORGEMAIL WHERE emailid = ?")) {
            stmt.setInt(1, emailid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;

    }
}
