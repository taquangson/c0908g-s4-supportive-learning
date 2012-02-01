/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import EntityClass.TblUsers;
import StatelessBean.TblUsersFacade;
import StatelessBean.UserService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Hoang Tuan
 */
@ManagedBean
@RequestScoped
public class UserBean {

    String username;
    String password;
    String groupname, fullname, gender, address, phone, email, description, status;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserBean(String username, String password, String groupname, String fullname, String gender, String address, String phone, String email, String description, String status, byte[] avatar, Date birthday, Date startdate, Date enddate) {
        this.username = username;
        this.password = password;
        this.groupname = groupname;
        this.fullname = fullname;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.description = description;
        this.status = status;
        this.avatar = avatar;
        this.birthday = birthday;
        this.startdate = startdate;
        this.enddate = enddate;
    }
    byte[] avatar;
    Date birthday, startdate, enddate;
    TblUsers selectedUser;

    public TblUsers getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(TblUsers selectedUser) {
        this.selectedUser = selectedUser;
    }

    /** Creates a new instance of UserBean */
    public UserBean() {
    }
    @EJB
    UserService us;

    public void createNewUser(ActionEvent e) {
        //us.AddUser(username, password, groupname, fullname, gender, avatar, address, birthday, phone, email, startdate, enddate, description, status);
        FacesMessage mes = new FacesMessage("User : " + username + " added!");
        FacesContext.getCurrentInstance().addMessage(null, mes);
        username = "";
    }

    public List<TblUsers> getAllUsers() {
        return us.getAllUser();
    }
    @EJB
    TblUsersFacade tuf;

    public void addUser(ActionEvent e) {
        TblUsers user = new TblUsers();
        user.setUsername(username);
        user.setPassword("12345678");
        user.setGroupname(groupname);
        user.setFullname(fullname);
        user.setGender(gender);
        // user.setAvatar(avatar);
        user.setAddress(address);
        user.setBirthday(birthday);
        user.setPhone(phone);
        user.setEmail(email);
        user.setStartdate(startdate);
        user.setEnddate(enddate);
        user.setDescription(description);
        user.setStatus("false");

        //tuf.create(user);

        FacesMessage mes = new FacesMessage("User : " + username + " added!");
        FacesContext.getCurrentInstance().addMessage(null, mes);
        username = "";
        fullname = "";
        groupname = "";
        gender = "";
        address = "";
        birthday = null;
        phone = "";
        email = "";
        startdate = null;
        enddate = null;
        description = null;
        status = "";
        
    }

    public List<TblUsers> getAllAccount() {
        return tuf.findAll();
    }

    public int countAll() {
        return tuf.count();
    }
}
