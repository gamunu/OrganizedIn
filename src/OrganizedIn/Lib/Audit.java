/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Lib;

/**
 *
 * @author Dell
 */
public class Audit {
    
    private String audNo;
    private String stDate;
    private String enDate;
    private String AudNames;
    private String Notes;

    public String getAudNo() {
        return audNo;
    }

    public void setAudNo(String audNo) {
        this.audNo = audNo;
    }

    public String getStDate() {
        return stDate;
    }

    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    public String getEnDate() {
        return enDate;
    }

    public void setEnDate(String enDate) {
        this.enDate = enDate;
    }

    public String getAudNames() {
        return AudNames;
    }

    public void setAudNames(String AudNames) {
        this.AudNames = AudNames;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }    

    public Audit(String audNo, String stDate, String enDate, String AudNames, String Notes) {
        this.audNo = audNo;
        this.stDate = stDate;
        this.enDate = enDate;
        this.AudNames = AudNames;
        this.Notes = Notes;
    }
    
}
