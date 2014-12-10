<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Doctor Items</title>
            <link rel="stylesheet" type="text/css" href="/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Doctor Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Doctor Items Found)<br />" rendered="#{doctor.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{doctor.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{doctor.pagingInfo.firstItem + 1}..#{doctor.pagingInfo.lastItem} of #{doctor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{doctor.prev}" value="Previous #{doctor.pagingInfo.batchSize}" rendered="#{doctor.pagingInfo.firstItem >= doctor.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{doctor.next}" value="Next #{doctor.pagingInfo.batchSize}" rendered="#{doctor.pagingInfo.lastItem + doctor.pagingInfo.batchSize <= doctor.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{doctor.next}" value="Remaining #{doctor.pagingInfo.itemCount - doctor.pagingInfo.lastItem}"
                                   rendered="#{doctor.pagingInfo.lastItem < doctor.pagingInfo.itemCount && doctor.pagingInfo.lastItem + doctor.pagingInfo.batchSize > doctor.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{doctor.doctorItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Id"/>
                            </f:facet>
                            <h:outputText value="#{item.id}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="FirstName"/>
                            </f:facet>
                            <h:outputText value="#{item.firstName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="LastName"/>
                            </f:facet>
                            <h:outputText value="#{item.lastName}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Phone"/>
                            </f:facet>
                            <h:outputText value="#{item.phone}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Addresses"/>
                            </f:facet>
                            <h:outputText value="#{item.addresses}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Mobile"/>
                            </f:facet>
                            <h:outputText value="#{item.mobile}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Ssn"/>
                            </f:facet>
                            <h:outputText value="#{item.ssn}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{doctor.detailSetup}">
                                <f:param name="jsfcrud.currentDoctor" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][doctor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{doctor.editSetup}">
                                <f:param name="jsfcrud.currentDoctor" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][doctor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{doctor.remove}">
                                <f:param name="jsfcrud.currentDoctor" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][doctor.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>

                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{doctor.createSetup}" value="New Doctor"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />


            </h:form>
        </body>
    </html>
</f:view>
