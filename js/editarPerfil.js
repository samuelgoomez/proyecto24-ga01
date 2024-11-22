// Obtener el parámetro de la URL (profileID)
const urlParams = new URLSearchParams(window.location.search);
const profileID = urlParams.get('profileID');

// Obtener el userID desde sessionStorage
const userID = sessionStorage.getItem("userID");

// Función para cargar los datos del perfil desde la API
let cargarDatosPerfil = async (profileID) => {
    if (!profileID) {
        console.error("No se proporcionó un profileID.");
        alert("No se pudo encontrar el ID del perfil.");
        return;
    }

    try {
        const respuesta = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/profiles/${encodeURIComponent(profileID)}`);

        if (respuesta.ok) {
            const perfil = await respuesta.json();

            // Llenar los campos del formulario con los datos obtenidos
            document.getElementById("profileID").value = perfil.profileID;
            document.getElementById("name").value = perfil.name;
            document.getElementById("type").value = perfil.type;
            document.getElementById("avatarURL").value = perfil.avatarURL; // Asegúrate de usar la propiedad correcta
            document.getElementById("createdAt").value = perfil.createdAt; // Campo oculto, no editable
        } else {
            const error = await respuesta.json();
            alert("Hubo un error al intentar cargar los datos del perfil: " + error.message);
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo cargar la información del perfil.");
    }
};

// Función para editar el perfil (envía los datos al servidor)
let editarPerfil = async () => {
    if (!profileID) {
        console.error("No se proporcionó un profileID.");
        alert("No se pudo encontrar el ID del perfil.");
        return;
    }

    // Recoger los datos del formulario
    let perfil = {};
    perfil.profileID = profileID;
    perfil.userID = userID;
    perfil.name = document.getElementById("name").value.trim();
    perfil.type = document.getElementById("type").value.trim();
    perfil.avatarURL = document.getElementById("avatarURL").value.trim(); // Asegúrate de usar avatarURL
    perfil.createdAt = convertirCreatedAt(); // Convertimos el campo createdAt

    // Validar los campos requeridos
    if (!perfil.name || !perfil.type || !perfil.avatarURL) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    console.log("Datos del perfil a enviar:", perfil);

    try {
        const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/profiles/${encodeURIComponent(profileID)}`, {
            method: 'POST', // Asegúrate de que el método es correcto según lo que la API espera
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(perfil)
        });

        if (!peticion.ok) {
            const error = await peticion.json();
            console.error("Error al editar perfil:", error);
            alert("Hubo un error al intentar editar el perfil: " + (error.message || JSON.stringify(error))); // Mostrar el error detallado
        } else {
            alert("Perfil editado con éxito.");
            window.location.href = "verPerfiles.html";
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("Hubo un error al intentar hacer la solicitud.");
    }
};



// Función para convertir createdAt a formato UTC (solo si es válido)
function convertirCreatedAt() {
    const createdAtString = document.getElementById("createdAt").value;
    const createdAtDate = new Date(createdAtString);

    // Verificamos si la fecha es válida
    if (isNaN(createdAtDate.getTime())) {
        console.error("Fecha 'createdAt' no válida:", createdAtString);
        return new Date().toISOString(); // Devolver la fecha actual si la fecha es inválida
    }

    // Convertimos a formato UTC
    const createdAtOffset = new Date(createdAtDate.toISOString()).toISOString();

    return createdAtOffset;
}

// Cargar los datos del perfil si profileID existe
if (profileID) {
    cargarDatosPerfil(profileID); // Pasa el profileID como argumento
} else {
    console.error('No se proporcionó un profileID');
}

// Evento para guardar los cambios
document.getElementById("btn-guardarPerfil").addEventListener("click", () => {
    editarPerfil();
});




