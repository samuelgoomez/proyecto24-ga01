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
  
// Verifica si el userID existe antes de hacer la petición
if (userID) {
    obtenerInformacionCuenta(userID); // Llama a la función para mostrar los datos
} else {
    alert("No se ha encontrado sesión activa");
}

window.onload = function() {
    listarPeliculas();
    listarSeries();
    listarActores();
}

// Función para listar películas
let listarPeliculas = async () => {
    try {
        const response = await fetch("http://localhost:8080/v1/content/films", {
            method: "GET",
            headers: {
                Accept: "application/json",
                "Content-Type": "application/json",
            },
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
        console.error("Error al listar las películas:", error);
        alert("No se pudo cargar la lista de películas.");
    }
};

// Función para listar series
let listarSeries = async () => {
    try {
        const peticion = await fetch("http://localhost:8080/v1/content/series", {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) throw new Error("Error al obtener las películas");

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

let listarActores = async () => {
    try {
        const peticion = await fetch("http://localhost:8080/v1/content/actors", {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) throw new Error("Error al obtener las películas");

        const actores = await peticion.json();

        const container = document.querySelector(".carousel-container-actor");

        let contentHTML = "";

        actores.forEach((actor) => {
            contentHTML += `
            <a href="infoActor.html?actorID=${actor.actorID}" class="movie-card-link">
                <div class="movie-card">
                    <img src="${actor.photoURL}" alt="${actor.name}">
                    <div class="movie-title">${actor.name}</div>
                </div>
            </a>
            `;
        });

        // Inserta las tarjetas en el contenedor
        container.innerHTML = contentHTML;

    } catch (error) {
        console.error('Error al obtener los actores:', error);
        alert('No se pudo cargar la lista de actores');
    }
};



document.getElementById("btn-buscar").addEventListener("click", () => {
    const titulo = document.getElementById("titulo").value.trim();
    if (titulo) {
        window.location.href = `resultadoBusqueda.html?titulo=${encodeURIComponent(titulo)}`;
    } else {
        alert("Por favor, ingresa un título para buscar.");
    }
});

// Redirigir a una página de edición con el ID de la película
let editarPelicula = (filmID) => {
    window.location.href = `editarPelicula.html?filmID=${filmID}`;
};

// Redirigir a una página de edición con el ID de la serie
let editarSerie = (serieID) => {
    window.location.href = `editarSerie.html?serieID=${serieID}`;
};

// Redirigir a la lista de episodios de la serie seleccionada
let verEpisodios = (serieID) => {
    window.location.href = `verEpisodios.html?serieID=${serieID}`;
};

// Eliminar una película seleccionada
let eliminarPelicula = (filmID) => {
    window.location.href = `eliminarPelicula.html?filmID=${filmID}`;
};

// Eliminar una serie seleccionada
let eliminarSerie = (serieID) => {
    window.location.href = `eliminarSerie.html?serieID=${serieID}`;
};

// Funciones adicionales para registrar películas o series
let registrarPelicula = () => {
    window.location.href = `registrarPelicula.html`;
};

let registrarSerie = () => {
    window.location.href = `registrarSerie.html`;
};

let registrarActor = () => {
    window.location.href = `registrarActor.html`;
};

let editarActor = (actorID) => {
    window.location.href = `editarActor.html?actorID=${actorID}`;
};

let eliminarActor = (actorID) => {
    window.location.href = `eliminarActor.html?actorID=${actorID}`;
};

// Redirigir a una página de edición con el ID de la lista
let editarLista = (listID) => {
    window.location.href = `editarLista.html?listID=${listID}`;
};

// Eliminar una lista seleccionada
let eliminarLista = (listID) => {
    window.location.href = `eliminarLista.html?listID=${listID}`;
};

let reproductorPelicula = (filmID) => {
    window.location.href = `reproductorPelicula.html?filmID=${filmID}`;
};

let reproductorSerie = (serieID) => {
    window.location.href = `reproductorSerie.html?serieID=${serieID}`;
};


let listarPelicula = (filmID) => {
    if (!filmID) {
        alert("El ID de la película es inválido.");
        return;
    }
    window.location.href = `anadir_a_Lista.html?filmID=${filmID}`;
};

let listarSerie = (serieID) => {
    if (!serieID) {
        alert("El ID de la serie es inválido.");
        return;
    }
    window.location.href = `anadir_a_Lista.html?serieID=${serieID}`;
};


