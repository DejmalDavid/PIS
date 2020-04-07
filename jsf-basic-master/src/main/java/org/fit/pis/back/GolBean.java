package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Gol;
import org.fit.pis.service.GolManager;

@Named
@SessionScoped
public class GolBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private GolManager golMgr;
    private Gol gol;
    
    public GolBean()
    {
    	gol = new Gol();
    }
    
    public Gol getRozhodci()
    {
        return gol;
    }

    public void setRozhodci(Gol gol)
    {
        this.gol = gol;
    }
    
    public List<Gol> getRozhodciAll()
    {
		return golMgr.findAll();
    }
      
	public String actionNew()
	{
		gol = new Gol();
		return "new";
	}
	
	public String actionInsertNew()
    {
		golMgr.save(gol);
        return "insert";
    }
    
    public String actionUpdate()
    {
    	golMgr.save(gol);
        return "update";
    }
    
    public String actionEdit(Gol gol)
    {
    	setRozhodci(gol);
    	return "edit";
    }
    
    public String actionDelete(Gol gol)
    {
    	golMgr.remove(gol);
    	return "delete";
    }
    
}
