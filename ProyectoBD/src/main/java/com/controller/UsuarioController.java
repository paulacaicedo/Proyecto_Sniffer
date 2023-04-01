package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UsuarioDAO;
import com.model.Usuario;
import com.validacion.Errores;
import com.validacion.Validacion;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet(description = "Administra las peticiones de la tabla usuarios", urlPatterns = { "/usuario" })
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Validacion v;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		
		if(opcion.equals("Crear Usuario")) {
			System.out.println("Usted a elegido crear un usuario");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/crear.jsp");
			requestDispatcher.forward(request, response);
			
		}else if (opcion.equals("listar")) {
			System.out.println("Usted a elegido listar los usuarios");
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> lista = new ArrayList<>();
			
			try {
				lista = usuarioDAO.obtener();
				for (Usuario usuario : lista) {
					System.out.println(usuario);
				} 
				
				request.setAttribute("lista", lista);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/listar.jsp");
				requestDispatcher.forward(request, response);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			 
		}else if(opcion.equals("meditar")) {
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("Editar id: " + id);
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario u = new Usuario();
			try {
				u = usuarioDAO.obtenerUsuario(id);
				System.out.println(u.toString());
				request.setAttribute("usuario", u);  
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/editar.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(opcion.equals("eliminar")) {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			int id=Integer.parseInt(request.getParameter("id"));
			try {
				usuarioDAO.eliminar(id);
				System.out.println("Registro eliminado");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/gestionUsuario.jsp");
				requestDispatcher.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		

		if(opcion.equals("guardar")) {
			
			v = new Validacion();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			Usuario usuario = new Usuario();

			if(!v.isNumber( request.getParameter("id_usuario"))) {
				
			 
				v.sendError(Errores.ERROR_EXCEPCION_NUMERO, response.getWriter(), response);
				response.setHeader("Refresh", "0; http://localhost:8080/ProyectoBD/usuario?opcion=Crear+Usuario");
				return;
			}
			
			usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
			
			if(v.hasBlank(request.getParameter("contraseña"))) {
				 
				v.sendError(Errores.ERROR_CARACTER_VACIO, response.getWriter(), response);
				response.setHeader("Refresh", "0; http://localhost:8080/ProyectoBD/usuario?opcion=Crear+Usuario");
				return;
			}
			
			usuario.setContraseña(request.getParameter("contraseña"));
			usuario.setTipo_usuario(1);
			try {
				usuarioDAO.guardar(usuario);
				System.out.println("Registro guardado satsfactoriamente");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else if(opcion.equals("editar")) {
		
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			try {
				usuarioDAO.editar(Integer.parseInt(request.getParameter("id_usuario")), request.getParameter("contraseña"));
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/gestionUsuario.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
			
		
		} 
		
	 
		//doGet(request, response);
	}

}
