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
        <link rel="stylesheet" href="css/inicio.css">
    </head>
    <header class="header-bar">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-light">
                <a class="navbar-brand">ADMINISTRADOR</a>
                <div class="collapse navbar-collapse">
                    <ul class="navbar-nav ml-auto d-flex align-items-center">
                         <li class="nav-item mr-3">
                            <a href="${pageContext.servletContext.contextPath}/Perfil">
                                <span class="navbar-text">
                                    Perfil
                                </span>
                            </a>
                        </li>
                        <li class="nav-item mr-3">
                            <span class="navbar-text">
                                Comision
                            </span>
                        </li>
                        <li class="nav-item mr-3">
                            <span class="navbar-text">
                                Anuncios
                            </span>
                        </li>
                        <li class="nav-item mr-3">
                            <span class="navbar-text">
                                Revistas
                            </span>
                        </li>
                        <li class="nav-item mr-3">
                            <span class="navbar-text">
                                Reportes
                            </span>
                        </li>
                        <li class="nav-item mr-3">
                            <a href="jsp/Perfil.jsp">
                            <img src="${param.photoUrl}" alt="Foto de perfil" class="img-profile rounded-circle" </a> 
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link btn btn-primary" href="/proyecto1/">Cerrar sesión</a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>


    <body>
        <p>usuario Creado, username:${param.username}, contra: ${param.password}, descripcion: ${param.description}, userType
            ${param.userType}
        <p>
    </body>
</html>
