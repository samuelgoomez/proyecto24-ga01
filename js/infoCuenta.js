// Obtener el userID desde sessionStorage
const userID = sessionStorage.getItem("userID");

// Función para obtener la información del usuario
const obtenerInformacionCuenta = async (userID) => {
    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error al obtener la información del usuario');
        }

        const data = await response.json();

        // Mostrar los datos en el HTML
        document.getElementById("userID-display").textContent = data.userID;
        document.getElementById("username-display").textContent = data.username;
        document.getElementById("password-display").textContent = data.password;
        document.getElementById("email-display").textContent = data.email;
        document.getElementById("name-display").textContent = data.name;
        document.getElementById("createdAt-display").textContent = new Date(data.createdAt).toLocaleString();
        document.getElementById("preferences-display").textContent = data.preferences.join(", ");
    } catch (error) {
        console.error('Error obteniendo los datos:', error);
        alert('No se pudo cargar la información de la cuenta');
    }
};

// Función para obtener el método de pago
const obtenerInformacionPago = async (userID) => {
    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}/payment`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        // Si el método de pago no existe o no se puede obtener
        if (!response.ok) {
            console.error('No se encontró el método de pago para este usuario');
            const registrarMetodoPagoBtn = document.getElementById("registrarMetodoPago");

            if (registrarMetodoPagoBtn) {
                registrarMetodoPagoBtn.style.display = 'block';
                registrarMetodoPagoBtn.addEventListener('click', () => {
                    window.location.href = `registrarMetodoPago.html?userID=${userID}`;
                });
            }
            return;
        }

        const data = await response.json();

        // Verificar si existe un paymentID
        // Si existe un paymentID, mostramos el botón para ver el método de pago
        if (data.paymentID) {
            const verMetodoPagoBtn = document.getElementById("verMetodoPago");
            const registrarMetodoPagoBtn = document.getElementById("registrarMetodoPago");

            if (verMetodoPagoBtn) {
                verMetodoPagoBtn.style.display = 'block';
                verMetodoPagoBtn.addEventListener('click', () => {
                    window.location.href = `verMetodoPago.html?userID=${userID}`;
                });
            }

            if (registrarMetodoPagoBtn) {
                registrarMetodoPagoBtn.style.display = 'none';
            }
        }


    } catch (error) {
        console.error('Error obteniendo el método de pago:', error);
        // Si no se puede obtener el método de pago, redirigimos a la página de registrar
        // window.location.href = `registrarMetodoPago.html?userID=${userID}`;
    }
};

// Función para obtener la información del plan de suscripción
const obtenerInformacionPlan = async (userID) => {
    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}/getSuscripcionPlan`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        const payment = await fetch(`http://localhost:8082/v1/user/user/${userID}/payment`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok && payment.ok) {
            console.error('Error al obtener la información del plan de suscripción');

            // Si no tiene plan, mostramos el botón para contratar uno
            const contratarPlanBtn = document.getElementById("contratarPlan");

            if (contratarPlanBtn) {
                contratarPlanBtn.style.display = 'block';
                contratarPlanBtn.addEventListener('click', () => {
                    window.location.href = `contratarPlan.html`;
                });
            }
        }

        const data = await response.json();
        console.log(data);
        console.log(data.planID);

        // Verificamos si tiene un plan
        if (data.planID) {
            const verPlanBtn = document.getElementById("verPlanSuscripcion");

            if (verPlanBtn) {
                verPlanBtn.style.display = 'block';
                verPlanBtn.addEventListener('click', () => {
                    window.location.href = `verSuscripcion.html`;
                });
            }
        }
    } catch (error) {
        console.error('Error obteniendo los datos:', error);
    }
};

// Función que obtiene el nombre del plan según el planID
const getPlanName = (planID) => {
    switch(planID) {
        case 1: return 'Básico';
        case 2: return 'Estándar';
        case 3: return 'Premium';
        default: return 'No disponible';
    }
};

// Función que obtiene el precio del plan según el planID
const getPlanPrice = (planID) => {
    switch(planID) {
        case 1: return '7.99€ / mes';
        case 2: return '12.99€ / mes';
        case 3: return '17.99€ / mes';
        default: return 'No disponible';
    }
};

// Verifica si el userID existe antes de hacer la petición
if (userID) {
    obtenerInformacionCuenta(userID); // Llama a la función para mostrar los datos
    obtenerInformacionPago(userID);
    obtenerInformacionPlan(userID); 
}

// Función para redirigir a la página de edición de cuenta
document.getElementById("btn-editarCuenta").addEventListener("click", function() {
    // Guardamos los datos actuales en sessionStorage
    sessionStorage.setItem("username", document.getElementById("username-display").textContent);
    sessionStorage.setItem("password", document.getElementById("password-display").textContent);
    sessionStorage.setItem("email", document.getElementById("email-display").textContent);
    sessionStorage.setItem("name", document.getElementById("name-display").textContent);
    sessionStorage.setItem("createdAt", document.getElementById("createdAt-display").textContent);
    sessionStorage.setItem("preferences", document.getElementById("preferences-display").textContent);

    // Redirigimos a la página de edición de cuenta
    window.location.href = "editarCuenta.html";
});

document.getElementById("btn-eliminarCuenta").addEventListener("click", function() {
    window.location.href = "eliminarCuenta.html";
});


// Cerrar sesión
const cerrarSesion = () => {
    // Elimina el userID del sessionStorage
    sessionStorage.removeItem("userID");

    // Redirige al usuario a la página de inicio de sesión
    window.location.href = "paginaPrincipalInicial.html"; // Cambia "login.html" por la URL de tu página de inicio de sesión
};

document.getElementById("btn-cerrarSesion").addEventListener("click", evento => {
    evento.preventDefault(); // Evita que el formulario se envíe si es un botón dentro de un formulario
    cerrarSesion();
});


document.addEventListener('DOMContentLoaded', () => {
    const logo = document.querySelector('.logo'); // Seleccionar el logo de Ñefli

    // Añadir un evento onclick al logo
    if (logo) {
        logo.onclick = () => {
            window.location.href = 'paginaPrincipal.html'; // Redirigir a la página principal
        };
    }
});