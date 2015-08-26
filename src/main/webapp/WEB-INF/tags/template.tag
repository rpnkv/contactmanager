<%@ tag description="Template Tag" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype>
<html>
    <head>
        <title>Contact manager - contact list</title>
        <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
        <script src ="<c:url value="/resources/js/bookUtils.js"/> "></script>
        <div class="login-link-container">
            <sec:authorize access="isAnonymous()">
                <p>
                    <a href="/spring_security_login">Sign In</a>
                </p>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href = "/j_spring_security_logout">Sign Out</a>
            </sec:authorize>
        </div>

    </head>
    <body>
        <div class = "content">
            <jsp:doBody/>
        </div>

    </body>
</html>
