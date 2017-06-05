package Object;

import java.util.ArrayList;
import java.util.List;

import XmlData.Dom4j;

public class ListData {
	public List<News> newsList = new ArrayList<>();
	public List<News> classifiedNews = new ArrayList<>();
	public List<News> notClassifiedNews = new ArrayList<>();
	public List<String> classifiedTitle = new ArrayList<>();
	public List<String> notClassifiedTitle = new ArrayList<>();
	public List<News> deletedNews = new ArrayList<>();
	public List<String> deletedTitle = new ArrayList<>();
	private static ListData listData = new ListData();
	
	private ListData(){
//		init();
	}
	
	public static ListData getInstance(){
		return listData;
	}
	
	public void importFile(String filename){
		clear();
		Dom4j dom4j = new Dom4j();
		dom4j.initXml(filename);
		dom4j.parserXml(filename,newsList);
		
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
	public void importFile(String filename,List<News> list){
		Dom4j dom4j = new Dom4j();
		dom4j.initXml(filename);
		dom4j.parserXml(filename,list);
	}
	public void clear(){
		newsList.clear();
		notClassifiedNews.clear();
		notClassifiedTitle.clear();
		classifiedNews.clear();
		classifiedTitle.clear();
		deletedNews.clear();
		deletedTitle.clear();
	}
	
	public void importAllFile(){
		clear();
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
	public int findPosition(List<News> newsList,String id){
		int position = 0;
		for(;position < newsList.size();position++){
			if(newsList.get(position).getID().equals(id)){
				return position;
			}
		}
		return 0;
	}
}
