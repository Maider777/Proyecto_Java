package Logica;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Proyecto.Alumno;
import conexion.BBDD;

public class GestionAlumnos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//crear proyecto a parte y meter todo lo del proyecto
		//nombrar Proyecto Modelo
		//clases en mayus y package en minus
		Alumno a=new Alumno("Maider","Peñalba","Gomez");
		nuevoAlumno(a);
	}
	
	public static void nuevoAlumno(Alumno alumno) {
		//crear el objeto de la BBDD
		BBDD bd = new BBDD();
		//conectar a la BBDD
		bd.conectar();
		//obtener la conexion
		Connection conexion=bd.getConnection();
		//Statement para crear la query
		Statement s;
		try {
			s = conexion.createStatement();
			//hacer un insert
			s.executeUpdate("insert into alumnos (Nombre,Apellido1,Apellido2) values ('"+alumno.getNombre()+"','"+alumno.getApellido1()+"','"+alumno.getApellido2()+"')");
			//desconectar la BBDD
			bd.desconectar();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	//TODO dar de baja a alumno(delete) por id
	//si es select, executeQuery
	//si es insert o delete, executeUpdate
	//TODO asignar grupo a alumno(update)
	//TODO mostrar un alumno por nombre(select)
	//TODO mostrar todos los alumnos(select)
	//en las select, solo preparar la consulta
	//TODO nueva clase gestionGrupos, crear grupo y eliminar grupo y mostrar grupos
	//lo mismo para profes, crear profe, eliminar profe, creando nueva clase
}
