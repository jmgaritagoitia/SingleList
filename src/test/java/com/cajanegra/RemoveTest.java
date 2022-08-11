package com.cajanegra;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RemoveTest {
	private SingleLinkedListImpl<String> lS, lS2;
	private SingleLinkedListImpl<String> lSABC;

	@BeforeEach
	public void setUp() {
		this.lS = new SingleLinkedListImpl<String>();
		this.lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
		this.lS2 = new SingleLinkedListImpl<String>("A", "C", "B", "C", "D", "C");
	}

	@Test
	@Tag("removeLast")
	public void removeLast() throws EmptyCollectionException {
		lSABC.removeLast();
		assertEquals("[A, B]", lSABC.toString());
		Exception thrown = assertThrows(EmptyCollectionException.class, () -> {
			lS.removeLast();
		});
		assertEquals("La lista esta vacia", thrown.getMessage());

		lS.addLast("A");
		lS.removeLast();
		assertEquals("[]", lS.toString());
	}

	@ParameterizedTest
	@CsvSource(value = { 
			"B:A B C:[A, C]", 
			"D:A C B C D C:[A, C, B, C, C]",
			"D:D A C B C D C:[D, A, C, B, C, C]",
			"D:D A C B C C:[A, C, B, C, C]"
	}, delimiter = ':')

	@Tag("removeLastWithParameter")
	public void removeLastConParametro(String aborrar, String cadena, String resultado)
			throws EmptyCollectionException {

		if (cadena.length() > 1) {
			String[] s = cadena.split(" ");
			for (int i = 0; i < s.length; i++) {
				lS.addLast(s[i]);
			}
		} else {
			lS.addAtPos(cadena, 1);
		}
		assertAll("", () -> assertEquals(lS.removeLast(aborrar), aborrar),
				() -> assertEquals(lS.toString(), resultado));

	}

	@Test
	@Tag("removeLastWithParameterException")
	public void removeLastConParametroException() throws EmptyCollectionException {
		assertThrows(EmptyCollectionException.class, () -> {
			String _elem = "X";
			lS.removeLast(_elem);
		});
		/**
		 * lanza la excepcicón NoSuchElementException cuando el elemento a borrar no
		 * está en la lista
		 */
		assertThrows(NoSuchElementException.class, () -> {
			String _elem = "X";
			lS2.removeLast(_elem);
		});

	}
}
