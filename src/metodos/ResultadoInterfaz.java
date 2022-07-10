package metodos;

import modelos.Alumno;
import modelos.Examen;
import modelos.Resultado;

public interface ResultadoInterfaz {
	public Resultado buscarResultadoPorId(int id);
	public Resultado buscarResultadoPorMateria(int id);
	public void mostrarResultados(Resultado resultado);
	public void registrarResultado(Examen examen, Alumno alumno, int nota);
	public void regular(int id);
	public void borrarResultado(int alumno, int examen);
	public void actualizarResultado(int nota, int alumno, int materia);
	public Resultado buscarResultadoEspecifico(int alumno, int examen);
}
