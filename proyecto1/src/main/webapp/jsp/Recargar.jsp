<%-- 
    Document   : Recargar
    Created on : Sep 19, 2024, 6:40:47 AM
    Author     : alesso
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">RECARGAR CARTERA</a>
                <div class="collapse navbar-collapse">                   
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                        <li class="nav-item mr-2">
                            <form action="${pageContext.servletContext.contextPath}/Anunciante" method="POST">
                                <input type="hidden" name="username" value="${param.username}">
                                <button class="btn btn-primary" type="submit">Crear Anuncio</button>
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
        <div class="container mt-5">
            <h3>Recargar Cartera</h3>
            <p>${param.username} , ${cartera != null ? cartera : 0}</p>
            <p>Saldo actual en la cartera: <strong>${cartera != null ? cartera : 0}</strong> USD</p>

            <form id="recargarCarteraForm" method="POST" action="${pageContext.servletContext.contextPath}/CargarCartera">
                <input type="hidden" id="username" name="username" value="${param.username}">
                <input type="hidden" id="cartera" name="cartera" value="${cartera != null ? cartera : 0}">
                <div class="form-row">
                    <div class="form-group col-md-4">
                        <label for="cantidad">Cantidad a Recargar</label>
                        <input type="number" class="form-control" id="cantidad" name="cantidad" min="1" required placeholder="Ingrese la cantidad en USD">
                    </div>
                </div>

                <div class="text-center mt-4">
                    <button type="submit" class="btn btn-success">Recargar</button>
                </div>
            </form>
        </div>
        <script src="js/Cartera.js"></script>

    </body>

</html>
