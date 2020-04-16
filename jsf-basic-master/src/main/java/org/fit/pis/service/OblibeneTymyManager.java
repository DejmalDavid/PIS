package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.OblibeneTymy;


/**
 * @author kalabza
 */
@Stateless
public class OblibeneTymyManager 
{
    @PersistenceContext
    private EntityManager em;

    public OblibeneTymyManager() 
    {
    }
    
    public void save(OblibeneTymy p)
    {
    	em.merge(p);
    }
	
    public void remove(OblibeneTymy p)
    {
    	em.remove(em.merge(p));
    }
    
    public OblibeneTymy find(int id)
    {
    	return em.find(OblibeneTymy.class, id);
    }
    
    public List<OblibeneTymy> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT o FROM Oblibene_tymy o", OblibeneTymy.class).getResultList();
    }
    public List<OblibeneTymy> findAllByUzivatel(int id)
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT o FROM Oblibene_tymy o WHERE o.UZIVATEL_ID = :id", OblibeneTymy.class).setParameter("id",id).getResultList();
    }

}
