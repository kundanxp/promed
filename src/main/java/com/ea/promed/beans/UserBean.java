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
import com.ea.promed.util.Email;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;


@ManagedBean
@RequestScoped
public class UserBean extends AbstractBean
{
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private Email emailBean;
    
    User user;
    
    String verifyPassword;
    
    
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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
    
    
    
    public String createUser() throws MessagingException, UnsupportedEncodingException
    {
        
        String hash = Hashing.sha256().hashString(user.getPassword(), Charsets.UTF_8).toString();
        
        user.setVerification(hash);
        user.setPassword(hash);
        userFacade.create(user);
        
        emailBean.setToemail(user.getEmail());
        emailBean.setSubject("Email Verification from Pro Medical Services");
        emailBean.setMessagetext(user.getPerson().getFirstName(),hash);
        
        emailBean.sendEMail();
        
        return "register";
    }
    
    
    public String verifyUser(String code) throws IOException
    {
        User checkUser = userFacade.getUserByCode(code);
        
        if(checkUser != null)
        {
            checkUser.setVerification("");
            userFacade.edit(checkUser);
            userFacade.activateUser(checkUser);
            
            context.addMessage("message", new FacesMessage("Email has been verified. Please login"));
            
            ec.redirect("/dashboard/");
            
        }else{
            FacesContext.getCurrentInstance().addMessage("message", new FacesMessage("Verification Code Error"));
        }
        
        return "verification";
    }
    

    public String forgetPassword()
    {
        
        user = userFacade.getUserByEmail(user.getEmail());
        if(user != null)
        {
            String code = UUID.randomUUID().toString();
            user.setVerification(code);


            userFacade.edit(user);

            emailBean.setToemail(user.getEmail());
            emailBean.setSubject("Password Reset Request : Pro Medical Services");
            emailBean.setMessagetext(user.getPerson().getFirstName(),"Please click below link to reset password.",code);

            emailBean.sendEMail();
            
            context.addMessage("forget:email", new FacesMessage("Email has been sent to your email address."));
            
        }else{
            context.addMessage("forget:email", new FacesMessage("User not found with this email."));
        }
        return "forget-password";
    }
    
    
    
    public boolean checkVerification(String code)
    {
        user = userFacade.getUserByCode(code);
        
        return user != null;
        
    }
    
    
    public String resetPassword(String code) throws IOException
    {
        System.out.println(user.getPassword());
        System.out.println(verifyPassword);
        
        if(user.getPassword().equals(verifyPassword))
        {
            return "/dashboard/index";
        }else{
            return "/reset-password?faces-redirect=true&includeViewParams=true";
        }
        
    }
    
    
}
