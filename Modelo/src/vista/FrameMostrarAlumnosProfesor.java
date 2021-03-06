package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Alumno;
import entidades.Grupo;
import entidades.Profesor;
import logica.AlumnoLogica;
import logica.ProfesorLogica;

public class FrameMostrarAlumnosProfesor extends JFrame {

	Profesor profesor;
	JComboBox gruposCombo;
	JTable tabla;
	public FrameMostrarAlumnosProfesor(Profesor profesor) {
		super("mostrar alumnos");
		this.profesor=profesor;
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		contenedor.setLayout(new FlowLayout());

		// crear panel con JPanel
		JPanel panelGrupos = new JPanel();
		// crear etiqueta con JLabel
		JLabel gruposLabel = new JLabel();
		gruposLabel.setText("Grupos");
		panelGrupos.add(gruposLabel);
		//obtener los grupos de un profesor y guardar en arraylist
		ArrayList<Grupo> grupos = ProfesorLogica.obtenerGruposProfesor(profesor.getDNI());
		gruposCombo = new JComboBox();
		for(int i = 0; i < grupos.size(); i++) {
			//a?adir grupos
			gruposCombo.addItem(grupos.get(i).getId());
		}
		//actualizar tabla
		gruposCombo.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	actualizarTabla();
		    }
		});
		panelGrupos.add(gruposCombo);
		contenedor.add(panelGrupos);

		
		tabla=new JTable();
		actualizarTabla();
		
		JScrollPane scroll=new JScrollPane(tabla);
		scroll.setBounds(40,40,400,200);
		
		contenedor.add(scroll);
	}
	
	private void actualizarTabla() {
		//Obtener los alumnos del primer grupo del select
		String grupoObtenido = (String) gruposCombo.getSelectedItem();
		String[] cabecera={"Nombre","Apellido1","Apellido2", "Calificar","Ver Notas", "id" };
		
		//crear matriz para meter los datos
		String[][] datos;
		//si se ha seleccionado un grupo, meter el grupo
		if(grupoObtenido==null) {
			datos= new String[0][5];
		}else {
			//obtener alumnos del grupo
			ArrayList<Alumno> arraylistAlumnos=AlumnoLogica.obtenerAlumnosGrupo(grupoObtenido);
			datos= new String[arraylistAlumnos.size()][6];
			//recorrer arraylist
			for(int i=0;i<arraylistAlumnos.size();i++) {
				Alumno alumno=arraylistAlumnos.get(i);
				//meter datos en la matriz
				if(alumno.getNombre()!=null) {
					datos[i][0]=alumno.getNombre();
				}else {
					datos[i][0]="";
				}
				if(alumno.getApellido1()!=null) {
					datos[i][1]=alumno.getApellido1();
				}else {
					datos[i][1]="";
				}
				if(alumno.getApellido2()!=null) {
					datos[i][2]=alumno.getApellido2();
				}else {
					datos[i][2]="";
				}
				datos[i][3]="calificar";
				datos[i][4]="ver";
				datos[i][5]= String.valueOf(alumno.getId());
			}
		}
		DefaultTableModel mod=new DefaultTableModel(datos,cabecera);
		tabla.setModel(mod);
		
		Action calificar = (Action) new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        //modelRow fila donde se ha clickado
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        //obtener el valor de la celda y de la columna
		        int idAlumno=Integer.valueOf((String)table.getValueAt(modelRow, 5));
		        JFrame fameCalificar = new FrameCalificar(idAlumno,profesor.getDNI(),gruposCombo.getSelectedItem().toString());
		        fameCalificar.setVisible(true);
		    }
		};
		Action verNotas = (Action) new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        //modelRow fila donde se ha clickado
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        //obtener el valor de la celda y de la columna
		        int idAlumno=Integer.valueOf((String)table.getValueAt(modelRow, 5));
		       //mostrar verNotas
		        JFrame frameMostrarNotas=new FrameMostrarNotas(idAlumno);
		        frameMostrarNotas.setVisible(true);
		    }
		}; 
		ButtonColumn buttonColumn = new ButtonColumn(tabla, calificar, 3);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		ButtonColumn buttonColumn2 = new ButtonColumn(tabla, verNotas, 4);
		buttonColumn2.setMnemonic(KeyEvent.VK_D);
		tabla.getColumn("id").setWidth(0);
		tabla.getColumn("id").setMinWidth(0);
		tabla.getColumn("id").setMaxWidth(0);
	}



}
