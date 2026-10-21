package pract08.c1_arbol_binario_ph;

/**
 * Arbol binario "padre-hijos" (nodos con punteros a
 * padre, hijo derecho e hijo izquierdo.
 * 
 * @param <E> tipo de los elementos almacenados en el arbol
 * 
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 * @version oct-2022
 */
public class ArbolBinarioPH<E> implements IArbolBinario<E> {

	private Nodo raiz;

	private int numNodos;

	/**
	 * Crea un arbol vacio.
	 */
	public ArbolBinarioPH() {
		raiz = null;
		numNodos = 0;
	}

	/**
	 * Crea un arbol con con el contenido indicado en su nodo raiz.
	 * @param contenidoRaiz contenido del nodo raiz.
	 */
	public ArbolBinarioPH(E contenidoRaiz) {
		raiz = new Nodo(contenidoRaiz, null, null, null);
		numNodos = 1;
	}

	/**
	 * Retorna el elemento que ocupa la raiz del arbol.
	 * @return elemento que ocupa la raiz del arbol
	 */
	@Override
	public Nodo raiz() {
		return raiz;
	}

	/**
	 * Retorna el numero de elementos del arbol.
	 * @return numero de elementos del arbol
	 */
	@Override
	public int tamanho() {
		return numNodos;
	}

	/**
	 * Vacia el arbol.
	 */
	@Override
	public void haceVacio() {
		raiz.hijoDer = null;
		raiz.hijoIzq = null;
		raiz = null;

		numNodos = 0;
	}

	/**
	 * En un arbol vacio anhade el nodo raiz con el contenido indicado.
	 * @param contenidoRaiz contenido del nodo raiz
	 * @throws UnsupportedOperationException si el arbol no esta vacio.
	 */
	@Override
	public void anhadeRaiz(E contenidoRaiz)
			throws UnsupportedOperationException {

		if (raiz != null) {
			throw new UnsupportedOperationException();
		}

		raiz = new Nodo(contenidoRaiz, null, null, null);
		numNodos++;
	}

	/**
	 * Clase que implementa un nodo del arbol.
	 */
	public class Nodo implements INodoArbolBinario<E> {

		private E contenido;
		private Nodo padre;
		private Nodo hijoIzq;
		private Nodo hijoDer;

		/**
		 * Construye un nodo.
		 * @param contenido contenido del nodo.
		 * @param padre nodo padre del nodo.
		 * @param hijoIzq nodo que es el hijo izquierdo del nodo.
		 * @param hijoDer nodo que es el hijo derecho del nodo.
		 */
		private Nodo(E contenido, Nodo padre, Nodo hijoIzq, Nodo hijoDer) {
			this.contenido = contenido;
			this.padre = padre;
			this.hijoIzq = hijoIzq;
			this.hijoDer = hijoDer;
		}

		/**
		 * Retorna el contenido del nodo.
		 * @return el contenido del nodo.
		 */
		@Override
		public E contenido() {
			return contenido;
		}

		/**
		 * Cambia el contenido del nodo.
		 * @param nuevoContenido nuevo contenido del nodo.
		 */
		@Override
		public void cambiaContenido(E nuevoContenido) {
			contenido = nuevoContenido;
		}

		/**
		 * Retorna el padre del nodo.
		 * @return el padre del nodo o null si el nodo actual es la raiz.
		 */
		@Override
		public Nodo padre() {	
			return padre;
		}

		/**
		 * Retorna el hijo izquierdo del nodo.
		 * @return el hijo izquierdo del nodo o null si el nodo actual
		 * no tiene hijo izquierdo.
		 */
		@Override
		public Nodo hijoIzq() {
			return hijoIzq;
		}

		/**
		 * Retorna el hijo derecho del nodo.
		 * @return el hijo derecho del nodo o null si el nodo actual
		 * no tiene hijo derecho.
		 */
		@Override
		public Nodo hijoDer() {
			return hijoDer;
		}

		/**
		 * Anhade el hijo izquierdo al nodo.
		 * @param contenido contenido del hijo izquierdo.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo izquierdo.
		 */
		@Override
		public void anhadeHijoIzq(E contenido)
				throws UnsupportedOperationException {

			if (hijoIzq != null) {
				throw new UnsupportedOperationException();
			}

			hijoIzq = new Nodo(contenido, this, null, null);
			numNodos++;
		}

		/**
		 * Anhade el hijo derecho al nodo.
		 * @param contenido contenido del hijo derecho.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo derecho.
		 */
		@Override
		public void anhadeHijoDer(E contenido)
				throws UnsupportedOperationException {

			if (hijoDer != null) {
				throw new UnsupportedOperationException();
			}

			hijoDer = new Nodo(contenido, this, null, null);
			numNodos++;
		}

		/**
		 * Anhade la raiz de la rama como hijo izquierdo del nodo actual.
		 * Despues de esta operacion el arbol "rama" queda vacio.
		 * @param rama rama que anhadir como hijo izquierdo.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo izquierdo.
		 */
		@Override
		public void anhadeRamaIzq(IArbolBinario<E> rama)
				throws UnsupportedOperationException {
			// XXX parte opcional			
		}

		/**
		 * Anhade la raiz de la rama como hijo derecho del nodo actual.
		 * Despues de esta operacion el arbol "rama" queda vacio.
		 * @param rama rama que anhadir como hijo derecho.
		 * @throws UnsupportedOperationException si el nodo ya tiene
		 * hijo derecho.
		 */
		@Override
		public void anhadeRamaDer(IArbolBinario<E> rama) throws UnsupportedOperationException {
			// XXX parte opcional			
		}

		/**
		 * Elimina la rama del arbol cuya raiz es el nodo izquierdo del
		 * nodo actual.
		 * @return arbol cuya raiz es el nodo que era el hijo izquierdo del
		 * nodo actual.
		 */
		@Override
		public IArbolBinario<E> cortaRamaIzq() {
			// XXX parte opcional
			return null;
		}

		/**
		 * Elimina la rama del arbol cuya raiz es el nodo derecho del
		 * nodo actual.
		 * @return arbol cuya raiz es el nodo que era el hijo derecho del
		 * nodo actual.
		 */
		@Override
		public IArbolBinario<E> cortaRamaDer() {
			// XXX parte opcional
			return null;
		}
	}
}
