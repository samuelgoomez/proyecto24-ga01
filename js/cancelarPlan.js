const urlParams = new URLSearchParams(window.location.search);
const userID = sessionStorage.getItem("userID");

document.getElementById("btn-confirmarEliminacion").addEventListener("click", async () => {
    await eliminarPlan();
});

const eliminarPlan= async () => {
    if (userID) {
        try {
            const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/cancelSuscripcionPlan`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (peticion.ok) {
                alert("Suscripción eliminada con éxito.");
                window.location.href = `infoCuenta.html?userID=${userID}`;
            } else {
                alert("Hubo un error al intentar eliminar la suscripción.");
            }
        } catch (error) {
            console.error("Error al darse de baja en la suscripción:", error);
            alert("Ocurrió un problema al conectar con el servidor.");
        }
    } else {
        alert("No se pudo encontrar el ID de la subscripción.");
    }
};

document.getElementById("btn-cancelarEliminacion").addEventListener("click", () => {
    window.location.href = `infoCuenta.html?userID=${userID}`;
});
