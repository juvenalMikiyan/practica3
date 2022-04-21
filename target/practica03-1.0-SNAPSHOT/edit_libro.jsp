
<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Libro"%>
<%@page import="com.emergentes.modelo.Categoria"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Libro libro = (Libro) request.getAttribute("libro");
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
        <h1>Libro</h1>
        <form action="LibroController" method="POST">
            <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                    <td></td>
                    <td>
                        <input type="hidden" name="id" min="1" value="${libro.id}" required>
                    </td>
                </tr>
                <tr>
                    <td>Título</td>
                    <td>
                        <input type="text" name="titulo" value="${libro.titulo}" required>
                    </td>
                </tr>
                <tr>
                    <td>Autor</td>
                    <td>
                        <input type="text" name="autor" value="${libro.autor}" required>
                    </td>
                </tr>
                <tr>
                    <td>Disponible</td>
                    <td>
                        <select name="disponible" required>
                            <option value="">--Seleccione una opción--</option>
                            <option value="Si" <c:if test="${libro.disponible == 'Si'}">selected</c:if>>Si</option>
                            <option value="No" <c:if test="${libro.disponible == 'No'}">selected</c:if>>No</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Categoría</td>
                    <td>
                        <select name="categoria" required>
                            <option value="">--Seleccione una opción--</option>
                            <c:forEach var="item" items="${listaCategorias}">
                            <option value="${item.categoria}" <c:if test="${libro.categoria == item.categoria}">selected</c:if>>${item.categoria}</option>
                            </c:forEach>
                        </select>
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
            <a href="libro.jsp">Volver al listado</a>
        </p>
    </body>
</html>
