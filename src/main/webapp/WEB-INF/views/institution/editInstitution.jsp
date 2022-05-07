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
    <h2>Załóż konto</h2>
    <form:form modelAttribute="institution" action="/admin/edit_institution" method="post">
        <div class="form-group">
            <form:input path="name" placeholder="Nazwa instytucji"/>
            <form:errors path="name"/>
        </div>
        <div class="form-group">
            <form:input path="description" placeholder="Opis instytucji"/>
            <form:errors path="description"/>
        </div>
        <div class="form-group form-group--buttons">
            <a href="${pageContext.request.contextPath}/admin/institution_list" class="btn btn--without-border">Anuluj edycje</a>
            <button class="btn" type="submit">Edytuj instytucje</button>
        </div>
        <form:hidden path="id"/>
    </form:form>
</section>

<jsp:include page="../constants/footer.jsp"/>




