<%-- 
    Document   : ReporteEditor
    Created on : Sep 15, 2024, 11:29:22 PM
    Author     : alesso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDITOR</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="css/Reportes.css">
        <script src="js/Reporte.js"></script>
    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">REPORTES EDITOR</a>
                <div class="collapse navbar-collapse">                   
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/RevistasCreadas" method="GET">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Revistas Creadas</button>
                            </form>
                        </li>
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/CrearRevista" method="POST">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Crear Revista</button>
                            </form>
                        </li>
                        <li class="nav-item mr-3">
                            <img src="${param.photoUrl}" alt="Foto de perfil" class="img-profile rounded-circle"> 
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-4 reportes-sidebar">
                    <h5>Reportes Disponibles</h5>
                    <form id="reporteForm" method="GET" action="/proyecto1/GenerarReporte">
                        <input type="hidden" name="username" value="${param.username}">
                        <div class="form-group">
                            <label for="reporte">Tipo de Reporte</label>
                            <select class="form-control" name="reporte" id="reporte">
                                <option value="comentarios">Comentarios de Revista</option>
                                <option value="suscripciones">Suscripciones a revistas</option>
                                <option value="likes">5 revistas con más likes</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Generar Reporte</button>
                    </form>
                </div>

                <div class="col-md-8 reporte-generado">
                    <h5 class="text-center">Reporte Generado</h5>
                    <iframe id="pdfFrame" name="pdfFrame" width="100%" height="600px"></iframe>
                </div>
            </div>
        </div>        
    </body>
    <script>
        $(document).ready(function () {
            $('#reporteForm').on('submit', function (e) {
                e.preventDefault();

                var formData = $(this).serialize();

                $.ajax({
                    url: $(this).attr('action'),
                    type: 'GET',
                    data: formData,
                    xhrFields: {
                        responseType: 'blob' // Asegúrate de que la respuesta sea tratada como un blob
                    },
                    success: function (response) {
                        var blob = new Blob([response], {type: 'application/pdf'});
                        var url = window.URL.createObjectURL(blob);

                        // Abre el PDF en un nuevo iframe
                        var iframe = $('<iframe>', {
                            src: url,
                            style: 'width:100%; height:500px; border:none;',
                        });

                        // Limpia cualquier contenido anterior y añade el nuevo iframe
                        $('#pdfFrame').empty().append(iframe);
                    },
                    error: function (xhr, status, error) {
                        Swal.fire({
                            title: 'Error',
                            text: 'Ocurrió un error al generar el reporte.',
                            icon: 'error',
                            confirmButtonText: 'Aceptar'
                        });
                    }
                });
            });
        });
    </script>
</html>
