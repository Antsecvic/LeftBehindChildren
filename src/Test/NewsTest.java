package Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import XmlData.News;

public class NewsTest {
	
	public static String a="null";
	
	private static News test=new News();
	@Before
	public void setUp() throws Exception {
	}
	
	

	@Test
	public void testSetNewsData() {
		test.setNewsData(a);
		assertEquals(a,test.getNewsData());
	}

	@Test
	public void testSetTagIts() {
		test.setTagIts(a);
		assertEquals(a,test.getTagIts());
	}
	
	@Test
	public void testSetIsLoad() {
		test.setIsLoad(a);
		assertEquals(a,test.getIsLoad());
	}

	@Test
	public void testSetIsDeleted() {
		test.setIsDeleted(a);
		assertEquals(a,test.getIsDeleted());
	}

	@Test
	public void testSetMemo() {
		test.setMemo(a);
		assertEquals(a,test.getMemo());
	}

	@Test
	public void testSetTitle() {
		test.setTitle(a);
		assertEquals(a,test.getTitle());
	}

	@Test
	public void testSetDate() {
		test.setDate(a);
		assertEquals(a,test.getDate());
		
	}

	@Test
	public void testSetLocation() {
		test.setLocation(a);
		assertEquals(a,test.getLocation());
	}

	@Test
	public void testSetUrl() {
		test.setUrl(a);
		assertEquals(a,test.getUrl());
	}
	

	@Test
	public void testSetType() {
		test.setType(a);
		assertEquals(a,test.getType());
	}

	@Test
	public void testSetWordCount() {
		test.setWordCount(a);
		assertEquals(a,test.getWordCount());
	}
	

	@Test
	public void testSetID() {
		test.setID(a);
		assertEquals(a,test.getID());
	}

	@Test
	public void testSetTrueUrl() {
		test.setTrueUrl(a);
		assertEquals(a,test.getTrueUrl());
	}

	@Test
	public void testSetEncodedContent() {
		test.setEncodedContent(a);
		assertEquals(a,test.getEncodedContent());
	}
	

}
