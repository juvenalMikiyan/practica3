package com.emergentes.controlador;

import com.emergentes.modelo.Categoria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op") != null ? request.getParameter("op") : "view";
        int id;
        
        if (op.equals("nuevo")) {
            request.setAttribute("categoria", new Categoria());
            request.getRequestDispatcher("edit_categoria.jsp").forward(request, response);
        }
        
        if (op.equals("editar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) ses.getAttribute("listaCategorias");
            request.setAttribute("categoria", obtenerRegistro(listaCategorias, id));
            request.getRequestDispatcher("edit_categoria.jsp").forward(request, response);
        }
        
        if (op.equals("eliminar")) {
            int pos;
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) ses.getAttribute("listaCategorias");
            pos = ubicarPosicion(listaCategorias, id);
            listaCategorias.remove(pos);
            ses.setAttribute("listaCategorias", listaCategorias);
            response.sendRedirect("categoria.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Categoria> listaCategorias = (ArrayList<Categoria>) ses.getAttribute("listaCategorias");
        if (id == 0) {
            Categoria categoria = new Categoria();
            categoria.setId(generarId(listaCategorias));
            categoria.setCategoria(request.getParameter("categoria"));
            listaCategorias.add(categoria);
            ses.setAttribute("listaCategorias", listaCategorias);
            response.sendRedirect("categoria.jsp");
        } else {
            int pos = ubicarPosicion(listaCategorias, id);
            Categoria categoria = obtenerRegistro(listaCategorias, id);
            categoria.setCategoria(request.getParameter("categoria"));
            listaCategorias.set(pos, categoria);
            ses.setAttribute("listaCategorias", listaCategorias);
            response.sendRedirect("categoria.jsp");
        }
    }
    
    private int ubicarPosicion(ArrayList<Categoria> listaCategorias, int id) {
        int pos = -1;
        for (int i = 0; i < listaCategorias.size(); i++) {
            if (listaCategorias.get(i).getId() == id) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    private Categoria obtenerRegistro(ArrayList<Categoria> listaCategorias, int id) {
        Categoria categoria = null;
        for (int i = 0; i < listaCategorias.size(); i++) {
            if (listaCategorias.get(i).getId() == id) {
                categoria = listaCategorias.get(i);
                break;
            }
        }
        return categoria;
    }
    
    private int generarId(ArrayList<Categoria> listaCategorias) {
        int id = listaCategorias.size();
        if (id == 0) {
            return 1;
        } else {
            return id+1;
        }
    }

}
