package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import XmlData.Dom4j;
import XmlData.News;
import XmlData.SaveToXml;
import XmlData.Tags;

@SuppressWarnings("serial")
public class ClassifiedNewsContent extends JFrame{
	public static Logger logger = LogManager.getLogger(ClassifiedNewsContent.class.getName());
	public JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane mainBody;
	private List<News> newsList;
	private int position;
	private JButton showExternalNews = new JButton("加载外部新闻");
	private Tags tags;
	private ListData listData;
	

	public ClassifiedNewsContent(List<News> newsList,int position) {
		this.newsList = newsList;
		this.position = position;
		tags = new Tags();
		listData = ListData.getInstance();
		if(newsList.get(position).getTagIts().equals("true")){
			tags = newsList.get(position).getTags();
		}
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
				new LeftBehindChildren();
				LeftBehindChildren.mainFrame.setVisible(true);			
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
		label_3.setBounds(621, 115, 51, 23);
		label_3.setBackground(Color.WHITE);
		contentPane.add(label_3);
		
		//显示报道主题
		Label label_2 = new Label("报道主题");
		label_2.setBounds(621, 150, 51, 23);
		label_2.setBackground(Color.WHITE);
		contentPane.add(label_2);
		
		Label label_7 = new Label("新闻主体");
		label_7.setBounds(621, 185, 51, 23);
		label_7.setBackground(Color.WHITE);
		contentPane.add(label_7);
		
		Label label_8 = new Label("具体种类");
		label_8.setBounds(621, 220, 51, 23);
		label_8.setBackground(Color.WHITE);
		contentPane.add(label_8);
		
		Label label_9 = new Label("性别");
		label_9.setBounds(621, 255, 27, 23);
		label_9.setBackground(Color.WHITE);
		contentPane.add(label_9);
		
		Label label_4 = new Label("新闻报道消息来源");
		label_4.setBounds(621, 290, 103, 23);
		label_4.setBackground(Color.WHITE);
		contentPane.add(label_4);
		
		Label label_5 = new Label("媒体形象呈现");
		label_5.setBounds(621, 325, 79, 23);
		label_5.setBackground(Color.WHITE);
		contentPane.add(label_5);
		
		Label label_6 = new Label("农民工子女不能留在城市读书的原因");
		label_6.setBounds(621, 360, 195, 23);
		label_6.setBackground(Color.WHITE);
		contentPane.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(615, 531, 400, 2);
		contentPane.add(scrollPane_2);
		
		
		
		
		Label type = new Label(tags.getType());
		type.setBounds(678, 115, 100, 23);
		contentPane.add(type);
		
		Label theme = new Label(tags.getTheme());
//		theme.setFont(new Font("宋体", Font.PLAIN, 11));
		theme.setBounds(678, 150, 100, 23);
		contentPane.add(theme);
		
		Label mainBody = new Label(tags.getMainBody());
//		mainBody.setFont(new Font("宋体", Font.PLAIN, 11));
		mainBody.setBounds(678, 185, 100, 23);
		contentPane.add(mainBody);
		
		Label helpType = new Label(tags.getHelpType());
//		helpType.setFont(new Font("宋体", Font.PLAIN, 11));
		helpType.setBounds(678, 220, 100, 23);
		contentPane.add(helpType);
		
		Label gender = new Label(tags.getGender());
//		gender.setFont(new Font("宋体", Font.PLAIN, 11));
		gender.setBounds(660, 255, 100, 23);
		contentPane.add(gender);
		
		Label source = new Label(tags.getSource());
//		source.setFont(new Font("宋体", Font.PLAIN, 11));
		source.setBounds(730, 290, 100, 23);
		contentPane.add(source);
		
		Label mediaImage = new Label(tags.getShowing());
//		mediaImage.setFont(new Font("宋体", Font.PLAIN, 11));
		mediaImage.setBounds(703, 325, 150, 23);
		contentPane.add(mediaImage);
		
		Label reason = new Label(tags.getReason());
//		reason.setFont(new Font("宋体", Font.PLAIN, 11));
		reason.setBounds(820, 360, 150, 23);
		contentPane.add(reason);
		
		JButton reset = new JButton("重置标签");
		reset.setBounds(858, 542, 93, 23);
		contentPane.add(reset);
		
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根								
				String id = newsList.get(position).getID();
				tags.setType("");
				tags.setTheme("");
				tags.setSource("");
				tags.setShowing("");
				tags.setReason("");
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
				modifyList();
				dispose();
				NewsContent newsContent = 
						new NewsContent(listData.notClassifiedNews,
								listData.findPosition(listData.notClassifiedNews, id));
				newsContent.setVisible(true);
			}			
		});
		
		this.addWindowListener(new WindowAdapter() {  
			public void windowClosing(WindowEvent e) {  
				super.windowClosing(e);  
				SaveToXml saveToXml = new SaveToXml();
			}
		});
	}
	public void modifyList(){
		Dom4j dom4j = new Dom4j();
		newsList.get(position).setTags(tags);
		newsList.get(position).setTagIts("false");
		dom4j.modifyXml(newsList.get(position));
		listData.notClassifiedTitle.add(newsList.get(position).getTitle());
		listData.notClassifiedNews.add(newsList.get(position));
		listData.classifiedTitle.remove(newsList.get(position).getTitle());
		listData.classifiedNews.remove(newsList.get(position));

	}
}
