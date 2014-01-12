/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.IReports;

import OrganizedIn.Data.DataAccess;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

/**
 *
 * @author Mayuri
 */
public class MyReportViewerPayments extends JFrame{

    public MyReportViewerPayments(String fileName) {
        this(fileName, null);
    }
    
    public MyReportViewerPayments(String fileName, HashMap parameter){
        super("View Report");
        
        DataAccess da = new DataAccess();
        
        Connection con = da.getConnection();
        
        try{
            JasperPrint print = JasperFillManager.fillReport(fileName, parameter, con);
            JRViewer viewer=new JRViewer(print);

            Container c=getContentPane();
            c.add(viewer);
        }
        
        catch (JRException jre){}
        
        setBounds(10, 10, 850, 690);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
