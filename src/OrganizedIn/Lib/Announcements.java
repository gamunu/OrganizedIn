/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Data.DataAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class Announcements {

    private String announcementId;
    private String subject;
    private String modDate;
    private String targetGroup;
    private String announcement;

    public Announcements(String announcementId, String subject, String mpdDate, String targetGroup, String announcement) {
        this.announcementId = announcementId;
        this.subject = subject;
        this.modDate = mpdDate;
        this.targetGroup = targetGroup;
        this.announcement = announcement;
    }

    public Announcements() {
    }

    /*public String[] createTableRow(String announcementId, String subject, Date Date, String targetGroup, String announcement){
     Format formatter = new SimpleDateFormat("yyyy-MM-dd ");
     String strDate = formatter.format(Date);
     String[] rowAr = {announcementId, subject,strDate , targetGroup, announcement};
     return rowAr;
     }*/
    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getModDate() {
        return modDate;
    }

    public void setModDate(String modDate) {
        this.modDate = modDate;
    }

    public String getTargetGroup() {
        return targetGroup;
    }

    public void setTargetGroup(String targetGroup) {
        this.targetGroup = targetGroup;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public Boolean addAnnouncement(String annId, String sub, String tgtGroup, String annsmnt, String modDt) {
        boolean result = false;
        Connection dbcon = null;


        try {



            DataAccess DataAccess = new DataAccess();
            Connection con = DataAccess.getConnection();

            String query = "Insert into IAnnouncement(componentId, subject, targetGroup, Announcement, modDate) values ('" + annId + "', '" + sub + "', '" + tgtGroup + "', '" + annsmnt + "', '" + modDt + "');";

            Statement stmt = con.createStatement();


            int value1 = stmt.executeUpdate(query);
            if (value1 == 1) {
                JOptionPane.showMessageDialog(null, "Successfully Inserted");
            } else {
                JOptionPane.showMessageDialog(null, "Insertion Error");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        return result;
    }

    public boolean deleteAnnouncement(String annId) {
        Connection con = DataAccess.getConnection();

        try {
            Statement stmt = con.createStatement();

            String query = "UPDATE iannouncement set status='Unvailable'  WHERE componentId='" + annId + "' ";

            int value1 = stmt.executeUpdate(query);

            if (value1 == 1) {
                JOptionPane.showMessageDialog(null, "Successfully Deleted");
            } else {
                JOptionPane.showMessageDialog(null, "Deletion Error");
            }

            return true;
        } catch (SQLException ex) {
        }

        return false;
    }

    public boolean updateAnnouncement(String annId, String subject, String targetGrp, String annsmnt, String modDate) {

        Connection con = DataAccess.getConnection();

        try {
            Statement stmt = con.createStatement();

            String query = "UPDATE iannouncement SET subject='" + subject + "' , targetGroup='" + targetGrp + "' , modDate='" + modDate + "' where componentId='" + annId + "' ";

            int value = stmt.executeUpdate(query);

            if (value == 1) {

                JOptionPane.showMessageDialog(null, "Successfully Updated");
            } else {
                JOptionPane.showMessageDialog(null, "Error in updating");
            }

            return true;
        } catch (SQLException ex) {
        }


        return false;

    }

    public boolean searchAnnouncement(String annId, String Array[]) {
        Connection con = DataAccess.getConnection();
        try {

            Statement stmt = con.createStatement();
            String query = "Select * from iannouncement where componentId = '" + annId + "'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                Array[0] = rs.getString("subject");
                Array[1] = rs.getString("targetGroup");
                Array[2] = rs.getString("Announcement");
                Array[3] = rs.getString("modDate");

            }
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, "Invalid Announcement ID, Searching failed");
        }

        return false;
    }
}
