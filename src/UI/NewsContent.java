package UI;

import XmlData.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Button;
import java.awt.Choice;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.JEditorPane;
import javax.swing.JRadioButton;

public class NewsContent extends JFrame{
	public static Logger logger = LogManager.getLogger(NewsContent.class.getName());
	public JPanel contentPane;
	private JTextArea textArea;
	private JButton browse;
	private JScrollPane mainBody;
	private List<News> newsList;
	private int position;
	private JButton showExternalNews = new JButton("加载外部新闻");
	
	private Choice choice;
	private Choice choice_1;
	private Choice choice_2;
	
	

	public NewsContent(List<News> newsList,int position) {
		this.newsList = newsList;
		this.position = position;
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
		
		Label label_1 = new Label("报纸类别");
		label_1.setBounds(621, 80, 51, 23);
		label_1.setBackground(Color.WHITE);
		contentPane.add(label_1);
		
		Label label_3 = new Label("报纸类型");
		label_3.setBounds(621, 116, 51, 23);
		label_3.setBackground(Color.WHITE);
		contentPane.add(label_3);
		
		Button button_3 = new Button("光明日报（党一级日报）");
		button_3.setBounds(678, 80, 146, 23);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_3.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_3);
		
		Label label_2 = new Label("报道主题");
		label_2.setBounds(621, 155, 51, 23);
		label_2.setBackground(Color.WHITE);
		contentPane.add(label_2);
		
		Label label_4 = new Label("新闻报道消息来源");
		label_4.setBounds(621, 278, 103, 23);
		label_4.setBackground(Color.WHITE);
		contentPane.add(label_4);
		
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
		
		choice_2 = new Choice();
		choice_2.setBounds(814, 242, 62, 21);
		contentPane.add(choice_2);
		choice_2.setEnabled(false);
		
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
		
		JButton button_35 = new JButton("完成");
		button_35.setBounds(858, 542, 93, 23);
		contentPane.add(button_35);
		
		
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
		
		type1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});

		type2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("香香");
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
		
		JRadioButton resoure1 = new JRadioButton("记者");
		resoure1.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure1.setBounds(728, 278, 51, 23);
		contentPane.add(resoure1);
		
		JRadioButton resoure2 = new JRadioButton("政府");
		resoure2.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure2.setBounds(781, 278, 47, 23);
		contentPane.add(resoure2);
		
		JRadioButton resoure3 = new JRadioButton("企业");
		resoure3.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure3.setBounds(830, 278, 51, 23);
		contentPane.add(resoure3);
		
		JRadioButton resoure4 = new JRadioButton("事业单位");
		resoure4.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure4.setBounds(882, 278, 73, 23);
		contentPane.add(resoure4);
		
		JRadioButton resoure5 = new JRadioButton("公益单位");
		resoure5.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure5.setBounds(670, 307, 73, 23);
		contentPane.add(resoure5);
		
		JRadioButton resoure6 = new JRadioButton("专家学者");
		resoure6.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure6.setBounds(746, 307, 73, 23);
		contentPane.add(resoure6);
		
		JRadioButton resoure7 = new JRadioButton("政府领导");
		resoure7.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure7.setBounds(823, 307, 73, 23);
		contentPane.add(resoure7);
		
		JRadioButton resoure8 = new JRadioButton("其他");
		resoure8.setFont(new Font("宋体", Font.PLAIN, 11));
		resoure8.setBounds(906, 306, 51, 23);
		contentPane.add(resoure8);
		
		ButtonGroup resourceGroup = new ButtonGroup();
		resourceGroup.add(resoure1);
		resourceGroup.add(resoure2);
		resourceGroup.add(resoure3);
		resourceGroup.add(resoure4);
		resourceGroup.add(resoure5);
		resourceGroup.add(resoure6);
		resourceGroup.add(resoure7);
		resourceGroup.add(resoure8);
		
		JRadioButton mediaImage1 = new JRadioButton("可怜悲惨的形象");
		mediaImage1.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage1.setBounds(703, 339, 103, 23);
		contentPane.add(mediaImage1);
		
		JRadioButton mediaImage2 = new JRadioButton("沐恩幸福的形象");
		mediaImage2.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage2.setBounds(830, 339, 121, 23);
		contentPane.add(mediaImage2);
		
		JRadioButton mediaImage3 = new JRadioButton("积极健康的形象");
		mediaImage3.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage3.setBounds(650, 368, 113, 23);
		contentPane.add(mediaImage3);
		
		JRadioButton mediaImage4 = new JRadioButton("问题儿童的形象");
		mediaImage4.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage4.setBounds(768, 368, 103, 23);
		contentPane.add(mediaImage4);
		
		JRadioButton mediaImage5 = new JRadioButton("其他");
		mediaImage5.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage5.setBounds(875, 367, 62, 23);
		contentPane.add(mediaImage5);
		
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
		
		JRadioButton reason2 = new JRadioButton("私立学校学费高");
		reason2.setFont(new Font("宋体", Font.PLAIN, 11));
		reason2.setBounds(830, 429, 103, 23);
		contentPane.add(reason2);
		
		JRadioButton reason3 = new JRadioButton("越来越多小型私立学校被取消办学资格");
		reason3.setFont(new Font("宋体", Font.PLAIN, 11));
		reason3.setBounds(678, 459, 218, 23);
		contentPane.add(reason3);
		
		JRadioButton reason4 = new JRadioButton("私立学校办学质量没保障");
		reason4.setFont(new Font("宋体", Font.PLAIN, 11));
		reason4.setBounds(678, 488, 146, 23);
		contentPane.add(reason4);
		
		JRadioButton reason5 = new JRadioButton("其他");
		reason5.setFont(new Font("宋体", Font.PLAIN, 11));
		reason5.setBounds(834, 488, 62, 23);
		contentPane.add(reason5);
		
		ButtonGroup reasonGroup = new ButtonGroup();
		reasonGroup.add(reason1);
		reasonGroup.add(reason2);
		reasonGroup.add(reason3);
		reasonGroup.add(reason4);
		reasonGroup.add(reason5);
		
		

		choice_2.add("性别");
		choice_2.add("男");
		choice_2.add("女");
		choice_2.select(0);
	    
		setTitle("新闻内容");
			
	}
	
}

