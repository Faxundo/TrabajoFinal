package metodos;

import modelos.Alumno;
import modelos.Carrera;
import modelos.Inscripcion;

public interface AlumnoInterfaz {
	public void mostrarAlumnos ();
	public Alumno buscarAlumno (int id);
	public void inscripcionCarrera (Alumno alumno, Carrera carrera);
	public void inscripcionExamen (Inscripcion inscripcion);
	public void mostrarInscripciones (int id);
	public void darBajaExamen (int id1, int id2);
	public boolean verificarInscripcion (int id, Inscripcion inscripcion);
	public void nuevoAlumno(Alumno alumno);
	public void eliminarAlumno(Alumno alumno);
}
