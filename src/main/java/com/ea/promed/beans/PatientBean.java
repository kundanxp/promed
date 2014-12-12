/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import com.ea.promed.entities.Client;
import com.ea.promed.entities.Patient;
import com.ea.promed.entities.User;
import com.ea.promed.facades.ClientFacade;
import com.ea.promed.facades.PatientFacade;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author kunda_000
 */
@ManagedBean
@RequestScoped
public class PatientBean extends AbstractBean {
    
    @EJB
    PatientFacade patientFacade;
    
    @EJB
    ClientFacade clientFacade;

    private Patient patient;
    
    public PatientBean() {
        patient = new Patient();
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    
    public String createPatient()
    {
        if(patient != null)
        {
            Client client = (Client) sessionMap.get("cClient");
            
            patient.setClient(client);
            
            patientFacade.create(patient);
            
        }
            
        
        sessionMap.put("message", "Patient added successfully.");
        
        return "patients";
    }
    
    public List<Patient> listPatientsByClient()
    {
        Client client = (Client) sessionMap.get("cClient");
        
        return client.getPatients();
    }
    
    
    
}
