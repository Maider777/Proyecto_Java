package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.BBDD;
import entidades.Grupo;
import entidades.Profesor;

public class ProfesorModelo {

	// dar de alta a un profesor
	public static void insertProfesor(Profesor profesor) {
		// crear el objeto de la BBDD
		BBDD bd = new BBDD();
		// conectar a la BBDD
		bd.conectar();
		// obtener la conexion
		Connection conexion = bd.getConnection();
		// Statement para crear la query
		Statement s;
		try {
			s = conexion.createStatement();
			String values = "";
			if (profesor.getDNI() == null) {
				values += " null ";
			} else {
				values += "'" + profesor.getDNI() + "'";
			}
			if (profesor.getNombre() == null) {
				values += " null ";
			} else {
				values += ",'" + profesor.getNombre() + "'";
			}
			if (profesor.getApellido1() == null) {
				values += " null ";
			} else {
				values += ",'" + profesor.getApellido1() + "'";
			}
			if (profesor.getApellido2() == null) {
				values += " null ";
			} else {
				values += ",'" + profesor.getApellido2() + "'";
			}
			if (profesor.getFechaDeNacimiento() == null) {
				values += " null ";
			} else {
				values += ",'" + profesor.getFechaDeNacimiento() + "'";
			}
			if (profesor.getNumeroDeTelefono() == null) {
				values += " null ";
			} else {
				values += ",'" + profesor.getNumeroDeTelefono() + "'";
			}
			if (profesor.getEmail() == null) {
				values += " null ";
			} else {
				values += ",'" + profesor.getEmail() + "'";
			}
			if (profesor.getDireccion() == null) {
				values += " null ";
			} else {
				values += ",'" + profesor.getDireccion() + "'";
			}
			String query = "insert into profesores (dni, nombre, apellido1, apellido2, fechaDeNacimiento, numeroDeTelefono, email, direccion) values ("
					+ values + ")";
			//System.out.println(query);
			// hacer un insert
			// al hacer consulta, poner variables de la BBDD
			s.executeUpdate(query);
			// desconectar la BBDD
			bd.desconectar();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	// dar de baja a un profesor
	public static void deleteProfesor(Profesor profesor) {
		// crear el objeto de la BBDD
		BBDD bd = new BBDD();
		// conectar a la BBDD
		bd.conectar();
		// obtener la conexion
		Connection conexion = bd.getConnection();
		// Statement para crear la query
		Statement s;
		try {
			s = conexion.createStatement();
			String query = "delete from profesores where dni='" + profesor.getDNI() + "'";
			//System.out.println(query);
			// hacer un delete
			s.executeUpdate(query);
			// desconectar la BBDD
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	// dni, nombre, apellido1, apellido2
	// mostrar todos los profesores
	public static ArrayList<Profesor> findProfesores(String dni, String nombre, String apellido1, String apellido2, String email) {
		// crear el objeto de la BBDD
		BBDD bd = new BBDD();
		// conectar a la BBDD
		bd.conectar();
		// obtener la conexion
		Connection conexion = bd.getConnection();
		// Statement para crear la query
		Statement s;
		// crear array de resultado
		ArrayList<Profesor> resultado;
		try {
			s = conexion.createStatement();
			// hacer un select
			// crear string de filtro para ir concatenando la query
			String filtro = "";
			// si alguno de los datos es diferente de nulo, que haga el filtro con el
			// a?adido
			if (dni != null) {
				filtro += " dni='" + dni + "' ";
			}
			// cuando haya algo mas en el filtro, entonces a?adir el AND
			if (nombre != null) {
				if (!filtro.equals("")) {
					filtro += " AND ";
				}
				filtro += " nombre='" + nombre + "' ";
			}
			if (apellido1 != null) {
				if (!filtro.equals("")) {
					filtro += " AND ";
				}
				filtro += " apellido1='" + apellido1 + "' ";
			}
			if (apellido2 != null) {
				if (!filtro.equals("")) {
					filtro += " AND ";
				}
				filtro += " apellido2='" + apellido2 + "' ";
			}
			if (email != null) {
				if (!filtro.equals("")) {
					filtro += " AND ";
				}
				filtro += " email='" + email + "' ";
			}
			if (dni != null || nombre != null || apellido1 != null || apellido2 != null|| email!=null) {
				filtro = " where " + filtro;
			}
			String query = "select * from profesores" + filtro + " order by nombre asc, apellido1 asc, apellido2 asc";
			//System.out.println(query);
			// al hacer consulta, poner variables de la BBDD
			ResultSet res = s.executeQuery(query);
			// a?adir objeto de profesor al array
			resultado = new ArrayList<Profesor>();
			// hacer el while para ver el contenido del res
			while (res.next()) {
				String dniRes = res.getString("dni");
				String nombreRes = res.getString("nombre");
				String apellido1Res = res.getString("apellido1");
				String apellido2Res = res.getString("apellido2");
				Date fechaDeNacimientoRes=null;
				if(res.getString("fechaDeNacimiento")==null) {
					fechaDeNacimientoRes=null;
				}else {
					fechaDeNacimientoRes = Date.valueOf(res.getString("fechaDeNacimiento"));
				}
				String numeroDeTelefonoRes = res.getString("numeroDeTelefono");
				String emailRes = res.getString("email");
				String direccionRes = res.getString("direccion");
				Profesor profesorBD = new Profesor(dniRes, nombreRes, apellido1Res, apellido2Res, fechaDeNacimientoRes,
						numeroDeTelefonoRes, emailRes, direccionRes);
				resultado.add(profesorBD);
			}
			// si las consultas estan bien, revolver el resultado
			return resultado;
		} catch (SQLException e1) {
			e1.printStackTrace();
			// sino, devolver null
			return null;
		} finally {
			bd.desconectar();
		}
	}

	public static ArrayList<Grupo> findGruposProfesor(String dni){
		// crear el objeto de la BBDD
				BBDD bd = new BBDD();
				// conectar a la BBDD
				bd.conectar();
				// obtener la conexion
				Connection conexion = bd.getConnection();
				// Statement para crear la query
				Statement s;
				// crear array de resultado
				ArrayList<Grupo> resultado;
				try {
					s = conexion.createStatement();
				
					String query = "SELECT DISTINCT(grupo) AS id, nivel, clase from profesores P JOIN grupos_asignaturas GA ON P.dni=GA.profesor JOIN grupos G ON G.id=Ga.grupo WHERE P.dni='" + dni+"' ORDER BY G.id";
					//System.out.println(query);
					// al hacer consulta, poner variables de la BBDD
					ResultSet res = s.executeQuery(query);
					// a?adir objeto de profesor al array
					resultado = new ArrayList<Grupo>();
					// hacer el while para ver el contenido del res
					while (res.next()) {
						String idGrupoRes = res.getString("id");
						int nivelRes = res.getInt("nivel");
						String claseRes = res.getString("clase");
						Grupo grupo = new Grupo(idGrupoRes, nivelRes, claseRes);
						resultado.add(grupo);
					}
					// si las consultas estan bien, revolver el resultado
					return resultado;
				} catch (SQLException e1) {
					e1.printStackTrace();
					// sino, devolver null
					return null;
				} finally {
					bd.desconectar();
				}
	}
}
