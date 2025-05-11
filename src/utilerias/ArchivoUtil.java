package utilerias;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import modelo.Libro;
import modelo.Venta;

public class ArchivoUtil {
	public static List<Libro> cargarLibros(String archivo) {
		List<Libro> libros = new ArrayList<>();
		
		if(!Files.exists(Paths.get(archivo)))
			return libros;
		
		try {
			libros = Files.lines(Paths.get(archivo))
					//.skip(1)
					//.peek(System.out::println)
					.map(linea -> Libro.fromCSV(linea))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return libros;
	}

	public static List<Venta> cargarVentas(String archivo, List<Libro> inventario) {
		List<Venta> ventas = new ArrayList<>();
		
		if(!Files.exists(Paths.get(archivo)))
			return ventas;
		
		try {
			ventas = Files.lines(Paths.get(archivo))
					.skip(1)
					.map(linea -> Venta.fromCSV(linea, inventario))
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ventas;
	}

	public static void guardarLibros(String archivo, List<Libro> inventario) {
		try (PrintWriter pw = new PrintWriter(archivo)) {
			inventario.forEach(libro -> pw.println(libro.toCSV()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void guardarVentas(String archivo, List<Venta> ventas) {
		try (PrintWriter pw = new PrintWriter(archivo)) {
			pw.println("libro|cantidad|fecha"); // encabezado
			ventas.forEach(venta -> pw.println(venta.toCSV()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
