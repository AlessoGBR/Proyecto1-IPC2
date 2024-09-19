function aprobarRevista(idRevista) {
    $.ajax({
        url: '/proyecto1/AprobarRevista', 
        type: 'POST',
        data: {idRevista: idRevista}, 
        success: function (response) {
            Swal.fire({
                title: 'Revista aprobada',
                text: 'La revista ha sido aprobada correctamente.',
                icon: 'success',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                location.reload();
            });
        },
        error: function (xhr, status, error) {
            Swal.fire({
                title: 'Error',
                text: 'Ocurrió un error al aprobar la revista.',
                icon: 'error',
                confirmButtonText: 'Aceptar'
            });
        }
    });
}

function denegarRevista(idRevista) {
    $.ajax({
        url: '/proyecto1/DenegarRevista', 
        type: 'POST',
        data: {idRevista: idRevista}, 
        success: function (response) {
            Swal.fire({
                title: 'Revista denegada',
                text: 'La revista ha sido denegada correctamente',
                icon: 'success',
                confirmButtonText: 'Aceptar'
            }).then(() => {
                location.reload();
            });
        },
        error: function (xhr, status, error) {
            Swal.fire({
                title: 'Error',
                text: 'Ocurrió un error al aprobar la revista.',
                icon: 'error',
                confirmButtonText: 'Aceptar'
            });
        }
    });
}