<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../constants/addDonationHeader.jsp"/>

<section id="donations" class="form--steps">
    <div class="form--steps-container">
        <div class="form--steps-counter">
            <div class="form--steps-counter">Twoje darowizny</div>

            <table style="width: 70%">
                <thead>
                <tr>
                    <th>Fundacja</th>
                    <th>Data utworzenia</th>
                    <th>Status darowizny</th>
                    <th>Data odebrania</th>
                    <th>Szczegóły</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${donations}" var="donation">
                    <tr>
                        <th>
                                ${donation.institution.name}<br>
                                ${donation.institution.description}
                        </th>
                        <th>${donation.created}</th>
                        <th>
                            <c:if test="${donation.pickedUp == false}">
                                Nieodebrane
                            </c:if>
                            <c:if test="${donation.pickedUp == true}">
                                Odebrane
                            </c:if>
                        </th>
                        <th>${donation.pickUpDate}<br>
                            ${donation.pickUpTime}
                        </th>
                        <th>
                            <a class="table-link" href="/donation/donation_details/${donation.id}/#summary">Szczegóły donacji</a>
                        </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/resources/js/donationForm.js"></script>
<jsp:include page="../constants/footer.jsp"/>
