package modelos;


public class Alumno {
	private int id;
	private String nombre_completo;
	private String dni;
	private String lu;
	private Carrera carrera;

	public Alumno() {
	}

	public Alumno(String nombre_completo, String dni, String lu, Carrera carrera) {
		this.nombre_completo = nombre_completo;
		this.dni = dni;
		this.lu = lu;
		this.carrera = carrera;
	}

	public Alumno(int id, String nombre_completo, String dni, String lu, Carrera carrera) {
		this.id = id;
		this.nombre_completo = nombre_completo;
		this.dni = dni;
		this.lu = lu;
		this.carrera = carrera;
	}

	public Alumno(String nombre_completo, String dni, String lu) {
		this.nombre_completo = nombre_completo;
		this.dni = dni;
		this.lu = lu;
	}
	

	public Alumno(String nombre_completo, String dni) {
		this.nombre_completo = nombre_completo;
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	@Override
	public String toString() {
		return "[ ID: " + id + " ]" + " [ Nombre: " + nombre_completo + " ] [ DNI: " + dni + " ] [ LU: "
				+ lu + " ] [ Carrera: " + carrera.getNombre() + " ]";
	}

}
