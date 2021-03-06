package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.GrupoAsignatura;
import logica.GrupoAsignaturaLogica;
import logica.NotaLogica;

public class FrameCalificar extends JFrame {

	private int idAlumno;
	private JComboBox asignaturas;
	private JTextField textoNota;

	public FrameCalificar(int idAlumno, String idProfesor, String idGrupo) {
		super("A?adir calificaci?n");
		this.idAlumno = idAlumno;
		this.setSize(250, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		// GridLayout para poner por filas y columnas
		// como parametro, se le pasan primero las filas y luego las columnas
		GridLayout glt = new GridLayout(3, 1);
		contenedor.setLayout(glt);

		JPanel panelAsignaturas = new JPanel();
		// crear etiqueta con JLabel
		JLabel asignatura = new JLabel();
		asignatura.setText("Asignatura");
		panelAsignaturas.add(asignatura);
		ArrayList<GrupoAsignatura> arrayAsignaturas=GrupoAsignaturaLogica.obtenerGrupoAsignaturaProfesor(idProfesor, idGrupo);
		
		asignaturas = new JComboBox();
		for(int i=0;i<arrayAsignaturas.size();i++) {
			asignaturas.addItem(arrayAsignaturas.get(i).getAsignatura());
		}
		panelAsignaturas.add(asignaturas);
		contenedor.add(panelAsignaturas);

		//contenedor.add(alumnos);
		//contenedor.add(asignaturas);

		// crear panel con JPanel
		JPanel panelNota = new JPanel();
		// crear etiqueta con JLabel
		JLabel nota = new JLabel();
		nota.setText("Nota ");
		panelNota.add(nota);
		// crear area de texto con JTextField
		textoNota = new JTextField(15);
		panelNota.add(textoNota);
		contenedor.add(panelNota);

		// Crear boton de a?adir calificacion
		JPanel panelBoton = new JPanel();
		JButton botonMatricularAlumno = new JButton("A?adir calificaci?n");
		botonMatricularAlumno.addActionListener(new EventoA?adirCalificacion(this));
		panelBoton.add(botonMatricularAlumno);
		contenedor.add(botonMatricularAlumno);

	}

	public class EventoA?adirCalificacion implements ActionListener {
		
		private JFrame frame;
		
		public EventoA?adirCalificacion(JFrame frame) {
			this.frame=frame;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			String asignatura=(String) asignaturas.getSelectedItem();
			float nota=Float.parseFloat(textoNota.getText());
			//si la nota y la asignatura estan bien, entonces guardar nota
			boolean esNotaValida=validarNota(textoNota.getText());
			if(esNotaValida) {
				NotaLogica.guardarNota(idAlumno, asignatura, nota);
				frame.dispose();
			}
		}

		private boolean validarNota(String nota) {
			try {
				float notaInt=Float.parseFloat(nota);
				return true;
			}catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error, la nota no es valida");
				return false;
			}
		}
	
	}
}
