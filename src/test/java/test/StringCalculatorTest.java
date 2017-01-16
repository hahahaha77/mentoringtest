package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest {
	StringCalculator sCal;
	@Before
	public void setup() {
		sCal = new StringCalculator();
	}
	
	@Test
	public void add_공백() throws Exception {
		int result = sCal.add("");
		
		assertEquals(0, result);
	}
	
	@Test
	public void add_숫자1개() throws Exception {
		assertEquals(3, sCal.add("3"));
		
	}
	
	@Test
	public void add_쉼표() throws Exception {		
		assertEquals(6, sCal.add("1,2,3"));
	}
	
	@Test
	public void add_콜론() throws Exception {
		assertEquals(3, sCal.add("1:2"));
	}
	
	@Test
	public void add_쉼표_콜론() throws Exception {
		
	}
	
	
}
