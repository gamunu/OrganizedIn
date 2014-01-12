/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Help;

import OrganizedIn.Lib.OrgSystem;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author gamunu
 */
public class TicketCell extends JTextPane implements TableCellRenderer {

    private List<List<Integer>> rowColHeight = new ArrayList<>();

    public TicketCell() {
        this.setEditable(false);
    }

    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Ticket ticket = (Ticket) value;
        this.setText("");
        Color clr;
        this.setText("");
        if (isSelected) {
            clr = table.getSelectionBackground();

            // Alternative: Leave a 2px border but draw it in the same color
            setBorder(BorderFactory.createLineBorder(table.getSelectionBackground(), 1));
        } else if (ticket.getHighlight().equals("Y") && OrgSystem.User.getUserId() == ticket.getUserid()) {
            clr = Color.YELLOW;
            setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
        } else if (ticket.getOthighlight().equals("Y") && OrgSystem.User.getUserId() == ticket.getLastMessage().getUserId()) {
            clr = Color.YELLOW;
            setBorder(BorderFactory.createLineBorder(Color.YELLOW, 1));
        } else {
            clr = table.getSelectionForeground();

            // Alternative: Leave a 2px border but draw it in the same color
            setBorder(BorderFactory.createLineBorder(table.getSelectionForeground(), 1));
        }
        setBackground(clr);

        StyledDocument sc = getStyledDocument();
        //  Define a keyword attribute

        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord, Color.BLACK);
        StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontFamily(keyWord, "Segoe UI");
        StyleConstants.setFontSize(keyWord, 14);
        StyleConstants.setBold(keyWord, true);

        SimpleAttributeSet keyWord2 = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord2, Color.RED);
        StyleConstants.setAlignment(keyWord2, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontFamily(keyWord2, "Segoe UI");
        StyleConstants.setFontSize(keyWord2, 14);
        StyleConstants.setBold(keyWord2, true);

        SimpleAttributeSet keyWord3 = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord3, Color.BLACK);
        StyleConstants.setAlignment(keyWord3, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontFamily(keyWord3, "Segoe UI");
        StyleConstants.setFontSize(keyWord3, 14);
        StyleConstants.setBold(keyWord3, false);

        SimpleAttributeSet keyWord4 = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord4, Color.GRAY);
        StyleConstants.setAlignment(keyWord4, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontFamily(keyWord4, "Segoe UI");
        StyleConstants.setFontSize(keyWord4, 12);
        StyleConstants.setBold(keyWord4, false);

        try {
            sc.insertString(0, ticket.getSubject(), keyWord);
            sc.insertString(sc.getLength(), "\t(" + ticket.getPriority().toString() + ")", keyWord2);
            sc.insertString(sc.getLength(), "\n" + ticket.getMessage(), keyWord3);
            sc.insertString(sc.getLength(), "\n(" + ticket.getCreatedTime() + ")", keyWord4);
        } catch (Exception e) {
            System.out.println(e);
        }

        adjustRowHeight(table, row, column);
        return this;
    }

    /**
     * Calculate the new preferred height for a given row, and sets the height
     * on the table.
     */
    private void adjustRowHeight(JTable table, int row, int column) {
        //The trick to get this to work properly is to set the width of the column to the 
        //textarea. The reason for this is that getPreferredSize(), without a width tries 
        //to place all the text in one line. By setting the size with the with of the column, 
        //getPreferredSize() returnes the proper height which the row should have in
        //order to make room for the text.
        int cWidth = table.getColumnModel().getColumn(column).getWidth();
        setSize(new Dimension(cWidth, 1000));
        int prefH = getPreferredSize().height;
        while (rowColHeight.size() <= row) {
            rowColHeight.add(new ArrayList<Integer>(column));
        }
        List<Integer> colHeights = rowColHeight.get(row);
        while (colHeights.size() <= column) {
            colHeights.add(0);
        }
        colHeights.set(column, prefH);
        int maxH = prefH;
        for (int colHeight : colHeights) {
            if (colHeight > maxH) {
                maxH = colHeight;
            }
        }
        if (table.getRowHeight(row) != maxH) {
            table.setRowHeight(row, maxH);
        }
    }
}