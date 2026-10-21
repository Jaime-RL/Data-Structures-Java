package pract08.c2_algoritmos_recursivos;

import pract08.c1_arbol_binario_ph.IArbolBinario;
import pract08.c1_arbol_binario_ph.INodoArbolBinario;

/**
 * Algoritmos recursivos sobre arboles binarios.
 * 
 * @author Estructuras de Datos (UC) y Jaime Rodríguez López
 * @version oct-2022
 */
public class AlgoritmosRecursivos {

	/**
	 * Retorna el numero de veces que se encuentra el elemento
	 * buscado en el arbol.
	 * @param <E> tipo de los elementos almacenados en el arbol.
	 * @param arbol arbol en el que contar el numero de ocurrencias.
	 * @param buscado elemento buscado.
	 * @return numero de ocurrencias del elemento en el arbol.
	 */
	public static <E> int cuentaOcurrenciasElemento(IArbolBinario<E> arbol,
			E buscado) {

		return preorden(arbol.raiz(), buscado);
	}

	private static <E> int preorden(INodoArbolBinario<E> nodo, E buscado) {

		if (nodo == null) {
			return 0;
		}
	
		int contador = 0; 
		if (nodo.contenido().equals(buscado)) {
			contador++;
		}

		if (nodo.hijoIzq() != null) {
			contador += preorden(nodo.hijoIzq(), buscado);
		}
		if (nodo.hijoDer() != null) {
			contador += preorden(nodo.hijoDer(), buscado);
		}
		
		return contador;
	}
	
	/**
	 * Busca el primer nodo del arbol siguiendo un recorrido en inorden
	 * que tiene sus dos hijos iguales.
	 * @param <E> tipo de los elementos almacenados en el arbol.
	 * @param arbol arbol en el que buscar.
	 * @return el contenido del primer nodo del arbol siguiendo un recorrido en inorden
	 * que tiene sus dos hijos iguales o null si no existe ningun nodo con sus dos hijos
	 * iguales.
	 */
	public static <E> E buscaElemConDosHijosIgualesInorden(IArbolBinario<E> arbol)	{
		
		return inorden(arbol.raiz());
	}

	private static <E>  E inorden(INodoArbolBinario<E> nodo) {
		
		if (nodo == null) {
			return null;
		}
		
		if (nodo.hijoIzq() != null) {
			E aux = inorden(nodo.hijoIzq());
			if (aux != null) {
				return aux;
			}
		}
		
		if (nodo.hijoIzq() != null && nodo.hijoDer() != null &&
			nodo.hijoIzq().contenido().equals(nodo.hijoDer().contenido())) {
			return nodo.contenido();
		}
		
		if (nodo.hijoDer() != null) {
			E aux = inorden(nodo.hijoDer());
			if (aux != null) {
				return aux;
			}
		}
		
		return null;
	}

	
}
