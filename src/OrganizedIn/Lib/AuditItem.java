/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OrganizedIn.Audit;

/**
 *
 * @author Dell
 */
public class AuditItem {
    
    private String AuditID;
    private String Branch;
    private String SCode;
    private String Name;
    private String Brand;
    private String Model;
    private String Status;

    public String getAuditID() {
        return AuditID;
    }

    public void setAuditID(String AuditID) {
        this.AuditID = AuditID;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    public String getSCode() {
        return SCode;
    }

    public void setSCode(String SCode) {
        this.SCode = SCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String Brand) {
        this.Brand = Brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public AuditItem(String AuditID, String Branch, String SCode, String Name, String Brand, String Model, String Status) {
        this.AuditID = AuditID;
        this.Branch = Branch;
        this.SCode = SCode;
        this.Name = Name;
        this.Brand = Brand;
        this.Model = Model;
        this.Status = Status;
    }
    
    
    
    
}
