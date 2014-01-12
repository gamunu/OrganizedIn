/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Helper;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.Lib.AutoCompletion;
import OrganizedIn.Lib.ComboItem;
import OrganizedIn.UI.InventoryPanel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author gamunu
 */
public class HelperGlobal {
    //Fill inventory combo boxes

    private static Logger logger = LogManager.getLogger(DataAccess.class.getName());

    public static void fillComboWInventorySerial(InventoryPanel panel) {

        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement()) {

            String query1 = "SELECT serialno FROM INVENTORY";
            ResultSet rs = stmt.executeQuery(query1);

            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("serialno"), rs.getString("serialno"));
                panel.Inven_ManagePCSerialNoCbx.addItem(cbi);
                panel.Inven_ManagePrinSerialNoCbx.addItem(cbi);
                panel.Inven_ManageLapSerialNoCbx.addItem(cbi);
                panel.Inven_ManageOtherSerialNoCbx.addItem(cbi);
                panel.Inven_AssgnPartSerialNoCbx.addItem(cbi);
                panel.Inven_AssgnUsrSerialNoCbx.addItem(cbi);
               // panel.serialNO_cmb.addItem(cbi);
                
            }

        } catch (SQLException ex) {
            System.out.println(ex + "Function: fillComboWInventorySerial in Class: HelperGlobal");
        }
        AutoCompletion.enableAutoComplete(panel.Inven_ManagePCSerialNoCbx);
        AutoCompletion.enableAutoComplete(panel.Inven_ManagePrinSerialNoCbx);
        AutoCompletion.enableAutoComplete(panel.Inven_ManageLapSerialNoCbx);
        AutoCompletion.enableAutoComplete(panel.Inven_ManageOtherSerialNoCbx);
        AutoCompletion.enableAutoComplete(panel.Inven_AssgnPartSerialNoCbx);
        AutoCompletion.enableAutoComplete(panel.Inven_AssgnUsrSerialNoCbx);

    }

    public static void fillComboBoxes(InventoryPanel frame) {

        String query1 = "SELECT * FROM SUPPLIER";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {

            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("SUPPLIERID"), rs.getString("COMPANYNAME"));
                frame.Inven_ManagePcSupNameCbx.addItem(cbi);
                frame.Inven_ManagePrinSupNameCbx.addItem(cbi);
                frame.Inven_ManageLapSupNameCbx.addItem(cbi);
                frame.Inven_ManageOthSupNameCbx.addItem(cbi);
                //    frame.War_NewSupNameCbx.addItem(cbi);
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(ex + "Function: fillComboBoxes in Class: HelperGlobal");
        }

        query1 = "SELECT * FROM BRANCH";
        try (Connection dbConn = DataAccess.getConnection();
                Statement stmt = (Statement) dbConn.createStatement();
                ResultSet rs = stmt.executeQuery(query1)) {


            while (rs.next()) {
                ComboItem cbi = new ComboItem(rs.getString("BRANCHID"), rs.getString("ADDRESS"));
                frame.Inven_ManagePcBranNameCbx.addItem(cbi);
                frame.Inven_ManagePriBranNameCbx.addItem(cbi);
                frame.Inven_ManageLapBranNameCbx.addItem(cbi);
                frame.Inven_ManageOthBranNameCbx.addItem(cbi);
            }

        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(this, "Error occured");
            System.out.println(ex + "Function: fillComboBoxes in Class: HelperGlobal");
        }

        //Add printer, type combo items fill
        ComboItem pcbi = new ComboItem("Local", "Local");
        ComboItem pcbi2 = new ComboItem("Network", "Network");
        frame.Inven_ManagePrinTypeCbx.addItem(pcbi);
        frame.Inven_ManagePrinTypeCbx.addItem(pcbi2);

        //Add printer F.Front combo items fill
        pcbi = new ComboItem("Y", "Yes");
        pcbi2 = new ComboItem("N", "No");
        frame.Inven_ManagePrinFrontCbx.addItem(pcbi);
        frame.Inven_ManagePrinFrontCbx.addItem(pcbi2);
    }

    public ImageIcon createImageIcon(String path,
            String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            logger.error("Couldn't find file: " + path);
            return null;
        }

    }
}
