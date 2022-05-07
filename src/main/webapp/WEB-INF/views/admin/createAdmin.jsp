<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>
    <jsp:include page="../constants/headerTopNavBarAdmin.jsp"/>
</header>

<section class="login-page">
    <h2>Stwórz nowego administratora</h2>
    <form:form modelAttribute="user" method="post">
        <div class="form-group">
            <form:input path="email" placeholder="Email"/>
            <form:errors path="email"/>
        </div>
        <div class="form-group">
            <form:input path="name" placeholder="Imie"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:input path="surname" placeholder="Nazwisko"/>
            <form:errors path="surname"/>
        </div>
        <div class="form-group">
            <form:input type="password" path="password" placeholder="Hasło"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <input type="password" name="password2" placeholder="Powtórz hasło"/>
        </div>

        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/admin/admin_list" class="btn btn--without-border">Cofnij tworzenie</a>
            <button class="btn" type="submit">Stwórz nowego administratora</button>
        </div>
    </form:form>
</section>

<jsp:include page="../constants/footer.jsp"/>




