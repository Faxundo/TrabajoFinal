package modelos;

public class Materia {
	private int id;
	private Carrera carrera;
	private String nombre;

	public Materia(Carrera carrera, String nombre) {
		this.carrera = carrera;
		this.nombre = nombre;
	}

	public Materia() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "[ID: " + id + " ]" + " [ Carrera: " + carrera.getNombre() + " ] " + " [ Materia: " + nombre + " ]";
	}

}
