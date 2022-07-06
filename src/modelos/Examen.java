package modelos;

public class Examen {
	private int id;
	private Materia materia;
	private int anio;
	private int mes;
	private int dia;




	public Examen(int id, Materia materia, int anio, int mes, int dia) {
		this.id = id;
		this.materia = materia;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
	}
	

	public Examen(Materia materia, int anio, int mes, int dia) {
		this.materia = materia;
		this.anio = anio;
		this.mes = mes;
		this.dia = dia;
	}


	public Examen() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	@Override
	public String toString() {
		return "[ ID: " + id + " ]" + " [ Materia: " + materia.getNombre() + " ]" + " [ Fecha: " + dia + "/" + mes + "/" + anio + " ]";
	}

}
