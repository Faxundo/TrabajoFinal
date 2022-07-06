package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import conexion.Conexion;
import metodos.*;
import modelos.*;

public class Controlador {
	
	public static Statement stmt = null;
	public static void programa() {
		//Servicios
		CarreraInterfaz carreras = new CarreraImplementacion(stmt);
		AlumnoInterfaz alumnos = new AlumnoImplementacion(stmt);
		MateriaInterfaz materias = new MateriaImplementacion(stmt);
		ExamenInterfaz examenes = new ExamenImplementacion(stmt);
		ResultadoInterfaz resultados = new ResultadoImplementacion(stmt);
		
		switch (opcion) {
		case "1":
			menuAlumno();
			switch (opcion) {
			case "1":
				System.out.println("———————————");
				System.out.println("[ Crear Nuevo Perfil de Alumno ]");
				System.out.print("- Ingrese su nombre completo: ");
				String respuestaA61 = sc.nextLine();
				System.out.print("\n- Ingrese su DNI: ");
				String respuestaA62 = sc.nextLine();
				int cantidad = 0;
				String lu = "";
				while (cantidad != 6) {
					Random aleatorio = new Random();
					int valor = aleatorio.nextInt((8+1)+1);
					lu = lu + String.valueOf(valor);
					cantidad++;					
				}
				Alumno a = new Alumno(respuestaA61, respuestaA62, lu);
				System.out.println("");
				alumnos.nuevoAlumno(a);
				respuestaSiNo();
				break;
			case "2":
				System.out.println("———————————");
				System.out.println("[ Inscribir a Carrera ]");
				System.out.println("- ¿Quien desea inscribirse a una carrera?");
				System.out.println("");
				alumnos.mostrarAlumnos();
				System.out.println("");
				System.out.print("- Ingrese el ID del alumno: ");
				int respuestaA21 = sc.nextInt();
				Alumno A21 = alumnos.buscarAlumno(respuestaA21);
				if (A21 != null) {
					System.out.println("");
					System.out.println("- ¿A que carrera desea inscribirse?");
					System.out.println("");
					carreras.mostrarCarreras();
					System.out.println("");
					System.out.print("- Ingrese el ID de la carrera: ");
					int respuestaA22 = sc.nextInt();
					Carrera A22 = carreras.buscarPorId(respuestaA22);
					if (A22 != null) {
						System.out.println("");
						alumnos.inscripcionCarrera(alumnos.buscarAlumno(respuestaA21), carreras.buscarPorId(respuestaA22));;
					} else {
						System.out.println("");
						System.err.println("[ No se encontró la carrera buscada ]");
					}
				} else {
					System.out.println("");
					System.err.println("[ No se encontró al alumno buscado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "3": 
				System.out.println("———————————");
				System.out.println("[ Inscribir a Mesa de Exámenes ]");
				System.out.println("- ¿Quien desea inscribirse a una mesa de exámenes?\n");
				alumnos.mostrarAlumnos();
				System.out.println("");
				System.out.print("- Ingrese el ID del alumno: ");
				int respuestaA31 = sc.nextInt();
				Alumno A31 = alumnos.buscarAlumno(respuestaA31);
				if (A31 != null) {
					System.out.println("");
					System.out.println("- ¿A que exámen desea inscribirse?\n");
					//Si se añade los examenes resultado a una lista y se tiene que seleccionar de esa lista, se elimina la
					//posibilida de que aun cuando un examen no se muestre, se pueda inscribir en él.
					examenes.mostrarPorCarrera(A31.getCarrera().getId());
					System.out.println("");
					System.out.print("- Ingrese el ID de la mesa de exámenes: ");
					int respuestaA32 = sc.nextInt();
					Examen A32 = examenes.buscarPorId(respuestaA32);
					if (A32 != null) {
						Inscripcion i = new Inscripcion(alumnos.buscarAlumno(respuestaA31), examenes.buscarPorId(respuestaA32));
						System.out.println("");
						boolean A33 = alumnos.verificarInscripcion(respuestaA31, i);
						if (A33 == true) {
							alumnos.inscripcionExamen(i);
						} else {
							System.err.println("[ Ya tienes un exámen que se realiza ese día ]");
						}
					} else {
						System.err.println("\n[ No se encontró el exámen buscado ]");
					}
				} else {
					System.err.println("\n[ No se encontró al alumno buscado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "4":
				System.out.println("———————————");
				System.out.println("[ Certificado de Alumno Regular ]");
				System.out.println("- ¿Para que alumno desea generar el certificado?");
				System.out.println("");
				alumnos.mostrarAlumnos();
				System.out.println("");
				System.out.print("- Ingrese el ID del alumno: ");
				int respuestaA11 = sc.nextInt();
				Alumno A11 = alumnos.buscarAlumno(respuestaA11);
				if (A11 != null) {
					System.out.println("");
					resultados.regular(respuestaA11);
				} else {
					System.err.println("[ No se encontró al alumno seleccionado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				//Si en el año lectivo tenes 3 materias con mas de 6, es regular.
				break;
			case "5":
				System.out.println("———————————");
				System.out.println("[ Dar de Baja en Mesa de Exámenes ]");
				System.out.println("- ¿Qué alumno quiere darse de baja? \n");
				alumnos.mostrarAlumnos();
				System.out.println("");
				System.out.print("- Ingrese el ID del alumno: ");
				int respuestaA41 = sc.nextInt();
				Alumno A41 = alumnos.buscarAlumno(respuestaA41);
				if (A41 != null) {
					System.out.println("");
					System.out.println("- ¿De que mesa desea darse de baja?\n");
					alumnos.mostrarInscripciones(respuestaA41);
					System.out.println("");
					System.out.print("- Ingrese el ID de la mesa de exámen: ");
					int respuestaA42 = sc.nextInt();
					Examen A42 = examenes.buscarPorId(respuestaA42);
					//Cambiar el método para que detecte los examenes del alumno seleccionado
					if (A42 != null) {
						System.out.println("");
						alumnos.darBajaExamen(respuestaA41, respuestaA42);
					} else {
						System.err.println("\n[ No se encontró el exámen buscado ]");
					}
				} else {
					System.err.println("\n[ No se encontró al alumno buscado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "6":
				System.out.println("———————————");
				System.out.println("[ Ver Historial Académico ]");
				System.out.println("- ¿De que alumno desea ver el historial? \n");
				alumnos.mostrarAlumnos();
				System.out.println("");
				System.out.print("- Ingrese el ID del alumno: ");
				int respuestaA51 = sc.nextInt();
				Alumno A51 = alumnos.buscarAlumno(respuestaA51);
				if (A51 != null) {
					System.out.println("");
					System.out.println(resultados.buscarPorId(respuestaA51));
				} else {
					System.err.println("\n[ No se encontró al alumno buscado ]");
				}
				//Si no hay resultados, no muestra nada
				sc.nextLine();
				respuestaSiNo();
				break;
			default:
				break;
			}
			break;
		case "2":
			menuAdministracion();
			switch (opcion) {
			case "1":
				//Evitar que se cree una carrera que ya existe
				System.out.println("———————————");
				System.out.println("[ Creando Carrera ]");
				System.out.print("- Ingrese el nombre de la carrera que desea añadir: ");
				String respuestaB11 = sc.nextLine();
				System.out.println("");
				carreras.crearCarrera(respuestaB11);
				respuestaSiNo();
				break;
			case "2":
				//Evitar que se cree una Materia que ya existe
				System.out.println("———————————");
				System.out.println("[ Creando Materia ]");
				System.out.print("- Ingrese el nombre de la materia: ");
				String respuestaB21 = sc.nextLine();
				System.out.println("");
				System.out.println("- ¿A que carrera pertenece?");
				System.out.println("");
				carreras.mostrarCarreras();
				System.out.println("");
				System.out.print("- Seleccione una de la lista: ");
				System.out.println("");
				int respuestaB22 = sc.nextInt();
				Carrera p = carreras.buscarPorId(respuestaB22);
				if (p != null) {
					if (respuestaB22 != 1) {
						System.out.println("");
						Materia m = new Materia(carreras.buscarPorId(respuestaB22), respuestaB21);
						materias.crearMateria(m);
					} else {
						System.err.println("[ No se ha ingresado una carrera válida ]");
					}
				} else {
					System.out.println("");
					System.err.println("[ No se encontró la carrera buscada ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "3":
				System.out.println("———————————");
				System.out.println("[ Creando Mesa de Examenes ]");
				System.out.println("- ¿Para que materia es la mesa de examenes?");
				System.out.println("");
				materias.mostrarMaterias();
				System.out.println("");
				System.out.print("- Seleccione una de la lista: ");
				int respuestaB31 = sc.nextInt();
				Materia B31 = materias.buscarPorId(respuestaB31);
				if (B31 != null) {
					System.out.println("");
					System.out.print("- Ingrese un número para el mes: ");
					int respuestaB32 = sc.nextInt();
					if (respuestaB32 <= 12) {
						System.out.println("");
						System.out.print("- Ingrese el día: ");
						int respuestaB33 = sc.nextInt();
						if (respuestaB33 <=31) {
							System.out.println("");
							Examen e = new Examen(materias.buscarPorId(respuestaB31), anioLectivo, respuestaB32, respuestaB33);
							examenes.crearMesaDeExamen(e);
						} else {
							System.out.println("");
							System.err.println("[ El día introducido no es válido ]");
						}
						
					} else {
						System.out.println("");
						System.err.println("[ El mes introducido no es válido ]");
					}
				} else {
					System.out.println("");
					System.err.println("[ No se encontró la materia buscada ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "4":
				System.out.println("———————————");
				System.out.println("[ Registrando Nota de Alumno ]");
				System.out.println("- ¿A que alumno pertenece la nota?");
				System.out.println("");
				alumnos.mostrarAlumnos();
				System.out.println("");
				System.out.print("- Seleccione uno de la lista: ");
				int respuestaB41 = sc.nextInt();
				Alumno B41 = alumnos.buscarAlumno(respuestaB41);
				if (B41 != null) {
					//Mostrar los examenes en que está inscripto el alumno
					System.out.println("- ¿En que exámen rindió?");
					System.out.println("");
					examenes.mostrarExamenes();
					System.out.println("");
					System.out.print("- Seleccione una de la lista: ");
					System.out.println("");
					int respuestaB42 = sc.nextInt();
					Examen B42 = examenes.buscarPorId(respuestaB42);
					if (B42 != null) {
						System.out.print("\n- Ingrese la nota: ");
						System.out.println("");
						int respuestaB43 = sc.nextInt();
						if (respuestaB43 <= 10) {
							resultados.registrarResultado(examenes.buscarPorId(respuestaB42), alumnos.buscarAlumno(respuestaB41), respuestaB43);
						} else {
							System.err.println("\n[ La nota ingresada no es válida ]");
						}
					} else {
						System.err.println("\n[ No se encontró el exámen buscado ]");
					}
				} else {
					System.err.println("\n[ No se encontró el alumno buscado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "5":
				System.out.println("———————————");
				System.out.println("[ Resultados de Mesa de Examenes ]");
				System.out.println("- ¿De que mesa desea ver los resultados? \n");
				examenes.mostrarExamenes();
				System.out.println("");
				System.out.print("- Seleccione una de la lista: ");
				int respuestaB51 = sc.nextInt();
				Examen B51 = examenes.buscarPorId(respuestaB51);
				if (B51 != null) {
					System.out.println("");
					resultados.buscarPorMateria(respuestaB51);
				} else {
					System.err.println("[ No se encontró la mesa buscada ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
	public static String respuesta = null;
	public static String opcion;
	public static Scanner sc = new Scanner(System.in);
	public static int anioLectivo = 2022;
	public static void main(String[] args) {
		Connection conn = null;
		
		try {
			Class.forName(Conexion.JDBC_DRIVER);
			System.out.println("[ Conectando con el servidor, por favor espere... ]\n");
			conn = DriverManager.getConnection(Conexion.DB_URL, Conexion.USER, Conexion.PASS);

			System.out.println("[ Inicializando programa... ]\n");
			stmt = conn.createStatement();

			/*Moví el grueso del programa hacia un método en la parte superior para poder usarlo en una recursion y así poder
			 * comprobar que las respuestas ingresadas como "opcion" sean correctas, es decir, no sean un número diferente de 1 o 2,
			 * ni tampoco sea un String que pueda romper el programa.
			 */
			do {
				menuPrincipal();
				if (opcion.equals("1") || opcion.equals("2")) {
					programa();
				} else {
					do {
						System.err.println("\n[ La respuesta ingresada no es correcta, intentelo de nuevo ]\n");
						menuPrincipal();
						programa();
					} while (!opcion.equals("1") || !opcion.equals("2") || respuesta.toUpperCase().equals("SI"));
				}
			} while (respuesta.toUpperCase().equals("SI"));

			
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("\n[ Hubo un error al intentar establecer la conexión. ]\n");
		}
		System.out.println("[ El programa finalizó. ]");
	}
	public static void menuPrincipal() {
		System.out.println("————————————");
		System.out.println("[ Menú Principal ]");
		System.out.println("1. Alumno");
		System.out.println("2. Administración");
		System.out.println("");
		System.out.print("- Seleccione una opción: ");
		opcion = sc.nextLine();
	}
	public static void menuAlumno() {
		System.out.println("————————————");
		System.out.println("[ Menú de Alumno ]");
		System.out.println("1. Crear Nuevo Alumno");
		System.out.println("2. Inscribirse a carrera.");
		System.out.println("3. Inscribirse a mesa de exámen.");
		System.out.println("4. Generar certificado de alumno regular.");
		System.out.println("5. Darse de baja de una mesa de exámen.");
		System.out.println("6. Ver historial académico.");
		System.out.println("");
		System.out.print("- Seleccione una opción: ");
		opcion = sc.nextLine();
	}
	public static void menuAdministracion() {
		System.out.println("————————————");
		System.out.println("[ Menú de Administración ]");
		System.out.println("1. Crear nueva carrera.");
		System.out.println("2. Crear nueva materia.");
		System.out.println("3. Crear mesa de exámenes.");
		System.out.println("4. Registrar nota");
		System.out.println("5. Ver resultados de mesa de exámenes.");
		System.out.println("");
		System.out.print("- Seleccione una opción: ");
		opcion = sc.nextLine();
	}
	public static void respuestaSiNo() {
		System.out.println("\n- ¿Desea continuar? \n[ SI ] o [ NO ]");
		respuesta = sc.nextLine();
		if (respuesta.toUpperCase().equals("SI") || respuesta.toUpperCase().equals("NO")) {
		} else {
			System.err.println("\n[ Respuesta no válida, intente de nuevo ]");
			respuestaSiNo();
		}
	}
}
