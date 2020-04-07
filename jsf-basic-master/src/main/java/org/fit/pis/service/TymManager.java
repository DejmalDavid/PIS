package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Tym;


/**
 * @author dejmal
 */
@Stateless
public class TymManager 
{
    @PersistenceContext
    private EntityManager em;

    public TymManager() 
    {
    }
    
    public void save(Tym p)
    {
    	em.merge(p);
    }
	
    public void remove(Tym p)
    {
    	em.remove(em.merge(p));
    }
    
    public Tym find(int id)
    {
    	return em.find(Tym.class, id);
    }
    
    public List<Tym> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM Tym r", Tym.class).getResultList();
    }

}
