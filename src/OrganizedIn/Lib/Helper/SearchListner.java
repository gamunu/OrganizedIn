/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib.Helper;

import OrganizedIn.UI.HelpDeskPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author gamunu
 */
public class SearchListner implements DocumentListener {

    private HelpDeskPanel helpdesk;

    public SearchListner(HelpDeskPanel hdp) {
        super();
        this.helpdesk = hdp;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        try {
            HelperHelpdesk.loadTickets(helpdesk, e.getDocument().getText(0, e.getDocument().getLength()));
        } catch (BadLocationException ex) {
            Logger.getLogger(SearchListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        try {
            HelperHelpdesk.loadTickets(helpdesk, e.getDocument().getText(0, e.getDocument().getLength()));
        } catch (BadLocationException ex) {
            Logger.getLogger(SearchListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        try {
            HelperHelpdesk.loadTickets(helpdesk, e.getDocument().getText(0, e.getDocument().getLength()));
        } catch (BadLocationException ex) {
            Logger.getLogger(SearchListner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
