/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.Lib.OrgMail;
import OrganizedIn.Lib.OrgSystem;
import OrganizedIn.UI.Lib.AdminSettings;
import OrganizedIn.UI.Login;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

/**
 *
 * @author Gamunu
 */
public class Main extends Thread {

    private static Logger logger = LogManager.getLogger(Main.class.getName());
    private volatile boolean running = true; // Run unless told to pause

    @Override
    public void run() {
        try {
            String query = "SELECT * FROM ORGEMAIL";
            try (Connection conn = DataAccess.getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                logger.info("before while");
                try (ResultSet result = stmt.executeQuery()) {
                    while (result.next()) {
                        boolean send = false;
                        int count = 10;
                        while (!send || count == 0) {
                            logger.info("looping messages");
                            send = OrgMail.sendMail(result.getString("userto"), result.getString("subject"), result.getString("message"));
                            count--;
                        }
                        if (count > 0) {
                            //if sent delete message
                            logger.info("delete message");
                            OrgMail.deleteEmail(result.getInt("emailid"));
                        } else {
                            //set as failed message
                            logger.info("falied message");
                        }
                    }
                }
            } catch (SQLException ex) {
                logger.error(ex.getMessage());
            }

            while (true) {
                try {
                    System.out.println("Sleeping thread");
                    Thread.sleep(50000);
                } catch (InterruptedException ex) {
                    logger.error(ex.getMessage());
                }

                System.out.println("In run method: woke up again");
            }
        } finally {
            System.out.println("Leaving run Method");

            for (int i = 0;; i++) {
                // Only keep painting while "running" is true
                // This is a crude implementation of pausing the thread
                System.out.println("Running while");
                while (!running) {
                    System.out.println("before yeild");
                }
                yield();
                System.out.println("after yeild");
            }
        }
    }

    public void pauseThread() throws InterruptedException {
        running = false;
    }

    public void resumeThread() {
        running = true;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        OrgSystem.maildeamon = new Main();
        OrgSystem.maildeamon.setDaemon(true);
        OrgSystem.maildeamon.start();

        // TODO code application logic here
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            // BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            UIManager.put("TabbedPane.tabAreaInsets", new Insets(3, 2, 1, 20));
            UIManager.put("RootPane.setupButtonVisible", false);
        } catch (Exception e) {
            logger.error(e + "UI exception thrown");
        }

        if (!DataAccess.testConnection()) {
            if (JOptionPane.showConfirmDialog(null, "We having a trouble to connect with database\n Do you want to configure database settings", "Warning", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                AdminSettings as = new AdminSettings();
                as.setVisible(true);
            }
        } else {
            Login lg = new Login();
            OrgSystem.centreWindow(lg);
            lg.setVisible(true);
        }
    }
}
