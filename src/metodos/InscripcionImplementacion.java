package metodos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Alumno;
import modelos.Carrera;
import modelos.Examen;
import modelos.Inscripcion;
import modelos.Materia;

public class InscripcionImplementacion implements InscripcionInterfaz {

	private Statement stmt;
	private String sql;
	
	public InscripcionImplementacion(Statement stmt) {
		this.stmt = stmt;
	}
	


	@Override
	public void inscripcionExamen(Inscripcion inscripcion) {
		try {
			sql = "INSERT INTO inscripcion (id, Alumno_FK, Examen_FK) VALUES (NULL, " + inscripcion.getAlumno().getId() + ", " + inscripcion.getExamen().getId() + ")";
			stmt.executeUpdate(sql);
			System.out.println("[ El alumno " + inscripcion.getAlumno().getNombre_completo() + " se ha inscripto correctamente al examen de " + inscripcion.getExamen().getMateria().getNombre() + " del " + inscripcion.getExamen().getDia() + "/" + inscripcion.getExamen().getMes() + "/" + inscripcion.getExamen().getAnio() + " ]");
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		
	}
	@Override
	public void mostrarInscripciones(int id) {
		try {
			sql = "SELECT inscripcion.*, alumno.id as alumnoId, alumno.Nombre_Completo, alumno.DNI, alumno.LU, alumno.Carrera_FK as alumnoCarrera, examen.id as examenId, examen.Materia as examenMateria, examen.anio, examen.mes, examen.dia, carrera.id as carreraId, carrera.Carrera carreraNombre, materia.id materiaId, materia.Carrera_FK materiaCarrera, materia.Nombre materiaNombre\r\n"
					+ "FROM inscripcion\r\n"
					+ "INNER JOIN alumno ON\r\n"
					+ "inscripcion.Alumno_FK=alumno.id\r\n"
					+ "INNER JOIN examen ON\r\n"
					+ "inscripcion.Examen_FK=examen.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "alumno.Carrera_FK=carrera.id\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "WHERE alumno.id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Inscripcion i = new Inscripcion();
				i.setId(rs.getInt("id"));
				Alumno a = new Alumno();
				a.setId(rs.getInt("alumnoId"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("carreraId"));
				c.setNombre(rs.getString("carreraNombre"));
				a.setCarrera(c);
				i.setAlumno(a);
				Examen ex = new Examen();
				ex.setId(rs.getInt("examenId"));
				Materia m = new Materia();
				m.setId(rs.getInt("materiaId"));
				m.setCarrera(c);
				m.setNombre(rs.getString("materiaNombre"));
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
				i.setExamen(ex);
				System.out.println(i.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		
	}
	@Override
	public boolean verificarInscripcion(int id,Inscripcion inscripcion) {
		//Este método compara las fechas de dos inscripciones y devuelve un boolean que indica si las fechas son iguales o no.
		int anio = inscripcion.getExamen().getAnio();
		int mes = inscripcion.getExamen().getMes();
		int dia = inscripcion.getExamen().getDia();
		int anioC = 0;
		int mesC = 0;
		int diaC = 0;
		boolean resultado = true;
		try {
			sql = "SELECT inscripcion.*, alumno.id as alumnoId, alumno.Nombre_Completo, alumno.DNI, alumno.LU, alumno.Carrera_FK as alumnoCarrera, examen.id as examenId, examen.Materia as examenMateria, examen.anio, examen.mes, examen.dia, carrera.id as carreraId, carrera.Carrera carreraNombre, materia.id materiaId, materia.Carrera_FK materiaCarrera, materia.Nombre materiaNombre\r\n"
					+ "FROM inscripcion\r\n"
					+ "INNER JOIN alumno ON\r\n"
					+ "inscripcion.Alumno_FK=alumno.id\r\n"
					+ "INNER JOIN examen ON\r\n"
					+ "inscripcion.Examen_FK=examen.id\r\n"
					+ "INNER JOIN carrera ON\r\n"
					+ "alumno.Carrera_FK=carrera.id\r\n"
					+ "INNER JOIN materia ON\r\n"
					+ "examen.Materia=materia.id\r\n"
					+ "WHERE alumno.id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Inscripcion i = new Inscripcion();
				i.setId(rs.getInt("id"));
				Alumno a = new Alumno();
				a.setId(rs.getInt("alumnoId"));
				a.setNombre_completo(rs.getString("Nombre_Completo"));
				a.setDni(rs.getString("DNI"));
				a.setLu(rs.getString("LU"));
				Carrera c = new Carrera();
				c.setId(rs.getInt("carreraId"));
				c.setNombre(rs.getString("carreraNombre"));
				a.setCarrera(c);
				i.setAlumno(a);
				Examen ex = new Examen();
				ex.setId(rs.getInt("examenId"));
				Materia m = new Materia();
				m.setId(rs.getInt("materiaId"));
				m.setCarrera(c);
				m.setNombre(rs.getString("materiaNombre"));
				ex.setMateria(m);
				ex.setAnio(rs.getInt("anio"));
				ex.setMes(rs.getInt("mes"));
				ex.setDia(rs.getInt("dia"));
				i.setExamen(ex);
				anioC=i.getExamen().getAnio();
				mesC=i.getExamen().getMes();
				diaC=i.getExamen().getDia();
				if (anio == anioC && mes == mesC && dia == diaC) {
					resultado=false;
				} 
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		return resultado;
	}
	@Override
	public void eliminarInscripcion(int alumno, int examen) {
		try {
			sql="DELETE FROM inscripcion\r\n"
					+ "WHERE inscripcion.Alumno_FK=" + alumno + " AND inscripcion.Examen_FK=" + examen;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {	
			System.err.println("[ Hubo un error al realizar la consulta ]");
		}
		
	}
}
