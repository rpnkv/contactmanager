<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Contact manager - add contact</title>
</head>
<body>

<t:template>
    <h1>Add new contact</h1>

    <form:form method="post" action="addContact" commandName="contact" >
        <table >
            <tr>
                <td><form:label path="firstname">
                    Firstname
                </form:label></td>
                <td><form:input path="firstname"/></td>
                <td><form:errors cssClass="error" path="firstname"></form:errors>  </td>
            </tr>
            <tr>
                <td><form:label path="lastname">
                    Lastname
                </form:label>
                </td>
                <td>
                    <form:input path="lastname"/> </td>
                <td><form:errors cssClass="error" path="lastname"></form:errors>  </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Add contact"/></td>
            </tr>
        </table>
    </form:form>
</t:template>

