let params = new URLSearchParams(window.location.search);
let userID = params.get('userID');

// Función para obtener la información del método de pago desde la API
const obtenerInformacionPago = async (userID) => {
    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}/payment`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error al obtener la información del método de pago');
        }

        const data = await response.json();

        // Mostrar los datos en el HTML
        document.getElementById("cardnumber-display").textContent = data.cardNumber || 'No disponible';
        document.getElementById("expirationdate-display").textContent = data.expirationDate 
            ? new Date(data.expirationDate).toLocaleDateString() 
            : 'No disponible';
        document.getElementById("cardholdername-display").textContent = data.cardHolderName || 'No disponible';

    } catch (error) {
        console.error('Error obteniendo los datos:', error);
        alert('No se pudo cargar la información del método de pago');
    }
};

// Verifica si el userID existe antes de hacer la petición
if (userID) {
    obtenerInformacionPago(userID); // Llama a la función para mostrar los datos
} else {
    alert("No se ha encontrado sesión activa");
}

document.addEventListener("DOMContentLoaded", async function () {
    const btnEliminar = document.getElementById("btn-eliminarMetodoPago");

    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}/getSuscripcionPlan`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            }
        });

        if (response.ok) {
            const data = await response.json();
            console.log(data);
            // Comprueba si hay planes de suscripción
            if (data) {
                btnEliminar.style.display = "none"; // Oculta el botón si tiene suscripciones
            } else {
                btnEliminar.style.display = "block"; // Muestra el botón si no tiene suscripciones
            }
        } else if (response.status === 404) {
            console.warn("No se encontraron suscripciones para el usuario.");
            btnEliminar.style.display = "block"; // Muestra el botón si no hay suscripciones
        } else {
            console.error("Error inesperado al obtener los datos de la suscripción.");
        }
    } catch (error) {
        console.error("Error al conectar con el servidor:", error);
    }

    // Redirige al usuario al hacer clic en el botón
    btnEliminar.addEventListener("click", function () {
        window.location.href = `eliminarMetodoPago.html?userID=${userID}`;
    });
});


