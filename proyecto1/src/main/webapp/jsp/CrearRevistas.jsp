<%-- 
    Document   : CrearRevistas
    Created on : Sep 16, 2024, 6:40:02 PM
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
        <link rel="stylesheet" href="css/register.css">
    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="#">EDITOR</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/RevistasCreadas" method="GET">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Ver revistas creadas</button>
                            </form>
                        </li>
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/ReporteEditor" method="POST">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Reportes</button>
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
        <div class="container-fluid">
            <div class="row justify-content-center">
                <main role="main" class="col-md-12 px-md-4">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-6 mb-4">
                                <form action="${pageContext.servletContext.contextPath}/Revista" method="POST" enctype="multipart/form-data" id="formRegistrarRevista" >
                                    <h3 class="text-center">Datos de Revista</h3>

                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="tituloRevista">Título</label>
                                            <input type="text" class="form-control" id="tituloRevista" name="tituloRevista" placeholder="Título" maxlength="50" required>
                                        </div>

                                        <div class="form-group col-md-6">
                                            <label for="versionRevista">Versión</label>
                                            <input type="text" class="form-control" id="versionRevista" name="versionRevista" placeholder="Versión" maxlength="10" required>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-6">
                                            <label for="fileUpload">Seleccionar archivo</label>
                                            <input type="file" class="form-control-file" id="fileUpload" name="fileUpload" accept="application/pdf" required>
                                        </div>
                                        <div class="form-group col-md-6">
                                            <label for="dateRevista">Fecha</label>
                                            <input type="date" class="form-control" id="dateRevista" name="dateRevista" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="descripcionRevista">Descripción</label>
                                        <textarea class="form-control" id="descripcionRevista" name="descripcionRevista" rows="3" placeholder="Descripción" maxlength="150" required></textarea>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-md-4">
                                            <label for="esGratuita">¿Es gratuita?</label>
                                            <input type="checkbox" class="form-control" id="esGratuita" name="esGratuita">
                                        </div>

                                        <div class="form-group col-md-4">
                                            <label for="precioRevista">Precio</label>
                                            <input type="number" class="form-control" id="precioRevista" name="precioRevista" value="1" min="1" step="any">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label>Ajustes de publicación</label>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" id="permitirComentarios" name="permitirComentarios">
                                            <label class="form-check-label" for="permitirComentarios">Permitir Comentarios</label>
                                        </div>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" id="permitirMeGusta" name="permitirMeGusta">
                                            <label class="form-check-label" for="permitirMeGusta">Permitir 'Me Gusta'</label>
                                        </div>
                                        <div class="form-check">
                                            <input type="checkbox" class="form-check-input" id="permitirSubscripcion" name="permitirSubscripcion">
                                            <label class="form-check-label" for="permitirSubscripcion">Permitir Subscriptores</label>
                                        </div>
                                    </div>

                                    <div class="text-center mt-4">
                                        <button type="submit" class="btn btn-primary" id="crear">Crear Revista</button>
                                    </div>                                
                            </div>
                            <div class="col-md-6 mb-4">
                                <h3 class="text-center">Seleccione las etiquetas relacionadas</h3>
                                <div class="form-group">
                                    <label for="categories">Categorías y etiquetas existentes</label>
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
                                </form>
                                <form id="agregarEtiquetaForm" action="${pageContext.servletContext.contextPath}/Etiqueta" method="POST">
                                    <div class="form-row align-items-center">
                                        <div class="col-md-8">
                                            <input type="text" class="form-control" id="nuevaEtiqueta" name="nuevaEtiqueta" placeholder="Nueva Etiqueta" required>
                                        </div>
                                        <div class="col-md-4">
                                            <button type="submit" class="btn btn-success btn-block" id="agregar">Agregar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
            </div>
        </main>
    </div>
</div>

<script src="js/Editor.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>      
<script>
    document.addEventListener('DOMContentLoaded', function () {
        new AgregarEtiqueta('#agregarEtiquetaForm');

        new AgregarRevista('#formRegistrarRevista');
    });

    function toggleInput() {
        var checkbox = document.getElementById("esGratuita");
        var input = document.getElementById("precioRevista");

        if (checkbox.checked) {
            input.disabled = true;
            input.value = 0;
        } else {
            input.disabled = false;
            input.value = 1;
        }
    }

    document.getElementById('crear').addEventListener('click', function () {
        const selectedTagsContainer = document.getElementById('selectedTags');
        const selectedTags = Array.from(selectedTagsContainer.children)
                .map(tag => tag.textContent.trim());
        document.getElementById('selectedTagsInput').value = selectedTags.join(',');

        localStorage.setItem('selectedTags', JSON.stringify(selectedTags));
    });
</script>
</body>
