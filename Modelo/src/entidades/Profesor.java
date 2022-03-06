package entidades;

import java.sql.Date;

public class Profesor {

	// definir caracteristicas, atributos
	private String dni;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaDeNacimiento;
	private String numeroDeTelefono;
	private String email;
	private String direccion;

	// construir el elemento, pasando por parametro los valores
	public Profesor(String dni, String nombre, String apellido1, String apellido2, Date fechaDeNacimiento,
			String numeroDeTelefono, String email, String direccion) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.numeroDeTelefono = numeroDeTelefono;
		this.email = email;
		this.direccion = direccion;
	}

	public Profesor(String dni) {
		this.dni = dni;
	}

	// crear metodos getter y setter
	// los getter son para, desde otras clases, poder coger su informacion
	public String getDNI() {
		return dni;
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

	public Date getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public String getNumeroDeTelefono() {
		return numeroDeTelefono;
	}

	public String getEmail() {
		return email;
	}

	public String getDireccion() {
		return direccion;
	}

	// los setter, son lo que se utilizan para poder modificar esos datos
	public void setDNI(String dni) {
		this.dni = dni;
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

	public void setFechaDeNacimiento(Date fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public void setNumeroDeTelefono(String numeroDeTelefono) {
		this.numeroDeTelefono = numeroDeTelefono;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	// al final, crear el metodo toString, para poder imprimir la informacion
	public String toString() {
		return "Profesor [dni=" + dni + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", direccion=" + direccion + ", fechaDeNacimiento=" + fechaDeNacimiento + ", numeroDeTelefono="
				+ numeroDeTelefono + ", email=" + email + ", Direccion=" + direccion + "]";
	}
}
