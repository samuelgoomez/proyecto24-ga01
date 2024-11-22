document.getElementById("btn-confirmarEliminacion").addEventListener("click", evento => {
    eliminarCuenta();
});

let eliminarCuenta = async () => {
    // Aquí, en lugar de obtener datos como userID, simplemente ejecutamos la solicitud de eliminación.
    const userID = sessionStorage.getItem("userID"); // O de otro modo obtén el userID
    
    if (userID) {
        const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        });

        if (peticion.ok) {
            alert("Cuenta eliminada con éxito.");
            window.location.href = "iniciarSesion.html";
        } else {
            alert("Hubo un error al intentar eliminar la cuenta.");
        }
    } else {
        alert("No se pudo encontrar el ID de usuario.");
    }
};


// Función para convertir preferences
function convertirCreatedAt() {
    const createdAtString = document.getElementById("createdAt").value;
    const createdAtDate = new Date(createdAtString);

    // Convertimos a formato UTC (equivalente a ZoneOffset.UTC)
    const createdAtOffset = new Date(createdAtDate.toISOString()).toISOString();

    return createdAtOffset;
}

// Función para convertir la fecha createdAt
function convertirStringAArrayDeNumeros(str) {
    // Separa el string en un array usando un delimitador, por ejemplo, una coma
    // Luego convierte cada elemento en un número
    return str.split(',').map(num => parseFloat(num.trim())).filter(num => !isNaN(num)); 
}

document.getElementById("btn-cancelarEliminacion").addEventListener("click", evento => {
    const userID = sessionStorage.getItem("userID");
    if(userID)
        window.location.href = "infoCuenta.html";
    else
        window.location.href = "iniciarSesion.html";
});