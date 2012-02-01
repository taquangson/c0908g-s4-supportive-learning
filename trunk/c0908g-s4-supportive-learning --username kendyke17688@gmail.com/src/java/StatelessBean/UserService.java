/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatelessBean;

import EntityClass.TblUsers;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hoang Tuan
 */
@Stateless
@LocalBean
public class UserService {

    @PersistenceContext
    EntityManager em;
    
    public void AddUser(String Username, String Password, String Groupname, String Fullname, String Gender, byte[] Avatar, String Address, Date Birthday, String Phone, String Email, Date Startdate, Date Enddate, String Description, String Status) {
    TblUsers e1 = em.find(TblUsers.class, Username);
        if (e1 == null) {
            TblUsers emp = new TblUsers(Username, Password, Groupname, Fullname);
            emp.setAddress(Address);
            em.persist(emp);
            //return emp;
        }
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public java.util.List<TblUsers> getAllUser() {
        
        return em.createQuery("Select u from TblUsers u").getResultList();
    }
    
}
