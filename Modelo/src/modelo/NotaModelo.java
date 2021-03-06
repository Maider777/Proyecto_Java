package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.BBDD;
import entidades.Alumno;
import entidades.Nota;

public class NotaModelo {

	// crear nota
	public static void insertNota(Nota nota) {
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
			String values = "";
			if (nota.getAlumno() == null) {
				values += ", null ";
			} else {
				values += "'" + nota.getAlumno().getId() + "'";
			}
			if (nota.getAsignatura() == null) {
				values += ", null";
			} else {
				values += ",'" + nota.getAsignatura() + "'";
			}
			if (nota.getNotaGlobal() == 0) {
				values += " null ";
			} else {
				values += ",'" + nota.getNotaGlobal() + "'";
			}
			// al hacer consulta, poner variables de la BBDD
			String query = "insert into notas (idAlumno,asignatura,notaGlobal) values (" + values + ")";
			//System.out.println(query);
			s.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	public static void updateNota(Nota nota) {
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
			String query = "update notas set notaGlobal =" + nota.getNotaGlobal() + " where idAlumno=" + nota.getAlumno().getId() +" AND asignatura='"+nota.getAsignatura()+"'";
			//System.out.println(query);
			// hacer un update
			s.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			bd.desconectar();
		}
	}

	// mostrar todas las notas
	public static ArrayList<Nota> findNotas(int idAlumno, String asignatura, float notaGlobal) {
		// crear el objeto de la BBDD
		BBDD bd = new BBDD();
		// conectar a la BBDD
		bd.conectar();
		// obtener la conexion
		Connection conexion = bd.getConnection();
		// Statement para crear la query
		Statement s;
		ArrayList<Nota> resultado;
		try {
			s = conexion.createStatement();
			String filtro = "";
			if (idAlumno != 0) {
				filtro += " idAlumno=" + idAlumno + "";
			}
			if (asignatura != null) {
				if (!filtro.isEmpty()) {
					filtro += " AND ";
				}
				filtro += " asignatura='" + asignatura+"'";
			}
			if (notaGlobal != 0) {
				if (!filtro.isEmpty()) {
					filtro += " AND ";
				}
				filtro += " notaGlobal=" + notaGlobal + "";
			}

			if (idAlumno != 0 || asignatura != null || notaGlobal != 0) {
				filtro = " where " + filtro;
			}
			// hacer un select
			// al hacer consulta, poner variables de la BBDD
			String query = "select * from notas" + filtro;
			//System.out.println(query);
			ResultSet res = s.executeQuery(query);
			resultado = new ArrayList<Nota>();
			while (res.next()) {
				int alumnoIdRes = Integer.parseInt(res.getString("idAlumno"));
				String asignaturaRes = res.getString("asignatura");
				float notaGlobalRes = res.getFloat("notaGlobal");
				ArrayList<Alumno> arrayAlumnos = AlumnoModelo.findAlumno(alumnoIdRes, null, null, null);
				if (arrayAlumnos.size() != 0) {
					Alumno alumnobd = arrayAlumnos.get(0);
					Nota notaBD = new Nota(alumnobd, asignaturaRes, notaGlobalRes);
					resultado.add(notaBD);
				}
			}
			return resultado;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		} finally {
			bd.desconectar();
		}
	}
}
