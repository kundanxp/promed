/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ea.promed.beans;

import com.ea.promed.entities.Person;
import com.ea.promed.facades.PersonFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author kunda_000
 */


@ManagedBean
@RequestScoped
public class PersonBean
{
    @EJB
    private PersonFacade personFacade;

    private Person person;
    /**
     * Creates a new instance of PersonBean
     */
    public PersonBean()
    {
        person = new Person();
    }

    public Person getPerson()
    {
        return person;
    }

    public void setPerson(Person person)
    {
        this.person = person;
    }
    
    
    
    
    
}
