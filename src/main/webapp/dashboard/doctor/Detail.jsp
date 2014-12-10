<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Doctor Detail</title>
            <link rel="stylesheet" type="text/css" href="/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Doctor Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{doctor.doctor.id}" title="Id" />
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{doctor.doctor.id}" title="Id" />
                    <h:outputText value="FirstName:"/>
                    <h:outputText value="#{doctor.doctor.firstName}" title="FirstName" />
                    <h:outputText value="LastName:"/>
                    <h:outputText value="#{doctor.doctor.lastName}" title="LastName" />
                    <h:outputText value="Phone:"/>
                    <h:outputText value="#{doctor.doctor.phone}" title="Phone" />
                    <h:outputText value="Addresses:"/>
                    <h:outputText value="#{doctor.doctor.addresses}" title="Addresses" />
                    <h:outputText value="Mobile:"/>
                    <h:outputText value="#{doctor.doctor.mobile}" title="Mobile" />
                    <h:outputText value="Ssn:"/>
                    <h:outputText value="#{doctor.doctor.ssn}" title="Ssn" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{doctor.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentDoctor" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][doctor.doctor][doctor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{doctor.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentDoctor" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][doctor.doctor][doctor.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{doctor.createSetup}" value="New Doctor" />
                <br />
                <h:commandLink action="#{doctor.listSetup}" value="Show All Doctor Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
