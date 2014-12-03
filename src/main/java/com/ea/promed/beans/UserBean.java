/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ea.promed.beans;

import com.ea.promed.entities.Person;
import com.ea.promed.entities.User;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.ea.promed.facades.UserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class UserBean
{
    @EJB
    private UserFacade userFacade;
    
    
    
    User user;
    
    public UserBean()
    {
        user = new User();
        user.setPerson(new Person());
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
    
    public String createUser()
    {
        
        String hash = Hashing.sha256().hashString(user.getPassword(), Charsets.UTF_8).toString();
//        String hash = Hashing.md5().hashString(user.getPassword(), Charsets.UTF_8).toString();
        
        user.setPassword(hash);
        userFacade.create(user);
        return "register";
    }
    
    
}
