package metodos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Alumno;
import modelos.Carrera;

public class CarreraImplementacion implements CarreraInterfaz {
	private Statement stmt;
	private String sql;

	public CarreraImplementacion(Statement stmt) {
		this.stmt = stmt;
	}

	@Override
	public void crearCarrera(String carrera) {
		try {
			sql = "INSERT INTO carrera (id, Carrera) VALUES (NULL, '" + carrera + "')";
			stmt.executeUpdate(sql);
			System.out.println("[ La carrera '" + carrera + "' se ha registrado correctamente ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}

	@Override
	public void mostrarCarreras() {
		try {
			sql="SELECT * FROM carrera \r\n"
					+ "WHERE id!=1";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Carrera c = new Carrera();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("Carrera"));
				System.out.println(c.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}

	@Override
	public Carrera buscarCarreraPorId(int id) {
		Carrera c = null;
		try {
			sql = "SELECT * FROM carrera WHERE id = " + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c = new Carrera();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("Carrera"));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return c;
	}
	@Override
	public void inscripcionCarrera(Alumno alumno, Carrera carrera) {
		try {
			sql = "UPDATE alumno SET Carrera_FK=" + carrera.getId() + " WHERE alumno.id=" + alumno.getId();
			stmt.executeUpdate(sql);
			System.out.println("[ Te has inscripto correctamente a la carrera de " + carrera.getNombre() + " ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}

	@Override
	public void actualizarCarrera(String carrera, int id) {
		try {
			sql = "UPDATE carrera SET Carrera='" + carrera + "' WHERE carrera.id=" + id;
			stmt.executeUpdate(sql);
			System.out.println("\n[ El nombre de la carrera ha sido actualizado ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}
}
