package pract14.c2_lista_con_elementos_comunes;

/**
 * Clase producto.
 * 
 * @author Estructuras de Datos (UC) y <TODO: nombre alumno>
 * @version ene-2021
 */
public class Producto {
	private final int codigo;
	private final String descripcion;
	
	/**
	 * Construye un producto con los valores pasados como parametros.
	 * @param codigo codigo del producto
	 * @param descripcion descripcion del producto
	 */
	public Producto(int codigo, String descripcion) {
		this.codigo = codigo;
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el codigo del producto.
	 * @return el codigo del producto.
	 */
	public int codigo() {
		return codigo;
	}

	/**
	 * Retorna la descripcion del producto.
	 * @return la descripcion del producto.
	 */
	public String descripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return "(" + codigo + ")";
	}

	// TODO: otros metodos necesarios para usar esta clase en el TAD elegido.
		
	public boolean equals(Object o) {
		return codigo == (((Producto) o) .codigo);
	}
	
	public int hashCode (int cod) {
		return Integer.hashCode(cod);
	}
}
