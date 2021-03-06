package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Nota;
import logica.NotaLogica;

public class FrameMostrarNotas extends JFrame {

	private int idAlumno;

	public FrameMostrarNotas(int idAlumno) {
		super("mostrar notas");
		this.idAlumno = idAlumno;
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		contenedor.setLayout(new FlowLayout());

		String[] cabecera = { "Asignatura", "Nota" };

		// llamamos a la funcion de la clase AlumnoLogica a obtener los alumnos, el cual
		// devuelve un arraylist
		ArrayList<Nota> arraylistNotas = NotaLogica.obtenerNotasAlumno(idAlumno);
		// generamos una matriz para meter los datos, con cinco columnas
		String[][] datos = new String[arraylistNotas.size()][2];
		// recorremos el arraylist
		for (int i = 0; i < arraylistNotas.size(); i++) {
			Nota nota = arraylistNotas.get(i);
			// insertamos los datos en los campos
			datos[i][0] = nota.getAsignatura();
			datos[i][1] = String.valueOf(nota.getNotaGlobal());
		}
		DefaultTableModel mod = new DefaultTableModel(datos, cabecera);
		JTable tabla = new JTable(mod);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(40, 40, 400, 200);

		contenedor.add(scroll);
	}

}
