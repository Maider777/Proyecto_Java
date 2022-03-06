package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class EventoBotonDireccion implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		//pedir contraseña para logearse, y cuando sea correcta entonces entrar
		JFrame FrameLoginDireccion=new FrameDireccion();
		FrameLoginDireccion.setVisible(true);
	}

}
