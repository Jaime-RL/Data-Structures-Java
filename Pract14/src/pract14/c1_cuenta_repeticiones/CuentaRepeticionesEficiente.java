package pract14.c1_cuenta_repeticiones;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Contabiliza las veces que se repite cada una de las palabras existentes
 * en un fichero de texto.
 * 
 * @author Estructuras de Datos (UC) y <TODO: nombre alumno>
 * @version dic-2021
 */
public class CuentaRepeticionesEficiente {

	// TODO: elegir un TAD que permita una implementacion mas eficiente
	private HashMap<String, RepeticionesPalabra> cuentaRepeticiones = new HashMap<String, RepeticionesPalabra> ();

	private List<RepeticionesPalabra> ranking = null;
	// creada la primera vez que se llama al metodo palabraEnRanking()

	/**
	 * Lanzada por palabraEnRanking cuando se pasa una posicion no valida.
	 */
	@SuppressWarnings("serial")
	public static class PosicionIncorrecta extends RuntimeException {
	}

	/**
	 * Contabiliza las veces que se repite cada una de las palabras existentes
	 * en un fichero de texto y lo almacena en una estructura de datos interna para
	 * su posterior consulta con el metodo repeticiones.
	 * @param nomFich nombre del fichero en el que contabilizar las palabras.
	 * @throws FileNotFoundException si no existe el fichero.
	 */
	public CuentaRepeticionesEficiente(String nomFich) throws FileNotFoundException {
		String str = "";
		try (Scanner in = new Scanner(new FileReader(nomFich))) {
			while (in.hasNext()) {
				str = in.next();

				// convierte a minusculas
				str = str.toLowerCase();

				// elimina los caracteres que no sean letras
				str = str.replaceAll("[^a-zñáéíóúü]", "");

				// acumula ocurrencia
				if (!str.equals("")) {
					// : implementar de forma mas eficiente
					RepeticionesPalabra word = cuentaRepeticiones.get(str); 
					if (word == null) {
						cuentaRepeticiones.put(str,new RepeticionesPalabra(str));
					} else {
						cuentaRepeticiones.get(str).sumaRepeticion();
					}
				}
			}
		}
	}

	/**
	 * Retorna las veces que se repite la palabra en el fichero que fue
	 * pasada como parametro al constructor.
	 * @param palabra palabra de la que se quiere saber cuantas veces se
	 * encuentra repetida en el fichero.
	 * @return numero de repeticiones de la palabra en el fichero.
	 */
	public int repeticionesPalabra(String palabra) {
		// TODO: implementar de forma mas eficiente
		RepeticionesPalabra word = cuentaRepeticiones.get(palabra); 
		if (word == null) {
			return 0;
		} else {
			return cuentaRepeticiones.get(palabra).repeticiones();
		}
	}

	/**
	 * Retorna la palabra que ocupa la posicion indicada en el ranking de palabras
	 * mas repetidas.
	 * La posicion 1 corresponde a la palabra mas repetida.
	 * @param pos posicion en el ranking.
	 * @return palabra que ocupa la posicion indicada en el ranking.
	 * @throws PosicionIncorrecta si la posicion es menor o igual que 0
	 * o mayor que el numero de palabras en el fichero.
	 */
	public RepeticionesPalabra palabraEnRanking(int pos) throws PosicionIncorrecta {

		if (pos <= 0 || pos > cuentaRepeticiones.size()) {
			throw new PosicionIncorrecta();
		}

		if (ranking == null) {
			ranking = new ArrayList<RepeticionesPalabra>(cuentaRepeticiones.values());
			ranking.sort(null);
		}
		
		

		return 	ranking.get(pos-1);

	}
}
