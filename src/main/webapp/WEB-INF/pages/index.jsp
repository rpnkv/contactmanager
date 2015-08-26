<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:template>
    <h1>Contact list</h1>
        <c:if test = "${!empty contacts}">
            <table class ="contact-list-table">
                <tr>
                   <%-- <th><b>Id</b></th>--%>
                    <th><b>Firstname</b></th>
                    <th><b>Lastname</b></th>
                    <th><b>Records</b></th>
                    <sec:authorize access="hasRole('admin')">
                        <th></th>
                    </sec:authorize>

                </tr>
                <c:forEach items = "${contacts}" var = "contact">
                    <c:set var="n" value="${contact.contactRecords.size()}"/>
                    <tr td valign="top">
                        <%--<td rowspan=${n+1}>${contact.iD} ${contNum}</td>--%>
                        <td rowspan=${n+1}>${contact.firstname}</td>
                        <td rowspan=${n+1}>${contact.lastname}</td>
                      <%--      <c:choose>
                                <c:when test="${contNum!=0}">
                                    <c:forEach items="${contact.contacts}" var="contVal">
                                        <td>${contVal.name} : ${contVal.value}</td>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <td align="middle"> - </td>
                                </c:otherwise>
                            </c:choose>--%>
                        <td><a href="/addRecord/${contact.iD}" /> New record </td>
                        <sec:authorize access="hasRole('admin')">
                            <td valign="middle" rowspan=${n+1}><a href="javascript:BookUtil.deleteBook(${contact.iD})">Delete contact</a></td>
                        </sec:authorize>
                    </tr>
                    <c:choose>
                        <c:when test="${n!=0}">
                    <c:forEach var="i" begin="0" end="${n-1}">
                         <tr>
                            <td>
                                ${contact.contactRecords.get(i).type} : ${contact.contactRecords.get(i).value}
                                    <a href="/delContactRecord/contact_id=${contact.iD}+record_id=${i}">Delete record ${i}</a>
                           </td>
                         </tr>
                    </c:forEach>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </table>
        </c:if>
         <%-- <sec:authorize access="isAuthenticated()">
             <a href="addContact">New contact</a>
          </sec:authorize>--%>
    <a href="addContact">New contact</a>

</t:template>
