<%-- 
    Document   : inicioAdmin
    Created on : Sep 11, 2024, 10:30:43 PM
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
                            <form action="${pageContext.servletContext.contextPath}/AnunciosAdmin" method="GET">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Anuncios</button>
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
            <h1 class="text-center">Revistas Sin Procesar</h1>
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
                        <th>Aceptar / Denegar</th>
                    </tr>
                </thead>
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
                            <td>
                                <input type="hidden" name="idRevista" value="${revista.idRevista}">
                                <button type="button" class="btn btn-success" onclick="aprobarRevista(${revista.idRevista})">Aceptar</button>
                                <button type="button" class="btn btn-info" onclick="denegarRevista(${revista.idRevista})">Denegar</button>
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
        <div class="container mt-5">
            <h1 class="text-center">Revistas Procesadas</h1>
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
                    <c:forEach var="revistasDenegadas" items="${revistasDenegadas}">
                        <tr>
                            <td>${revistasDenegadas.idRevista}</td>
                            <td>${revistasDenegadas.titulo}</td>
                            <td>${revistasDenegadas.version}</td>
                            <td>
                                <span class="badge ${revistasDenegadas.denegada ? 'badge-danger' : 'badge-primary'}">
                                    ${revistasDenegadas.denegada ? 'Denegada' : 'En Espera'}
                                </span>
                            </td>
                            <td>
                                <input type="checkbox" ${revistasDenegadas.comentarios ? 'checked' : ''} disabled>
                            </td>
                            <td>
                                <input type="checkbox" ${revistasDenegadas.meGusta ? 'checked' : ''} disabled>
                            </td>
                            <td>
                                <input type="checkbox" ${revistasDenegadas.subscriptores ? 'checked' : ''} disabled>
                            </td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" 
                                        data-target="#modalRevista${revistasDenegadas.idRevista}" onclick="openPDF('${revistasDenegadas.pathArchivo}')">
                                    Previsualizar
                                </button>
                            </td>
                        </tr>
                    <div class="modal fade" id="modalRevista${revistasDenegadas.idRevista}" tabindex="-1" role="dialog" aria-labelledby="modalRevistaLabel${revistasDenegadas.idRevista}" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="modalRevistaLabel${revistasDenegadas.idRevista}">Previsualización de Revista: ${revistasDenegadas.titulo}</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <h6><strong>Título:</strong> ${revistasDenegadas.titulo}</h6>
                                    <h6><strong>Versión:</strong> ${revistasDenegadas.version}</h6>
                                    <h6><strong>Descripción:</strong> ${revistasDenegadas.descripcion}</h6>
                                    <div class="embed-responsive embed-responsive-16by9">
                                        <iframe class="embed-responsive-item" src="${revistasDenegadas.pathArchivo}" type="application/pdf" width="100%" height="500px"></iframe>
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
        <script src="js/Admin.js"></script>
    </body>
</html>
