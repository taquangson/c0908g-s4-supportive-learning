/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClass;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hoang Tuan
 */
@Entity
@Table(name = "tbl_Classes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblClasses.findAll", query = "SELECT t FROM TblClasses t"),
    @NamedQuery(name = "TblClasses.findByClassname", query = "SELECT t FROM TblClasses t WHERE t.classname = :classname"),
    @NamedQuery(name = "TblClasses.findByTimestart", query = "SELECT t FROM TblClasses t WHERE t.timestart = :timestart"),
    @NamedQuery(name = "TblClasses.findByStaffname", query = "SELECT t FROM TblClasses t WHERE t.staffname = :staffname")})
public class TblClasses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Classname")
    private String classname;
    @Column(name = "Timestart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestart;
    @Size(max = 50)
    @Column(name = "Staffname")
    private String staffname;
    @JoinColumn(name = "CurriculumID", referencedColumnName = "CurriculumID")
    @ManyToOne(optional = false)
    private TblCurriculums curriculumID;
    @OneToMany(mappedBy = "classname")
    private List<TblUsers> tblUsersList;

    public TblClasses() {
    }

    public TblClasses(String classname) {
        this.classname = classname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Date getTimestart() {
        return timestart;
    }

    public void setTimestart(Date timestart) {
        this.timestart = timestart;
    }

    public String getStaffname() {
        return staffname;
    }

    public void setStaffname(String staffname) {
        this.staffname = staffname;
    }

    public TblCurriculums getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(TblCurriculums curriculumID) {
        this.curriculumID = curriculumID;
    }

    @XmlTransient
    public List<TblUsers> getTblUsersList() {
        return tblUsersList;
    }

    public void setTblUsersList(List<TblUsers> tblUsersList) {
        this.tblUsersList = tblUsersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classname != null ? classname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblClasses)) {
            return false;
        }
        TblClasses other = (TblClasses) object;
        if ((this.classname == null && other.classname != null) || (this.classname != null && !this.classname.equals(other.classname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.TblClasses[ classname=" + classname + " ]";
    }
    
}
