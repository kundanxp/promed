/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.demo.beans;

import com.ea.promed.beans.SessionBean;
import com.ea.promed.entities.User;
import com.ea.promed.facades.UserFacade;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author you
 */

@Named(value = "authBackingBean")
@RequestScoped
public class AuthBackingBean {

    private static final Logger log = Logger.getLogger(AuthBackingBean.class.getName());
    
    @EJB
    private UserFacade userFacade;
    
    
    
    private FacesContext context;
    private HttpServletRequest request;
    private ExternalContext ec;
    Map<String, Object> sessionMap;
    

    public AuthBackingBean() {
        
        context = FacesContext.getCurrentInstance();
        request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        ec = context.getExternalContext();
        
        sessionMap = context.getExternalContext().getSessionMap();

    }
    
    
    

    public void logout() throws IOException {
        String result = "/dashboard";
        
        try {
            request.logout();
            
        } catch (ServletException e) {
            log.log(Level.SEVERE, "Failed to logout user!", e);
            result = "/dashboard";
        }
            
        ec.redirect(ec.getRequestContextPath() + result);
    }
    
    
    public void userRoleRedirect() throws IOException{
        
        String username = request.getUserPrincipal().getName();
        
        if(request.isUserInRole("ADMIN"))
        {
            sessionMap.put("role", "ADMIN");
            sessionMap.put("cUser", userFacade.getUserByUserName(username));
            ec.redirect(ec.getRequestContextPath() + "/dashboard/admin/");
            
        }else if(request.isUserInRole("NURSE")){
            
            sessionMap.put("role", "NURSE");
            sessionMap.put("cUser", userFacade.getUserByUserName(username));
            ec.redirect(ec.getRequestContextPath() + "/dashboard/nurse/");
            
        }else if(request.isUserInRole("DOCTOR")){
            
            sessionMap.put("role", "DOCTOR");
            sessionMap.put("cUser", userFacade.getUserByUserName(username));
            ec.redirect(ec.getRequestContextPath() + "/dashboard/doctor/");
            
        }else if(request.isUserInRole("USER")){
            
            sessionMap.put("role", "USER");
            sessionMap.put("cUser", userFacade.getUserByUserName(username));
            ec.redirect(ec.getRequestContextPath() + "/dashboard/user/");
            
        }
 
    }
    
    
    
    public void isAdmin() throws IOException{
        
        if(request.isUserInRole("ADMIN"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
    public void isDoctor() throws IOException{
        
        if(request.isUserInRole("DOCTOR"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
    public void isNurse() throws IOException{
        
        
        if(request.isUserInRole("NURSE"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
    public void isUser() throws IOException{
        
        
        if(request.isUserInRole("USER"))
        {
            
        }else{
            ec.redirect(ec.getRequestContextPath() + "/access-denied.xhtml");
        }
 
    }
    
    
}