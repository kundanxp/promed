<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Nurse Items</title>
            <link rel="stylesheet" type="text/css" href="/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Nurse Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Nurse Items Found)<br />" rendered="#{nurse.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{nurse.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{nurse.pagingInfo.firstItem + 1}..#{nurse.pagingInfo.lastItem} of #{nurse.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{nurse.prev}" value="Previous #{nurse.pagingInfo.batchSize}" rendered="#{nurse.pagingInfo.firstItem >= nurse.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{nurse.next}" value="Next #{nurse.pagingInfo.batchSize}" rendered="#{nurse.pagingInfo.lastItem + nurse.pagingInfo.batchSize <= nurse.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{nurse.next}" value="Remaining #{nurse.pagingInfo.itemCount - nurse.pagingInfo.lastItem}"
                                   rendered="#{nurse.pagingInfo.lastItem < nurse.pagingInfo.itemCount && nurse.pagingInfo.lastItem + nurse.pagingInfo.batchSize > nurse.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{nurse.nurseItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{nurse.detailSetup}">
                                <f:param name="jsfcrud.currentNurse" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][nurse.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{nurse.editSetup}">
                                <f:param name="jsfcrud.currentNurse" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][nurse.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{nurse.remove}">
                                <f:param name="jsfcrud.currentNurse" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][nurse.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{nurse.createSetup}" value="New Nurse"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
