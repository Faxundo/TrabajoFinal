package metodos;

import modelos.Alumno;

public interface AlumnoInterfaz {
	public void mostrarAlumnos ();
	public Alumno buscarAlumno (int id);
	public void nuevoAlumno(Alumno alumno);
	public void eliminarAlumno(int id);
	public void actualizarAlumno(String nombre, String dni, int id);
}
