package com.cajanegra;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OtherTests {

	private SingleLinkedListImpl<String> lS, lS2;
	private SingleLinkedListImpl<String> lSABC, lSABABC, lSubLista;

	@BeforeEach
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();
		this.lS2 = new SingleLinkedListImpl<String>("B", "C", "X");
		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
		this.lSABABC = new SingleLinkedListImpl<String>("A", "B", "A", "B", "C");
		this.lSubLista = new SingleLinkedListImpl<String>();
	}

	@Test
	@Tag("Other")
	public void constructorElemens() {
		lS = new SingleLinkedListImpl<String>("A", "B", "C", "D");
		assertEquals("[A, B, C, D]", lS.toString());
	}

	@Test
	@Tag("Other")
	public void isEmpty() {
		assertEquals(lS.isEmpty(), true);
		lS.addAtPos("A", 1);
		assertEquals(lS.isEmpty(), false);
	}

	@ParameterizedTest
	@CsvSource({ "A,1", "A B,2", "A B C D E F,6" })
	@Tag("Other")
	public void size(String input, String result) {
		int result_ = Integer.parseInt(result);
		if (input.length() > 1) {
			String[] s = input.split(" ");
			for (int i = 0; i < s.length; i++) {
				lS.addAtPos(s[i], 1);
			}
		} else
			lS.addAtPos(input, 1);
		assertEquals(lS.size(), result_);
	}

	@ParameterizedTest(name = "#{index} - El elemento {0} esta en la posicion {1}")
	@CsvSource({ "A,1", "B,2", "C,3" })
	@Tag("Other")
	public void indexOf(String input, String result) {
		int result_ = Integer.parseInt(result);
		assertEquals(lSABC.indexOf(input), result_);
	}

	@Test
	@Tag("Other")
	public void indexOfEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			lS.indexOf("X");
		});
	}

	@Test
	@Tag("Other")
	public void getAtPosTest() {
		assertEquals("A", lSABC.getAtPos(1));
		assertEquals("B", lSABC.getAtPos(2));
		assertEquals("C", lSABC.getAtPos(3));
	}

	@ParameterizedTest(name = "#{index} - ejecuto Test con pos = {0} ")
	@ValueSource(ints = { -1, 0, Integer.MAX_VALUE })
	@Tag("Other")
	public void getAtPosTestException(int num) {
		assertThrows(IllegalArgumentException.class, () -> {
			lSABC.getAtPos(num);
		});
	}

	@ParameterizedTest
	@CsvSource(value = { 
			"A B A B:B A X:-1", 
			"A B A B C:B A:2", 
			"A B A B C:A B:1", 
			"A B A B C X A:B C X:4", 
			"A:A:1",
			"A B:B:2" 
	}, delimiter = ':')

	@Tag("Other")
	public void isSublist(String lista, String subLista, String resultado) {
		String[] s = lista.split(" ");
		for (int i = 0; i < s.length; i++) {
			lS.addLast(s[i]);
		}
		s = subLista.split(" ");
		for (int i = 0; i < s.length; i++) {
			lSubLista.addLast(s[i]);
		}
		int result = Integer.parseInt(resultado);
		assertEquals(result, lS.isSubList(lSubLista));
	}

	@Test
	@Tag("Other")
	@DisplayName("Test isSublist lanzando Excepciones")
	public void isSublistEx() {
		assertEquals(0, lSABABC.isSubList(null));
		assertEquals(0, lSABABC.isSubList(new SingleLinkedListImpl<String>()));
	}

	@Test
	@Tag("Other")
	@DisplayName("Test iterator_1")
	public void iterator_1() {
		Iterator<String> it = lS2.iterator();
		SingleLinkedListImpl<String> aux = new SingleLinkedListImpl<String>();
		while (it.hasNext()) {
			String s = (String) it.next();
			aux.addLast(s);
		}
		System.out.println(lS2 + " " + aux);
		assertEquals(lS2.toString(), aux.toString());
	}

	@Test
	@Tag("Other")
	@DisplayName("Test iterator_2")
	public void iterator_2() {
		Iterator<String> it = lS2.iterator();
		SingleLinkedListImpl<String> aux = new SingleLinkedListImpl<String>();
		while (it.hasNext()) {
			String s = (String) it.next();
			aux.addLast(s);
		}
		aux = (SingleLinkedListImpl<String>) aux.reverse();
		lS2 = (SingleLinkedListImpl<String>) lS2.reverse();
		System.out.println(lS2 + " " + aux);
		assertEquals(lS2.toString(), aux.toString());
	}

	@Test
	@Tag("Other")
	@DisplayName("Reverse")
	public void reverse() {

		assertEquals(lS2.reverse().toString(), "[X, C, B]");
		assertEquals(lSABC.reverse().toString(), "[C, B, A]");
		assertEquals(lS.reverse().toString(), "[]");

	}

}
