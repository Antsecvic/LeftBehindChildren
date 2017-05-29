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
	public SaveToXml(){
		listData = ListData.getInstance();
		if(listData.newsList.size()!=0){
			Dom4j dom4j = new Dom4j();
			for(News news : listData.deletedNews){
				if(news.getLocation().contains("�����ձ�")) {
				filePath = "assets/guangming.xml";
				}else if(news.getLocation().contains("�Ϸ��ձ�")) {
					filePath = "assets/nanfangdaily.xml";
				}else if(news.getLocation().contains("�Ĵ��ձ�")) {
					filePath = "assets/sichuan.xml";
				}
				dom4j.modifyXml(news,filePath);
			}
			for(News news : listData.classifiedNews){
				if(news.getLocation().contains("�����ձ�")) {
				filePath = "assets/guangming.xml";
				}else if(news.getLocation().contains("�Ϸ��ձ�")) {
					filePath = "assets/nanfangdaily.xml";
				}else if(news.getLocation().contains("�Ĵ��ձ�")) {
					filePath = "assets/sichuan.xml";
				}
				dom4j.modifyXml(news,filePath);
		  	}
		}
	}
}
