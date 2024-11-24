// Obtener los parámetros de la URL (serieID y episodeID)
const urlParams = new URLSearchParams(window.location.search);
const serieID = urlParams.get('serieID');
const episodeID = urlParams.get('episodeID');

// Función para cargar los datos del episodio desde la API
let cargarDatosEpisodio = async () => {
    if (!serieID || !episodeID) {
        console.error("No se proporcionó serieID o episodeID.");
        alert("No se pudo encontrar el ID del episodio o serie.");
        return;
    }

    try {
        const respuesta = await fetch(`http://localhost:8080/v1/content/series/${encodeURIComponent(serieID)}/episodes/${encodeURIComponent(episodeID)}`);

        if (respuesta.ok) {
            const episodio = await respuesta.json();

            // Llenar los campos del formulario con los datos obtenidos
            document.getElementById("numEpisodio").value = episodio.numEpisodio;
            document.getElementById("numTemporada").value = episodio.numTemporada;
            document.getElementById("titulo").value = episodio.titulo;
            document.getElementById("photoURL").value = episodio.photoURL;
        } else {
            const error = await respuesta.json();
            alert("Hubo un error al intentar cargar los datos del episodio: " + error.message);
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo cargar la información del episodio.");
    }
};

// Función para editar episodio (envía los datos al servidor)
let editarEpisodio = async () => {
    if (!serieID || !episodeID) {
        console.error("No se proporcionó serieID o episodeID.");
        alert("No se pudo encontrar el ID del episodio o serie.");
        return;
    }

    // Recoger los datos del formulario
    let episodio = {};
    episodio.episodeID = episodeID;
    episodio.serieID = serieID;
    episodio.numEpisodio = parseInt(document.getElementById("numEpisodio").value.trim());
    episodio.numTemporada = parseInt(document.getElementById("numTemporada").value.trim());
    episodio.titulo = document.getElementById("titulo").value.trim();
    episodio.photoURL = document.getElementById("photoURL").value.trim();

    // Validar los campos requeridos
    if (isNaN(episodio.numEpisodio) || isNaN(episodio.numTemporada) || !episodio.titulo || !episodio.photoURL) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    // Depuración: Verificar los datos antes de enviarlos
    console.log("Datos del episodio a enviar:", JSON.stringify(episodio));

    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/series/${encodeURIComponent(serieID)}/episodes/${encodeURIComponent(episodeID)}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(episodio) // Enviar los datos en formato JSON
        });

        if (!peticion.ok) {
            const error = await peticion.json();
            console.error("Error al editar episodio:", error);
            alert("Hubo un error al intentar editar el episodio: " + (error.message || JSON.stringify(error))); // Mostrar el error detallado
        } else {
            alert("Episodio editado con éxito.");
            window.location.href = "verEpisodios.html?serieID=" + serieID; // Redirigir a la página de episodios
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("Hubo un error al intentar hacer la solicitud.");
    }
};

// Cargar los datos del episodio si serieID y episodeID existen
if (serieID && episodeID) {
    cargarDatosEpisodio();
} else {
    console.error('No se proporcionó un serieID o episodeID');
}

// Evento para guardar los cambios
document.getElementById("btn-guardarEpisodio").addEventListener("click", evento => {
    editarEpisodio();
});


