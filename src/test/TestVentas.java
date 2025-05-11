package test;

import java.util.List;
import java.util.Scanner;

import modelo.Libro;
import modelo.Venta;
import utilerias.ArchivoUtil;
import utilerias.GestionInventario;
import utilerias.GestionVentas;
import utilerias.Reportes;

public class TestVentas {
	private static final String ARCHIVO_LIBROS = "libros.csv";
	private static final String ARCHIVO_VENTAS = "ventas.csv";
	
	public static void main(String[] args) {
		List<Libro> inventario = ArchivoUtil.cargarLibros(ARCHIVO_LIBROS);
		List<Venta> ventas = ArchivoUtil.cargarVentas(ARCHIVO_VENTAS, inventario);
		//inventario.forEach(System.out::println);
		//System.out.println("---------Ventas----------");
		//ventas.forEach(System.out::println);
		
		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {
			System.out.println("======MENU PRINCIPAL======");
			System.out.println("1. Registrar nueva venta");
			System.out.println("2. Ver inventario");
			System.out.println("3. Reportes");
			System.out.println("4. Ver ventas");
			System.out.println("5. Guardar y salir");
			System.out.println("Seleccione una opción: ");
			int opcion = sc.nextInt();
			switch (opcion) {
			case 1 -> GestionVentas.registrarVenta(inventario, ventas, sc);
			case 2 -> GestionInventario.menuInventario(inventario, sc);
			case 3 -> Reportes.mostrar(ventas, sc);
			case 4 -> {
				System.out.println("-------------Ventas----------------");
				ventas.forEach(System.out::println);
			}
			case 5 -> {
				System.out.println("Datos guardados correctamente, hasta pronto");
				ArchivoUtil.guardarLibros(ARCHIVO_LIBROS, inventario);
				ArchivoUtil.guardarVentas(ARCHIVO_VENTAS, ventas);
				salir = true;
			}
			default -> System.out.println("Opción incorrecta, seleccione del 1 al 5");
			}
		}
	}
}
