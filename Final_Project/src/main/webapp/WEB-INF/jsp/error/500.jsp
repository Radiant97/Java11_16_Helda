<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty language ?
    language : pageContext.request.locale}" scope="session" />
<fmt:setBundle basename="localization" var="loc"/>

<!DOCTYPE html>
<html>
<head>
    <title>Like IT</title>
</head>
<body>
<div id="error-500">
    <h1>Error 500</h1>
    <h3>${requestScope['javax.servlet.error.message']}</h3>
</div>
</body>
</html>