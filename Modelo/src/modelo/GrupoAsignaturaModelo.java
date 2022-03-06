package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.BBDD;
import entidades.Grupo;
import entidades.GrupoAsignatura;
import entidades.Profesor;

public class GrupoAsignaturaModelo {
	
	public static void insertGrupoAsignatura(GrupoAsignatura grupoAsignatura) {
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
			String values="";
			if (grupoAsignatura.getProfesor() == null) {
				values += " null ";
			} else {
				values += "'" + grupoAsignatura.getProfesor().getDNI() + "'";
			}
			if (grupoAsignatura.getAsignatura() == null) {
				values += ", null ";
			} else {
				values += ",'" + grupoAsignatura.getAsignatura() + "'";
			}
			if (grupoAsignatura.getGrupo() == null) {
				values += ", null ";
			} else {
				values += ",'" + grupoAsignatura.getGrupo().getId() + "'";
			}
			// al hacer consulta, poner variables de la BBDD
			String query = "insert into grupos_asignaturas (profesor,asignatura,grupo) values (" + values + ")";
			//System.out.println(query);
			s.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			bd.desconectar();
		}
	}

	// mostrar todos los grupos
		public static ArrayList<GrupoAsignatura> findGrupoAsignatura(String grupo, String profesor, String asignatura) {
			// crear el objeto de la BBDD
			BBDD bd = new BBDD();
			// conectar a la BBDD
			bd.conectar();
			// obtener la conexion
			Connection conexion = bd.getConnection();
			// Statement para crear la query
			Statement s;
			ArrayList<GrupoAsignatura> resultado;
			try {
				s = conexion.createStatement();
				String filtro = "";
				if (grupo != null) {
					filtro += " grupo='" + grupo + "'";
				}
				if (profesor != null) {
					if(!filtro.isEmpty()) {
						filtro+=" AND ";
					}
					filtro += " profesor='" + profesor +"'";
				}
				if (asignatura != null) {
					if(!filtro.isEmpty()) {
						filtro+=" AND ";
					}
					filtro += " asignatura='" + asignatura + "'";
				}

				if (grupo != null || profesor != null || asignatura != null) {
					filtro = " where " + filtro;
				}
				// hacer un select
				// al hacer consulta, poner variables de la BBDD
				String query = "select * from grupos_asignaturas" + filtro;
				//System.out.println(query);
				ResultSet res = s.executeQuery(query);
				resultado = new ArrayList<GrupoAsignatura>();
				while (res.next()) {
					String grupoRes = res.getString("grupo");
					String profesorRes = res.getString("profesor");
					String asignaturaRes = res.getString("asignatura");
					ArrayList<Grupo> grupos=GrupoModelo.findGrupos(grupoRes, 0, null);
					Grupo grupobd = null;
					if(grupos.size()>0) {
						grupobd=grupos.get(0);
					}
					ArrayList<Profesor> profesores=ProfesorModelo.findProfesores(profesorRes, null, null, null,null);
					Profesor profesorbd = null;
					if(profesores.size()>0) {
						profesorbd=profesores.get(0);
					}
					GrupoAsignatura grupoAsignaturaBD = new GrupoAsignatura(grupobd, profesorbd, asignaturaRes);
					resultado.add(grupoAsignaturaBD);
				}
				return resultado;
			} catch (SQLException e1) {
				e1.printStackTrace();
				return null;
			}finally {
				bd.desconectar();
			}
		}
}
