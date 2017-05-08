package UI;

import java.util.ArrayList;
import java.util.List;

import XmlData.Dom4j;
import XmlData.News;

public class ListData {
	public List<News> newsList = new ArrayList<>();
	public List<News> classifiedNews = new ArrayList<>();
	public List<News> notClassifiedNews = new ArrayList<>();
	public List<String> classifiedTitle = new ArrayList<>();
	public List<String> notClassifiedTitle = new ArrayList<>();
	public List<News> deletedNews = new ArrayList<>();
	public List<String> deletedTitle = new ArrayList<>();
	
	public ListData(){
		init();
	}
	
	public void init(){
		Dom4j dom4j = new Dom4j();
		dom4j.initXml("assets/guangming.xml");
		dom4j.initXml("assets/nanfangdaily.xml");
		dom4j.initXml("assets/sichuan.xml");
		dom4j.parserXml("assets/guangming.xml",newsList);
		dom4j.parserXml("assets/nanfangdaily.xml",newsList);
		dom4j.parserXml("assets/sichuan.xml",newsList);
		for(News news : newsList){
			if(!news.getIsDeleted().equals("true")){
				if(!news.getTagIts().equals("true")){
					notClassifiedNews.add(news);
					notClassifiedTitle.add(news.getTitle());
				}else{
					classifiedNews.add(news);
					classifiedTitle.add(news.getTitle());
				}
			}else{
				deletedNews.add(news);
				deletedTitle.add(news.getTitle());
			}
		}
	}
	public int findPosition(List<News> newsList,News news){
		int position = 0;
		for(;position < newsList.size();position++){
			if(newsList.get(position).getID().equals(news.getID())){
				return position;
			}
		}
		return -1;
	}
}
