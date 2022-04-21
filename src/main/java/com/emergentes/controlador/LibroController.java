package com.emergentes.controlador;

import com.emergentes.modelo.Libro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LibroController", urlPatterns = {"/LibroController"})
public class LibroController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op = request.getParameter("op") != null ? request.getParameter("op") : "view";
        int id;
        
        if (op.equals("nuevo")) {
            request.setAttribute("libro", new Libro());
            request.getRequestDispatcher("edit_libro.jsp").forward(request, response);
        }
        
        if (op.equals("editar")) {
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ArrayList<Libro> listaLibros = (ArrayList<Libro>) ses.getAttribute("listaLibros");
            request.setAttribute("libro", obtenerRegistro(listaLibros, id));
            request.getRequestDispatcher("edit_libro.jsp").forward(request, response);
        }
        
        if (op.equals("eliminar")) {
            int pos;
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            ArrayList<Libro> listaLibros = (ArrayList<Libro>) ses.getAttribute("listaLibros");
            pos = ubicarPosicion(listaLibros, id);
            listaLibros.remove(pos);
            ses.setAttribute("listaLibros", listaLibros);
            response.sendRedirect("libro.jsp");
        }
                     
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession ses = request.getSession();
        ArrayList<Libro> listaLibros = (ArrayList<Libro>) ses.getAttribute("listaLibros");
        if (id == 0) {
            Libro libro = new Libro();
            libro.setId(generarId(listaLibros));
            libro.setTitulo(request.getParameter("titulo"));
            libro.setAutor(request.getParameter("autor"));
            libro.setDisponible(request.getParameter("disponible"));
            libro.setCategoria(request.getParameter("categoria"));
            listaLibros.add(libro);
            ses.setAttribute("listaLibros", listaLibros);
            response.sendRedirect("libro.jsp");
        } else {
            int pos = ubicarPosicion(listaLibros, id);
            Libro libro = obtenerRegistro(listaLibros, id);
            libro.setTitulo(request.getParameter("titulo"));
            libro.setAutor(request.getParameter("autor"));
            libro.setDisponible(request.getParameter("disponible"));
            libro.setCategoria(request.getParameter("categoria"));
            listaLibros.set(pos, libro);
            ses.setAttribute("listaLibros", listaLibros);
            response.sendRedirect("libro.jsp");
        }
    }
    
    private int ubicarPosicion(ArrayList<Libro> listaLibros, int id) {
        int pos = -1;
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getId() == id) {
                pos = i;
                break;
            }
        }
        return pos;
    }
    
    private Libro obtenerRegistro(ArrayList<Libro> listaLibros, int id) {
        Libro libro = null;
        for (int i = 0; i < listaLibros.size(); i++) {
            if (listaLibros.get(i).getId() == id) {
                libro = listaLibros.get(i);
                break;
            }
        }
        return libro;
    }
    
    private int generarId(ArrayList<Libro> listaLibros) {
        int id = listaLibros.size();
        if (id == 0) {
            return 1;
        } else {
            return id+1;
        }
    }

}
