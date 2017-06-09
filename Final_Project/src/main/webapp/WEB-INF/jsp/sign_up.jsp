<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty sessionScope.language ?
    sessionScope.language : pageContext.request.locale}"  />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization" var="loc"/>

<fmt:message bundle="${loc}" key="label.nickname" var="nickname_label"/>
<fmt:message bundle="${loc}" key="label.email" var="email_label"/>
<fmt:message bundle="${loc}" key="label.password" var="password_label"/>
<fmt:message bundle="${loc}" key="label.confirm_password" var="confirm_password_label"/>
<fmt:message bundle="${loc}" key="label.registr" var="reg_label"/>

<fmt:message bundle="${loc}" key="button.sign_up" var="sign_up_btn"/>
<fmt:message bundle="${loc}" key="button.clear_form" var="clear_form_btn"/>

<!DOCTYPE html>
<html>
<head>
    <title>Like IT</title>
    <meta charset="utf-8"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/WEB-INF/jsp/templates/header.jsp"/>

    <div class="container">
        <h2>${reg_label}</h2>
        <form class="form-horizontal" action="Controller" method="post">
            <input type="hidden" name="command" value="sign-up">
            <div class="form-group">
                <label class="control-label col-xs-3" for="inputNickname">${nickname_label}:</label>
                <div class="col-xs-9">
                    <input type="text" name="nickname" class="form-control" id="inputNickname" pattern="[a-Z_\d]{5,20}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="inputEmail">${email_label}:</label>
                <div class="col-xs-9">
                    <input type="email" name="email" class="form-control" id="inputEmail" pattern="([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="inputPassword">${password_label}:</label>
                <div class="col-xs-9">
                    <input type="password" name="password" class="form-control" id="inputPassword" pattern="[A-z\d]{6,}" required>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-xs-3" for="confirmPassword">${confirm_password_label}:</label>
                <div class="col-xs-9">
                    <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" pattern="[A-z\d]{6,}" required>
                </div>
            </div>

            <br />
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input type="submit" class="btn btn-primary" value="${sign_up_btn}">
                    <input type="reset" class="btn btn-default" value="${clear_form_btn}">
                </div>
            </div>
        </form>
    </div>
</body>
</html>