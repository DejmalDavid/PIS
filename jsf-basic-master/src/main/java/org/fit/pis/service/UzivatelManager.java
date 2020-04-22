package org.fit.pis.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
    

    @Transactional
    public void insert(Uzivatel p) {
    	em.createNativeQuery("INSERT INTO Uzivatel (email, heslo, jmeno, prijmeni, opravneni) VALUES (?,?,?,?,?)")
	       .setParameter(1, p.getEmail())
	       .setParameter(2, p.getHeslo())
	       .setParameter(3, p.getJmeno())
	       .setParameter(4, p.getPrijmeni())
	       .setParameter(5, p.getOpravneni())
	       .executeUpdate(); 
    }

    public Uzivatel findWithEmail(String email) {
         List<Uzivatel> results = em
                .createQuery("SELECT u FROM Uzivatel u WHERE u.email = :email", Uzivatel.class)
                .setParameter("email", email).getResultList();
          return results.isEmpty() ? null : results.get(0);
    }
    
    public boolean existWithEmail(String email) {
        List<Uzivatel> results = em
               .createQuery("SELECT u FROM Uzivatel u WHERE u.email = :email", Uzivatel.class)
               .setParameter("email", email).getResultList();
         return results.isEmpty() ? false : true;
   }

}
