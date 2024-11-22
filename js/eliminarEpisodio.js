// Obtener parámetros de la URL
const urlParams = new URLSearchParams(window.location.search);
const serieID = urlParams.get('serieID');
const episodeID = urlParams.get('episodeID');

// Verificar si se han recibido los IDs
if (serieID == null) {
    console.log("No se ha recibido serieID");
}

if (episodeID == null) {
    console.log("No se ha recibido episodeID");
}

// Confirmar eliminación al hacer clic en el botón
document.getElementById("btn-confirmarEliminacion").addEventListener("click", async () => {
    await eliminarSerie();
});

// Función para eliminar el episodio
const eliminarSerie = async () => {
    if (serieID && episodeID) {
        try {
            const peticion = await fetch(`http://localhost:8080/v1/content/series/${encodeURIComponent(serieID)}/episodes/${encodeURIComponent(episodeID)}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (peticion.ok) {
                alert("Episodio eliminado con éxito.");
                // Redirigir a la lista de episodios de la serie actual
                window.location.href = `verEpisodios.html?serieID=${encodeURIComponent(serieID)}`;
            } else {
                alert("Hubo un error al intentar eliminar el episodio.");
            }
        } catch (error) {
            console.error("Error al eliminar el episodio:", error);
            alert("Ocurrió un problema al conectar con el servidor.");
        }
    } else {
        alert("No se pudo encontrar el ID de la serie o episodio.");
    }
};

// Cancelar eliminación y redirigir al listado de episodios o series
document.getElementById("btn-cancelarEliminacion").addEventListener("click", () => {
    if (serieID) {
        // Redirige a la página que muestra los episodios de la serie actual
        window.location.href = `verEpisodios.html?serieID=${encodeURIComponent(serieID)}`;
    } else {
        // Redirige a la lista de series si no hay ID
        window.location.href = "listaSeries.html";
    }
});
