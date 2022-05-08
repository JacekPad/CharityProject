<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../constants/addDonationHeader.jsp"/>

<section id="summary" class="form--steps">
    <div class="form--steps-container">
        <form:form modelAttribute="donation" method="post">
        <div class="active">
            <h3>Podsumowanie Twojej darowizny</h3>
            <div class="summary">
                <div class="form-section">
                    <ul>
                        <li>
                            <span class="summary--text">
                                Utworzono: ${donation.created}
                            </span>
                        </li>
                    </ul>
                    <h4>Oddajesz:</h4>
                    <ul>
                        <li>
                            <span class="icon icon-bag"></span>
                            <span id="summaryQuantity" class="summary--text">
                                <c:forEach items="${donation.categories}" var="category">
                                    ${category.name},
                                </c:forEach>
                                ${donation.quantity}
                                    <c:if test="${donation.quantity == 1}">
                                        worek
                                    </c:if>
                                    <c:if test="${donation.quantity > 1 && donation.quantity < 5}">
                                        worki
                                    </c:if>
                                    <c:if test="${donation.quantity >= 5}">
                                        worków
                                    </c:if>
                            </span>
                        </li>
                        <li>
                            <span class="icon icon-hand"></span>
                            <span id="summaryInstitution"
                                  class="summary--text">Dla fundacji - ${donation.institution.name}</span>
                        </li>
                    </ul>
                </div>
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru:</h4>
                        <ul>
                            <li id="summaryStreet">${donation.street}</li>
                            <li id="summaryCity">${donation.city}</li>
                            <li id="summaryZipCode">${donation.zipCode}</li>
                            <li id="summaryPhone">${donation.phoneNumber}</li>
                        </ul>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru:</h4>
                        <ul>
                            <li id="summaryDate">${donation.pickUpDate}</li>
                            <li id="summaryTime">${donation.pickUpTime}</li>
                            <li id="summaryComment">${donation.pickUpComment}</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="form-group form-group--buttons">
                <a class="btn" href="/donation/show_list/#donations">Powrót</a>
            </div>
        </div>
    </div>
    </form:form>
</section>
<jsp:include page="../constants/footer.jsp"/>
