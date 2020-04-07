package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Uzivatel;
import org.fit.pis.service.UzivatelManager;

@Named
@SessionScoped
public class UzivatelBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UzivatelManager uzivatelMgr;
    private Uzivatel uzivatel;
    
    public UzivatelBean()
    {
    	uzivatel = new Uzivatel();
    }
    
    public Uzivatel getUzivatel()
    {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel)
    {
        this.uzivatel = uzivatel;
    }
    
    public List<Uzivatel> getUzivatelAll()
    {
		return uzivatelMgr.findAll();
    }
      
	public String actionNew()
	{
		uzivatel = new Uzivatel();
		return "new";
	}
	
	public String actionInsertNew()
    {
		uzivatelMgr.save(uzivatel);
        return "insert";
    }
    
    public String actionUpdate()
    {
    	uzivatelMgr.save(uzivatel);
        return "update";
    }
    
    public String actionEdit(Uzivatel uzivatel)
    {
    	setUzivatel(uzivatel);
    	return "edit";
    }
    
    public String actionDelete(Uzivatel uzivatel)
    {
    	uzivatelMgr.remove(uzivatel);
    	return "delete";
    }
    
}
