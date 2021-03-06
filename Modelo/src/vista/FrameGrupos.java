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

import entidades.Grupo;
import logica.GrupoLogica;

public class FrameGrupos extends JFrame{
		
	public FrameGrupos(){
		super("mostrar grupos");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor=getContentPane();
		
		contenedor.setLayout(new FlowLayout());
		
		String[] cabecera={"Nivel","Clase"};
		
		ArrayList<Grupo> arraylistGrupos=GrupoLogica.obtenerTodosGrupos();
		//generamos una matriz para meter los datos, con cinco columnas
		String[][] datos= new String[arraylistGrupos.size()][2];
		//recorremos el arraylist
		for(int i=0;i<arraylistGrupos.size();i++) {
			Grupo grupo=arraylistGrupos.get(i);
			//insertamos los datos en los campos
			datos[i][0]=String.valueOf(grupo.getNivel());
			datos[i][1]=grupo.getClase();
			if(grupo.getNivel()!=0) {
				datos[i][0]=String.valueOf(grupo.getNivel());
			}else {
				datos[i][0]="";
			}
			if(grupo.getClase()!=null) {
				datos[i][1]=grupo.getClase();
			}else {
				datos[i][1]="";
			}
		}
		DefaultTableModel mod=new DefaultTableModel(datos,cabecera);
		JTable tabla=new JTable(mod);
		JScrollPane scroll=new JScrollPane(tabla);
		scroll.setBounds(40,40,400,200);
		
		contenedor.add(scroll);
		
		//Crear boton de creacion de grupos
		JButton botonCrearGrupos=new JButton("Crear grupos");
		botonCrearGrupos.addActionListener(new EventoBotonCreacionGrupos(this));
		contenedor.add(botonCrearGrupos);
	}
	
	public class EventoBotonCreacionGrupos implements ActionListener {
		
		private JFrame frame;
		
		public EventoBotonCreacionGrupos(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Gracias por pulsar el boton de crear grupos");
			frame.dispose();
			JFrame FrameCrearGrupos=new FrameCrearGrupo();
			FrameCrearGrupos.setVisible(true);
		}

	}
	
}
