// Obtener el parámetro de la URL (serieID)
const urlParams = new URLSearchParams(window.location.search);
const serieID = urlParams.get('serieID');

// Función para cargar los datos de la serie desde la API
let cargarDatosSerie = async () => {
    if (!serieID) {
        console.error("No se proporcionó un serieID.");
        alert("No se pudo encontrar el ID de la serie.");
        return;
    }

    try {
        const respuesta = await fetch(`http://localhost:8080/v1/content/series/${encodeURIComponent(serieID)}`);

        if (respuesta.ok) {
            const serie = await respuesta.json();

            // Llenar los campos del formulario con los datos obtenidos
            document.getElementById("title").value = serie.title;
            document.getElementById("seasons").value = serie.seasons;
            document.getElementById("releaseYear").value = serie.releaseYear;
            document.getElementById("genreID").value = serie.genreID;
            document.getElementById("description").value = serie.description;
            document.getElementById("photoURL").value = serie.photoURL;
            document.getElementById("arrayActors").value = serie.arrayActors.join(", "); // Para mostrar como string
        } else {
            const error = await respuesta.json();
            alert("Hubo un error al intentar cargar los datos de la serie: " + error.message);
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo cargar la información de la serie.");
    }
};

// Función para editar serie (envía los datos al servidor)
let editarSerie = async () => {
    if (!serieID) {
        console.error("No se proporcionó un serieID.");
        alert("No se pudo encontrar el ID de la serie.");
        return;
    }

    // Recoger los datos del formulario
    let serie = {};
        serie.serieID = serieID;
        serie.title = document.getElementById("title").value.trim(),
        serie.seasons = parseInt(document.getElementById("seasons").value),
        serie.releaseYear = parseInt(document.getElementById("releaseYear").value),
        serie.genreID = parseInt(document.getElementById("genreID").value),
        serie.description = document.getElementById("description").value.trim(),
        serie.photoURL = document.getElementById("photoURL").value.trim(),
        serie.arrayActors = convertirStringAArrayDeNumeros(document.getElementById("arrayActors").value)

    // Validar los campos requeridos
    if (!serie.title || !serie.description || isNaN(serie.genreID) || isNaN(serie.releaseYear) || isNaN(serie.seasons)) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    if (!serie.arrayActors.length) {
        alert("Por favor, ingresa al menos un actor.");
        return;
    }

    console.log(serieID);
    console.log("Datos de la serie a enviar:", serie);

    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/series/${encodeURIComponent(serie.serieID)}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(serie)
        });

        if (!peticion.ok) {
            const error = await peticion.json();
            console.error("Error al editar serie:", error);
            alert("Hubo un error al intentar editar la serie: " + (error.message || JSON.stringify(error))); // Mostrar el error detallado
        } else {
            alert("Serie editada con éxito.");
            window.location.href = "paginaPrincipal.html";
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("Hubo un error al intentar hacer la solicitud.");
    }
};

// Cargar los datos de la serie si serieID existe
if (serieID) {
    cargarDatosSerie();
} else {
    console.error('No se proporcionó un serieID');
}

// Evento para guardar los cambios
document.getElementById("btn-guardarSerie").addEventListener("click", evento => {
    editarSerie();
});

// Función para convertir el string de actores en un array de números
function convertirStringAArrayDeNumeros(str) {
    // Separa el string en un array usando coma como delimitador y luego convierte cada elemento en un número
    return str.split(',').map(num => parseInt(num.trim())).filter(num => !isNaN(num)); 
}
