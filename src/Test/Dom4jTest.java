package Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import XmlData.Dom4j;
import XmlData.News;


public class Dom4jTest {
	
	
	public Dom4j testD=new Dom4j();
	public News testN=new News();
	public List<News> testl=new ArrayList<News>();
	
	@Test
	public void testInitXml() {
		testD.initXml("assets/NewFile.xml");
	}
	//初始化一个新的xml
	
	@Test
	public void testModifyXml() {
		testN.setLocation("光明日报");
		testD.modifyXml(testN);
	}

	@Test
	public void testParserXml() {
		testD.parserXml("assets/NewFile.xml", testl);
	}

	@Test
	public void testDeleteNews() {
		testN.setIsDeleted("true");
		testD.modifyXml(testN);
	}

	@Test
	public void testRestoreNews() {
		testN.setIsDeleted("false");
		testD.modifyXml(testN);
	}

}
