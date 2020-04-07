package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Rozhodci;
import org.fit.pis.service.RozhodciManager;

@Named
@SessionScoped
public class RozhodciBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RozhodciManager rozhodciMgr;
    private Rozhodci rozhodci;
    
    public RozhodciBean()
    {
    	rozhodci = new Rozhodci();
    }
    
    public Rozhodci getRozhodci()
    {
        return rozhodci;
    }

    public void setRozhodci(Rozhodci rozhodci)
    {
        this.rozhodci = rozhodci;
    }
    
    public List<Rozhodci> getRozhodciAll()
    {
		return rozhodciMgr.findAll();
    }
      
	public String actionNew()
	{
		rozhodci = new Rozhodci();
		return "new";
	}
	
	public String actionInsertNew()
    {
		rozhodciMgr.save(rozhodci);
        return "insert";
    }
    
    public String actionUpdate()
    {
    	rozhodciMgr.save(rozhodci);
        return "update";
    }
    
    public String actionEdit(Rozhodci rozhodci)
    {
    	setRozhodci(rozhodci);
    	return "edit";
    }
    
    public String actionDelete(Rozhodci rozhodci)
    {
    	rozhodciMgr.remove(rozhodci);
    	return "delete";
    }
    
}
