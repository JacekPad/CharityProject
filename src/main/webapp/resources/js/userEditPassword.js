const changePasswordDiv = document.getElementById("passwordChangeDiv");
const passwordWindow1 = document.getElementById("password1");
const passwordWindow2 = document.getElementById("password2");

function passwordCheckbox(checkbox){
    if (checkbox.checked) {
        changePasswordDiv.classList.remove("hidden")
    } else {
        changePasswordDiv.classList.add("hidden")
        passwordWindow1.value = "";
        passwordWindow2.value = "";
    }
}