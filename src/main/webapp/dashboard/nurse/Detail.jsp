<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Nurse Detail</title>
            <link rel="stylesheet" type="text/css" href="/jsfcrud.xhtml" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Nurse Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Id:"/>
                    <h:outputText value="#{nurse.nurse.id}" title="Id" />


                </h:panelGrid>
                <br />
                <h:commandLink action="#{nurse.remove}" value="Destroy">
                    <f:param name="jsfcrud.currentNurse" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][nurse.nurse][nurse.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{nurse.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentNurse" value="#{jsfcrud_class['com.ea.promed.beans.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][nurse.nurse][nurse.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{nurse.createSetup}" value="New Nurse" />
                <br />
                <h:commandLink action="#{nurse.listSetup}" value="Show All Nurse Items"/>
                <br />
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />

            </h:form>
        </body>
    </html>
</f:view>
