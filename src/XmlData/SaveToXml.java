package XmlData;

import UI.ListData;

public class SaveToXml {
	private ListData listData;
	public SaveToXml(){
		listData = ListData.getInstance();
		Dom4j dom4j = new Dom4j();
		dom4j.modifyAll(listData.deletedNews);
		dom4j.modifyAll(listData.classifiedNews);
	}
}
