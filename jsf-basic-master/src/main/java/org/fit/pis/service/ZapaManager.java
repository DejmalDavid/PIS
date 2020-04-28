package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Zapa;


/**
 * @author dejmal
 */
@Stateless
public class ZapaManager 
{
    @PersistenceContext
    private EntityManager em;

    public ZapaManager() 
    {
    }
    
    public void save(Zapa p)
    {
    	em.merge(p);
    }
	
    public void remove(Zapa p)
    {
    	em.remove(em.merge(p));
    	em.getEntityManagerFactory().getCache().evictAll();
    }
    
    public Zapa find(int id)
    {
    	return em.find(Zapa.class, id);
    }
    
    public List<Zapa> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM Zapa r", Zapa.class).getResultList();
    }

}
