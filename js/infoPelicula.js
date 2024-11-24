// Obtener el userID desde sessionStorage
const userID = sessionStorage.getItem("userID");

console.log(userID);

const obtenerInformacionCuenta = async (userID) => {
    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });
  
        if (!response.ok) {
            throw new Error('Error al obtener la información del usuario');
        }
  
        const data = await response.json();
  
        // Mostrar los datos en el HTML
        document.getElementById("username-display").textContent = data.username;
  
        // Si necesitas ver el valor de username en la consola, usa data.username
        console.log(data.username);
  
    } catch (error) {
        console.error('Error obteniendo los datos:', error);
        alert('No se pudo cargar la información de la cuenta');
    }
};

// Verifica si el userID existe antes de hacer la petición
if (userID) {
    obtenerInformacionCuenta(userID); // Llama a la función para mostrar los datos
} else {
    alert("No se ha encontrado sesión activa");
}

// Función para obtener los nombres de los actores por sus IDs
let obtenerNombresActores = async (arrayIds) => {
    try {
        const actoresPromesas = arrayIds.map(id => 
            fetch(`http://localhost:8080/v1/content/actors/${id}`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            }).then(response => {
                if (!response.ok) {
                    throw new Error('Error al obtener el nombre del actor');
                }
                return response.json();
            })
        );

        const actoresDetalles = await Promise.all(actoresPromesas);
        return actoresDetalles.map(actor => actor.name); // Mapear a los nombres
    } catch (error) {
        console.error('Error obteniendo los nombres de los actores:', error);
        alert('No se pudo cargar los nombres de los actores');
        return []; // Retornar un array vacío como fallback
    }
};

// Función para obtener el nombre del género a partir de su ID
const obtenerNombreGenero = (genreID) => {
    switch (genreID) {
        case 1:
            return "Acción";
        case 2:
            return "Comedia";
        case 3:
            return "Drama";
        case 4:
            return "Terror";
        case 5:
            return "Misterio / Suspense";
        case 6:
            return "Infantil";
        default:
            return "Género desconocido"; // En caso de que el ID no coincida con ninguno
    }
};


// Función para obtener la información de la película y los nombres de los actores
let obtenerInformacionPelicula = async () => {
    // Obtener el filmID desde la URL de la página
    const urlParams = new URLSearchParams(window.location.search);
    const filmID = urlParams.get("filmID"); // Obtiene el parámetro filmID de la URL

    if (!filmID) {
        alert("No se ha encontrado el ID de la película");
        return;
    }

    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/films/${filmID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) {
            throw new Error('Error al obtener la información de la película');
        }

        const pelicula = await peticion.json();

        // Llamar a la función para obtener los nombres de los actores
        const nombresActores = await obtenerNombresActores(pelicula.arrayActors);

        // Llamar a la función para obtener el nombre del género
        const nombreGenero = obtenerNombreGenero(pelicula.genreID);

        // Acceder a los elementos mediante clase
        const titleElem = document.querySelector(".movie-title");
        const genreElem = document.querySelector(".movie-genre");
        const yearElem = document.querySelector(".movie-year");
        const durationElem = document.querySelector(".movie-duration");
        const synopsisElem = document.querySelector(".movie-synopsis");
        const actorsElem = document.querySelector(".movie-actors");
        const imageElem = document.querySelector(".movie-image img");

        if (!titleElem || !genreElem || !yearElem || !durationElem || !synopsisElem || !actorsElem || !imageElem) {
            console.error("No se encontraron los elementos en el DOM.");
            return;
        }

        // Insertar los detalles de la película en el HTML
        titleElem.textContent = pelicula.title;
        genreElem.textContent = `Género: ${nombreGenero}`;
        yearElem.textContent = `Año: ${pelicula.releaseYear}`;
        durationElem.textContent = `Duración: ${pelicula.duration} min`;
        synopsisElem.textContent = pelicula.description;
        actorsElem.innerHTML = `<strong>Actores:</strong> ${nombresActores.join(", ")}`;
        imageElem.src = pelicula.photoURL;

        document.getElementById("edit-button").addEventListener("click", () => editarPelicula(pelicula.filmID));
        document.getElementById("delete-button").addEventListener("click", () => eliminarPelicula(pelicula.filmID));
        document.getElementById("play-button").addEventListener("click", () => reproductorPelicula(pelicula.filmID));
        document.getElementById("add-to-list-button").addEventListener("click", () => listarPelicula(pelicula.filmID));

    } catch (error) {
        console.error('Error al obtener la película:', error);
        alert('No se pudo cargar la información de la película');
    }
};

// Llamada a la función cuando se cargue la página
window.onload = function() {
    obtenerInformacionPelicula();
}

// Redirigir a una página de edición con el ID de la película
let editarPelicula = (filmID) => {
    window.location.href = `editarPelicula.html?filmID=${filmID}`;
};

// Eliminar una película seleccionada
let eliminarPelicula = (filmID) => {
    window.location.href = `eliminarPelicula.html?filmID=${filmID}`;
};

let reproductorPelicula = (filmID) => {
    window.location.href = `reproductorPelicula.html?filmID=${filmID}`;
};

let listarPelicula = (filmID) => {
    if (!filmID) {                           
        alert("El ID de la película es inválido.");
        return;
    }
    window.location.href = `anadir_a_Lista.html?filmID=${filmID}`;
};

document.addEventListener('DOMContentLoaded', () => {
    const logo = document.querySelector('.logo'); // Seleccionar el logo de Ñefli

    // Añadir un evento onclick al logo
    if (logo) {
        logo.onclick = () => {
            window.location.href = 'paginaPrincipal.html'; // Redirigir a la página principal
        };
    }
});


