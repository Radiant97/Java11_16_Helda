<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty sessionScope.language ?
    sessionScope.language : pageContext.request.locale}"  />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization" var="loc"/>

<!DOCTYPE html>
<html>
  <head>
    <title>Like IT</title>
    <meta charset="utf-8"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
  </head>

  <c:import url="/WEB-INF/jsp/templates/header.jsp"/>

  </body>
</html>
