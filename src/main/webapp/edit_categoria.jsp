
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Categoria"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Categoria categoria = (Categoria) request.getAttribute("categoria");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Categoria</h1>
        <form action="CategoriaController" method="POST">
            <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="id" min="1" value="${categoria.id}" required>
                    </td>
                </tr>
                <tr>
                    <td>Categoría</td>
                    <td>
                        <input type="text" name="categoria" value="${categoria.categoria}" required>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit">Enviar</button>
                    </td>
                </tr>
            </table>
        </form>
        <p>
            <a href="categoria.jsp">Volver al listado</a>
        </p>
    </body>
</html>
