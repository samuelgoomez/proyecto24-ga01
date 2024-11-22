// Obtener el userID desde sessionStorage
const userID = sessionStorage.getItem("userID");

// Función para obtener la información del plan de suscripción desde la API
const obtenerInformacionPlan = async (userID) => {
    try {
        const response = await fetch(`http://localhost:8082/v1/user/user/${userID}/getSuscripcionPlan`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error al obtener la información del plan de suscripción');
        }

        const data = await response.json();
        console.log("Respuesta del servidor:", data);

        // Determinar el nombre del plan según planID
        let planName;
        let price;
        switch (data.planID) {
            case 1:
                planName = 'Básico';
                price = '7.99 €';
                console.log("Ha entrado aquí", data.planID);
                console.log(planName);
                break;
            case 2:
                planName = 'Estandar';
                price = '12.99 €';
                console.log("Ha entrado aquí", data.planID);
                break;
            case 3:
                planName = 'Premium';
                price = '17.99 €';
                console.log("Ha entrado aquí", data.planID);
                break;
            default:
                planName = 'Plan desconocido';
                price = 'No disponible';
        }

        // Mostrar los datos en el HTML
        document.getElementById("planname-display").textContent = planName;
        console.log("Ha escrito esto", planName);
        console.log("La fecha es", data.startDate);
        document.getElementById("startdate-display").textContent = data.startDate 
            ? new Date(data.startDate).toLocaleDateString() 
            : 'No disponible';
        document.getElementById("enddate-display").textContent = data.endDate 
            ? new Date(data.endDate).toLocaleDateString() 
            : 'No disponible';
        document.getElementById("price-display").textContent = price;

    } catch (error) {
        console.error('Error obteniendo los datos:', error);
        alert('No se pudo cargar la información del plan de suscripción');
    }
};

// Verifica si el userID existe antes de hacer la petición
if (userID) {
    obtenerInformacionPlan(userID);
} else {
    alert("No se ha encontrado sesión activa");
}

// Redirigir al cancelar el plan
document.getElementById("btn-cancelarPlan").addEventListener("click", function () {
    window.location.href = `cancelarPlan.html`;
});
