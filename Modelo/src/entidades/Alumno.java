package entidades;

import java.sql.Date;

public class Alumno {

	// definir caracteristicas, atributos
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String direccion;
	private Date fechaDeNacimiento;
	private String nombreDeTutor1;
	private String nombreDeTutor2;
	private String numeroDeTelefonoTutor1;
	private String numeroDeTelefonoTutor2;
	private Grupo grupo;

	// construir el elemento, pasando por parametro los valores
	public Alumno(int id, String nombre, String apellido1, String apellido2, String dni, String direccion,
			Date fechaDeNacimiento, String nombreDeTutor1, String nombreDeTutor2, String numeroDeTelefonoTutor1,
			String numeroDeTelefonoTutor2, Grupo grupo) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.dni = dni;
		this.direccion = direccion;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.nombreDeTutor1 = nombreDeTutor1;
		this.nombreDeTutor2 = nombreDeTutor2;
		this.numeroDeTelefonoTutor1 = numeroDeTelefonoTutor1;
		this.numeroDeTelefonoTutor2 = numeroDeTelefonoTutor2;
		this.grupo = grupo;
	}

	// crear metodos getter y setter
	// los getter son para, desde otras clases, poder coger su informacion
	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getDni() {
		return dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public String getNombreDeTutor1() {
		return nombreDeTutor1;
	}

	public String getNombreDeTutor2() {
		return nombreDeTutor2;
	}

	public String getNumeroDeTelefonoTutor1() {
		return numeroDeTelefonoTutor1;
	}

	public String getNumeroDeTelefonoTutor2() {
		return numeroDeTelefonoTutor2;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	// los setter, son lo que se utilizan para poder modificar esos datos
	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public void setNombreDeTutor1(String nombreDeTutor1) {
		this.nombreDeTutor1 = nombreDeTutor1;
	}

	public void setNombreDeTutor2(String nombreDeTutor2) {
		this.nombreDeTutor2 = nombreDeTutor2;
	}

	public void setNumeroDeTelefonoTutor1(String numeroDeTelefonoTutor1) {
		this.numeroDeTelefonoTutor1 = numeroDeTelefonoTutor1;
	}

	public void setNumeroDeTelefonoTutor2(String numeroDeTelefonoTutor2) {
		this.numeroDeTelefonoTutor2 = numeroDeTelefonoTutor2;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	// al final, crear el metodo toString, para poder imprimir la informacion
	public String toString() {
		return "Alumno [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", dni=" + dni + ", direccion=" + direccion + ", fechaDeNacimiento=" + fechaDeNacimiento
				+ ", nombreDeTutor1=" + nombreDeTutor1 + ", nombreDeTutor2=" + nombreDeTutor2
				+ ", numeroDeTelefonoTutor1=" + numeroDeTelefonoTutor1 + ", numeroDeTelefonoTutor2="
				+ numeroDeTelefonoTutor2 + ", idGrupo=" + grupo + "]";
	}
}
