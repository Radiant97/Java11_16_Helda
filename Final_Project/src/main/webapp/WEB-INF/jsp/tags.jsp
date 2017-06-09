<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<c:set var="language" value="${not empty sessionScope.language ?
    sessionScope.language : pageContext.request.locale}"  />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization" var="loc"/>

<fmt:message bundle="${loc}" key="lable.site.name" var="site_name_label"/>
<fmt:message bundle="${loc}" key="label.tags.all" var="all_tags_label"/>
<fmt:message bundle="${loc}" key="label.tags" var="tags_label"/>
<fmt:message bundle="${loc}" key="button.tag.add" var="add_tag_btn"/>

<fmt:message bundle="${loc}" key="label.tag" var="tag_label"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>${tags_label} - ${site_name_label}</title>
    <meta charset="utf-8"/>

    <link href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/tag.js"></script>
</head>
<body>
    <c:import url="${pageContext.request.contextPath}/WEB-INF/jsp/templates/header.jsp"/>

    <div class="container">
        <h2 id="all-tags-label">${all_tags_label}</h2>
        <div class="error-div row"></div>

        <c:if test="${ctg:isadmin(sessionScope.get('role'))}">
            <form class="form-horizontal" action="javascript:void(null);" onsubmit="addTag()" method="post">
                <div class="col-xs-9">
                    <label class="control-label col-md-3" for="tagName">${tag_label}:</label>
                    <input type="text" name="name" class="form-control col-md-3" id="tagName" pattern="[a-zA-Zа-яА-Я\d\s-_]{1,30}" required>
                    <input type="submit" class="btn btn-primary col-md-3" value="${add_tag_btn}">
                </div>
            </form>
        </c:if>

        <div id="tags-list" class="row">
        </div>
    </div>
</body>
</html>