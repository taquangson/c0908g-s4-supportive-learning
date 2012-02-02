/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFBeans;

import EntityClass.TblUsers;
import StatelessBean.TblUsersFacade;
import StatelessBean.UserService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FileUploadEvent;

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
    private String filePath;
    private static final int BUFFER_SIZE = 6124;
    byte[] avatar;
    Date birthday, startdate, enddate;
    TblUsers selectedUser;

    /** Creates a new instance of UserBean */
    public UserBean() {
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

    public void handleAvatarUpload(FileUploadEvent event) {
        String uploadedFolder = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("avatarFolder");
        ExternalContext extContext = FacesContext.getCurrentInstance().
                getExternalContext();
        File result = new File(extContext.getRealPath("//" + uploadedFolder) + "//" + event.getFile().getFileName());
        System.out.println(result.getAbsolutePath());

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(result);
            byte[] buffer = new byte[BUFFER_SIZE];
            int bulk;
            InputStream inputStream = event.getFile().getInputstream();
            while (true) {
                bulk = inputStream.read(buffer);
                if (bulk < 0) {
                    break;
                }
                fileOutputStream.write(buffer, 0, bulk);
                fileOutputStream.flush();
            }

            fileOutputStream.close();
            inputStream.close();


            filePath = "../" + uploadedFolder + "/" + event.getFile().getFileName();

            FacesMessage msg = new FacesMessage("Succesful",
                    event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }
    //Tiem EJB.
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
    //Cach lam gen code theo netbean.
    @EJB
    TblUsersFacade tuf;

    public void addUser(ActionEvent e) {
        try {
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

            tuf.create(user);

            FacesMessage mes = new FacesMessage("Information", "User : " + username + " added!");
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
        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage mes = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Add user : " + username + " is not successful!");
            FacesContext.getCurrentInstance().addMessage(null, mes);
        }
    }

    public List<TblUsers> getAllAccount() {
        return tuf.findAll();
    }

    public int countAll() {
        return tuf.count();
    }

    public void updateMyAccount(ActionEvent e) {
        try {

            myAcc.setFullname(fullname);
            myAcc.setGender(gender);
            myAcc.setAddress(address);
            myAcc.setBirthday(birthday);
            myAcc.setPhone(phone);
            myAcc.setEmail(email);
            myAcc.setStartdate(startdate);
            myAcc.setEnddate(enddate);
            myAcc.setDescription(description);

            tuf.edit(myAcc);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Information", "Update Your Account is successful.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            // redirectAdmin();

        } catch (Exception ex) {
            ex.printStackTrace();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update Error", "Update Your Account is not successful.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }


    }
//    public void redirectAdmin() throws IOException, InterruptedException{
//        Thread.sleep(3000);
//        FacesContext.getCurrentInstance().getExternalContext().redirect(""
//                + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/index.xhtml");
//    }
    TblUsers myAcc;

    public TblUsers showMyAccount() {
        try {
            String myName = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
            TblUsers ufinding = tuf.find(myName);
            myAcc = ufinding;
            return ufinding;
        } catch (Exception e) {
            e.printStackTrace();
            return new TblUsers("Error on loading your account! ");
        }

    }
    //****************Change Password*******************//
    String oldPass;
    String newPass1;
    String newPass2;

    public void changePassword(ActionEvent e) {
        try {
            showMyAccount();
            if (newPass1.equals(newPass2)) {
                if (oldPass.equals(myAcc.getPassword())) {
                    myAcc.setPassword(newPass1);
                    tuf.edit(myAcc);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Change Password", "Your Password is updated!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                    oldPass = "";
                    newPass1 = "";
                    newPass2 = "";
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Password is wrong!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                }
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "New Password do not match!");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void cancelAdmin(ActionEvent e){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/Admin/HomePage.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //********************Getter and Setter*******************************************//
    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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

    public TblUsers getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(TblUsers selectedUser) {
        this.selectedUser = selectedUser;
    }

    public TblUsers getMyAcc() {
        return myAcc;
    }

    public void setMyAcc(TblUsers myAcc) {
        this.myAcc = myAcc;
    }

    public String getNewPass1() {
        return newPass1;
    }

    public void setNewPass1(String newPass1) {
        this.newPass1 = newPass1;
    }

    public String getNewPass2() {
        return newPass2;
    }

    public void setNewPass2(String newPass2) {
        this.newPass2 = newPass2;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }
}
