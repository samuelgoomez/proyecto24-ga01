let boton = document.getElementById("btregistrar");

boton.addEventListener("click", evento =>{
    registrarPelicula();
    window.location.href = `paginaPrincipal.html`;
});

function convertirStringAArrayDeNumeros(str) {
    // Separa el string en un array usando un delimitador, por ejemplo, una coma
    // Luego convierte cada elemento en un número
    return str.split(',').map(num => parseFloat(num.trim())).filter(num => !isNaN(num)); 
}

let registrarPelicula = async()=>{

    
    let campos = {};
    campos.filmID = document.getElementById("filmID").value;
    campos.title = document.getElementById("title").value;
    campos.genreID = document.getElementById("genreID").value;
    campos.releaseYear = document.getElementById("releaseYear").value;
    campos.duration = document.getElementById("duration").value;
    campos.description = document.getElementById("description").value;
    campos.photoURL = document.getElementById("photoURL").value;
    campos.arrayActors = convertirStringAArrayDeNumeros(document.getElementById("arrayActors").value);

    console.log(campos.arrayActors);

    const peticion = await fetch("http://localhost:8080/v1/content/films",
    {   method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(campos)  
    });

}