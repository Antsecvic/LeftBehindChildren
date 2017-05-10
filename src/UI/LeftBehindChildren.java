package UI;

import java.awt.EventQueue;

import org.apache.logging.log4j.*;
import XmlData.Dom4j;
import XmlData.SaveToXml;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class LeftBehindChildren {
	
	private ListData listData;
	public static JFrame mainFrame;
	public static Logger logger = LogManager.getLogger(LeftBehindChildren.class.getName());

	/**
	 * Create the application.
	 */
	public LeftBehindChildren() {
		initialize();
	}
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
					logger.info("查看首页");
					new LeftBehindChildren();
					LeftBehindChildren.mainFrame.setVisible(true);
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
		
		listData = ListData.getInstance();
		
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

        ListModel<Object> jList1Model =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());
        
        // 重写获取列表位置的方法，使点击列表空白处不出现选中最后一项的问题
        JList<Object> myJlist = new JList<Object>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public int locationToIndex(Point location) {
				int index = super.locationToIndex(location);
                if (index != -1 && !getCellBounds(index, index).contains(location)) {
                    return -1;
                }
                else {
                    return index;
                }
			}
		};

        myJlist.setModel(jList1Model);            //设置数据
        
        //鼠标监听器
        myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int index = myJlist.locationToIndex(e.getPoint());    //已选项的下标
            	if(index != -1){
            		myJlist.setSelectedIndex(index);
                    if(e.getClickCount() == 1 && e.getButton() == 1){  
                        logger.info("首页点击打开未分类新闻--"+listData.notClassifiedNews.get(index).getTitle());
        				NewsContent newsContent = new NewsContent(listData.notClassifiedNews,index);
        				newsContent.setVisible(true);
                        LeftBehindChildren.mainFrame.dispose();
                    }
                    if(e.isMetaDown()) {
//                		设置右键菜单
                		JPopupMenu menu = new JPopupMenu();
                        JMenuItem item1 = new JMenuItem("删除");
                      //鼠标监听器
                        item1.addMouseListener(new MouseAdapter(){
                        	public void mouseReleased(MouseEvent e) {
                        		logger.info("删除未分类新闻--"+listData.notClassifiedNews.get(index).getTitle());
                        		
//                        		dom4j.deleteNews(listData.notClassifiedNews.get(index));
                        		listData.notClassifiedNews.get(index).setIsDeleted("true");
                        		
                        		listData.deletedNews.add(listData.notClassifiedNews.get(index));
        						listData.deletedTitle.add(listData.notClassifiedNews.get(index).getTitle());
                        		listData.notClassifiedTitle.remove(index);//在数据列表中删除该新闻标题
                        		listData.notClassifiedNews.remove(index);//在数据列表中删除该新闻
                        		ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//重新绑定列表模型数据
                        		myJlist.setModel(jList1Model1);//重新绑定列表模型
                        		myJlist.updateUI();//更新列表
                        	}
                        });
                        menu.add(item1);
                        menu.show(myJlist,e.getX(),e.getY());
//                        myJlist.setComponentPopupMenu(menu);//将按钮与右键菜单关联
                    }
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
		ListModel<Object> jList1Model2 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());
		
		// 重写获取列表位置的方法，使点击列表空白处不出现选中最后一项的问题
		JList<Object> myJlist2 = new JList<Object>() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public int locationToIndex(Point location) {
				int index = super.locationToIndex(location);
                if (index != -1 && !getCellBounds(index, index).contains(location)) {
                    return -1;
                }
                else {
                    return index;
                }
			}
		};
		
        myJlist2.setModel(jList1Model2);            //设置数据
        
      //鼠标监听器
        myJlist2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int index = myJlist2.locationToIndex(e.getPoint());    //已选项的下标
            	if(index != -1){
            		myJlist2.setSelectedIndex(index);
                    if(e.getClickCount() == 1 && e.getButton() == 1){  
//                        Object obj = myJlist.getModel().getElementAt(index);  //取出数据
                        logger.info("首页点击打开已分类新闻--"+listData.classifiedNews.get(index).getTitle());
                        ClassifiedNewsContent newsContent = new ClassifiedNewsContent(listData.classifiedNews,index);
        				newsContent.setVisible(true);
                        LeftBehindChildren.mainFrame.dispose();
                    }
                    if(e.isMetaDown()) {
                		//设置右键菜单
                		JPopupMenu menu = new JPopupMenu();
                        JMenuItem item1 = new JMenuItem("删除");
                      //鼠标监听器
                        item1.addMouseListener(new MouseAdapter(){
                        	public void mouseReleased(MouseEvent e) {
                        		logger.info("删除已分类新闻--"+listData.classifiedNews.get(index).getTitle());
                        		
//                        		dom4j.deleteNews(listData.classifiedNews.get(index));
                        		listData.classifiedNews.get(index).setIsDeleted("true");
                        		
                        		listData.deletedNews.add(listData.classifiedNews.get(index));
        						listData.deletedTitle.add(listData.classifiedNews.get(index).getTitle());
                        		listData.classifiedTitle.remove(index);//在数据列表中删除该新闻标题
                        		listData.classifiedNews.remove(index);//在数据列表中删除该新闻
                        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//重新绑定列表模型数据
                        		myJlist2.setModel(jList1Model3);//重新绑定列表模型
                        		myJlist2.updateUI();//更新列表
                        	}
                        });
                        menu.add(item1);
                        menu.show(myJlist2,e.getX(),e.getY());
//                        myJlist2.setComponentPopupMenu(menu);//将按钮与右键菜单关联
                    }
            	}
            }
        });
        
        scrollPane_3.setViewportView(myJlist2);    //不能直接add
		mainFrame.getContentPane().add(scrollPane_3);
		
		JButton btnNewButton = new JButton("\u7EDF\u8BA1\u7AD9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsBin statics = new StatisticsBin();
				statics.setVisible(true);
				mainFrame.setVisible(false);
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
				RecycleBin recycle = new RecycleBin(listData.deletedNews,listData.deletedTitle);
				recycle.setVisible(true);
				LeftBehindChildren.mainFrame.dispose();
			}
		});
		button.setBounds(613, 628, 93, 23);
		mainFrame.getContentPane().add(button);
		
		mainFrame.addWindowListener(new WindowAdapter() {  
			  
			public void windowClosing(WindowEvent e) {  
				super.windowClosing(e);  
				SaveToXml saveToXml = new SaveToXml();
			}
		});
	}
}

