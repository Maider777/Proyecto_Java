package logica;

import java.util.ArrayList;

import entidades.Grupo;
import entidades.GrupoAsignatura;
import entidades.Profesor;
import modelo.GrupoAsignaturaModelo;

public class GrupoAsignaturaLogica {

	public static ArrayList<GrupoAsignatura> obtenerGrupoAsignaturaProfesor(String idProfesor) {
		return GrupoAsignaturaModelo.findGrupoAsignatura(null, idProfesor, null);
	}
	
	public static ArrayList<GrupoAsignatura> obtenerGrupoAsignaturaProfesor(String idProfesor, String idGrupo) {
		return GrupoAsignaturaModelo.findGrupoAsignatura(idGrupo, idProfesor,null);
	}

	public static boolean guardarGrupoAsignaturaProfesor(String idProfesor, String grupo, String asignatura) {
		GrupoAsignatura grupoAsignatura=new GrupoAsignatura(new Grupo(grupo,0,null),new Profesor(idProfesor,null,null,null,null,null,null,null),asignatura);
		ArrayList<GrupoAsignatura> arrayGrupoAsignatura=GrupoAsignaturaModelo.findGrupoAsignatura(grupo, null, asignatura);
		if(arrayGrupoAsignatura.size()==0) {
			GrupoAsignaturaModelo.insertGrupoAsignatura(grupoAsignatura);
			return true;
		}else {
			return false;
		}
	}
}
