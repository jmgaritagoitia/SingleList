package com.cajanegra;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListImpl<T> extends AbstractSingleLinkedListImpl<T> {

	public SingleLinkedListImpl(T... elements) {
		this.header = null;
		SingleLinkedListImplRec(0, header, elements);
	}

	private void SingleLinkedListImplRec(int i, Node<T> a, T... elements) {
		if (i < elements.length) {
			if (a == null) {
				header = new Node<T>(elements[i]);
				a = header;
			} else {
				a.next = new Node<T>(elements[i]);
				a = a.next;
			}
			SingleLinkedListImplRec(i + 1, a, elements);
		}
	}

	@Override
	public void addLast(T element) {
		if (this.isEmpty()) {
			this.header = new Node<T>(element);
		} else
			addLast(element, header);
	}

	private void addLast(T element, Node<T> a) {
		if (a.next == null) {
			a.next = new Node<T>(element);
		} else {
			addLast(element, a.next);
		}
	}

	public Iterator<T> iterator() {
		return new SingleLinkedListIterator<T>(header);
	}

	public class SingleLinkedListIterator<T> implements Iterator<T> {

		private Node<T> current;

		public SingleLinkedListIterator(Node<T> header) {
			current = header;
		}

		public boolean hasNext() {
			return this.current != null;
		}

		public T next() {
			T result = current.content;
			current = current.next;
			return result;
		}
	}

	@Override
	public boolean isEmpty() {
		return this.header == null;
	}

	@Override
	public int size() {
		return size(header);
	}

	private int size(Node<T> a) {
		if (a == null)
			return 0;
		else
			return 1 + size(a.next);
	}

	@Override
	public void addFirst(T element) {
		Node<T> nuevo = new Node<T>(element);
		nuevo.next = header;
		header = nuevo;
	}

	@Override
	public void addAtPos(T element, int p) throws IllegalArgumentException {
		if (p <= 0)
			throw new IllegalArgumentException("Posicion incorrecta");
		Node<T> anterior;
		anterior = null;
		if (header == null)
			header = new Node<T>(element);
		else if (p == 1) {
			Node<T> nuevo = new Node<T>(element);
			nuevo.next = header;
			header = nuevo;
		} else
			addAtPos(element, p, anterior, header);

	}

	private void addAtPos(T element, int p, Node<T> anterior, Node<T> actual) {

		if (p == 1 || actual == null) {
			Node<T> nuevo = new Node<T>(element);
			nuevo.next = actual;
			anterior.next = nuevo;
		} else {
			anterior = actual;
			addAtPos(element, p - 1, anterior, actual.next);
		}

	}

	@Override
	public void addNTimes(T element, int n) throws IllegalArgumentException {
		if (n <= 0)
			throw new IllegalArgumentException("n No puede ser <= igual a cero");
		if (header == null) {
			for (int i = 1; i <= n; i++) {
				Node<T> nuevo = new Node<T>(element);
				nuevo.next = header;
				header = nuevo;
			}
		} else {
			Node<T> anterior = null;
			addNTimes(element, n, header, anterior);
		}
	}

	private void addNTimes(T element, int n, Node<T> a, Node<T> anterior) {
		if (a == null) {
			for (int i = 1; i <= n; i++) {
				Node<T> nuevo = new Node<T>(element);
				anterior.next = nuevo;
				anterior = nuevo;
			}
		} else {
			anterior = a;
			addNTimes(element, n, a.next, anterior);
		}
	}

	@Override
	public int indexOf(T elem) throws NoSuchElementException {
		return indexOf(elem, header);
	}

	private int indexOf(T elem, Node<T> a) throws NoSuchElementException {
		if (a == null)
			throw new NoSuchElementException("El elemento no está en la lista");
		else {
			if (elem.equals(a.content))
				return 1;
			else
				return 1 + indexOf(elem, a.next);
		}
	}

	@Override
	public T removeLast() throws EmptyCollectionException {
		if (isEmpty())
			throw new EmptyCollectionException("La lista esta vacia");
		if (this.size() == 1) {
			T e = header.content;
			header = null;
			return e;
		}
		return removeLast(header);
	}

	private T removeLast(Node<T> a) {
		Node<T> actual = this.header, anterior = null;
		while (actual.next != null) {
			anterior = actual;
			actual = actual.next;
		}
		T e = actual.content;
		anterior.next = null;
		return e;
	}

	@Override
	public T removeLast(T elem) throws EmptyCollectionException {
		if (isEmpty())
			throw new EmptyCollectionException("La lista está vacía");
		try {
			int i = indexOf(elem);
		} catch (NoSuchElementException e) {
			throw new NoSuchElementException("El elemento no esta en la lista");
		}
		Node<T> aborrar = null, actual = this.header, anterior = null;
		while (actual != null) {
			if (actual.content.equals(elem)) {
				aborrar = actual;
			}
			actual = actual.next;
		}
		actual = this.header;
		while (actual != aborrar) {
			anterior = actual;
			actual = actual.next;
		}
		if (anterior == null) {
			this.header = actual.next;
		} else {
			anterior.next = actual.next;
		}
		return aborrar.content;

	}

	@Override
	public AbstractSingleLinkedListImpl<T> reverse() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return new SingleLinkedListImpl<T>();
		}
		return reverse(header);
	}

	private AbstractSingleLinkedListImpl<T> reverse(Node<T> a) {
		if (a == null) {
			return new SingleLinkedListImpl<T>();
		} else {
			AbstractSingleLinkedListImpl<T> lista = reverse(a.next);
			lista.addLast(a.content);
			return lista;
		}
	}

	@Override
	public int isSubList(AbstractSingleLinkedListImpl<T> part) {
		if (part == null || part.isEmpty())
			return 0;
		return isSubList(part, header, 1);
	}

	private int isSubList(AbstractSingleLinkedListImpl<T> part, Node<T> a, int i) {
		if (a == null)
			return -1;
		else {
			Iterator<T> it = part.iterator();
			boolean encontrado;
			if (a.content.equals(it.next())) {
				Node<T> aux = a.next;
				encontrado = true;
				if (it.hasNext() && aux == null) {
					encontrado = false;
				}
				while (it.hasNext() && encontrado && aux != null) {
					T e = it.next();
					if (aux.content.equals(e)) {
						aux = aux.next;
					} else
						encontrado = false;
				}
				if (!encontrado) {
					return isSubList(part, a.next, i + 1);
				} else {
					return i;
				}
			} else {
				return isSubList(part, a.next, i + 1);
			}
		}
	}

	@Override
	public T getAtPos(int pos) throws IllegalArgumentException {
		if (pos <= 0 || pos > this.size())
			throw new IllegalArgumentException("La posición no puede ser menor o igual a cero");
		Iterator<T> it = new SingleLinkedListIterator<T>(header);
		int i = 1;
		T elem = null;
		while (i <= pos) {
			elem = (T) it.next();
			i++;
		}
		return elem;
	}

}
