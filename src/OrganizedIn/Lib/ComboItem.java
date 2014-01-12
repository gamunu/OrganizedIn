/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

/**
 *
 * @author gamunu
 */
public class ComboItem {

    private String value;
    private String label;

    public ComboItem(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public ComboItem() {
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static String getValue(Object obj) {
        if (obj instanceof ComboItem) {
            ComboItem com = (ComboItem) obj;
            return com.getValue();
        } else if(obj instanceof String){
            return obj.toString();
        }
        return "";
    }
}
