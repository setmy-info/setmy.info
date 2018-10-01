<%-- 
    Document   : upload
    Created on : 2.10.2010, 20:40:07
    Author     : Imre Tabur
--%>
<%@page contentType="text/html" pageEncoding="windows-1257"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
    <head>
        <title>File Upload Example</title>
        <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>

    </head>

    <body>

        <s:actionerror />
        <s:fielderror />
        <s:form action="upload" method="POST" enctype="multipart/form-data">
            <h1>File Upload Example</h1>
            <s:file name="upload" label="File"/>
            <s:textfield name="caption" label="Caption"/>
            <s:submit />
        </s:form>
    </body>
</html>