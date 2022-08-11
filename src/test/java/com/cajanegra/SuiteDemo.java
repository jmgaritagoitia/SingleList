package com.cajanegra;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(value = { AddTest.class, OtherTests.class })
class SuiteDemo {
	@BeforeAll
	public void setUp() {
		System.out.println("Hola mundo");
	}
}