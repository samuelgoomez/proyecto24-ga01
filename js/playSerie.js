window.onload = function() {
    // Añadir un listener para el botón Reproducir
    const btnReproducir = document.getElementById("play-button");
    const btnPausar = document.getElementById("pause-button");
    const btnFinalizar = document.getElementById("finish-button");
    const btnEspañol = document.getElementById("spanish-button");
    const btnIngles = document.getElementById("english-button");
    const btnSubtituloOn = document.getElementById("subtitle-on-button");
    const btnSubtituloOff = document.getElementById("subtitle-off-button");
    const btnDescargar = document.getElementById("download-button");
    const loadingGif = document.getElementById("loading-gif");

    if (btnReproducir) {
        btnReproducir.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            loadingGif.classList.remove("hidden");
            activarReproduccion();
        });
    } 

    if (btnPausar) {
        btnPausar.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            loadingGif.classList.add("hidden");
            pausarReproduccion();
        });
    } 

    if (btnFinalizar) {
        btnFinalizar.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            finalizarReproduccion();
        });
    }

    if (btnEspañol) {
        btnEspañol.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            españolReproduccion();
        });
    }

    if (btnIngles) {
        btnIngles.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            inglesReproduccion();
        });
    }

    if (btnSubtituloOn) {
        btnSubtituloOn.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            subtitulosOnReproduccion();
        });
    }

    if (btnSubtituloOff) {
        btnSubtituloOff.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            subtitulosOffReproduccion();
        });
    }
    
    if (btnDescargar) {
        btnDescargar.addEventListener("click", function() {
            // Llamar a la función que maneja la reproducción
            descargarReproduccion();
        });
    }
};

// Función que se ejecuta cuando se pulsa el botón Reproducir
async function activarReproduccion() {
    // Obtener userID desde sessionStorage (si lo tienes allí)
    const userID = sessionStorage.getItem("userID");
    // Obtener filmID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const serieID = urlParams.get("serieID");

    if (userID && serieID) {
        // Llamar a la URL para obtener los datos de reproducción
        try {
            const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/play`, {
                method: 'POST',  // Usamos POST para iniciar la reproducción
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (response.ok) {
                const data = await response.json();

                // Establecer valores en los campos con los datos obtenidos
                document.getElementById("visualizationID").textContent = `ID de Visualización: ${data.visualizationID}`;
                document.getElementById("estado").textContent = `Estado: ${data.estado}`;
                document.getElementById("idioma").textContent = `Idioma: ${data.idioma}`;
                document.getElementById("subtitulos").textContent = `Subtítulos: ${data.subtitulos ? "Sí" : "No"}`;

            } else {
                console.error("Error al hacer la petición:", await response.json());
                alert("Hubo un problema al iniciar la reproducción.");
            }
        } catch (error) {
            console.error("Error al hacer la petición:", error);
            alert("No se pudo iniciar la reproducción.");
        }
    } else {
        alert("Faltan datos necesarios para iniciar la reproducción.");
    }
}

// Función que se ejecuta cuando se pulsa el botón Reproducir
async function pausarReproduccion() {
    // Obtener userID desde sessionStorage (si lo tienes allí)
    const userID = sessionStorage.getItem("userID");
    // Obtener filmID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const serieID = urlParams.get("serieID");

    if (userID && serieID) {
        // Llamar a la URL para obtener los datos de reproducción
        try {
            const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/pause`, {
                method: 'POST',  // Usamos POST para iniciar la reproducción
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                }
            });

            if (response.ok) {
                const data = await response.json();

                // Establecer valores en los campos con los datos obtenidos
                document.getElementById("visualizationID").textContent = `ID de Visualización: ${data.visualizationID}`;
                document.getElementById("estado").textContent = `Estado: ${data.estado}`;
                document.getElementById("idioma").textContent = `Idioma: ${data.idioma}`;
                document.getElementById("subtitulos").textContent = `Subtítulos: ${data.subtitulos ? "Sí" : "No"}`;

            } else {
                console.error("Error al hacer la petición:", await response.json());
                alert("Hubo un problema al pausar la reproducción.");
            }
        } catch (error) {
            console.error("Error al hacer la petición:", error);
            alert("No se pudo pausar la reproducción.");
        }
    } else {
        alert("Faltan datos necesarios para pausar la reproducción.");
    }
}

async function finalizarReproduccion() {
    const userID = sessionStorage.getItem("userID"); // Obtener userID de sessionStorage
    const serieID = new URLSearchParams(window.location.search).get("serieID"); // Obtener filmID de los parámetros de la URL

    if (!userID || !serieID) {
        alert("No se encontró el usuario o la película.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/end`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            console.error("Error al finalizar el servicio:", error);
            alert("Hubo un problema al finalizar la reproducción: " + (error.message || "Error desconocido."));
            return;
        }

        alert("Reproducción finalizada con éxito.");
        // Redirigir a paginaPrincipal.html
        window.location.href = "paginaPrincipal.html";
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo finalizar el servicio debido a un error.");
    }
}

async function descargarReproduccion() {
    const userID = sessionStorage.getItem("userID"); // Obtener userID de sessionStorage
    const serieID = new URLSearchParams(window.location.search).get("serieID"); // Obtener filmID de los parámetros de la URL

    if (!userID || !serieID) {
        alert("No se encontró el usuario o la película.");
        return;
    }

    try {
        const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/download`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            console.error("Error al finalizar el servicio:", error);
            alert("Hubo un problema al finalizar la reproducción: " + (error.message || "Error desconocido."));
            return;
        }

        const data = await response.json();

        alert(data.message);
    } catch (error) {
        console.error("Error al hacer la petición:", error);
        alert("No se pudo finalizar el servicio debido a un error.");
    }
}

async function españolReproduccion() {
    // Obtener userID desde sessionStorage (si lo tienes allí)
    const userID = sessionStorage.getItem("userID");
    // Obtener filmID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const serieID = urlParams.get("serieID");

    if (userID && serieID) {
        // Llamar a la URL para obtener los datos de reproducción
        let campos={};
        campos.languageCode = "Español";
        
        try {
            const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/language`, {
                method: 'POST',  // Usamos POST para iniciar la reproducción
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(campos) 
            });

            if (response.ok) {
                const data = await response.json();

                // Establecer valores en los campos con los datos obtenidos
                document.getElementById("visualizationID").textContent = `ID de Visualización: ${data.visualizationID}`;
                document.getElementById("estado").textContent = `Estado: ${data.estado}`;
                document.getElementById("idioma").textContent = `Idioma: ${data.idioma}`;
                document.getElementById("subtitulos").textContent = `Subtítulos: ${data.subtitulos ? "Sí" : "No"}`;

            } else {
                console.error("Error al hacer la petición:", await response.json());
                alert("Hubo un problema al cambiar idioma en la reproducción.");
            }
        } catch (error) {
            console.error("Error al hacer la petición:", error);
            alert("No se pudo cambiar idioma en la reproducción.");
        }
    } else {
        alert("Faltan datos necesarios para cambiar idioma en la reproducción.");
    }
}

async function inglesReproduccion() {
    // Obtener userID desde sessionStorage (si lo tienes allí)
    const userID = sessionStorage.getItem("userID");
    // Obtener filmID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const serieID = urlParams.get("serieID");

    if (userID && serieID) {
        // Llamar a la URL para obtener los datos de reproducción
        let campos={};
        campos.languageCode = "Ingles";
        
        try {
            const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/language`, {
                method: 'POST',  // Usamos POST para iniciar la reproducción
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(campos) 
            });

            if (response.ok) {
                const data = await response.json();

                // Establecer valores en los campos con los datos obtenidos
                document.getElementById("visualizationID").textContent = `ID de Visualización: ${data.visualizationID}`;
                document.getElementById("estado").textContent = `Estado: ${data.estado}`;
                document.getElementById("idioma").textContent = `Idioma: ${data.idioma}`;
                document.getElementById("subtitulos").textContent = `Subtítulos: ${data.subtitulos ? "Sí" : "No"}`;

            } else {
                console.error("Error al hacer la petición:", await response.json());
                alert("Hubo un problema al cambiar idioma en la reproducción.");
            }
        } catch (error) {
            console.error("Error al hacer la petición:", error);
            alert("No se pudo cambiar idioma en la reproducción.");
        }
    } else {
        alert("Faltan datos necesarios para cambiar idioma en la reproducción.");
    }
}

async function subtitulosOnReproduccion() {
    // Obtener userID desde sessionStorage (si lo tienes allí)
    const userID = sessionStorage.getItem("userID");
    // Obtener filmID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const serieID = urlParams.get("serieID");

    if (userID && serieID) {
        // Llamar a la URL para obtener los datos de reproducción
        let campos={};
        campos.languageCode = "Sí";
        try {
            const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/subtitles`, {
                method: 'POST',  // Usamos POST para iniciar la reproducción
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(campos) 
            });

            if (response.ok) {
                const data = await response.json();

                // Establecer valores en los campos con los datos obtenidos
                document.getElementById("visualizationID").textContent = `ID de Visualización: ${data.visualizationID}`;
                document.getElementById("estado").textContent = `Estado: ${data.estado}`;
                document.getElementById("idioma").textContent = `Idioma: ${data.idioma}`;
                document.getElementById("subtitulos").textContent = `Subtítulos: ${data.subtitulos ? "Sí" : "No"}`;

            } else {
                console.error("Error al hacer la petición:", await response.json());
                alert("Hubo un problema al activar subtitulos en la reproducción.");
            }
        } catch (error) {
            console.error("Error al hacer la petición:", error);
            alert("No se pudo activar subtitulos en la reproducción.");
        }
    } else {
        alert("Faltan datos necesarios para activar subtitulos en la reproducción.");
    }
}

async function subtitulosOffReproduccion() {
    // Obtener userID desde sessionStorage (si lo tienes allí)
    const userID = sessionStorage.getItem("userID");
    // Obtener filmID de la URL
    const urlParams = new URLSearchParams(window.location.search);
    const serieID = urlParams.get("serieID");

    if (userID && serieID) {
        // Llamar a la URL para obtener los datos de reproducción
        let campos={};
        campos.languageCode = "No";
        try {
            const response = await fetch(`http://localhost:8081/views/visualization/${encodeURIComponent(userID)}/serie/${encodeURIComponent(serieID)}/subtitles`, {
                method: 'POST',  // Usamos POST para iniciar la reproducción
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(campos) 
            });

            if (response.ok) {
                const data = await response.json();

                // Establecer valores en los campos con los datos obtenidos
                document.getElementById("visualizationID").textContent = `ID de Visualización: ${data.visualizationID}`;
                document.getElementById("estado").textContent = `Estado: ${data.estado}`;
                document.getElementById("idioma").textContent = `Idioma: ${data.idioma}`;
                document.getElementById("subtitulos").textContent = `Subtítulos: ${data.subtitulos ? "Sí" : "No"}`;

            } else {
                console.error("Error al hacer la petición:", await response.json());
                alert("Hubo un problema al desactivar subtitulos en la reproducción.");
            }
        } catch (error) {
            console.error("Error al hacer la petición:", error);
            alert("No se pudo desactivar subtitulos en la reproducción.");
        }
    } else {
        alert("Faltan datos necesarios para desactivar subtitulos en la reproducción.");
    }
}

