package entidades;

public class GrupoAsignatura {

	private Grupo grupo;
	private Profesor profesor;
	private String asignatura;
	
	public GrupoAsignatura(Grupo grupo, Profesor profesor, String asignatura) {
		super();
		this.grupo = grupo;
		this.profesor = profesor;
		this.asignatura = asignatura;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public String getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}

	public String toString() {
		return "GrupoAsignatura [grupo=" + grupo + ", profesor=" + profesor + ", asignatura=" + asignatura + "]";
	}
	
}
