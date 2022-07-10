package metodos;

import java.util.List;

import modelos.Examen;

public interface ExamenInterfaz {
	public void mostrarExamenes();
	public Examen buscarExamenPorId(int id);
	public List<Examen> mostrarExamenesPorCarrera(int id);
	public void crearMesaDeExamen(Examen examen);
	public void darBajaExamen (int id1, int id2);
	public void actualizarExamen(int mes, int dia, int id);
}
