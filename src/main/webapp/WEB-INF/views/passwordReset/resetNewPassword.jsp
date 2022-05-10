<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<header class="header--form-page">
    <jsp:include page="../constants/headerTopNavBar.jsp"/>
    <div class="slogan container container--90">
        <h2>
            <div style="padding: 20px">
                Aby zresetować hasło, wprowadź swój adres email:
            </div>
            <div>
                <form:form modelAttribute="user" action="/reset_password" method="post">
                    <div style="width: 100%; padding-bottom: 20px">
                        <form:password  path="password" placeholder="Wprowadź nowe hasło"/>
                    </div>
                    <div style="width: 100%; padding-bottom: 20px">
                        <input type="password" name="password2" placeholder="Wprowadź ponownie nowe hasło">
                    </div>
                    <div style="justify-content: center; width: 100%">
                        <span>
                            <button class="btn" type="submit">Zresetuj hasło</button> <br>
                            <form:errors path="password"/>
                        </span>
                    </div>
                    <input type="hidden" name="tokenUUID" value="${tokenUUID}">
                </form:form>
            </div>
        </h2>
    </div>
</header>

<jsp:include page="../constants/footer.jsp"/>