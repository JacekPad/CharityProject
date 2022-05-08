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
                <th>Email</th>
                <th>Imie</th>
                <th>Nazwisko</th>
                <th>Stworzony</th>
                <th>Aktywny</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <th>${user.id}</th>
                    <th>${user.email}</th>
                    <th>${user.name}</th>
                    <th>${user.surname}</th>
                    <th>${user.created}</th>
                    <th>
                        <c:if test="${user.enabled == 1}">
                            Aktywny
                        </c:if>
                        <c:if test="${user.enabled == 0}">
                            Nieaktywny
                        </c:if>
                    </th>
                    <th style="padding: 0">
                        <a class="table-link" href="/admin/edit_user/${user.id}">Edytuj</a>
                        <c:if test="${user.enabled == 1}">
                        <a class="table-link" href="/admin/block_user/${user.id}">Zablokuj</a>
                        </c:if>
                        <c:if test="${user.enabled == 0}">
                            <a class="table-link" href="/admin/unblock_user/${user.id}">Odblokuj</a>
                        </c:if>
                        <a class="table-link" href="/admin/delete_user/${user.id}">Usuń</a>
                    </th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<jsp:include page="../constants/footer.jsp"/>