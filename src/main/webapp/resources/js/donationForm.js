//summary window
const summaryQuantity = document.getElementById("summaryQuantity");
const summaryInstitution = document.getElementById("summaryInstitution");
const summaryStreet = document.getElementById("summaryStreet");
const summaryCity = document.getElementById("summaryCity");
const summaryZipCode = document.getElementById("summaryZipCode");
const summaryPhone = document.getElementById("summaryPhone");
const summaryDate = document.getElementById("summaryDate");
const summaryTime = document.getElementById("summaryTime");
const summaryComment = document.getElementById("summaryComment");

// buttons
const institutionRadioButton = document.querySelectorAll(".radioDonation");
const summaryButton = document.getElementById("summaryButton");

// summary institution name
institutionRadioButton.forEach(button => {
    button.addEventListener("click", event => {
        let label = event.target.parentElement;
        let description = label.querySelector(".description").querySelector(".title").innerHTML;

        summaryButton.addEventListener("click", event2 => {
            //quantity summary
            let quantity = checkQuantity();
            if (quantity == 1) {
                summaryQuantity.innerHTML = quantity + " worek";
            } else if (quantity > 1 && quantity < 5) {
                summaryQuantity.innerHTML = quantity + " worki";
            } else {
                summaryQuantity.innerHTML = quantity + " worków";
            }
            //Institution summary
            summaryInstitution.innerHTML = "Dla: " + description;

        //    Address check
            summaryStreet.innerHTML = checkStreet();
            summaryCity.innerHTML = checkCity();
            summaryZipCode.innerHTML = checkZipCode();
            summaryPhone.innerHTML = checkPhone();
        //    Delivery check
            summaryDate.innerHTML = checkDate();
            summaryTime.innerHTML = checkTime();
            summaryComment.innerHTML = checkComment();
        })

    })
})

function checkQuantity() {
    return document.getElementById("quantity").value
}

function checkStreet() {
    return document.getElementById("street").value;
}

function checkCity() {
    return document.getElementById("city").value;
}

function checkZipCode() {
    return document.getElementById("zipCode").value;
}

function checkPhone() {
    return document.getElementById("phone").value;
}

function checkDate() {
    return document.getElementById("date").value;
}

function checkTime() {
    return document.getElementById("time").value;
}

function checkComment() {
    return document.getElementById("comment").value;
}