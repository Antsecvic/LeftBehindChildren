package XmlData;

import java.util.Map;

/**  
*  
* ����XML�ĵ�����������Ľӿ�  
*/   
public interface XmlDocument {   
	/**  
	* ����XML�ĵ�  
	* @param fileName �ļ�ȫ·������  
	*/   
	public void modifyXml(String fileName,News modifiedNews);   
	/**  
	* ����XML�ĵ�  
	* @param fileName �ļ�ȫ·������  
	*/   
	public void parserXml(String fileName,Map<String,News> map);   
}   