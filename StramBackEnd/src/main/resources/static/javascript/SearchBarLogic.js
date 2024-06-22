const videogameItems = document.getElementsByClassName('videogame-data');
document.addEventListener('DOMContentLoaded', function() {
    SearchBar = document.getElementById("searchBar");

    SearchBar.addEventListener("input", (e) => {
        let value = e.target.value.trim();
        let AtLeastOneVideogame=false;
        let placeHolder=document.getElementById("placeHolder");

        if(value.trim() !== "") {
            for (let i = 0; i < videogameItems.length; i++) {
                // Ottieni il nome del videogioco dall'attributo alt dell'immagine
                let videogameName = videogameItems[i].querySelector('img').getAttribute('alt').toLowerCase();

                // Verifica se il nome del videogioco include il testo della ricerca
                if (videogameName.includes(value.toLowerCase())) {
                    // Mostra l'elemento se corrisponde al criterio di ricerca
                    videogameItems[i].style.display = 'block';
                    AtLeastOneVideogame=true;
                } else {
                    // Nascondi l'elemento se non corrisponde al criterio di ricerca
                    videogameItems[i].style.display = 'none';
                }
            }
            if(!AtLeastOneVideogame)
                placeHolder.style.display="block";
            else{
                placeHolder.style.display="none";
            }

        }
        else{
            placeHolder.style.display="none";
            for (let i = 0; i < videogameItems.length; i++) {
                videogameItems[i].style.display = 'block';
            }
        }
    });
});

function ToTheTopPressed(){
    window.scrollTo({top:0,behavior:'smooth'})
}