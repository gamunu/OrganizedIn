/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Data.DataAccess;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author gamunu
 */
public class ReportViewer extends JFrame {

    private static Logger logger = LogManager.getLogger(DataAccess.class.getName());

    public void showReport(String filename, HashMap parametersMap) {
        try {
            Connection conn = DataAccess.getConnection();


            JasperReport report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/OrganizedIn/IReports/" + filename);

            JasperPrint print = JasperFillManager.fillReport(report, parametersMap, conn);

            JRViewer viewer = new JRViewer(print);

            viewer.setOpaque(true);

            viewer.setVisible(true);
//make your JFrame visible

            this.add(viewer);

            this.setSize(800, 800);

            this.setVisible(true);

            this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        } catch (Exception ex) {

            logger.info("CAUSE: " + ex.getCause());

            logger.info("MESSAGE" + ex.getMessage());

            logger.info("LOCAL MESSAGE" + ex.getLocalizedMessage());
        }

    }
}
