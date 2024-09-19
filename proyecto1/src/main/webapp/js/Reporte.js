function cargarPDF(event) {
    event.preventDefault();

    const form = document.getElementById('reporteForm');
    const formData = new FormData(form);

    // Construir la URL con los parámetros del formulario
    const url = new URL('/proyecto1/GenerarReporte', window.location.origin);
    formData.forEach((value, key) => url.searchParams.append(key, value));

    fetch(url, {
        method: 'GET'
    })
            .then(response => response.blob())
            .then(blob => {
                const blobUrl = window.URL.createObjectURL(blob);
                const iframe = document.getElementById('pdfFrame');
                iframe.src = blobUrl;
            })
            .catch(error => console.error('Error al generar el reporte:', error));
}
