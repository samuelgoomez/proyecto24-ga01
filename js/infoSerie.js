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
let obtenerInformacionSerie = async () => {
    // Obtener el filmID desde la URL de la página
    const urlParams = new URLSearchParams(window.location.search);
    const serieID = urlParams.get("serieID"); // Obtiene el parámetro filmID de la URL

    if (!serieID) {
        alert("No se ha encontrado el ID de la serie");
        return;
    }

    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/series/${serieID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) {
            throw new Error('Error al obtener la información de la serie');
        }

        const serie = await peticion.json();

        // Llamar a la función para obtener los nombres de los actores
        const nombresActores = await obtenerNombresActores(serie.arrayActors);

        // Llamar a la función para obtener el nombre del género
        const nombreGenero = obtenerNombreGenero(serie.genreID);

        // Acceder a los elementos mediante clase
        const titleElem = document.querySelector(".movie-title");
        const genreElem = document.querySelector(".movie-genre");
        const seasonsElem = document.querySelector(".movie-seasons");
        const yearElem = document.querySelector(".movie-year");
        const synopsisElem = document.querySelector(".movie-synopsis");
        const actorsElem = document.querySelector(".movie-actors");
        const imageElem = document.querySelector(".movie-image img");

        if (!titleElem || !genreElem || !yearElem  || !synopsisElem || !actorsElem || !imageElem || !seasonsElem) {
            console.error("No se encontraron los elementos en el DOM.");
            return;
        }

        // Insertar los detalles de la película en el HTML
        titleElem.textContent = serie.title;
        genreElem.textContent = `Género: ${nombreGenero}`;
        yearElem.textContent = `Año: ${serie.releaseYear}`;
        seasonsElem.textContent = `Temporadas: ${serie.seasons}`;
        synopsisElem.textContent = serie.description;
        actorsElem.innerHTML = `<strong>Actores:</strong> ${nombresActores.join(", ")}`;
        imageElem.src = serie.photoURL;

        document.getElementById("edit-button").addEventListener("click", () => editarSerie(serie.serieID));
        document.getElementById("delete-button").addEventListener("click", () => eliminarSerie(serie.serieID));
        document.getElementById("play-button").addEventListener("click", () => reproductorSerie(serie.serieID));
        document.getElementById("add-to-list-button").addEventListener("click", () => listarSerie(serie.serieID));
        document.getElementById("episodes-button").addEventListener("click", () => verEpisodios(serie.serieID));

    } catch (error) {
        console.error('Error al obtener la serie:', error);
        alert(`No se pudo cargar la información de la serie: ${error.message}`);
    }
};

// Llamada a la función cuando se cargue la página
window.onload = function() {
    obtenerInformacionSerie();
}

// Redirigir a una página de edición con el ID de la película
let editarSerie = (serieID) => {
    window.location.href = `editarSerie.html?serieID=${serieID}`;
};

// Eliminar una película seleccionada
let eliminarSerie = (serieID) => {
    window.location.href = `eliminarSerie.html?serieID=${serieID}`;
};

let reproductorSerie = (serieID) => {
    window.location.href = `reproductorSerie.html?serieID=${serieID}`;
};

let listarSerie = (serieID) => {
    if (!serieID) {                           
        alert("El ID de la serie es inválido.");
        return;
    }
    window.location.href = `anadir_a_Lista.html?serieID=${serieID}`;
};

// Redirigir a la lista de episodios de la serie seleccionada
let verEpisodios = (serieID) => {
    window.location.href = `verEpisodios.html?serieID=${serieID}`;
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

