package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Sestava;
import org.fit.pis.service.SestavaManager;

@Named
@SessionScoped
public class SestavaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private SestavaManager sestavaMgr;
    private Sestava sestava;
    
    public SestavaBean()
    {
    	sestava = new Sestava();
    }
    
    public Sestava getTym()
    {
        return sestava;
    }

    public void setTym(Sestava sestava)
    {
        this.sestava = sestava;
    }
    
    public List<Sestava> getTymAll()
    {
		return sestavaMgr.findAll();
    }
      
	public String actionNew()
	{
		sestava = new Sestava();
		return "new";
	}
	
	public String actionInsertNew()
    {
		sestavaMgr.save(sestava);
        return "insert";
    }
    
    public String actionUpdate()
    {
    	sestavaMgr.save(sestava);
        return "update";
    }
    
    public String actionEdit(Sestava sestava)
    {
    	setTym(sestava);
    	return "edit";
    }
    
    public String actionDelete(Sestava sestava)
    {
    	sestavaMgr.remove(sestava);
    	return "delete";
    }
    
}
