/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Help;

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
public class MessageCell extends JTextPane implements TableCellRenderer {

    private List<List<Integer>> rowColHeight = new ArrayList<>();

    public MessageCell() {
        this.setEditable(false);
    }

    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        Message message = (Message) value;
        this.setText("");
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            // Alternative: Leave a 2px border but draw it in the same color
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 1));//.createLineBorder(table.getSelectionBackground(), 3));
        } else {
            setBackground(table.getSelectionForeground());

            // Alternative: Leave a 2px border but draw it in the same color
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 1));
        }

        StyledDocument sc = getStyledDocument();
        //Define a keyword attribute

        SimpleAttributeSet keyWord = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord, Color.BLACK);
        StyleConstants.setAlignment(keyWord, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontFamily(keyWord, "Segoe UI");
        StyleConstants.setFontSize(keyWord, 14);
        StyleConstants.setBold(keyWord, false);

        SimpleAttributeSet keyWord2 = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord2, Color.BLUE);
        StyleConstants.setAlignment(keyWord2, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontFamily(keyWord2, "Segoe UI");
        StyleConstants.setFontSize(keyWord2, 14);
        StyleConstants.setBold(keyWord2, true);
        
        SimpleAttributeSet keyWord4 = new SimpleAttributeSet();
        StyleConstants.setForeground(keyWord4, Color.GRAY);
        StyleConstants.setAlignment(keyWord4, StyleConstants.ALIGN_LEFT);
        StyleConstants.setFontFamily(keyWord4, "Segoe UI");
        StyleConstants.setFontSize(keyWord4, 14);
        StyleConstants.setBold(keyWord4, false);

        try {
            
            sc.insertString(0, message.getUsername() + ": ", keyWord2);
            sc.insertString(sc.getLength(), "\t" + message.getMessge() + "", keyWord);
            sc.insertString(sc.getLength(), "\n(" + message.getCreatedTime() + ")", keyWord4);
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