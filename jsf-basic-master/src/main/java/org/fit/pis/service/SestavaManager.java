package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.fit.pis.data.Sestava;
import org.fit.pis.data.Tym;


/**
 * @author kalabza
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
    	return em.createQuery("SELECT s FROM Sestava s", Sestava.class).getResultList();
    }
    /*
    public List<Sestava> findSestavaByZapas(int id)
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT s FROM Zapa z JOIN z.sestavas s WHERE z.id = :id", Sestava.class).setParameter("id",id).getResultList();
    }
	*/
}
