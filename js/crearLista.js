const userID = sessionStorage.getItem("userID");

let boton = document.getElementById("btcrear");

boton.addEventListener("click", evento =>{
    crearLista();
});

let crearLista = async()=>{
    
    let campos = {};
    campos.listID = document.getElementById("listID").value;
    campos.userID = userID;
    campos.name = document.getElementById("name").value;
    campos.films = convertirStringAArrayDeNumeros(document.getElementById("films").value);
    campos.series = convertirStringAArrayDeNumeros(document.getElementById("series").value); 
    campos.createdAt = convertirCreatedAt();

    console.log(campos);

    const peticion = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/list`,
    {   method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(campos)  
    });

    if (peticion.ok) {
        alert("Lista creada con éxito.");
        window.location.href = "verListas.html";
    } else {
        alert("Hubo un error al intentar editar la cuenta.");
    }
}


function convertirCreatedAt() {
    const createdAtString = document.getElementById("createdAt").value;
    const createdAtDate = new Date(createdAtString);

    // Convertimos a formato UTC (equivalente a ZoneOffset.UTC)
    const createdAtOffset = new Date(createdAtDate.toISOString()).toISOString();

    return createdAtOffset;
}

function convertirStringAArrayDeNumeros(str) {
    // Separa el string en un array usando un delimitador, por ejemplo, una coma
    // Luego convierte cada elemento en un número
    return str.split(',').map(num => parseFloat(num.trim())).filter(num => !isNaN(num)); 
}