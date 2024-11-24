const urlParams = new URLSearchParams(window.location.search);
const userID = urlParams.get('userID');

document.getElementById("btn-confirmarEliminacion").addEventListener("click", async () => {
    await eliminarMetodoPago();
});

const eliminarMetodoPago = async () => {
    if (userID) {
        try {
            const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/payment`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (peticion.ok) {
                alert("Método de pago eliminado con éxito.");
                window.location.href = `infoCuenta.html?userID=${userID}`;
            } else {
                alert("Hubo un error al intentar eliminar el método de pago.");
            }
        } catch (error) {
            console.error("Error al eliminar el método de pago:", error);
            alert("Ocurrió un problema al conectar con el servidor.");
        }
    } else {
        alert("No se pudo encontrar el ID del método de pago.");
    }
};

document.getElementById("btn-cancelarEliminacion").addEventListener("click", () => {
    window.location.href = `infoCuenta.html?userID=${userID}`;
});
