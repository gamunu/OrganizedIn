/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Lib.Help.HIGHLIGHT;
import OrganizedIn.Lib.Help.STATUS;
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
public class Feedback {

    private static Logger logger = LogManager.getLogger(Feedback.class.getName());
    private int senderid;
    private int reciverid;
    private int ticketid;
    private int feedbackid;
    private String highlight;
    private String message;
    private STATUS status;

    public Feedback(int senderid, int reciverid, int ticketid, int feedbackid, String highlight, String message, STATUS status) {
        this.senderid = senderid;
        this.reciverid = reciverid;
        this.ticketid = ticketid;
        this.feedbackid = feedbackid;
        this.highlight = highlight;
        this.message = message;
        this.status = status;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
    
    public int getSenderid() {
        return senderid;
    }

    public void setSenderid(int senderid) {
        this.senderid = senderid;
    }

    public int getReciverid() {
        return reciverid;
    }

    public void setReciverid(int reciverid) {
        this.reciverid = reciverid;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public int getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(int feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }
    
    public boolean changeHighlight(HIGHLIGHT hl) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE FEEDBACK SET highlight = ? WHERE feedbackid = ?")) {
            stmt.setString(1, hl.toString());
            stmt.setInt(2, this.feedbackid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public int createFeedback(Feedback message) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO FEEDBACK(ticketid,senderid,receiverid,message,highlight,status) VALUES(?,?,?,?,?,?)", new String[]{"feedbackid"})) {
            pstmt.setInt(1, message.getTicketid());
            pstmt.setInt(2, message.getSenderid());
            pstmt.setInt(3, message.getReciverid());
            pstmt.setString(4, message.getMessage());
            pstmt.setString(5, "Y");            
            pstmt.setString(6, status.toString());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return -1;
    }

    public static int createFeedback(int ticketid, int senderid, int reciverid, String message, STATUS status) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO FEEDBACK(ticketid,senderid,receiverid,message,highlight,status) VALUES(?,?,?,?,?,?)", new String[]{"feedbackid"})) {
            pstmt.setInt(1, ticketid);
            pstmt.setInt(2, senderid);
            pstmt.setInt(3, reciverid);
            pstmt.setString(4, message);
            pstmt.setString(5, "Y");
            pstmt.setString(6, status.toString());
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return -1;
    }

    public boolean deleteFeedback() {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM FEEDBACK WHERE feedbackid = ?")) {
            stmt.setInt(1, this.feedbackid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }
}
