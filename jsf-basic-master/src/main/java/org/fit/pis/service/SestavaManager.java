package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Sestava;


/**
 * @author dejmal
 */
@Stateless
public class SestavaManager 
{
    @PersistenceContext
    private EntityManager em;

    public SestavaManager() 
    {
    }
    
    public void save(Sestava p)
    {
    	em.merge(p);
    }
	
    public void remove(Sestava p)
    {
    	em.remove(em.merge(p));
    }
    
    public Sestava find(int id)
    {
    	return em.find(Sestava.class, id);
    }
    
    public List<Sestava> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM Sestava r", Sestava.class).getResultList();
    }
    


}
