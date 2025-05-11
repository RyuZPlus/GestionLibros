package modelo;

public class Libro {
	private String titulo;
	private String autor;
	private double precio;
	private String genero;
	private int stock;
	
	public Libro(String titulo, String autor, double precio, String genero, int stock) {
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.genero = genero;
		this.stock = stock;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public double getPrecio() {
		return precio;
	}

	public String getGenero() {
		return genero;
	}

	public int getStock() {
		return stock;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "{titulo: " + titulo + ", autor: " + autor + ", precio: " + precio + ", genero: " + genero
				+ ", stock: " + stock + "}";
	}
	
	public static Libro fromCSV(String linea) {
		String[] elementos = linea.split("\\|");
		Libro libro = new Libro(elementos[0], elementos[1], Double.parseDouble(elementos[2]), 
								elementos[3], Integer.parseInt(elementos[4]));
		return libro;
	}
	
	public String toCSV() {
		String linea = titulo + "|" + autor + "|" + precio + "|" + genero + "|" + stock;
		return linea;
	}
}
