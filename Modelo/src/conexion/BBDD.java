package conexion;

import java.sql.*;

public class BBDD {

	private String nombreBD = "bdproyecto";
	private String usuario = "root";
	private String password = "root";
	private String url = "jdbc:mysql://localhost/" + nombreBD;

	Connection conn = null;

	// constructor
	public BBDD() {

	}

	public Connection getConnection() {
		return conn;
	}

	public void conectar() {
		try {
			// obtener el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// obtener la conexion
			conn = DriverManager.getConnection(url, usuario, password);
			if (conn != null) {
				//System.out.println("Conexion exitosa a la BBDD: " + nombreBD);
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void desconectar() {
		conn = null;
	}
}
