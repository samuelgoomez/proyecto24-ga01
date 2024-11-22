    const userID = sessionStorage.getItem("userID");
    const paymentID = sessionStorage.getItem("paymentID");
    const currentDate = new Date().toISOString().split('T')[0];
    document.addEventListener('DOMContentLoaded', () => {
        const plans = document.querySelectorAll('.plan'); // Selecciona las tarjetas de planes

        plans.forEach(plan => {
            plan.addEventListener('click', () => {
                // Obtén el planID desde el atributo data-plan-id
                const planID = plan.getAttribute('data-plan-id');

                // Datos a enviar
                const data = {
                    planID: parseInt(planID), // Convierte a entero
                    userID: parseInt(userID), // Reemplaza con el ID del usuario real
                    startDate: currentDate, // Fecha actual
                    endDate: currentDate,
                    paymentID: parseInt(paymentID) // Reemplaza con el ID del pago real
                };

                console.log("Payload enviado:", data);
                // Envía los datos al servidor
                fetch(`http://localhost:8082/v1/user/user/${userID}/hireSuscripcionPlan`, { // Reemplaza con tu endpoint real
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(data)
                })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Error al enviar los datos');
                    }
                })
                .then(result => {
                    alert(`Suscripción al plan ${planID} realizada con éxito.`);
                    console.log('Respuesta del servidor:', result);
                    window.location.href = "paginaPrincipalInicial.html";
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Hubo un problema al procesar tu suscripción.');
                });
            });
        });
    });
    
