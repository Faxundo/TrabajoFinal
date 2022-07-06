package metodos;

import modelos.Materia;

public interface MateriaInterfaz {
	public void crearMateria(Materia materia);
	public void mostrarMaterias();
	public Materia buscarPorId(int id);
}
