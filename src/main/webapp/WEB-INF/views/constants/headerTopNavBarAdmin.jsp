<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="container container--70">
    <ul class="nav--actions">
        <sec:authorize access="isAuthenticated()">
            <li class="logged-user">
                Witaj <sec:authentication property="principal.username"/>
                <ul class="dropdown">
                    <li><a href="#">Profil</a></li>
                    <li><a href="${pageContext.request.contextPath}/donation/show_list/#donations">Moje zbiórki</a></li>
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><a href="${pageContext.request.contextPath}/admin/dashboard">Tryb administratora</a></li>
                    </sec:authorize>
                    <li><a href="#" onclick="document.logoutForm.submit()">Wyloguj</a></li>
                    <li>
                        <form name="logoutForm" method="post" action="<c:url value="/logout"/>">
                            <input class="dropdown-item" type="submit" value="Logout" hidden>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </li>
                </ul>
            </li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <li><a href="${pageContext.request.contextPath}/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="${pageContext.request.contextPath}/registration" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </sec:authorize>
    </ul>
    <ul>
        <li><a href="${pageContext.request.contextPath}/" class="btn btn--without-border active">Strona główna</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/user_list" class="btn btn--without-border">Lista użytkowników</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/admin_list" class="btn btn--without-border">Lista administratorów</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/institution_list" class="btn btn--without-border">Lista fundacji</a></li>
    </ul>
</nav>
