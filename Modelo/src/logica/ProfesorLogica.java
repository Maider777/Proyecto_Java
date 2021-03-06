package logica;

import java.sql.Date;
import java.util.ArrayList;

import entidades.Grupo;
import entidades.Profesor;
import modelo.ProfesorModelo;

public class ProfesorLogica {

	// buscar profesor
	public static boolean loginProfesor(String usuario, String contrasena) {
		ArrayList<Profesor> profesores =ProfesorModelo.findProfesores(contrasena, null, null, null, usuario);
		if(profesores.size()!=0) {
			//si hay profesores
			return true;
		}else {
			//sino, el login esta mal
			return false;
		}
	}
	public static ArrayList<Profesor> buscarTodosProfesores() {
		return ProfesorModelo.findProfesores(null, null, null, null,null);
	}
	
	public static Profesor buscarProfesor(String email) {
		ArrayList<Profesor> profesores =  ProfesorModelo.findProfesores(null, null, null, null,email);
		if(profesores.size()!=0) {
			return profesores.get(0);
		}else {
			return null;
		}
	}
	public static ArrayList<Grupo> obtenerGruposProfesor(String idProfesor){
		ArrayList<Grupo> grupos=ProfesorModelo.findGruposProfesor(idProfesor);
		return grupos;
	}
	// insertar profesor
	public static boolean guardarProfesor(String dni, String nombre, String apellido1, String apellido2, Date fechaDeNacimiento,
			String numeroDeTelefono, String email, String direccion) {
		// crear el profesor
		Profesor profesor = new Profesor(dni, nombre, apellido1, apellido2, fechaDeNacimiento, numeroDeTelefono, email, direccion);
		ArrayList<Profesor> profesores=ProfesorModelo.findProfesores(dni, null, null, null,null);
		if(profesores.size()==0) {
			//no puede haber dos profesores con mismo email
			ArrayList<Profesor> profesores2=ProfesorModelo.findProfesores(null, null, null, null,email);
			if(profesores2.size()==0) {
				// insertar profesor
				ProfesorModelo.insertProfesor(profesor);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}

	// eliminar profesor
	public static void eliminarProfesor(String dni) {
		return;
	}

}
