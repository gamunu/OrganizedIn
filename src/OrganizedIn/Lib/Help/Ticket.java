/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Help;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.Lib.OrgMail;
import OrganizedIn.Lib.OrgSystem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Gamunu
 */
public class Ticket {

    private static Logger logger = LogManager.getLogger(Ticket.class.getName());
    private int ticketid;
    private String subject;
    private String message;
    private PRIORITY priority;
    private String highlight;
    private String othighlight;
    private int userid;

    public Ticket(int ticketid, String subject, String message, PRIORITY priority, String highlight, String othighlight, int userid) {
        this.ticketid = ticketid;
        this.subject = subject;
        this.message = message;
        this.priority = priority;
        this.highlight = highlight;
        this.othighlight = othighlight;
        this.userid = userid;
    }

    public String getOthighlight() {
        return othighlight;
    }

    public void setOthighlight(String othighlight) {
        this.othighlight = othighlight;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    public Ticket() {
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    public static int createTicket(String subject, String message, PRIORITY priority) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("INSERT INTO TICKET(subject,message,status,priority,userid) VALUES(?,?,?,?,?)", new String[]{"ticketid"})) {
            pstmt.setString(1, subject);
            pstmt.setString(2, message);
            pstmt.setString(3, "open");
            pstmt.setString(4, priority.toString());
            pstmt.setInt(5, OrgSystem.User.getUserId());
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

    /**
     * Change the priority of the ticket
     *
     * @return if success return true else false
     */
    public boolean changeStatus(STATUS status) {
        boolean result= false;
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET status = ? WHERE ticketid = ?")) {
            stmt.setString(1, status.toString());
            stmt.setInt(2, this.ticketid);
            if(stmt.executeUpdate() > 0){
                result =true;
            }
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return result;
    }

    public static boolean changeStatus(STATUS status, int ticketid) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET status = ? WHERE ticketid = ?")) {
            stmt.setString(1, status.toString());
            stmt.setInt(2, ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return false;
    }

    public ArrayList<Message> getMesssages() {
        ArrayList<Message> messages = new ArrayList<>();

        String msg;
        int tkid;
        int parentid = 0;
        int messageid;
        int usrid;

        String query = "SELECT * FROM MESSAGE WHERE ticketid = ? AND parentid is null";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.ticketid);
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    msg = result.getString("message");
                    tkid = result.getInt("ticketid");
                    messageid = result.getInt("messageid");
                    usrid = result.getInt("userid");
                    messages.add(new Message(usrid, tkid, messageid, msg));
                }
            }
        } catch (SQLException e) {
            logger.error(e + "Function: getMesssages listener in Class: Ticket (ResultSet)");
        }
        query = "SELECT * FROM Message WHERE ticketid = ? AND parentid IS NOT NULL ORDER BY messageid ASC";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmttwo = conn.prepareStatement(query)) {
            stmttwo.setInt(1, this.ticketid);
            ResultSet resulttwo = stmttwo.executeQuery();
            while (resulttwo.next()) {
                msg = resulttwo.getString("message");
                tkid = resulttwo.getInt("ticketid");
                messageid = resulttwo.getInt("messageid");
                parentid = resulttwo.getInt("parentid");
                usrid = resulttwo.getInt("userid");
                messages.add(new Message(usrid, tkid, parentid, messageid, msg));
            }
        } catch (SQLException e) {
            logger.error(e + "Function: getMesssages listener in Class: Ticket (ResultSet)");
        }
        return messages;
    }

    public static ArrayList<Message> getMesssages(int ticketid) {
        ArrayList<Message> messages = new ArrayList<>();

        String msg;
        int tkid;
        int parentid = 0;
        int messageid;
        int usrid;

        String query = "SELECT * FROM MESSAGE WHERE ticketid = ? AND parentid is null";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticketid);
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    msg = result.getString("message");
                    tkid = result.getInt("ticketid");
                    messageid = result.getInt("messageid");
                    usrid = result.getInt("userid");
                    messages.add(new Message(usrid, tkid, messageid, msg));
                }
            }
        } catch (SQLException e) {
            logger.error(e + "Function: getMesssages listener in Class: Ticket (ResultSet)");
        }
        query = "SELECT * FROM Message WHERE ticketid = ? AND parentid IS NOT NULL ORDER BY messageid ASC";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmttwo = conn.prepareStatement(query)) {
            stmttwo.setInt(1, ticketid);
            ResultSet resulttwo = stmttwo.executeQuery();
            while (resulttwo.next()) {
                msg = resulttwo.getString("message");
                tkid = resulttwo.getInt("ticketid");
                messageid = resulttwo.getInt("messageid");
                parentid = resulttwo.getInt("parentid");
                usrid = resulttwo.getInt("userid");
                messages.add(new Message(usrid, tkid, parentid, messageid, msg));
            }
        } catch (SQLException e) {
            logger.error(e + "Function: getMesssages listener in Class: Ticket (ResultSet)");
        }
        return messages;
    }

    public boolean deleteTicket() {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM TICKET WHERE ticketid = ?")) {
            stmt.setInt(1, this.ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public boolean changePriority(PRIORITY priority) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET priority = ? WHERE ticketid = ?")) {
            stmt.setString(1, priority.toString());
            stmt.setInt(2, this.ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public boolean changePriority(String priority) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET priority = ? WHERE ticketid = ?")) {
            stmt.setString(1, priority);
            stmt.setInt(2, this.ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public boolean changeOwnerHighlight(HIGHLIGHT hl) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET highlight = ? WHERE ticketid = ?")) {
            stmt.setString(1, hl.toString());
            stmt.setInt(2, this.ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public boolean changeOtherHighlight(HIGHLIGHT hl) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET othighlight = ? WHERE ticketid = ?")) {
            stmt.setString(1, hl.toString());
            stmt.setInt(2, this.ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public static boolean changeOwnerHighlight(HIGHLIGHT hl, int ticketid) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET othighlight = ? WHERE ticketid = ?")) {
            stmt.setString(1, hl.toString());
            stmt.setInt(2, ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public static boolean changeOtherHighlight(HIGHLIGHT hl, int ticketid) {
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE TICKET SET othighlight = ? WHERE ticketid = ?")) {
            stmt.setString(1, hl.toString());
            stmt.setInt(2, ticketid);
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            logger.error(ex);
        }
        return false;
    }

    public String getCreatedTime() {
        String query = "SELECT ctime FROM TICKET WHERE ticketid = ?";
        String rtnvalue = "";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, this.ticketid);
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

    public static int getUserId(int ticketid) {
        String query = "SELECT userid FROM TICKET WHERE ticketid = ?";
        int result = 0;
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticketid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getInt("userid");
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }
    
     public static String getSubject(int ticketid) {
        String query = "SELECT subject FROM TICKET WHERE ticketid = ?";
        String result = "";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, ticketid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString("subject");
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }

        } catch (SQLException ex) {
            logger.error(ex.getMessage());
        }
        return result;
    }

    /**
     *
     * @return Last message object, if last message empty return ticket objec
     */
    public Message getLastMessage() {
        String query = "SELECT * FROM Message WHERE ticketid = ? AND parentid IS NOT NULL ORDER BY messageid DESC";
        Message msg = null;
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmttwo = conn.prepareStatement(query)) {
            stmttwo.setInt(1, ticketid);
            ResultSet result = stmttwo.executeQuery();
            if (result.next()) {

                msg = new Message(result.getInt("userid"), result.getInt("ticketid"), result.getInt("parentid"), result.getInt("messageid"), result.getString("message"));
            }
        } catch (SQLException e) {
            logger.error(e + "Function: getMesssages listener in Class: Ticket (ResultSet)");
        }
        return msg;
    }

    /**
     *
     * @return Last message userid, if messages are empty return ticket userid
     */
    public int getLastMessageUserdId() {
        String query = "SELECT * FROM Message WHERE ticketid = ? AND parentid IS NOT NULL ORDER BY messageid DESC";
        int msg = -1;
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmttwo = conn.prepareStatement(query)) {
            stmttwo.setInt(1, ticketid);
            ResultSet result = stmttwo.executeQuery();
            if (result.next()) {
                msg = result.getInt("userid");
            }
        } catch (SQLException e) {
            logger.error(e + "Function: getMesssages listener in Class: Ticket (ResultSet)");
        }

        if (msg == -1) {
            msg = this.userid;
        }
        return msg;
    }

    public String getOwnerDetails() {
        String user = "";
        String query = "SELECT * FROM USERS WHERE userid = ?";
        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setInt(1, this.userid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = "User: " + rs.getString("username") + "\nEmail:" + rs.getString("email");
                }
            }
        } catch (SQLException e) {
            logger.error(e + "Function:getByUsername in Class:User");
        }
        return user;
    }
}