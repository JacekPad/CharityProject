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

<header class="header">
    <jsp:include page="../constants/headerTopNavBarAdmin.jsp"/>
</header>

<section class="help">
    <div style="width: 100%; padding: 3em">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nazwa instytucji</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${institutions}" var="institution">
                <tr>
                    <th>${institution.id}</th>
                    <th>${institution.name}</th>
                    <th style="padding: 0">
                        <a class="table-link" href="/admin/edit_institution/${institution.id}">Edytuj</a>
                        <a class="table-link" href="/admin/delete_institution/${institution.id}">Usuń</a>
                    </th>
                </tr>
            </c:forEach>
            <tr>
                <th colspan="3">
                    <a class="table-link" href="${pageContext.request.contextPath}/admin/add_institution">Dodaj nową instytucję</a>
                </th>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="../constants/footer.jsp"/>