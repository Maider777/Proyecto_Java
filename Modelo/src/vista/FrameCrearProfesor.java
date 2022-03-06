package vista;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logica.ProfesorLogica;

public class FrameCrearProfesor extends JFrame {

	JTextField textoDni;
	JTextField textoFechaDeNacimiento;
	JTextField textoNombre;
	JTextField textoTelefono;
	JTextField textoApellido1;
	JTextField textoEmail;
	JTextField textoApellido2;
	JTextField textoDireccion;

	public FrameCrearProfesor() {
		super("Crear profesor");
		this.setSize(800, 250);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container contenedor = getContentPane();

		// GridLayout para poner por filas y columnas
		// como parametro, se le pasan primero las filas y luego las columnas
		GridLayout glt = new GridLayout(5, 2);
		contenedor.setLayout(glt);

		JPanel panelDni = new JPanel();
		JLabel dni = new JLabel();
		dni.setText("DNI*");
		panelDni.add(dni);
		textoDni = new JTextField(20);
		panelDni.add(textoDni);
		contenedor.add(panelDni);

		JPanel panelFechaDeNacimiento = new JPanel();
		JLabel fechaDeNacimiento = new JLabel();
		fechaDeNacimiento.setText("Fecha de nacimiento*");
		panelFechaDeNacimiento.add(fechaDeNacimiento);
		textoFechaDeNacimiento = new JTextField(20);
		textoFechaDeNacimiento.setText("aaaa-mm-dd");
		panelFechaDeNacimiento.add(textoFechaDeNacimiento);
		contenedor.add(panelFechaDeNacimiento);

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

		JPanel panelNumeroTelefono = new JPanel();
		JLabel telefono = new JLabel();
		telefono.setText("Número de teléfono*");
		panelNumeroTelefono.add(telefono);
		textoTelefono = new JTextField(20);
		panelNumeroTelefono.add(textoTelefono);
		contenedor.add(panelNumeroTelefono);

		JPanel panelApellido1 = new JPanel();
		JLabel apellido1 = new JLabel();
		apellido1.setText("Primer apellido*");
		panelApellido1.add(apellido1);
		textoApellido1 = new JTextField(20);
		panelApellido1.add(textoApellido1);
		contenedor.add(panelApellido1);

		JPanel panelEmail = new JPanel();
		JLabel email = new JLabel();
		email.setText("Email*");
		panelEmail.add(email);
		textoEmail = new JTextField(20);
		panelEmail.add(textoEmail);
		contenedor.add(panelEmail);

		JPanel panelApellido2 = new JPanel();
		JLabel apellido2 = new JLabel();
		apellido2.setText("Segundo apellido*");
		panelApellido2.add(apellido2);
		textoApellido2 = new JTextField(20);
		panelApellido2.add(textoApellido2);
		contenedor.add(panelApellido2);

		JPanel panelDireccion = new JPanel();
		JLabel direccion = new JLabel();
		direccion.setText("Direccion*");
		panelDireccion.add(direccion);
		textoDireccion = new JTextField(20);
		panelDireccion.add(textoDireccion);
		contenedor.add(panelDireccion);

		// Crear boton de matricular profesor
		JPanel panelBoton = new JPanel();
		JButton botonMatricularProfesor = new JButton("Guardar profesor");
		botonMatricularProfesor.addActionListener(new EventoBotonMatricularProfesor(this));
		panelBoton.add(botonMatricularProfesor);
		contenedor.add(botonMatricularProfesor);

	}

	public class EventoBotonMatricularProfesor implements ActionListener {
		
		JFrame frame;
		
		public EventoBotonMatricularProfesor(JFrame frame) {
			this.frame=frame;
		}

		public void actionPerformed(ActionEvent arg0) {
			boolean esDniValido=validarDni(textoDni.getText());
			if(!esDniValido) {
				JOptionPane.showMessageDialog(null, "Error, el dni no es valido");
			}else {
				boolean esNombreValido=validarString(textoNombre.getText());
				if(!esNombreValido) {
					JOptionPane.showMessageDialog(null, "Error, el nombre no es valido");
				}else {
					boolean esApellido1Valido=validarString(textoApellido1.getText());
					if(!esApellido1Valido) {
						JOptionPane.showMessageDialog(null, "Error, el primer apellido no es valido");
					}else {
						boolean esApellido2Valido=validarString(textoApellido2.getText());
						if(!esApellido2Valido) {
							JOptionPane.showMessageDialog(null, "Error, el segundo apellido no es valido");
						}else {
							boolean esFechaValida=validarFecha(textoFechaDeNacimiento.getText());
							if(!esFechaValida) {
								JOptionPane.showMessageDialog(null, "Error, la fecha no es correcta");
							}else {
								boolean esTelefonoValido=validarTelefono(textoTelefono.getText());
								if(!esTelefonoValido) {
									JOptionPane.showMessageDialog(null, "Error, el telefono no es valido");
								}else {
									boolean esEmailValido=validarEmail(textoEmail.getText());
									if(!esEmailValido) {
										JOptionPane.showMessageDialog(null, "Error, el email no es valido");
									}else {
										boolean esDireccionValida=validarDireccion(textoDireccion.getText());
										if(!esDireccionValida) {
											JOptionPane.showMessageDialog(null, "Error, el segundo apellido no es valido");
										}else {
											boolean esProfesorGuardado=ProfesorLogica.guardarProfesor(textoDni.getText(),textoNombre.getText(),textoApellido1.getText(),textoApellido2.getText(),Date.valueOf(textoFechaDeNacimiento.getText()),textoTelefono.getText(), textoEmail.getText(),textoDireccion.getText());
											if(esProfesorGuardado) {
												frame.dispose();
												JFrame mostrarProfesor=new FrameProfesores();
												mostrarProfesor.setVisible(true);
											}else {
												JOptionPane.showMessageDialog(null, "Error, el profesor ya existe");
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

		private boolean validarDni(String dniStr) {
			if (!dniStr.isEmpty()) {
				if (dniStr.length() != 9) {
					return false;
				} else {
					String dniNumeros = dniStr.substring(0, dniStr.length() - 1);
					String dniLetra = dniStr.substring(dniStr.length() - 1, dniStr.length());
					boolean sonNumeros=dniNumeros.matches("[0-9]{8}");
					if (sonNumeros) {
						//si los numeros son validos
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

		private boolean validarTelefono(String telefono) {
			boolean esTelefonoValido=telefono.matches("(\\+[0-9][0-9]?)?[0-9]{9}");
			if (esTelefonoValido) {
				// si el telefono es valido
				return true;
			} else {
				return false;
			}
		}

		private boolean validarEmail(String email) {
			boolean esEmailValido=email.matches("^[A-Za-z]+[0-9]*[@][a-z]+[.][a-z]+$");
			if (esEmailValido) {
				// si el email es valido
				return true;
			} else {
				return false;
			}
		}

		private boolean validarDireccion(String direccion) {
			if (direccion.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Error: La direccion no es correcta");
				return false;
			} else {
				return true;
			}
		}

	}

}
