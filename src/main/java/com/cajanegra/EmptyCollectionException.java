package com.cajanegra;
/**
 * Al heredar de {@link Exception} se trata de una excepción de tipo
 * "checked", y como tal debe declararse mediante <code>throws</code>
 * en los métodos que pueden lanzarla.
 * 
 * @author profesor
 *
 */
public class EmptyCollectionException extends Exception {

	private static final long serialVersionUID = 4468275233875850540L;

	/**
	 * 
	 * @param nameCollection 
	 */
	public EmptyCollectionException(String nameCollection) {
		super(nameCollection);
	}
}
