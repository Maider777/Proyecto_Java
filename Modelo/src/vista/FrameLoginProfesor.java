package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entidades.Profesor;
import logica.ProfesorLogica;

public class FrameLoginProfesor extends JFrame {

	JTextField textoUsuario;
	JPasswordField textoContrasena;

	public FrameLoginProfesor() {
		super("Login Profesor");
		this.setSize(800, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		GridLayout glt = new GridLayout(3, 1);
		contenedor.setLayout(glt);

		// crear panel con JPanel
		JPanel panelUsuario = new JPanel();
		// crear etiqueta con JLabel
		JLabel usuario = new JLabel();
		usuario.setText("Email*");
		panelUsuario.add(usuario);
		// crear area de texto con JTextField
		textoUsuario = new JTextField(15);
		panelUsuario.add(textoUsuario);
		contenedor.add(panelUsuario);

		// crear panel con JPanel
		JPanel panelContrasena = new JPanel();
		// crear etiqueta con JLabel
		JLabel contrasena = new JLabel();
		contrasena.setText("Contrasena*");
		panelContrasena.add(contrasena);
		// crear area de texto con JTextField
		textoContrasena = new JPasswordField(15);
		panelContrasena.add(textoContrasena);
		contenedor.add(panelContrasena);

		// crear boton de login
		JButton botonLogin = new JButton("Acceder");
		botonLogin.addActionListener(new EventoBotonLogin(this));
		contenedor.add(botonLogin);
	}

	public class EventoBotonLogin implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonLogin(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			//guardar booleano del usuario que se ha logeado
			boolean loginCorrecto = ProfesorLogica.loginProfesor(textoUsuario.getText(), textoContrasena.getText());
			//si el login es correcto
			if(loginCorrecto) {
				//pasar por parametro el login correcto para que se muestre informacion de este
				Profesor profesor = ProfesorLogica.buscarProfesor(textoUsuario.getText());
				//cerrar este frame
				frame.dispose();
				//crear jframe
				JFrame FrameMostrarAlumnos = new FrameMostrarAlumnosProfesor(profesor);
				FrameMostrarAlumnos.setVisible(true);
			}else {
				//si no es correcto
				JOptionPane.showMessageDialog(null, "Error, el usuario o contrase?a no son correctos");
			}
		}
	}

}
