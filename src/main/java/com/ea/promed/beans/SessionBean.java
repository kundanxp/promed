/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.User;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author kunda_000
 */
@Named(value = "sessionBean")
@SessionScoped
public class SessionBean{

    private User user;
    
    public SessionBean() {
        user = new User();
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
}
