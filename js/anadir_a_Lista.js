// Función para listar las listas de un usuario
const userID = sessionStorage.getItem("userID");
const urlParams = new URLSearchParams(window.location.search);
const filmID = urlParams.get('filmID');
const serieID = urlParams.get('serieID');
console.log(userID);
console.log(filmID);
console.log(serieID);

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
        document.getElementById("username-display2").textContent = data.username;
        
  
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
    listarListas();
}

// Función para obtener nombres de películas
const obtenerNombresPeliculas = async (films) => {
    try {
        if (!Array.isArray(films) || films.length === 0) return "No hay películas";

        const nombresPeliculas = await Promise.all(
            films.map(async (id) => {
                const response = await fetch(`http://localhost:8080/v1/content/films/${id}`, {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                });

                if (!response.ok) throw new Error(`No se pudo obtener la película con ID ${id}`);
                const data = await response.json();
                return data.title; // Asumiendo que el campo "name" contiene el nombre de la película
            })
        );

        return nombresPeliculas.join(", ");
    } catch (error) {
        console.error('Error al obtener nombres de películas:', error);
        return "Error al cargar películas";
    }
};

// Función para obtener nombres de series
const obtenerNombresSeries = async (series) => {
    try {
        if (!Array.isArray(series) || series.length === 0) return "No hay series";

        const nombresSeries = await Promise.all(
            series.map(async (id) => {
                const response = await fetch(`http://localhost:8080/v1/content/series/${id}`, {
                    method: 'GET',
                    headers: {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    }
                });

                if (!response.ok) throw new Error(`No se pudo obtener la serie con ID ${id}`);
                const data = await response.json();
                return data.title; // Asumiendo que el campo "name" contiene el nombre de la serie
            })
        );

        return nombresSeries.join(", ");
    } catch (error) {
        console.error('Error al obtener nombres de series:', error);
        return "Error al cargar series";
    }
};


// Función para listar las listas de un usuario
let listarListas = async () => {
    try {
        const peticion = await fetch(`http://localhost:8082/v1/user/user/${userID}/lists`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) {
            alert('No tienes listas creadas');
            return;
        }

        const listas = await peticion.json();
        console.log(listas);

        let content = "";
        for (let lista of listas) {
            // Obtener nombres de películas y series
            const nombresPeliculas = await obtenerNombresPeliculas(lista.films);
            const nombresSeries = await obtenerNombresSeries(lista.series);

            let listaCard = `
            <div class="list-card">
                <h3>${lista.name}</h3>
                <p><strong>Películas:</strong> ${nombresPeliculas}</p>
                <p><strong>Series:</strong> ${nombresSeries}</p>
                <p><strong>Creado el:</strong> ${new Date(lista.createdAt).toLocaleDateString()}</p>
                <!-- Botón de Añadir -->
                <button class="btn-anadir" onclick="anadirLista(${lista.listID})">Añadir</button>
            </div>
            `;
            content += listaCard;
        }

        document.getElementById("lista-dinamica").innerHTML = content;
    } catch (error) {
        console.error('Error al obtener las listas:', error);
    }
};

let anadirLista = async (listID) => {
    try {
        // Verifica si se está añadiendo una película o una serie
        let url = null;
        if (filmID) {
            url = `http://localhost:8082/v1/user/user/${userID}/list/${listID}/film/${filmID}`;
        } else if (serieID) {
            url = `http://localhost:8082/v1/user/user/${userID}/list/${listID}/serie/${serieID}`;
        } else {
            alert('Debes seleccionar una película o serie para agregarla a la lista');
            return; // Salimos si no hay un elemento seleccionado
        }

        // Realiza la petición
        const peticion = await fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        // Verifica si la petición fue exitosa
        if (!peticion.ok) {
            throw new Error('Error al añadir el elemento a la lista');
        }

        // Intenta obtener el contenido de la respuesta
        let resultado;
        try {
            const text = await peticion.text();
            // Si la respuesta no está vacía, intenta parsear como JSON
            resultado = text ? JSON.parse(text) : {};
        } catch (err) {
            console.warn('La respuesta no es JSON válido:', err);
            resultado = {}; // Si la respuesta no es JSON, se maneja como un objeto vacío
        }

        alert(`Elemento añadido correctamente a la lista: ${listID}`);
        console.log('Respuesta del servidor:', resultado);

        // Opcional: Actualizar las listas después de añadir
        listarListas();
    } catch (error) {
        console.error('Error al añadir el elemento a la lista:', error);
        alert('No se pudo añadir el elemento a la lista');
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

