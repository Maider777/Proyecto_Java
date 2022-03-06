package logica;

import java.sql.Date;
import java.util.ArrayList;

import entidades.Alumno;
import entidades.Grupo;
import modelo.AlumnoModelo;

public class AlumnoLogica {
	
	//buscar alumnos
	public static ArrayList<Alumno> obtenerAlumnosGrupo ( String idGrupo){
		ArrayList<Alumno>resultado=AlumnoModelo.findAlumnosGrupo(idGrupo);
		return resultado;
	}
	public static ArrayList<Alumno> obtenerTodosAlumnos(){
		ArrayList<Alumno>resultado=AlumnoModelo.findAlumno(0, null, null, null);
		return resultado;
	}
	
	//insertar alumno, insertAlumno
	public static boolean guardarAlumno(String nombre, String apellido1, String apellido2, String dni, String direccion,
			Date fechaDeNacimiento, String nombreDeTutor1, String nombreDeTutor2, String numeroDeTelefonoTutor1,
			String numeroDeTelefonoTutor2, String idGrupo) {
		//guardar un grupo por id, ya que devulve un solo grupo
		Grupo grupo=GrupoLogica.obtenerGrupoPorId(idGrupo);
		//crear el alumno
		Alumno alumno=new Alumno(0, nombre, apellido1, apellido2, dni, direccion,
				fechaDeNacimiento, nombreDeTutor1, nombreDeTutor2, numeroDeTelefonoTutor1,
				numeroDeTelefonoTutor2, grupo);
		ArrayList<Alumno> arrayAlumnos=AlumnoModelo.findAlumno(0, nombre, apellido1, apellido2);
		if(arrayAlumnos.size()==0) {
			//insertar alumno
			AlumnoModelo.insertAlumno(alumno);
			return true;
		}else {
			return false;
		}
	}
	
	//borrar alumno, deleteAlumno
	public static void eliminarAlumno(int id) {
		return;
	}
}
