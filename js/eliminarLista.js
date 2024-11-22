document.getElementById("btn-confirmarEliminacion").addEventListener("click", evento => {
    eliminarLista();
});

let eliminarLista = async () => {
    // Obtener el userID y listID desde el sessionStorage y la URL respectivamente
    const userID = sessionStorage.getItem("userID"); // Obtener el ID del usuario
    const listID = new URLSearchParams(window.location.search).get("listID"); // Obtener el ID de la lista de la URL

    // Verificar que ambos IDs estén presentes
    if (userID && listID) {
        // Realizar la solicitud DELETE para eliminar la lista
        const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/list/${encodeURIComponent(listID)}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        });

        // Verificar si la solicitud fue exitosa
        if (peticion.ok) {
            alert("Lista eliminada con éxito.");
            window.location.href = "verListas.html"; // Redirigir a la página principal después de eliminar
        } else {
            alert("Hubo un error al intentar eliminar la lista.");
        }
    } else {
        alert("No se pudo encontrar el ID de usuario o de lista.");
    }
};

// Función para cancelar la eliminación y redirigir al usuario
document.getElementById("btn-cancelarEliminacion").addEventListener("click", evento => {
    const userID = sessionStorage.getItem("userID");
    window.location.href = "verListas.html";
});
