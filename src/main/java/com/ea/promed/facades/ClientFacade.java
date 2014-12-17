/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.facades;

import com.ea.promed.entities.Client;
import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.Patient;
import com.ea.promed.entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.hibernate.Hibernate;

/**
 *
 * @author kunda_000
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {
    @PersistenceContext(unitName = "promedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    public List<Client> listAllClients()
    {
        return getEntityManager().createQuery("SELECT c FROM Client c").getResultList();
    }
    
    public Client getClientByUser(User user)
    {
        return (Client) getEntityManager().createQuery("SELECT c FROM Client c WHERE c.user = ?1").setParameter(1, user).getSingleResult();
    }
    
    
    public List<Patient> getMyPatients(Client client)
    {
        return getEntityManager().createQuery("SELECT p FROM Patient p WHERE p.client = ?1 ").setParameter(1, client).getResultList();
    }
    
}
