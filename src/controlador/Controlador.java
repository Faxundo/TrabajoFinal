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
		InscripcionInterfaz inscripciones = new InscripcionImplementacion(stmt);
		
		//Programa
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
				//Genera 6 números aleatorios entre el 0 y el 9 para crear la libreta universitaria del alumno.
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
				System.out.println("- ¿Quien desea inscribirse a una carrera? \n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaA21 = sc.nextInt();
				Alumno A21 = alumnos.buscarAlumno(respuestaA21);
				if (A21 != null) {
					System.out.println("\n- ¿A que carrera desea inscribirse? \n");
					carreras.mostrarCarreras();
					System.out.print("\n- Ingrese el ID de la carrera: ");
					int respuestaA22 = sc.nextInt();
					Carrera A22 = carreras.buscarCarreraPorId(respuestaA22);
					if (A22 != null) {
						System.out.println("");
						carreras.inscripcionCarrera(alumnos.buscarAlumno(respuestaA21), carreras.buscarCarreraPorId(respuestaA22));;
					} else {
						System.err.println("\n[ No se encontró la carrera buscada ]");
					}
				} else {
					System.err.println("\n[ No se encontró al alumno buscado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "3": 
				System.out.println("———————————");
				System.out.println("[ Inscribir a Mesa de Exámenes ]");
				System.out.println("- ¿Quien desea inscribirse a una mesa de exámenes?\n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaA31 = sc.nextInt();
				Alumno A31 = alumnos.buscarAlumno(respuestaA31);
				if (A31 != null) {
					System.out.println("\n- ¿A que exámen desea inscribirse?\n");
					examenes.mostrarExamenesPorCarrera(A31.getCarrera().getId());
					System.out.print("\n- Ingrese el ID de la mesa de exámenes: ");
					int respuestaA32 = sc.nextInt();
					Examen A32 = examenes.buscarExamenPorId(respuestaA32);
					if (A32 != null) {
						Inscripcion i = new Inscripcion(alumnos.buscarAlumno(respuestaA31), examenes.buscarExamenPorId(respuestaA32));
						System.out.println("");
						boolean A33 = inscripciones.verificarInscripcion(respuestaA31, i);
						//Acá se usa este método para comprar las fechas del exámen seleccionado y los que el alumno ya tiene
						//para ver si no tiene ya un exámen ese mismo día.
						if (A33 == true) {
								inscripciones.inscripcionExamen(i);
						} else {
							System.err.println("\n[ Ya tienes un exámen que se realiza ese día ]");
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
				//Un alumno es regular cuando tiene al menos 3 materias con una nota de 6.
				System.out.println("———————————");
				System.out.println("[ Certificado de Alumno Regular ]");
				System.out.println("- ¿Para que alumno desea generar el certificado? \n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaA11 = sc.nextInt();
				Alumno A11 = alumnos.buscarAlumno(respuestaA11);
				if (A11 != null) {
					System.out.println("");
					resultados.regular(respuestaA11);
				} else {
					System.err.println("\n[ No se encontró al alumno seleccionado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "5":
				System.out.println("———————————");
				System.out.println("[ Dar de Baja en Mesa de Exámenes ]");
				System.out.println("- ¿Qué alumno quiere darse de baja? \n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaA41 = sc.nextInt();
				Alumno A41 = alumnos.buscarAlumno(respuestaA41);
				if (A41 != null) {
					System.out.println("\n- ¿De que mesa desea darse de baja?\n");
					inscripciones.mostrarInscripciones(respuestaA41);
					System.out.print("\n- Ingrese el ID de la mesa de exámen: ");
					int respuestaA42 = sc.nextInt();
					Examen A42 = examenes.buscarExamenPorId(respuestaA42);
					if (A42 != null) {
						System.out.println("");
						examenes.darBajaExamen(respuestaA41, respuestaA42);
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
					resultados.buscarResultadoPorId(respuestaA51);
				} else {
					System.err.println("\n[ No se encontró al alumno buscado ]");
				}
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
				System.out.println("———————————");
				System.out.println("[ Creando Carrera ]");
				System.out.print("- Ingrese el nombre de la carrera que desea añadir: ");
				String respuestaB11 = sc.nextLine();
				carreras.crearCarrera(respuestaB11);
				respuestaSiNo();
				break;
			case "2":
				System.out.println("———————————");
				System.out.println("[ Creando Materia ]");
				System.out.print("- Ingrese el nombre de la materia: ");
				String respuestaB21 = sc.nextLine();
				System.out.println("\n- ¿A que carrera pertenece? \n");
				carreras.mostrarCarreras();
				System.out.print("\n- Ingrese el ID de la carrera: ");
				System.out.println("");
				int respuestaB22 = sc.nextInt();
				Carrera p = carreras.buscarCarreraPorId(respuestaB22);
				if (p != null) {
						System.out.println("");
						Materia m = new Materia(carreras.buscarCarreraPorId(respuestaB22), respuestaB21);
						materias.crearMateria(m);
				} else {
					System.err.println("\n[ No se encontró la carrera buscada ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "3":
				System.out.println("———————————");
				System.out.println("[ Creando Mesa de Examenes ]");
				System.out.println("- ¿Para que materia es la mesa de examenes? \n");
				materias.mostrarMaterias();
				System.out.print("\n- Ingrese el ID de la materia: ");
				int respuestaB31 = sc.nextInt();
				Materia B31 = materias.buscarMateriaPorId(respuestaB31);
				if (B31 != null) {
					System.out.print("\n- Ingrese un número para el mes: ");
					int respuestaB32 = sc.nextInt();
					//Comprueba que ingrese uno de los 12 meses válidos.
					if (respuestaB32 <= 12) {
						System.out.print("\n- Ingrese el día: ");
						int respuestaB33 = sc.nextInt();
						//Puse la máxima cantidad de días que puede tener un mes, así compara si el número ingresado es igual o
						//inferior a esa cantidad.
						if (respuestaB33 <=31) {
							System.out.println("");
							Examen e = new Examen(materias.buscarMateriaPorId(respuestaB31), anioLectivo, respuestaB32, respuestaB33);
							examenes.crearMesaDeExamen(e);
						} else {
							System.err.println("\n[ El día introducido no es válido ]");
						}
						
					} else {
						System.err.println("\n[ El mes introducido no es válido ]");
					}
				} else {
					System.err.println("\n[ No se encontró la materia buscada ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "4":
				//Para registrar una nota, se necesita que el alumno esté inscripto en una mesa de exámenes.
				System.out.println("———————————");
				System.out.println("[ Registrando Nota de Alumno ]");
				System.out.println("- ¿A que alumno pertenece la nota? \n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaB41 = sc.nextInt();
				Alumno B41 = alumnos.buscarAlumno(respuestaB41);
				if (B41 != null) {
					System.out.println("- ¿En que exámen rindió? \n");
					examenes.mostrarExamenes();;
					System.out.print("\n- Ingrese el ID del exámen: ");
					System.out.println("");
					int respuestaB42 = sc.nextInt();
					Examen B42 = examenes.buscarExamenPorId(respuestaB42);
					if (B42 != null) {
						System.out.print("\n- Ingrese la nota: ");
						System.out.println("");
						int respuestaB43 = sc.nextInt();
						//Comprueba que la nota ingresada sea igual o menor a 10.
						if (respuestaB43 <= 10) {
							resultados.registrarResultado(examenes.buscarExamenPorId(respuestaB42), alumnos.buscarAlumno(respuestaB41), respuestaB43);
							inscripciones.eliminarInscripcion(respuestaB41, respuestaB42);
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
				System.out.print("\n- Ingrese el ID de la mesa de exámenes: ");
				int respuestaB51 = sc.nextInt();
				Examen B51 = examenes.buscarExamenPorId(respuestaB51);
				if (B51 != null) {
					System.out.println("");
					resultados.buscarResultadoPorMateria(respuestaB51);
				} else {
					System.err.println("\n[ No se encontró la mesa buscada ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "6":
				System.out.println("———————————");
				System.out.println("[ Eliminar Nota ]");
				System.out.println("- ¿A que alumno pertenece la nota? \n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaB61 = sc.nextInt();
				Alumno B62 = alumnos.buscarAlumno(respuestaB61);
				if (B62 != null) {
					System.out.println("\n- ¿De que exámen desea borrar la nota?\n");
					System.out.println(resultados.buscarResultadoPorId(respuestaB61));
					System.out.print("\n- Ingrese el ID de la mesa de exámenes: ");
					int respuestaB63 = sc.nextInt();
					resultados.borrarResultado(respuestaB61, respuestaB63);
					sc.nextLine();
					respuestaSiNo();
				} else {
					System.err.println("\n[ No se encontró el alumno buscado ]");
				}
				break;
			case "7":
				System.out.println("———————————");
				System.out.println("[ Actualizar Datos de Alumno ]");
				System.out.println("- ¿Los datos de que alumno desea actualizar? \n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaA71 = sc.nextInt();
				Alumno A72 = alumnos.buscarAlumno(respuestaA71);
				if (A72 != null) {
					sc.nextLine();
					System.out.print("\n- Ingrese un nuevo nombre para el alumno: ");
					String respuestaA73 = sc.nextLine();
					System.out.print("\n- Ingrese un nuevo DNI para el alumno: ");
					String respuestaA74 = sc.nextLine();
					alumnos.actualizarAlumno(respuestaA73, respuestaA74, A72.getId());
					respuestaSiNo();
				} else {
					System.err.println("\n[ No se encontró el alumno buscado ]");
				}
			case "8":
				System.out.println("———————————");
				System.out.println("[ Actualizar Nombre de Carerra ]");
				System.out.println("- ¿El nombre de que carrera desea actualizar? \n");
				carreras.mostrarCarreras();
				System.out.print("\n- Ingrese el ID de la carrera: ");
				int respuestaB81 = sc.nextInt();
				if (carreras.buscarCarreraPorId(respuestaB81) != null) {
					System.out.print("\n- Ingrese el nuevo nombre para esta carrera: ");
					sc.nextLine();
					String respuestaB82 = sc.nextLine();
					carreras.actualizarCarrera(respuestaB82, respuestaB81);
				} else {
					System.err.println("[ No se ha encontrado la carrera seleccionada ]");
					sc.nextLine();
				}
				respuestaSiNo();
				break;
			case "9":
				System.out.println("———————————");
				System.out.println("[ Actualizar Fecha de Mesa de Examenes ]");
				System.out.println("- ¿Qué mesa de examenes desea actualizar? \n");
				examenes.mostrarExamenes();
				int respuestaB91 = sc.nextInt();
				if (examenes.buscarExamenPorId(respuestaB91) != null) {
					System.out.print("\n- Ingrese un número para el mes: ");
					int respuestaB92 = sc.nextInt();
					if (respuestaB92 <= 12) {
						System.out.print("\n- Ingrese el día: ");
						int respuestaB93 = sc.nextInt();
						if (respuestaB93 <=31) {
							examenes.actualizarExamen(respuestaB92, respuestaB93, respuestaB91);;
						} else {
							System.err.println("\n[ El día introducido no es válido ]");
						}
					} else {
						System.err.println("\n[ El mes introducido no es válido ]");
					}
				} else {
					System.err.println("[ No se ha encontrado el exámen buscado ]");
				}
				sc.nextLine();
				respuestaSiNo();
				break;
			case "10":
				System.out.println("———————————");
				System.out.println("[ Actualizar Nota de Alumno ]");
				System.out.println("- ¿De que alumno desea actualizar la nota? \n");
				alumnos.mostrarAlumnos();
				System.out.print("\n- Ingrese el ID del alumno: ");
				int respuestaB101 = sc.nextInt();
				if (alumnos.buscarAlumno(respuestaB101) != null) {
					System.out.println("- ¿De que exámen del alumno desea actualizar la nota? \n");
					if (resultados.buscarResultadoPorId(respuestaB101) != null) {
						System.out.print("\n- Ingrese el ID del examen: ");
						int respuestaB102 = sc.nextInt();
						if (examenes.buscarExamenPorId(respuestaB102) != null) {
							System.out.print("\n- Ingrese la nueva nota: ");
							int respuestaB103 = sc.nextInt();
							if (respuestaB103 <= 10) {
								resultados.actualizarResultado(respuestaB103, respuestaB101, respuestaB102);
							} else {
								System.err.println("\n[ La nota ingresada no es válida ]");
							}
						} else {
							System.err.println("\n[ No se ha encontrado el exámen buscado ]");
						}
					}
				} else {
					System.err.println("\n[ No se ha encontrado al alumno buscado ]");
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
			//Conexión
			Class.forName(Conexion.JDBC_DRIVER);
			System.out.println("[ Conectando con el servidor, por favor espere... ]\n");
			conn = DriverManager.getConnection(Conexion.DB_URL, Conexion.USER, Conexion.PASS);

		    //Crear la declaración
			System.out.println("[ Inicializando programa... ]\n");
			stmt = conn.createStatement();

			/*Moví el grueso del programa hacia un método en la parte superior para poder usarlo en una recursion y así poder
			 * comprobar que las respuestas ingresadas como "opcion" sean correctas, es decir, no sean un número diferente de 1 o 2,
			 * ni tampoco sea una cadena que pueda romper el programa.
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
					//Acá se crea el bucle hasta que se ingrese una opción correcta = "NO", ya que si se pone
					//"si" se seguiría recorriendo los menús, así que la forma de terminar el programar es no continuar.
					} while (!opcion.equals("1") || !opcion.equals("2") || respuesta.toUpperCase().equals("SI"));
				}
			} while (respuesta.toUpperCase().equals("SI"));

			sc.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.err.println("\n[ Hubo un error al intentar establecer la conexión. ]\n");
		}
		System.out.println("\n[ El programa finalizó. ]");
	}
	
	//Menús
	public static void menuPrincipal() {
		System.out.println(" —————————————————————————————");
		System.out.println("——— Facultad de Humanidades ———");
		System.out.println(" —————————————————————————————");
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
		System.out.println("6. Eliminar nota");
		System.out.println("7. Actualizar datos de alumno");
		System.out.println("8. Actualizar nombre de carrera");
		System.out.println("9. Actualizar fecha de mesa de examenes");
		System.out.println("10. Actualizar resultados de alumno");
		System.out.println("");
		System.out.print("- Seleccione una opción: ");
		opcion = sc.nextLine();
	}
	//Método para hacer transitable los menú del programa.
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
