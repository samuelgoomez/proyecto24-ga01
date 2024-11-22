let boton = document.getElementById("btRegistrar");

boton.addEventListener("click", async (evento) => {
    await registrarPerfil();
    // Redirigir a la página principal solo si el registro fue exitoso
    window.location.href = `verPerfiles.html`;
});

 // Obtener el userID desde sessionStorage
 const userID = sessionStorage.getItem("userID");

 function convertirCreatedAt() {
    const createdAtString = document.getElementById("createdAt").value;
    const createdAtDate = new Date(createdAtString);

    // Convertimos a formato UTC (equivalente a ZoneOffset.UTC)
    const createdAtOffset = new Date(createdAtDate.toISOString()).toISOString();

    return createdAtOffset;
}

let registrarPerfil = async () => {
    // Crear un objeto con los campos del formulario
    let campos = {};
    campos.profileID = parseInt(document.getElementById("profileID").value); // Asegúrate de que sea un número
    campos.userID = userID; // Asegúrate de que sea un número
    campos.name = document.getElementById("name").value;
    campos.type = document.getElementById("type").value;
    campos.avatarURL = document.getElementById("avatarurl").value;
    campos.createdAt = convertirCreatedAt();

    // Imprimir en la consola para verificar la información antes de enviar
    console.log('Campos a enviar:', campos);

    try {
        // Hacer la petición para registrar el perfil
        const peticion = await fetch(`http://localhost:8082/v1/user/user/${campos.userID}/profiles`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(campos)
        });

        // Verificar si la respuesta es exitosa
        if (!peticion.ok) {
            throw new Error('Error al registrar el perfil. Código de estado: ' + peticion.status);
        }

        const respuesta = await peticion.json();
        console.log('Perfil registrado correctamente:', respuesta);

        // Mostrar un mensaje de éxito
        alert('Perfil registrado con éxito.');

    } catch (error) {
        // Manejar errores y mostrar mensajes en caso de fallo
        console.error('Error en el registro del perfil:', error);
        alert('No se pudo registrar el perfil. Verifique la consola para más detalles.');
    }
};

