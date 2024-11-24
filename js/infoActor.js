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

// Función para obtener la información del actor
const obtenerInformacionActor = async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const actorID = urlParams.get("actorID"); // Obtiene el parámetro filmID de la URL
    try {
        const response = await fetch(`http://localhost:8080/v1/content/actors/${actorID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error al obtener la información del actor.');
        }

        const actor = await response.json();

        // Mostrar la información del actor en el HTML
        const nameElem = document.querySelector(".movie-title");
        const birthdayElem = document.querySelector(".movie-genre");
        const imageElem = document.querySelector(".movie-image img");

        nameElem.textContent = actor.name;
        // Formatear la fecha de nacimiento para mostrar solo la fecha
        const birthDate = actor.birthdayDate ? new Date(actor.birthdayDate).toISOString().split('T')[0] : 'Desconocido';
        birthdayElem.textContent = `Cumpleaños: ${birthDate || 'Desconocido'}`;
        imageElem.src = actor.photoURL || 'https://via.placeholder.com/300x450';

        // Llamar a las funciones para obtener y mostrar películas y series
        obtenerPeliculasActor(actorID);
        obtenerSeriesActor(actorID);

        document.getElementById("edit-button").addEventListener("click", () => editarActor(actor.actorID));
        document.getElementById("delete-button").addEventListener("click", () => eliminarActor(actor.actorID));

    } catch (error) {
        console.error('Error al obtener la información del actor:', error);
        alert(`No se pudo cargar la información del actor: ${error.message}`);
    }
};

// Función para obtener las películas del actor
const obtenerPeliculasActor = async (actorID) => {
    try {
        const response = await fetch(`http://localhost:8080/v1/content/films/actor/${actorID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            return;
        }

        const responseText = await response.text(); // Obtener texto en lugar de JSON directamente
        if (!responseText) {
            console.warn('La respuesta del servidor está vacía.');
            return;
        }

        const peliculas = JSON.parse(responseText);

        // Mostrar las películas en el HTML
        const filmsElem = document.querySelector(".movie-films");
        filmsElem.innerHTML = `<strong>Películas:</strong> ${peliculas.map(p => p.title).join(", ") || 'Ninguna'}`;
    } catch (error) {
        console.error('Error al obtener las películas del actor:', error);
        alert(`No se pudieron cargar las películas del actor: ${error.message}`);
    }
};

// Función para obtener las series del actor
const obtenerSeriesActor = async (actorID) => {
    try {
        const response = await fetch(`http://localhost:8080/v1/content/series/actor/${actorID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            return;
        }

        const responseText = await response.text(); // Obtener texto en lugar de JSON directamente
        if (!responseText) {
            console.warn('La respuesta del servidor está vacía.');
            return;
        }

        const series = JSON.parse(responseText);

        // Mostrar las series en el HTML
        const seriesElem = document.querySelector(".movie-series");
        seriesElem.innerHTML = `<strong>Series:</strong> ${series.map(s => s.title).join(", ") || 'Ninguna'}`;
    } catch (error) {
        console.error('Error al obtener las series del actor:', error);
        alert(`No se pudieron cargar las series del actor: ${error.message}`);
    }
};

window.onload = function() {
    obtenerInformacionActor();
}

let editarActor = (actorID) => {
    window.location.href = `editarActor.html?actorID=${actorID}`;
};

let eliminarActor = (actorID) => {
    window.location.href = `eliminarActor.html?actorID=${actorID}`;
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
