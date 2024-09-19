function meGusta(idRevista, username) {
    fetch('/proyecto1/MeGusta', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            idRevista: idRevista,
            username: username  // Asegúrate de que este nombre coincida con el que usas en el servlet
        }).toString()
    })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: '¡Éxito!',
                        text: data.message,
                        showConfirmButton: false,
                        timer: 2000
                    }).then(() => {
                        window.location.reload(); // Recargar la página para reflejar cambios
                    });
                } else if (data.status === 'error') {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: data.message
                    });
                }
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Error al procesar la solicitud.'
                });
                console.error('Error:', error);
            });
}
;

function agregarComentario(idRevista) {
    const comentario = document.querySelector(`#comentario${idRevista}`).value;
    const username = document.querySelector('#username').value;

    fetch('/proyecto1/Comentar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            comentario: comentario,
            username: username,
            idRevista: idRevista
        }).toString()
    })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: '¡Comentario agregado!',
                        text: data.message,
                        showConfirmButton: false,
                        timer: 2000
                    }).then(() => {
                        location.reload(); // Opcional: recargar la página para reflejar los cambios
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: data.message
                    });
                }
            })
            .catch(error => {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Error al agregar el comentario.'
                });
            });
}
;

function suscripcion(idRevista) {
    const username = document.querySelector('#username').value;

    console.log('ID Revista:', idRevista);
    console.log('Username:', username);

    if (!username) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Nombre de usuario no encontrado.'
        });
        return;
    }

    fetch('/proyecto1/Suscribirse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            username: username,
            idRevista: idRevista
        }).toString()
    })
            .then(response => {
                console.log('Response Status:', response.status);
                if (!response.ok) {
                    throw new Error('Error en la respuesta del servidor');
                }
                return response.json();
            })
            .then(data => {
                console.log('Response Data:', data);
                if (data.status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: '¡Te has suscrito a la revista!',
                        text: data.message,
                        showConfirmButton: false,
                        timer: 2000
                    }).then(() => {
                        location.reload(); // Opcional: recargar la página para reflejar los cambios
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: data.message
                    });
                }
            })
            .catch(error => {
                console.error('Fetch Error:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Error al agregar la suscripción. ' + error.message
                });
            });
}
;


document.getElementById('buscar').addEventListener('click', function () {
    const selectedTagsContainer = document.getElementById('selectedTags');
    const selectedTags = Array.from(selectedTagsContainer.children)
            .map(tag => tag.textContent.trim());
    document.getElementById('selectedTagsInput').value = selectedTags.join(',');

    localStorage.setItem('selectedTags', JSON.stringify(selectedTags));
});
document.getElementById('agregarEtiquetaForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Evitar el envío del formulario tradicional

    const form = document.getElementById('agregarEtiquetaForm');
    const formData = new FormData(form);

    fetch('/proyecto1/Buscar?' + new URLSearchParams(formData).toString(), {
        method: 'GET'
    })
            .then(response => response.text())
            .then(html => {
                // Actualizar el contenedor con el contenido HTML recibido
                document.querySelector('.container').innerHTML = html;
            })
            .catch(error => console.error('Error al buscar revistas:', error));
});
document.getElementById("agregarEtiquetaForm").addEventListener("submit", function (event) {
    event.preventDefault(); // Evitar el envío normal del formulario

    const username = document.querySelector('input[name="username"]').value;
    const selectedTagsInput = document.querySelector('input[name="selectedTagsInput"]').value;

    // Crear una solicitud AJAX
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "Buscar?username=" + encodeURIComponent(username) + "&selectedTagsInput=" + encodeURIComponent(selectedTagsInput), true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            // Inserta el contenido de la respuesta en el div con id "resultadoBusqueda"
            document.getElementById("resultadoBusqueda").innerHTML = xhr.responseText;
        } else {
            // Manejo de errores (opcional)
            console.error("Error en la búsqueda: " + xhr.status);
            document.getElementById("resultadoBusqueda").innerHTML = "<p>Error al buscar revistas.</p>";
        }
    };

    xhr.onerror = function () {
        console.error("Error en la solicitud AJAX.");
        document.getElementById("resultadoBusqueda").innerHTML = "<p>Error en la solicitud.</p>";
    };

    // Enviar la solicitud AJAX
    xhr.send();
});
function suscripcion(idRevista) {
    const username = document.querySelector('#username').value;

    console.log('ID Revista:', idRevista);
    console.log('Username:', username);

    if (!username) {
        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'Nombre de usuario no encontrado.'
        });
        return;
    }

    fetch('/proyecto1/Suscribirse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            username: username,
            idRevista: idRevista
        }).toString()
    })
            .then(response => {
                console.log('Response Status:', response.status);
                if (!response.ok) {
                    throw new Error('Error en la respuesta del servidor');
                }
                return response.json();
            })
            .then(data => {
                console.log('Response Data:', data);
                if (data.status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: '¡Te has suscrito a la revista!',
                        text: data.message,
                        showConfirmButton: false,
                        timer: 2000
                    }).then(() => {
                        location.reload(); // Opcional: recargar la página para reflejar los cambios
                    });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: data.message
                    });
                }
            })
            .catch(error => {
                console.error('Fetch Error:', error);
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Error al agregar la suscripción. ' + error.message
                });
            });
}
