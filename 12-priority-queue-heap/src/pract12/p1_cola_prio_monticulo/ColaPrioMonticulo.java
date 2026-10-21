package pract12.p1_cola_prio_monticulo;

import java.util.Arrays;

/**
 * Implementación de una cola de prioridad generica utilizando un monticulo binario.
 * Se trata de una estructura acotada, con una capacida maxima indicada
 * en el constructor.
 *
 * @param <E> tipo de los elementos almacenados en la cola
 * 
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 * @version nov-2024
 */
public class ColaPrioMonticulo<E extends Comparable<E>> implements IColaPrioridad<E> {
	// Atributos de la clase
	private E[] elementos; // array sobre el que se implementa el monticulo binario
	private int numEle;    // numero de elementos en la cola

	/**
	 * Crea una cola con la capacidad maxima indicada.
	 * @param capacidad capacidad maxima de la cola.
	 */
	@SuppressWarnings("unchecked")
	public ColaPrioMonticulo(int capacidad) {
		// crea un array de elementos comparables
		// (el compilador pone un "warning", pero esta bien)
		elementos = (E[]) new Comparable[capacidad + 1]; 
		numEle = 0;
	}

	/**
	 * Anhade el elemento dado a la cola en la posicion
	 * correspondiente a su prioridad.
	 * La cola es acotada, por lo que no es posible encolar si se
	 * ha alcanzado la capacidad.
	 * @param e elemento a anhadir.
	 * @throws UnsupportedOperationException si la cola esta llena.
	 */
	@Override
	public void encolaConPrioridad(E e) throws UnsupportedOperationException {

		if (numEle == elementos.length - 1) {
			throw new UnsupportedOperationException(); // cola llena
		}

		// empezamos en la primera posicion libre del arbol
		int posHueco = numEle + 1;

		// reflota el hueco
		while (posHueco > 1 && e.compareTo(elementos[posPadre(posHueco)]) < 0) {
			elementos[posHueco] = elementos[posPadre(posHueco)];
			posHueco = posPadre(posHueco);		
		}

		numEle++;
		// copia el elemento en la posicion final del hueco
		elementos[posHueco] = e;
	}

	/**
	 * Elimina y retorna el elemento mas prioritario.
	 * @return elemento mas prioritario o null si la cola esta vacia.
	 */
	@Override
	public E desencolaMasPrioritario() {
		if (numEle == 0) {
			return null;
		}
		E eleMasPrio = masPrioritario();
		E x = elementos[numEle];
		int posHueco = 1;

		boolean verificaMinimalidad = false;
		int posHijoMenor;

		while (!verificaMinimalidad && ((posHueco * 2) < numEle)) {

			if (elementos[posHijoDer(posHueco)] != null && 
					elementos[posHijoIzq(posHueco)]
							.compareTo(elementos[posHijoDer(posHueco)]) > 0) {
				
				posHijoMenor = posHijoDer(posHueco);
			} else {
				posHijoMenor = posHijoIzq(posHueco);
			}

			if (elementos[posHijoMenor].compareTo(x) < 0) {
				elementos[posHueco] = elementos[posHijoMenor];
				posHueco = posHijoMenor;
			} else {
				verificaMinimalidad = true;
			}
		}
		elementos[posHueco] = x;
		elementos[numEle] = null;
		numEle--;
		return eleMasPrio;
	}

	/**
	 * Retorna (pero no elimina) el elemento  mas prioritario.
	 * @return elemento  mas prioritario o null si la cola esta vacia.
	 */
	@Override
	public E masPrioritario() {
		if (numEle == 0) {
			return null;
		}

		return elementos[1];
	}

	/**
	 * Vacia la cola (pasa a tener 0 elementos).
	 */
	@Override
	public void haceVacia() {
		// Ponemos a null para que pueda entrar el recolector de basura
		for (int pos = 1; pos <= numEle; pos++) {
			elementos[pos] = null;
		}
		numEle = 0;
	}

	/**
	 * Retorna el numero de elementos en la cola.
	 * @return el numero de elementos en la cola.
	 */
	@Override
	public int tamanho() {
		return numEle;
	}

	/**
	 * Retorna la posicion del padre de la posicion dada.
	 * @param pos posicion del elemento.
	 * @return posicion del padre.
	 */
	private int posPadre(int pos) {
		return pos / 2;
	}

	/**
	 * Retorna la posicion del hijo izquierdo de la posicion dada.
	 * @param pos posicion del elemento.
	 * @return posicion del hijo izquierdo.
	 */
	private int posHijoIzq(int pos) {
		return pos * 2;
	}

	/**
	 * Retorna la posicion del hijo derecho de la posicion dada.
	 * @param pos posicion del elemento.
	 * @return posicion del hijo derecho.
	 */
	private int posHijoDer(int pos) {
		return pos * 2 + 1;
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(elementos, 1, numEle + 1)) + " numEle:" + numEle;
	}
}
