/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Help;

import OrganizedIn.Data.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author gamunu
 */
public class Message {

    private static Logger logger = LogManager.getLogger(Message.class.getName());
    private int userid;
    private int ticketid;
    private int parentid;
    private int messageid;
    private String message;
    
    public Message(int userid, int TicketID, int ParentID, int MessageID, String Messge) {
        this.userid = userid;
        this.ticketid = TicketID;
        this.parentid = ParentID;
        this.messageid = MessageID;
        this.message = Messge;
    }

    public Message(int userid, int TicketID, int MessageID, String Messge) {
        this.userid = userid;
        this.ticketid = TicketID;
        this.messageid = MessageID;
        this.message = Messge;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public int getTicketId() {
        return ticketid;
    }

    public void setTicketId(int ticketid) {
        this.ticketid = ticketid;
    }

    public int getParentId() {
        return parentid;
    }

    public void setParentId(int parentid) {
        this.parentid = parentid;
    }

    public int getMessageId() {
        return messageid;
    }

    public void setMessageId(int messageid) {
        this.messageid = messageid;
    }

    public String getMessge() {
        return message;
    }

    public void setMessge(String message) {
        this.message = message;
    }

    public static int createMessage(Message message) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO MESSAGE(ticketid,parentid,userid,message) VALUES(?,?,?,?,?)", new String[]{"messageid"})) {
            pstmt.setInt(1, message.getTicketId());
            pstmt.setInt(2, message.getParentId());
            pstmt.setInt(3, message.getUserId());
            pstmt.setString(4, message.getMessge());

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

    public static int createMessage(int userid, int ticketid, int parentid, String message) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO MESSAGE(parentid,ticketid,userid,message) VALUES(?,?,?,?)", new String[]{"messageid"})) {

            if (parentid != -1) {
                pstmt.setInt(1, parentid);
            } else {
                pstmt.setNull(1, parentid);
            }
            pstmt.setInt(2, ticketid);
            pstmt.setInt(3, userid);
            pstmt.setString(4, message);

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

//    public int crateMessage(int ticketid, int pmessageid, String message) {
//
//        try (Connection conn = DataAccess.getConnection();
//                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO message(ticketid,parentid,userid,message) VALUES(?,?,?,?) RETURNING messageid INTO ?", Statement.RETURN_GENERATED_KEYS)) {
//
//            pstmt.setInt(1, ticketid);
//
//            if (pmessageid >= 0) {
//                pstmt.setInt(2, pmessageid);
//            } else {
//                pstmt.setNull(2, Types.int);
//            }
//
//            pstmt.setInt(3, OrgSystem.User.getUserId());
//            pstmt.setString(4, message);
//
//            pstmt.executeUpdate();
//
//            try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                if (rs.next()) {
//                    return rs.getInt(1);
//                }
//            }
//
//        } catch (SQLException ex) {
//            logger.error(ex);
//        }
//        return -1;
//    }
    public boolean deleteMessage() {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM MESSAGE WHERE messageid = ?")) {
            stmt.setInt(1, this.messageid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public String getUsername() {
        String query = "SELECT u.username FROM USERS u JOIN MESSAGE m ON u.userid = m.userid AND m.messageid = ?";
        String rtnvalue = "";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.messageid);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    rtnvalue = result.getString("username");

                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return rtnvalue;
    }

    public String getCreatedTime() {
        String query = "SELECT ctime FROM MESSAGE WHERE messageid = ?";
        String rtnvalue= "";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.messageid);
            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    
                    rtnvalue = new SimpleDateFormat("yyyy-MMM-dd").format(result.getDate("ctime")) + " " + new SimpleDateFormat("hh:mm:ss aaa").format(result.getTime("ctime"));

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
