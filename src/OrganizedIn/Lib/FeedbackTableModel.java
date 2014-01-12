/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gamunu
 */
public class FeedbackTableModel extends AbstractTableModel {

    List messages;

    public FeedbackTableModel(List messages) {
        this.messages = messages;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Feedback.class;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "Feedback";
    }

    @Override
    public int getRowCount() {
        return (messages == null) ? 0 : messages.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (messages == null) ? null : messages.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int columnIndex, int rowIndex) {
        return true;
    }
}