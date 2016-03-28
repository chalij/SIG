/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entidades.RegistroBloqueo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author chali
 */
@Stateless
public class RegistroBloqueoFacade extends AbstractFacade<RegistroBloqueo> {

    @PersistenceContext(unitName = "gerencialesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if(em==null)
        {
            EntityManagerFactory emf=Persistence.createEntityManagerFactory("gerencialesPU");
            em=emf.createEntityManager();
        }
        return em;
    }

    public RegistroBloqueoFacade() {
        super(RegistroBloqueo.class);
    }
    
}
