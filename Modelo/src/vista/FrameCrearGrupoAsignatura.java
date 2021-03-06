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

import entidades.Grupo;
import logica.GrupoAsignaturaLogica;
import logica.GrupoLogica;

public class FrameCrearGrupoAsignatura extends JFrame {
	
	JComboBox gruposCombo;
	String idProfesor;
	JComboBox asignaturas;

	public FrameCrearGrupoAsignatura(String idProfesor) {
		super("Crear grupo y asignatura");
		this.idProfesor=idProfesor;
		this.setSize(350, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		//GridLayout para poner por filas y columnas
		//como parametro, se le pasan primero las filas y luego las columnas
		GridLayout glt=new GridLayout(3,1);
		contenedor.setLayout(glt);
        
		JPanel panelGrupo = new JPanel();
		// crear etiqueta con JLabel
		JLabel grupoLabel = new JLabel();
		grupoLabel.setText("Grupo");
		panelGrupo.add(grupoLabel);
		gruposCombo = new JComboBox();
		//coger grupos de la BBDD
		ArrayList<Grupo> arrayGrupos = GrupoLogica.obtenerTodosGrupos();
		for (int i = 0; i < arrayGrupos.size(); i++) {
			// obtener los atributos
			Grupo grupo = arrayGrupos.get(i);
			gruposCombo.addItem(grupo.getId());
		}
		panelGrupo.add(gruposCombo);
		contenedor.add(panelGrupo);
        
		JPanel panelAsignatura = new JPanel();
		// crear etiqueta con JLabel
		JLabel asignatura = new JLabel();
		asignatura.setText("Asignatura");
		panelAsignatura.add(asignatura);
		asignaturas = new JComboBox();
		asignaturas.addItem("Lengua Castellana");
		asignaturas.addItem("Historia");
		asignaturas.addItem("Matem?ticas");
		asignaturas.addItem("F?sica y Qu?mica");
		asignaturas.addItem("Euskera");
		asignaturas.addItem("Filosof?a");
		asignaturas.addItem("Ingles");
		asignaturas.addItem("Biologia");
		asignaturas.addItem("Dibujo T?cnico");
		asignaturas.addItem("Educaci?n F?sica");
		asignaturas.addItem("Religi?n");
		panelAsignatura.add(asignaturas);
		contenedor.add(panelAsignatura);
        
		// Crear boton de crear grupo y asignatura
        JPanel panelBoton=new JPanel();
	    JButton botonCrearGrupoAsignatura = new JButton("Asignar grupo y asignatura");
	    //pasar el jframe como this
	    botonCrearGrupoAsignatura.addActionListener(new EventoBotonCrearGrupoAsignatura(this));
	    panelBoton.add(botonCrearGrupoAsignatura);
	    contenedor.add(botonCrearGrupoAsignatura);
	}
	
	public class EventoBotonCrearGrupoAsignatura implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonCrearGrupoAsignatura(JFrame frame) {
			this.frame=frame;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if(gruposCombo.getSelectedItem()!=null) {
				boolean esGrupoGuardado=GrupoAsignaturaLogica.guardarGrupoAsignaturaProfesor(idProfesor,gruposCombo.getSelectedItem().toString(),asignaturas.getSelectedItem().toString());
			if(esGrupoGuardado) {
				//cerrar el frame
				frame.dispose();
				JFrame FrameMostrarGrupoAsignatura=new FrameGrupoAsignatura(idProfesor);
				FrameMostrarGrupoAsignatura.setVisible(true);
			}else {
				//mostrar un jpanel diciendo que ya existe un grupo para ese profesor
				JOptionPane.showMessageDialog(null, "Error, Grupo y asignatura ya asignados");
			}
			}else {
				JOptionPane.showMessageDialog(null, "Por favor inserte un grupo");
			}
			
		}
		
	}
}
