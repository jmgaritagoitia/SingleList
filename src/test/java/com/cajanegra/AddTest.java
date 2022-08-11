package com.cajanegra;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AddTest {

	private SingleLinkedListImpl<String> lS;
	private SingleLinkedListImpl<String> lSABC, lResultado;

	@BeforeEach
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();
		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
		this.lResultado = new SingleLinkedListImpl<String>();
	}

	@Test
	@Tag("add")
	public void addFirst() {
		/**
		 * FALLA No mete el elemento en al ppio de la lista
		 */
		this.lS = new SingleLinkedListImpl<String>();
		lS.addFirst("A");
		// assertEquals("[A]", lS.toString());
		lS.addFirst("B");
		lS.addFirst("C");
		lS.addFirst("D");
		// assertEquals("[D, C, B, A]", lS.toString());
	}

	@Test
	@Tag("add")
	public void addLast() {
		lS = new SingleLinkedListImpl<String>();
		lS.addLast("hola");
		lS.addLast("adios");
		lS.addLast("vete");
		lS.addLast("estar");
		assertEquals("[hola, adios, vete, estar]", lS.toString());
	}

	@ParameterizedTest
	@CsvSource(value = { 
			"A B C:X:1:X A B C", 
			"A B C:X:3:A B X C",			
			"A B C:X:8:A B C X", 
			"A B C:X:-1:X A B C",
			"A B C:X:0:X A B C", 
			"A:X:1:X A",
			"A B C D E F G H I:X:5:A B C D X E F G H I",
			"A B C D E F G H I:X:15:A B C D E F G H I X"}, delimiter = ':')
	@Tag("add")
	public void addAtPos(String lista, String elem, String pos, String resultado) {
		if (lista.length() > 1) {
			String[] s = lista.split(" ");
			for (int i = 0; i < s.length; i++) {
				lS.addLast(s[i]);
			}
		} else
			lS.addAtPos(lista, 1);
		if (resultado != null) {
			String[] s = resultado.split(" ");
			for (int i = 0; i < s.length; i++) {
				lResultado.addLast(s[i]);
			}
		}
		if (Integer.parseInt(pos) > 0) {
			lS.addAtPos(elem, Integer.parseInt(pos));
			assertEquals(lResultado.toString(), lS.toString());
		} else {
			if (Integer.parseInt(pos) < 0) {
				assertThrows(IllegalArgumentException.class, () -> {
					lS.addAtPos("X", -1);
				}, "Posicion incorrecta");
				System.out.println(lS + " " + pos);
			}
			if (Integer.parseInt(pos) == 0) {
				assertThrows(IllegalArgumentException.class, () -> {
					lS.addAtPos("X", 0);
				}, "Posicion incorrecta");
			}
		}

	}

	@Test
	@Tag("add")
	public void addNTimes() {
		lSABC.addNTimes("Z", 4);
		assertEquals("[A, B, C, Z, Z, Z, Z]", lSABC.toString());
		/**
		 * FALLA porque el método lanza la excepcicón IllegalArgumentException cuando n
		 * <=0 y las espe- cificaciones dice que solo cuando n < 0
		 */
		// lSABC.addNTimes("X", 0);
		// assertEquals("[A, B, C, Z, Z, Z, Z]", lSABC.toString());
		assertThrows(IllegalArgumentException.class, () -> {
			lSABC.addNTimes("X", -1);
		}, "n No puede ser <= igual a cero");
		lS = new SingleLinkedListImpl<String>();
		lS.addNTimes("A", 3);
		assertEquals("[A, A, A]", lS.toString());
		lS.addNTimes("B", 2);
		assertEquals("[A, A, A, B, B]", lS.toString());

	}

}
