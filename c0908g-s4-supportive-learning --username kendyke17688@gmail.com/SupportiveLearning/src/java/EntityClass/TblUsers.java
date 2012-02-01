/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityClass;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hoang Tuan
 */
@Entity
@Table(name = "tbl_Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUsers.findAll", query = "SELECT t FROM TblUsers t"),
    @NamedQuery(name = "TblUsers.findByUsername", query = "SELECT t FROM TblUsers t WHERE t.username = :username"),
    @NamedQuery(name = "TblUsers.findByPassword", query = "SELECT t FROM TblUsers t WHERE t.password = :password"),
    @NamedQuery(name = "TblUsers.findByGroupname", query = "SELECT t FROM TblUsers t WHERE t.groupname = :groupname"),
    @NamedQuery(name = "TblUsers.findByFullname", query = "SELECT t FROM TblUsers t WHERE t.fullname = :fullname"),
    @NamedQuery(name = "TblUsers.findByGender", query = "SELECT t FROM TblUsers t WHERE t.gender = :gender"),
    @NamedQuery(name = "TblUsers.findByAddress", query = "SELECT t FROM TblUsers t WHERE t.address = :address"),
    @NamedQuery(name = "TblUsers.findByBirthday", query = "SELECT t FROM TblUsers t WHERE t.birthday = :birthday"),
    @NamedQuery(name = "TblUsers.findByPhone", query = "SELECT t FROM TblUsers t WHERE t.phone = :phone"),
    @NamedQuery(name = "TblUsers.findByEmail", query = "SELECT t FROM TblUsers t WHERE t.email = :email"),
    @NamedQuery(name = "TblUsers.findByStartdate", query = "SELECT t FROM TblUsers t WHERE t.startdate = :startdate"),
    @NamedQuery(name = "TblUsers.findByEnddate", query = "SELECT t FROM TblUsers t WHERE t.enddate = :enddate"),
    @NamedQuery(name = "TblUsers.findByDescription", query = "SELECT t FROM TblUsers t WHERE t.description = :description"),
    @NamedQuery(name = "TblUsers.findByStatus", query = "SELECT t FROM TblUsers t WHERE t.status = :status")})
public class TblUsers implements Serializable {
    @Lob
    @Column(name = "Avatar")
    private byte[] avatar;
    @Column(name = "Birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @Column(name = "Startdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startdate;
    @Column(name = "Enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @JoinColumn(name = "Classname", referencedColumnName = "Classname")
    @ManyToOne
    private TblClasses classname;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Groupname")
    private String groupname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Fullname")
    private String fullname;
    @Size(max = 50)
    @Column(name = "Gender")
    private String gender;
    @Size(max = 1073741823)
    @Column(name = "Address")
    private String address;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 250)
    @Column(name = "Email")
    private String email;
    @Size(max = 1073741823)
    @Column(name = "Description")
    private String description;
    @Size(max = 50)
    @Column(name = "Status")
    private String status;

    public TblUsers() {
    }

    public TblUsers(String username) {
        this.username = username;
    }

    public TblUsers(String username, String password, String groupname, String fullname) {
        this.username = username;
        this.password = password;
        this.groupname = groupname;
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUsers)) {
            return false;
        }
        TblUsers other = (TblUsers) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EntityClass.TblUsers[ username=" + username + " ]";
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public TblClasses getClassname() {
        return classname;
    }

    public void setClassname(TblClasses classname) {
        this.classname = classname;
    }
    
}
