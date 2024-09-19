$(document).ready(function () {
    $('#crearAnuncioForm').on('submit', function (e) {
        e.preventDefault(); // Evita el comportamiento por defecto del formulario

        var formData = new FormData(this); // Recoge todos los datos del formulario

        $.ajax({
            url: '/proyecto1/CrearAnuncio',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                Swal.fire({
                    title: 'Anuncio creado',
                    text: 'El anuncio ha sido creado correctamente.',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                }).then(() => {
                    location.reload(); // Recargar la página después de cerrar el modal
                });
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al crear el anuncio.',
                    icon: 'error',
                    confirmButtonText: 'Aceptar'
                });
            }
        });
    });
});
function updateFormFields() {
    var userType = document.getElementById('userType').value;

    // Ocultar todos los campos
    document.getElementById('fileUploadDiv').style.display = 'none';
    document.getElementById('textAnuncioDiv').style.display = 'none';
    document.getElementById('urlAnuncioDiv').style.display = 'none';

    // Mostrar campos basados en la selección
    if (userType === 'TEXTO') {
        document.getElementById('textAnuncioDiv').style.display = 'block';
        document.getElementById('textAnuncio').required = true;
    } else if (userType === 'IMAGEN Y TEXTO') {
        document.getElementById('fileUploadDiv').style.display = 'block';
        document.getElementById('textAnuncioDiv').style.display = 'block';
        document.getElementById('fileUpload').required = true;
        document.getElementById('textAnuncio').required = true;
    } else if (userType === 'VIDEO') {
        document.getElementById('urlAnuncioDiv').style.display = 'block';
        document.getElementById('urlAnuncio').required = true;
    }
}
function previewImageFile() {
    var file = document.getElementById("fileUpload").files[0];
    var previewImage = document.getElementById("previewImage");

    if (file) {
        var reader = new FileReader();
        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = 'flex';
        }
        reader.readAsDataURL(file);
    } else {
        previewImage.style.display = 'none';
        previewImage.src = '';
    }
}

