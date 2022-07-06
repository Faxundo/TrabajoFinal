package metodos;

import modelos.Carrera;

public interface CarreraInterfaz {
	public void crearCarrera(String carrera);
	public void mostrarCarreras();
	public Carrera buscarPorId(int id);
}
