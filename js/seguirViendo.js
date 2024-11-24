const userID = sessionStorage.getItem("userID");

window.onload = function() {
    verSeguirViendo();
}

let verSeguirViendo = async () => {
    try {
        const peticion = await fetch(`http://localhost:8081/views/visualization/${userID}/continue-watching`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) {
            alert("No tienes contenido para seguir viendo");
        }

        if (peticion.ok) {
        const historial = await peticion.json();

        console.log("Historial recibido:", historial);

        const filmIDs = historial
            .filter(h => h.filmID !== null && h.filmID !== 0)
            .map(h => h.filmID);
        const serieIDs = historial
            .filter(h => h.serieID !== null && h.serieID !== 0)
            .map(h => h.serieID);

        console.log("Film IDs:", filmIDs);
        console.log("Serie IDs:", serieIDs);

        // Llamar a las funciones para obtener la información de películas y series
        if (filmIDs.length > 0) {
            obtenerInformacionPeliculas(filmIDs);
        } else {
            console.log("No hay películas para mostrar");
        }

        if (serieIDs.length > 0) {
            obtenerInformacionSeries(serieIDs);
        } else {
            console.log("No hay series para mostrar");
        }
        }
        
    } catch (error) {
        console.error('Error al obtener las películas:', error);
        alert('No se pudo cargar la lista de películas');
    }
};

const obtenerInformacionPeliculas = async (filmIDs) => {
    try {
        for (const filmID of filmIDs) {
            const peticion = await fetch(`http://localhost:8080/v1/content/films/${filmID}`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            if (!peticion.ok) {
                throw new Error(`Error al obtener la información de la película con ID: ${filmID}`);
            }

            const pelicula = await peticion.json();
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
    } catch (error) {
        console.error('Error al obtener información de las películas:', error);
    }
};

// Función para obtener información de series y mostrarla en pantalla
const obtenerInformacionSeries = async (serieIDs) => {
    try {
        for (const serieID of serieIDs) {
            const peticion = await fetch(`http://localhost:8080/v1/content/series/${serieID}`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            if (!peticion.ok) {
                throw new Error(`Error al obtener la información de la serie con ID: ${serieID}`);
            }

            const pelicula = await peticion.json();
            const container = document.querySelector(".carousel-container-serie");

        let contentHTML = "";
            contentHTML += `
            <a href="infoSerie.html?serieID=${pelicula.serieID}" class="movie-card-link">
                <div class="movie-card">
                    <img src="${pelicula.photoURL}" alt="${pelicula.title}">
                    <div class="movie-title">${pelicula.title}</div>
                </div>
            </a>
            `;
        // Inserta las tarjetas en el contenedor
        container.innerHTML = contentHTML;
        }
    } catch (error) {
        console.error('Error al obtener información de las series:', error);
    }
};
