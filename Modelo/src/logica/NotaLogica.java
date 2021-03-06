package logica;

import java.util.ArrayList;

import entidades.Alumno;
import entidades.Nota;
import modelo.AlumnoModelo;
import modelo.NotaModelo;

public class NotaLogica {

	//obtener todas las notas
	public static ArrayList<Nota> obtenerTodasNotas() {
		return NotaModelo.findNotas(0, null, 0);
	}

	//guardar notas
	public static boolean guardarNota(int idAlumno, String asignatura, float nota) {
		//Llamar a findNota para saber si tiene la nota insertada
		ArrayList<Nota> notas = NotaModelo.findNotas(idAlumno, asignatura, 0);
		// devolver ya sea nulo o no
		if (notas.size() == 0) {
			Alumno alumno=null;
			ArrayList<Alumno> alumnos=AlumnoModelo.findAlumno(idAlumno, null, null, null);
			if(alumnos.size()>0) {
				alumno=alumnos.get(0);
			}
			Nota notaGlobal=new Nota(alumno,asignatura,nota);
			NotaModelo.insertNota(notaGlobal);
			return true;
		} else {
			Alumno alumno=null;
			ArrayList<Alumno> alumnos=AlumnoModelo.findAlumno(idAlumno, null, null, null);
			if(alumnos.size()>0) {
				alumno=alumnos.get(0);
			}
			Nota notaGlobal=new Nota(alumno,asignatura,nota);
			NotaModelo.updateNota(notaGlobal);
			return true;
		}
	}

	public static ArrayList<Nota> obtenerNotasAlumno(int idAlumno) {
		return NotaModelo.findNotas(idAlumno, null, 0);
	}

}
