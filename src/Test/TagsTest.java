package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import XmlData.Tags;

public class TagsTest {

	public Tags testTag=new Tags();
	private String str="hello";
	
	@Test
	public void testGetType() {
		testTag.setType(str);
		assertEquals(str,testTag.getType());
	}

	@Test
	public void testSetType() {
		testTag.setType(str);
	}

	@Test
	public void testGetTheme() {
		testTag.setTheme(str);
		assertEquals(str,testTag.getTheme());
	}

	@Test
	public void testSetTheme() {
		testTag.setTheme(str);
	}

	@Test
	public void testGetSource() {
		testTag.setSource(str);
		assertEquals(str,testTag.getSource());
	}

	@Test
	public void testSetSource() {
		testTag.setSource(str);
	}

	@Test
	public void testGetShowing() {
		testTag.setShowing(str);
		assertEquals(str,testTag.getShowing());
	}

	@Test
	public void testSetShowing() {
		testTag.setShowing(str);
	}

	@Test
	public void testGetReason() {
		testTag.setReason(str);
		assertEquals(str,testTag.getReason());
	}

	@Test
	public void testSetReason() {
		testTag.setReason(str);
	}

	@Test
	public void testGetMainBody() {
		testTag.setMainBody(str);
		assertEquals(str,testTag.getMainBody());
	}

	@Test
	public void testSetMainBody() {
		testTag.setMainBody(str);
	}

	@Test
	public void testGetHelpType() {
		testTag.setHelpType(str);
		assertEquals(str,testTag.getHelpType());
	}

	@Test
	public void testSetHelpType() {
		testTag.setHelpType(str);
	}

	@Test
	public void testGetGender() {
		testTag.setGender(str);
		assertEquals(str,testTag.getGender());
	}

	@Test
	public void testSetGender() {
		testTag.setGender(str);
	}

}
