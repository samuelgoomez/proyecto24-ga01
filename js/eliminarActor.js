const urlParams = new URLSearchParams(window.location.search);
const actorID = urlParams.get('actorID');

document.getElementById("btn-confirmarEliminacion").addEventListener("click", async () => {
    await eliminarActor();
    //window.location.href = `paginaPrincipal.html`;
});

const eliminarActor = async () => {
    if (actorID) {
        try {
            const peticion = await fetch(`http://localhost:8080/v1/content/actors/${encodeURIComponent(actorID)}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (peticion.ok) {
                alert("Actor eliminado con éxito.");
                window.location.href = "paginaPrincipal.html"; // Cambia a la página que corresponda tras eliminar.
            } else {
                alert("Hubo un error al intentar eliminar el actor.");
            }
        } catch (error) {
            console.error("Error al eliminar el actor:", error);
            alert("Ocurrió un problema al conectar con el servidor.");
        }
    } else {
        alert("No se pudo encontrar el ID del actor.");
    }
};

document.getElementById("btn-cancelarEliminacion").addEventListener("click", () => {
    if (actorID)
        window.location.href = "paginaPrincipal.html"; // Cambia a la página que muestra detalles del actor.
    else
        window.location.href = "paginaPrincipal.html"; // Redirige a la lista de actores si no hay ID.
});
