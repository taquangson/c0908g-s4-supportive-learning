/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClass;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hoang Tuan
 */
@Entity
@Table(name = "tblTest")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTest.findAll", query = "SELECT t FROM TblTest t"),
    @NamedQuery(name = "TblTest.findByTestid", query = "SELECT t FROM TblTest t WHERE t.testid = :testid")})
public class TblTest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "testid")
    private String testid;
    @Lob
    @Column(name = "Fileupload")
    private byte[] fileupload;

    public TblTest() {
    }

    public TblTest(String testid) {
        this.testid = testid;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public byte[] getFileupload() {
        return fileupload;
    }

    public void setFileupload(byte[] fileupload) {
        this.fileupload = fileupload;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (testid != null ? testid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblTest)) {
            return false;
        }
        TblTest other = (TblTest) object;
        if ((this.testid == null && other.testid != null) || (this.testid != null && !this.testid.equals(other.testid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.TblTest[ testid=" + testid + " ]";
    }
    
}
