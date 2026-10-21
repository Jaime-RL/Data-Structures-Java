package pract10;

import java.util.LinkedList;
import java.util.List;

/**
 * Implementacion de mapa basado en ABB.
 *
 * @param <K> clase de elementos usados como llaves del mapa
 * @param <V> clase de elementos usados como valores del mapa
 * 
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 * @version nov-2024
 */
public class MapaABB<K extends Comparable<K>, V> implements IMapaOrdenado<K, V> {

	// Entrada del mapa
	private static class Entrada<K, V> implements IMapaOrdenado.IEntrada<K, V> {
		private K llave;
		private V valor;

		public Entrada(K llave, V valor) {
			this.llave = llave;
			this.valor = valor;
		}

		@Override
		public K llave() {
			return llave;
		}

		@Override
		public V valor() {
			return valor;
		}

		@Override
		public String toString() {
			return "(" + llave + "," + valor + ")";
		}
	}

	// Nodo del ABB sobre el que se implementa el mapa
	private static class Nodo<K, V> {
		private Entrada<K, V> entrada;
		private Nodo<K, V> hijoIzq;
		private Nodo<K, V> hijoDer;

		public Nodo(K k, V v) {
			entrada = new Entrada<K, V>(k, v);
		}
	}

	// raiz del ABB  sobre el que se implementa el mapa
	private Nodo<K, V> raiz = null;

	// numero de entradas en el mapa
	private int numEntradas = 0;

	/**
	 * Si NO existe la entrada para la llave, anhade la entrada (llave,valor) al mapa.
	 * Si existe la entrada para la llave, pone valor como nuevo valor asociado con la llave.
	 * 
	 * @param llave llave con la que asociar el valor.
	 * @param valor valor asociado a la llave.
	 */
	@Override
	public void anhade(K llave, V valor) {
		if (raiz == null) {
			raiz = new Nodo<K, V>(llave, valor);
			numEntradas++;
			return;
		}

		Nodo<K, V> n = raiz;
		Nodo<K, V> nPrev = null;
		boolean encontrado = false;

		do {
			nPrev = n;
			if (llave.compareTo(n.entrada.llave()) == 0) {
				encontrado = true;
				break;
				
			} else if (llave.compareTo(n.entrada.llave()) < 0) {
				n = n.hijoIzq;
			} else {
				n = n.hijoDer;
			}

		} while (n != null && !encontrado);
		
		if (encontrado) {
			nPrev.entrada.valor = valor;
		} else if (llave.compareTo(nPrev.entrada.llave()) < 0) {
			nPrev.hijoIzq = new Nodo<K, V>(llave, valor);
			numEntradas++;
		} else {
			nPrev.hijoDer = new Nodo<K, V>(llave, valor);
			numEntradas++;
		}
	}

	/**
	 * Si existe la entrada para la llave, la elimina.
	 * 
	 * @param llave llave de la entrada a eliminar.
	 */
	@Override
	public void elimina(K llave) {
		raiz = eliminaRec(raiz, llave);
	}

	/**
	 * Metodo recursivo que elimina la entrada con la llave indicada en
	 * el subarbol que tiene como raíz al nodo n.
	 * @param n nodo actual
	 * @param llave llave de la entrada a eliminar
	 * @return la nueva raíz del subárbol, que podrá ser
	 * el mismo nodo n o uno distinto en el caso de que
	 * n fuera precisamente el nodo a eliminar
	 */
	private Nodo<K, V> eliminaRec(Nodo<K, V> n, K llave) {
		if (n == null) {
			// no hay ninguna entrada en el mapa con la llave buscada
			return null;
		}
		int comp = llave.compareTo(n.entrada.llave);

		if (comp == 0) {
			// encontrada entrada con la llave buscada
			numEntradas--;

			if (n.hijoIzq != null && n.hijoDer != null) {
				// tiene los dos hijos

				// elimina el menor del subarbol derecho
				Nodo<K, V> menor = buscaMenor(n.hijoDer);
				n.hijoDer = eliminaRec(n.hijoDer, menor.entrada.llave);

				// sustituye el nodo por el menor eliminado del subarbol derecho
				n.entrada = menor.entrada;
				numEntradas++; // suma 1, porque la anterior llamada a eliminaRec
				// habría restado 1

			} else if (n.hijoIzq != null) {
				// tiene sólo el hijo izquierdo: se sustituye por él
				n = n.hijoIzq;

			} else {
				// o sólo tiene hijo derecho o no tiene nigún hijo: se sustituye
				// por el hijo derecho o se elimina directametente
				n = n.hijoDer;
			}
		}

		// caso recursivo
		if (comp < 0) {
			// llave < n.llave: sigo por el hijo izquierdo
			n.hijoIzq = eliminaRec(n.hijoIzq, llave);

		} else if (comp > 0) {
			// llave > n.llave: sigo por el hijo derecho
			n.hijoDer = eliminaRec(n.hijoDer, llave);
		}

		return n;
	}

	/**
	 * Retorna el valor asociado con la llave.
	 * 
	 * @param llave llave de la entrada buscada
	 * @return valor asociado con la llave o null si no hay ninguna
	 * entrada para la llave indicada
	 */
	@Override
	public V busca(K llave) {

		Nodo<K, V> n = raiz;
		V valorRet = null;
		boolean encontrado = false;

		while (n != null && !encontrado) {
			if (llave.compareTo(n.entrada.llave()) == 0) {
				encontrado = true;
				valorRet = n.entrada.valor;
			} else if (llave.compareTo(n.entrada.llave()) < 0) {
				n = n.hijoIzq;
			} else {
				n = n.hijoDer;
			}
		}
		return valorRet;
	}

	/**
	 * Retorna el numero de entradas en el mapa.
	 *	
	 * @return numero de entradas en el mapa
	 */
	@Override
	public int tamanho() {
		return numEntradas;
	}

	/**
	 * Vacia el mapa (pasa a tener 0 entradas).
	 */
	@Override
	public void haceVacio() {
		raiz = null;
		numEntradas = 0;
	}

	/**
	 * Retorna la entrada del mapa con la menor llave.
	 * @return la entrada del mapa con la menor llave o null
	 * si el mapa se encuentra vacio
	 */
	@Override
	public Entrada<K, V> primeraEntrada() {
		if (numEntradas == 0) {
			return null;
		}
		return buscaMenor(raiz).entrada;
	}

	/**
	 * Busca el nodo con menor llave en el subarbol que tiene como
	 * raiz al nodo n.
	 * @param n raiz del subarbol en el que buscar el menor
	 * @return elemento con menor llave en el subarbol
	 */
	private Nodo<K, V> buscaMenor(Nodo<K, V> n) {
		while (n.hijoIzq != null) {
			n = n.hijoIzq;
		}
		return n;
	}

	/**
	 * Retorna la entrada del mapa con la mayor llave.
	 * @return la entrada del mapa con la mayor llave o null
	 * si el mapa se encuentra vacio
	 */
	@Override
	public Entrada<K, V> ultimaEntrada() {
		
		Nodo<K, V> n = raiz;
		if (n == null) {
			return null;
		}
		while (n.hijoDer != null) {
			n = n.hijoDer;
		}
		return n.entrada;
	}

	/**
	 * Retorna todas las entradas existentes en el mapa
	 * (ordenadas en función de sus llaves).
	 * @return todas las entradas existentes en el mapa
	 * (ordenadas en función de sus llaves)
	 */
	@Override
	public List<IEntrada<K, V>> entradas() {
		List<IEntrada<K, V>> lst = new LinkedList<>();
		entradasInorden(raiz, lst);
		return lst;
	}

	/**
	 * Metodo recursivo que recorre el arbol en inorden y va anhadiendo
	 * las entradas en la lista.
	 * @param n nodo actual
	 * @param lst lista en la que se anhadiendo en inorden las entradas
	 */
	private void entradasInorden(Nodo<K, V> n, List<IEntrada<K, V>> lst) {
		
		if (n == null) {
			return;
		}
		
		if (n.hijoIzq != null) {
			entradasInorden(n.hijoIzq, lst);
		}
		
		lst.add(n.entrada);
		
		if (n.hijoDer != null) {
			entradasInorden(n.hijoDer, lst);
		}
	}

	/**
	 * Retorna una lista con todos los valores del mapa
	 * (ordenados en funcion de sus llaves).
	 * @return lista con todos los valores del mapa
	 * (ordenados en funcion de sus llaves)
	 */
	@Override
	public List<V> valores() {
		List<V> valores = new LinkedList<V>();
		
		
		valoresInorden(raiz, valores);
		
		return valores;
	}
	
	private void valoresInorden(Nodo<K, V> aux, List<V> lista) {
		if (aux == null) {
			return;
		}
		
		
		if (aux.hijoIzq != null) {
			valoresInorden(aux.hijoIzq, lista);
		}
		
		lista.add(aux.entrada.valor);
		
		if (aux.hijoDer != null) {
			valoresInorden(aux.hijoDer, lista);
		}
		
		
		
	}

	/**
	 * Retorna todas las entradas del mapa que tienen una
	 * llave menor que la llave indicada (ordenadas en
	 * función de sus llaves).
	 * @param llaveCorte las entradas retornadas tienen una llave menor
	 * que este valor 
	 * @return todas las entradas del mapa que tienen una
	 * llave menor que la llave indicada (ordenadas en
	 * función de sus llaves)
	 */
	@Override
	public List<IEntrada<K, V>> predecesores(K llaveCorte) {
	    List<IEntrada<K, V>> resultado = new LinkedList<>();
	    List<IEntrada<K, V>> entradasOrdenadas = new LinkedList<>();
	    
	    // Llamar a entradasInorden para llenar las entradas ordenadas en el árbol
	    entradasInorden(raiz, entradasOrdenadas);  // Asumiendo que 'raiz' es la raíz del árbol
	    
	    // Filtrar las entradas que tienen una llave menor que llaveCorte
	    for (IEntrada<K, V> entrada : entradasOrdenadas) {
	        if (entrada.llave().compareTo(llaveCorte) < 0) {
	            resultado.add(entrada);
	        }
	    }
	    
	    return resultado;
	}


	// Complejidad temporal: O(numEntradas)
	@Override
	public String toString() {
		return entradas().toString();
	}
}
