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
import com.ea.promed.entities.Nurse;
import com.ea.promed.facades.NurseFacade;
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
public class NurseController {

    public NurseController() {
        pagingInfo = new PagingInfo();
        converter = new NurseConverter();
    }
    private Nurse nurse = null;
    private List<Nurse> nurseItems = null;
    private NurseFacade jpaController = null;
    private NurseConverter converter = null;
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

    public NurseFacade getJpaController() {
        if (jpaController == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            jpaController = (NurseFacade) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "nurseJpa");
        }
        return jpaController;
    }

    public SelectItem[] getNurseItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), false);
    }

    public SelectItem[] getNurseItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(getJpaController().findAll(), true);
    }

    public Nurse getNurse() {
        if (nurse == null) {
            nurse = (Nurse) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNurse", converter, null);
        }
        if (nurse == null) {
            nurse = new Nurse();
        }
        return nurse;
    }

    public String listSetup() {
        reset(true);
        return "nurse_list";
    }

    public String createSetup() {
        reset(false);
        nurse = new Nurse();
        return "nurse_create";
    }

    public String create() {
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().create(nurse);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Nurse was successfully created.");
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
        return scalarSetup("nurse_detail");
    }

    public String editSetup() {
        return scalarSetup("nurse_edit");
    }

    private String scalarSetup(String destination) {
        reset(false);
        nurse = (Nurse) JsfUtil.getObjectFromRequestParameter("jsfcrud.currentNurse", converter, null);
        if (nurse == null) {
            String requestNurseString = JsfUtil.getRequestParameter("jsfcrud.currentNurse");
            JsfUtil.addErrorMessage("The nurse with id " + requestNurseString + " no longer exists.");
            return relatedOrListOutcome();
        }
        return destination;
    }

    public String edit() {
        String nurseString = converter.getAsString(FacesContext.getCurrentInstance(), null, nurse);
        String currentNurseString = JsfUtil.getRequestParameter("jsfcrud.currentNurse");
        if (nurseString == null || nurseString.length() == 0 || !nurseString.equals(currentNurseString)) {
            String outcome = editSetup();
            if ("nurse_edit".equals(outcome)) {
                JsfUtil.addErrorMessage("Could not edit nurse. Try again.");
            }
            return outcome;
        }
        try {
            utx.begin();
        } catch (Exception ex) {
        }
        try {
            Exception transactionException = null;
            getJpaController().edit(nurse);
            try {
                utx.commit();
            } catch (javax.transaction.RollbackException ex) {
                transactionException = ex;
            } catch (Exception ex) {
            }
            if (transactionException == null) {
                JsfUtil.addSuccessMessage("Nurse was successfully updated.");
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
        String idAsString = JsfUtil.getRequestParameter("jsfcrud.currentNurse");
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
                JsfUtil.addSuccessMessage("Nurse was successfully deleted.");
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

    public List<Nurse> getNurseItems() {
        if (nurseItems == null) {
            getPagingInfo();
            nurseItems = getJpaController().findRange(new int[]{pagingInfo.getFirstItem(), pagingInfo.getFirstItem() + pagingInfo.getBatchSize()});
        }
        return nurseItems;
    }

    public String next() {
        reset(false);
        getPagingInfo().nextPage();
        return "nurse_list";
    }

    public String prev() {
        reset(false);
        getPagingInfo().previousPage();
        return "nurse_list";
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
        nurse = null;
        nurseItems = null;
        pagingInfo.setItemCount(-1);
        if (resetFirstItem) {
            pagingInfo.setFirstItem(0);
        }
    }

    public void validateCreate(FacesContext facesContext, UIComponent component, Object value) {
        Nurse newNurse = new Nurse();
        String newNurseString = converter.getAsString(FacesContext.getCurrentInstance(), null, newNurse);
        String nurseString = converter.getAsString(FacesContext.getCurrentInstance(), null, nurse);
        if (!newNurseString.equals(nurseString)) {
            createSetup();
        }
    }

    public Converter getConverter() {
        return converter;
    }
    
}
