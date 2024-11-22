const userID = sessionStorage.getItem("userID");
console.log("UserID:", userID);

function comprobarSesionUsuario() {
    const userID = sessionStorage.getItem("userID");  // Obtiene el userID desde sessionStorage

    if (userID) {
        // Si userID está presente, significa que hay un usuario en la sesión
        console.log("Usuario encontrado: " + userID);
        return true;  // Retorna verdadero si el usuario está en la sesión
    } else {
        // Si no se encuentra userID, significa que no hay usuario en la sesión
        console.log("No hay usuario en la sesión.");
        alert("No hay usuario en la sesión. Por favor, inicie sesión.");
        return false;  // Retorna falso si no hay usuario
    }
}

window.onload = function() {
    if (!comprobarSesionUsuario()) {
        console.error("Error: No hay un usuario autenticado.");
    }
};

document.addEventListener('DOMContentLoaded', () => {
    const logo = document.querySelector('.icono_vuelta'); // Seleccionar el logo de Ñefli

    // Añadir un evento onclick al logo
    if (logo) {
        logo.onclick = () => {
            window.location.href = 'paginaPrincipal.html'; // Redirigir a la página principal
        };
    }
});

// Obtener el parámetro de la URL (filmID)
const urlParams = new URLSearchParams(window.location.search);
const filmID = urlParams.get('filmID');

// Función para cargar y mostrar los datos de la película
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

            // Mostrar el título y la URL de la imagen en el HTML
            document.getElementById("titulo").textContent = pelicula.title;
            const imagen = document.getElementById("imagen");
            imagen.src = pelicula.photoURL;
            imagen.alt = `Poster de ${pelicula.title}`;
        } else {
            const error = await respuesta.json();
            alert("Hubo un error al intentar cargar los datos de la película: " + error.message);
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo cargar la información de la película.");
    }
};

// Cargar los datos de la película si filmID existe
if (filmID) {
    cargarDatosPelicula();
} else {
    console.error('No se proporcionó un filmID.');
}


