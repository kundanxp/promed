<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Nurse</title>
            <link rel="stylesheet" type="text/css" href="/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Nurse</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{nurse.validateCreate}" value="value"/>
                <h:panelGrid columns="2">

                </h:panelGrid>
                <br />
                <h:commandLink action="#{nurse.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{nurse.listSetup}" value="Show All Nurse Items" immediate="true"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
