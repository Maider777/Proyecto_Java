package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entidades.Grupo;
import logica.AlumnoLogica;
import logica.GrupoLogica;

public class FrameCrearAlumno extends JFrame {

	JTextField textoNombre;
	JTextField textoNombreTutor1;
	JTextField textoApellido1;
	JTextField textoNombreTutor2;
	JTextField textoApellido2;
	JTextField textoTelefono1;
	JTextField textoDni;
	JTextField textoTelefono2;
	JTextField textoFechaDeNacimiento;
	JTextField textoDireccion;
	JComboBox comboGrupo;

	public static void main(String[] args) {
		JFrame framePrincipal = new FrameCrearAlumno();
		framePrincipal.setVisible(true);
	}

	public FrameCrearAlumno() {
		super("Matricular alumno");
		this.setSize(800, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		// GridLayout para poner por filas y columnas
		// como parametro, se le pasan primero las filas y luego las columnas
		GridLayout glt = new GridLayout(6, 2);
		contenedor.setLayout(glt);

		// crear panel con JPanel
		JPanel panelNombre = new JPanel();
		// crear etiqueta con JLabel
		JLabel nombre = new JLabel();
		nombre.setText("Nombre*");
		panelNombre.add(nombre);
		// crear area de texto con JTextField
		textoNombre = new JTextField(15);
		panelNombre.add(textoNombre);
		contenedor.add(panelNombre);

		JPanel paneltextoNombreTutor1 = new JPanel();
		JLabel nombreTutor1 = new JLabel();
		nombreTutor1.setText("Nombre y apellidos de tutor 1");
		paneltextoNombreTutor1.add(nombreTutor1);
		textoNombreTutor1 = new JTextField(15);
		paneltextoNombreTutor1.add(textoNombreTutor1);
		contenedor.add(paneltextoNombreTutor1);

		JPanel panelApellido1 = new JPanel();
		JLabel apellido1 = new JLabel();
		apellido1.setText("Primer apellido*");
		panelApellido1.add(apellido1);
		textoApellido1 = new JTextField(20);
		panelApellido1.add(textoApellido1);
		contenedor.add(panelApellido1);

		JPanel paneltextoNombreTutor2 = new JPanel();
		JLabel nombreTutor2 = new JLabel();
		nombreTutor2.setText("Nombre y apellidos de tutor 2");
		paneltextoNombreTutor2.add(nombreTutor2);
		textoNombreTutor2 = new JTextField(15);
		paneltextoNombreTutor2.add(textoNombreTutor2);
		contenedor.add(paneltextoNombreTutor2);

		JPanel panelApellido2 = new JPanel();
		JLabel apellido2 = new JLabel();
		apellido2.setText("Segundo apellido*");
		panelApellido2.add(apellido2);
		textoApellido2 = new JTextField(20);
		panelApellido2.add(textoApellido2);
		contenedor.add(panelApellido2);

		JPanel panelNumTelTutor1 = new JPanel();
		JLabel telefono1 = new JLabel();
		telefono1.setText("N?mero de tel?fono tutor 1");
		panelNumTelTutor1.add(telefono1);
		textoTelefono1 = new JTextField(20);
		panelNumTelTutor1.add(textoTelefono1);
		contenedor.add(panelNumTelTutor1);

		JPanel panelDni = new JPanel();
		JLabel dni = new JLabel();
		dni.setText("DNI");
		panelDni.add(dni);
		textoDni = new JTextField(20);
		panelDni.add(textoDni);
		contenedor.add(panelDni);

		JPanel panelNumTelTutor2 = new JPanel();
		JLabel telefono2 = new JLabel();
		telefono2.setText("N?mero de tel?fono tutor 2");
		panelNumTelTutor2.add(telefono2);
		textoTelefono2 = new JTextField(20);
		panelNumTelTutor2.add(textoTelefono2);
		contenedor.add(panelNumTelTutor2);

		JPanel panelFechaDeNacimiento = new JPanel();
		JLabel fechaDeNacimiento = new JLabel();
		fechaDeNacimiento.setText("Fecha de nacimiento*");
		panelFechaDeNacimiento.add(fechaDeNacimiento);
		textoFechaDeNacimiento = new JTextField(20);
		textoFechaDeNacimiento.setText("aaaa-mm-dd");
		panelFechaDeNacimiento.add(textoFechaDeNacimiento);
		contenedor.add(panelFechaDeNacimiento);

		JPanel panelDireccion = new JPanel();
		JLabel direccion = new JLabel();
		direccion.setText("Direcci?n*");
		panelDireccion.add(direccion);
		textoDireccion = new JTextField(20);
		panelDireccion.add(textoDireccion);
		contenedor.add(panelDireccion);

		JPanel panelGrupo = new JPanel();
		JLabel grupo = new JLabel();
		grupo.setText("Grupo*");
		panelGrupo.add(grupo);
		// obtener los grupos de la bbdd
		ArrayList<Grupo> arrayGrupos = GrupoLogica.obtenerTodosGrupos();
		// crear selectOption
		comboGrupo = new JComboBox();
		for (int i = 0; i < arrayGrupos.size(); i++) {
			// obtener los atributos
			Grupo arrayGrupo = arrayGrupos.get(i);
			String nombreGrupo = arrayGrupo.getNivel() + arrayGrupo.getClase();
			comboGrupo.addItem(nombreGrupo);
		}
		panelGrupo.add(comboGrupo);
		contenedor.add(panelGrupo);

		// Crear boton de matricular alumno
		JPanel panelBoton = new JPanel();
		JButton botonMatricularAlumno = new JButton("Matricular alumno");
		botonMatricularAlumno.addActionListener(new EventoBotonMatricularAlumno(this));
		panelBoton.add(botonMatricularAlumno);
		contenedor.add(botonMatricularAlumno);

	}

	public class EventoBotonMatricularAlumno implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonMatricularAlumno(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			boolean esNombreValido = validarString(textoNombre.getText());
			if (!esNombreValido) {
				JOptionPane.showMessageDialog(null, "Error, el nombre introducido no es v?lido");
			} else {
				boolean esApellido1Valido = validarString(textoApellido1.getText());
				if (!esApellido1Valido) {
					JOptionPane.showMessageDialog(null, "Error, el primer apellido no es v?lido");
				} else {
					boolean esApellido2Valido = validarString(textoApellido2.getText());
					if (!esApellido2Valido) {
						JOptionPane.showMessageDialog(null, "Error, el segundo apellido no es v?lido");
					} else {
						boolean esDniValido = validarDni(textoDni.getText());
						if (!esDniValido) {
							JOptionPane.showMessageDialog(null, "Error, el dni no es v?lido");
						} else {
							boolean esFechaValida = validarFecha(textoFechaDeNacimiento.getText());
							if (!esFechaValida) {
								JOptionPane.showMessageDialog(null, "Error, el formato de la fecha no es valido");
							} else {
								boolean esGrupoValido = validarGrupo();
								if (esGrupoValido) {
									boolean sonTutoresValidos = validarTutores();
									if (sonTutoresValidos) {
										boolean esDireccionValida = validarDireccion();
										if (esDireccionValida) {
											boolean esAlumnoGuardado=AlumnoLogica.guardarAlumno(textoNombre.getText(), textoApellido1.getText(),
													textoApellido2.getText(), textoDni.getText(),
													textoDireccion.getText(),
													Date.valueOf(textoFechaDeNacimiento.getText()),
													textoNombreTutor1.getText(), textoNombreTutor2.getText(),
													textoTelefono1.getText(), textoTelefono2.getText(),
													comboGrupo.getSelectedItem().toString());
											if(esAlumnoGuardado) {
												frame.dispose();
												JFrame frameAlumnos=new FrameAlumnos();
												frameAlumnos.setVisible(true);
											}else {
												JOptionPane.showMessageDialog(null, "Error, el alumno ya existe");
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		private boolean validarString(String nombreStr) {
			boolean esStringValido=nombreStr.matches("^([a-z]+[ ]?){1,2}$");
			if (esStringValido) {
				return true;
			} else {
				return false;
			}
		}

		private boolean validarFecha(String fecha) {
			//validacion de fechaDeNacimiento
			boolean esFechaValida=fecha.matches("^(0?[1-9]|[123][0-9])\\d{2}[-](0?[1-9]|1[012])[-]([012][0-9])$");
			if (esFechaValida) {
				// si la fecha es valida
				return true;
			} else {
				return false;
			}
		}

		private boolean validarGrupo() {
			//validacion de grupo
			if(comboGrupo.getSelectedItem()!=null) {
				return true;
			} else {
				JOptionPane.showMessageDialog(null, "Error: El grupo no puede ser nulo");
				return false;
			}
		}

		private boolean validarTutores() {
			String nombreTutor1 = textoNombreTutor1.getText();
			String nombreTutor2 = textoNombreTutor2.getText();
			if (nombreTutor1.isEmpty() && nombreTutor2.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Error: Como minimo hay que introducir el nombre y apellidos de un tutor");
				return false;
			} else {
				if (nombreTutor1.length() > 0) {
					boolean esNombre1Valido = validarString(nombreTutor1);
					if (!esNombre1Valido) {
						JOptionPane.showMessageDialog(null, "Error: Nombre de tutor no valido");
						return false;
					}
				}
				if (nombreTutor2.length() > 0) {
					boolean esNombre2Valido = validarString(nombreTutor2);
					if (!esNombre2Valido) {
						JOptionPane.showMessageDialog(null, "Error: Nombre de tutor no valido");
						return false;
					}
				}
				String numeroTutor1 = textoTelefono1.getText();
				String numeroTutor2 = textoTelefono2.getText();
				if (numeroTutor1.isEmpty() && numeroTutor2.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Error: Como minimo hay que introducir el numero de telefono de un tutor");
					return false;
				} else {
					if (numeroTutor1.length() > 0) {
						boolean esTelefono1Valido = validarTelefono(numeroTutor1);
						if (!esTelefono1Valido) {
							JOptionPane.showMessageDialog(null, "Error: El telefono no es valido");
							return false;
						}
					}
					if (numeroTutor2.length() > 0) {
						boolean esTelefono2Valido = validarTelefono(numeroTutor2);
						if (!esTelefono2Valido) {
							JOptionPane.showMessageDialog(null, "Error: El telefono no es valido");
							return false;
						}
					}
					if (numeroTutor1.length() > 0 && nombreTutor1.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Error: El nombre introducido no est? asignado a un tutor");
						return false;
					}
					if (numeroTutor2.length() > 0 && nombreTutor2.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Error: El nombre introducido no est? asignado a un tutor");
						return false;
					}
					return true;
				}

			}
		}

		private boolean validarTelefono(String telefono) {
			boolean esTelefonoValido=telefono.matches("(\\+[0-9][0-9]?)?[0-9]{9}");
			if (esTelefonoValido) {
				// si el telefono es valido
				return true;
			} else {
				return false;
			}
		}

		private boolean validarDni(String dniStr) {
			if (!dniStr.isEmpty()) {
				if (dniStr.length() != 9) {
					return false;
				} else {
					String dniNumeros = dniStr.substring(0, dniStr.length() - 1);
					String dniLetra = dniStr.substring(dniStr.length() - 1, dniStr.length());
					boolean sonNumeros=dniNumeros.matches("[0-9]{8}");
					if (sonNumeros) {
						boolean esLetra=dniLetra.matches("[A-Za-z]");
						if (esLetra) {
							// si la letra es valida
							return true;
						} else {
							return false;
						}
					} else {
						return false;
					}
				}
			} else {
				return true;
			}
		}

		private boolean validarDireccion() {
			String direccion = textoDireccion.getText();
			if (direccion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error: La direccion no es correcta");
				return false;
			} else {
				return true;
			}
		}

	}
}
