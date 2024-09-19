<%-- 
    Document   : Anunciante
    Created on : Sep 19, 2024, 2:56:25 AM
    Author     : alesso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ANUNCIANTE</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>  
        <link rel="stylesheet" href="css/register.css">
    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">CREAR ANUNCIO</a>
                <div class="collapse navbar-collapse">                   
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/Recargar" method="POST">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Recarga Cartera</button>
                            </form>
                        </li>
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/LogOut" method="GET">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Cerrar sesion</button>
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
        <div class="container mt-4">
            <form method="POST" action="${pageContext.servletContext.contextPath}/CrearAnuncio" enctype="multipart/form-data" id="crearAnuncioForm">
                <input type="hidden" name="username" value="${param.username}">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="userType">Tipo de anuncio</label>
                        <select class="form-control" id="userType" name="userType" required onchange="updateFormFields()">
                            <option value="">SELECCIONA UN TIPO DE ANUNCIO</option>
                            <option value="TEXTO">TEXTO</option>
                            <option value="IMAGEN Y TEXTO">IMAGEN Y TEXTO</option>
                            <option value="VIDEO">VIDEO</option>
                        </select>
                    </div>
                </div>

                <div class="form-row" id="fileUploadDiv" style="display: none;">
                    <div class="form-group col-md-4">
                        <label for="fileUpload">Seleccionar archivo</label>
                        <input type="file" class="form-control-file" id="fileUpload" name="fileUpload" accept="image/*">
                        <img id="previewImage" src="" alt="Vista previa" style="display: none; max-width: 100%; margin-top: 10px;">
                    </div>
                </div>

                <div class="form-row" id="textAnuncioDiv" style="display: none;">
                    <div class="form-group col-md-4">
                        <label for="textAnuncio">Ingrese el texto del anuncio</label>
                        <textarea type="text" class="form-control" id="textAnuncio" name="textAnuncio"></textarea>
                    </div>
                </div>

                <div class="form-row" id="urlAnuncioDiv" style="display: none;">
                    <div class="form-group col-md-4">
                        <label for="urlAnuncio">Ingrese la URL del video</label>
                        <input type="text" class="form-control" id="urlAnuncio" name="urlAnuncio" placeholder="URL del video">
                    </div>
                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-primary" id="crear">Crear Anuncio</button>
                </div>
            </form>
        </div>
        <script src="js/Anunciante.js">
                   
        </script>

        <style>
            #previewImage {
                display: none;
                max-width: 250px;
                max-height: 250px;
                margin-top: 20px;
                object-fit: contain;
            }
        </style>
    </body>
</html>
