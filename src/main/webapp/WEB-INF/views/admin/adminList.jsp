<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<header class="header">
    <jsp:include page="../constants/headerTopNavBarAdmin.jsp"/>
</header>

<section class="help">
    <div style="width: 100%; padding: 3em">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Email</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th>Stworzony</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${admins}" var="admin">
                <tr>
                    <th>${admin.id}</th>
                    <th>${admin.email}</th>
                    <th>${admin.name}</th>
                    <th>${admin.surname}</th>
                    <th>${admin.created}</th>

                    <th style="padding: 0">
                        <a class="table-link" href="/admin/edit_admin/${admin.id}">Edytuj</a>
                        <a class="table-link" href="/admin/delete_admin/${admin.id}">Usuń</a>
                    </th>
                </tr>
            </c:forEach>
            <tr>
                <th colspan="7">
                    <a class="table-link" href="${pageContext.request.contextPath}/admin/create_admin">Dodaj nowego administratora</a>
                </th>
            </tr>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="../constants/footer.jsp"/>