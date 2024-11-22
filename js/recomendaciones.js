const userID = sessionStorage.getItem("userID");

window.onload = function() {
    listarPeliculas();
    listarSeries();
}

// Función para listar películas
let listarPeliculas = async () => {
    try {
        const response = await fetch(`http://localhost:8081/views/recommendations/film/${userID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) throw new Error("Error al obtener las películas");

        const peliculas = await response.json();

        const container = document.querySelector(".carousel-container-film");

        let contentHTML = "";

        peliculas.forEach((pelicula) => {
            contentHTML += `
                <a href="infoPelicula.html?filmID=${pelicula.filmID}" class="movie-card-link">
                    <div class="movie-card">
                        <img src="${pelicula.photoURL}" alt="${pelicula.title}">
                        <div class="movie-title">${pelicula.title}</div>
                    </div>
                </a>
            `;
        });

        // Inserta las tarjetas en el contenedor
        container.innerHTML = contentHTML;
        
    } catch (error) {
        console.error('Error al obtener las películas:', error);
        alert(`No se pudo cargar la lista de películas: ${error.message}`);
    }
};

// Función para listar series
let listarSeries = async () => {
    try {
        const peticion = await fetch(`http://localhost:8081/views/recommendations/serie/${userID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        const peliculas = await peticion.json();

        const container = document.querySelector(".carousel-container-serie");

        let contentHTML = "";

        peliculas.forEach((pelicula) => {
            contentHTML += `
            <a href="infoSerie.html?serieID=${pelicula.serieID}" class="movie-card-link">
                <div class="movie-card">
                    <img src="${pelicula.photoURL}" alt="${pelicula.title}">
                    <div class="movie-title">${pelicula.title}</div>
                </div>
            </a>
            `;
        });

        // Inserta las tarjetas en el contenedor
        container.innerHTML = contentHTML;

    } catch (error) {
        console.error('Error al obtener las series:', error);
        alert('No se pudo cargar la lista de series');
    }
};