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
public class Admins extends Person implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private Long adminid;

    public Long getAdminid() {
        return adminid;
    }

    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }

}
