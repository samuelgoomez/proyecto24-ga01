// Función para cargar los datos desde sessionStorage
let cargarDatosDesdeSessionStorage = () => {
    document.getElementById("userID").value = sessionStorage.getItem("userID");
    document.getElementById("username").value = sessionStorage.getItem("username");
    document.getElementById("password").value = sessionStorage.getItem("password");
    document.getElementById("email").value = sessionStorage.getItem("email");
    document.getElementById("name").value = sessionStorage.getItem("name");

    // Verificar que el valor de createdAt en sessionStorage sea válido
    const createdAt = sessionStorage.getItem("createdAt");
    if (createdAt) {
        document.getElementById("createdAt").value = createdAt;
    } else {
        document.getElementById("createdAt").value = new Date().toISOString(); // Valor predeterminado si no está en sessionStorage
    }

    document.getElementById("preferences").value = sessionStorage.getItem("preferences");
};

// Llamamos a la función para cargar los datos cuando la página se cargue
cargarDatosDesdeSessionStorage();

// Función para editar cuenta (envía los datos al servidor)
let editarCuenta = async () => {
    let campos = {};
    campos.userID = document.getElementById("userID").value;
    campos.username = document.getElementById("username").value;
    campos.password = document.getElementById("password").value;
    campos.email = document.getElementById("email").value;
    campos.name = document.getElementById("name").value;
    campos.preferences = convertirStringAArrayDeNumeros(document.getElementById("preferences").value);
    campos.createdAt = convertirCreatedAt();

    if(campos.userID){
        const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(campos.userID)}`, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(campos)
        });
        
        if (peticion.ok) {
            alert("Cuenta editada con éxito.");
            window.location.href = "infoCuenta.html";
        } else {
            alert("Hubo un error al intentar editar la cuenta.");
        }
    } else {
        alert("No se pudo encontrar el ID de usuario.");
    }
};

// Evento para guardar los cambios
document.getElementById("btn-guardarCuenta").addEventListener("click", evento => {
    editarCuenta();
});

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

// Función para convertir la fecha preferences a un array de números
function convertirStringAArrayDeNumeros(str) {
    // Separa el string en un array usando un delimitador, por ejemplo, una coma
    // Luego convierte cada elemento en un número
    return str.split(',').map(num => parseFloat(num.trim())).filter(num => !isNaN(num)); 
}
