package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameDireccion extends JFrame{
	
	public FrameDireccion(){
		super("Direccion");
		setSize(250,250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor=getContentPane();
		
		contenedor.setLayout(new FlowLayout());
		
		//Alumnos
		JButton botonAlumnos=new JButton("Alumnos");
		botonAlumnos.addActionListener(new EventoBotonAlumnos());
		contenedor.add(botonAlumnos);
		//Grupos
		JButton botonGrupos=new JButton("Grupos");
		botonGrupos.addActionListener(new EventoBotonGrupos());
		contenedor.add(botonGrupos);
		//Profesores
		JButton botonProfesores=new JButton("Profesores");
		botonProfesores.addActionListener(new EventoBotonProfesores());
		contenedor.add(botonProfesores);
	}
	
	public class EventoBotonAlumnos implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Gracias por pulsar el boton Alumnos");
			JFrame FrameAlumnos=new FrameAlumnos();
			FrameAlumnos.setVisible(true);
		}

	}
	
	public class EventoBotonGrupos implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Gracias por pulsar el boton Grupos");
			JFrame FrameGrupos=new FrameGrupos();
			FrameGrupos.setVisible(true);
		}

	}

	public class EventoBotonProfesores implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Gracias por pulsar el boton de Profesores");
			JFrame FrameProfesores=new FrameProfesores();
			FrameProfesores.setVisible(true);
		}

	}

}