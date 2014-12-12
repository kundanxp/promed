/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author kunda_000
 */
@Entity
public class Nurse extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    private Long nurseId;
    
    private String licenseId;

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
    }

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }
    
    
    

    
    
}
