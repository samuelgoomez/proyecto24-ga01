let boton = document.getElementById("btregistrar");

boton.addEventListener("click", evento => {
    registrarSerie();
    window.location.href = `paginaPrincipal.html`;
});

function convertirStringAArrayDeNumeros(str) {
    // Separa el string en un array usando una coma
    // Luego convierte cada elemento en un número
    return str.split(',').map(num => parseFloat(num.trim())).filter(num => !isNaN(num));
}

let registrarSerie = async () => {
    // Recoger los datos del formulario
    let campos = {};
    campos.serieID = document.getElementById("serieID").value;
    campos.title = document.getElementById("title").value.trim();
    campos.genreID = parseInt(document.getElementById("genreID").value.trim());
    campos.releaseYear = parseInt(document.getElementById("releaseYear").value.trim());
    campos.seasons = parseInt(document.getElementById("seasons").value.trim());
    campos.description = document.getElementById("description").value.trim();
    campos.photoURL = document.getElementById("photoURL").value.trim();
    campos.arrayActors = convertirStringAArrayDeNumeros(document.getElementById("arrayActors").value.trim());

    // Validar que los campos requeridos no estén vacíos
    if (!campos.title || isNaN(campos.genreID) || isNaN(campos.releaseYear) || isNaN(campos.seasons) || !campos.description || !campos.photoURL || campos.arrayActors.length === 0) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    try {
        const peticion = await fetch("http://localhost:8080/v1/content/series", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(campos) // Enviar los datos en formato JSON
        });

        if (peticion.ok) {
            const resultado = await peticion.json();
            alert("Serie registrada con éxito.");
            // Puedes redirigir o hacer algo más después del registro exitoso
        } else {
            const error = await peticion.json();
            console.error("Error al registrar la serie:", error);
            alert("Hubo un error al intentar registrar la serie: " + (error.message || JSON.stringify(error)));
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("Hubo un error al intentar hacer la solicitud.");
    }
};
