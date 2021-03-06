package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Alumno;
import logica.AlumnoLogica;

public class FrameAlumnos extends JFrame {

	public FrameAlumnos() {
		super("mostrar alumnos");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		contenedor.setLayout(new FlowLayout());

		String[] cabecera = { "Nombre", "Apellido1", "Apellido2", "Nivel", "Grupo" };

		// llamamos a la funcion de la clase AlumnoLogica a obtener los alumnos, el cual
		// devuelve un arraylist
		ArrayList<Alumno> arraylistAlumnos = AlumnoLogica.obtenerTodosAlumnos();
		// generamos una matriz para meter los datos, con cinco columnas
		String[][] datos = new String[arraylistAlumnos.size()][5];
		// recorremos el arraylist
		for (int i = 0; i < arraylistAlumnos.size(); i++) {
			Alumno alumno = arraylistAlumnos.get(i);
			// insertamos los datos en los campos
			if (alumno.getNombre() != null) {
				datos[i][0] = alumno.getNombre();
			} else {
				datos[i][0] = "";
			}
			if (alumno.getApellido1() != null) {
				datos[i][1] = alumno.getApellido1();
			} else {
				datos[i][1] = "";
			}
			if (alumno.getApellido2() != null) {
				datos[i][2] = alumno.getApellido2();
			} else {
				datos[i][2] = "";
			}
			if (alumno.getGrupo() != null) {
				datos[i][3] = String.valueOf(alumno.getGrupo().getNivel());
			} else {
				datos[i][3] = "";
			}
			if (alumno.getGrupo() != null) {
				datos[i][4] = String.valueOf(alumno.getGrupo().getClase());
			} else {
				datos[i][4] = "";
			}
		}
		DefaultTableModel mod = new DefaultTableModel(datos, cabecera);
		JTable tabla = new JTable(mod);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(40, 40, 400, 200);

		contenedor.add(scroll);

		// Crear boton de creacion de alumnos
		JButton botonCrearAlumnos = new JButton("Crear alumno");
		botonCrearAlumnos.addActionListener(new EventoBotonCreacionAlumnos(this));
		contenedor.add(botonCrearAlumnos);
	}

	public class EventoBotonCreacionAlumnos implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonCreacionAlumnos(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			JFrame FrameCrearAlumnos=new FrameCrearAlumno();
			FrameCrearAlumnos.setVisible(true);
		}

	}

}
