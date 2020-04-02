package org.fit.pis.back;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.fit.pis.data.Car;
import org.fit.pis.data.Person;
import org.fit.pis.service.PersonManager;

@Named
@SessionScoped
public class PersonBean implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private PersonManager personMgr;
    private Person person;
    private Car car;
    
    public PersonBean()
    {
        person = new Person();
        car = new Car();
    }
    
    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }
    
    public List<Person> getPeople()
    {
		return personMgr.findAll();
    }
    
    public Car getCar()
    {
        return car;
    }

    public void setCar(Car car)
    {
        this.car = car;
    }

   //====================================================
    
	public String actionNew()
	{
		person = new Person();
		return "new";
	}
	
	public String actionInsertNew()
    {
        personMgr.save(person);
        return "insert";
    }
    
    public String actionUpdate()
    {
        personMgr.save(person);
        return "update";
    }
    
    public String actionEdit(Person person)
    {
    	setPerson(person);
    	return "edit";
    }
    
    public String actionDelete(Person person)
    {
    	personMgr.remove(person);
    	return "delete";
    }

    public String actionCarNew()
    {
        car = new Car();
        return "newcar";
    }
    
    public String actionCarAdd()
    {
        car.setOwner(person);
        person.getCars().add(car);
        return "add";
    }
    
    public String actionCarDel(Car car)
    {
        person.getCars().remove(car);
        return "delete";
    }
    
}
