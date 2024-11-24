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

// Obtener el parámetro de la URL (serieID)
const urlParams = new URLSearchParams(window.location.search);
const serieID = urlParams.get('serieID');



// Función para obtener los episodios de una serie
let obtenerEpisodios = async (serieID) => {
    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/series/${serieID}/episodes`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) {
            throw new Error('Error al obtener los episodios');
        }

        const episodios = await peticion.json();
        console.log('Episodios:', episodios);
        
        // Generar HTML para mostrar los episodios
        let contenidoEpisodios = '';
        episodios.forEach(episodio => {
            contenidoEpisodios += `
                <tr>
                    <td>${episodio.numEpisodio}</td>
                    <td>${episodio.numTemporada}</td>
                    <td>${episodio.titulo}</td>
                    <td><img src="${episodio.photoURL}" alt="Foto del episodio" width="100" height="150" /></td>
                    <td>
                        <!-- Botón de Editar -->
                        <button class="btn-editarPelicula" onclick="editarEpisodio(${serieID}, ${episodio.episodeID})">Editar</button>
                        <!-- Botón de Eliminar -->
                        <button class="btn-eliminarPelicula" onclick="eliminarEpisodio(${serieID}, ${episodio.episodeID})">Eliminar</button>
                    </td>
                </tr>
            `;
        });

        // Insertar los episodios en el HTML
        document.querySelector("#tablaContentEpisodes tbody").innerHTML = contenidoEpisodios;

    } catch (error) {
        console.error('Error al obtener episodios:', error);
        alert('No se pudo cargar la lista de episodios');
    }
};

if (serieID) {
    console.log('Serie ID:', serieID);
    // Llamar a una función que obtenga los episodios de la serie usando este ID
    obtenerEpisodios(serieID);
} else {
    console.error('No se proporcionó un serieID');
}

// Función para redirigir a la página de eliminación
let eliminarEpisodio = (serieID, episodeID) => {
    // Redirige a la página de eliminación con ambos parámetros en la URL
    window.location.href = `eliminarEpisodio.html?serieID=${serieID}&episodeID=${episodeID}`;
};

let editarEpisodio = (serieID, episodeID) => {
    // Redirige a la página de eliminación con ambos parámetros en la URL
    window.location.href = `editarEpisodio.html?serieID=${serieID}&episodeID=${episodeID}`;
};

// Asignar el evento click al botón de registrar episodio
document.getElementById("btn-registrarEpisodio").addEventListener("click", function() {
    registrarEpisodio(serieID);
});

// Función que redirige a registrarEpisodio.html con el `serieID` en la URL
let registrarEpisodio = (serieID) => {
    // Redirige a la página registrarEpisodio.html con el `serieID` como parámetro en la URL
    window.location.href = `registrarEpisodio.html?serieID=${serieID}`;
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