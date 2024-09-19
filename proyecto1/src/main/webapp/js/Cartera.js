$(document).ready(function () {
    $('#recargarCarteraForm').on('submit', function (e) {
        e.preventDefault(); // Evita el comportamiento por defecto del formulario

        var username = $('#username').val();
        var cartera = $('#cartera').val();
        var cantidad = $('#cantidad').val();

        if (!username || !cantidad) {
            Swal.fire({
                title: 'Error',
                text: 'El usuario o la cantidad no son válidos.',
                icon: 'error',
                confirmButtonText: 'Aceptar'
            });
            return; // Evita la ejecución si no se encuentran los datos
        }

        $.ajax({
            url: '/proyecto1/CargarCartera', // URL del servlet
            type: 'POST',
            data: {
                username: username,
                cartera: cartera,
                cantidad: cantidad
            },
            success: function (response) {
                Swal.fire({
                    title: 'Recarga exitosa',
                    text: 'La cartera ha sido recargada correctamente.',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                }).then(() => {
                    location.reload(); // Recargar la página después de cerrar el modal
                });
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ocurrió un error al recargar la cartera.',
                    icon: 'error',
                    confirmButtonText: 'Aceptar'
                });
            }
        });
    });
});