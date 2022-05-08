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
    <jsp:include page="../constants/headerTopNavBar.jsp"/>
</header>

<section class="login-page">
    <h2>Zmień swoje dane</h2>
    <form:form modelAttribute="fakeUser" method="post">
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
    <div style="width: 100%">
    <h3>
            <label>
            Zmień hasło?
            <input id="passwordChangeBox" type="checkbox" onchange="passwordCheckbox(this)">
                <form:errors path="password"/>
            </label>
        </h3>
    </div>
        <div id="passwordChangeDiv" class="hidden">
            <div class="form-group">
                <form:input id="password1" type="password" path="password" placeholder="Hasło"/>
            </div>
            <div class="form-group">
                <input id="password2" type="password" name="password2" placeholder="Powtórz hasło"/>
            </div>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zmień dane</button>
        </div>
        <form:hidden path="id"/>
    </form:form>
</section>
<script src="<c:url value="/resources/js/userEditPassword.js"/>"></script>
<jsp:include page="../constants/footer.jsp"/>




