<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <c:set var="language" value="${not empty sessionScope.language ?
    sessionScope.language : pageContext.request.locale}"  />

    <fmt:setLocale value="${language}"/>
    <fmt:setBundle basename="localization" var="loc"/>

    <fmt:message bundle="${loc}" key="label.add_answer" var="answer_label"/>
    <fmt:message bundle="${loc}" key="button.add_answer" var="add_answer_btn"/>

    <title>Like IT</title>
    <meta charset="utf-8"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/question.js"></script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/WEB-INF/jsp/templates/header.jsp"/>

<div class="container">
    <div class="error-div row"></div>

    <div id="question-div">
        <input type="hidden" name="id" value="${param.id}" id="question-id">
    </div>
    <c:if test="${sessionScope.user.id != null}">
        <div class="edit-question-div">
            <input type="hidden" name="currentUserId" value="${sessionScope.user.id}" id="current-user-id">
        </div>
    </c:if>

    <div id="answers-list"></div>

    <br/>

    <c:if test="${sessionScope.user != null}">
        <form class="form-horizontal" action="javascript:void(null);" onsubmit="addAnswer()" method="post">
            <input type="hidden" name="questionId" value="${param.id}">
            <input type="hidden" name="command" value="add-answer">
            <div class="form-group">
                <label class="control-label col-xs-3" for="inputAnswerText">${answer_label}:</label>
                <div class="col-xs-9">
                    <input type="text" name="text" class="form-control" id="inputAnswerText" maxlength="1000" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-offset-3 col-xs-9">
                    <input type="submit" class="btn btn-primary" value="${add_answer_btn}">
                </div>
            </div>
        </form>
    </c:if>
</div>
</body>
</html>