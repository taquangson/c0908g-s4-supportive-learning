/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StatelessBean;

import EntityClass.TblTest;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hoang Tuan
 */
@Stateless
public class TblTestFacade extends AbstractFacade<TblTest> {
    @PersistenceContext(unitName = "SupportiveLearningPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TblTestFacade() {
        super(TblTest.class);
    }
    
}
