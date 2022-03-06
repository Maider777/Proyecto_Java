package entidades;

public class Grupo {

	// definir caracteristicas, atributos
	private String id;
	private int nivel;
	private String clase;

	// construir el elemento, pasando por parametro los valores
	public Grupo(String id, int nivel, String clase) {
		this.id = id;
		this.nivel = nivel;
		this.clase = clase;
	}

	// crear metodos getter y setter
	// los getter son para, desde otras clases, poder coger su informacion
	public String getId() {
		return id;
	}

	public int getNivel() {
		return nivel;
	}

	public String getClase() {
		return clase;
	}

	// los setter, son lo que se utilizan para poder modificar esos datos
	public void setId(String id) {
		this.id = id;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	// al final, crear el metodo toString, para poder imprimir la informacion
	public String toString() {
		return "Grupo [id=" + id + ", nivel=" + nivel + ", clase=" + clase + "]";
	}
}
