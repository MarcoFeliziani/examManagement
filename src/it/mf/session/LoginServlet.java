package it.mf.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -8456588024401763035L;
	
	private static final String DB_NAME = "esse3staging";
	private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	private static final String USERNAME = "root";
	private static final String PASSWORD = "toor";
	private static final String MAX_POOL = "250"; // set your own limit
	private static final String DATABASE_URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;	// + "?user" + USERNAME + "?password" + PASSWORD + "?MaxPooledStatements" + MAX_POOL;
	
	private Connection connection;
	private Properties properties;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String user = request.getParameter("user");
		String pwd = request.getParameter("pwd");
				
		int doceId = 0;
		int doceMatricola = 0;
    	String doceNome = null;
    	String doceCognome = null;		    		    	

		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select doce_nome, doce_cognome, doce_id, doce_matricola ");
			sb.append("from docenti ");
			sb.append("where doce_utente = ? ");
			sb.append("and doce_pwd = ? ");
			sb.append("group by doce_id, doce_matricola, doce_cognome, doce_nome");


			PreparedStatement pps = getConnection().prepareStatement(sb.toString());
			pps.setString(1, user);
			pps.setString(2, pwd);
			//pps.setString(2, pwd);
			ResultSet rs = pps.executeQuery();
			if (rs.next()) {
				doceId = rs.getInt("doce_id");
				doceMatricola = rs.getInt("doce_matricola");
				doceNome = rs.getString("doce_nome");
				doceCognome = rs.getString("doce_cognome");
			}
			// close connection
			pps.close();
			disconnect();
		} catch (SQLException ex) {
			ex.printStackTrace();			
		} finally {
			disconnect();
		}
		
		//Date oggi = new Date();
		
		if (doceId> 0 ) {
			HttpSession session = request.getSession();
			session.setAttribute("doceId",doceId);
			session.setAttribute("doceMatricola",doceMatricola);
			session.setAttribute("doceNome", doceNome);
			session.setAttribute("doceCognome", doceCognome);

			session.setMaxInactiveInterval(30 * 60);
			Cookie userName = new Cookie("user", user);
			userName.setMaxAge(30 * 60);
			response.addCookie(userName);
			response.sendRedirect(request.getContextPath() + "/CalendarioAppController");
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			PrintWriter out = response.getWriter();
			out.println("<script> alert('Utente o password non corretti.')</script>");
			rd.include(request, response);
		}

	}
	
	// connect database
	private Connection getConnection() {
		if (connection == null) {
			try {
				// DriverManager.registerDriver((Driver)
				// Class.forName(DATABASE_DRIVER).newInstance());
				Class.forName(DATABASE_DRIVER);
				connection = DriverManager.getConnection(DATABASE_URL, getProperties());

			} catch (ClassNotFoundException | SQLException e) {				
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	private Properties getProperties() {
		if (properties == null) {
			properties = new Properties();
			properties.setProperty("user", USERNAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("MaxPooledStatements", MAX_POOL);
		}
		return properties;
	}
	
	private void disconnect() {
	    if (connection != null) {
	        try {
	            connection.close();
	            connection = null;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	

}