package com.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.conexion.Conexion;
import com.dao.CapturaDAO;
import com.dao.ConsultaDAO;
import com.dao.PaqueteDAO;
import com.dao.UsuarioDAO;
import com.model.Captura;
import com.model.Consulta;
import com.model.Paquete;
import com.model.Usuario;
import com.sniffer.*;
import com.validacion.Validacion;
import com.validacion.Errores;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int idUsuario = 0;
	Sniffer s;
	Errores er;
	Validacion v;
	
	private Connection connection;
	private PreparedStatement statement;
	
	public float CalculatePct(int num, int total)
	{
	  return total != 0 ? (num * 100.0f) / (total * 1.0f) : 0;
	}
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opcion = request.getParameter("opcion");
		
		
		if(opcion.equals("realizar")) {
			
			System.out.println("Usted a elegido realizar una consulta");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/RealizarConsulta.jsp");
			requestDispatcher.forward(request, response);
			
		}else if(opcion.equals("listar")) {
			
			System.out.println("Usted a elegido listar las consultas");
			
			ConsultaDAO consultaDAO = new ConsultaDAO();
			List<Consulta> listaConsulta = new ArrayList<>();
			
			try {
				listaConsulta = consultaDAO.obtener(idUsuario);
				for (Consulta consulta : listaConsulta) {
					System.out.println(consulta);
					
				} 
				
				request.setAttribute("listaConsulta", listaConsulta);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/ListaConsulta.jsp");
				requestDispatcher.forward(request, response);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}else if(opcion.equals("paquete")){
			
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("Mostrar Paquetes de esta Consulta " + id);
			
			
			PaqueteDAO paq = new PaqueteDAO();
			List<Paquete> listapaquete = new ArrayList<>();
			

			try {
				listapaquete = paq.obtener(id);
				for (Paquete paquete : listapaquete) {
					System.out.println(paquete);
					
				} 
				
				request.setAttribute("listapaquete", listapaquete);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/ListaPaquete.jsp");
				requestDispatcher.forward(request, response);
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			
		}else if(opcion.equals("captura")) {
			int id=Integer.parseInt(request.getParameter("id"));
			System.out.println("ID DE LA CAPTURA: " + id);
			
			CapturaDAO capturaDAO = new CapturaDAO();
			Captura captura = new Captura();
			try {
				captura = capturaDAO.obtenerCaptura(id);
				
				System.out.println(captura.toString());
				
				request.setAttribute("captura", captura);  
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/Captura.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if (opcion.equals("estadistica")) {
			
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/Estadistica.jsp");
			requestDispatcher.forward(request, response);
			
		}else if(opcion.equals("estadistica1")){
			
		
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			
			int HTTP = 0;
			int FTP = 0;
			int DNS = 0;
			int SMTP = 0;
			int POP3 = 0;
			int DHCP = 0;
			int HTTPS = 0;
			int FTPS = 0;
			int Otro = 0;
			
			ResultSet resultSet = null;
			String sql = null;
			
			try {
				connection = obtenerConexion();
				sql = "SELECT p.id_consulta , p.id_servicio \r\n"
						+ "FROM paquete p ,consulta c \r\n"
						+ "WHERE p.id_consulta = c.id_consulta";
				
				statement = connection.prepareStatement(sql);
			 
				
				resultSet = statement.executeQuery();
				
				while (resultSet.next()) {
					
					int servicio = resultSet.getInt(2);
					
					if(servicio==1) HTTP++; 
					if(servicio==2) FTP++;
					if(servicio==3) DNS++;
					if(servicio==4) SMTP++;
					if(servicio==5) POP3++;
					if(servicio==6) DHCP++;
					if(servicio==7) HTTPS++;
					if(servicio==8) FTPS++;
					if(servicio==9) Otro++;
					
				}
				int total = HTTP+FTP+DNS+SMTP+POP3+DHCP+HTTPS+FTPS+Otro;
			 
				
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("HTTP: " + String.valueOf(CalculatePct(HTTP,total)) + "%", HTTP);
				data.setValue("FTP: " + String.valueOf(CalculatePct(FTP,total))+ "%", FTP);
				data.setValue("DNS: " + String.valueOf(CalculatePct(DNS,total))+ "%", DNS);
				data.setValue("SMTP: " + String.valueOf(CalculatePct(SMTP,total))+ "%", SMTP);
				data.setValue("POP3: " + String.valueOf(CalculatePct(POP3,total))+ "%", POP3);
				data.setValue("DHCP: " + String.valueOf(CalculatePct(DHCP,total))+ "%", DHCP);
				data.setValue("HTTPS: " + String.valueOf(CalculatePct(HTTPS,total))+ "%", HTTPS);
				data.setValue("FTPS: " + String.valueOf(CalculatePct(FTPS,total))+ "%", FTPS);
				data.setValue("Otro: " + String.valueOf(CalculatePct(Otro,total))+ "%", Otro);
				
				JFreeChart chart = ChartFactory.createPieChart3D("Servicio que mas se utiliza", data, true,true,true);
				
				int ancho = 750;
				int alto = 600;
				
				ChartUtilities.writeChartAsPNG(out,chart,ancho,alto);
				
				statement.close();
				resultSet.close();
				connection.close();
				
			} catch (SQLException e1) {
	
				e1.printStackTrace();
			}
			
			
			
		} else if(opcion.equals("estadistica2")) {
			
			response.setContentType("image/PNG");
			OutputStream out = response.getOutputStream();
			
			ResultSet resultSet = null;
			String sql = null;
			
			DefaultCategoryDataset data = new DefaultCategoryDataset(); 
			
			try {
				connection = obtenerConexion();
				sql = "SELECT direccionIp_source, COUNT(direccionIp_source) \r\n"
						+ "FROM captura\r\n"
						+ "group by direccionIp_source";
				
				statement = connection.prepareStatement(sql);
				
				resultSet = statement.executeQuery();
				
				while(resultSet.next()) {
					 
					data.addValue(resultSet.getInt(2),"Direccion de procedencia",resultSet.getString(1));
					
				}
				 
				
				
				 
				JFreeChart chart = ChartFactory.createBarChart("Direccion ip que consume mas servicios", "Direcciones ip",
						   "Cantidad de veces que se repite", data,PlotOrientation.HORIZONTAL, true, true, false);
				
				
				int ancho = 750;
				int alto = 600;
				
				ChartUtilities.writeChartAsPNG(out,chart,ancho,alto);
				 
				statement.close();
				resultSet.close();
				connection.close();
			
		}catch (SQLException e1) {
			
			e1.printStackTrace();
		}
			
	}else if(opcion.equals("consultasUsuarios")) {
		
		System.out.println("Usted a elegido listar otras consultas");
		
		ConsultaDAO consultaDAO = new ConsultaDAO();
		List<Consulta> listaConsulta = new ArrayList<>();
		
		try {
			listaConsulta = consultaDAO.obtener();
			for (Consulta consulta : listaConsulta) {
				System.out.println(consulta);
				
			} 
			
			request.setAttribute("listaConsulta", listaConsulta);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Views/RealizarConsultaUsuario.jsp");
			requestDispatcher.forward(request, response);
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		 
		 
		
		
	}else if(opcion.equals("estadistica3")) {
		
		response.setContentType("image/PNG");
		OutputStream out = response.getOutputStream();
		
		ResultSet resultSet = null;
		String sql = null;
		
		DefaultCategoryDataset data = new DefaultCategoryDataset(); 
		
		try {
			connection = obtenerConexion();
			sql = "SELECT direccionIp_destino, COUNT(direccionIp_destino) \r\n"
					+ "FROM captura\r\n"
					+ "group by direccionIp_source";
			
			statement = connection.prepareStatement(sql);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				 
				data.addValue(resultSet.getInt(2),"Direccion de procedencia",resultSet.getString(1));
				
			}
			 
			
			
			 
			JFreeChart chart = ChartFactory.createBarChart("Direccion ip destino mas repetido", "Direcciones ip",
					   "Cantidad de veces que se repite", data,PlotOrientation.HORIZONTAL, true, true, false);
			
			
			int ancho = 750;
			int alto = 600;
			
			ChartUtilities.writeChartAsPNG(out,chart,ancho,alto);
			 
			statement.close();
			resultSet.close();
			connection.close();
		
	}catch (SQLException e1) {
		
		e1.printStackTrace();
	}
		
		
		
	}else if(opcion.equals("eliminar")) {
		
		ConsultaDAO consultaDAO = new ConsultaDAO();
		int id=Integer.parseInt(request.getParameter("id"));
		try {
			consultaDAO.eliminar(id);
			System.out.println("Registro eliminado");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("AccionesUsuario.jsp");
			requestDispatcher.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}else if(opcion.equals("estadistica4")) {
		
		response.setContentType("image/PNG");
		OutputStream out = response.getOutputStream();
		
		ResultSet resultSet = null;
		String sql = null;
		
		
		DefaultCategoryDataset data = new DefaultCategoryDataset(); 
		
		try {
			connection = obtenerConexion();
			sql = "SELECT fechaCaptura, SUM(longitud) \r\n"
					+ "FROM paquete\r\n"
					+ "GROUP BY   fechaCaptura;\r\n";
			
			statement = connection.prepareStatement(sql);
			
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				 
				data.addValue(resultSet.getInt(2),"Longitud de paquete (bytes)",resultSet.getString(1));
				
			}
			
			
			JFreeChart chart = ChartFactory.createLineChart ("Consumo de ancho de banda", "Fecha Captura", 
					 "Longitud total de los paquetes (bytes)", data, PlotOrientation.VERTICAL, true, true, false);
			
			
			
			CategoryPlot categoryPlot = chart.getCategoryPlot();
			CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
			
			categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90);
			
			int ancho = 750;
			int alto = 600;
			
			ChartUtilities.writeChartAsPNG(out,chart,ancho,alto);
			
			
			statement.close();
			resultSet.close();
			connection.close();
		}catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	}
	 
		
 
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String opcion = request.getParameter("opcion");

		if(opcion.equals("login")) {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			int id =  Integer.parseInt(request.getParameter("id_usuario"));
			String pass = request.getParameter("contraseña");

			
			try {
				if(usuarioDAO.loginValidate(id,pass)) {
					System.out.println("Ingreso Usuario: " + id );
					idUsuario = id;
					Usuario u = new Usuario();
					u.setId_usuario(id);
					u.setContraseña(pass);
					u.setTipo_usuario(1);
					if(usuarioDAO.loginTipo(u)) {
						response.sendRedirect("AccionesUsuario.jsp");
					}else{
						u.setId_usuario(id);
						u.setContraseña(pass);
						u.setTipo_usuario(2);
						
						if(usuarioDAO.loginTipo(u)) {
							response.sendRedirect("gestionUsuario.jsp");
						}
					}
					 
			
				}else {
					v = new Validacion();
					v.sendError(Errores.ERROR_LOGIN, response.getWriter(), response);
					
				}
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			 
			
		}else if(opcion.equals("consulta")) {
			
			s = new Sniffer();
			long t = System.currentTimeMillis();
			Consulta con = new Consulta();
			ConsultaDAO c = new ConsultaDAO();
			
			
			try {
				java.sql.Date z = new java.sql.Date(t);
				
				int tiempo_consulta = 0;
				v = new Validacion();
				
				
				
				if(!v.isNumber(request.getParameter("tiempo_consulta"))) {
					
					 
					v.sendError(Errores.ERROR_EXCEPCION_NUMERO, response.getWriter(), response);
					response.setHeader("Refresh", "0; http://localhost:8080/ProyectoBD/login?opcion=realizar");
					return;
				}
				
				
				tiempo_consulta = Integer.parseInt(request.getParameter("tiempo_consulta")); 
				
				con.setFecha_inicio(z);
				con.setTiempoConsulta(tiempo_consulta);
				con.setId_usuario(idUsuario);
				
				c.guardar(con);
				System.out.println("Registro guardado consulta satsfactoriamente");
				
				 
				
				int tiempo = tiempo_consulta*1000;

				s.sniff(tiempo);
	
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("AccionesUsuario.jsp");
				requestDispatcher.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			 
			
			
			
		}
		

	 
		//doGet(request, response);
	}
	
	//OBTENER CONEXION
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
		
	}

}
