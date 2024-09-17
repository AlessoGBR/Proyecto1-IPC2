<%-- 
    Document   : InicioDeSesion
    Created on : Sep 8, 2024, 7:11:31 PM
    Author     : alesso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inicio de Sesión</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="CSS/login.css">
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

        <main class="login-container">              
            <div class="container mt-5">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        ${error}
                    </div>
                </c:if>
                <div class="row justify-content-center">
                    <div class="col-md-4">
                        <h2 class="text-center">Iniciar Sesión</h2>
                        <form method="post" action="${pageContext.servletContext.contextPath}/InicioSesion">
                            <div class="form-group">
                                <label for="username">Nombre de Usuario</label>
                                <input type="text" class="form-control" id="username" name="username" required oninvalid>
                            </div>
                            <div class="form-group">
                                <label for="password">Contraseña</label>
                                <input type="password" class="form-control" id="password" name="password" required oninvalid >
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
                            <div class="text-center mt-3">
                                <a href="RegistroUsuario.jsp">¿No tienes una cuenta? Regístrate aquí</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <script >
                document.addEventListener('DOMContentLoaded', function () {
                    var errorDiv = document.querySelector('.alert-danger');
                    if (errorDiv && !errorDiv.textContent.trim()) {
                        errorDiv.style.display = 'none';
                    }
                });
                document.addEventListener("DOMContentLoaded", function () {
                    var elements = document.getElementsByTagName("INPUT");
                    for (var i = 0; i < elements.length; i++) {
                        elements[i].oninvalid = function (e) {
                            e.target.setCustomValidity("");
                            if (!e.target.validity.valid) {
                                e.target.setCustomValidity("Por favor llenar el campo");
                            }
                        };
                        elements[i].oninput = function (e) {
                            e.target.setCustomValidity("");
                        };
                    }
                });
            </script>
        </main>                            
    </body>
</html>