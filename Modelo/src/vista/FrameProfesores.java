package vista;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import entidades.Profesor;
import logica.ProfesorLogica;


public class FrameProfesores extends JFrame{
	
	JButton botonVerGrupo = new JButton();
	
	public FrameProfesores(){
		super("mostrar profesores");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor=getContentPane();
		
		contenedor.setLayout(new FlowLayout());
		
		String[] cabecera={"Nombre","Apellido1","Apellido2","DNI","Grupo y Asignatura"};
	
		ArrayList<Profesor> arraylistProfesores=ProfesorLogica.buscarTodosProfesores();
		//generamos una matriz para meter los datos, con cinco columnas
		String[][] datos= new String[arraylistProfesores.size()][5];
		//recorremos el arraylist
		for(int i=0;i<arraylistProfesores.size();i++) {
			Profesor profesor=arraylistProfesores.get(i);
			//insertamos los datos en los campos
			if(profesor.getNombre()!=null) {
				datos[i][0]=profesor.getNombre();
			}else {
				datos[i][0]="";
			}
			if(profesor.getApellido1()!=null) {
				datos[i][1]=profesor.getApellido1();
			}else {
				datos[i][1]="";
			}
			if(profesor.getApellido2()!=null) {
				datos[i][2]=profesor.getApellido2();
			}else {
				datos[i][2]="";
			}
			if(profesor.getDNI()!=null) {
				datos[i][3]=profesor.getDNI();
			}else {
				datos[i][3]="";
			}
			datos[i][4]="ver";
		}
		DefaultTableModel mod=new DefaultTableModel(datos,cabecera);
		JTable tabla=new JTable(mod);
		tabla.setModel(mod);
		Action delete = (Action) new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        //modelRow fila donde se ha clickado
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        //obtener el valor de la celda y de la columna
		        String idProfesor=(String) table.getValueAt(modelRow, 3);
		        JFrame FrameMostrarGrupoAsignatura = new FrameGrupoAsignatura(idProfesor);
				FrameMostrarGrupoAsignatura.setVisible(true);
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(tabla, delete, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		JScrollPane scroll=new JScrollPane(tabla);
		scroll.setBounds(40,40,400,200);
		
		contenedor.add(scroll);
		
		// Crear boton de creacion de profesores
		JButton botonCrearProfesores = new JButton("Crear profesor");
		botonCrearProfesores.addActionListener(new EventoBotonCreacionProfesores(this));
		contenedor.add(botonCrearProfesores);
	}
	
	public class EventoBotonCreacionProfesores implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonCreacionProfesores(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			JFrame FrameCrearProfesores=new FrameCrearProfesor();
			FrameCrearProfesores.setVisible(true);
		}

	}
	
	class ButtonRenderer extends JButton implements TableCellRenderer {
		public ButtonRenderer() {
			setOpaque(true);
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText((value == null) ? "Asignar" : value.toString());
			return this;
		}
	}

	class ButtonEditor extends DefaultCellEditor {
		private String label;

		public ButtonEditor(JCheckBox checkBox) {
			super(checkBox);
		}

		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			label = (value == null) ? "Asignar" : value.toString();
			botonVerGrupo.setText(label);
			return botonVerGrupo;
		}

		public Object getCellEditorValue() {
			return new String(label);
		}
	}
	
}
