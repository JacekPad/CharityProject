<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../constants/addDonationHeader.jsp"/>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <%--        TO DZIALA ALE NIE WYGLADA DOBRZE--%>

        <form:form modelAttribute="donation" method="post">
            <%--        <div data-step="1" class="active">--%>
            <%--            <h3>Zaznacz co chcesz oddać:</h3>--%>
            <%--            <c:forEach items="${categories}" var="category"> --%>
            <%--                <div class="form-group">--%>
            <%--                    <label>--%>
            <%--                        <span class="checkbox"></span>--%>
            <%--                        <form:checkbox path="categories" value="${category.id}"/>--%>
            <%--                        <span class="description">${category.name}</span>--%>
            <%--                    </label>--%>
            <%--                </div>--%>
            <%--            </c:forEach>--%>
            <%--            <div class="form-group form-group--buttons">--%>
            <%--                <button type="button" class="btn next-step">Dalej</button>--%>
            <%--            </div>--%>
            <%--        </div>--%>

            <%--        TO WYGLADA TAK JAK MA ALE NIE DZIALA--%>
        <div data-step="1" class="active">
            <h3>Zaznacz co chcesz oddać:</h3>
            <c:forEach items="${categories}" var="category">
                <div class="form-group form-group--checkbox">
                    <label>
                        <span class="checkbox"></span>
                        <form:checkbox path="categories" value="${category.id}"/>
                        <span class="description">${category.name}</span>
                    </label>
                </div>
            </c:forEach>
            <div class="form-group form-group--buttons">
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>

        <div data-step="2">
            <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>
            <div class="form-group form-group--inline">
                <label>
                    Liczba 60L worków:
                    <form:input path="quantity" type="number" min="1"/>
                </label>
            </div>
            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>
        <div data-step="3">
            <h3>Wybierz organizacje, której chcesz pomóc:</h3>
            <c:forEach items="${institutions}" var="institution">
                <div class="form-group form-group--checkbox">
                    <label>
                        <form:radiobutton path="institution" value="${institution.id}"/>
                        <span class="checkbox radio"></span>
                        <span class="description">
                        <div class="title">
                                ${institution.name}
                        </div>
                        <div class="subtitle">
                                ${institution.description}
                        </div>
                    </span>
                    </label>
                </div>
            </c:forEach>
            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>
        <div data-step="4">
            <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

            <div class="form-section form-section--columns">
                <div class="form-section--column">
                    <h4>Adres odbioru</h4>
                    <div class="form-group form-group--inline">
                        <label> Ulica <form:input path="street"/></label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Miasto <form:input path="city"/></label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Kod pocztowy: <form:input path="zipCode"/></label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Numer telefonu: <form:input path="phoneNumber"/></label>
                    </div>
                </div>

                <div class="form-section--column">
                    <h4> Termin odbioru </h4>

                    <div class="form-group form-group--inline">
                        <label> Data <form:input path="pickUpDate" type="date"/></label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Godzina <form:input path="pickUpTime" type="time" min="9:30:00" max="16:00:00"/></label>
                    </div>

                    <div class="form-group form-group--inline">
                        <label> Uwagi dla kuriera <form:textarea path="pickUpComment" rows="5"/></label>
                    </div>
                </div>
            </div>
            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="button" class="btn next-step">Dalej</button>
            </div>
        </div>

        <div data-step="5">
            <h3>Podsumowanie Twojej darowizny</h3>
            <div class="summary">
                <div class="form-section">

                <h4>Oddajesz:</h4>
                <ul>
                    <li>
                        <span class="icon icon-bag"></span>
                        <span class="summary--text">
                                Java script ?
                            </span>
                    </li>

                    <li>
                        <span class="icon icon-hand"></span>
                        <span class="summary--text">
                                Java script?
                            </span>
                    </li>
                </ul>
                </div>
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru:</h4>
                        <ul>
                            <li>Ulica JS?</li>
                            <li>Miasto JS?</li>
                            <li>ZIP CODE JS?</li>
                            <li>PHONE JS?</li>
                        </ul>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru:</h4>
                        <ul>
                            <li>DATA JS?</li>
                            <li>TIME JS?</li>
                            <li>COMMENTS JS?</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="form-group form-group--buttons">
                <button type="button" class="btn prev-step">Wstecz</button>
                <button type="submit" class="btn">Potwierdzam</button>
            </div>
        </div>
    </div>
    </form:form>
    </div>
</section>
<jsp:include page="../constants/footer.jsp"/>
