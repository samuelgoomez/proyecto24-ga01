let boton = document.getElementById("btcrear");

boton.addEventListener("click", evento => {
    crearCuenta();
});

function convertirStringAArrayDeNumeros(str) {
    // Convierte el string a un array de números
    return str
        .split(',')
        .map(num => parseFloat(num.trim()))
        .filter(num => !isNaN(num));
}

function convertirCreatedAt() {
    const createdAtString = document.getElementById("createdAt").value;
    const createdAtDate = new Date(createdAtString);

    // Convertimos a formato UTC
    const createdAtOffset = new Date(createdAtDate.toISOString()).toISOString();

    return createdAtOffset;
}

function obtenerGénerosSeleccionados() {
    let génerosSeleccionados = [];
    let checkboxes = document.querySelectorAll('input[name="genre"]:checked');
    
    checkboxes.forEach(checkbox => {
        génerosSeleccionados.push(parseInt(checkbox.value)); // Aseguramos que sean números enteros
    });
    
    return génerosSeleccionados; // Devuelve el array de géneros seleccionados
}

let crearCuenta = async () => {
    let campos = {};
    campos.userID = document.getElementById("userID").value;
    campos.username = document.getElementById("username").value;
    campos.password = document.getElementById("password").value;
    campos.email = document.getElementById("email").value;
    campos.name = document.getElementById("name").value;
    campos.preferences = obtenerGénerosSeleccionados();
    campos.createdAt = convertirCreatedAt();

    console.log(campos);

    try {
        const peticion = await fetch("http://localhost:8082/v1/user/user", {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(campos)
        });

        if (peticion.ok) {
            const data = await peticion.json();
            console.log("Respuesta del servidor:", data);
            console.log("Respuesta del servidor:", data);

            // Guarda el perfilID en sessionStorage
            sessionStorage.setItem("userID", data.userID);

            alert("Cuenta creada con éxito.");
            window.location.href = "registrarMetodoPago.html";
        } else {
            alert("Hubo un error al intentar crear la cuenta.");
        }
    } catch (error) {
        console.error("Error al crear la cuenta:", error);
        alert("Ocurrió un error inesperado.");
    }
};

    


