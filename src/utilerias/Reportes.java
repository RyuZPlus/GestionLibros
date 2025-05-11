package utilerias;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import modelo.Venta;

public class Reportes {
	public static void mostrar(List<Venta> ventas, Scanner sc) {
		System.out.println("-----Menú de Reportes-----");
		boolean volver = false;
		while (!volver) {
			System.out.println("1. Libro más vendido");
			System.out.println("2. Ventas por fecha específica");
			System.out.println("3. Total de libros vendidos");
			System.out.println("4. Volver");
			System.out.print("Seleccione una opción: ");
			int opReporte = sc.nextInt();
			sc.nextLine(); // limpiar buffer

			switch (opReporte) {
			case 1 -> mostrarMasVendido(ventas);
			case 2 -> mostrarVentasPorFecha(ventas, sc);
			case 3 -> mostrarTotalVendidos(ventas);
			case 4 -> volver = true;
			default -> System.out.println("Opción no válida.");
			}
		}
	}

	private static void mostrarMasVendido(List<Venta> ventas) {
		Venta masVendida = ventas.stream()
			.max((v1, v2) -> Integer.compare(v1.getCantidadVendida(), v2.getCantidadVendida()))
			.orElse(null);
		if (masVendida != null)
			System.out.println("Libro más vendido: " + masVendida.getLibro().getTitulo() +
				" (" + masVendida.getCantidadVendida() + " unidades)");
		else
			System.out.println("No hay ventas registradas.");
	}

	private static void mostrarVentasPorFecha(List<Venta> ventas, Scanner sc) {
		System.out.print("Ingrese fecha (AAAA-MM-DD): ");
		String fechaStr = sc.nextLine();
		List<Venta> porFecha = ventas.stream()
			.filter(v -> v.getFecha().toString().equals(fechaStr))
			.collect(Collectors.toList());
		if (porFecha.isEmpty())
			System.out.println("No hay ventas para esa fecha.");
		else
			porFecha.forEach(System.out::println);
	}

	private static void mostrarTotalVendidos(List<Venta> ventas) {
		int total = ventas.stream()
			.mapToInt(Venta::getCantidadVendida)
			.sum();
		System.out.println("Total de libros vendidos: " + total);
	}
}
