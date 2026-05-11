package pract12.p2_candidatos.modelo;

import java.util.HashMap;
import java.util.Map;

import pract12.p1_cola_prio_monticulo.ColaPrioMonticulo;
import pract12.p1_cola_prio_monticulo.IColaPrioridad;

/**
 * Candidatos para un puesto de trabajo.
 *
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 * @version dic-2020
 */
public class CandidatosPuesto {

	private static final int MAX = 30;

	private IColaPrioridad<Candidato> candidatosPuesto;
	
	private Map<String, Candidato> candidatos = new HashMap<String, Candidato>();
	
	private int maxNumcandidatos;

	/**
	 * Lanzada si ya existe otro candidato con el nombre indicado.
	 */
	@SuppressWarnings("serial")
	public static class NombreCandidatoYaExistente extends RuntimeException {
	}
	
	/**
	 * Lanzada si se ha alcanzado el numero maximo de candidatos para el puesto.
	 */
	@SuppressWarnings("serial")
	public static class AlcanzadoMaximoNumCandidatos extends RuntimeException {
	}
	
	/**
	 * Construye el registro de los candidatos para un puesto de trabajo.
	 * @param maxNumCandidatos numero maximo de candidatos para el puesto.
	 */
	public CandidatosPuesto(int maxNumCandidatos) {
		if (maxNumCandidatos > MAX) {
			maxNumCandidatos = MAX;
		}
		candidatosPuesto = new ColaPrioMonticulo<Candidato>(maxNumCandidatos);
		this.maxNumcandidatos = maxNumCandidatos;
	}

	/**
	 * Anhade un candidato para el puesto.
	 * @param candidato candidato a anhadir.
	 * @throws NombreCandidatoYaExistente si ya existe otro candidato con el
	 * nombre indicado.
	 * @throws AlcanzadoMaximoNumCandidatos si se ha alcanzado el numero maximo
	 * de candidatos para el puesto.
	 */
	public void anhadeCandidato(Candidato candidato) 
			throws NombreCandidatoYaExistente, AlcanzadoMaximoNumCandidatos {
		if (candidatosPuesto.tamanho() >= maxNumcandidatos) {
			throw new AlcanzadoMaximoNumCandidatos();
		}
		
		if (candidatos.get(candidato.nombre()) != null) {
			throw new NombreCandidatoYaExistente();
		}
		
		candidatos.put(candidato.nombre(), candidato);
		candidatosPuesto.encolaConPrioridad(candidato);
	}

	/**
	 * Retorna y elimina el candidato mejor valorado.
	 * @return el candidato mejor valorado o null si no hay ningun
	 * candidato en la cola.
	 */
	public Candidato mejorValorado() {
		Candidato mejor = candidatosPuesto.desencolaMasPrioritario();
		
		if (mejor != null) {
			candidatos.remove(mejor.nombre());
		}
		
		return mejor;
	}

}
