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
		//��ʼ����ʮ���й����ض�ͯ���ű�������
		for(int i = 0;i < 10;i++){
			sum.put((i+2006)+"",0);
		}		
		//ͳ�ƽ�ʮ���й����ض�ͯ���ű�������
		String temp;
		for(News news : listData.newsList){
			temp = news.getDate().substring(0, 4);
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
			
			//ͳ�Ʊ�ֽ����
			if(typeStr.equals("��������")){
				type[0]++;
			}else if(typeStr.equals("�ظ���д")){
				type[1]++;
			}else if(typeStr.equals("����")){
				type[2]++;
			}else if(typeStr.equals("����")){
				type[3]++;
			}
			
			//ͳ�Ʊ�������
			if(themeStr.equals("�����ذ�")){
				theme[0]++;
			}else if(themeStr.equals("���ù���")){
				theme[1]++;
			}else if(themeStr.equals("���ض�ͯŬ������")){
				theme[2]++;
			}else if(themeStr.equals("���鿴��")){
				theme[3]++;
			}else if(themeStr.equals("�򹤸�ĸ��������")){
				theme[4]++;
			}else if(themeStr.equals("���ض�ͯ����")){
				if(genderStr.equals("��")){
					sexualAssaultGender[0]++;
				}else if(genderStr.equals("Ů")){
					sexualAssaultGender[1]++;
				}
				theme[5]++;
			}else if(themeStr.equals("���ض�ͯ�Ⱪ��")){
				if(genderStr.equals("��")){
					violenceGender[0]++;
				}else if(genderStr.equals("Ů")){
					violenceGender[1]++;
				}
				theme[6]++;
			}else if(themeStr.equals("���ض�ͯ����")){
				if(genderStr.equals("��")){
					crimeGender[0]++;
				}else if(genderStr.equals("Ů")){
					crimeGender[1]++;
				}
				theme[7]++;
			}else if(themeStr.equals("����")){
				theme[8]++;
			}
			
			//ͳ�����ű�����Ϣ��Դ
			if(sourceStr.equals("����")){
				source[0]++;
			}else if(sourceStr.equals("����")){
				source[1]++;
			}else if(sourceStr.equals("��ҵ")){
				source[2]++;
			}else if(sourceStr.equals("��ҵ��λ")){
				source[3]++;
			}else if(sourceStr.equals("���浥λ")){
				source[4]++;
			}else if(sourceStr.equals("ר��ѧ��")){
				source[5]++;
			}else if(sourceStr.equals("�����쵼")){
				source[6]++;
			}else if(sourceStr.equals("����")){
				source[7]++;
			}
			
			//ͳ��ý���������
			if(showingStr.equals("�������ҵ�����")){
				showing[0]++;
			}else if(showingStr.equals("����Ҹ�������")){
				showing[1]++;
			}else if(showingStr.equals("��������������")){
				if(genderStr.equals("��")){
					positiveHealthGender[0]++;
				}else if(genderStr.equals("Ů")){
					positiveHealthGender[1]++;
				}
				showing[2]++;
			}else if(showingStr.equals("�����ͯ������")){
				showing[3]++;
			}else if(showingStr.equals("����")){
				showing[4]++;
			}
			
			//ͳ��ũ����Ů�������ڳ��ж����ԭ��
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
			
			//ͳ����������
			if(mainBodyStr.equals("��������")){
				mainBody[0]++;
			}else if(mainBodyStr.equals("��ҵ")){
				mainBody[1]++;
			}else if(mainBodyStr.equals("��ҵ��λ")){
				mainBody[2]++;
			}else if(mainBodyStr.equals("��������")){
				mainBody[3]++;
			}else if(mainBodyStr.equals("����")){
				mainBody[4]++;
			}
			
			//ͳ�����ž�������
			if(helpTypeStr.equals("����һ�ξ�����")){
				helpType[0]++;
			}else if(helpTypeStr.equals("���λ���ŵ���Ŀ֮һ")){
				helpType[1]++;
			}else if(helpTypeStr.equals("��ѿ���")){
				helpType[2]++;
			}else if(helpTypeStr.equals("��������������Ŀ")){
				helpType[3]++;
			}else if(helpTypeStr.equals("����")){
				helpType[4]++;
			}
			
//			//ͳ���Ա�
//			if(genderStr.equals("��")){
//				gender[0]++;
//			}else if(genderStr.equals("Ů")){
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
