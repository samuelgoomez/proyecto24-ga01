document.addEventListener('DOMContentLoaded', () => {
    // Obtener el userID desde sessionStorage
    const userID = sessionStorage.getItem("userID");

    // Función para obtener los perfiles desde la base de datos
    const obtenerPerfiles = async (userID) => {
        try {
            // Realiza la petición a la API para obtener los perfiles
            const response = await fetch(`http://localhost:8082/v1/user/user/${userID}/profiles`, {
                method: 'GET',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            const profilesContainer = document.querySelector('.profiles');
            profilesContainer.innerHTML = ''; // Limpiar cualquier contenido existente

            if (!response.ok) {
                console.error('Error al obtener los perfiles');
                mostrarBotonAñadirPerfil(profilesContainer);
                return;
            }

            // Obtener la lista de perfiles en formato JSON
            const perfiles = await response.json();

            // Si no hay perfiles, muestra el botón para añadir uno
            if (perfiles.length === 0) {
                mostrarBotonAñadirPerfil(profilesContainer);
                return;
            }

            // Generar el HTML para cada perfil
            perfiles.forEach(perfil => {
                const profileDiv = document.createElement('div');
                profileDiv.classList.add('profile');

                const avatarImg = document.createElement('img');
                console.log(perfil.avatarURL);
                avatarImg.src = perfil.avatarURL ; // Usa un avatar por defecto si no hay URL
                avatarImg.alt = `Perfil de ${perfil.name}`;
                avatarImg.width = 100; // Ancho opcional
                avatarImg.height = 150; // Alto opcional
                avatarImg.classList.add('avatar');
                avatarImg.onclick = function () {
                    window.location.href = 'paginaPrincipal.html'; // Cambia la ruta según corresponda
                };

                // Crear el nombre del perfil
                const profileName = document.createElement('p');
                profileName.textContent = perfil.name;

                // Crear el botón de editar perfil
                const editButton = document.createElement('button');
                editButton.textContent = 'Editar Perfil';
                editButton.classList.add('manage-button');
                editButton.onclick = function () {
                    window.location.href = `editarPerfil.html?profileID=${perfil.profileID}`;
                };

                // Crear el botón de eliminar perfil
                const deleteIcon = document.createElement('div');
                deleteIcon.classList.add('delete-icon');
                deleteIcon.innerHTML = '&times;';
                deleteIcon.onclick = (event) => {
                    event.stopPropagation(); // Evitar que se active el evento del avatar
                    eliminarPerfil(perfil.profileID);
                };

                // Añadir los elementos al contenedor del perfil
                profileDiv.appendChild(deleteIcon); // Botón de eliminar
                profileDiv.appendChild(avatarImg);
                profileDiv.appendChild(profileName);
                profileDiv.appendChild(editButton); // Botón de editar

                // Añadir el perfil generado al contenedor de perfiles
                profilesContainer.appendChild(profileDiv);
            });

            // Añadir el botón "Añadir Perfil" al final
            mostrarBotonAñadirPerfil(profilesContainer);

        } catch (error) {
            console.error('Error obteniendo los perfiles:', error);
        }
    };

    // Función para mostrar el botón "Añadir Perfil"
    const mostrarBotonAñadirPerfil = (profilesContainer) => {
        const addProfileDiv = document.createElement('div');
        addProfileDiv.classList.add('profile', 'add-profile');
    
        const addButton = document.createElement('button');
        addButton.textContent = 'Añadir Perfil';
        addButton.classList.add('add-profile-button-red'); // Clase para el estilo rojo
    
        addButton.onclick = crearNuevoPerfil; // Evento para crear un nuevo perfil
    
        addProfileDiv.appendChild(addButton);
        profilesContainer.appendChild(addProfileDiv);
    };
    

    // Función para eliminar un perfil
    const eliminarPerfil = async (profileID) => {
        try {
            const response = await fetch(`http://localhost:8082/v1/user/user/${encodeURIComponent(userID)}/profiles/${encodeURIComponent(profileID)}`, {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error('Error al eliminar el perfil');
            }

            // Recargar la lista de perfiles
            obtenerPerfiles(userID);

        } catch (error) {
            console.error('Error al eliminar el perfil:', error);
            alert('No se pudo eliminar el perfil');
        }
    };

    // Función para crear un nuevo perfil
    const crearNuevoPerfil = () => {
        // Redirigir a una página de creación de perfil
        window.location.href = `registrarPerfil.html`;
    };

    // Verifica si el userID existe antes de hacer la petición
    if (userID) {
        obtenerPerfiles(userID); // Llama a la función para mostrar los perfiles
    } else {
        alert("No se ha encontrado sesión activa");
    }
});



