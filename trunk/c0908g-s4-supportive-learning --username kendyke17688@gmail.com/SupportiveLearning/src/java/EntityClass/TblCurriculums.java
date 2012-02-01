/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClass;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hoang Tuan
 */
@Entity
@Table(name = "tbl_Curriculums")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblCurriculums.findAll", query = "SELECT t FROM TblCurriculums t"),
    @NamedQuery(name = "TblCurriculums.findByCurriculumID", query = "SELECT t FROM TblCurriculums t WHERE t.curriculumID = :curriculumID"),
    @NamedQuery(name = "TblCurriculums.findByName", query = "SELECT t FROM TblCurriculums t WHERE t.name = :name"),
    @NamedQuery(name = "TblCurriculums.findByYearstart", query = "SELECT t FROM TblCurriculums t WHERE t.yearstart = :yearstart")})
public class TblCurriculums implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CurriculumID")
    private Integer curriculumID;
    @Size(max = 250)
    @Column(name = "Name")
    private String name;
    @Column(name = "Yearstart")
    private Integer yearstart;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curriculumID")
    private List<TblClasses> tblClassesList;

    public TblCurriculums() {
    }

    public TblCurriculums(Integer curriculumID) {
        this.curriculumID = curriculumID;
    }

    public Integer getCurriculumID() {
        return curriculumID;
    }

    public void setCurriculumID(Integer curriculumID) {
        this.curriculumID = curriculumID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYearstart() {
        return yearstart;
    }

    public void setYearstart(Integer yearstart) {
        this.yearstart = yearstart;
    }

    @XmlTransient
    public List<TblClasses> getTblClassesList() {
        return tblClassesList;
    }

    public void setTblClassesList(List<TblClasses> tblClassesList) {
        this.tblClassesList = tblClassesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (curriculumID != null ? curriculumID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblCurriculums)) {
            return false;
        }
        TblCurriculums other = (TblCurriculums) object;
        if ((this.curriculumID == null && other.curriculumID != null) || (this.curriculumID != null && !this.curriculumID.equals(other.curriculumID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.TblCurriculums[ curriculumID=" + curriculumID + " ]";
    }
    
}
