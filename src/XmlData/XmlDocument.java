package XmlData;

import java.util.List;

import Object.News;

/**  
*  
* ����XML�ĵ�����������Ľӿ�  
*/   
public interface XmlDocument {   
	//��ʼ��xml
	public void initXml(String fileName);
	/**  
	* �޸�XML�ĵ�  
	* @param fileName �ļ�ȫ·������  
	*/   
	public void modifyXml(News modifiedNews, String fileName);   
	/**  
	* ����XML�ĵ�  
	* @param fileName �ļ�ȫ·������  
	*/   
	public void parserXml(String fileName,List<News> newsList);   
}   