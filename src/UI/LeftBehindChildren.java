package UI;

import java.awt.EventQueue;

import org.apache.logging.log4j.*;

import XmlData.Dom4j;
import XmlData.News;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class LeftBehindChildren {

	private List<News> newsList = new ArrayList<>();
	private List<News> classifiedNews = new ArrayList<>();
	private List<News> notClassifiedNews = new ArrayList<>();
	private List<String> classifiedTitle = new ArrayList<>();
	private List<String> notClassifiedTitle = new ArrayList<>();
	
//	private LeftBehindChildren leftBehindChildren;
	
	public static JFrame mainFrame;
	public static Logger logger = LogManager.getLogger(LeftBehindChildren.class.getName());

	/**
	 * Create the application.
	 */
	public LeftBehindChildren() {
		initialize();
	}
	
//	public static LeftBehindChildren getInstance(){
//		return leftBehindChildren;
//	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
//		for(News news : newsList){
//			System.out.println(news.getTitle());
//		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeftBehindChildren window = new LeftBehindChildren();
					logger.info("查看首页");
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					logger.error("首页错误");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the mainFrame.
	 */
	private void initialize() {
		
		Dom4j dom4j = new Dom4j();
		dom4j.initXml("assets/guangming.xml");
		dom4j.parserXml("assets/guangming.xml",newsList);
		
	//	dom4j.parserXml("assets/nanfangdaily.xml",newsList);
	//	dom4j.parserXml("assets/sichuan.xml",newsList);
		
		for(News news : newsList){
			if(!news.getTagIts().equals("false")){
				classifiedNews.add(news);
				classifiedTitle.add(news.getTitle());
				System.out.println(news.getTagIts());
			}else{
				notClassifiedNews.add(news);
				notClassifiedTitle.add(news.getTitle());
//				System.out.println(news.getTagIts());
			}
		}
		
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.BLACK);
		mainFrame.setBounds(300, 50, 1000, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setTitle("留守儿童舆情调查软件");
		mainFrame.setResizable(false);
		
		// 显示首页图片
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LeftBehindChildren.class.getResource("/image/image_1.png")));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 568, 325);
		mainFrame.getContentPane().add(lblNewLabel);
		
		// 显示文字“新闻 尚未分类”
		Label label = new Label("\u65B0\u95FB  \u5C1A\u672A\u5206\u7C7B");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setForeground(SystemColor.activeCaptionBorder);
		label.setBounds(10, 345, 174, 23);
		mainFrame.getContentPane().add(label);
		
		// 用做水平分割线
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 374, 568, 2);
		mainFrame.getContentPane().add(scrollPane);
		
		// 用来显示未分类的新闻标题
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 396, 568,266);
//        jScrollPane1.setPreferredSize(new java.awt.Dimension(218, 164));
        ListModel jList1Model =  new DefaultComboBoxModel(notClassifiedTitle.toArray());
		for(String news : notClassifiedTitle){
			System.out.println(news);
		}
        JList myJlist = new JList();
        myJlist.setModel(jList1Model);            //设置数据
        myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){  
                    JList myList = (JList) e.getSource();
                    int index = myList.getSelectedIndex();    //已选项的下标
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                    logger.info("首页点击打开未分类新闻--"+obj.toString());
                    LeftBehindChildren.mainFrame.dispose();
    				NewsContent newsContent = new NewsContent(notClassifiedNews,index);
    				newsContent.setVisible(true);
                }
            }
        });
        scrollPane_1.setViewportView(myJlist);    //不能直接add
		mainFrame.getContentPane().add(scrollPane_1);
		
		// 显示文字“新闻 已分类”
		Label label_1 = new Label("\u65B0\u95FB  \u5DF2\u5206\u7C7B");
		label_1.setForeground(SystemColor.activeCaptionBorder);
		label_1.setFont(new Font("Dialog", Font.BOLD, 20));
		label_1.setBounds(613, 10, 174, 23);
		mainFrame.getContentPane().add(label_1);
		
		// 用做水平分割线
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(613, 39, 349, 2);
		mainFrame.getContentPane().add(scrollPane_2);
		
		// 用来显示已分类的新闻标题
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(613, 65, 349, 508);
		ListModel jList1Model2 =  new DefaultComboBoxModel(classifiedTitle.toArray());
        JList myJlist2 = new JList();
        myJlist2.setModel(jList1Model2);            //设置数据
        myJlist2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1){
                    JList myList = (JList) e.getSource();
                    int index = myList.getSelectedIndex();    //已选项的下标
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                    logger.info("首页点击打开已分类新闻--"+obj.toString());
                    LeftBehindChildren.mainFrame.dispose();
    				NewsContent newsContent = new NewsContent(classifiedNews,index);
    				newsContent.setVisible(true);
                }
            }
        });
        scrollPane_3.setViewportView(myJlist2);    //不能直接add
		mainFrame.getContentPane().add(scrollPane_3);
		
		JButton btnNewButton = new JButton("\u7EDF\u8BA1\u7AD9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				StatisticsBin statics = new StatisticsBin();
				statics.setVisible(true);
			}
		});
		btnNewButton.setBounds(613, 583, 93, 23);
		mainFrame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(613, 616, 93, 2);
		mainFrame.getContentPane().add(scrollPane_4);
		
		JButton button = new JButton("\u56DE\u6536\u7AD9");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
				RecycleBin recycle = new RecycleBin();
				recycle.setVisible(true);
			}
		});
		button.setBounds(613, 628, 93, 23);
		mainFrame.getContentPane().add(button);
	}
}

