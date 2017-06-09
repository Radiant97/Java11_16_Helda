<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="language" value="${not empty sessionScope.language ?
    sessionScope.language : pageContext.request.locale}"  />

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="localization" var="loc"/>

<fmt:message bundle="${loc}" key="label.main" var="main_label"/>
<fmt:message bundle="${loc}" key="label.questions" var="questions_label"/>
<fmt:message bundle="${loc}" key="label.tags" var="tags_label"/>
<fmt:message bundle="${loc}" key="label.users" var="users_label"/>
<fmt:message bundle="${loc}" key="label.logo" var="logo_label"/>
<fmt:message bundle="${loc}" key="label.search" var="search_label"/>
<fmt:message bundle="${loc}" key="label.language.ru" var="ru_label"/>
<fmt:message bundle="${loc}" key="label.language.en" var="en_label"/>

<fmt:message bundle="${loc}" key="button.sign_in" var="sign_in_btn"/>
<fmt:message bundle="${loc}" key="button.sign_up" var="sign_up_btn"/>
<fmt:message bundle="${loc}" key="button.sign_out" var="sign_out_btn"/>
<fmt:message bundle="${loc}" key="button.user_profile" var="user_profile_btn"/>

<header>
  <nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/index.jsp">${logo_label}</a>
      </div>

      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
          <li><a href="/index.jsp">${main_label}</a></li>
          <li><a href="${pageContext.request.contextPath}/questions">${questions_label}</a></li>
          <li><a href="${pageContext.request.contextPath}/tags">${tags_label}</a></li>
          <li><a href="#">${users_label}</a></li>
        </ul>

        <ul class="nav navbar-nav navbar-right">
          <c:choose>
            <c:when test="${sessionScope.user == null}">
              <li><a href="${pageContext.request.contextPath}/sign_in">${sign_in_btn}</a></li>
              <li><a href="${pageContext.request.contextPath}/sign_up">${sign_up_btn}</a></li>
            </c:when>
            <c:otherwise>
              <li><a href="/profile">${user_profile_btn}</a></li>
              <li><a href="Controller?command=sign-out">${sign_out_btn}</a></li>
            </c:otherwise>
          </c:choose>

          <li>
              <form action="Controller" method="post">
                  <input type="hidden" name="command" value="change-locale">
                  <select id="language" name="language" onchange="submit()">
                      <option value="en_US" ${language == 'en_US' ? 'selected' : ''}>${en_label}</option>
                      <option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>${ru_label}</option>
                  </select>
              </form>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</header>