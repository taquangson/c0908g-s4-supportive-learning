/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import JSFBeans.UserBean;
import StatelessBean.UserService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpSession;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Hoang Tuan
 */
@ManagedBean
@RequestScoped
public class JSFController {

    boolean flag, flagAdmin, flagStaff, flagLogin;

    public boolean isFlagLogin() {
        return flagLogin;
    }

    public void setFlagLogin(boolean flagLogin) {
        this.flagLogin = flagLogin;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlagAdmin() {
        return flagAdmin;
    }

    public void setFlagAdmin(boolean flagAdmin) {
        this.flagAdmin = flagAdmin;
    }

    public boolean isFlagStaff() {
        return flagStaff;
    }

    public void setFlagStaff(boolean flagStaff) {
        this.flagStaff = flagStaff;
    }
    @EJB
    UserService us;

    /** Creates a new instance of JSFController */
    public JSFController() {
        try {
            //if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName().equals("")) {
            if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Admin")) {
                flagAdmin = true;
                flag = false;
                flagStaff = false;
                flagLogin = false;
            } else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Staff")) {
                flagStaff = true;
                flag = false;
                flagAdmin = false;
                flagLogin = false;
            } else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("Student")) {
                flag = true;
                flagStaff = false;
                flagAdmin = false;
                flagLogin = false;
            } else {
                flagLogin = true;
                flagStaff = false;
                flag = false;
                flagAdmin = false;
            }
        } catch (Exception ex) {
            flagLogin = true;
            flagStaff = false;
            flag = false;
            flagAdmin = false;
        }
    }
    String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void logOut() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
            session.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void logIn() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/LoginPage.xhtml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //File Upload
    private String filePath = "";
    private static final int BUFFER_SIZE = 6124;

    public void handleFileUpload(FileUploadEvent event) {
        String uploadedFolder = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("FileUploaded");
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

            filePath = uploadedFolder + "/" + event.getFile().getFileName();

            FacesMessage msg = new FacesMessage("Succesful",
                    event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (IOException e) {
            e.printStackTrace();

            FacesMessage error = new FacesMessage("The files were not uploaded!");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
