// Obtener el userID desde sessionStorage
const userID = sessionStorage.getItem("userID");
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

document.addEventListener('DOMContentLoaded', () => {
    const logo = document.querySelector('.logo'); // Seleccionar el logo de Ñefli

    // Añadir un evento onclick al logo
    if (logo) {
        logo.onclick = () => {
            window.location.href = 'paginaPrincipal.html'; // Redirigir a la página principal
        };
    }
});
  
// Verifica si el userID existe antes de hacer la petición
if (userID) {
    obtenerInformacionCuenta(userID); // Llama a la función para mostrar los datos
} else {
    alert("No se ha encontrado sesión activa");
}


document.getElementById("btn-buscar").addEventListener("click", () => {
    const titulo = document.getElementById("titulo").value.trim();
    if (titulo) {
        window.location.href = `resultadoBusqueda.html?titulo=${encodeURIComponent(titulo)}`;
    } else {
        alert("Por favor, introduce un título para buscar.");
    }
});

// Archivo: resultadoBusqueda.js

// Obtener el parámetro de búsqueda (título) desde la URL
const urlParams = new URLSearchParams(window.location.search);
const titulo = urlParams.get('titulo');


// Función principal para cargar y mostrar resultados
const cargarResultados = async () => {
    if (!titulo) {
        console.log("No se proporcionó un título. No se realizará la búsqueda.");
        return;
    }

    try {
        // Llamadas paralelas a las dos APIs
        const [respuestaPelicula, respuestaSerie] = await Promise.all([
            fetch(`http://localhost:8080/v1/content/films/title/${encodeURIComponent(titulo)}`),
            fetch(`http://localhost:8080/v1/content/series/title/${encodeURIComponent(titulo)}`)
        ]);

        let pelicula = null, serie = null;

        // Procesar respuesta de la película
        if (respuestaPelicula.status === 200) {
            pelicula = await respuestaPelicula.json();
        } else if (respuestaPelicula.status === 404) {
            console.log("No se encontró película con ese título.");
        } else {
            console.error("Error al buscar película:", await respuestaPelicula.json());
        }

        // Procesar respuesta de la serie
        if (respuestaSerie.status === 200) {
            serie = await respuestaSerie.json();
        } else if (respuestaSerie.status === 204) {
            console.log("No se encontró serie con ese título.");
        } else {
            console.error("Error al buscar serie:", await respuestaSerie.json());
        }

        // Mostrar resultados en la tabla
        mostrarEnTabla(pelicula, serie);

    } catch (error) {
        console.error("Error al realizar la búsqueda:", error);
        alert(`No se pudo realizar la búsqueda. Por favor, inténtalo más tarde: ${error.message}`);
    }
};

// Función para rellenar la tabla con los resultados
const mostrarEnTabla = (pelicula, serie) => {

    let tieneResultados = false;

    // Mostrar película si existe
    if (pelicula) {
        tieneResultados = true;

        const container = document.querySelector(".carousel-container-film");

        let contentHTML = "";
            contentHTML += `
                <a href="infoPelicula.html?filmID=${pelicula.filmID}" class="movie-card-link">
                    <div class="movie-card">
                        <img src="${pelicula.photoURL}" alt="${pelicula.title}">
                        <div class="movie-title">${pelicula.title}</div>
                    </div>
                </a>
            `;

        // Inserta las tarjetas en el contenedor
        container.innerHTML = contentHTML;
    }

    // Mostrar serie si existe
    if (serie) {
        tieneResultados = true;
        const container = document.querySelector(".carousel-container-film");

        let contentHTML = "";
            contentHTML += `
            <a href="infoSerie.html?serieID=${serie.serieID}" class="movie-card-link">
                <div class="movie-card">
                    <img src="${serie.photoURL}" alt="${serie.title}">
                    <div class="movie-title">${serie.title}</div>
                </div>
            </a>
            `;
        // Inserta las tarjetas en el contenedor
        container.innerHTML = contentHTML;
    }

    // Si no hay resultados
    if (!tieneResultados) {
        tablaPeliculas.innerHTML = `<div><strong>No se encontraron resultados para "${titulo}".</strong></div>`;
    }
};

// Cargar los resultados al iniciar la página
cargarResultados();
