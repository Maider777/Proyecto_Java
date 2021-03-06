package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexion.BBDD;
import entidades.Grupo;

public class GrupoModelo {

	// crear grupo
	public static void insertGrupo(Grupo grupo) {
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
			if (grupo.getId() == null) {
				values += " null ";
			} else {
				values += "'" + grupo.getId() + "'";
			}
			if (grupo.getNivel() == 0) {
				values += ", 0 ";
			} else {
				values += ",'" + grupo.getNivel() + "'";
			}
			if (grupo.getClase() == null) {
				values += ", null ";
			} else {
				values += ",'" + grupo.getClase() + "'";
			}
			// al hacer consulta, poner variables de la BBDD
			String query = "insert into grupos (id,nivel,clase) values (" + values + ")";
			//System.out.println(query);
			s.executeUpdate(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			bd.desconectar();
		}
	}

	// eliminar grupo
	public static void deleteGrupo(Grupo grupo) {
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
			// hacer un delete
			s.executeUpdate("delete from grupos where id=" + grupo.getId());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}finally {
			bd.desconectar();
		}
	}

	// mostrar todos los grupos
	public static ArrayList<Grupo> findGrupos(String id, int nivel, String clase) {
		// crear el objeto de la BBDD
		BBDD bd = new BBDD();
		// conectar a la BBDD
		bd.conectar();
		// obtener la conexion
		Connection conexion = bd.getConnection();
		// Statement para crear la query
		Statement s;
		ArrayList<Grupo> resultado;
		try {
			s = conexion.createStatement();
			String filtro = "";
			if (id != null) {
				filtro += " id='" + id + "'";
			}
			if (nivel != 0) {
				if(!filtro.isEmpty()) {
					filtro+=" AND ";
				}
				filtro += " nivel=" + nivel;
			}
			if (clase != null) {
				if(!filtro.isEmpty()) {
					filtro+=" AND ";
				}
				filtro += " clase='" + clase + "'";
			}

			if (id != null || nivel != 0 || clase != null) {
				filtro = " where " + filtro;
			}
			// hacer un select
			// al hacer consulta, poner variables de la BBDD
			String query = "select * from grupos" + filtro;
			//System.out.println(query);
			ResultSet res = s.executeQuery(query);
			resultado = new ArrayList<Grupo>();
			while (res.next()) {
				String idRes = res.getString("id");
				int nivelRes = Integer.parseInt(res.getString("nivel"));
				String claseRes = res.getString("clase");
				Grupo grupoBD = new Grupo(idRes, nivelRes, claseRes);
				resultado.add(grupoBD);
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
