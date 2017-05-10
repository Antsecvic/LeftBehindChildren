package XmlData;

import java.util.HashMap;
import java.util.Map;

import UI.ListData;

public class Statistics {
	private ListData listData;
	private Map<String,Integer> sum = new HashMap<String,Integer>();
	private int[] reason = new int[5];
	private String typeStr;
	private String themeStr;
	private String sourceStr;
	private String showingStr;
	private String reasonStr;
	private String mainBodyStr;
	private String helpTypeStr;
	private String genderStr;
	public Statistics(){
		listData = ListData.getInstance();
		//初始化近十年有关留守儿童新闻报道数量
		for(int i = 0;i < 10;i++){
			sum.put((i+2006)+"",0);
		}		
		//统计近十年有关留守儿童新闻报道数量
		String temp;
		for(News news : listData.newsList){
			temp = news.getDate().substring(0, 3);
			sum.put(temp, sum.get(temp)+1);
		}
		
		//统计农民工子女无法城市读书原因
		for(News news : listData.classifiedNews){
			typeStr = news.getTags().getType();
			themeStr = news.getTags().getTheme();
			sourceStr = news.getTags().getSource();
			showingStr = news.getTags().getShowing();
			reasonStr = news.getTags().getReason();
			mainBodyStr = news.getTags().getMainBody();
			helpTypeStr = news.getTags().getHelpType();
			genderStr = news.getTags().getGender();
			if(reasonStr.equals("无本地户籍难入公立学校")){
				reason[0]++;
			}else if(reasonStr.equals("私立学校学费高")){
				reason[1]++;
			}else if(reasonStr.equals("越来越多小型私立学校被取消办学资格")){
				reason[2]++;
			}else if(reasonStr.equals("私立学校办学质量没保障")){
				reason[3]++;
			}else if(reasonStr.equals("其他")){
				reason[4]++;
			}
		}
	}
//	if(str.equals("纯净新闻")||str.equals("特稿特写")||str.equals("评论")){
//		newsList.get(position).setTagIts("true");
//		tags.setType(str);
//	}else if(str.equals("记者")||str.equals("政府")||str.equals("企业")||
//			str.equals("政府")||str.equals("事业单位")||str.equals("公益单位")||
//			str.equals("专家学者")||str.equals("政府领导")){
//		newsList.get(position).setTagIts("true");
//		tags.setSource(str);
//	}else if(str.equals("可怜悲惨的形象")||str.equals("沐恩幸福的形象")||
//			str.equals("积极健康的形象")||str.equals("问题儿童的形象")){
//		newsList.get(position).setTagIts("true");
//		tags.setShowing(str);
//	}else if(str.equals("无本地户籍难入公立学校")||str.equals("私立学校学费高")||
//			str.equals("越来越多小型私立学校被取消办学资格")||
//			str.equals("私立学校办学质量没保障")){
//		newsList.get(position).setTagIts("true");
//		tags.setReason(str);
//	}
	public Map<String,Integer> getReason(){
		return sum;
	}
}
