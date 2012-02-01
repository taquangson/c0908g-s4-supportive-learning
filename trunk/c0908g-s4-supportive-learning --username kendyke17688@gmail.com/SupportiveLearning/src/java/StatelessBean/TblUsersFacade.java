/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatelessBean;

import EntityClass.TblUsers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hoang Tuan
 */
@Stateless
public class TblUsersFacade extends AbstractFacade<TblUsers> {
    @PersistenceContext(unitName = "SupportiveLearningPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TblUsersFacade() {
        super(TblUsers.class);
    }
    
}
