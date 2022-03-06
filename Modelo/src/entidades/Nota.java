package entidades;

public class Nota {

	// definir caracteristicas, atributos
	private Alumno alumno;
	private String asignatura;
	private float notaGlobal;
	
	
	public Nota(Alumno alumno, String asignatura, float notaGlobal) {
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.notaGlobal = notaGlobal;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public float getNotaGlobal() {
		return notaGlobal;
	}
	public void setNotaGlobal(float notaGlobal) {
		this.notaGlobal = notaGlobal;
	}
	
	public String toString() {
		return "Nota [alumno=" + alumno + ", asignatura=" + asignatura + ", notaGlobal=" + notaGlobal + "]";
	}

}
