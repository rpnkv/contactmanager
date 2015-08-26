<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
  <title>Contact manager - add record</title>
</head>
<body>
<t:template>
<h1>Adding record to contact: ${name} ${sname}</h1>
<form:form method="post" action="" commandName="record">

  <table >
    <tr>
      <td><form:label path="type">Type: </form:label></td>
      <td><form:input path="type" /></td>
      <td><form:errors cssClass="error" path="type"></form:errors>  </td>
    </tr>
    <tr>
      <td><form:label path="value">Value: </form:label></td>
      <td><form:input path="value" /></td>
      <td><form:errors cssClass="error" path="value"></form:errors>  </td>
    </tr>
    <tr>
      <td colspan="2">
        <input type="submit" value="Add record"/>
      </td>
    </tr>
  </table>

</form:form>
</t:template>
