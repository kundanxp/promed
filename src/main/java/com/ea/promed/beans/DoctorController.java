/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ea.promed.beans;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.FacesException;
import javax.annotation.Resource;
import javax.transaction.UserTransaction;
import com.ea.promed.beans.util.JsfUtil;
import com.ea.promed.beans.util.PagingInfo;
import com.ea.promed.entities.Doctor;
import com.ea.promed.facades.DoctorFacade;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Keshav
 */
public class DoctorController {

    public DoctorController() {
        pagingInfo = new PagingInfo();
        converter = new DoctorConverter();
    }
    private Doctor doctor = null;
    private List<Doctor> doctorItems = null;
    private DoctorFacade jpaController = null;
    private DoctorConverter converter = null;
    private PagingInfo pagingInfo = null;
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "promedPU")
    private EntityManagerFactory emf = null;

    public PagingInfo getPagingInfo() {
        if (pagingInfo.getItemCount() == -1) {
            pagingInfo.setItemCount(getJpaController().count());
        }
        return pagingInfo;
    }

    public DoctorFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (DoctorFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "doctorJpa");
        }
        return jpaController;
    }

    public SelectItem[] getDoctorItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getDoctorItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Doctor getDoctor() {
        if (doctor == null) {
            doctor = (Doctor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDoctor", converter, null);
        }
        if (doctor == null) {
            doctor = new Doctor();
        }
        return doctor;
    }

    public String listSetup() {
        reset(true);
        return "doctor_list";
    }

    public String createSetup() {
        reset(false);
        doctor = new Doctor();
        return "doctor_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(doctor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Doctor was successfully created.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return listSetup();
    }

    public String detailSetup() {
        return scalarSetup("doctor_detail");
    }

    public String editSetup() {
        return scalarSetup("doctor_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        doctor = (Doctor) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentDoctor", converter, null);
        if (doctor == null) {
            String requestDoctorString = JsfUtil.getRequestParameter("jsfcrud.currentDoctor");
            JsfUtil.addErrorMessage("The doctor with id " + requestDoctorString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String doctorString = converter.getAsString(FacesContext.getCurrentInstance(), null, doctor);
        String currentDoctorString = JsfUtil.getRequestParameter("jsfcrud.currentDoctor");
        if (doctorString == null || doctorString.length() == 0 || !doctorString.equals(currentDoctorString)) {
            String outcome = editSetup();
            if ("doctor_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit doctor. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(doctor);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Doctor was successfully updated.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return detailSetup();
    }

    public String remove() {
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentDoctor");
        Long id = new Long(idAsString);
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().remove(getJpaController().find(id));
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Doctor was successfully deleted.");
            } else {
                JsfUtil.ensureAddErrorMessage(transactionException, "A persistence error occurred.");
            }
        } catch (Exception e) {
            try {
                utx.rollback();
            } catch (Exception ex) {
            }
            JsfUtil.ensureAddErrorMessage(e, "A persistence error occurred.");
            return null;
        }
        return relatedOrListOutcome();
    }

    private String relatedOrListOutcome() {
        String relatedControllerOutcome = relatedControllerOutcome();
        if (relatedControllerOutcome != null) {
            return relatedControllerOutcome;
        }
        return listSetup();
    }

    public List<Doctor> getDoctorItems() {
        if (doctorItems == null) {
            getPagingInfo();
            doctorItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return doctorItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "doctor_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "doctor_list";
    }

    private String relatedControllerOutcome() {
        String relatedControllerString = JsfUtil.getRequestParameter("jsfcrud.relatedController");
        String relatedControllerTypeString = JsfUtil.getRequestParameter("jsfcrud.relatedControllerType");
        if (relatedControllerString != null && relatedControllerTypeString != null) {
            FacesContext context = FacesContext.getCurrentInstance();
            Object relatedController = context.getApplication().getELResolver().getValue(context.getELContext(), null, relatedControllerString);
            try {
                Class<?> relatedControllerType = Class.forName(relatedControllerTypeString);
                Method detailSetupMethod = relatedControllerType.getMethod("detailSetup");
                return (String) detailSetupMethod.invoke(relatedController);
            } catch (ClassNotFoundException e) {
                throw new FacesException(e);
            } catch (NoSuchMethodException e) {
                throw new FacesException(e);
            } catch (IllegalAccessException e) {
                throw new FacesException(e);
            } catch (InvocationTargetException e) {
                throw new FacesException(e);
            }
        }
        return null;
    }

    private void reset(boolean resetFirstItem) {
        doctor = null;
        doctorItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Doctor newDoctor = new Doctor();
        String newDoctorString = converter.getAsString(FacesContext.getCurrentInstance(), null, newDoctor);
        String doctorString = converter.getAsString(FacesContext.getCurrentInstance(), null, doctor);
        if (!newDoctorString.equals(doctorString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
