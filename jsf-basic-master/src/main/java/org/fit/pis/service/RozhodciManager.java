package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.Rozhodci;


/**
 * @author dejmal
 */
@Stateless
public class RozhodciManager 
{
    @PersistenceContext
    private EntityManager em;

    public RozhodciManager() 
    {
    }
    
    public void save(Rozhodci p)
    {
    	em.merge(p);
    }
	
    public void remove(Rozhodci p)
    {

    	em.remove(em.merge(p));

    }
    
    public Rozhodci find(int id)
    {
    	return em.find(Rozhodci.class, id);
    }
    
    public List<Rozhodci> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT r FROM Rozhodci r", Rozhodci.class).getResultList();
    }

	public List<Rozhodci> findAllByZapas(int id) {
		return em.createQuery("SELECT r FROM Rozhodci r JOIN r.rozhodciZapas o WHERE o.zapa.id = :id", Rozhodci.class).setParameter("id", id).getResultList();
	}

}
