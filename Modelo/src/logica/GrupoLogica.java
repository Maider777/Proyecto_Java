package logica;

import java.util.ArrayList;

import entidades.Grupo;
import modelo.GrupoModelo;

public class GrupoLogica {

	// encontrar grupo
	public static ArrayList<Grupo> obtenerTodosGrupos() {
		return GrupoModelo.findGrupos(null, 0, null);
	}

	public static Grupo obtenerGrupoPorId(String idGrupo) {
		// guardar array de grupos
		ArrayList<Grupo> grupos = GrupoModelo.findGrupos(idGrupo, 0, null);
		// devolver ya sea nulo o no
		if (grupos == null || grupos.size() == 0) {
			return null;
		} else {
			return grupos.get(0);
		}
	}

	// insertar grupo
	public static void insertarGrupo(String idGrupo, int nivel, String clase) {
		//crear id del grupo
		idGrupo=nivel+clase;
		// crear el grupo
		Grupo grupo = new Grupo(idGrupo, nivel, clase);
		// insertar grupo
		GrupoModelo.insertGrupo(grupo);
		return;
	}

	// eliminar grupo
	public static void eliminarGrupo(int id) {
		return;
	}

}
