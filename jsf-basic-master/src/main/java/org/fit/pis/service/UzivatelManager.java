package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Uzivatel;


/**
 * @author dejmal
 */
@Stateless
public class UzivatelManager 
{
    @PersistenceContext
    private EntityManager em;

    public UzivatelManager() 
    {
    }
    
    public void save(Uzivatel p)
    {
    	em.merge(p);
    }
	
    public void remove(Uzivatel p)
    {
    	em.remove(em.merge(p));
    }
    
    public Uzivatel find(int id)
    {
    	return em.find(Uzivatel.class, id);
    }
    
    public List<Uzivatel> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM Uzivatel r", Uzivatel.class).getResultList();
    }

}
