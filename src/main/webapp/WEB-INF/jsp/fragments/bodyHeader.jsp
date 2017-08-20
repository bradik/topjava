<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
    <a href="${pageContext.request.contextPath}/"><spring:message code="app.home"/></a>&nbsp;|&nbsp;
    <a href="meals"><spring:message code="app.title"/></a>&nbsp;|&nbsp;
    <a href="users"><spring:message code="user.title"/></a>
</header>