const urlParams = new URLSearchParams(window.location.search);
const serieID = urlParams.get('serieID');

document.getElementById("btn-confirmarEliminacion").addEventListener("click", async () => {
    await eliminarSerie();
});

const eliminarSerie = async () => {
    if (serieID) {
        try {
            const peticion = await fetch(`http://localhost:8080/v1/content/series/${encodeURIComponent(serieID)}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (peticion.ok) {
                alert("Serie eliminada con éxito.");
                window.location.href = "paginaPrincipal.html"; // Cambia a la página que corresponda tras eliminar.
            } else {
                alert("Hubo un error al intentar eliminar la serie.");
            }
        } catch (error) {
            console.error("Error al eliminar la serie:", error);
            alert("Ocurrió un problema al conectar con el servidor.");
        }
    } else {
        alert("No se pudo encontrar el ID de la serie.");
    }
};

document.getElementById("btn-cancelarEliminacion").addEventListener("click", () => {
    if (serieID) {
        window.location.href = "paginaPrincipal.html"; // Cambia a la página que muestra detalles de la serie.
    } else {
        window.location.href = "paginaPrincipal.html"; // Redirige a la lista de series si no hay ID.
    }
});
