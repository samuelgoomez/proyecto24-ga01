// Obtener el parámetro de la URL (actorID)
const urlParams = new URLSearchParams(window.location.search);
const actorID = urlParams.get('actorID');

// Función para cargar los datos del actor desde la API
let cargarDatosActor = async () => {
    if (!actorID) {
        console.error("No se proporcionó un actorID.");
        alert("No se pudo encontrar el ID del actor.");
        return;
    }

    try {
        const respuesta = await fetch(`http://localhost:8080/v1/content/actors/${encodeURIComponent(actorID)}`);

        if (respuesta.ok) {
            const actor = await respuesta.json();

            // Llenar los campos del formulario con los datos obtenidos
            document.getElementById("name").value = actor.name;
            document.getElementById("birthdayDate").value = actor.birthdayDate ? actor.birthdayDate.split('T')[0] : ''; // Convertir fecha al formato 'YYYY-MM-DD'
            document.getElementById("photoURL").value = actor.photoURL;
        } else {
            const error = await respuesta.json();
            alert("Hubo un error al intentar cargar los datos del actor: " + error.message);
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo cargar la información del actor.");
    }
};

// Función para editar actor (envía los datos al servidor)
let editarActor = async () => {
    if (!actorID) {
        console.error("No se proporcionó un actorID.");
        alert("No se pudo encontrar el ID del actor.");
        return;
    }

    // Recoger los datos del formulario
    let actor = {}
    actor.actorID = actorID;
    actor.name = document.getElementById("name").value.trim();
    actor.birthdayDate = convertirFechaAFormatoISO(document.getElementById("birthdayDate").value.trim());
    actor.photoURL = document.getElementById("photoURL").value.trim();

    // Validar los campos requeridos
    if (!actor.name || !actor.birthdayDate || !actor.photoURL) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    console.log("Datos del actor a enviar:", actor);

    try {
        const peticion = await fetch(`http://localhost:8080/v1/content/actors/${encodeURIComponent(actor.actorID)}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(actor)
        });

        if (!peticion.ok) {
            const error = await peticion.json();
            console.error("Error al editar actor:", error);
            alert("Hubo un error al intentar editar el actor: " + (error.message || JSON.stringify(error))); // Mostrar el error detallado
        } else {
            alert("Actor editado con éxito.");
            window.location.href = "paginaPrincipal.html";
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("Hubo un error al intentar hacer la solicitud.");
    }
};

// Cargar los datos del actor si actorID existe
if (actorID) {
    cargarDatosActor();
} else {
    console.error('No se proporcionó un actorID');
}

// Evento para guardar los cambios
document.getElementById("btn-guardarActor").addEventListener("click", evento => {
    editarActor();
});

function convertirFechaAFormatoISO(fechaInput) {
    // Asegurarse de que la fecha ingresada es válida
    if (!fechaInput) {
        console.error("La fecha ingresada no es válida.");
        return null;
    }

    // Crear un objeto Date y convertirlo a formato ISO 8601
    const fecha = new Date(fechaInput);
    if (isNaN(fecha)) {
        console.error("Fecha inválida.");
        return null;
    }

    // Convertir a formato ISO 8601 y asegurarse de que incluye la 'Z' al final
    return fecha.toISOString(); // Esto devuelve la fecha en formato `YYYY-MM-DDTHH:MM:SSZ`
}