package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.SestavaHrac;


/**
 * @author kalabza
 */
@Stateless
public class SestavaHracManager 
{
    @PersistenceContext
    private EntityManager em;

    public SestavaHracManager() 
    {
    }
    
    public void save(SestavaHrac p)
    {
    	em.merge(p);
    }
	
    public void remove(SestavaHrac p)
    {

    	em.remove(em.merge(p));

    }
    
    public SestavaHrac find(int id)
    {
    	return em.find(SestavaHrac.class, id);
    }
    
    public List<SestavaHrac> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM SestavaHrac r", SestavaHrac.class).getResultList();
    }

}
