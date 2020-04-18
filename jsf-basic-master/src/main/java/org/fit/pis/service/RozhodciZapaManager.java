package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.RozhodciZapa;


/**
 * @author kalabza
 */
@Stateless
public class RozhodciZapaManager 
{
    @PersistenceContext
    private EntityManager em;

    public RozhodciZapaManager() 
    {
    }
    
    public void save(RozhodciZapa p)
    {
    	em.merge(p);
    }
	
    public void remove(RozhodciZapa p)
    {
    	em.remove(em.merge(p));
    }
    
    public RozhodciZapa find(int id)
    {
    	return em.find(RozhodciZapa.class, id);
    }
    
    public List<RozhodciZapa> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM RozhodciZapa r", RozhodciZapa.class).getResultList();
    }

}
