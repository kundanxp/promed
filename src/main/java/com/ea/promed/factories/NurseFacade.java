/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.factories;

import com.ea.promed.entities.Nurse;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Keshav
 */
@Stateless
public class NurseFacade extends AbstractFacade<Nurse> {
    @PersistenceContext(unitName = "promedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NurseFacade() {
        super(Nurse.class);
    }
    
}
