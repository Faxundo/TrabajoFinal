package metodos;

import modelos.Inscripcion;

public interface InscripcionInterfaz {
	public void inscripcionExamen (Inscripcion inscripcion);
	public void mostrarInscripciones (int id);
	public boolean verificarInscripcion (int id, Inscripcion inscripcion);
	public void eliminarInscripcion(int alumno, int examen);

}
