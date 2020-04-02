package org.fit.pis.data;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;


@Entity
@Table(name = "Person")
public class Person
{
    @Id
    private long id;
	private String name;
    private String surname;
    @Temporal(TemporalType.DATE)
    @JsonbDateFormat("yyyy-MM-dd")
    private Date born;
    @OneToMany(cascade = { ALL }, fetch = EAGER, mappedBy = "owner", orphanRemoval = true)
    @JsonbTransient
	private Collection<Car> cars;

    public Person()
    {
        cars = new Vector<Car>();
    }
    
    public Collection<Car> getCars()
    {
        return cars;
    }

    public void setCars(Collection<Car> cars)
    {
        this.cars = cars;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * @return the surname
     */
    public String getSurname()
    {
        return surname;
    }
    
    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname)
    {
        this.surname = surname;
    }
    
    /**
     * @return the rc
     */
    public long getId()
    {
        return id;
    }
    
    /**
     * @param rc the rc to set
     */
    public void setId(long id)
    {
        this.id = id;
    }
    
    /**
     * @return the born
     */
    public Date getBorn()
    {
        return born;
    }
    
    /**
     * @param born the born to set
     */
    public void setBorn(Date born)
    {
        this.born = born;
    }
    
    @Override
    public String toString()
    {
        return "Person: " + name + " " + surname + "(" + cars.size() + " cars)";
    }
    
}
