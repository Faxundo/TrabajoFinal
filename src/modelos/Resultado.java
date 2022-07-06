package modelos;

public class Resultado {
	private Alumno alumno;
	private Examen numero_mesa;
	private int nota;
	public Resultado(Alumno alumno, Examen numero_mesa, int nota) {
		this.alumno = alumno;
		this.numero_mesa = numero_mesa;
		this.nota = nota;
		
	}
	public Resultado() {
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Examen getNumero_mesa() {
		return numero_mesa;
	}
	public void setNumero_mesa(Examen numero_mesa) {
		this.numero_mesa = numero_mesa;
	}
	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "[ Alumno: " + alumno.getNombre_completo() + " ]" + " [ Examen de " + numero_mesa.getMateria().getNombre() + " en la mesa N° " + numero_mesa.getId() + "] [ Nota: " + nota + " ]";
	}
	
	
}
