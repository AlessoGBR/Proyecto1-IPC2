<%-- 
    Document   : inicio
    Created on : Sep 8, 2024, 7:33:27 PM
    Author     : alesso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LECTOR</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link rel="stylesheet" href="css/register.css">     

    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">APP REVISTAS</a>
                <div class="collapse navbar-collapse">                   
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="" method="">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Perfil</button>
                            </form>
                        </li>                       
                        <li class="nav-item mr-3">
                            <img src="${param.photoUrl}" alt="Foto de perfil" class="img-profile rounded-circle"> 
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary" href="${pageContext.servletContext.contextPath}/LogOut">Cerrar sesión</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
    <body>
        <div class="container">
            <form id="agregarEtiquetaForm" action="${pageContext.servletContext.contextPath}/Buscar" method="GET">
                <h3 class="text-center">Categorías y etiquetas existentes</h3>
                <div class="form-group">
                    <div id="categoryTags" class="d-flex flex-wrap">
                        <c:forEach var="etiqueta" items="${etiquetas}">
                            <span class="badge badge-pill badge-secondary category-tag m-1">${etiqueta}</span>
                        </c:forEach>
                    </div>
                </div>
                <h5>Seleccionadas:</h5>
                <div id="selectedTags" class="d-flex flex-wrap"></div>
                <input type="hidden" id="selectedTagsInput" name="selectedTagsInput">
                <input type="hidden" name="username" value="${param.username}">
                <div class="form-row align-items-center">
                    <div class="container">
                        <button type="submit" class="btn btn-success btn-block" id="buscar">Buscar</button>
                    </div>
                </div>
            </form>
            <div id="revistasEncontradas" class="mt-4"></div>
        </div>
        <div class="container mt-4">
            <c:forEach var="revista" items="${revistas}">
                <div class="list-group-item mb-3">
                    <h4>${revista.titulo}</h4>
                    <p>${revista.descripcion}</p>
                    <div class="d-flex">
                        <form action="javascript:void(0);" method="POST" class="mr-2" >
                            <input type="hidden" name="idRevista" value="${revista.idRevista}">
                            <input type="hidden" name="username" value="${param.username}">
                            <button type="submit" class="btn btn-primary">
                                Me Gusta <span class="badge badge-light">${revista.likes}</span>
                            </button>
                        </form>
                        <form action="javascript:void(0);" method="POST" class="mr-2" >
                            <input type="hidden" name="idRevista" value="${revista.idRevista}">                                
                            <input type="hidden" id="username" value="${param.username}">                                
                            <button type="submit" class="btn btn-info">Ver Comentarios</button>
                        </form>
                        <form action="javascript:void(0);" method="POST" class="mr-2" onsubmit="event.preventDefault();
                                suscripcion('${revista.idRevista}');">
                            <input type="hidden" name="idRevista" value="${revista.idRevista}">                                
                            <input type="hidden" id="username" value="${param.username}">                                
                            <button type="submit" class="btn btn-info">Suscribirse</button>
                        </form>
                    </div>
                </div>                    
            </c:forEach>
        </div>
        <div class="container mt-4">
            <h2 class="text-center">Revistas suscrito </h2>
            <div class="list-group">
                <c:forEach var="revista" items="${suscripcion}">
                    <div class="list-group-item mb-3">
                        <h4>${revista.titulo}</h4>
                        <p>${revista.descripcion}</p>
                        <div class="d-flex">
                            <form action="javascript:void(0);" method="POST" class="mr-2" onsubmit="event.preventDefault();
                                    meGusta('${revista.idRevista}', '${param.username}');">
                                <input type="hidden" name="idRevista" value="${revista.idRevista}">
                                <input type="hidden" name="username" value="${param.username}">
                                <button type="submit" class="btn btn-primary">
                                    Me Gusta <span class="badge badge-light">${revista.likes}</span>
                                </button>
                            </form>
                            <form action="javascript:void(0);" method="POST" class="mr-2" onsubmit="event.preventDefault();
                                    agregarComentario('${revista.idRevista}');">
                                <button type="submit" class="btn btn-info">Agregar Comentario</button>
                                <input type="hidden" name="idRevista" value="${revista.idRevista}">                               
                                <input type="hidden" id="username" value="${param.username}">                               
                                <textarea id="comentario${revista.idRevista}" placeholder="Escribe tu comentario aquí..." required maxlength="200"></textarea>
                            </form>
                            <form class="mr-2">
                                <input type="hidden" name="idRevista" value="${revista.idRevista}">
                                <button type="button" class="btn btn-info" data-toggle="modal" 
                                        data-target="#modalRevista${revista.idRevista}">
                                    Previsualizar
                                </button>
                            </form>
                        </div>
                    </div>                    
                </c:forEach>
            </div>

        </div>
        <div class="container mt-4">
            <h2 class="text-center">Revistas </h2>
            <div class="list-group">
                <c:forEach var="revista" items="${revistasAprobadas}">
                    <div class="list-group-item mb-3">
                        <h4>${revista.titulo}</h4>
                        <p>${revista.descripcion}</p>
                        <div class="d-flex">
                            <form action="javascript:void(0);" method="POST" class="mr-2" >
                                <input type="hidden" name="idRevista" value="${revista.idRevista}">
                                <input type="hidden" name="username" value="${param.username}">
                                <button type="submit" class="btn btn-primary">
                                    Me Gusta <span class="badge badge-light">${revista.likes}</span>
                                </button>
                            </form>
                            <form action="javascript:void(0);" method="POST" class="mr-2" >
                                <input type="hidden" name="idRevista" value="${revista.idRevista}">                                
                                <input type="hidden" id="username" value="${param.username}">                                
                                <button type="submit" class="btn btn-info">Ver Comentarios</button>
                            </form>
                            <form action="javascript:void(0);" method="POST" class="mr-2" onsubmit="event.preventDefault();
                                    suscripcion('${revista.idRevista}');">
                                <input type="hidden" name="idRevista" value="${revista.idRevista}">                                
                                <input type="hidden" id="username" value="${param.username}">                                
                                <button type="submit" class="btn btn-info">Suscribirse</button>
                            </form>
                        </div>
                    </div> 
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
                                    <h6><strong>Descripción:</strong> ${revista.descripcion}</h6>
                                    <div class="embed-responsive embed-responsive-16by9">
                                        <iframe class="embed-responsive-item" src="${revista.pathArchivo}" type="application/pdf" width="100%" height="500px"></iframe>
                                    </div>
                                </div>                                
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary">Perfil Editor</button>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <script src="js/MeGusta.js"></script>
        <script src="js/Editor.js"></script>
    </body>
</html>
