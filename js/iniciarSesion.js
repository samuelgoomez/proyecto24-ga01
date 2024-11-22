document.getElementById("btn-iniciarSesion").addEventListener("click", evento => {
    evento.preventDefault(); // Evita que el formulario se envíe si es un botón dentro de un formulario
    iniciarSesion();
});

const iniciarSesion = async () => {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    // Construir la URL con los parámetros de búsqueda (query string)
    const url = `http://localhost:8082/v1/user/user/login?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`;

    try {
        const response = await fetch(url, {
            method: 'GET', // Usamos GET ya que el servidor lo espera de esta manera
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Error en la solicitud: ' + response.statusText);
        }

        const data = await response.json();
        console.log("Login exitoso", data);


        // Guardar el userID en sessionStorage
        sessionStorage.setItem("userID", data.userID);
        

        // Redirigir a la página de inicio o la página que desees
        window.location.href = "verPerfiles.html"; // Aquí cambias la URL por la página a la que quieras redirigir

    } catch (error) {
        console.error("Error en el inicio de sesión:", error);
        // Muestra un mensaje de error al usuario si falla el inicio de sesión
        alert("Error en el inicio de sesión. Por favor, verifica tus credenciales.");
    }
};

