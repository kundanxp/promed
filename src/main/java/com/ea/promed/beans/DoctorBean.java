/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Client;
import com.ea.promed.entities.Department;
import com.ea.promed.entities.Doctor;
import com.ea.promed.entities.Patient;
import com.ea.promed.entities.User;
import com.ea.promed.facades.DoctorFacade;
import com.ea.promed.facades.UserFacade;
import com.ea.promed.util.Email;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.MessagingException;

/**
 *
 * @author kunda_000
 */

@ManagedBean
@RequestScoped
public class DoctorBean extends AbstractBean {

    @EJB
    private DoctorFacade doctorFacade;
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private Email emailBean;
    
    private Doctor doctor;
    private int selectedValue;
    private Map<Integer,String> depTest=new HashMap<>();
    
    
    public DoctorBean() {
        depTest.put(1,"Nepal");
        depTest.put(2,"India");
        depTest.put(3,"China");
        System.out.println("Size is:: "+depTest);
    }
    
    public void testEdit(Doctor d){
        System.out.println("Current selected d id "+d.getId());
        
    }

    public Doctor getDoctor() {
        if(doctor == null)
        {
            doctor = new Doctor();
            doctor.setUser(new User());
            doctor.setDepartment(new Department());
        }
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    
    public String testItem(){
        System.out.println("Selected id is "+selectedValue);
        return "";
    }
    
    public String createDoctor() throws MessagingException, UnsupportedEncodingException
    {
        
        if(doctor.getId() != null)
        {
            Doctor eDoctor = (Doctor) sessionMap.get("eDoctor");
            
            doctor.setUser(eDoctor.getUser());
            
            doctorFacade.edit(doctor);
            
            sessionMap.put("message", "Doctor Info updated Successfully.");
            
        }else{
        
        
            String code = UUID.randomUUID().toString();

            String hash = Hashing.sha256().hashString(code, Charsets.UTF_8).toString();

            doctor.getUser().setVerification(hash);
            
            doctor.getUser().setPassword(hash);

            doctorFacade.create(doctor);

            userFacade.activateUser(doctor.getUser(), 2);

            emailBean.setToemail(doctor.getUser().getEmail());
            emailBean.setSubject("Login Credentials : Pro Medical Services");
            emailBean.setMessagetext(doctor.getFirstName(),"Your user name is " +doctor.getUser().getUsername()+ ". You can create new password by clicking below link.",hash);

            emailBean.sendEMail();

            sessionMap.put("message", "Doctor Info added Successfully.");
            
        }
        
        
        return "doctors?faces-redirect=true";
    }
    
    
    
    public void editDoctor(String doctorid) throws IOException
    {
        Long did = Long.parseLong(doctorid);
        doctor = doctorFacade.find(did);
        
        if(doctor != null)
        {
            sessionMap.put("eDoctor", doctor);
        }
    }
    
    
    public List<Doctor> listAllDoctors()
    {
        return doctorFacade.listAllDoctors();
    }
    
    public List<Doctor> listFreeDoctors()
    {
        List<Doctor> doctors = doctorFacade.getFreeDoctors();
        System.out.println(doctors.size());
        return doctors;
    }
    

    public int getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
    }

    public Map<Integer, String> getDepTest() {
        return depTest;
    }

    public void setDepTest(Map<Integer, String> depTest) {
        this.depTest = depTest;
    }
    
    
    
}
