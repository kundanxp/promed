<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Doctor</title>
            <link rel="stylesheet" type="text/css" href="/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Doctor</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{doctor.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="FirstName:"/>
                    <h:inputText id="firstName" value="#{doctor.doctor.firstName}" title="FirstName" />
                    <h:outputText value="LastName:"/>
                    <h:inputText id="lastName" value="#{doctor.doctor.lastName}" title="LastName" />
                    <h:outputText value="Phone:"/>
                    <h:inputText id="phone" value="#{doctor.doctor.phone}" title="Phone" />
                    <h:outputText value="Addresses:"/>
                    <h:inputText id="addresses" value="#{doctor.doctor.addresses}" title="Addresses" />
                    <h:outputText value="Mobile:"/>
                    <h:inputText id="mobile" value="#{doctor.doctor.mobile}" title="Mobile" />
                    <h:outputText value="Ssn:"/>
                    <h:inputText id="ssn" value="#{doctor.doctor.ssn}" title="Ssn" />

                </h:panelGrid>
                <br />
                <h:commandLink action="#{doctor.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{doctor.listSetup}" value="Show All Doctor Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
