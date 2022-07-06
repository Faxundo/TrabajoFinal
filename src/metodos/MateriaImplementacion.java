package metodos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Carrera;
import modelos.Materia;

public class MateriaImplementacion implements MateriaInterfaz {
	
	private Statement stmt;
	private String sql;
	

	
	public MateriaImplementacion(Statement stmt) {
		this.stmt = stmt;
	}



	@Override
	public void crearMateria(Materia materia) {
		try {
			sql = "INSERT INTO materia (id, Carrera_FK, Nombre) VALUES (NULL, '" + materia.getCarrera().getId() + "', '" + materia.getNombre() + "')";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		
		System.out.println("[ La materia '" + materia.getNombre() + "' se agregó correctamente en la carrera de " + materia.getCarrera().getNombre() +  " ]");
	}



	@Override
	public void mostrarMaterias() {
		sql="SELECT materia.*, carrera.id as idcarrera, carrera.Carrera as Carrera\r\n"
				+ "FROM materia\r\n"
				+ "INNER JOIN carrera ON\r\n"
				+ "materia.Carrera_FK=carrera.id";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Materia m = new Materia();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("Nombre"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("Carrera"));
				m.setCarrera(c);
				System.out.println(m.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		
	}



	@Override
	public Materia buscarPorId(int id) {
		Materia m = null;
		sql = "SELECT materia.*, carrera.id as idcarrera, carrera.Carrera as Carrera\r\n"
				+ "FROM materia\r\n"
				+ "INNER JOIN carrera ON\r\n"
				+ "materia.Carrera_FK=carrera.id\r\n"
				+ "WHERE materia.id="+id;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				m = new Materia();
				m.setId(rs.getInt("id"));
				m.setNombre(rs.getString("Nombre"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("Carrera"));
				m.setCarrera(c);
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return m;
	}

}
