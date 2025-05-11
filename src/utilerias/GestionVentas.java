package utilerias;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import modelo.Libro;
import modelo.Venta;

public class GestionVentas {
	public static void registrarVenta(List<Libro> inventario, List<Venta> ventas, Scanner sc) {
		System.out.println("\n--- Registrar Nueva Venta ---");
		
		if (inventario.isEmpty()) {
			System.out.println("No hay libros disponibles para vender.");
			return;
		}

		// Mostrar libros con índices
		for (int i = 0; i < inventario.size(); i++) {
			System.out.println((i + 1) + ". " + inventario.get(i));
		}

		System.out.print("Seleccione el número del libro: ");
		int opcion = sc.nextInt();
		sc.nextLine(); // limpiar buffer

		if (opcion < 1 || opcion > inventario.size()) {
			System.out.println("Selección inválida.");
			return;
		}

		Libro libroSeleccionado = inventario.get(opcion - 1);

		System.out.print("Cantidad a vender: ");
		int cantidad = sc.nextInt();
		sc.nextLine(); // limpiar buffer

		if (cantidad <= 0) {
			System.out.println("Cantidad inválida.");
			return;
		}

		if (cantidad > libroSeleccionado.getStock()) {
			System.out.println("No hay suficiente stock.");
			return;
		}
		
		LocalDate hoy = LocalDate.now();

		// Buscar si ya existe una venta del mismo libro hoy
		Venta ventaExistente = null;
		for (Venta v : ventas) {
			if (v.getLibro().equals(libroSeleccionado) && v.getFecha().equals(hoy)) {
				ventaExistente = v;
				break;
			}
		}

		if (ventaExistente != null) {
			// Si la venta existente de ese libro no es nulo (ya existe una venta), actualizar la cantidad de la venta existente
			ventaExistente.setCantidadVendida(ventaExistente.getCantidadVendida() + cantidad);
			System.out.println("Venta actualizada exitosamente.");
		} else {
			// Si no existe una venta previa del mismo libro en el mismo día, registrar una nueva venta
			Venta nuevaVenta = new Venta(libroSeleccionado, cantidad, hoy);
			ventas.add(nuevaVenta);
			System.out.println("Venta registrada exitosamente.");
		}

		// Actualizar el inventario
		libroSeleccionado.setStock(libroSeleccionado.getStock() - cantidad);
	}
}
