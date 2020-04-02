package org.fit.pis.data;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import static javax.persistence.FetchType.EAGER;

import javax.json.bind.annotation.JsonbTransient;

@Entity
@Table(name = "Car")
public class Car
{
    @Id
    private String reg;
	private String prod;
	private String type;
	
	@ManyToOne(fetch=EAGER)
	@JsonbTransient
    private Person owner;
	
	public Car()
	{
	}
	
    public String getProd()
    {
        return prod;
    }
    
    public boolean equals(Object other)
    {
        if (other instanceof Car)
            return ((Car) other).getReg().equals(reg);
        else
            return false;
    }
    
    public void setProd(String prod)
    {
        this.prod = prod;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getReg()
    {
        return reg;
    }
    
    public void setReg(String reg)
    {
        this.reg = reg;
    }

    public Person getOwner()
    {
        return owner;
    }

    public void setOwner(Person owner)
    {
        this.owner = owner;
    }
}
