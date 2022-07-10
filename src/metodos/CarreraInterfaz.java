package metodos;

import modelos.Alumno;
import modelos.Carrera;

public interface CarreraInterfaz {
	public void inscripcionCarrera (Alumno alumno, Carrera carrera);
	public void crearCarrera(String carrera);
	public void mostrarCarreras();
	public Carrera buscarCarreraPorId(int id);
	public void actualizarCarrera(String carrera, int id);
}
