package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Hrac;


/**
 * @author dejmal
 */
@Stateless
public class HracManager 
{
    @PersistenceContext
    private EntityManager em;

    public HracManager() 
    {
    }
    
    public void save(Hrac p)
    {
    	em.merge(p);
    }
	
    public void remove(Hrac p)
    {

    	em.remove(em.merge(p));
    	em.getEntityManagerFactory().getCache().evictAll();

    }
    
    public Hrac find(int id)
    {
    	return em.find(Hrac.class, id);
    }
    
    public List<Hrac> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM Hrac r", Hrac.class).getResultList();
    }

}
