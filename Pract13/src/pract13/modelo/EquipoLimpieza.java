package pract13.modelo;

import java.util.Collection;
import java.util.HashMap;

/**
 * Equipo de una empresa de limpieza.
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 * @version nov-21
 */
public class EquipoLimpieza implements Comparable<EquipoLimpieza>{
	private final String id;
	private int horasTrabajadas = 0;
	private boolean estaDisponible = true;
	
	private HashMap<String, Cliente> clientesTrabajados = new HashMap<String, Cliente>();

	/**
	 * Crea un equipo con el identificador indicado.
	 * @param id identificador del equipo.
	 */
	public EquipoLimpieza(String id) {
		this.id = id;
	}

	/**
	 * Retorna el identificador del equipo.
	 * @return el identificador del equipo.
	 */
	public String id() {
		return id;
	}

	/**
	 * Retorna las horas trabajadas por el equipo.
	 * @return las horas trabajadas por el equipo.
	 */
	public int horasTrabajadas() {
		return horasTrabajadas;
	}

	/**
	 * Indica si el equipo esta disponible.
	 * @return si el equipo esta disponible.
	 */
	public boolean estaDisponible() {
		return estaDisponible;
	}

	/**
	 * Registra que el equipo ha comenzado un trabajo para
	 * el cliente indicado.
	 * @param cliente cliente para el que el equipo comienza
	 * el trabajo.
	 */
	public void empiezaTrabajo(Cliente cliente) {
		clientesTrabajados.put(cliente.nombre(), cliente);
		cliente.encargaLimpieza();
		estaDisponible = false;
	}

	/**
	 * Registra que el equipo ha finalizado un trabajo con
	 * la duracion indicada.
	 * @param horasTrabajadas duracion en horas del trabajo
	 * finalizado.
	 */
	public void finalizaTrabajo(int horasTrabajadas) {
		estaDisponible = true;
		this.horasTrabajadas += horasTrabajadas;
	}

	/**
	 * Retorna los clientes para los que ha trabajado el equipo.
	 * @return los clientes para los que ha trabajado el equipo.
	 */
	public Collection<Cliente> clientes() {
		return clientesTrabajados.values();
	}

	@Override
	public String toString() {
		return "(" + id + ", h=" + horasTrabajadas + ", disp="
				+ estaDisponible + ")";
	}
	
	
	@Override
	public int compareTo(EquipoLimpieza e) {
		return Integer.compare(this.horasTrabajadas, e.horasTrabajadas);
	}

}
