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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Button;
import java.awt.Choice;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewsContent extends JFrame {
	public static Logger logger = LogManager.getLogger(NewsContent.class.getName());
	private JPanel contentPane;
	private JTextArea textArea;
	String pre;
	String follow;
	
	public void showNewsDetails(News news){
	    textArea.setLineWrap(true);        //激活自动换行功能 
	    textArea.setWrapStyleWord(true);            // 激活断行不断字功能
		Font font = new Font("宋体",Font.BOLD,20);
		textArea.setFont(font);
		textArea.setEditable(false);
		textArea.setCaretPosition(0);			//设置光标位置为首行
		textArea.setText(news.getTitle()+"\n\n"+news.getEncodedContent());

	}

	/**
	 * Create the frame.
	 */
	public NewsContent(Map<String,News> map,News news) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pre = preKey(map,news);
		follow = followKey(map,news);
		
		//显示新闻内容的版块
		textArea=new JTextArea(news.getTitle(),20,43);

		JScrollPane mainBody = new JScrollPane(textArea);
		mainBody.setBounds(5, 5, 600, 600);
		contentPane.add(mainBody);
		
		//上一页按钮
		JButton button = new JButton("\u4E0A\u4E00\u7BC7");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("点击上一页打开新闻--"+pre.toString());
				NewsContent.this.showNewsDetails(map.get(pre));
				pre = preKey(map,map.get(pre));
			}
		});
		button.setBounds(631, 22, 93, 23);
		contentPane.add(button);
		
		//首页按钮
		JButton button_1 = new JButton("\u9996\u9875");
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				logger.info("返回首页");
				setVisible(false);
				LeftBehindChildren window = new LeftBehindChildren();
				window.mainFrame.setVisible(true);
			}
		});
		button_1.setBounds(746, 22, 93, 23);
		contentPane.add(button_1);
		
		//下一页按钮
		JButton button_2 = new JButton("\u4E0B\u4E00\u7BC7");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("点击下一页打开新闻--"+follow.toString());
				setVisible(false);
				NewsContent newsContent = new NewsContent(map,map.get(follow));
				newsContent.setVisible(true);
			}
		});
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setBounds(5, 5, 600, 600);
//		contentPane.add(scrollPane);
//		
//		JButton button = new JButton("\u4E0A\u4E00\u7BC7");
//		button.setBounds(631, 22, 93, 23);
//		contentPane.add(button);
//		
//		JButton button_1 = new JButton("\u9996\u9875");
//		button_1.setBounds(746, 22, 93, 23);
//		contentPane.add(button_1);
//		
//		JButton button_2 = new JButton("\u4E0B\u4E00\u7BC7");
//		button_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
		button_2.setBounds(858, 22, 93, 23);
		contentPane.add(button_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(611, 68, 400, 2);
		contentPane.add(scrollPane_1);
		
		Label label = new Label("为此文章选择标签");
		label.setBounds(611, 51, 103, 23);
		contentPane.add(label);
		
		Label label_1 = new Label("报纸类别");
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(621, 80, 51, 23);
		contentPane.add(label_1);
		
		Label label_3 = new Label("报纸类型");
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(621, 116, 51, 23);
		contentPane.add(label_3);
		
		Button button_3 = new Button("光明日报（党一级日报）");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_3.setBackground(Color.yellow);
			}
		});
		button_3.setBounds(678, 80, 146, 23);
		contentPane.add(button_3);
		
		Button button_4 = new Button("纯净新闻");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				button_4.setBackground(Color.yellow);
			}
		});
		button_4.setBounds(678, 116, 62, 23);
		contentPane.add(button_4);
		
		Button button_5 = new Button("特稿特写");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_5.setBackground(Color.yellow);
			}
		});
		button_5.setBounds(746, 116, 62, 23);
		contentPane.add(button_5);
		
		Button button_6 = new Button("评论");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_6.setBackground(Color.yellow);
			}
		});
		button_6.setBounds(814, 116, 62, 23);
		contentPane.add(button_6);
		
		Button button_7 = new Button("其他");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_7.setBackground(Color.yellow);
			}
		});
		button_7.setBounds(882, 116, 62, 23);
		contentPane.add(button_7);
		
		Label label_2 = new Label("报道主题");
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(621, 155, 51, 23);
		contentPane.add(label_2);
		
		Button button_8 = new Button("帮助关爱");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_8.setBackground(Color.yellow);
				
			}
		});
		button_8.setBounds(678, 155, 62, 23);
		contentPane.add(button_8);
		
		Button button_9 = new Button("表彰鼓励");
		button_9.setBounds(746, 155, 62, 23);
		contentPane.add(button_9);
		
		Button button_10 = new Button("建议看法");
		button_10.setBounds(678, 184, 62, 23);
		contentPane.add(button_10);
		
		Button button_11 = new Button("留守儿童努力向上");
		button_11.setBounds(814, 155, 103, 23);
		contentPane.add(button_11);
		
		Button button_12 = new Button("打工父母艰难生活");
		button_12.setBounds(746, 184, 103, 23);
		contentPane.add(button_12);
		
		Button button_13 = new Button("留守儿童遭性侵");
		button_13.setBounds(861, 184, 103, 23);
		contentPane.add(button_13);
		
		Button button_14 = new Button("留守儿童遭暴力");
		button_14.setBounds(678, 213, 103, 23);
		contentPane.add(button_14);
		
		Button button_15 = new Button("留守儿童犯罪");
		button_15.setBounds(787, 213, 86, 23);
		contentPane.add(button_15);
		
		Button button_16 = new Button("其他");
		button_16.setBounds(882, 213, 68, 23);
		contentPane.add(button_16);
		
		Label label_4 = new Label("新闻报道消息来源");
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(621, 278, 103, 23);
		contentPane.add(label_4);
		
		Button button_17 = new Button("记者");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_17.setBackground(Color.yellow);
			}
		});
		button_17.setBounds(734, 278, 35, 23);
		contentPane.add(button_17);
		
		Button button_18 = new Button("政府");
		button_18.setBounds(773, 278, 35, 23);
		contentPane.add(button_18);
		
		Button button_19 = new Button("企业");
		button_19.setBounds(814, 278, 35, 23);
		contentPane.add(button_19);
		
		Button button_20 = new Button("事业单位");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_20.setBounds(855, 278, 62, 23);
		contentPane.add(button_20);
		
		Button button_21 = new Button("公益团体");
		button_21.setBounds(678, 307, 62, 23);
		contentPane.add(button_21);
		
		Button button_22= new Button("专家学者");
		button_22.setBounds(746, 307, 62, 23);
		contentPane.add(button_22);
		
		Button button_23 = new Button("政府领导");
		button_23.setBounds(814, 307, 62, 23);
		contentPane.add(button_23);
		
		Button button_24 = new Button("其他");
		button_24.setBounds(882, 307, 62, 23);
		contentPane.add(button_24);
		
		Choice choice = new Choice();
		choice.setBounds(678, 242, 62, 21);
		contentPane.add(choice);
		choice.add("新闻主体");
		choice.add("政府部门");
		choice.add("企业");
		choice.add("事业单位");
		choice.add("公益团体");
		choice.add("个人");
		choice.select(0);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(746, 242, 62, 21);
		contentPane.add(choice_1);
		choice_1.add("具体种类");
		choice_1.add("单纯一次捐款捐物");
		choice_1.add("旅游活动安排的项目之一");
		choice_1.add("免费开放");
		choice_1.add("设立长期资助项目");
		choice_1.add("其他");
		choice_1.select(0);
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(814, 242, 62, 21);
		contentPane.add(choice_2);
		
		Label label_5 = new Label("媒体形象呈现");
		label_5.setBackground(Color.WHITE);
		label_5.setBounds(621, 339, 79, 23);
		contentPane.add(label_5);
		
		Button button_25 = new Button("可怜悲惨的形象");
		button_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_25.setBackground(Color.yellow);
			}
		});
		button_25.setBounds(706, 339, 102, 23);
		contentPane.add(button_25);
		
		Button button_26 = new Button("沐恩幸福的形象");
		button_26.setBounds(824, 339, 102, 23);
		contentPane.add(button_26);
		
		Button button_27 = new Button("积极健康的形象");
		button_27.setBounds(678, 368, 102, 23);
		contentPane.add(button_27);
		
		Button button_28 = new Button("问题儿童的形象");
		button_28.setBounds(787, 368, 102, 23);
		contentPane.add(button_28);
		
		Button button_29 = new Button("其他");
		button_29.setBounds(895, 368, 35, 23);
		contentPane.add(button_29);
		
		Label label_6 = new Label("农民工子女不能留在城市读书的原因");
		label_6.setBackground(Color.WHITE);
		label_6.setBounds(621, 403, 195, 23);
		contentPane.add(label_6);
		
		Button button_30 = new Button("无本地户籍难入公立学校");
		button_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_30.setBackground(Color.yellow);
			}
		});
		button_30.setBounds(678, 430, 146, 23);
		contentPane.add(button_30);
		
		Button button_31 = new Button("私立学校学费高");
		button_31.setBounds(834, 430, 102, 23);
		contentPane.add(button_31);
		
		Button button_32 = new Button("越来越多小型私立学校被取消办学资格");
		button_32.setBounds(678, 459, 211, 23);
		contentPane.add(button_32);
		
		Button button_33 = new Button("私立学校教学质量没保障");
		button_33.setBounds(679, 488, 145, 23);
		contentPane.add(button_33);
		
		Button button_34 = new Button("其他");
		button_34.setBounds(834, 488, 102, 23);
		contentPane.add(button_34);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(615, 531, 400, 2);
		contentPane.add(scrollPane_2);
		
		JButton button_35 = new JButton("完成");
		button_35.setBounds(858, 542, 93, 23);
		contentPane.add(button_35);

		choice_2.add("性别");
		choice_2.add("男");
		choice_2.add("女");
		choice_2.select(0);
	    
		setTitle("新闻内容");
	}
	
	//获取前一条新闻的key
	String preKey(Map<String,News> map,News news){
		String pre = "";
		int i = 0;
		for (String key : map.keySet()) {
			if(key.equals(news.getTitle())){
				break;
			}
			i++;
			pre = key;
		}
		if(i == 0){
			return news.getTitle();
		}
		return pre;
	}
	
	//获取下一条新闻的key
	String followKey(Map<String,News> map,News news){
		String follow = "";
		boolean tag = false;
		int i = 0;
		for (String key : map.keySet()) {
			if(tag){
				follow = key;
				break;
			}
			i++;
			if(i == map.size()){
				return news.getTitle();
			}
			if(key.equals(news.getTitle())){
				tag = true;
				continue;
			}
		}
		return follow;
	}
}
