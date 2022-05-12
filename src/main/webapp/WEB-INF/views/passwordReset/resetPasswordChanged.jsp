<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <jsp:include page="../constants/headerTopNavBar.jsp"/>
    <div class="slogan container container--90">
        <h2>
            <div style="padding: 20px">
                Twoje hasło zostało zresetowane. Możesz teraz zalogować się używając nowego hasła.
            </div>
            <div style="padding: 20px">
                <a href="${pageContext.request.contextPath}/login" class="btn btn--large">Zaloguj się</a>
            </div>
        </h2>
    </div>
</header>

<jsp:include page="../constants/footer.jsp"/>