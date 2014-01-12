/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Helper;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.Lib.ComboItem;
import OrganizedIn.Lib.Feedback;
import OrganizedIn.Lib.FeedbackCell;
import OrganizedIn.Lib.FeedbackTableModel;
import OrganizedIn.Lib.Help.HIGHLIGHT;
import OrganizedIn.Lib.Help.Message;
import OrganizedIn.Lib.Help.MessageCell;
import OrganizedIn.Lib.Help.MessageTableModel;
import OrganizedIn.Lib.OrgSystem;
import OrganizedIn.Lib.Help.PRIORITY;
import OrganizedIn.Lib.Help.STATUS;
import OrganizedIn.Lib.Help.Ticket;
import OrganizedIn.Lib.Help.TicketCell;
import OrganizedIn.Lib.Help.TicketTableModel;
import OrganizedIn.Lib.OrgMail;
import OrganizedIn.Lib.User;
import OrganizedIn.UI.HelpDeskPanel;
import OrganizedIn.UI.Lib.FeedBack;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author gamunu
 */
public class HelperHelpdesk {
    //Load ticket list

    private static Logger logger = LogManager.getLogger(HelperHelpdesk.class.getName());

    public static void loadTickets(HelpDeskPanel panel) {
        panel.Help_TicketsTicketList.setDefaultRenderer(Ticket.class, new TicketCell());

        List<Ticket> tiketlist = new ArrayList<>();

        String query;

        if (OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
            query = "SELECT * FROM TICKET WHERE status = 'open'";
        } else {
            query = "SELECT * FROM TICKET WHERE status = 'open' AND userid= ?";
        }

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                stmt.setInt(1, OrgSystem.User.getUserId());
            }

            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    String subject = result.getString("subject");
                    String message = result.getString("message");
                    int ticketid = result.getInt("ticketid");
                    int userid = result.getInt("userid");
                    String priority = result.getString("priority");
                    String highlight = result.getString("highlight");
                    String othighlight = result.getString("othighlight");
                    tiketlist.add(new Ticket(ticketid, subject, message, PRIORITY.valueOf(priority), highlight, othighlight, userid));
                }
            } catch (SQLException e) {
                logger.error(e + "Function:loadTickets in Class:HelperHelpdesk (ResultSet)");
            }
            TicketTableModel ticketlist = new TicketTableModel(tiketlist);
            panel.Help_TicketsTicketList.setModel(ticketlist);
        } catch (SQLException ex) {
            logger.error(ex + "Function:loadTickets in Class:HelperHelpdesk");
        }
    }

    public static void loadTickets(HelpDeskPanel panel, String searchq) {
        panel.Help_TicketsTicketList.setDefaultRenderer(Ticket.class, new TicketCell());

        List<Ticket> tiketlist = new ArrayList<>();

        String query;

        if (OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
            query = "SELECT * FROM TICKET WHERE status = 'open' AND subject LIKE ? OR message LIKE ?";
        } else {
            query = "SELECT * FROM TICKET WHERE status = 'open' AND userid= ? AND subject LIKE ? OR message LIKE ?";
        }

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                stmt.setInt(1, OrgSystem.User.getUserId());
                stmt.setString(2, "%" + searchq + "%");
                stmt.setString(3, "%" + searchq + "%");
            }
            stmt.setString(1, "%" + searchq + "%");
            stmt.setString(2, "%" + searchq + "%");

            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    String subject = result.getString("subject");
                    String message = result.getString("message");
                    int ticketid = result.getInt("ticketid");
                    int userid = result.getInt("userid");
                    String priority = result.getString("priority");
                    String highlight = result.getString("highlight");
                    String othighlight = result.getString("othighlight");
                    tiketlist.add(new Ticket(ticketid, subject, message, PRIORITY.valueOf(priority), highlight, othighlight, userid));
                }
            } catch (SQLException e) {
                logger.error(e + "Function:loadTickets in Class:HelperHelpdesk (ResultSet)");
            }
            TicketTableModel ticketlist = new TicketTableModel(tiketlist);
            panel.Help_TicketsTicketList.setModel(ticketlist);
        } catch (SQLException ex) {
            logger.error(ex + "Function:loadTickets in Class:HelperHelpdesk");
        }
    }

    public static void loadTickets(HelpDeskPanel panel, String searchq, PRIORITY ppriority) {
        panel.Help_TicketsTicketList.setDefaultRenderer(Ticket.class, new TicketCell());

        List<Ticket> tiketlist = new ArrayList<>();

        String query;

        if (!searchq.equals("")) {
            if (OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                query = "SELECT * FROM TICKET WHERE status = 'open' AND priority = ? AND subject LIKE ? OR message LIKE ?";
            } else {
                query = "SELECT * FROM TICKET WHERE status = 'open' AND userid= ? AND priority = ? AND subject LIKE ? OR message LIKE ?";
            }
        } else {
            if (OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                query = "SELECT * FROM TICKET WHERE status = 'open' AND priority = ?";
            } else {
                query = "SELECT * FROM TICKET WHERE status = 'open' AND priority = ? AND userid= ? ";
            }
        }

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!searchq.equals("")) {
                if (!OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                    stmt.setInt(1, OrgSystem.User.getUserId());
                    stmt.setString(2, ppriority.toString());
                    stmt.setString(3, "%" + searchq + "%");
                    stmt.setString(4, "%" + searchq + "%");
                } else {
                    stmt.setString(1, ppriority.toString());
                    stmt.setString(2, "%" + searchq + "%");
                    stmt.setString(3, "%" + searchq + "%");
                }
            } else {
                if (!OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                    stmt.setString(1, ppriority.toString());
                    stmt.setInt(2, OrgSystem.User.getUserId());
                } else {
                    stmt.setString(1, ppriority.toString());
                }
            }
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    String subject = result.getString("subject");
                    String message = result.getString("message");
                    int ticketid = result.getInt("ticketid");
                    int userid = result.getInt("userid");
                    String priority = result.getString("priority");
                    String highlight = result.getString("highlight");

                    String othighlight = result.getString("othighlight");
                    tiketlist.add(new Ticket(ticketid, subject, message, PRIORITY.valueOf(priority), highlight, othighlight, userid));
                }
            } catch (SQLException e) {
                logger.error(e + "Function:loadTickets in Class:HelperHelpdesk (ResultSet)");
            }
            TicketTableModel ticketlist = new TicketTableModel(tiketlist);
            panel.Help_TicketsTicketList.setModel(ticketlist);
        } catch (SQLException ex) {
            logger.error(ex + "Function:loadTickets in Class:HelperHelpdesk");
        }
    }

    public static void loadSlovedTickets(HelpDeskPanel panel) {
        panel.Help_SolvedTicketList.setDefaultRenderer(Ticket.class, new TicketCell());

        List<Ticket> tiketlist = new ArrayList<>();

        String query;
        if (OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
            query = "SELECT * FROM TICKET WHERE status = 'solved'";
        } else {
            query = "SELECT * FROM TICKET WHERE status = 'solved' AND userid= ?";
        }

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                stmt.setInt(1, OrgSystem.User.getUserId());
            }
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    String subject = result.getString("subject");
                    String message = result.getString("message");
                    int ticketid = result.getInt("ticketid");
                    int userid = result.getInt("userid");
                    String priority = result.getString("priority");
                    String highlight = result.getString("highlight");

                    String othighlight = result.getString("othighlight");
                    tiketlist.add(new Ticket(ticketid, subject, message, PRIORITY.valueOf(priority), highlight, othighlight, userid));
                }
            } catch (SQLException ex) {
                logger.error(ex + "Function:loadSlovedTickets in Class:HelperHelpdesk (ResultSet)");
            }
            TicketTableModel ticketlist = new TicketTableModel(tiketlist);
            panel.Help_SolvedTicketList.setModel(ticketlist);

        } catch (SQLException e) {
            logger.error(e + "Function:loadSlovedTickets in Class:HelperHelpdesk");
        }
    }

    public static void loadClosedTickets(HelpDeskPanel panel) {
        panel.Help_ClosedTicketList.setDefaultRenderer(Ticket.class, new TicketCell());

        List<Ticket> tiketlist = new ArrayList<>();

        String query;
        if (OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
            query = "SELECT * FROM TICKET WHERE status = 'closed'";
        } else {
            query = "SELECT * FROM TICKET WHERE status = 'closed' AND userid= ?";
        }

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!OrgSystem.User.hasPrivilege("view:tickets") || OrgSystem.User.hasPrivilege("has:root")) {
                stmt.setInt(1, OrgSystem.User.getUserId());
            }
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    String subject = result.getString("subject");
                    String message = result.getString("message");
                    int ticketid = result.getInt("ticketid");
                    int userid = result.getInt("userid");
                    String priority = result.getString("priority");
                    String highlight = result.getString("highlight");
                    String othighlight = result.getString("othighlight");
                    tiketlist.add(new Ticket(ticketid, subject, message, PRIORITY.valueOf(priority), highlight, othighlight, userid));
                }
            }
            TicketTableModel ticketlist = new TicketTableModel(tiketlist);
            panel.Help_ClosedTicketList.setModel(ticketlist);

        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public static void loadFeedbackMessages(HelpDeskPanel panel) {
        panel.helpFeedBackTbl.setDefaultRenderer(Feedback.class, new FeedbackCell());

        ArrayList<Feedback> fbmessages = new ArrayList<>();

        String query;
        if (OrgSystem.User.hasPrivilege("view:feedbacks") || OrgSystem.User.hasPrivilege("has:root")) {
            query = "SELECT * FROM FEEDBACK";
        } else {
            query = "SELECT * FROM FEEDBACK WHERE receiverid = ?";
        }

        try (Connection conn = DataAccess.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            if (!OrgSystem.User.hasPrivilege("view:feedbacks") || OrgSystem.User.hasPrivilege("has:root")) {
                stmt.setInt(1, OrgSystem.User.getUserId());
            }
            try (ResultSet result = stmt.executeQuery()) {
                while (result.next()) {
                    int feedbackid = result.getInt("feedbackid");
                    int senderid = result.getInt("senderid");
                    int reciverid = result.getInt("receiverid");
                    int ticketid = result.getInt("ticketid");
                    String message = result.getString("message");
                    String highlight = result.getString("highlight");
                    String status = result.getString("status");

                    fbmessages.add(new Feedback(senderid, reciverid, ticketid, feedbackid, highlight, message, STATUS.valueOf(status)));
                }
            }
            FeedbackTableModel fbmodel = new FeedbackTableModel(fbmessages);
            panel.helpFeedBackTbl.setModel(fbmodel);

        } catch (SQLException ex) {
            logger.error(ex);
        }
    }

    public static void closedReopen(HelpDeskPanel panel) {
        if (OrgSystem.User.hasPrivilege("reopen:ticket") || OrgSystem.User.hasPrivilege("has:root")) {
            Ticket o = (Ticket) panel.Help_ClosedTicketList.getModel().getValueAt(panel.Help_ClosedTicketList.getSelectedRow(), 0);
            if (o.changeStatus(STATUS.open)) {
                JOptionPane.showMessageDialog(panel, "Ticket Is Reopned");
                HelperHelpdesk.loadTickets(panel);
                HelperHelpdesk.loadSlovedTickets(panel);
                HelperHelpdesk.loadClosedTickets(panel);
            } else {
                JOptionPane.showMessageDialog(panel, "Something went wrong!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "You don't have sufficient privileges", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void solvedReopen(HelpDeskPanel panel) {
        if (OrgSystem.User.hasPrivilege("reopen:ticket") || OrgSystem.User.hasPrivilege("has:root")) {
            Ticket o = (Ticket) panel.Help_SolvedTicketList.getModel().getValueAt(panel.Help_SolvedTicketList.getSelectedRow(), 0);
            if (o.changeStatus(STATUS.open)) {
                JOptionPane.showMessageDialog(panel, "Ticket Is Reopned");
                HelperHelpdesk.loadTickets(panel);
                HelperHelpdesk.loadSlovedTickets(panel);
                HelperHelpdesk.loadClosedTickets(panel);
            } else {
                JOptionPane.showMessageDialog(panel, "Something went wrong!");
            }
        } else {
            JOptionPane.showMessageDialog(panel, "You don't have sufficient privileges", "Warning!", JOptionPane.WARNING_MESSAGE);
        }

    }

    public static void setTicketSolved(HelpDeskPanel panel) {
        if (OrgSystem.User.hasPrivilege("change:status") || OrgSystem.User.hasPrivilege("has:root")) {
            Ticket o = (Ticket) panel.Help_TicketsTicketList.getModel().getValueAt(panel.Help_TicketsTicketList.getSelectedRow(), 0);
            if (o.getUserid() == OrgSystem.User.getUserId()) {
                if (o.changeStatus(STATUS.solved)) {
                    JOptionPane.showMessageDialog(panel, "Ticket Set to solved");
                  
                    HelperHelpdesk.loadTickets(panel);
                    HelperHelpdesk.loadSlovedTickets(panel);
                    HelperHelpdesk.loadClosedTickets(panel);
                } else {
                    JOptionPane.showMessageDialog(panel, "Something went wrong!");
                }
            } else {

                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("Send Feedback");
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                FeedBack fdb = new FeedBack(o.getTicketid(), o.getUserid(), dialog, STATUS.solved, panel);
                dialog.add(fdb);
                dialog.setSize(fdb.getPreferredSize());
                dialog.setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(panel, "You don't have sufficient privileges", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void setTicketClosed(HelpDeskPanel panel) {
        //Check user has privileges to change status
        if (OrgSystem.User.hasPrivilege("change:status") || OrgSystem.User.hasPrivilege("has:root")) {

            //Get selected ticket(row object)
            Ticket o = (Ticket) panel.Help_TicketsTicketList.getModel().getValueAt(panel.Help_TicketsTicketList.getSelectedRow(), 0);

            //Check logged in user is created the ticket
            if (o.getUserid() == OrgSystem.User.getUserId()) {
                //Close ticket
                if (o.changeStatus(STATUS.closed)) {
                    //if close success show message
                    JOptionPane.showMessageDialog(panel, "Ticket Is Closed");

                    OrgMail.addMail("LECO (Pvt)Ltd Help Desk: Your Ticket Closed", "Your ticket set to closed :\n\n" + o.getSubject(), User.getEmail(o.getUserid()));
                    //reload tickets
                    HelperHelpdesk.loadTickets(panel);
                    HelperHelpdesk.loadSlovedTickets(panel);
                    HelperHelpdesk.loadClosedTickets(panel);
                } else {
                    //if close failed show error messages
                    JOptionPane.showMessageDialog(panel, "Something went wrong!");
                }
            } else {
                //Show feedback dialog
                JDialog dialog = new JDialog();
                dialog.setModal(true);
                dialog.setTitle("Send Feedback");
                dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                FeedBack fdb = new FeedBack(o.getTicketid(), o.getUserid(), dialog, STATUS.closed, panel);
                dialog.add(fdb);
                dialog.setSize(new Dimension(fdb.getPreferredSize().width + 20, fdb.getPreferredSize().height + 20));// fdb.getPreferredSize());
                dialog.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(panel, "Sorry! You don't have sufficient privileges", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void acceptFeedback(HelpDeskPanel panel) {
        Feedback o = (Feedback) panel.helpFeedBackTbl.getModel().getValueAt(panel.helpFeedBackTbl.getSelectedRow(), 0);
        Ticket.changeStatus(o.getStatus(), o.getTicketid());
        o.changeHighlight(HIGHLIGHT.N);
        //Reload Tables
        HelperHelpdesk.loadTickets(panel);
        HelperHelpdesk.loadSlovedTickets(panel);
        HelperHelpdesk.loadClosedTickets(panel);
        HelperHelpdesk.loadFeedbackMessages(panel);
    }

    public static void declineFeedback(HelpDeskPanel panel) {
        Feedback o = (Feedback) panel.helpFeedBackTbl.getModel().getValueAt(panel.helpFeedBackTbl.getSelectedRow(), 0);
        o.changeHighlight(HIGHLIGHT.N);
        //Reload Tables
        HelperHelpdesk.loadTickets(panel);
        HelperHelpdesk.loadSlovedTickets(panel);
        HelperHelpdesk.loadClosedTickets(panel);
        HelperHelpdesk.loadFeedbackMessages(panel);
    }

    public static void replyToMessage(HelpDeskPanel frame) {
        if (OrgSystem.User.hasPrivilege("create:message") || OrgSystem.User.hasPrivilege("has:root")) {
            int sze = frame.Help_TicketsMessageList.getModel().getRowCount();
            int pmssageId;
            int tketId;

            if (sze > 0) {
                Message msg = (Message) frame.Help_TicketsMessageList.getModel().getValueAt(sze - 1, 0);
                pmssageId = msg.getMessageId();
                tketId = msg.getTicketId();
            } else {
                tketId = frame.getReplyTicketId();
                pmssageId = -1;
            }

            Message.createMessage(OrgSystem.User.getUserId(), tketId, pmssageId, frame.Help_ReplyMessageTxt.getText());
            //if(OrgSystem.User.getUserId() == )
            if (OrgSystem.User.getUserId() == Ticket.getUserId(tketId)) {
                Ticket.changeOwnerHighlight(HIGHLIGHT.N, tketId);
                Ticket.changeOtherHighlight(HIGHLIGHT.Y, tketId);
            } else {
                Ticket.changeOwnerHighlight(HIGHLIGHT.Y, tketId);
                Ticket.changeOtherHighlight(HIGHLIGHT.N, tketId);
            }
            frame.Help_TicketsMessageList.setDefaultRenderer(Message.class, new MessageCell());
            MessageTableModel messageModel = new MessageTableModel(Ticket.getMesssages(tketId));
            frame.Help_TicketsMessageList.setModel(messageModel);
        } else {
            JOptionPane.showMessageDialog(frame, "You don't have sufficient privileges", "Warning!", JOptionPane.WARNING_MESSAGE);
        }


    }

    public static void deleteMessage(HelpDeskPanel frame) {
        if (OrgSystem.User.hasPrivilege("delete:message") || OrgSystem.User.hasPrivilege("has:root")) {
            int sze = frame.Help_TicketsMessageList.getModel().getRowCount();
            if (sze >= 0) {
                Message o = (Message) frame.Help_TicketsMessageList.getModel().getValueAt(sze - 1, 0);
                if (o.getUserId() == OrgSystem.User.getUserId()) {
                    if (o.deleteMessage()) {
                        JOptionPane.showMessageDialog(frame, "Message Deletion Successfull");
                        frame.Help_TicketsMessageList.removeAll();
                        frame.Help_TicketsMessageList.setDefaultRenderer(Message.class, new MessageCell());
                        MessageTableModel messageModel = new MessageTableModel(Ticket.getMesssages(o.getTicketId()));
                        frame.Help_TicketsMessageList.setModel(messageModel);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Something went wrong!");
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "You canno't delete this message");
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "You don't have sufficient privileges", "Warning!", JOptionPane.WARNING_MESSAGE);
        }
    }

//    public static void changePriority(HelpDeskPanel panel) {
//        if (OrgSystem.User.hasPrivilege("change:priority") || OrgSystem.User.hasPrivilege("has:root")) {
//            ComboItem item = (ComboItem) panel.TicketPriority.getSelectedItem();
//            String value = item.getValue();
//
//            Ticket o = (Ticket) panel.Help_TicketsTicketList.getModel().getValueAt(panel.Help_TicketsTicketList.getSelectedRow(), 0);
//            if (o.changePriority(value)) {
//                JOptionPane.showMessageDialog(panel, "Priority Changed!");
//            } else {
//                JOptionPane.showMessageDialog(panel, "Something went wrong!");
//            }
//        } else {
//            JOptionPane.showMessageDialog(panel, "You don't have sufficient privileges", "Warning!", JOptionPane.WARNING_MESSAGE);
//        }
//    }
    public static void fillComboItems(HelpDeskPanel panel) {
        panel.Help_CreatePriorityCmb.addItem(new ComboItem("high", "High"));
        panel.Help_CreatePriorityCmb.addItem(new ComboItem("medium", "Medium"));
        panel.Help_CreatePriorityCmb.addItem(new ComboItem("low", "Low"));

        panel.filterTicketPriority.addItem(new ComboItem("high", "High"));
        panel.filterTicketPriority.addItem(new ComboItem("medium", "Medium"));
        panel.filterTicketPriority.addItem(new ComboItem("low", "Low"));
    }
}