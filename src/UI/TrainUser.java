package UI;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import XmlData.News;
import XmlData.Tags;

public class TrainUser {
	private int count;
	private int all;
	
	public TrainUser(File[] f){
		count = 0;
		List<List<News>> listOfList = new ArrayList<>();
		for(int i = 0;i < 2;i++){
			List<News> list = new ArrayList<>();
			ListData.getInstance().importFile(f[i].getPath(),list);
			listOfList.add(list);
		}	
		if(listOfList.size()!=0){
			all = listOfList.get(0).size()*8;
			Iterator<News> iter1 = listOfList.get(0).iterator();
			Iterator<News> iter2 = listOfList.get(1).iterator();
			while(iter1.hasNext()&&iter2.hasNext()){
				Tags tags1 = iter1.next().getTags();
				Tags tags2 = iter2.next().getTags();
				if(tags1.getType().equals(tags2.getType())){
					count++;
				}
				if(tags1.getTheme().equals(tags2.getTheme())){
					count++;
				}
				if(tags1.getSource().equals(tags2.getSource())){
					count++;
				}
				if(tags1.getShowing().equals(tags2.getShowing())){
					count++;
				}
				if(tags1.getReason().equals(tags2.getReason())){
					count++;
				}
				if(tags1.getMainBody().equals(tags2.getMainBody())){
					count++;
				}
				if(tags1.getHelpType().equals(tags2.getHelpType())){
					count++;
				}
				if(tags1.getGender().equals(tags2.getGender())){
					count++;
				}
			}
		}
	}
	
	public double countOfSame(){
		return count*1.0/all;
	}
}
