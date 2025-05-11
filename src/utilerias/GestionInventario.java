package utilerias;

import java.util.List;
import java.util.Scanner;
import modelo.Libro;

public class GestionInventario {
	public static void menuInventario(List<Libro> inventario, Scanner sc) {
		boolean volver = false;
		while (!volver) {
			System.out.println("\n------GESTIÓN DE INVENTARIO------");
			System.out.println("1. Ver inventario");
			System.out.println("2. Agregar nuevo libro");
			System.out.println("3. Volver al menú principal");
			System.out.print("Seleccione una opción: ");
			int opcion = sc.nextInt();
			sc.nextLine(); // limpiar buffer

			switch (opcion) {
			case 1 -> verInventario(inventario);
			case 2 -> agregarLibro(inventario, sc);
			case 3 -> volver = true;
			default -> System.out.println("Opción no válida.");
			}
		}
	}

	private static void verInventario(List<Libro> inventario) {
		System.out.println("\n---------INVENTARIO ACTUAL---------");
		if (inventario.isEmpty()) {
			System.out.println("No hay libros en el inventario.");
		} else {
			inventario.forEach(System.out::println);
		}
	}

	private static void agregarLibro(List<Libro> inventario, Scanner sc) {
		System.out.println("\n--- Agregar nuevo libro ---");
		//título
		System.out.print("Título: ");
		String titulo = sc.nextLine();
		//Autor
		System.out.print("Autor: ");
		String autor = sc.nextLine();
		//Precio
		System.out.print("Precio: ");
		double precio = sc.nextDouble();
		sc.nextLine();
		//Género
		System.out.print("Género: ");
		String genero = sc.nextLine();
		//Stock
		System.out.print("Stock: ");
		int stock = sc.nextInt();
		sc.nextLine(); // limpiar buffer

		Libro nuevo = new Libro(titulo, autor, precio, genero, stock);
		inventario.add(nuevo);
		System.out.println("Libro agregado con éxito.");
	}
}
