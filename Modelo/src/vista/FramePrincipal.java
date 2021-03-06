package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FramePrincipal extends JFrame {
	
	// crear botones de direccion y profesor
	public FramePrincipal() {
		super("Login");
		setSize(250, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container contenedor = getContentPane();

		contenedor.setLayout(new FlowLayout());

		// Direccion
		JButton botonDireccion = new JButton("Entrar como direccion");
		botonDireccion.addActionListener(new EventoBotonDireccion(this));
		contenedor.add(botonDireccion);

		// Profesor
		JButton botonProfesor = new JButton("Entrar como profesor");
		botonProfesor.addActionListener(new EventoBotonProfesor(this));
		contenedor.add(botonProfesor);
	}

	public class EventoBotonDireccion implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonDireccion(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			// pedir contraseņa para logearse, y cuando sea correcta entonces entrar
			JFrame FrameLoginDireccion = new FrameLoginDireccion();
			FrameLoginDireccion.setVisible(true);
		}

	}
	
	public class EventoBotonProfesor implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonProfesor(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			//profesor
			JFrame FrameLoginProfesor = new FrameLoginProfesor();
			FrameLoginProfesor.setVisible(true);
		}

	}
}
