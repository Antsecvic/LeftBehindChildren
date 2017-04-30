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
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class LeftBehindChildren {

	private static Map<String,News> map = new HashMap<String,News>();
	private static Map<String,News> mapClassified = new HashMap<String,News>();
	private static Map<String,News> mapNotClassified = new HashMap<String,News>();
	
	public static JFrame mainFrame;
	public static Logger logger = LogManager.getLogger(LeftBehindChildren.class.getName());

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		Dom4j dom4j = new Dom4j();
		dom4j.parserXml("assets/guangming.xml",map);
		dom4j.parserXml("assets/nanfangdaily.xml",map);
		dom4j.parserXml("assets/sichuan.xml",map);
		//遍历以测试数据是否读入map
//		for(Entry<String, News> entry : map.entrySet()){
//			News news = entry.getValue();
//			System.out.println(entry.getKey()+":\n"+news.getEncodedContent()); 
//		}
		
		for(News value:map.values()){
			if(!value.getTags().equals("")){
				mapClassified.put(value.getTitle(), value);
			}else{
				mapNotClassified.put(value.getTitle(), value);
			}
		}
/*	
		//修改xml文件
		dom4j.modifyXml("assets/guangming.xml",map.get("news:23lh^200601161410077(S:193916305)"));
		
*/
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
        ListModel jList1Model =  new DefaultComboBoxModel(mapNotClassified.keySet().toArray());
        JList myJlist = new JList();
        myJlist.setModel(jList1Model);            //设置数据
        myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    System.out.println("双击");
                    JList myList = (JList) e.getSource();
                    int index = myList.getSelectedIndex();    //已选项的下标
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                    mainFrame.setVisible(false);
    				NewsContent newsContent = new NewsContent(mapNotClassified,mapNotClassified.get(obj));
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
		ListModel jList1Model2 =  new DefaultComboBoxModel(mapClassified.keySet().toArray());
        JList myJlist2 = new JList();
        myJlist2.setModel(jList1Model2);            //设置数据
        myJlist2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){
                    System.out.println("双击");
                    JList myList = (JList) e.getSource();
                    int index = myList.getSelectedIndex();    //已选项的下标
                    Object obj = myList.getModel().getElementAt(index);  //取出数据
                    NewsContent newsContent = new NewsContent(mapClassified,mapClassified.get(obj));
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

