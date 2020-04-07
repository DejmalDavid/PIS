package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Gol;


/**
 * @author dejmal
 */
@Stateless
public class GolManager 
{
    @PersistenceContext
    private EntityManager em;

    public GolManager() 
    {
    }
    
    public void save(Gol p)
    {
    	em.merge(p);
    }
	
    public void remove(Gol p)
    {
    	em.remove(em.merge(p));
    }
    
    public Gol find(int id)
    {
    	return em.find(Gol.class, id);
    }
    
    public List<Gol> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT g FROM Gol g", Gol.class).getResultList();
    }

}
