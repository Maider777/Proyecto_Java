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

import entidades.GrupoAsignatura;
import logica.GrupoAsignaturaLogica;

public class FrameGrupoAsignatura extends JFrame {

	String idProfesor;
	
	public FrameGrupoAsignatura(String idProfesor) {

		super("mostrar grupos y asignaturas profesor");
		this.idProfesor=idProfesor;
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		contenedor.setLayout(new FlowLayout());

		String[] cabecera = { "Nivel", "Clase", "Asignatura" };

		ArrayList<GrupoAsignatura> arraylistGruposAsignaturas = GrupoAsignaturaLogica
				.obtenerGrupoAsignaturaProfesor(idProfesor);
		// generamos una matriz para meter los datos, con cinco columnas
		String[][] datos = new String[arraylistGruposAsignaturas.size()][3];
		// recorremos el arraylist
		for (int i = 0; i < arraylistGruposAsignaturas.size(); i++) {
			GrupoAsignatura grupoAsignatura = arraylistGruposAsignaturas.get(i);
			// insertamos los datos en los campos
			if (grupoAsignatura.getGrupo() != null) {
				datos[i][0] = String.valueOf(grupoAsignatura.getGrupo().getNivel());
			} else {
				datos[i][0] = "";
			}
			if (grupoAsignatura.getGrupo() != null) {
				datos[i][1] = String.valueOf(grupoAsignatura.getGrupo().getClase());
			} else {
				datos[i][1] = "";
			}
			if (grupoAsignatura.getAsignatura() != null) {
				datos[i][2] = grupoAsignatura.getAsignatura();
			} else {
				datos[i][2] = "";
			}
		}
		DefaultTableModel mod = new DefaultTableModel(datos, cabecera);
		JTable tabla = new JTable(mod);
		tabla.setModel(mod);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setBounds(40, 40, 400, 200);

		contenedor.add(scroll);

		// Crear boton de creacion de profesores
		JButton botonAsignarGrupoAsignatura = new JButton("Asignar grupo y asignatura");
		botonAsignarGrupoAsignatura.addActionListener(new EventoBotonAsignarGrupoAsignatura(this));
		contenedor.add(botonAsignarGrupoAsignatura);
	}

	public class EventoBotonAsignarGrupoAsignatura implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonAsignarGrupoAsignatura(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			JFrame frameCrearGrupoAsignatura=new FrameCrearGrupoAsignatura(idProfesor);
			frameCrearGrupoAsignatura.setVisible(true);
		}

	}

}
