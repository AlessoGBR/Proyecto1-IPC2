<%-- 
    Document   : AnuncioAdmin
    Created on : Sep 19, 2024, 10:50:17 AM
    Author     : alesso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMINISTRADOR</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>  
        <link rel="stylesheet" href="css/inicio.css">
    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">ADMINISTRADOR</a>
                <div class="collapse navbar-collapse">                   
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/Admin" method="GET">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Inicio</button>
                            </form>
                        </li>
                        <li class="nav-item mr-2">
                            <form action="" method="">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Reportes</button>
                            </form>
                        </li>
                        <li class="nav-item mr-3">
                            <img src="${param.photoUrl}" alt="Foto de perfil" class="img-profile rounded-circle"> 
                        </li>
                        <li class="nav-item mr-2">
                            <form action="/proyecto1/LogOut" method="GET">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Cerrar sesion</button>
                            </form>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Anuncios Sin Procesar</h1>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Texto</th>
                        <th>Video URL</th>
                        <th>Imagen</th>
                        <th>Estado</th>
                        <th>Fecha Inicio</th>
                        <th>Fecha Final</th>
                        <th>Pago</th>
                        <th>Nombre Anunciante</th>
                        <th>Tipo</th>
                        <th>Aceptar / Denegar</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="anuncio" items="${anuncios}">
                        <tr data-id="${anuncio.idAnuncio}">
                            <td>${anuncio.idAnuncio}</td>
                            <td>${anuncio.texto}</td>
                            <td>${anuncio.urlVideo}</td>
                            <td>
                                <img src="${anuncio.pathImagen}" alt="Imagen del Anuncio" width="100">
                            </td>
                            <td>
                                <span class="badge ${anuncio.activo ? 'badge-success' : 'badge-warning'}">
                                    ${anuncio.activo ? 'Activo' : 'Inactivo'}
                                </span>
                            </td>
                            <td>
                                <input type="date" class="form-control" name="fechaInicio" required>
                            </td>
                            <td>
                                <input type="date" class="form-control" name="fechaFinal" required>
                            </td>
                            <td>
                                <input type="number" class="form-control" name="pago" required>
                            </td>
                            <td>${anuncio.nombreAnunciante}</td>
                            <td>${anuncio.tipo}</td>
                            <td>
                                <button type="button" class="btn btn-success" onclick="aprobarAnuncio(${anuncio.idAnuncio})">Aceptar</button>
                                <button type="button" class="btn btn-danger" onclick="denegarAnuncio(${anuncio.idAnuncio})">Denegar</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
        </div>
        <script src="js/AdminAnuncio.js"></script>
    </body>
</html>
