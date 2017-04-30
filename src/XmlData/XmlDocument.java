package XmlData;

import java.util.Map;

/**  
*  
* 定义XML文档建立与解析的接口  
*/   
public interface XmlDocument {   
	/**  
	* 建立XML文档  
	* @param fileName 文件全路径名称  
	*/   
	public void modifyXml(String fileName,News modifiedNews);   
	/**  
	* 解析XML文档  
	* @param fileName 文件全路径名称  
	*/   
	public void parserXml(String fileName,Map<String,News> map);   
}   