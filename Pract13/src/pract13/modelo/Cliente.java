package pract13.modelo;

/**
 * Cliente de una empresa de limpieza.
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 */
public class Cliente implements Comparable<Cliente>{
	private final String nombre;
	private final String direccion;
	private int numLimpiezasEncargadas = 0;
	
	/**
	 * Crea un cliente con el nombre y la direccion indicados.
	 * @param nombre nombre del cliente.
	 * @param direccion direccion del cliente.
	 */
	public Cliente(String nombre, String direccion) {
		this.nombre = nombre;
		this.direccion = direccion;
	}

	/**
	 * Retorna el nombre del cliente.
	 * @return el nombre del cliente.
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna la direccion del cliente.
	 * @return la direccion del cliente.
	 */
	public String direccion() {
		return direccion;
	}
	
	/**
	 * Registra que el cliente ha encargado un servicio de limpieza.
	 */
	public void encargaLimpieza() {
		numLimpiezasEncargadas++;
	}

	@Override
	public String toString() {
		return "(" + nombre + ",n=" + numLimpiezasEncargadas + ")";
	}
	
	
	/**
	 * Compara este objeto Cliente con el objeto Cliente especificado para el orden.
	 * Retorna un número entero negativo, cero o un número entero positivo según este objeto
	 * tenga menos, igual o más numLimpiezasEncargadas que el objeto especificado.
	 *
	 * @param c el objeto Cliente a comparar.
	 * @return un número entero negativo, cero o un número entero positivo según este objeto
	 *         tenga menos, igual o más numLimpiezasEncargadas que el objeto especificado.
	 */
	@Override
	public int compareTo(Cliente c) {
		return Integer.compare(this.numLimpiezasEncargadas, c.numLimpiezasEncargadas);
	}

}
