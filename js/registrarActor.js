let boton = document.getElementById("btregistrarActor");

boton.addEventListener("click", evento =>{
    registrarActor();
    window.location.href = `paginaPrincipal.html`;
});

let registrarActor = async () => {
    let campos = {};
    campos.actorID = parseInt(document.getElementById("actorID").value); // Asegurarse de que actorID es un número
    campos.name = document.getElementById("name").value.trim();
    campos.birthdayDate = convertirFechaAFormatoISO(document.getElementById("birthdayDate").value); // Fecha en formato ISO
    campos.photoURL = document.getElementById("photoURL").value.trim();

    // Validación de campos
    if (!campos.name || !campos.birthdayDate || !campos.photoURL) {
        console.error("Por favor, complete todos los campos.");
        alert("Por favor, complete todos los campos.");
        return;
    }

    console.log("Datos a enviar:", campos); // Inspeccionar los datos antes de enviar

    try {
        const peticion = await fetch("http://localhost:8080/v1/content/actors", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(campos),
        });

        // Verificación de la respuesta del servidor
        if (peticion.ok) {
            console.log("Actor registrado exitosamente.");
            alert("Actor registrado exitosamente.");
            // Redirigir si es necesario
            // window.location.href = `paginaPrincipal.html`;
        } else {
            const errorText = await peticion.text();
            console.error("Error al registrar el actor:", peticion.statusText, errorText);
            alert("Error al registrar el actor: " + peticion.statusText + " " + errorText);
        }
    } catch (error) {
        console.error("Error en la solicitud:", error);
        alert("Error en la solicitud: " + error);
    }
};

// Función para convertir la fecha a formato ISO 8601 (incluyendo la 'Z' al final)
function convertirFechaAFormatoISO(fechaInput) {
    // Asegurarse de que la fecha ingresada es válida
    if (!fechaInput) {
        console.error("La fecha ingresada no es válida.");
        return null;
    }

    // Crear un objeto Date y convertirlo a formato ISO 8601
    const fecha = new Date(fechaInput);
    if (isNaN(fecha)) {
        console.error("Fecha inválida.");
        return null;
    }

    // Convertir a formato ISO 8601 y asegurarse de que incluye la 'Z' al final
    return fecha.toISOString(); // Esto devuelve la fecha en formato `YYYY-MM-DDTHH:MM:SSZ`
}
