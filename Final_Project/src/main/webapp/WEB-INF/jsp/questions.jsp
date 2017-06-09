<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty sessionScope.language ?
    sessionScope.language : pageContext.request.locale}"  />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization" var="loc"/>

<fmt:message bundle="${loc}" key="label.all_questions" var="all_questions_label"/>
<fmt:message bundle="${loc}" key="label.question_header" var="question_header_label"/>
<fmt:message bundle="${loc}" key="label.question_text" var="question_text_label"/>
<fmt:message bundle="${loc}" key="label.question.tag" var="question_tag_label"/>

<fmt:message bundle="${loc}" key="button.ask_question" var="ask_question_btn"/>

<!DOCTYPE html>
<html>
<head>
    <title>Like IT</title>
    <meta charset="utf-8"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/qquestions.js"></script>
</head>
<body>
<c:import url="${pageContext.request.contextPath}/WEB-INF/jsp/templates/header.jsp"/>


<div class="container">
    <h2>${all_questions_label}</h2>
    <div class="error-div row"></div>

    <c:if test="${sessionScope.user != null}">
        <form class="form-horizontal" action="Controller"  method="post">
            <input type="hidden" name="command" value="add-question">
            <div class="col-xs-9">
                <div class="form-group">
                    <label class="control-label col-xs-3" for="inputQuestionHeader">${question_header_label}:</label>
                    <div class="col-xs-9">
                        <input type="text" name="header" class="form-control" id="inputQuestionHeader" pattern="[a-zA-Zа-яА-Я\d\s-_]{1,100}" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-xs-3" for="inputQuestionText">${question_text_label}:</label>
                    <div class="col-xs-9">
                        <input type="text" name="text" class="form-control" id="inputQuestionText" pattern=".{1,1000}" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-xs-3" for="inputQuestionTag">${question_tag_label}:</label>
                    <div class="col-xs-9">
                        <input type="text" name="tag" class="form-control" id="inputQuestionTag" pattern="[\w\d\s]{1,30}">
                    </div>
                </div>

                <br />
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-9">
                        <input type="submit" class="btn btn-primary" value="${ask_question_btn}">
                    </div>
                </div>
            </div>
        </form>
    </c:if>

    <div id="questions-list" class="row list-group">
    </div>
</div>


</body>
</html>