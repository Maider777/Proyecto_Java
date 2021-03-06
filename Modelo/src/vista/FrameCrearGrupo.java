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
import javax.swing.JTextField;

import logica.GrupoLogica;

public class FrameCrearGrupo extends JFrame {
	
	JTextField textoNivel;
	JTextField textoClase;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame framePrincipal=new FrameCrearGrupo();
		framePrincipal.setVisible(true);
	}

	public FrameCrearGrupo() {
		super("Crear grupo");
		this.setSize(350, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		//GridLayout para poner por filas y columnas
		//como parametro, se le pasan primero las filas y luego las columnas
		GridLayout glt=new GridLayout(3,1);
		contenedor.setLayout(glt);
        
		JPanel panelNivel=new JPanel();
        JLabel nivel=new JLabel();
        nivel.setText("Nivel*");
        panelNivel.add(nivel);		
        textoNivel=new JTextField(20);
        panelNivel.add(textoNivel);
        contenedor.add(panelNivel);
        
        JPanel panelClase=new JPanel();
        JLabel clase=new JLabel();
        clase.setText("Clase*");
        panelClase.add(clase);		
        textoClase=new JTextField(20);
        panelClase.add(textoClase);
        contenedor.add(panelClase);
        
        // Crear boton de crear grupo
        JPanel panelBoton=new JPanel();
	    JButton botonCrearGrupo = new JButton("Crear grupo");
	    botonCrearGrupo.addActionListener(new EventoBotonCrearGrupo(this));
	    panelBoton.add(botonCrearGrupo);
	    contenedor.add(botonCrearGrupo);
	    
	}
	
	public class EventoBotonCrearGrupo implements ActionListener {
		
		private JFrame frame;
		
		public EventoBotonCrearGrupo(JFrame frame) {
			this.frame=frame;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			//el nivel que hayan escrito algo y que sea un numero
			boolean esNivelValido=validarNivel(textoNivel.getText());
			if(!esNivelValido) {
				JOptionPane.showMessageDialog(null, "Error, el nivel es incorrecto");
			}else {
				boolean esGrupoValido=validarGrupo(textoClase.getText());
				if(!esGrupoValido) {
					JOptionPane.showMessageDialog(null, "Error, el grupo no es valido");
				}else {
					GrupoLogica.insertarGrupo(Integer.valueOf(textoNivel.getText())+textoClase.getText(),Integer.valueOf(textoNivel.getText()), textoClase.getText());
					frame.dispose();
					JFrame frameGrupos=new FrameGrupos();
					frameGrupos.setVisible(true);
				}
			}
		}
		
		private boolean validarNivel(String nivel) {
			boolean esNivelValido=nivel.matches("^[1-6]$");
			if (esNivelValido) {
				return true;
			} else {
				return false;
			}
		}
		
		private boolean validarGrupo(String grupo) {
			if (grupo.length()>0) {
				return true;
			} else {
				return false;
			}
		}

	}
}
