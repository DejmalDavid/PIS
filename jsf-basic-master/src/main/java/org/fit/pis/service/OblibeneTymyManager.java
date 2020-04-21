package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.fit.pis.data.OblibeneTymy;
import org.fit.pis.data.Tym;


/**
 * @author kalabza
 */
@Stateless
public class OblibeneTymyManager 
{
    @PersistenceContext
    private EntityManager em;

    public OblibeneTymyManager() 
    {
    }
    
    public void save(OblibeneTymy p)
    {
    	em.merge(p);
    }
	
    public void remove(OblibeneTymy p)
    {
    	em.remove(em.merge(p));
    }
    
    public OblibeneTymy find(int id)
    {
    	return em.find(OblibeneTymy.class, id);
    }
    
    public List<OblibeneTymy> findAll()
    {
    	System.out.println("Manager");
    	return em.createQuery("SELECT o FROM OblibeneTymy o", OblibeneTymy.class).getResultList();
    }

	public List<OblibeneTymy> findAllByTymUzivatel(int tid, int uid) {
		return em.createQuery("SELECT o FROM OblibeneTymy o WHERE o.tym.id = :tid AND o.uzivatel.id = :uid", OblibeneTymy.class).setParameter("tid", tid).setParameter("uid", uid).getResultList();
	}

}
