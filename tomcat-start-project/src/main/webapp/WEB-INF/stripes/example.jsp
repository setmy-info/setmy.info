<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%> 

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stripe Page</title>
    </head>
    <body>
    <stripes:form beanclass="ee.pub.web.actions.stripes.CalculatorActionBean" focus="">
        <table>
            <tr>
                <td>Person:</td>
                <td><stripes:text name="person.firstName"/><stripes:text name="person.lastName"/></td>
            </tr>
            <tr>
                <td>Number 1:</td>
                <td><stripes:text name="numberOne"/></td>
            </tr>
            <tr>
                <td>Number 2:</td>
                <td><stripes:text name="numberTwo"/></td>
            </tr>
            <tr>
                <td colspan="2">
            <stripes:submit name="addition" value="Add"/>
            </td>
            </tr>
            <tr>
                <td>Result:</td>
                <td>${actionBean.result}</td>
            </tr>
        </table>
    </stripes:form>
</body>
</html>