package XmlData;

import UI.CipherUtil;
import UI.ListData;

public class SaveToXml {
	private ListData listData;
	private String filePath;
	public SaveToXml(String filePath){
		this.filePath = filePath;
		listData = ListData.getInstance();
		if(listData.newsList.size()!=0){
			Dom4j dom4j = new Dom4j();
			dom4j.modifyAll(listData.deletedNews,filePath);
			dom4j.modifyAll(listData.classifiedNews,filePath);
		}		
	}
}
