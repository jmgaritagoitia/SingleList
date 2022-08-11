package com.cajanegra;

import java.util.NoSuchElementException;

public abstract class AbstractSingleLinkedListImpl<T> implements SingleLinkedList<T> {

	/**
	 *  @return true si la lista es vacía, false en caso contrario
	 */
	public abstract boolean isEmpty();
	
	/**
	 *  @return número de elementos de la lista
	 */
	public abstract int size();
	
	/**
	 * Inserta un elemento como primer elemento.
	 * @param element a insertar
	 */
	public abstract void addFirst(T element);
	
	/**
	 * Inserta un elemento como último de la lista
	 * @param element a insertar
	 */
	public abstract  void addLast(T element);

	
	/**  
	 * <p>Inserta el elemento en la posición p, desplazando los elementos a partir de esa posición. 
	 * Si la lista tiene menos de p elementos lo insertará como último elemento.</p>
	 * 
	 * <p>Si la lista es [A, B, C] :</p>
	 *       <ul>
	 *       <li>lista.addAtPos(Z, 1) dejará la lista  [Z, A, B, C]</li>
	 *       <li>lista.addAtPos(Z, 3) dejará la lista  [A, B, Z, C]</li>
	 *       <li>lista.addAtPos(Z, 5) dejará la lista  [A, B, C, Z]</li>
	 *       </ul>
	 * @param element a insertar
	 * @param p posicion en la que se insertará el elemento, desplazando los siguientes. 
	 * @throws IllegalArgumentException Si p es menor o igual a cero.
	 */
	public abstract void addAtPos(T element, int p) throws IllegalArgumentException;
	
	/**
	 *  
	 * <p>Inserta n veces el elemento al final de la lista.</p>
	 * <p>Si lista=[A, B, C], lista.addNTimes(Z, 4) dejará la lista como: [A, B, C, Z, Z, Z, Z]</p>
	 *       
	 * @param element a insertar
	 * @param n es el número de veces que se repite el elemento. 
	 * @throws IllegalArgumentException si n es &le; que cero
	 */
	public abstract void addNTimes(T element, int n) throws IllegalArgumentException;

	/**
	 *  
	 * <p>Indica la posición donde se encuentra la primera aparición de elem desde el principio de la lista (las posiciones empiezan en 1).</p> 
	 * <p>Se lanza NoSuchElementException si el elemento no está en la lista en la lista.</p>
	 *       
	 * @param elem  elemento a buscar 
	 * @return posición que ocupa el elemento en la lista. 
	 * @throws NoSuchElementException si elem no está en la lista.
	 */
	public abstract int indexOf(T elem) throws NoSuchElementException;
	
	
	/**
	 *  
	 * Devuelve y elimina el último elemento de la lista.
	 * 
	 * @return el elemento que es eliminado. 
	 * @throws EmptyCollectionException si la lista está vacía
	 */
	public abstract T removeLast() throws EmptyCollectionException;

	/**
	 * <p>Elimina la última aparición del elemento que se le pasa como parámetro
	 * . Si la lista es vacía dispara la excepción EmptyCollectionException.</p>
	 * 
	 * Si lista=[A, C, B, C, D, C], lista.removeLast(D) dejará la lista=[A, C, B, C, C]
	 * @param elem  el elemento a eliminar 
	 * @return el elemento que es eliminado. 
	 * @throws EmptyCollectionException si la lista está vacía
	 * @throws NoSuchElementException si el elemento a eliminar no está en la lista
	 */
	public abstract T removeLast(T elem) throws EmptyCollectionException, NoSuchElementException;

	/**
	 * Devuelve el elemento de la posición pos
	 * @param pos posición del elemento a buscar
	 * @return el elemento que está en pos
	 * @throws IllegalArgumentException  Si no encuentra el elemento o 
	 * 	        pos &le; 0  o  pos &gt; size()  
	 */
	public abstract T getAtPos (int pos)throws IllegalArgumentException;
	
	/**
	 * <p>Devuelve la lista inversa de la lista actual. Deja la lista actual sin modificar.</p>
	 * <p>Por ejemplo, si la lista era [A, B, C], la lista devuelta será [C,B,A] </p>
	 * @return la lista invertida
	 */
	public abstract AbstractSingleLinkedListImpl<T> reverse();
	
	/**
	 *   
	 *  
	 * <p>Indica a partir de qué posición se encuentra la sublista pasada como parámetro en la lista actual o 
	 * -1 si no se encuentra. Si part es vacía o le pasamos null devuelve 0.</p>
	 * <p>Ejemplos:</p>
	 * <ul>
	 *  <li>[A, B, A, B, C] con part=[B, A, X], devolverá -1</li>  
	 *  <li>[A, B, A, B, C] con part=[B, A], devolverá 2</li>  
	 *  <li>[A, B, A, B, C] con part=[A, B], devolverá 1</li>
	 *  <li>[A, B, A, B, C, X, A] con part=[B, C, X], devolverá 4</li>
	 *  </ul>
	 * @param part comprobamos si "part" es sublista de la actual
	 * @return posición a partir de la que se encuentra la sublista en la lista actual
	 */
	public abstract int isSubList(AbstractSingleLinkedListImpl<T> part) ;


	static class Node<G> {
		
		public Node(G element) {
			this.content = element;
			this.next = null;
		}
		
		G content;
		
		Node<G> next;
	}
	
	protected Node<T> header;

	
	@Override
	public String toString() {
		if (header != null) {
			StringBuffer rx = new StringBuffer();
			rx.append("[");
			Node<T> i = header;
			while (i != null) {
				rx.append(i.content);
				rx.append(", ");
				i = i.next;
			}
			rx.delete(rx.length() - 2, rx.length());
			rx.append("]");
			
			return rx.toString();
		} else {
			return "[]";
		}
	}

	
	

}
