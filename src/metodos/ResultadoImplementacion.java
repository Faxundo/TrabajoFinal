package metodos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Alumno;
import modelos.Carrera;
import modelos.Examen;
import modelos.Materia;
import modelos.Resultado;

public class ResultadoImplementacion implements ResultadoInterfaz{

	private Statement stmt;
	private String sql;
	
	
	public ResultadoImplementacion(Statement stmt) {
		this.stmt = stmt;
	}


	@Override
	public void registrarResultado(Examen examen, Alumno alumno, int nota) {
		try {
			sql = "INSERT INTO resultado (Alumno_FK, Numero_Mesa, Nota) VALUES (" + alumno.getId() + ", " + examen.getId() + ", " + nota + ")";
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		
		System.out.println("[ Se ha registrado al alumno " + alumno.getNombre_completo() + " en el exámen de " + examen.getMateria().getNombre() + " con una nota de " + nota + " ]");
	}



	@Override
	public void mostrarResultados(Resultado resultado) {
		try {
			sql="SELECT resultado.*, alumno.id as alumnoId, alumno.Nombre_Completo, alumno.DNI, alumno.LU, alumno.Carrera_FK as alumnoCarrera, examen.id as examenId, examen.Materia examenMateria, examen.anio, examen.mes, examen.dia, materia.id as materiaId, materia.Carrera_FK as materiaCarrera, materia.Nombre materiaNombre, carrera.id as carreraId, carrera.Carrera nombreCarrera\r\n"
					+ "FROM resultado\r\n"
					+ "INNER JOIN alumno ON\r\n"
					+ "resultado.Alumno_FK=alumno.id\r\n"
					+ "INNER JOIN examen ON\r\n"
					+ "resultado.Numero_Mesa=examen.id\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "materia.Carrera_FK=carrera.id";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Resultado r = new Resultado();
				Alumno a = new Alumno();
				a.setId(rs.getInt("alumnoId"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("carreraId"));
				c.setNombre("nombreCarrera");
				a.setCarrera(c);
				r.setAlumno(a);
				Examen ex = new Examen();
				ex.setId(rs.getInt("examenId"));
				Materia m = new Materia();
				m.setId(rs.getInt("materiaId"));
				m.setNombre(rs.getString("materiaNombre"));
				m.setCarrera(c);
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
				r.setNumero_mesa(ex);
				r.setNota(rs.getInt("Nota"));
				System.out.println(r.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}	
		
	}


	@Override
	public Resultado buscarPorId(int id) {
		Resultado r = null;
		try {
			sql = "SELECT resultado.*, alumno.id as alumnoId, alumno.Nombre_Completo, alumno.DNI, alumno.LU, alumno.Carrera_FK as alumnoCarrera, examen.id as examenId, examen.Materia examenMateria, examen.anio, examen.mes, examen.dia, materia.id as materiaId, materia.Carrera_FK as materiaCarrera, materia.Nombre materiaNombre, carrera.id as carreraId, carrera.Carrera nombreCarrera\r\n"
					+ "FROM resultado\r\n"
					+ "INNER JOIN alumno ON\r\n"
					+ "resultado.Alumno_FK=alumno.id\r\n"
					+ "INNER JOIN examen ON\r\n"
					+ "resultado.Numero_Mesa=examen.id\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "materia.Carrera_FK=carrera.id\r\n"
					+ "WHERE alumno.id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				r = new Resultado();
				Alumno a = new Alumno();
				a.setId(rs.getInt("alumnoId"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("carreraId"));
				c.setNombre("nombreCarrera");
				a.setCarrera(c);
				r.setAlumno(a);
				Examen ex = new Examen();
				ex.setId(rs.getInt("examenId"));
				Materia m = new Materia();
				m.setId(rs.getInt("materiaId"));
				m.setNombre(rs.getString("materiaNombre"));
				m.setCarrera(c);
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
				r.setNumero_mesa(ex);
				r.setNota(rs.getInt("Nota"));
				System.out.println(r.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return r;
	}


	@Override
	public Resultado buscarPorMateria(int id) {
		Resultado r = null;
		try {
			sql = "SELECT resultado.*, alumno.id as alumnoId, alumno.Nombre_Completo, alumno.DNI, alumno.LU, alumno.Carrera_FK as alumnoCarrera, examen.id as examenId, examen.Materia examenMateria, examen.anio, examen.mes, examen.dia, materia.id as materiaId, materia.Carrera_FK as materiaCarrera, materia.Nombre materiaNombre, carrera.id as carreraId, carrera.Carrera nombreCarrera\r\n"
					+ "FROM resultado\r\n"
					+ "INNER JOIN alumno ON\r\n"
					+ "resultado.Alumno_FK=alumno.id\r\n"
					+ "INNER JOIN examen ON\r\n"
					+ "resultado.Numero_Mesa=examen.id\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "materia.Carrera_FK=carrera.id\r\n"
					+ "WHERE resultado.Numero_Mesa=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				r = new Resultado();
				Alumno a = new Alumno();
				a.setId(rs.getInt("alumnoId"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("carreraId"));
				c.setNombre("nombreCarrera");
				a.setCarrera(c);
				r.setAlumno(a);
				Examen ex = new Examen();
				ex.setId(rs.getInt("examenId"));
				Materia m = new Materia();
				m.setId(rs.getInt("materiaId"));
				m.setNombre(rs.getString("materiaNombre"));
				m.setCarrera(c);
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
				r.setNumero_mesa(ex);
				r.setNota(rs.getInt("Nota"));
				//Si está vacío da null
				System.out.println(r.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return r;
	}


	@Override
	public void regular(int id) {
		Resultado r = null;
		int cont = 0;
		try {
			sql = "SELECT resultado.*, alumno.id as alumnoId, alumno.Nombre_Completo, alumno.DNI, alumno.LU, alumno.Carrera_FK as alumnoCarrera, examen.id as examenId, examen.Materia examenMateria, examen.anio, examen.mes, examen.dia, materia.id as materiaId, materia.Carrera_FK as materiaCarrera, materia.Nombre materiaNombre, carrera.id as carreraId, carrera.Carrera nombreCarrera\r\n"
					+ "FROM resultado\r\n"
					+ "INNER JOIN alumno ON\r\n"
					+ "resultado.Alumno_FK=alumno.id\r\n"
					+ "INNER JOIN examen ON\r\n"
					+ "resultado.Numero_Mesa=examen.id\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "materia.Carrera_FK=carrera.id\r\n"
					+ "WHERE alumno.id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				r = new Resultado();
				Alumno a = new Alumno();
				a.setId(rs.getInt("alumnoId"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("carreraId"));
				c.setNombre("nombreCarrera");
				a.setCarrera(c);
				r.setAlumno(a);
				Examen ex = new Examen();
				ex.setId(rs.getInt("examenId"));
				Materia m = new Materia();
				m.setId(rs.getInt("materiaId"));
				m.setNombre(rs.getString("materiaNombre"));
				m.setCarrera(c);
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
				r.setNumero_mesa(ex);
				r.setNota(rs.getInt("Nota"));
				if (r.getNota() >= 6) {
					cont++;
				}
			}
			if (cont >= 3) {
				System.out.println("[ Eres un Alumno Regular ]");
			} else {
				System.out.println("[ No eres Alumno Regular ]");
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
	}


}
