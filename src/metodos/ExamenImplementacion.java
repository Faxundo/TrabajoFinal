package metodos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelos.Carrera;
import modelos.Examen;
import modelos.Materia;

public class ExamenImplementacion implements ExamenInterfaz{

	private Statement stmt;
	private String sql;



	public ExamenImplementacion(Statement stmt) {
		this.stmt = stmt;
	}

	@Override
	public void crearMesaDeExamen(Examen examen) {
		String mes = null;
		try {
			sql = "INSERT INTO examen (id, Materia, anio, mes, dia) VALUES (NULL, " + examen.getMateria().getId() + ", " + examen.getAnio() + ", " + examen.getMes() + ", " + examen.getDia() + ")";
			stmt.executeUpdate(sql);
			switch (examen.getMes()) {
			case 1:
				mes = "Enero";
				break;
			case 2:
				mes = "Febrero";
				break;
			case 3:
				mes = "Marzo";
				break;
			case 4:
				mes = "Abril";
				break;
			case 5:
				mes = "Mayo";
				break;
			case 6:
				mes = "Junio";
				break;
			case 7:
				mes = "Julio";
				break;
			case 8:
				mes = "Agosto";
				break;
			case 9:
				mes = "Septiembre";
				break;
			case 10:
				mes = "Octubre";
				break;
			case 11:
				mes = "Noviembre";
				break;
			case 12:
				mes = "Diciembre";
				break;
			default:
				break;
			}
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		System.out.println("[ La mesa de examenes para " + examen.getMateria().getNombre() + " se ha registrado correctamente para el " + examen.getDia() + " de " + mes + " del año " + examen.getAnio() + " ]");
	}

	@Override
	public void mostrarExamenes() {
		try {
			sql="SELECT examen.*, materia.id as idmateria, materia.Carrera_FK as Carrera, materia.Nombre as NombreMateria, carrera.id idcarrera, carrera.Carrera as NombreCarrera\r\n"
					+ "FROM examen\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "materia.Carrera_FK=carrera.id";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Examen e = new Examen();
				e.setId(rs.getInt("id"));
				e.setAnio(rs.getInt("anio"));
				e.setMes(rs.getInt("mes"));
				e.setDia(rs.getInt("dia"));
				Materia m = new Materia();
				m.setId(rs.getInt("idmateria"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("idcarrera"));
				c.setNombre(rs.getString("NombreCarrera"));
				m.setCarrera(c);
				m.setNombre(rs.getString("NombreMateria"));
				e.setMateria(m);
				System.out.println(e.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}

	@Override
	public Examen buscarExamenPorId(int id) {
		Examen ex = null;
		try {
			sql = "SELECT examen.*, materia.id as idmateria, materia.Carrera_FK as Carrera, materia.Nombre as NombreMateria, carrera.id idcarrera, carrera.Carrera as NombreCarrera\r\n"
					+ "FROM examen\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "materia.Carrera_FK=carrera.id\r\n"
					+ "WHERE examen.id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ex = new Examen();
				ex.setId(rs.getInt("id"));
				Materia m = new Materia();
				m.setId(rs.getInt("idmateria"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("idcarrera"));
				c.setNombre(rs.getString("NombreCarrera"));
				m.setCarrera(c);
				m.setNombre(rs.getString("NombreMateria"));
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return ex;
	}

	@Override
	public List<Examen> mostrarExamenesPorCarrera(int id) {
		List<Examen> lista = new ArrayList<Examen>();
		try {
				sql = "SELECT examen.*, materia.id as idmateria, materia.Carrera_FK as Carrera, materia.Nombre as NombreMateria, carrera.id idcarrera, carrera.Carrera as NombreCarrera\r\n"
						+ "FROM examen\r\n"
						+ "INNER JOIN materia ON\r\n"
						+ "examen.Materia=materia.id\r\n"
						+ "INNER JOIN carrera ON\r\n"
						+ "materia.Carrera_FK=carrera.id\r\n"
						+ "WHERE materia.Carrera_FK=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Examen ex = new Examen();
				ex.setId(rs.getInt("id"));
				Materia m = new Materia();
				m.setId(rs.getInt("idmateria"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("idcarrera"));
				c.setNombre(rs.getString("NombreCarrera"));
				m.setCarrera(c);
				m.setNombre(rs.getString("NombreMateria"));
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
				lista.add(ex);
				System.out.println(ex.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return lista;
	}

	@Override
	public void darBajaExamen(int id1, int id2) {
		try {
			sql = "DELETE FROM inscripcion WHERE inscripcion.Alumno_FK=" + id1 + " AND inscripcion.Examen_FK=" + id2;
			stmt.executeUpdate(sql);
			System.out.println("[ Has sido dado de baja exitósamente ]");
		}catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		
	}

	@Override
	public void actualizarExamen(int mes, int dia, int id) {
		try {
			sql = "UPDATE examen SET mes='" + mes + "', dia='" + dia + "' WHERE examen.id=" + id;
			stmt.executeUpdate(sql);
			System.out.println("\n[ Los datos del exámen han sido actualizados ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}
	
}
