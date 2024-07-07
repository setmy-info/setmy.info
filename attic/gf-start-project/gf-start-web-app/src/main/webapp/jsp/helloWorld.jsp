<!DOCTYPE html PUBLIC 
    "-//W3C//DTD XHTML 1.1 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>Tere Maailma</title>
        <s:head />
    </head>
    <body>
        <s:property value="HelloWorld.message" />
        <s:property value="label"/>
	Tere <s:property value="name"/>
        <%
        // PUG: TODO : Issue: Issue 7
        //Today: <s:property value="dateNow" />
        %>
        Download file - <a href="<s:url action="content"/>">File</a>
        <h3>Languages</h3>
        <ul>
            <li>
                <s:url var="url" action="content">
                    <s:param name="request_locale">en</s:param>
                </s:url>
                <s:a href="%{url}">English</s:a>
            </li>
            <li>
                <s:url var="url" action="content">
                    <s:param name="request_locale">es</s:param>
                </s:url>
                <s:a href="%{url}">Espanol</s:a>
            </li>
            <li>
                <s:url var="url" action="content">
                    <s:param name="request_locale">et</s:param>
                </s:url>
                <s:a href="%{url}">Estonian</s:a>
            </li>
        </ul>
            
        <s:actionerror />
        <s:fielderror />
        <s:form action="uploader" method="POST" enctype="multipart/form-data">
            <h1>File Upload Example</h1>
            <s:file name="upload" label="File" />
            <s:textfield name="fileCaption" label="Caption" />
            <s:submit />
        </s:form>

    </body>
</html>
