package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Hrac;
import org.fit.pis.service.HracManager;

@Named
@SessionScoped
public class HracBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private HracManager hracMgr;
    private Hrac hrac;
    
    public HracBean()
    {
    	hrac = new Hrac();
    }
    
    public Hrac getHrac()
    {
        return hrac;
    }

    public void setHrac(Hrac hrac)
    {
        this.hrac = hrac;
    }
    
    public List<Hrac> getHracAll()
    {
		return hracMgr.findAll();
    }
      
	public String actionNew()
	{
		hrac = new Hrac();
		return "new";
	}
	
	public String actionInsertNew()
    {
		hracMgr.save(hrac);
        return "insert";
    }
    
    public String actionUpdate()
    {
    	hracMgr.save(hrac);
        return "update";
    }
    
    public String actionEdit(Hrac hrac)
    {
    	setHrac(hrac);
    	return "edit";
    }
    
    public String actionDelete(Hrac hrac)
    {
    	hracMgr.remove(hrac);
    	return "delete";
    }
    
}
