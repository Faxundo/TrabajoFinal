package metodos;

import modelos.Alumno;
import modelos.Examen;
import modelos.Resultado;

public interface ResultadoInterfaz {
	public Resultado buscarPorId(int id);
	public Resultado buscarPorMateria(int id);
	public void mostrarResultados(Resultado resultado);
	public void registrarResultado(Examen examen, Alumno alumno, int nota);
	public void regular(int id);

}
