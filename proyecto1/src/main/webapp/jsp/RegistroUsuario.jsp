<%-- 
    Document   : RegistroUsuario
    Created on : Sep 6, 2024, 10:47:40 PM
    Author     : alesso
--%>

<%@page import="com.mycompany.proyecto1.backend.sql.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registro de Usuario</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/register.css">
    </head>
    <body>
        <header class="header-bar">
            <div class="container">
                <nav class="navbar navbar-expand-lg navbar-light">
                    <a class="navbar-brand" >APP REVISTAS</a>
                    <div class="collapse navbar-collapse">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link btn btn-primary" href="/proyecto1">Regresar</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>        
        <div class="container mt-5">
            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>
            </c:if>
            <h2 class="text-center mb-4">Ingresa tus Datos</h2>            
            <div class="row">
                <div class="col-md-6">
                    <form action="${pageContext.servletContext.contextPath}/RegistroUsuario" method="post" onsubmit="return validateForm()" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="username">Nombre de Usuario</label>
                            <input type="text" class="form-control" id="username" name="username" maxlength="50" required oninvalid>
                        </div>
                        <div class="form-group">
                            <label for="fileUpload">Seleccionar archivo</label>
                            <input type="file" class="form-control-file" id="fileUpload" name="fileUpload" accept="image/*" onchange="previewImage(event)" required oninvalid>
                        </div>

                        <div id="imagePreviewContainer" class="mb-3">
                            <img id="imagePreview" style="max-width: 100px; max-height: 100px;">
                        </div>

                        <div class="form-group">
                            <label for="password" id="passwordLabel">Contraseña</label>
                            <input type="password" class="form-control" id="password" name="password" required oninvalid>
                            <small id="passwordHelp" class="text-success d-none">Contraseña Aceptada</small>
                        </div>

                        <div class="form-group">
                            <label for="confirmPassword" id="confirmPasswordLabel">Confirmar Contraseña</label>
                            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required oninvalid>
                            <small id="confirmPasswordHelp" class="text-danger d-none">Las contraseñas deben coincidir</small>
                        </div>

                        <div class="form-group">
                            <label for="userType">Seleccione su tipo de usuario</label>
                            <select class="form-control" id="userType" name="userType" required oninvalid>
                                <option>LECTOR</option>
                                <option>ADMINISTRADOR</option>
                                <option>EDITOR</option>
                                <option>ANUNCIANTE</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="description">Descripción</label>
                            <textarea class="form-control" id="description" maxlength="200" name="description" rows="3" placeholder="Descripcion" required oninvalid></textarea>
                        </div>
                </div>
                <div class="col-md-6">
                    <h4>Selecciona tus Preferencias</h4>
                    <p>Haz click para agregar tus intereses</p>
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
                    <input type="hidden" id="selectedTagsInput" name="selectedTagsInput" required oninvalid>
                </div>                        
            </div>
            <button type="submit" class="btn btn-primary" id="enviar">Registrarse</button>
        </form>
    </div>
</div>

<script src="js/Registro.js">
</script>

</body>

</html>


