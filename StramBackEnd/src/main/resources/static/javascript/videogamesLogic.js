
function ButtonPressed(button) {
    let spanButton = button.getElementsByClassName("material-symbols-outlined");
    spanButton[0].classList.toggle("icon-outline");
    spanButton[0].classList.toggle("icon-fill");
}

//fare chiamata ajax per passare aggiunte e rimozioni dalle liste dei videogiochi