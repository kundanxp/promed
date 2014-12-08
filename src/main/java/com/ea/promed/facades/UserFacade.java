/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ea.promed.facades;

import com.ea.promed.entities.Person;
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
        return em.createQuery("SELECT u FROM User u WHERE u.username = ?1",User.class).setParameter(1, username).getSingleResult();
//        return em.find(User.class, 1L);
    }
    
    
    public User getUserByCode(String code)
    {
        try{
            return em.createQuery("SELECT u FROM User u WHERE u.verification = ?1",User.class).setParameter(1, code).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
    
    public User getUserByEmail(String email)
    {
        try{
            return em.createQuery("SELECT u FROM User u WHERE u.email = ?1",User.class).setParameter(1, email).getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
    
    public int activateUser(User user)
    {
        return em.createNativeQuery("INSERT INTO user_groups VALUES (?1, ?2)").setParameter(1, user.getUser_id()).setParameter(2, 4).executeUpdate();
    }
    
    
}
