/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entidades.Persona;
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
public class PersonaFacade extends AbstractFacade<Persona> {

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

    public PersonaFacade() {
        super(Persona.class);
    }
    
}
