package UI;

import java.awt.EventQueue;

import org.apache.logging.log4j.*;

import XmlData.Dom4j;
import XmlData.News;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.awt.event.ActionEvent;

public class LeftBehindChildren {

	public static JFrame mainFrame;
	public static Logger logger = LogManager.getLogger(LeftBehindChildren.class.getName());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		Map<String,News> map = new HashMap<String,News>();
		Map<String,News> mapClassified = new HashMap<String,News>();
		Map<String,News> mapNotClassified = new HashMap<String,News>();
		
		Dom4j dom4j = new Dom4j();
//		dom4jDemo.createXml("haha");
		dom4j.parserXml("assets/guangming.xml",map);
		
		for(Entry<String, News> entry : map.entrySet()){
			News news = entry.getValue();
			System.out.println(entry.getKey()+":\n"+news.getEncodedContent()); 
		}
		
		for(News value:map.values()){
			if(!value.getTags().equals("")){
				mapClassified.put(value.getID(), value);
			}else{
				mapNotClassified.put(value.getID(), value);
			}
		}
		
		dom4j.modifyXml("assets/guangming.xml",map.get("news:23lh^200601161410077(S:193916305)"));
		
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
	 * Create the application.
	 */
	public LeftBehindChildren() {
		initialize();
	}

	/**
	 * Initialize the contents of the mainFrame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.WHITE);
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
		
		// 用来存放
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 396, 568,266);
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
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(613, 65, 349, 508);
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
