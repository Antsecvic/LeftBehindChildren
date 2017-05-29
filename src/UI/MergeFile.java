package UI;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import XmlData.Dom4j;
import XmlData.News;

public class MergeFile {
	private File[] f;
	private List<List<News>> listOfList = new ArrayList<>();
	public MergeFile(File[] f){
		this.f = f;
	}
	public boolean merge(){
		if(f.length>=2){
			for(int i = 0;i < 2;i++){
				List<News> list = new ArrayList<>();
				ListData.getInstance().importFile(f[i].getPath(),list);
				listOfList.add(list);
			}	
			if(listOfList.size()!=0){
				Iterator<News> iter1 = listOfList.get(0).iterator();
				Iterator<News> iter2 = listOfList.get(1).iterator();
				if(listOfList.get(0).size()==listOfList.get(1).size()){
					Dom4j dom4j = new Dom4j();
					while(iter1.hasNext()&&iter2.hasNext()){
						News news1 = iter1.next();
						News news2 = iter2.next();
						if(!news1.getTagIts().equals("true")&&news2.getTagIts().equals("true")){							
							news1.setTagIts("true");
							news1.setTags(news2.getTags());
							dom4j.modifyXml(news1, f[0].getPath());
						}
					}
					f[1].delete();
					return true;
				}else{
					return false;
				}
			}			
		}
		return false;
	}
}
