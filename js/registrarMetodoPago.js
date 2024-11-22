const userID = sessionStorage.getItem('userID');

// Verificar el valor de userID
console.log('ID del usuario:', userID);

let boton = document.getElementById("btregistrarMetodoPago");

boton.addEventListener("click", async (evento) => {
    evento.preventDefault(); // Evitar el comportamiento predeterminado del botón

    // Verificar si el userID es válido antes de llamar a registrarMetodoPago
    if (!userID) {
        alert("No se ha proporcionado un ID de usuario válido.");
        return;
    }

    await registrarMetodoPago(userID); // Llamar a registrarMetodoPago con el userID
    // Redirigir a la página de métodos de pago
    // window.location.href = `verMetodosPago.html?userID=${userID}`;
});

let registrarMetodoPago = async (userID) => {
    // Recoger los datos del formulario
    let campos = {};
    campos.paymentID = document.getElementById("paymentID").value;
    campos.userID = userID;
    campos.cardNumber = document.getElementById("cardNumber").value;
    campos.expirationDate = convertirFechaAFormatoISO(document.getElementById("expirationDate").value);
    campos.cardHolderName = document.getElementById("cardHolderName").value;

    // Validar que los campos requeridos no estén vacíos
    if (!campos.cardNumber || !campos.expirationDate || !campos.cardHolderName) {
        alert("Por favor, completa todos los campos requeridos.");
        return;
    }

    console.log("Datos a enviar:", campos); // Imprimir los datos para depuración

    try {
        const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/payment`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(campos) // Enviar los datos en formato JSON
        });

        if (peticion.ok) {
            const resultado = await peticion.json();
            alert("Método de pago registrado con éxito.");
            // Puedes redirigir aquí si es necesario
            sessionStorage.setItem("paymentID", resultado.paymentID);
            window.location.href = `contratarPlan.html`;
        } else {
            const error = await peticion.json();
            console.error("Error al registrar el método de pago:", error);
            alert("Hubo un error al intentar registrar el método de pago: " + (error.message || JSON.stringify(error)));
        }
    } catch (error) {
        console.error("Error al hacer la petición:", error);
    }
};

function convertirFechaAFormatoISO(fechaInput) {
    // Verificar si la fechaInput tiene un valor
    if (!fechaInput) {
        console.error("La fecha ingresada no tiene valor.");
        return null;
    }

    // Verificar si la fecha está en el formato adecuado (YYYY-MM-DD)
    const fecha = new Date(fechaInput);

    // Verificar si la fecha es válida
    if (isNaN(fecha)) {
        console.error("La fecha es inválida.");
        return null;
    }

    // Retornar la fecha en formato ISO (YYYY-MM-DD)
    return fecha.toISOString().split('T')[0]; // Solo la parte de la fecha, sin la hora
}



