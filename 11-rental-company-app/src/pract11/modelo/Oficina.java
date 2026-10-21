package pract11.modelo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Oficina de una empresa de alquiler de vehiculos.
 * 
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 * @version nov-2019
 */
public class Oficina {
	// nombre de la oficina
	private final String nombre;
	private Queue<Vehiculo> coches = new LinkedList<Vehiculo>();

	/**
	 * Construye una oficina con el nombre indicado.
	 * @param nombre nombre de la oficina
	 */
	public Oficina(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Anhade un vehiculo disponible para alquiler a la oficina.
	 * @param v nuevo vehiculo disponible para alquiler
	 */
	public void anhadeVehiculoDisponible(Vehiculo v) {
		coches.add(v);
	}

	/**
	 * Busca un vehiculo con las caracteristicas indicadas y le elimina de la lista
	 * de vehiculos disponibles para alquiler en la oficina.
	 * @param numPlazas numero de plazas del vehiculo deseado
	 * @param tipoCombustible tipo de combustible del vehiculo deseado
	 * @return el vehiculo que lleva mas tiempo disponible con las caracteristicas indicadas
	 * o null si no existe ningun vehiculo con esas caracterisiticas
	 */
	public Vehiculo alquila(int numPlazas, Vehiculo.TipoCombustible tipoCombustible) {		
		for (Vehiculo c: coches) {
			if (c.tipoCombustible().equals(tipoCombustible) && c.numPlazas() == numPlazas) {
				coches.remove(c);
				c.alquila();	
				return c;
			}
		}
		return null;
	}

	/**
	 * Retorna el nombre de la oficina.
	 * @return el nombre de la oficina
	 */
	public String nombre() {
		return nombre;
	}

	/**
	 * Retorna el numero de vehiculos disponibles para alquiler en la oficina.
	 * @return numero de vehiculos disponibles para alquiler en la oficina
	 */
	public int numVehiculosDisponibles() {
		return coches.size();
	}

	@Override
	public String toString() {
		return "(nombre=" + nombre + ")";
	}

}
