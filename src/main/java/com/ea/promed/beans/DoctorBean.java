/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.Patient;
import com.ea.promed.entities.User;
import com.ea.promed.facades.DoctorFacade;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.UnsupportedEncodingException;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

/**
 *
 * @author kunda_000
 */

@ManagedBean
@RequestScoped
public class DoctorBean {

    @EJB
    private DoctorFacade doctorFacade;
    
    User user;
    
    public DoctorBean() {
        user = new User();
        user.setPerson(new Doctor());
    }
    
    
    public String createDoctor() throws MessagingException, UnsupportedEncodingException
    {
        
        String hash = Hashing.sha256().hashString(user.getPassword(), Charsets.UTF_8).toString();
        
        user.setVerification(hash);
        user.setPassword(hash);
        doctorFacade.create((Doctor) user.getPerson());
        
        
        
        return "dashboard/index?faces-redirect=true";
    }
    
    
    
}
