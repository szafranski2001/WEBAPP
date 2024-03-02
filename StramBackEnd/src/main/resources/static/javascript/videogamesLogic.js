window.addEventListener("DOMContentLoaded", function() {
    let favorite_btns= document.getElementsByClassName("videogame-data");

    favorite_btns.forEach(function (button) {
        button.addEventListener('click',function () {
            var videogameId=button.getAttribute("data-videogame");
        })
    })
});