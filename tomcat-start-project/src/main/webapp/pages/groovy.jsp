<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Message from Groovy</title>
    </head>
    <body>
        <!-- http://localhost:8080/tomcat-start-project/private/viewGreeting -->
        <h1>Message from Groovy</h1>
        <s:property value="greeting"/><br>
        <s:property value="getText('requiredstring')"/><br>
        <s:property value="getText('some.string')"/><br>
        <s:a href="Greeting?request_locale=en_US" >English</s:a>
        <s:a href="Greeting?request_locale=et_EE" >Estonian</s:a>
    </body>
</html>
