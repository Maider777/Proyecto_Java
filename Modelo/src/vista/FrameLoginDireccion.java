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

public class FrameLoginDireccion extends JFrame {

	JTextField textoUsuario;
	JPasswordField textoContrasena;

	public FrameLoginDireccion() {
		super("Login direccion");
		this.setSize(800, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		GridLayout glt = new GridLayout(3, 1);
		contenedor.setLayout(glt);

		// crear panel con JPanel
		JPanel panelUsuario = new JPanel();
		// crear etiqueta con JLabel
		JLabel usuario = new JLabel();
		usuario.setText("Usuario*");
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
		
		//crear boton de login
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
			//coger texto de la contraseña
			String contrasenaLogin="direccion";
			if(textoContrasena.getText().equals(contrasenaLogin)) {
				frame.dispose();
				JFrame FrameDireccion=new FrameDireccion();
				FrameDireccion.setVisible(true);
			}else{
				JOptionPane.showMessageDialog(null, "Error, la contraseña no es correcta");
			};
		}

	}

}
