package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import XmlData.BareBonesBrowserLaunch;

public class BareBonesBrowserLaunchTest {

	public BareBonesBrowserLaunch launcher=new BareBonesBrowserLaunch();

	@Test
	public void testOpenURL() {
		launcher.openURL("www.baidu.com");
	}

}
