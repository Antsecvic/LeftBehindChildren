package Util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Object.ListData;
import Object.News;
import XmlData.Dom4j;

public class MergeFile {
	private File f;
	private String filePath;
	public MergeFile(String filePath,File f){
		System.out.print(f.getPath());
		System.out.print(filePath);
		if(f.getPath().contains("LeftBehindChildren\\assets\\guangming.xml")){
			this.filePath = "assets/guangming.xml";
			this.f = new File(filePath);
		}else if(f.getPath().contains("LeftBehindChildren\\assets\\nanfangdaily.xml")){
			this.filePath = "assets/nanfangdaily.xml";
			this.f = new File(filePath);
		}else if(f.getPath().contains("LeftBehindChildren\\assets\\sichuan.xml")){
			this.filePath = "assets/sichuan.xml";
			this.f = new File(filePath);
		}else{
			this.f = f;
			this.filePath = filePath;
		}
	}
	public boolean merge(){
		if(f!=null&&filePath!=null){
			List<News> list = new ArrayList<>();
			ListData.getInstance().importFile(f.getPath(),list);
			List<News> listNews = new ArrayList<>();
			ListData.getInstance().importFile(filePath,listNews);
			Iterator<News> iter1 = listNews.iterator();
			Iterator<News> iter2 = list.iterator();
			if(listNews.size()==list.size()){
				Dom4j dom4j = new Dom4j();
				while(iter1.hasNext()&&iter2.hasNext()){
					News news1 = iter1.next();
					News news2 = iter2.next();
					if(!news1.getTagIts().equals("true")&&news2.getTagIts().equals("true")){							
						news1.setTagIts("true");
						news1.setTags(news2.getTags());
						dom4j.modifyXml(news1, filePath);
					}
				}
				f.delete();
				return true;
			}else{
				return false;
			}	
		}				
		return false;
	}
	public String filepath(){
		return filePath;		
	}
}
