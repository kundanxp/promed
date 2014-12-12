/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author kunda_000
 */

@Entity
public class Patient extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long patientNo;
    
    @ManyToOne
    private Client client;

    public Long getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(Long patientNo) {
        this.patientNo = patientNo;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
    
}
