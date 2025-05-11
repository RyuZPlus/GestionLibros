package modelo;

import java.time.LocalDate;
import java.util.List;

public class Venta {
	private Libro libro;
	private int cantidadVendida;
	private LocalDate fecha;
	
	public Venta(Libro libro, int cantidadVendida, LocalDate fecha) {
		this.libro = libro;
		this.cantidadVendida = cantidadVendida;
		this.fecha = fecha;
	}
	public Libro getLibro() {
		return libro;
	}
	public int getCantidadVendida() {
		return cantidadVendida;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public void setCantidadVendida(int cantidadVendida) {
		this.cantidadVendida = cantidadVendida;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		return "{Titulo Libro: " + libro + ", cantidadVendida: " + cantidadVendida + ", fecha: " + fecha + "}";
	}
	
	public String toCSV() {
		String linea = libro.getTitulo() + "|" + cantidadVendida + "|" + fecha;
		return linea;
	}
	
	public static Venta fromCSV(String linea, List<Libro> inventario) {
		String[] elementos = linea.split("\\|");
		String titulo = elementos[0];
		int cantidad = Integer.parseInt(elementos[1]);
		LocalDate fecha = LocalDate.parse(elementos[2]);
		Libro libro = inventario.stream()
				.filter(l -> l.getTitulo().equals(titulo))
				//.filter(l -> l.getTitulo().trim().equalsIgnoreCase(titulo.trim()))
				.findFirst()
				.orElse(null);
		if(libro == null)
			return null;
		Venta venta = new Venta(libro, cantidad, fecha);
		return venta;
	}
}
