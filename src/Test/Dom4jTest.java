package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import XmlData.Dom4j;

public class Dom4jTest {

	private static Dom4j test=new Dom4j();
	private String str="PABoAHQAbQBsAD4AP";
	
	
	@Test
	public void testDecodeContent() {
		assertEquals("", test.decodeContent(str));
	}

}
