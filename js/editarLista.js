const userID = sessionStorage.getItem("userID"); // Obtener ID del usuario desde sessionStorage
const listID = new URLSearchParams(window.location.search).get("listID"); // Obtener ID de la lista de la URL
const filmsAct = [];
const seriesAct = [];
// Función para cargar la información de la lista (películas y series)
let createdAt = null; // Variable para guardar la fecha de creación

async function editarLista() {
    try {
        // Obtener películas y series
        const filmsResponse = await fetch(`http://localhost:8082/v1/user/user/${userID}/list/${listID}/films`);
        const seriesResponse = await fetch(`http://localhost:8082/v1/user/user/${userID}/list/${listID}/series`);

        if (!filmsResponse.ok || !seriesResponse.ok) {
            throw new Error('Error al obtener las películas o series');
        }

        const films = await filmsResponse.json();
        const series = await seriesResponse.json();

        // Obtener detalles de la lista (nombre y createdAt)
        const listResponse = await fetch(`http://localhost:8082/v1/user/user/${userID}/list/${listID}`);
        if (!listResponse.ok) {
            throw new Error('Error al obtener la información de la lista');
        }

        const listData = await listResponse.json();
        createdAt = listData.createdAt; // Guardar createdAt

        // Actualizar el campo de texto con el nombre de la lista
        document.getElementById("name").value = listData.name; // Mostrar el nombre actual de la lista

        // Actualizar las listas de elementos que permanecen
        filmsAct.length = 0; // Limpiar y actualizar películas actuales
        seriesAct.length = 0; // Limpiar y actualizar series actuales

        films.forEach(film => {
            filmsAct.push(film); // Agregar a filmsAct
        });

        series.forEach(serie => {
            seriesAct.push(serie); // Agregar a seriesAct
        });

        // Mostrar películas y series en la lista con botones de eliminación
        let peliculasContent = "";
        films.forEach(film => {
            peliculasContent += `
            <li>${film.title} 
                <button class="delete-btn" onclick="eliminarElemento(${listID}, 'film', ${film.filmID})">Eliminar</button>
            </li>`;
        });
        document.getElementById("peliculas-list").innerHTML = peliculasContent;

        let seriesContent = "";
        series.forEach(serie => {
            seriesContent += `
            <li>${serie.title} 
                <button class="delete-btn" onclick="eliminarElemento(${listID}, 'serie', ${serie.serieID})">Eliminar</button>
            </li>`;
        });
        document.getElementById("series-list").innerHTML = seriesContent;

    } catch (error) {
        console.error('Error al obtener las películas y series:', error);
        alert('No se pudo cargar las películas y series.');
    }
}




// Función para eliminar películas o series de la lista
async function eliminarElemento(listID, tipo, itemID) {

    try {
        let url = null;
        if (tipo === 'film') {
            url = `http://localhost:8082/v1/user/user/${userID}/list/${listID}/film/${itemID}`;
        } else if (tipo === 'serie') {
            url = `http://localhost:8082/v1/user/user/${userID}/list/${listID}/serie/${itemID}`;
        }

        // Realizar la petición DELETE
        const peticion = await fetch(url, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!peticion.ok) {
            throw new Error('Error al eliminar el elemento');
        }

        alert('Elemento eliminado correctamente');
        editarLista(); // Actualizar la lista después de eliminar el elemento
    } catch (error) {
        console.error('Error al eliminar el elemento:', error);
        alert('No se pudo eliminar el elemento');
    }
}

// Función para guardar el nombre de la lista
async function guardarNombre() {
    let campos = {};
    campos.listID = listID;
    campos.userID = userID;
    campos.name = document.getElementById("name").value;
    campos.films = [0];
    campos.series = [0];
    campos.createdAt = "2023-10-09T10:00:00Z";

    console.log(campos);

    const peticion = await fetch(`http://localhost:8082/v1/user/user/${userID}/list/${listID}`,
    {   method: 'PUT',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(campos)  
    });

    if (peticion.ok) {
        alert("Lista modificada con éxito.");
        window.location.href = "verListas.html";
    } else {
        alert("Hubo un error al intentar editar la cuenta.");
    }
}




// Cargar las películas y series cuando la página esté lista
window.onload = editarLista;


const obtenerInformacionLista = async () => {
    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}/list/${listID}`, {
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
        createdAt = data.createdAt;

    } catch (error) {
        console.error('Error obteniendo los datos:', error);
        alert('No se pudo cargar la información de la cuenta');
    }
};
