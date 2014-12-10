<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Nurse</title>
            <link rel="stylesheet" type="text/css" href="/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Nurse</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="LicenseNumber:"/>
                    <h:inputText id="licenseNumber" value="#{nurse.nurse.licenseNumber}" title="LicenseNumber" />
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{nurse.nurse.id}" title="Id" />
                    <h:outputText value="FirstName:"/>
                    <h:inputText id="firstName" value="#{nurse.nurse.firstName}" title="FirstName" />
                    <h:outputText value="LastName:"/>
                    <h:inputText id="lastName" value="#{nurse.nurse.lastName}" title="LastName" />
                    <h:outputText value="Phone:"/>
                    <h:inputText id="phone" value="#{nurse.nurse.phone}" title="Phone" />
                    <h:outputText value="Addresses:"/>
                    <h:inputText id="addresses" value="#{nurse.nurse.addresses}" title="Addresses" />
                    <h:outputText value="Mobile:"/>
                    <h:inputText id="mobile" value="#{nurse.nurse.mobile}" title="Mobile" />
                    <h:outputText value="Ssn:"/>
                    <h:inputText id="ssn" value="#{nurse.nurse.ssn}" title="Ssn" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{nurse.edit}" value="Save">
                    <f:param name="jsfcrud.currentNurse" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][nurse.nurse][nurse.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{nurse.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentNurse" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][nurse.nurse][nurse.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{nurse.listSetup}" value="Show All Nurse Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
