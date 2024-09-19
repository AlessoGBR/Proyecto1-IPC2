function aprobarAnuncio(idAnuncio) {
    // Obtener la fila del anuncio
    const row = document.querySelector(`tr[data-id='${idAnuncio}']`);
    const fechaInicio = row.querySelector('input[name="fechaInicio"]').value;
    const fechaFinal = row.querySelector('input[name="fechaFinal"]').value;
    const pago = row.querySelector('input[name="pago"]').value;

    // Verificar que los campos estén llenos
    if (!fechaInicio || !fechaFinal || !pago) {
        alert("Por favor, complete todos los campos antes de aprobar el anuncio.");
        return;
    }

    // Lógica para aprobar el anuncio (por ejemplo, usando AJAX)
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "proyecto1/AprobarAnuncio", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onload = function () {
        if (xhr.status === 200) {
            alert("Anuncio aprobado exitosamente.");
            // Puedes actualizar la tabla o recargar la página
            location.reload();
        } else {
            alert("Error al aprobar el anuncio. Intenta nuevamente.");
        }
    };

    xhr.send(`idAnuncio=${idAnuncio}&fechaInicio=${fechaInicio}&fechaFinal=${fechaFinal}&pago=${pago}`);
}

function denegarAnuncio(idAnuncio) {
    // Lógica para denegar el anuncio (por ejemplo, usando AJAX)
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "proyecto1/DenegarAnuncio", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onload = function () {
        if (xhr.status === 200) {
            alert("Anuncio denegado exitosamente.");
            // Puedes actualizar la tabla o recargar la página
            location.reload();
        } else {
            alert("Error al denegar el anuncio. Intenta nuevamente.");
        }
    };

    xhr.send(`idAnuncio=${idAnuncio}`);
}
function aprobarAnuncio(idAnuncio) {
    // Obtener valores de los inputs
    var fechaInicio = $('#fechaInicio' + idAnuncio).val();
    var fechaFinal = $('#fechaFinal' + idAnuncio).val();
    var pago = $('#pago' + idAnuncio).val();

    // Verifica los valores en la consola
    console.log('ID Anuncio:', idAnuncio);
    console.log('Fecha Inicio:', fechaInicio);
    console.log('Fecha Final:', fechaFinal);
    console.log('Pago:', pago);

    $.ajax({
        url: 'proyecto1/AprobarAnuncio',
        type: 'POST',
        data: {
            idAnuncio: idAnuncio,
            fechaInicio: fechaInicio,
            fechaFinal: fechaFinal,
            pago: pago
        },
        dataType: 'json',
        success: function (response) {
            console.log('Respuesta:', response); // Verifica la respuesta
            if (response.status === "success") {
                alert(response.message);
            } else {
                alert("Error: " + response.message);
            }
        },
        error: function (xhr, status, error) {
            console.error('Error en AJAX:', error); // Muestra el error en la consola
            alert("Error: " + error);
        }
    });
}

