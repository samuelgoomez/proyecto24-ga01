// Obtener el parámetro de la URL (filmID)
const urlParams = new URLSearchParams(window.location.search);
const filmID = urlParams.get('filmID');

// Función para cargar los datos de la película desde la API
let cargarDatosPelicula = async () => {
    if (!filmID) {
        console.error("No se proporcionó un filmID.");
        alert("No se pudo encontrar el ID de la película.");
        return;
    }

    try {
        const respuesta = await fetch(`http://localhost:8080/v1/content/films/${encodeURIComponent(filmID)}`);

        if (respuesta.ok) {
            const pelicula = await respuesta.json();

            // Llenar los campos del formulario con los datos obtenidos
            document.getElementById("title").value = pelicula.title;
            document.getElementById("genreID").value = pelicula.genreID;
            document.getElementById("releaseYear").value = pelicula.releaseYear;
            document.getElementById("duration").value = pelicula.duration;
            document.getElementById("description").value = pelicula.description;
            document.getElementById("photoURL").value = pelicula.photoURL;
            document.getElementById("arrayActors").value = pelicula.arrayActors.join(", "); // Para mostrar como string
        } else {
            const error = await respuesta.json();
            alert("Hubo un error al intentar cargar los datos de la película: " + error.message);
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo cargar la información de la película.");
    }
};

// Función para editar película (envía los datos al servidor)
let editarPelicula = async () => {
    if (!filmID) {
        console.error("No se proporcionó un filmID.");
        alert("No se pudo encontrar el ID de la película.");
        return;
    }

    // Recoger los datos del formulario
    let pelicula = {}
        pelicula.filmID = filmID;
        pelicula.title = document.getElementById("title").value.trim(),
        pelicula.genreID = parseInt(document.getElementById("genreID").value),
        pelicula.releaseYear = parseInt(document.getElementById("releaseYear").value),
        pelicula.duration = parseInt(document.getElementById("duration").value),
        pelicula.description = document.getElementById("description").value.trim(),
        pelicula.photoURL = document.getElementById("photoURL").value.trim(),
        pelicula.arrayActors = convertirStringAArrayDeNumeros(document.getElementById("arrayActors").value)

    // Validar los campos requeridos
    if (!pelicula.title || !pelicula.description || isNaN(pelicula.genreID) || isNaN(pelicula.releaseYear) || isNaN(pelicula.duration)) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    if (!pelicula.arrayActors.length) {
        alert("Por favor, ingresa al menos un actor.");
        return;
    }

    console.log(filmID);
    console.log("Datos de la película a enviar:", pelicula);

    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/films/${encodeURIComponent(pelicula.filmID)}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(pelicula)
        });

        if (!peticion.ok) {
            const error = await peticion.json();
            console.error("Error al editar película:", error);
            alert("Hubo un error al intentar editar la película: " + (error.message || JSON.stringify(error))); // Mostrar el error detallado
        } else {
            alert("Película editada con éxito.");
            window.location.href = "paginaPrincipal.html";
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("Hubo un error al intentar hacer la solicitud.");
    }
};

// Cargar los datos de la película si filmID existe
if (filmID) {
    cargarDatosPelicula();
} else {
    console.error('No se proporcionó un filmID');
}

// Evento para guardar los cambios
document.getElementById("btn-guardarPelicula").addEventListener("click", evento => {
    editarPelicula();
});

// Función para convertir el string de actores en un array de números
function convertirStringAArrayDeNumeros(str) {
    // Separa el string en un array usando coma como delimitador y luego convierte cada elemento en un número
    return str.split(',').map(num => parseInt(num.trim())).filter(num => !isNaN(num)); 
}
