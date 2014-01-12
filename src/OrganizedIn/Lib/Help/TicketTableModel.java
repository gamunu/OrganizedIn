/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Help;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gamunu
 */
public class TicketTableModel extends AbstractTableModel {

    List tickets;

    public TicketTableModel(List tickets) {
        this.tickets = tickets;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return Ticket.class;
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return "Ticket";
    }

    @Override
    public int getRowCount() {
        return (tickets == null) ? 0 : tickets.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (tickets == null) ? null : tickets.get(rowIndex);
    }

    @Override
    public boolean isCellEditable(int columnIndex, int rowIndex) {
        return true;
    }
}