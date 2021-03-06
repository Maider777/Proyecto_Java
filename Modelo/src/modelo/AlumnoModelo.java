package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.BBDD;
import entidades.Alumno;
import entidades.Grupo;

public class AlumnoModelo {

	// dar de alta a un alumno
	// pasar todos los datos menos la id porque es autoincrement
	public static void insertAlumno(Alumno alumno) {
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
			// hacer un insert
			// al hacer consulta, poner variables de la BBDD
			String values = "";
			if (alumno.getNombre() == null) {
				values += " null ";
			} else {
				values += "'" + alumno.getNombre() + "'";
			}
			if (alumno.getApellido1() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getApellido1() + "'";
			}
			if (alumno.getApellido2() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getApellido2() + "'";
			}
			if (alumno.getDni() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getDni() + "'";
			}
			if (alumno.getDireccion() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getDireccion() + "'";
			}
			if (alumno.getFechaDeNacimiento() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getFechaDeNacimiento() + "'";
			}
			if (alumno.getNombreDeTutor1() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getNombreDeTutor1() + "'";
			}
			if (alumno.getNombreDeTutor2() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getNombreDeTutor2() + "'";
			}
			if (alumno.getNumeroDeTelefonoTutor1() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getNumeroDeTelefonoTutor1() + "'";
			}
			if (alumno.getNumeroDeTelefonoTutor2() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getNumeroDeTelefonoTutor2() + "'";
			}
			if (alumno.getGrupo() == null) {
				values += ", null ";
			} else {
				values += ",'" + alumno.getGrupo().getId() + "'";
			}
			String query = "insert into alumnos (nombre, apellido1, apellido2, dni, direccion, fechaDeNacimiento, nombreDeTutor1, nombreDeTutor2, numeroDeTelefonoTutor1, numeroDeTelefonoTutor2, idGrupo) values ("
					+ values + ")";

			//System.out.println(query);
			s.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	// dar de baja a alumno(delete) por id
	public static void deleteAlumno(Alumno alumno) {
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
			String query = "delete from alumnos where id=" + alumno.getId();
			//System.out.println(query);
			// hacer un delete
			s.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	// asignar grupo a alumno(update)
	public static void updateAlumno(Alumno alumno) {
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
			// hacer casuisticas
			String filtro = "";
			if (alumno.getId() != 0) {
				filtro += " id='" + alumno.getId() + "' where id='" + alumno.getId() + "'";
			}
			String query = "update alumnos set idGrupo ='" + alumno.getGrupo().getId() + "' where id=" + alumno.getId();
			//System.out.println(query);
			// hacer un update
			s.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	// mostrar alumnos(select)
	public static ArrayList<Alumno> findAlumno(int id, String nombre, String apellido1, String apellido2) {
		// crear el objeto de la BBDD
		BBDD bd = new BBDD();
		// conectar a la BBDD
		bd.conectar();
		// obtener la conexion
		Connection conexion = bd.getConnection();
		// Statement para crear la query
		Statement s;
		ArrayList<Alumno> resultado;
		try {
			s = conexion.createStatement();
			// hacer un select
			// al hacer consulta, poner variables de la BBDD
			String filtro = "";

			if (id != 0) {
				filtro += "id=" + id;
			}
			if (nombre != null) {
				if (!filtro.equals("")) {
					filtro += " AND ";
				}
				filtro += "nombre='" + nombre + "' ";
			}
			if (apellido1 != null) {
				if (!filtro.equals("")) {
					filtro += " AND ";
				}
				filtro += "apellido1='" + apellido1 + "' ";
			}
			if (apellido2 != null) {
				if (!filtro.equals("")) {
					filtro += " AND ";
				}
				filtro += "apellido2='" + apellido2 + "' ";
			}
			if (id != 0 || nombre != null || apellido1 != null || apellido2 != null) {
				filtro = " where " + filtro;
			}
			String query = "select * from alumnos" + filtro + " order by nombre asc";
			//System.out.println(query);
			ResultSet res = s.executeQuery(query);
			resultado = new ArrayList<Alumno>();
			while (res.next()) {
				int idRes = Integer.parseInt(res.getString("id"));
				String nombreRes = res.getString("nombre");
				String apellido1Res = res.getString("apellido1");
				String apellido2Res = res.getString("apellido2");
				String dniRes = res.getString("dni");
				String direccionRes = res.getString("direccion");
				Date fechaDeNacimientoRes;
				if (res.getString("fechaDeNacimiento") == null) {
					fechaDeNacimientoRes = null;
				} else {
					fechaDeNacimientoRes = Date.valueOf(res.getString("fechaDeNacimiento"));
				}
				String nombreDeTutor1Res = res.getString("nombreDeTutor1");
				String nombreDeTutor2Res = res.getString("nombreDeTutor2");
				String numeroDeTelefonoTutor1Res = res.getString("numeroDeTelefonoTutor1");
				String numeroDeTelefonoTutor2Res = res.getString("numeroDeTelefonoTutor2");
				String idGrupoRes = res.getString("idGrupo");
				Grupo grupo;
				if (idGrupoRes == null) {
					grupo = null;
				} else {
					ArrayList<Grupo> grupos = GrupoModelo.findGrupos(idGrupoRes, 0, null);
					if (grupos.size() > 0) {
						grupo = grupos.get(0);
					} else {
						grupo = null;
					}
				}
				Alumno alumnoBD = new Alumno(idRes, nombreRes, apellido1Res, apellido2Res, dniRes, direccionRes,
						fechaDeNacimientoRes, nombreDeTutor1Res, nombreDeTutor2Res, numeroDeTelefonoTutor1Res,
						numeroDeTelefonoTutor2Res, grupo);
				resultado.add(alumnoBD);
			}
			return resultado;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		} finally {
			bd.desconectar();
		}

	}

	public static ArrayList<Alumno> findAlumnosGrupo(String idGrupo) {
		// crear el objeto de la BBDD
				BBDD bd = new BBDD();
				// conectar a la BBDD
				bd.conectar();
				// obtener la conexion
				Connection conexion = bd.getConnection();
				// Statement para crear la query
				Statement s;
				ArrayList<Alumno> resultado;
				try {
					s = conexion.createStatement();
					
					String query = "select A.* from alumnos A JOIN grupos G ON A.idGrupo=G.id WHERE G.id='" + idGrupo + "' order by nombre asc, apellido1 asc, apellido2 asc";
					//System.out.println(query);
					ResultSet res = s.executeQuery(query);
					resultado = new ArrayList<Alumno>();
					while (res.next()) {
						int idRes = Integer.parseInt(res.getString("id"));
						String nombreRes = res.getString("nombre");
						String apellido1Res = res.getString("apellido1");
						String apellido2Res = res.getString("apellido2");
						String dniRes = res.getString("dni");
						String direccionRes = res.getString("direccion");
						Date fechaDeNacimientoRes;
						if (res.getString("fechaDeNacimiento") == null) {
							fechaDeNacimientoRes = null;
						} else {
							fechaDeNacimientoRes = Date.valueOf(res.getString("fechaDeNacimiento"));
						}
						String nombreDeTutor1Res = res.getString("nombreDeTutor1");
						String nombreDeTutor2Res = res.getString("nombreDeTutor2");
						String numeroDeTelefonoTutor1Res = res.getString("numeroDeTelefonoTutor1");
						String numeroDeTelefonoTutor2Res = res.getString("numeroDeTelefonoTutor2");
						String idGrupoRes = res.getString("idGrupo");
						Grupo grupo;
						if (idGrupoRes == null) {
							grupo = null;
						} else {
							ArrayList<Grupo> grupos = GrupoModelo.findGrupos(idGrupoRes, 0, null);
							if (grupos.size() > 0) {
								grupo = grupos.get(0);
							} else {
								grupo = null;
							}
						}
						Alumno alumnoBD = new Alumno(idRes, nombreRes, apellido1Res, apellido2Res, dniRes, direccionRes,
								fechaDeNacimientoRes, nombreDeTutor1Res, nombreDeTutor2Res, numeroDeTelefonoTutor1Res,
								numeroDeTelefonoTutor2Res, grupo);
						resultado.add(alumnoBD);
					}
					return resultado;
				} catch (SQLException e1) {
					e1.printStackTrace();
					return null;
				} finally {
					bd.desconectar();
				}
	}
	// si es select, executeQuery
	// si es insert o delete, executeUpdate
	// en las select, solo preparar la consulta
	// en las select, sacar el ResultSet
}
