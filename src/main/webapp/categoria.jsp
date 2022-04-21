<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Categoria"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
HttpSession ses = request.getSession();
ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) ses.getAttribute("listaCategorias");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Categorías</h1>
        <p><a href="CategoriaController?op=nuevo">Nuevo</a></p>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr>
                <th>Id</th>
                <th>Categoría</th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${listaCategorias}">
            <tr>
                <th>${item.id}</th>
                <th>${item.categoria}</th>
                <th>
                    <a href="CategoriaController?op=editar&id=${item.id}">Editar</a>
                    <a href="CategoriaController?op=eliminar&id=${item.id}">Eliminar</a>
                </th>
            </tr>
            </c:forEach>
        </table>
        <p>
            <a href="index.jsp">Volver al menú</a>
        </p>
    </body>
</html>
