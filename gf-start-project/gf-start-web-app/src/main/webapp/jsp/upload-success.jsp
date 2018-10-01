<%-- 
    Document   : upload-success.jsp
    Created on : 2.10.2010, 20:48:27
    Author     : Imre Tabur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
    <head>
        <title>Showcase</title>
        <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <table class="wwFormTable">
            <tr>

                <td colspan="2"><h1>File Upload Example</h1></td>
            </tr>

            <tr>
                <td class="tdLabel"><label for="doUpload_upload" class="label">Content Type:</label></td>
                <td><s:property value="uploadContentType" /></td>
            </tr>

            <tr>
                <td class="tdLabel"><label for="doUpload_upload" class="label">File Name:</label></td>
                <td ><s:property value="uploadFileName" /></td>
            </tr>


            <tr>
                <td class="tdLabel"><label for="doUpload_upload" class="label">File:</label></td>
                <td><s:property value="upload" /></td>
            </tr>

            <tr>
                <td class="tdLabel"><label for="doUpload_upload" class="label">File Caption:</label></td>
                <td><s:property value="fileCaption" /></td>
            </tr>


        </table>

    </body>
</html>