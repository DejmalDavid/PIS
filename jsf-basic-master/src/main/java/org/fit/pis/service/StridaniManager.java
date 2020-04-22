package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Stridani;


/**
 * @author kalabza
 */
@Stateless
public class StridaniManager 
{
    @PersistenceContext
    private EntityManager em;

    public StridaniManager() 
    {
    }
    
    public void save(Stridani p)
    {
    	em.merge(p);
    }
	
    public void remove(Stridani p)
    {
    	 
    	em.remove(em.merge(p));
    	 
    }
    
    public Stridani find(int id)
    {
    	return em.find(Stridani.class, id);
    }
    
    public List<Stridani> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM Stridani r", Stridani.class).getResultList();
    }

}
