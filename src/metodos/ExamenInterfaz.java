package metodos;

import modelos.Examen;

public interface ExamenInterfaz {
	public void mostrarExamenes();
	public Examen buscarPorId(int id);
	public void mostrarPorCarrera(int id);
	public void crearMesaDeExamen(Examen examen);
}
