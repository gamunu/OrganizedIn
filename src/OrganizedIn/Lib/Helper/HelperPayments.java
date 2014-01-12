/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Helper;

import OrganizedIn.Data.DataAccess;
import OrganizedIn.UI.PaymentsPanel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mayuri
 */
public class HelperPayments {
    //load payment details to table
 /*    public static void PaymentTable(PaymentsPanel frame) {
      frame.PaymentDetailsTbl.removeAll();
      
        DefaultTableModel payments = new DefaultTableModel();
        payments.addColumn("Recipt Number");
        payments.addColumn("Total amount paid");
        payments.addColumn("Type");
        payments.addColumn("Date");
        
        try (Connection conn = DataAccess.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet result = stmt.executeQuery("SELECT reciptno,totalpayment,type,paydate FROM Payment")) {
            
             while (result.next()) {
               
                String Recipt_Number = result.getString(1);
                String Total_amount_paid = result.getString(2);
                String Type = result.getString(3);
                String Date = result.getString(4);
                  
                Payments pay = new Payments();

                payments.addRow(pay.createTableRow(Recipt_Number,Total_amount_paid,Type,Date));//.addElement(pay);
            }
             
              frame.PaymentDetailsTbl.setModel(payments);
              
     } catch (SQLException ex) {  
  
}*/
    public ResultSet getInvoiceNum() {

        //ArrayList data = null;
        DataAccess da = new DataAccess();
        Connection con = da.getConnection();
        ResultSet rs = null;
        
        try {
            Statement st = con.createStatement();
            String qry = " SELECT invoiceno from purchase";
            rs = st.executeQuery(qry);
      //      data = new ArrayList();
            
       /*     while (rs.next()) {

                data.add(rs.getString(1));
                System.out.println("value----:"+rs.getString(1));
            }
            */
            
        } catch (Exception e) {
            
            
        }

        return rs;
    }

    
    
    
    public boolean addPaymentDetails(String rNum, double totAmount, String type, String date, int invNo){
        
        
        boolean result = false;
        
       //create da object from DataAccess
        DataAccess da = new DataAccess();
        //Get the connection to da object
        Connection con = da.getConnection();
        
        try{
         
            Statement st = con.createStatement();
            String qry = "INSERT INTO payment(recieptno, totamount, type, pdate, purchaseid) VALUES( '" + rNum + "' , '" + totAmount + "' , '" + type + "' , '" + date + "' , "+invNo+" )";
            st.executeUpdate(qry);
            result = true;
        
            }
        catch (SQLException ex){
            result = false;
        }
          return result;
    }
    
     public ResultSet getReceiptNum() {

     //   ArrayList data = null;
        DataAccess da = new DataAccess();
        Connection con = da.getConnection();
        ResultSet rs = null;
        
        try {
            Statement st = con.createStatement();
            String qry = " SELECT recieptno from payment";
            rs = st.executeQuery(qry);
       /*     data = new ArrayList();
            
            while (rs.next()) {

                data.add(rs.getString(1));
            }*/
            
        } catch (Exception e) {
            
            
        }

        return rs;
    }
     
     

     
     //1=availalbe, 0=not avlble
     public boolean addGRNDetails(String suppliER , String grnDes , int paymentid , int ordeRnum ){
        
        
        boolean result = false;
        
       //create da object from DataAccess
        DataAccess da = new DataAccess();
        //Get the connection to da object
        Connection con = da.getConnection();
        
        try{
         
            Statement st = con.createStatement();
            String qry = "INSERT INTO grn(supplier, grndes,paymentid,ordernum, status) VALUES( '" + suppliER + "' , '" + grnDes + "' ,"+paymentid+"," + ordeRnum + ", 1)";
            st.executeUpdate(qry);
            result = true;
        
            }
        catch (Exception ex){
            System.out.println(ex);
            result = false;
        }
          return result;
    }
     
     public boolean deleteGRNDetails(int gNo){
          boolean result = false;
        
       //create da object from DataAccess
        DataAccess da = new DataAccess();
        //Get the connection to da object
        Connection con = da.getConnection();
        
        try{
         
            Statement st = con.createStatement();
            String qry = "update grn set status=0 where grnno="+gNo;
            st.executeUpdate(qry);
            result = true;
        
            }
        catch (Exception ex){
            System.out.println(ex);
            result = false;
        }
          return result;
     
     
     }
    
      public ResultSet grnRecords(){
        Connection con = DataAccess.getConnection();
        ResultSet rs = null;
        try{
            Statement st = con.createStatement();            
            String qry = "select grnno,supplier,grndes,paymentid, ordernum from grn where status=1";
            rs=st.executeQuery(qry);
        }
        
        catch (SQLException ex){}
        
        return  rs;
    }
      
      
      public ResultSet getPrchaseRecordsByTyping(String supl){
        Connection con = DataAccess.getConnection();
        ResultSet rs = null;
        try{
            Statement st = con.createStatement();            
            String qry = "select invoiceno, purdate, item, quantity, supplier from PURCHASE WHERE supplier LIKE '"+supl+"%' ";
            rs=st.executeQuery(qry);
        }
        
        catch (SQLException ex){}
        
        return  rs;
    }
      
     
    //public boolean  addPurchaseDetails(String invoicenum, String puchasedate,)


      
      public String getLastInvoiceNo() {

        String invcNo = null;
        DataAccess da = new DataAccess();
        Connection con = da.getConnection();
        ResultSet rs = null;
        
        try {
            Statement st = con.createStatement();
            String qry = " select invoiceno from PURCHASE order by invoiceno asc";
            rs = st.executeQuery(qry);
            
            rs.last();
            invcNo = rs.getString(1);
            
        } catch (Exception e) {
            
            
        }

        return invcNo;
    }
      
      public String getLastReceiptNo() {

        String receno = null;
        DataAccess da = new DataAccess();
        Connection con = da.getConnection();
        ResultSet rs = null;
        
        try {
            Statement st = con.createStatement();
            String qry = " select recieptno from PAYMENT order by recieptno asc";
            rs = st.executeQuery(qry);
            
            rs.last();
            receno = rs.getString(1);
            
        } catch (Exception e) {
            
            
        }

        return receno;
    }
      
     
      
      public boolean addPurchaseDetails(String invoicenum, String purchsedate, String item, int quantityInt, String supplier){
          
          boolean result = false;
        
       //create da object from DataAccess
        //Get the connection to da object
        Connection con = DataAccess.getConnection();
        
        try{
         
            Statement st = con.createStatement();
            String qry = "INSERT INTO PURCHASE(invoiceno, purdate, item, quantity, supplier) VALUES( '" + invoicenum + "' , '" + purchsedate + "' , '" + item + "' , " + quantityInt + ", '"+supplier+"')";
            st.executeUpdate(qry);
            result = true;
        
            }
        catch (Exception ex){
            System.out.println(ex);
            result = false;
        }
          return result;
          
      }
      
      
      public ResultSet getPurchaseData(){
        Connection con = DataAccess.getConnection();
        ResultSet rs = null;
        try{
            Statement st = con.createStatement();            
            String qry = "select invoiceno, purdate, item, quantity, supplier from PURCHASE";
            rs=st.executeQuery(qry);
        }
        catch (SQLException ex){}
        return  rs;
    }
      
      
      
      public String getRelevanatPrchsId(String invcNo){
          
        String prchsId = null;
        DataAccess da = new DataAccess();
        Connection con = da.getConnection();
        ResultSet rs = null;
        
        try {
            Statement st = con.createStatement();
            String qry = "select purchaseid from PURCHASE where invoiceno='"+invcNo+"'";
            rs = st.executeQuery(qry);
            rs.first();
            prchsId = rs.getString(1);
            
        } catch (Exception e) {
            System.out.println("failed");
        }
        return prchsId;
      }
      
      public String getRelevanatPaymentId(String receiptNo){
          
        String paymentId = null;
        DataAccess da = new DataAccess();
        Connection con = da.getConnection();
        ResultSet rs = null;
        
        try {
            Statement st = con.createStatement();
            String qry = "select paymentid from payment where recieptno='"+receiptNo+"'";
            rs = st.executeQuery(qry);
            rs.first();
            paymentId = rs.getString(1);
            
        } catch (Exception e) {
            System.out.println("failed");
        }
        return paymentId;
      }
      
      
      
      
      
      
      
}


