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
		//��ʼ����ʮ���й����ض�ͯ���ű�������
		for(int i = 0;i < 10;i++){
			sum.put((i+2006)+"",0);
		}		
		//ͳ�ƽ�ʮ���й����ض�ͯ���ű�������
		String temp;
		for(News news : listData.newsList){
			temp = news.getDate().substring(0, 3);
			sum.put(temp, sum.get(temp)+1);
		}
		
		//ͳ��ũ����Ů�޷����ж���ԭ��
		for(News news : listData.classifiedNews){
			typeStr = news.getTags().getType();
			themeStr = news.getTags().getTheme();
			sourceStr = news.getTags().getSource();
			showingStr = news.getTags().getShowing();
			reasonStr = news.getTags().getReason();
			mainBodyStr = news.getTags().getMainBody();
			helpTypeStr = news.getTags().getHelpType();
			genderStr = news.getTags().getGender();
			if(reasonStr.equals("�ޱ��ػ������빫��ѧУ")){
				reason[0]++;
			}else if(reasonStr.equals("˽��ѧУѧ�Ѹ�")){
				reason[1]++;
			}else if(reasonStr.equals("Խ��Խ��С��˽��ѧУ��ȡ����ѧ�ʸ�")){
				reason[2]++;
			}else if(reasonStr.equals("˽��ѧУ��ѧ����û����")){
				reason[3]++;
			}else if(reasonStr.equals("����")){
				reason[4]++;
			}
		}
	}
//	if(str.equals("��������")||str.equals("�ظ���д")||str.equals("����")){
//		newsList.get(position).setTagIts("true");
//		tags.setType(str);
//	}else if(str.equals("����")||str.equals("����")||str.equals("��ҵ")||
//			str.equals("����")||str.equals("��ҵ��λ")||str.equals("���浥λ")||
//			str.equals("ר��ѧ��")||str.equals("�����쵼")){
//		newsList.get(position).setTagIts("true");
//		tags.setSource(str);
//	}else if(str.equals("�������ҵ�����")||str.equals("����Ҹ�������")||
//			str.equals("��������������")||str.equals("�����ͯ������")){
//		newsList.get(position).setTagIts("true");
//		tags.setShowing(str);
//	}else if(str.equals("�ޱ��ػ������빫��ѧУ")||str.equals("˽��ѧУѧ�Ѹ�")||
//			str.equals("Խ��Խ��С��˽��ѧУ��ȡ����ѧ�ʸ�")||
//			str.equals("˽��ѧУ��ѧ����û����")){
//		newsList.get(position).setTagIts("true");
//		tags.setReason(str);
//	}
	public Map<String,Integer> getReason(){
		return sum;
	}
}
