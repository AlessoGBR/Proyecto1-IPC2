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
    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">REPORTES</a>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/RevistasCreadas" method="GET">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Ver revistas creadas</button>
                            </form>
                        </li>
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/CrearRevista" method="POST">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Crear Revista</button>
                            </form>
                        </li>
                        <li class="nav-item mr-3">
                            <img src="${param.photoUrl}" alt="Foto de perfil" class="img-profile rounded-circle" width="40" height="40">
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary" href="${pageContext.servletContext.contextPath}/LogoutServlet">Cerrar sesión</a>
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
                    <form action="${pageContext.servletContext.contextPath}/GenerarReporte" method="POST">
                        <input type="hidden" name="username" value="${param.username}">
                        <div class="form-group">
                            <select class="form-control" name="tipoReporte" id="reporte">
                                <option value="comentariosIntervalo">Comentarios de Revista</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="fechaInicio">Fecha de Inicio</label>
                            <div class="input-group">
                                <input type="date" class="form-control" id="fechaInicio" name="fechaInicio" placeholder="dd/mm/aaaa" id="fechaInicio">
                            </div>                            
                        </div>
                        <small>Fechas vacías muestran todos los datos</small>
                        <div class="form-group">
                            <label for="fechaFinal">Fecha de Final</label>
                            <div class="input-group">
                                <input type="date" class="form-control" id="fechaFinal" name="fechaFinal" placeholder="dd/mm/aaaa" id="fechaFinal">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="revistasCreadas">Revistas Creadas</label>
                            <input type="text" class="form-control" id="revistasCreadas" name="revistasCreadas" placeholder="Todas" id="revista">
                        </div>
                        <button type="submit" class="btn btn-primary">Generar Reporte</button>
                    </form>
                </div>

                <div class="col-md-8 reporte-generado">
                    <h5 class="text-center">Reporte Generado</h5>
                </div>
            </div>
        </div>
    </body>
</html>
