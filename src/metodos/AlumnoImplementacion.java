package metodos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.*;

public class AlumnoImplementacion implements AlumnoInterfaz {
	private Statement stmt;
	private String sql;
	
	public AlumnoImplementacion(Statement stmt) {
		this.stmt = stmt;
	}






	@Override
	public void mostrarAlumnos() {
		try {
			sql="SELECT alumno.*, carrera.id as idcarrera, carrera.Carrera as Carrera \r\n"
					+ "FROM alumno \r\n"
					+ "INNER JOIN carrera ON \r\n"
					+ "alumno.Carrera_FK=carrera.id;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Alumno a = new Alumno();
				a.setId(rs.getInt("id"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("Carrera"));
				a.setCarrera(c);
				System.out.println(a.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}	
	}

	@Override
	public Alumno buscarAlumno(int id) {
		Alumno a = null;
		try {
			sql = "SELECT alumno.*, carrera.id as idcarrera, carrera.Carrera as Carrera \r\n"
					+ "FROM alumno \r\n"
					+ "INNER JOIN carrera ON \r\n"
					+ "alumno.Carrera_FK=carrera.id\r\n"
					+ "WHERE alumno.id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				a = new Alumno();
				a.setId(rs.getInt("id"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("idcarrera"));
				c.setNombre(rs.getString("Carrera"));
				a.setCarrera(c);
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return a;
	}

	

	

	@Override
	public void nuevoAlumno(Alumno alumno) {
		try {
			sql = "INSERT INTO alumno (id, Nombre_Completo, DNI, LU, Carrera_FK) VALUES (NULL, '" + alumno.getNombre_completo() + "', '" + alumno.getDni() + "', '" + alumno.getLu() + "', " + 1 + ")";
			stmt.executeUpdate(sql);
			System.out.println("[ El alumno '" + alumno.getNombre_completo() + "' se ha registrado correctamente ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}

	@Override
	public void eliminarAlumno(int id) {
		try {
			sql="DELETE FROM alumno\r\n"
					+ "WHERE alumno.id=" + id;
			stmt.executeUpdate(sql);
			System.out.println("[ Se ha eliminado al alumno correctamente ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}


	@Override
	public void actualizarAlumno(String nombre, String dni, int id) {
		try {
			sql = "UPDATE alumno SET Nombre_Completo='" + nombre + "', DNI='" + dni + "' WHERE alumno.id=" + id;
			stmt.executeUpdate(sql);
			System.out.println("\n[ Los datos del alumno han sido actualizados ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}

	


}
