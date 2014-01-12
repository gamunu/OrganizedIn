/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

import OrganizedIn.Main;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;

/**
 *
 * @author Gamunu
 */
public class OrgSystem {
    
    public static User User;
    public static Main maildeamon;

    
    /**
     * Center the widow 
     * @param frame the JFrame object
     */
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    /**
     * Create and return font
     * @return Font object
     */
    public static Font getFont(){
    	return new Font("Segoe UI", Font.PLAIN, 14);
    }
}
