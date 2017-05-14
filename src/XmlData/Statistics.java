package XmlData;

import java.util.HashMap;
import java.util.Map;

import UI.ListData;

public class Statistics {
	private ListData listData;
	private Map<String,Integer> sum = new HashMap<String,Integer>();

	public int[] type = new int[4];
	private int[] theme = new int[9];
	private int[] source = new int[8];
	private int[] showing = new int[5];
	private int[] reason = new int[5];
	private int[] mainBody = new int[5];
	private int[] helpType = new int[5];
//	private int[] gender = new int[2];
	
	private int[] sexualAssaultGender = new int[2];
	private int[] violenceGender = new int[2];
	private int[] crimeGender = new int[2];
	private int[] positiveHealthGender = new int[2];
	
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
			temp = news.getDate().substring(0, 4);
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
			
			//统计报纸类型
			if(typeStr.equals("纯净新闻")){
				type[0]++;
			}else if(typeStr.equals("特稿特写")){
				type[1]++;
			}else if(typeStr.equals("评论")){
				type[2]++;
			}else if(typeStr.equals("其他")){
				type[3]++;
			}
			
			//统计报道主题
			if(themeStr.equals("帮助关爱")){
				theme[0]++;
			}else if(themeStr.equals("表彰鼓励")){
				theme[1]++;
			}else if(themeStr.equals("留守儿童努力向上")){
				theme[2]++;
			}else if(themeStr.equals("建议看法")){
				theme[3]++;
			}else if(themeStr.equals("打工父母艰难生活")){
				theme[4]++;
			}else if(themeStr.equals("留守儿童性侵")){
				if(genderStr.equals("男")){
					sexualAssaultGender[0]++;
				}else if(genderStr.equals("女")){
					sexualAssaultGender[1]++;
				}
				theme[5]++;
			}else if(themeStr.equals("留守儿童遭暴力")){
				if(genderStr.equals("男")){
					violenceGender[0]++;
				}else if(genderStr.equals("女")){
					violenceGender[1]++;
				}
				theme[6]++;
			}else if(themeStr.equals("留守儿童犯罪")){
				if(genderStr.equals("男")){
					crimeGender[0]++;
				}else if(genderStr.equals("女")){
					crimeGender[1]++;
				}
				theme[7]++;
			}else if(themeStr.equals("其他")){
				theme[8]++;
			}
			
			//统计新闻报道消息来源
			if(sourceStr.equals("记者")){
				source[0]++;
			}else if(sourceStr.equals("政府")){
				source[1]++;
			}else if(sourceStr.equals("企业")){
				source[2]++;
			}else if(sourceStr.equals("事业单位")){
				source[3]++;
			}else if(sourceStr.equals("公益单位")){
				source[4]++;
			}else if(sourceStr.equals("专家学者")){
				source[5]++;
			}else if(sourceStr.equals("政府领导")){
				source[6]++;
			}else if(sourceStr.equals("其他")){
				source[7]++;
			}
			
			//统计媒体呈现形象
			if(showingStr.equals("可怜悲惨的形象")){
				showing[0]++;
			}else if(showingStr.equals("沐恩幸福的形象")){
				showing[1]++;
			}else if(showingStr.equals("积极健康的形象")){
				if(genderStr.equals("男")){
					positiveHealthGender[0]++;
				}else if(genderStr.equals("女")){
					positiveHealthGender[1]++;
				}
				showing[2]++;
			}else if(showingStr.equals("问题儿童的形象")){
				showing[3]++;
			}else if(showingStr.equals("其他")){
				showing[4]++;
			}
			
			//统计农民工子女不能留在城市读书的原因
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
			
			//统计新闻主体
			if(mainBodyStr.equals("政府部门")){
				mainBody[0]++;
			}else if(mainBodyStr.equals("企业")){
				mainBody[1]++;
			}else if(mainBodyStr.equals("事业单位")){
				mainBody[2]++;
			}else if(mainBodyStr.equals("公益团体")){
				mainBody[3]++;
			}else if(mainBodyStr.equals("其他")){
				mainBody[4]++;
			}
			
			//统计新闻具体种类
			if(helpTypeStr.equals("单纯一次捐款捐物")){
				helpType[0]++;
			}else if(helpTypeStr.equals("旅游活动安排的项目之一")){
				helpType[1]++;
			}else if(helpTypeStr.equals("免费开放")){
				helpType[2]++;
			}else if(helpTypeStr.equals("设立长期资助项目")){
				helpType[3]++;
			}else if(helpTypeStr.equals("其他")){
				helpType[4]++;
			}
			
//			//统计性别
//			if(genderStr.equals("男")){
//				gender[0]++;
//			}else if(genderStr.equals("女")){
//				gender[1]++;
//			}
		}
	}

	public Map<String,Integer> getSum(){
		return sum;
	}
	
	public int[] getType(){
		return type;
	}
	
	public int[] getTheme(){
		return theme;
	}
	
	public int[] getSource(){
		return source;
	}
	
	public int[] getShowing(){
		return showing;
	}
	
	public int[] getReason(){
		return reason;
	}
	
	public int[] getMainBody(){
		return mainBody;
	}
	
	public int[] getHelpType(){
		return helpType;
	}
	
	public int[] getSexualAssaultGender(){
		return sexualAssaultGender;
	}
	
	public int[] getViolenceGender(){
		return violenceGender;
	}
	
	public int[] getCrimeGender(){
		return crimeGender;
	}
	
	public int[] getPositiveHealthGender(){
		return positiveHealthGender;
	}

}
