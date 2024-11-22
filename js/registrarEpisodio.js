// Obtener el valor de `serieID` de la URL
let params = new URLSearchParams(window.location.search);
let serieID = params.get('serieID');

// Verificar el valor de serieID
console.log('ID de la serie:', serieID);

let boton = document.getElementById("btregistrar");

boton.addEventListener("click", async (evento) => {
    evento.preventDefault(); // Evitar el comportamiento predeterminado del botón

    // Verificar si el serieID es válido antes de llamar a registrarEpisodio
    if (!serieID) {
        alert("No se ha proporcionado un ID de serie válido.");
        return;
    }

    await registrarEpisodio(serieID); // Cambié la función a registrarEpisodio en lugar de registrarSerie
    window.location.href = `verEpisodios.html?serieID=${serieID}`;
});

let registrarEpisodio = async (serieID) => {
    // Recoger los datos del formulario
    let campos = {};
    campos.serieID = serieID;
    campos.episodeID = document.getElementById("episodeID").value;
    campos.titulo = document.getElementById("titulo").value.trim();
    campos.numEpisodio = parseInt(document.getElementById("numEpisodio").value.trim());
    campos.numTemporada = parseInt(document.getElementById("numTemporada").value.trim());
    campos.photoURL = document.getElementById("photoURL").value.trim();

    // Validar que los campos requeridos no estén vacíos
    if (!campos.titulo || isNaN(campos.numEpisodio) || isNaN(campos.numTemporada) || !campos.photoURL) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/series/${encodeURIComponent(serieID)}/episodes`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(campos) // Enviar los datos en formato JSON
        });

        if (peticion.ok) {
            const resultado = await peticion.json();
            alert("Episodio registrado con éxito.");
        } else {
            const error = await peticion.json();
            console.error("Error al registrar el episodio:", error);
            alert("Hubo un error al intentar registrar el episodio: " + (error.message || JSON.stringify(error)));
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
    }
};


