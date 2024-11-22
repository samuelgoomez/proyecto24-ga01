const urlParams = new URLSearchParams(window.location.search);
const filmID = urlParams.get('filmID');

document.getElementById("btn-confirmarEliminacion").addEventListener("click", async () => {
    await eliminarPelicula();
});

const eliminarPelicula = async () => {
    if (filmID) {
        try {
            const peticion = await fetch(`http://localhost:8080/v1/content/films/${encodeURIComponent(filmID)}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (peticion.ok) {
                alert("Película eliminada con éxito.");
                window.location.href = "paginaPrincipal.html"; // Cambia a la página que corresponda tras eliminar.
            } else {
                alert("Hubo un error al intentar eliminar la película.");
            }
        } catch (error) {
            console.error("Error al eliminar la película:", error);
            alert("Ocurrió un problema al conectar con el servidor.");
        }
    } else {
        alert("No se pudo encontrar el ID de la película.");
    }
};

document.getElementById("btn-cancelarEliminacion").addEventListener("click", () => {
    if (filmID)
        window.location.href = "paginaPrincipal.html"; // Cambia a la página que muestra detalles de la película.
    else
        
        window.location.href = "paginaPrincipal.html"; // Redirige a la lista de películas si no hay ID.
});
