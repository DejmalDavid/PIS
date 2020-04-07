package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Zapa;
import org.fit.pis.service.ZapaManager;

@Named
@SessionScoped
public class ZapaBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ZapaManager zapaMgr;
    private Zapa zapa;
    
    public ZapaBean()
    {
    	zapa = new Zapa();
    }
    
    public Zapa getZapa()
    {
        return zapa;
    }

    public void setZapa(Zapa zapa)
    {
        this.zapa = zapa;
    }
    
    public List<Zapa> getZapaAll()
    {
		return zapaMgr.findAll();
    }
      
	public String actionNew()
	{
		zapa = new Zapa();
		return "new";
	}
	
	public String actionInsertNew()
    {
		zapaMgr.save(zapa);
        return "insert";
    }
    
    public String actionUpdate()
    {
    	zapaMgr.save(zapa);
        return "update";
    }
    
    public String actionEdit(Zapa zapa)
    {
    	setZapa(zapa);
    	return "edit";
    }
    
    public String actionDelete(Zapa zapa)
    {
    	zapaMgr.remove(zapa);
    	return "delete";
    }
    
}
