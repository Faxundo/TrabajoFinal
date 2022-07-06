package modelos;

public class Inscripcion {
	private int id;
	private Alumno alumno;
	private Examen examen;

	public Inscripcion(Alumno alumno, Examen examen) {
		this.alumno = alumno;
		this.examen = examen;
	}
	
	public Inscripcion() {
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Examen getExamen() {
		return examen;
	}
	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	@Override
	public String toString() {
		return "[ ID: " + examen.getId() + " ] [ " + examen.getMateria().getNombre() + " ] [ " + examen.getAnio() + "/" + examen.getMes() + "/" + examen.getDia() + " ]";
	}
	
	
}
