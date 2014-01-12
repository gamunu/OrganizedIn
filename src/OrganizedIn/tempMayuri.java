/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn;

import OrganizedIn.Lib.Email.EmailSending;
import OrganizedIn.IReports.MyReportViewerPayments;
import OrganizedIn.Lib.AutoCompletion;
import OrganizedIn.Lib.ComboItem;
import OrganizedIn.Lib.Helper.HelperPayments;
import OrganizedIn.Lib.OrgSystem;
import OrganizedIn.UI.PaymentsPanel;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mayuri
 */
public class tempMayuri extends javax.swing.JFrame {

    HelperPayments hp = new HelperPayments();
    public ArrayList invcNumList=null;
    
    /**
     * Creates new form tempMayuri
     */
    public tempMayuri() {
        
        
        initComponents();
        loadRecieptNum();
        loadTable();
        loadLastInvoiceNo();
        loadPurchaseTable();
        loadLastRecieptNo();
        loadInvoiceNum();
        
        //initComponents();
    }
    
    
    
    public void loadLastInvoiceNo(){
        
        HelperPayments hpObj = new HelperPayments();
        String result = null;
        result = hpObj.getLastInvoiceNo();
        jLabelLastInvoiceNo.setText(result);
        
    }
    public void loadLastRecieptNo(){
        
        HelperPayments hpObj = new HelperPayments();
        String result = null;
        result = hpObj.getLastReceiptNo();
        jLabelLastReceiptNo1.setText(result);
        
    
    
    
    }
    
    
    
    
    public void loadPurchaseTable(){
        HelperPayments hp = new HelperPayments();
    try{
            ResultSet rst = hp.getPurchaseData();
            
            DefaultTableModel dtm = (DefaultTableModel)jTablepurchaseDetails.getModel();
            
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            
            dtm.addColumn("Invoice No");
            dtm.addColumn("Purchase Date");
            dtm.addColumn("Item");
            dtm.addColumn("Quantity");
            dtm.addColumn("Supplier");
            
            
            while(rst.next()){
                Vector v = new Vector();
                v.add(rst.getString(1));
                v.add(rst.getString(2));
                v.add(rst.getString(3));
                v.add(rst.getString(4));
                v.add(rst.getString(5));
                dtm.addRow(v);
            }
        }catch(Exception e){
            System.out.println("load table failed---"+e);
        }
    }
    
    
    
    
    
    public void  loadInvoiceNum(){
        DefaultComboBoxModel cbx = new DefaultComboBoxModel();
        try {
            HelperPayments hp = new HelperPayments();
            //invcNumList = hp.getInvoiceNum();
            ResultSet rs = hp.getInvoiceNum();
            
            while (rs.next()) {
                //ComboItem cbi = new ComboItem(rs.getString("SERIALNO"), rs.getString("SERIALNO"));
                jComboBox2invoice.addItem(rs.getString(1));
            }
            
            /*Iterator i = invcNumList.iterator();

            while (i.hasNext()) {

                cbx.addElement(i.next());
                //System.out.println("recpt list:"+i.next());
            }*/
            
        } catch (Exception ex) {
        }
        AutoCompletion.enableAutoComplete(jComboBox2invoice);

       // jComboBox2invoice.setModel(cbx);
    }
    
    
    
    public void  loadRecieptNum(){
        DefaultComboBoxModel cbx = new DefaultComboBoxModel();
        try {
            HelperPayments hp = new HelperPayments();
            //ArrayList RecieptNum = hp.getReceiptNum();
            ResultSet rs = hp.getReceiptNum();
            
            while (rs.next()) {
                //ComboItem cbi = new ComboItem(rs.getString("SERIALNO"), rs.getString("SERIALNO"));
                receiptnum1.addItem(rs.getString(1));
            }
            
    /*        Iterator i = RecieptNum.iterator();

            while (i.hasNext()) {

                cbx.addElement(i.next());
            }*/
        } catch (Exception ex) {
        }
        AutoCompletion.enableAutoComplete(receiptnum1);

        //receiptnum1.setModel(cbx);
    }
    
    
    
    
    public void loadTable(){
        HelperPayments hp = new HelperPayments();
    try{
            ResultSet rst = hp.grnRecords();
            
            DefaultTableModel dtm = (DefaultTableModel)grntbl.getModel();
            
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            
            dtm.addColumn("GRN No");
            dtm.addColumn("Supplier");
            dtm.addColumn("Description");
            dtm.addColumn("Reciept No");
            dtm.addColumn("Order No");
            
            
            while(rst.next()){
                Vector v = new Vector();
                v.add(rst.getString(1));
                v.add(rst.getString(2));
                v.add(rst.getString(3));
                v.add(rst.getString(4));
                v.add(rst.getString(5));
                dtm.addRow(v);
            }
        }catch(Exception e){
            System.out.println("load table failed---"+e);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jTabbedPane18 = new javax.swing.JTabbedPane();
        maincardconfirmationjPanel49 = new javax.swing.JPanel();
        addjPanel53 = new javax.swing.JPanel();
        jLabel123 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        invoiceNum = new javax.swing.JTextField();
        Quantity = new javax.swing.JTextField();
        jButton69 = new javax.swing.JButton();
        jLabel145 = new javax.swing.JLabel();
        Item = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabelLastInvoiceNo = new javax.swing.JLabel();
        purchaseDate = new com.toedter.calendar.JDateChooser();
        jComboBox2sup = new javax.swing.JComboBox();
        maincardpurchsesjPanel57 = new javax.swing.JPanel();
        detailsjPanel49 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTablepurchaseDetails = new javax.swing.JTable();
        jTextField1prchsTyping = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        updatejPanel53 = new javax.swing.JPanel();
        jLabel140 = new javax.swing.JLabel();
        jTextField104 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jTextField105 = new javax.swing.JTextField();
        jLabel142 = new javax.swing.JLabel();
        jTextField106 = new javax.swing.JTextField();
        jLabel143 = new javax.swing.JLabel();
        jTextField107 = new javax.swing.JTextField();
        jLabel144 = new javax.swing.JLabel();
        jTextField108 = new javax.swing.JTextField();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jLabel146 = new javax.swing.JLabel();
        jTextField110 = new javax.swing.JTextField();
        jPanel55 = new javax.swing.JPanel();
        jTabbedPane16 = new javax.swing.JTabbedPane();
        jPanel56 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        reciptnum = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        totamount = new javax.swing.JTextField();
        jButton60 = new javax.swing.JButton();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jButton67 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2invoice = new javax.swing.JComboBox();
        jLabelLastReceiptNo1 = new javax.swing.JLabel();
        paymentDate1 = new com.toedter.calendar.JDateChooser();
        jPanel58 = new javax.swing.JPanel();
        jTabbedPane17 = new javax.swing.JTabbedPane();
        jPanel52 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jButton65 = new javax.swing.JButton();
        orderNum = new javax.swing.JTextField();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        GRNDes = new javax.swing.JTextArea();
        jComboBoxsupplier2 = new javax.swing.JComboBox();
        receiptnum1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        receiveremail = new javax.swing.JTextField();
        senderemail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        maincardGRNjPanel53 = new javax.swing.JPanel();
        GRNdetailsjPanel53 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        grntbl = new javax.swing.JTable();
        jButton64 = new javax.swing.JButton();
        updateGRNjPanel53 = new javax.swing.JPanel();
        jTextField97 = new javax.swing.JTextField();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jTextField98 = new javax.swing.JTextField();
        jLabel136 = new javax.swing.JLabel();
        jTextField99 = new javax.swing.JTextField();
        jButton63 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        maincardconfirmationjPanel49.setLayout(new java.awt.CardLayout());

        jLabel123.setText("Invoice number (ex: IN001)");

        jLabel135.setText("Purchase date");

        jLabel137.setText("Quantity");

        jLabel139.setText("Supplier");

        Quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QuantityKeyReleased(evt);
            }
        });

        jButton69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Windows Card Space.png"))); // NOI18N
        jButton69.setText("Add");
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });

        jLabel145.setText("Item");

        Item.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Laptop", "Printers", "PC", "Scanner", "Mouse", "Other" }));

        jLabel4.setText("Last Invoice No:");

        purchaseDate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        purchaseDate.setMinSelectableDate(new Date());

        jComboBox2sup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abance", "Soft Logic", "Singer", "Metropolitan" }));

        javax.swing.GroupLayout addjPanel53Layout = new javax.swing.GroupLayout(addjPanel53);
        addjPanel53.setLayout(addjPanel53Layout);
        addjPanel53Layout.setHorizontalGroup(
            addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addjPanel53Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel145, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel123, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel135, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel139, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(75, 75, 75)
                .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(invoiceNum, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(Quantity, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(Item, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(purchaseDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jComboBox2sup, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLastInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        addjPanel53Layout.setVerticalGroup(
            addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addjPanel53Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel123)
                    .addComponent(invoiceNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelLastInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel135)
                    .addComponent(purchaseDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel145)
                    .addComponent(Item, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel137))
                .addGap(41, 41, 41)
                .addGroup(addjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel139)
                    .addComponent(jComboBox2sup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(84, 84, 84)
                .addComponent(jButton69)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        maincardconfirmationjPanel49.add(addjPanel53, "card3");

        jTabbedPane18.addTab("Add purchase", new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Color Management.png")), maincardconfirmationjPanel49); // NOI18N

        maincardpurchsesjPanel57.setLayout(new java.awt.CardLayout());

        jTablepurchaseDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane21.setViewportView(jTablepurchaseDetails);

        jTextField1prchsTyping.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1prchsTypingKeyReleased(evt);
            }
        });

        jLabel7.setText("Type to search by supplier:");

        javax.swing.GroupLayout detailsjPanel49Layout = new javax.swing.GroupLayout(detailsjPanel49);
        detailsjPanel49.setLayout(detailsjPanel49Layout);
        detailsjPanel49Layout.setHorizontalGroup(
            detailsjPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsjPanel49Layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(jLabel7)
                .addGap(79, 79, 79)
                .addComponent(jTextField1prchsTyping, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(520, Short.MAX_VALUE))
            .addGroup(detailsjPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane21)
                .addContainerGap())
        );
        detailsjPanel49Layout.setVerticalGroup(
            detailsjPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailsjPanel49Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(detailsjPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1prchsTyping, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.DEFAULT_SIZE, 413, Short.MAX_VALUE)
                .addContainerGap())
        );

        maincardpurchsesjPanel57.add(detailsjPanel49, "card2");

        jLabel140.setText("Invoice number");

        jLabel141.setText("Purchase date");

        jLabel142.setText("Quantity");

        jLabel143.setText("Branch");

        jLabel144.setText("Supplier");

        jButton72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Windows Card Space.png"))); // NOI18N
        jButton72.setText("Add");
        jButton72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton72ActionPerformed(evt);
            }
        });

        jButton73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Recycle Bin Full.png"))); // NOI18N
        jButton73.setText("Close");
        jButton73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton73ActionPerformed(evt);
            }
        });

        jLabel146.setText("Item");

        javax.swing.GroupLayout updatejPanel53Layout = new javax.swing.GroupLayout(updatejPanel53);
        updatejPanel53.setLayout(updatejPanel53Layout);
        updatejPanel53Layout.setHorizontalGroup(
            updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatejPanel53Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel144, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel140, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel141, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel143, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel146, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(61, 61, 61)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jTextField104, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(jTextField105, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(jTextField106, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(jTextField107, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addComponent(jTextField110, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                    .addComponent(jTextField108, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(651, Short.MAX_VALUE))
            .addGroup(updatejPanel53Layout.createSequentialGroup()
                .addGap(341, 341, 341)
                .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton73)
                .addGap(0, 609, Short.MAX_VALUE))
        );
        updatejPanel53Layout.setVerticalGroup(
            updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatejPanel53Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel140)
                    .addComponent(jTextField104, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel141)
                    .addComponent(jTextField105, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel146)
                    .addComponent(jTextField110, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel142)
                    .addComponent(jTextField106, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel143)
                    .addComponent(jTextField107, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel144)
                    .addComponent(jTextField108, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(updatejPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton72)
                    .addComponent(jButton73))
                .addGap(23, 23, 23))
        );

        maincardpurchsesjPanel57.add(updatejPanel53, "card3");

        jTabbedPane18.addTab("Purchase details", new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Backup and Restore.png")), maincardpurchsesjPanel57); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane18)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane18)
        );

        jTabbedPane5.addTab("Purchases", new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/AutoPlay.png")), jPanel10); // NOI18N

        jLabel12.setText("Receipt Number");

        jLabel18.setText("Total Amount Paid (Rs.)");

        totamount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                totamountFocusLost(evt);
            }
        });
        totamount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                totamountKeyReleased(evt);
            }
        });

        jButton60.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Windows Card Space.png"))); // NOI18N
        jButton60.setText("Add");
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton60ActionPerformed(evt);
            }
        });

        jLabel124.setText("Type");

        jLabel125.setText("Date");

        jButton67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/System Information.png"))); // NOI18N
        jButton67.setText("View payment details");
        jButton67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton67ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cheque", "Cash" }));

        jLabel2.setText("Invoice Number List");

        jLabel5.setText("Last Reciept No:");

        jComboBox2invoice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox2invoiceKeyReleased(evt);
            }
        });

        paymentDate1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        paymentDate1.setMinSelectableDate(new Date());

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel125, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reciptnum)
                            .addComponent(totamount)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, 250, Short.MAX_VALUE)
                            .addComponent(paymentDate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(42, 42, 42)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelLastReceiptNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jComboBox2invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(jButton60, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jButton67)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2invoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reciptnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel5)))
                    .addComponent(jLabelLastReceiptNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(totamount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel124, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel125)
                    .addComponent(paymentDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton60)
                    .addComponent(jButton67))
                .addContainerGap(173, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(349, Short.MAX_VALUE))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane16.addTab("Add Payments", new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/On Screen Keyboard.png")), jPanel56); // NOI18N

        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Printing"));

        jButton65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Recent Items.png"))); // NOI18N
        jButton65.setText("Add GRN and Send the email");
        jButton65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton65ActionPerformed(evt);
            }
        });

        jLabel127.setText("Order Number");

        jLabel128.setText("Supplier");

        jLabel129.setText("GRN Description");

        jLabel3.setText("Reciept Number List");

        GRNDes.setColumns(20);
        GRNDes.setRows(5);
        jScrollPane18.setViewportView(GRNDes);

        jComboBoxsupplier2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abans", "Singer", "Soft Logic", "Metropolitan" }));
        jComboBoxsupplier2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxsupplier2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                            .addGroup(jPanel54Layout.createSequentialGroup()
                                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxsupplier2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel54Layout.createSequentialGroup()
                                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel127)
                                            .addComponent(jLabel128)
                                            .addComponent(jLabel129))
                                        .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel54Layout.createSequentialGroup()
                                                .addGap(65, 65, 65)
                                                .addComponent(orderNum, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(receiptnum1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(receiptnum1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel127)
                    .addComponent(orderNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel128)
                    .addComponent(jComboBoxsupplier2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel129)
                    .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("email"));

        jLabel1.setText("Sender Email");

        jLabel130.setText("Receiver Email");

        jLabel6.setText("Password");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(senderemail, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel130, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(receiveremail, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                            .addComponent(password))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senderemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel130)
                    .addComponent(receiveremail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 101, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        jTabbedPane17.addTab("Main", jPanel52);

        maincardGRNjPanel53.setLayout(new java.awt.CardLayout());

        grntbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane20.setViewportView(grntbl);

        jButton64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x32/Recycle Bin Full.png"))); // NOI18N
        jButton64.setText("Delete");
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton64ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GRNdetailsjPanel53Layout = new javax.swing.GroupLayout(GRNdetailsjPanel53);
        GRNdetailsjPanel53.setLayout(GRNdetailsjPanel53Layout);
        GRNdetailsjPanel53Layout.setHorizontalGroup(
            GRNdetailsjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, GRNdetailsjPanel53Layout.createSequentialGroup()
                .addContainerGap(193, Short.MAX_VALUE)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton64)
                .addGap(34, 34, 34))
        );
        GRNdetailsjPanel53Layout.setVerticalGroup(
            GRNdetailsjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GRNdetailsjPanel53Layout.createSequentialGroup()
                .addGroup(GRNdetailsjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GRNdetailsjPanel53Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jButton64))
                    .addGroup(GRNdetailsjPanel53Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        maincardGRNjPanel53.add(GRNdetailsjPanel53, "card2");

        jLabel133.setText("Order Number");

        jLabel134.setText("Supplier");

        jLabel136.setText("GRN No");

        jButton63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Windows Card Space.png"))); // NOI18N
        jButton63.setText("Add");
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton63ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateGRNjPanel53Layout = new javax.swing.GroupLayout(updateGRNjPanel53);
        updateGRNjPanel53.setLayout(updateGRNjPanel53Layout);
        updateGRNjPanel53Layout.setHorizontalGroup(
            updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateGRNjPanel53Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(updateGRNjPanel53Layout.createSequentialGroup()
                        .addGroup(updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel133)
                            .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel134))
                        .addGap(39, 39, 39)
                        .addGroup(updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField99, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField97, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(jTextField98))
                        .addGap(219, 219, 219)))
                .addContainerGap(462, Short.MAX_VALUE))
        );
        updateGRNjPanel53Layout.setVerticalGroup(
            updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateGRNjPanel53Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel133)
                    .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel136)
                    .addComponent(jTextField99, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(updateGRNjPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel134)
                    .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(jButton63)
                .addGap(151, 151, 151))
        );

        maincardGRNjPanel53.add(updateGRNjPanel53, "card3");

        jTabbedPane17.addTab("Line", maincardGRNjPanel53);

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 1136, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane17)
        );

        jTabbedPane16.addTab("GRN Entry", new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/BitLocker.png")), jPanel58); // NOI18N

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 1157, Short.MAX_VALUE)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 564, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Payments", new javax.swing.ImageIcon(getClass().getResource("/OrganizedIn/Res/Images/x16/Folder Options.png")), jPanel55); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1178, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton60ActionPerformed
        // TODO add your handling code here:
       String invNo = jComboBox2invoice.getSelectedItem().toString();
       String prchsId = null;
       
       HelperPayments hp1 = new HelperPayments();
       prchsId = hp1.getRelevanatPrchsId(invNo);
       
       int prchasIdInt = 0;
       try{
           prchasIdInt = Integer.parseInt(prchsId);
       }
       catch(Exception e){
           System.out.println("failed");
       }
       
        
        
        String reciptNum = reciptnum.getText();
        
        String totAmount = totamount.getText();
        
        Double totAmountDbl=0.0;
        try{
         totAmountDbl = Double.parseDouble(totAmount);
        }
        catch(Exception e){
            System.out.println("failed");
        }
        String moneyType = null;
        
        moneyType = jComboBox1.getSelectedItem().toString();
        
        String date = null;
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sm.format(paymentDate1.getDate());
        } catch (Exception e) {
            System.out.println("date failed");
        }
        
        
        if(!reciptNum.equals(null) && !totAmount.equals(null) && !moneyType.equals(null)&& !date.equals(null) && prchasIdInt!=0){
            boolean ds4 = hp.addPaymentDetails(reciptNum, totAmountDbl,  moneyType, date, prchasIdInt);
            
            if(ds4==true){
                String st3 = "Successfully inserted..!!";
                JOptionPane.showMessageDialog(null, st3);
                loadRecieptNum();
                loadLastRecieptNo();
                reciptnum.setText("");
                paymentDate1.setDate(null);
                //jTextFieldInvoiceNumber.setText("");
                totamount.setText("");
                
            }
            else{
                String st3 = "Reciept number is already exist.\nPlease enter another reciept number.";
                JOptionPane.showMessageDialog(null, st3);

            }

        }
        else{
            String st3 = "Please fill all the fields !!";
            JOptionPane.showMessageDialog(null, st3);
        }
    }//GEN-LAST:event_jButton60ActionPerformed

    private void jButton67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton67ActionPerformed
        // TODO add your handling code here:
       
       MyReportViewerPayments viewer = new MyReportViewerPayments("./src/OrganizedIn/IReports/viewAllPayments.jasper");
       //MyReportViewerPayments viewer = new MyReportViewerPayments("C:\\Users\\Mayuri\\Downloads\\OrganizedIn (1)\\OrganizedIn\\OrganizedIn\\src\\OrganizedIn\\IReports\\viewAllPayments.jasper");
       viewer.setVisible(true);
        
    }//GEN-LAST:event_jButton67ActionPerformed

    private void jButton65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton65ActionPerformed
        // TODO add your handling code here:
       
        //sen email---------------------------------------------------
        String senderEmail =senderemail.getText();
        String passWord = password.getText();
        String receiverEmail =receiveremail.getText();
        boolean resultEmail = false;
        
        
       //adding the grn-----------------------------------------------------------------------------------
       String receiptNo = receiptnum1.getSelectedItem().toString();
       String paymentId = null;
       
       HelperPayments hp1 = new HelperPayments();
       paymentId = hp1.getRelevanatPaymentId(receiptNo);
       
       int paymentIdInt = 0;
       try{
           paymentIdInt = Integer.parseInt(paymentId);
       }
       catch(Exception e){
           System.out.println("failed");
       }
        
       
       
        
        String ordernum = orderNum.getText();
        
        int orderNumInt = 0;
        try{
         orderNumInt = Integer.parseInt(ordernum);
        }
        catch (Exception e){
            System.out.println("failed");
        }
        
        String supplier = jComboBoxsupplier2.getSelectedItem().toString();
        String GRNdes = GRNDes.getText();
        //String REciver = receiveremail.getText();
        //String sender = senderemail.getText();
       
        
        String receiptnum = receiptnum1.getSelectedItem().toString();
        
        
        
        
        if(!receiverEmail.equals(null) && !senderEmail.equals(null) && !passWord.equals(null) && !ordernum.equals(null) && !supplier.equals(null) && !GRNdes.equals(null)&& /*!REciver.equals(null) && !sender.equals(null) &&*/ !receiptnum.equals(null)){
            
            String body =GRNdes;
            String title = "GRN For Reciept No: "+receiptnum;
            
            //send email
            EmailSending ems = new EmailSending();
            resultEmail=ems.mail(receiverEmail, senderEmail, passWord, body,title);

            
            boolean ds4 = hp.addGRNDetails(supplier,GRNdes,paymentIdInt,orderNumInt );
             
            if(ds4==true && resultEmail==true){
                String st3 = "Operation Successfull !!";
                JOptionPane.showMessageDialog(null, st3);
                receiveremail.setText(null);
                senderemail.setText(null);
                password.setText(null);
                //jTextFieldreceiptnum1.setText(null);
                GRNDes.setText(null);
                loadTable();
            }
            else{
                String st3 = "Operation failed..!!";
                JOptionPane.showMessageDialog(null, st3);

            }

        }
        else{
            String st3 = "Please fill all the fields !!";
            JOptionPane.showMessageDialog(null, st3);
        }
        
        

    }//GEN-LAST:event_jButton65ActionPerformed

    private void jButton63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton63ActionPerformed
        // TODO add your handling code here:
        updateGRNjPanel53.setVisible(false);
        GRNdetailsjPanel53.setVisible(true);
    }//GEN-LAST:event_jButton63ActionPerformed

    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed
        // TODO add your handling code here:
        //addjPanel53.setVisible(false);
        //confirmjPanel49.setVisible(true);

        String invoicenum = invoiceNum.getText();
        
        String purchsedate = null;
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            purchsedate = sm.format(purchaseDate.getDate());
        } catch (Exception e) {
            System.out.println("date failed");
        }
        
        
        
        String item = null;
        item = Item.getSelectedItem().toString();
        
        
        
        String quantity = Quantity.getText();
        int quantityInt = 0;
        try{
        quantityInt = Integer.parseInt(quantity);
        }
        catch(Exception e){
            System.out.println("qnt failed");
        }
        
        
        
        String supplier = jComboBox2sup.getSelectedItem().toString();

        if(!invoicenum.equals(null) && !purchsedate.equals(null) && !item.equals(null)&& !quantity.equals(null) && !supplier.equals(null)){
            boolean ds4 = hp.addPurchaseDetails(invoicenum,purchsedate,item,quantityInt,supplier);
            
            
            if(ds4 == true){
                String st3 = "Successfully inserted..!!";
                JOptionPane.showMessageDialog(null, st3);
                loadLastInvoiceNo();
                invoiceNum.setText("");
                purchaseDate.setDate(null);
                Quantity.setText("");
                loadPurchaseTable();
                
            }
            else{
                String st3 = "Operation failed";
                JOptionPane.showMessageDialog(null, st3);
            }
            
            

        }
        else{
            String st3 = "Please fill all the fields !!";
            JOptionPane.showMessageDialog(null, st3);
        }

    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton72ActionPerformed
        // TODO add your handling code here:
        updatejPanel53.setVisible(false);
        detailsjPanel49.setVisible(true);
    }//GEN-LAST:event_jButton72ActionPerformed

    private void jButton73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton73ActionPerformed
        // TODO add your handling code here:
        updatejPanel53.setVisible(false);
        detailsjPanel49.setVisible(true);
    }//GEN-LAST:event_jButton73ActionPerformed

    private void QuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QuantityKeyReleased
        // TODO add your handling code here:
        
        String value = Quantity.getText();
        if(!value.matches("\\d+")){
            JOptionPane.showMessageDialog(rootPane, "Please enter a valid quantity");
            Quantity.setText("");
            Quantity.requestFocus();
        }
        
    }//GEN-LAST:event_QuantityKeyReleased

    private void totamountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_totamountKeyReleased
        // TODO add your handling code here:
        
        String value = totamount.getText();
        if(!value.matches("^[-+]?\\d+(\\.(\\d{0,2}))?$")){
            JOptionPane.showMessageDialog(rootPane, "Please enter a valid amount");
            totamount.setText("");
            totamount.requestFocus();
        }
        
    }//GEN-LAST:event_totamountKeyReleased

    private void totamountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_totamountFocusLost
        // TODO add your handling code here:
        
        String value = totamount.getText();
        if(value.matches("^[-+]?\\d+(\\.(\\d{0}))?$")){
            totamount.setText(value + ".00");
        }
        
        
    }//GEN-LAST:event_totamountFocusLost

    private void jComboBoxsupplier2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxsupplier2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxsupplier2ActionPerformed

    private void jButton64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton64ActionPerformed
        // TODO add your handling code here:
        int grnNo=0;
        try{
            grnNo= Integer.parseInt(grntbl.getValueAt(grntbl.getSelectedRow(),0).toString());
        
        }catch(Exception e){
            System.out.println("grn no failed"+e);
        }
        
        if(grnNo==0){
             String st3 = "select the row you want to delete..";
             JOptionPane.showMessageDialog(null, st3);
           
        }
        else{
            HelperPayments hp = new HelperPayments();
            boolean status = false;
            status = hp.deleteGRNDetails(grnNo);
            
            if(status==true){
                String st3 = "sucessfully deleted..";
                JOptionPane.showMessageDialog(null, st3);
                loadTable();
                
            }
            else{
                String st3 = "Operation failed";
                JOptionPane.showMessageDialog(null, st3);
            }
        }
        
        
        
        
    }//GEN-LAST:event_jButton64ActionPerformed

    private void jComboBox2invoiceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2invoiceKeyReleased
        // TODO add your handling code here:
        
      //  String value = jComboBox2invoice.get
        
    }//GEN-LAST:event_jComboBox2invoiceKeyReleased

    private void jTextField1prchsTypingKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1prchsTypingKeyReleased
        // TODO add your handling code here:
        
        String value = jTextField1prchsTyping.getText();
        
        HelperPayments hp = new HelperPayments();
        try{
            ResultSet rst = hp.getPrchaseRecordsByTyping(value);
            
            DefaultTableModel dtm = (DefaultTableModel)jTablepurchaseDetails.getModel();
            
            dtm.setRowCount(0);
            dtm.setColumnCount(0);
            
            dtm.addColumn("Invoice No");
            dtm.addColumn("Purchase Date");
            dtm.addColumn("Item");
            dtm.addColumn("Quantity");
            dtm.addColumn("Supplier");
            
            
            while(rst.next()){
                Vector v = new Vector();
                v.add(rst.getString(1));
                v.add(rst.getString(2));
                v.add(rst.getString(3));
                v.add(rst.getString(4));
                v.add(rst.getString(5));
                dtm.addRow(v);
            }
        }catch(Exception e){
            System.out.println("load table failed typing---"+e);
        }
        
        
    }//GEN-LAST:event_jTextField1prchsTypingKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tempMayuri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tempMayuri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tempMayuri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tempMayuri.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tempMayuri().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea GRNDes;
    private javax.swing.JPanel GRNdetailsjPanel53;
    private javax.swing.JComboBox Item;
    private javax.swing.JTextField Quantity;
    private javax.swing.JPanel addjPanel53;
    private javax.swing.JPanel detailsjPanel49;
    private javax.swing.JTable grntbl;
    private javax.swing.JTextField invoiceNum;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2invoice;
    private javax.swing.JComboBox jComboBox2sup;
    private javax.swing.JComboBox jComboBoxsupplier2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelLastInvoiceNo;
    private javax.swing.JLabel jLabelLastReceiptNo1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JTabbedPane jTabbedPane16;
    private javax.swing.JTabbedPane jTabbedPane17;
    private javax.swing.JTabbedPane jTabbedPane18;
    public javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTable jTablepurchaseDetails;
    private javax.swing.JTextField jTextField104;
    private javax.swing.JTextField jTextField105;
    private javax.swing.JTextField jTextField106;
    private javax.swing.JTextField jTextField107;
    private javax.swing.JTextField jTextField108;
    private javax.swing.JTextField jTextField110;
    private javax.swing.JTextField jTextField1prchsTyping;
    private javax.swing.JTextField jTextField97;
    private javax.swing.JTextField jTextField98;
    private javax.swing.JTextField jTextField99;
    private javax.swing.JPanel maincardGRNjPanel53;
    private javax.swing.JPanel maincardconfirmationjPanel49;
    private javax.swing.JPanel maincardpurchsesjPanel57;
    private javax.swing.JTextField orderNum;
    private javax.swing.JPasswordField password;
    private com.toedter.calendar.JDateChooser paymentDate1;
    private com.toedter.calendar.JDateChooser purchaseDate;
    private javax.swing.JComboBox receiptnum1;
    private javax.swing.JTextField receiveremail;
    private javax.swing.JTextField reciptnum;
    private javax.swing.JTextField senderemail;
    private javax.swing.JTextField totamount;
    private javax.swing.JPanel updateGRNjPanel53;
    private javax.swing.JPanel updatejPanel53;
    // End of variables declaration//GEN-END:variables
}
