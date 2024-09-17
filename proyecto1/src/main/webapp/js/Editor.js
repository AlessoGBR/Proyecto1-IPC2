/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

document.addEventListener('DOMContentLoaded', function () {
    document.querySelectorAll('.category-tag').forEach(tag => {
        tag.addEventListener('click', function () {
            toggleTagSelection(this);
        });
    });

    function toggleTagSelection(tagElement) {
        const selectedTagsContainer = document.getElementById('selectedTags');
        const tagText = tagElement.textContent.trim();
        const tagAlreadySelected = tagElement.classList.contains('selected');

        if (tagAlreadySelected) {
            tagElement.classList.remove('selected');
            Array.from(selectedTagsContainer.children).forEach(child => {
                if (child.textContent.trim() === tagText) {
                    selectedTagsContainer.removeChild(child);
                }
            });
        } else {
            tagElement.classList.add('selected');
            const selectedTag = tagElement.cloneNode(true);
            selectedTagsContainer.appendChild(selectedTag);

            selectedTag.addEventListener('click', function () {
                toggleTagSelection(tagElement);
            });
        }
    }
});
document.addEventListener("DOMContentLoaded", function () {
    var elements = document.getElementsByTagName("INPUT");
    for (var i = 0; i < elements.length; i++) {
        elements[i].oninvalid = function (e) {
            e.target.setCustomValidity("");
            if (!e.target.validity.valid) {
                e.target.setCustomValidity("Por favor llenar el campo");
            }
        };
        elements[i].oninput = function (e) {
            e.target.setCustomValidity("");
        };
    }
});
document.getElementById("agregarEtiquetaForm").addEventListener("submit", function (event) {
    var nuevaEtiqueta = document.getElementById("nuevaEtiqueta").value.trim();

    if (nuevaEtiqueta === "") {
        event.preventDefault();
        alert("Por favor ingrese una etiqueta.");
    }
});
function toggleInput() {
    var checkbox = document.getElementById("esGratuita");
    var input = document.getElementById("precioRevista");

    if (checkbox.checked) {
        input.disabled = false;
        input.value = 0;
    } else {
        input.disabled = true;
        input.value = 1;
    }
}
document.getElementById('crear').addEventListener('click', function () {
    const selectedTagsContainer = document.getElementById('selectedTags');
    const selectedTags = Array.from(selectedTagsContainer.children)
            .map(tag => tag.textContent.trim());
    document.getElementById('selectedTagsInput').value = selectedTags.join(',');

    localStorage.setItem('selectedTags', JSON.stringify(selectedTags));
});

class AgregarEtiqueta {
    constructor(formSelector) {
        this.form = document.querySelector(formSelector);
        this.init();
    }

    init() {
        this.form.addEventListener('submit', (e) => this.enviarFormulario(e));
    }

    enviarFormulario(event) {
        event.preventDefault();

        let nuevaEtiqueta = document.querySelector('#nuevaEtiqueta').value;

        $.ajax({
            url: '/proyecto1/Etiqueta',
            type: 'POST',
            data: {nuevaEtiqueta: nuevaEtiqueta},
            dataType: 'json',
            success: (response) => {
                if (response.status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: '¡Etiqueta agregada!',
                        text: response.message,
                        showConfirmButton: false,
                        timer: 2000
                    }).then(() => {
                        window.location.reload(); // Recargar la página
                    });
                    document.querySelector('#nuevaEtiqueta').value = '';
                } else if (response.status === 'error') {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: response.message
                    });
                }
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Error al agregar la etiqueta.'
                });
            }
        });
    }
}

class AgregarRevista {
    constructor(formSelector) {
        this.form = document.querySelector(formSelector);
        this.init();
    }

    init() {
        this.form.addEventListener('submit', (e) => this.enviarFormulario(e));
    }

    enviarFormulario(event) {
        event.preventDefault();

        let formData = new FormData(this.form);

        $.ajax({
            url: this.form.action, // Usa la URL del formulario
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            dataType: 'json',
            success: (response) => {
                console.log('Respuesta del servidor:', response); // Para debugging
                if (response.status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: '¡Revista registrada!',
                        text: response.message,
                        showConfirmButton: false,
                        timer: 2000
                    }).then(() => {
                        window.location.reload(); // Recargar la página
                    });
                } else if (response.status === 'error') {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: response.message
                    });
                }
            },
            error: function (xhr, status, error) {
                console.error('Error en la solicitud AJAX:', status, error); // Para debugging
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: 'Error al registrar la revista.'
                });
            }
        });
    }
}





