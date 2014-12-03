/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ea.promed.facades;

import com.ea.promed.entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kunda_000
 */
@Stateless
public class UserFacade extends AbstractFacade<User>
{
    @PersistenceContext(unitName = "promedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UserFacade()
    {
        super(User.class);
    }
    
    public User getUserByUserName(String username)
    {
//        return (User) em.createQuery("SELECT u FROM User WHERE username = :username").setParameter(1, username).getSingleResult();
        return em.find(User.class, 1L);
    }
    
    
}
