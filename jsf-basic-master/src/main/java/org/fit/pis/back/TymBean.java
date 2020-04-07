package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Tym;
import org.fit.pis.service.TymManager;

@Named
@SessionScoped
public class TymBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private TymManager tymMgr;
    private Tym tym;
    
    public TymBean()
    {
    	tym = new Tym();
    }
    
    public Tym getTym()
    {
        return tym;
    }

    public void setTym(Tym tym)
    {
        this.tym = tym;
    }
    
    public List<Tym> getTymAll()
    {
		return tymMgr.findAll();
    }
      
	public String actionNew()
	{
		tym = new Tym();
		return "new";
	}
	
	public String actionInsertNew()
    {
		tymMgr.save(tym);
        return "insert";
    }
    
    public String actionUpdate()
    {
    	tymMgr.save(tym);
        return "update";
    }
    
    public String actionEdit(Tym tym)
    {
    	setTym(tym);
    	return "edit";
    }
    
    public String actionDelete(Tym tym)
    {
    	tymMgr.remove(tym);
    	return "delete";
    }
    
}
