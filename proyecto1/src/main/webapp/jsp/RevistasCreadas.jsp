<%-- 
    Document   : RevistasCreadas
    Created on : Sep 15, 2024, 11:28:09 PM
    Author     : alesso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EDITOR</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/register.css">
    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">REVISTAS CREADAS</a>
                <div class="collapse navbar-collapse">                   
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/CrearRevista" method="POST">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Crear Revista</button>
                            </form>
                        </li>
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/ReporteEditor" method="POST">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Reportes</button>
                            </form>
                        </li>
                        <li class="nav-item mr-3">
                            <img src="${param.photoUrl}" alt="Foto de perfil" class="img-profile rounded-circle"> 
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary" href="/proyecto1/LogOut">Cerrar sesión</a>
                        </li>

                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Revistas Creadas por el Editor</h1>
            <table class="table table-striped table-bordered">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Título</th>
                        <th>Versión</th>
                        <th>Estado</th>
                        <th>Comentarios</th>
                        <th>Me Gusta</th>
                        <th>Suscripciones</th>
                        <th>Visualizar</th>
                    </tr>
                </thead>
                <tbody>
                <tbody>
                    <c:forEach var="revista" items="${revistas}">
                        <tr>
                            <td>${revista.idRevista}</td>
                            <td>${revista.titulo}</td>
                            <td>${revista.version}</td>
                            <td>
                                <span class="badge ${revista.aprobada ? 'badge-success' : 'badge-primary'}">
                                    ${revista.aprobada ? 'Aprobada' : 'En Espera'}
                                </span>
                            </td>
                            <td>
                                <input type="checkbox" ${revista.comentarios ? 'checked' : ''} disabled>
                            </td>
                            <td>
                                <input type="checkbox" ${revista.meGusta ? 'checked' : ''} disabled>
                            </td>
                            <td>
                                <input type="checkbox" ${revista.subscriptores ? 'checked' : ''} disabled>
                            </td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" 
                                        data-target="#modalRevista${revista.idRevista}" onclick="openPDF('${revista.pathArchivo}')">
                                    Previsualizar
                                </button>
                            </td>
                        </tr>
                    <div class="modal fade" id="modalRevista${revista.idRevista}" tabindex="-1" role="dialog" aria-labelledby="modalRevistaLabel${revista.idRevista}" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalRevistaLabel${revista.idRevista}">Previsualización de Revista: ${revista.titulo}</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <h6><strong>Título:</strong> ${revista.titulo}</h6>
                                    <h6><strong>Versión:</strong> ${revista.version}</h6>
                                    <h6><strong>Descripcion:</strong> ${revista.descripcion}</h6>
                                    <div class="embed-responsive embed-responsive-16by9">
                                        <iframe class="embed-responsive-item" src="${revista.pathArchivo}" type="application/pdf" width="100%" height="500px"></iframe>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                </div>
                            </div>
                        </div>
                    </div>

                </c:forEach>
                </tbody>

            </table>
        </div>
    </body>

</html>
