package UI;

import XmlData.*;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Choice;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.JRadioButton;

public class NewsContent extends JFrame implements ActionListener{
	public static Logger logger = LogManager.getLogger(NewsContent.class.getName());
	public JPanel contentPane;
	private JTextArea textArea;
	private JButton browse;
	private JScrollPane mainBody;
	private List<News> newsList;
	private int position;
	private JButton showExternalNews = new JButton("加载外部新闻");
	private Tags tags;
	private Choice choice;
	private Choice choice_1;
	private Choice choice_2;
	
	

	public NewsContent(List<News> newsList,int position) {
		this.newsList = newsList;
		this.position = position;
		tags = new Tags();
		initialize();
	}
	
	//更新新闻显示内容
	public void showNewsDetails(News news){
		
		if (news.getEncodedContent().equals(""))
		{
			showExternalNews.setEnabled(true);
		}
		else
		{
	
			showExternalNews.setEnabled(false);
			textArea.setText(news.getTitle()+"\n\n"+news.getEncodedContent());
		    textArea.setLineWrap(true);                 //激活自动换行功能 
		    textArea.setWrapStyleWord(true);            // 激活断行不断字功能
			Font font = new Font("宋体",Font.BOLD,20);
			textArea.setFont(font);
			textArea.setEditable(false);
			textArea.setCaretPosition(0);		//设置光标位置为首行
		}
	}
	
	//初始化界面和按钮
	public void initialize(){	
		setTitle("新闻内容");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		
		
		//显示新闻内容的版块
		textArea=new JTextArea(newsList.get(position).getTitle(),20,43);
		textArea.setEditable(false);
		showNewsDetails(newsList.get(position));
		contentPane.setLayout(null);
		
		mainBody = new JScrollPane(textArea);
		mainBody.setBounds(5, 5, 600, 600);
		
		contentPane.add(mainBody);
				
		//上一篇按钮
		JButton button = new JButton("上一篇");
		button.setBounds(631, 22, 93, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(position != 0){
					logger.info("点击上一篇打开新闻--"+newsList.get(position-1).getTitle());
					showNewsDetails(newsList.get(--position));
				}
			}
		});
		contentPane.add(button);
		
		//首页按钮
		JButton button_1 = new JButton("首页");
		button_1.setBounds(746, 22, 93, 23);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				logger.info("返回首页");
				setVisible(false);
				dispose();
				LeftBehindChildren window = new LeftBehindChildren();
				window.mainFrame.setVisible(true);				
			}
		});
		contentPane.add(button_1);
		
		//下一篇按钮
		JButton button_2 = new JButton("下一篇");
		button_2.setBounds(858, 22, 93, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(position != newsList.size()-1){
					logger.info("点击下一篇打开新闻--"+newsList.get(position+1).getTitle());
					showNewsDetails(newsList.get(++position));
				}
			}
		});
		contentPane.add(button_2);
				
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(611, 68, 400, 2);
		contentPane.add(scrollPane_1);
		
		Label label = new Label("为此文章选择标签");
		label.setBounds(611, 51, 103, 23);
		contentPane.add(label);
		
		//显示报纸类别（三种报纸）
		Label label_1 = new Label("报纸类别");
		label_1.setBounds(621, 80, 51, 23);
		label_1.setBackground(Color.WHITE);
		contentPane.add(label_1);
		Label location = new Label(newsList.get(position).getLocation());
		location.setBounds(678, 80, 146, 23);
		contentPane.add(location);
		
		//显示报纸类型
		Label label_3 = new Label("报纸类型");
		label_3.setBounds(621, 116, 51, 23);
		label_3.setBackground(Color.WHITE);
		contentPane.add(label_3);
		
		//显示报道主题
		Label label_2 = new Label("报道主题");
		label_2.setBounds(621, 155, 51, 23);
		label_2.setBackground(Color.WHITE);
		contentPane.add(label_2);
		
		
		choice = new Choice();
		choice.setBounds(678, 242, 62, 21);
		contentPane.add(choice);
		choice.add("新闻主体");
		choice.add("政府部门");
		choice.add("企业");
		choice.add("事业单位");
		choice.add("公益团体");
		choice.add("个人");
		choice.select(0);
		choice.setEnabled(false);
		choice.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				if(e.getItem()!="新闻主体"){
					tags.setMainBody(e.getItem().toString());
				}else{
					tags.setMainBody("");
				}
			}			
		});
		
		choice_1 = new Choice();
		choice_1.setBounds(746, 242, 62, 21);
		contentPane.add(choice_1);
		choice_1.add("具体种类");
		choice_1.add("单纯一次捐款捐物");
		choice_1.add("旅游活动安排的项目之一");
		choice_1.add("免费开放");
		choice_1.add("设立长期资助项目");
		choice_1.add("其他");
		choice_1.select(0);
		choice_1.setEnabled(false);
		choice_1.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				if(e.getItem()!="具体种类"){
					tags.setHelpType(e.getItem().toString());
				}else{
					tags.setHelpType("");
				}
			}
		});
		
		choice_2 = new Choice();
		choice_2.setBounds(814, 242, 62, 21);
		contentPane.add(choice_2);
		choice_2.setEnabled(false);
		choice_2.add("性别");
		choice_2.add("男");
		choice_2.add("女");
		choice_2.select(0);
		choice_2.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO 自动生成的方法存根
				if(e.getItem()!="性别"){
					tags.setGender(e.getItem().toString());
				}else{
					tags.setGender("");
				}
			}
		});
		
		Label label_4 = new Label("新闻报道消息来源");
		label_4.setBounds(621, 278, 103, 23);
		label_4.setBackground(Color.WHITE);
		contentPane.add(label_4);
		
		Label label_5 = new Label("媒体形象呈现");
		label_5.setBounds(621, 339, 79, 23);
		label_5.setBackground(Color.WHITE);
		contentPane.add(label_5);
		
		Label label_6 = new Label("农民工子女不能留在城市读书的原因");
		label_6.setBounds(621, 403, 195, 23);
		label_6.setBackground(Color.WHITE);
		contentPane.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(615, 531, 400, 2);
		contentPane.add(scrollPane_2);
		
		
		//点击打开外部新闻
		showExternalNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = newsList.get(position).getTrueUrl();
				BareBonesBrowserLaunch.openURL(url);
			}
		});
		showExternalNews.setBounds(706, 542, 118, 23);
		contentPane.add(showExternalNews);
		
		JRadioButton type1 = new JRadioButton("纯净新闻");
		type1.setBounds(678, 116, 79, 23);
		contentPane.add(type1);
		
		JRadioButton type2 = new JRadioButton("特稿特写");
		type2.setBounds(759, 116, 73, 23);
		contentPane.add(type2);
		
		JRadioButton type3 = new JRadioButton("评论");
		type3.setBounds(834, 116, 51, 23);
		contentPane.add(type3);
		
		JRadioButton type4 = new JRadioButton("其他");
		type4.setBounds(889, 116, 62, 23);
		contentPane.add(type4);
		
		ButtonGroup typeGroup = new ButtonGroup();
		typeGroup.add(type1);
		typeGroup.add(type2);
		typeGroup.add(type3);
		typeGroup.add(type4);		
		type1.addActionListener(this);
		type2.addActionListener(this);		
		type3.addActionListener(this);
		type4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tags.setType(type4.getActionCommand());
			}
		});
		
		
		JRadioButton theme1 = new JRadioButton("帮助关爱");
		theme1.setFont(new Font("宋体", Font.PLAIN, 11));
		theme1.setBounds(678, 155, 79, 23);
		contentPane.add(theme1);
		theme1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						choice.setEnabled(true);
						choice_1.setEnabled(true);
						choice_2.setEnabled(false);
						tags.setTheme(theme1.getActionCommand());
						tags.setGender("");
					}
				});
		
		JRadioButton theme2 = new JRadioButton("表彰鼓励");
		theme2.setFont(new Font("宋体", Font.PLAIN, 11));
		theme2.setBounds(759, 155, 79, 23);
		contentPane.add(theme2);
		theme2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						choice.setEnabled(true);
						choice_1.setEnabled(false);
						choice_2.setEnabled(false);
						tags.setTheme(theme2.getActionCommand());
						tags.setHelpType("");
						tags.setGender("");
					}
					
				});
		
		JRadioButton theme3 = new JRadioButton("留守儿童努力向上");
		theme3.setFont(new Font("宋体", Font.PLAIN, 11));
		theme3.setBounds(843, 155, 121, 23);
		contentPane.add(theme3);
		theme3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choice.setEnabled(false);
				choice_1.setEnabled(false);
				choice_2.setEnabled(false);
				tags.setTheme(theme3.getActionCommand());
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
			}
			
		});
		
		JRadioButton theme4 = new JRadioButton("建议看法");
		theme4.setFont(new Font("宋体", Font.PLAIN, 11));
		theme4.setBounds(678, 184, 79, 23);
		contentPane.add(theme4);
		theme4.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choice.setEnabled(false);
				choice_1.setEnabled(false);
				choice_2.setEnabled(false);
				tags.setTheme(theme4.getActionCommand());
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
			}
			
		});
		
		JRadioButton theme5 = new JRadioButton("打工父母艰难生活");
		theme5.setFont(new Font("宋体", Font.PLAIN, 11));
		theme5.setBounds(759, 184, 113, 23);
		contentPane.add(theme5);
		theme5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choice.setEnabled(false);
				choice_1.setEnabled(false);
				choice_2.setEnabled(false);
				tags.setTheme(theme5.getActionCommand());
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
			}
			
		});
		
		JRadioButton theme6 = new JRadioButton("留守儿童性侵");
		theme6.setFont(new Font("宋体", Font.PLAIN, 11));
		theme6.setBounds(875, 184, 93, 23);
		contentPane.add(theme6);
		theme6.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choice.setEnabled(false);
				choice_1.setEnabled(false);
				choice_2.setEnabled(true);
				tags.setTheme(theme6.getActionCommand());
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
			}
			
		});
		
		JRadioButton theme7 = new JRadioButton("留守儿童遭暴力");
		theme7.setFont(new Font("宋体", Font.PLAIN, 11));
		theme7.setBounds(678, 213, 103, 23);
		contentPane.add(theme7);
		theme7.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choice.setEnabled(false);
				choice_1.setEnabled(false);
				choice_2.setEnabled(true);
				tags.setTheme(theme7.getActionCommand());
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
			}
			
		});
		
		JRadioButton theme8 = new JRadioButton("留守儿童犯罪");
		theme8.setFont(new Font("宋体", Font.PLAIN, 11));
		theme8.setBounds(783, 213, 93, 23);
		contentPane.add(theme8);
		theme8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choice.setEnabled(false);
				choice_1.setEnabled(false);
				choice_2.setEnabled(true);
				tags.setTheme(theme8.getActionCommand());
				tags.setMainBody("");
				tags.setHelpType("");
			}			
		});
		
		JRadioButton theme9 = new JRadioButton("其他");
		theme9.setFont(new Font("宋体", Font.PLAIN, 11));
		theme9.setBounds(882, 212, 51, 23);
		contentPane.add(theme9);
		theme9.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choice.setEnabled(false);
				choice_1.setEnabled(false);
				choice_2.setEnabled(false);
				tags.setTheme(theme9.getActionCommand());
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
			}
			
		});
		
		ButtonGroup themeGroup = new ButtonGroup();
		themeGroup.add(theme1);
		themeGroup.add(theme2);
		themeGroup.add(theme3);
		themeGroup.add(theme4);
		themeGroup.add(theme5);
		themeGroup.add(theme6);
		themeGroup.add(theme7);
		themeGroup.add(theme8);
		themeGroup.add(theme9);
		
		
		JRadioButton source1 = new JRadioButton("记者");
		source1.setFont(new Font("宋体", Font.PLAIN, 11));
		source1.setBounds(728, 278, 51, 23);
		contentPane.add(source1);
		source1.addActionListener(this);
		
		JRadioButton source2 = new JRadioButton("政府");
		source2.setFont(new Font("宋体", Font.PLAIN, 11));
		source2.setBounds(781, 278, 47, 23);
		contentPane.add(source2);
		source2.addActionListener(this);

		JRadioButton source3 = new JRadioButton("企业");
		source3.setFont(new Font("宋体", Font.PLAIN, 11));
		source3.setBounds(830, 278, 51, 23);
		contentPane.add(source3);
		source3.addActionListener(this);
		
		JRadioButton source4 = new JRadioButton("事业单位");
		source4.setFont(new Font("宋体", Font.PLAIN, 11));
		source4.setBounds(882, 278, 73, 23);
		contentPane.add(source4);
		source4.addActionListener(this);
		
		JRadioButton source5 = new JRadioButton("公益单位");
		source5.setFont(new Font("宋体", Font.PLAIN, 11));
		source5.setBounds(670, 307, 73, 23);
		contentPane.add(source5);
		source5.addActionListener(this);
		
		JRadioButton source6 = new JRadioButton("专家学者");
		source6.setFont(new Font("宋体", Font.PLAIN, 11));
		source6.setBounds(746, 307, 73, 23);
		contentPane.add(source6);
		source6.addActionListener(this);
		
		JRadioButton source7 = new JRadioButton("政府领导");
		source7.setFont(new Font("宋体", Font.PLAIN, 11));
		source7.setBounds(823, 307, 73, 23);
		contentPane.add(source7);
		source7.addActionListener(this);
		
		JRadioButton source8 = new JRadioButton("其他");
		source8.setFont(new Font("宋体", Font.PLAIN, 11));
		source8.setBounds(906, 306, 51, 23);
		contentPane.add(source8);
		source8.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tags.setSource(e.getActionCommand());
			}			
		});
		
		ButtonGroup sourceGroup = new ButtonGroup();
		sourceGroup.add(source1);
		sourceGroup.add(source2);
		sourceGroup.add(source3);
		sourceGroup.add(source4);
		sourceGroup.add(source5);
		sourceGroup.add(source6);
		sourceGroup.add(source7);
		sourceGroup.add(source8);
		  	
		JRadioButton mediaImage1 = new JRadioButton("可怜悲惨的形象");
		mediaImage1.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage1.setBounds(703, 339, 103, 23);
		contentPane.add(mediaImage1);
		mediaImage1.addActionListener(this);
		
		JRadioButton mediaImage2 = new JRadioButton("沐恩幸福的形象");
		mediaImage2.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage2.setBounds(830, 339, 121, 23);
		contentPane.add(mediaImage2);
		mediaImage2.addActionListener(this);
		
		JRadioButton mediaImage3 = new JRadioButton("积极健康的形象");
		mediaImage3.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage3.setBounds(650, 368, 113, 23);
		contentPane.add(mediaImage3);
		mediaImage3.addActionListener(this);
		
		JRadioButton mediaImage4 = new JRadioButton("问题儿童的形象");
		mediaImage4.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage4.setBounds(768, 368, 103, 23);
		contentPane.add(mediaImage4);
		mediaImage4.addActionListener(this);
		
		JRadioButton mediaImage5 = new JRadioButton("其他");
		mediaImage5.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage5.setBounds(875, 367, 62, 23);
		contentPane.add(mediaImage5);
		mediaImage5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tags.setShowing(e.getActionCommand());
			}			
		});
		
		ButtonGroup mediaImageGroup = new ButtonGroup();
		mediaImageGroup.add(mediaImage1);
		mediaImageGroup.add(mediaImage2);
		mediaImageGroup.add(mediaImage3);
		mediaImageGroup.add(mediaImage4);
		mediaImageGroup.add(mediaImage5);
		
		JRadioButton reason1 = new JRadioButton("无本地户籍难入公立学校");
		reason1.setFont(new Font("宋体", Font.PLAIN, 11));
		reason1.setBounds(674, 430, 150, 23);
		contentPane.add(reason1);
		reason1.addActionListener(this);
		
		JRadioButton reason2 = new JRadioButton("私立学校学费高");
		reason2.setFont(new Font("宋体", Font.PLAIN, 11));
		reason2.setBounds(830, 429, 103, 23);
		contentPane.add(reason2);
		reason2.addActionListener(this);
		
		JRadioButton reason3 = new JRadioButton("越来越多小型私立学校被取消办学资格");
		reason3.setFont(new Font("宋体", Font.PLAIN, 11));
		reason3.setBounds(678, 459, 218, 23);
		contentPane.add(reason3);
		reason3.addActionListener(this);
		
		JRadioButton reason4 = new JRadioButton("私立学校办学质量没保障");
		reason4.setFont(new Font("宋体", Font.PLAIN, 11));
		reason4.setBounds(678, 488, 146, 23);
		contentPane.add(reason4);
		reason4.addActionListener(this);
		
		JRadioButton reason5 = new JRadioButton("其他");
		reason5.setFont(new Font("宋体", Font.PLAIN, 11));
		reason5.setBounds(834, 488, 62, 23);
		contentPane.add(reason5);
		reason5.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tags.setReason(e.getActionCommand());
			}			
		});
		
		ButtonGroup reasonGroup = new ButtonGroup();
		reasonGroup.add(reason1);
		reasonGroup.add(reason2);
		reasonGroup.add(reason3);
		reasonGroup.add(reason4);
		reasonGroup.add(reason5);		
		JButton finish = new JButton("完成");
		finish.setBounds(858, 542, 93, 23);
		contentPane.add(finish);
		
//		if(tags.getType().isEmpty()||tags.getTheme().isEmpty()||tags.getSource().isEmpty()||
//				tags.getShowing().isEmpty()||tags.getReason().isEmpty()){
//			finish.setEnabled(false);
//		}else{
//			finish.setEnabled(true);
//		}
		finish.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根				
//				System.out.println(tags.getType() + tags.getTheme() + tags.getMainBody() + tags.getGender()
//				+tags.getHelpType()+tags.getShowing()+tags.getReason()+tags.getSource());				
				Dom4j dom4j = new Dom4j();
				newsList.get(position).setTags(tags);
				newsList.get(position).setTagIts("true");
				dom4j.modifyXml(newsList.get(position));
				dispose();
				ListData listData = new ListData();
				ClassifiedNewsContent newsContent = 
						new ClassifiedNewsContent(listData.classifiedNews,
								listData.findPosition(listData.classifiedNews, newsList.get(position)));
				newsContent.setVisible(true);
			}			
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String str = e.getActionCommand();
		if(str.equals("纯净新闻")||str.equals("特稿特写")||str.equals("评论")){
			tags.setType(str);
		}else if(str.equals("记者")||str.equals("政府")||str.equals("企业")||
				str.equals("政府")||str.equals("事业单位")||str.equals("公益单位")||
				str.equals("专家学者")||str.equals("政府领导")){
			tags.setSource(str);
		}else if(str.equals("可怜悲惨的形象")||str.equals("沐恩幸福的形象")||
				str.equals("积极健康的形象")||str.equals("问题儿童的形象")){
			tags.setShowing(str);
		}else if(str.equals("无本地户籍难入公立学校")||str.equals("私立学校学费高")||
				str.equals("越来越多小型私立学校被取消办学资格")||
				str.equals("私立学校办学质量没保障")){
			tags.setReason(str);
		}		
	}	
}

