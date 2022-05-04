<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="container container--70">
    <ul class="nav--actions">
        <li><a href="${pageContext.request.contextPath}/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="${pageContext.request.contextPath}/registration" class="btn btn--small btn--highlighted">Załóż konto</a></li>
    </ul>

    <ul>
        <li><a href="${pageContext.request.contextPath}/" class="btn btn--without-border active">Start</a></li>
        <li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#about" class="btn btn--without-border">O nas</a></li>
        <li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>
