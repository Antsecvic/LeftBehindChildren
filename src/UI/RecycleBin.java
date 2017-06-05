package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Object.ListData;
import Object.News;
import XmlData.SaveToXml;

import javax.swing.JList;
import javax.swing.JMenuItem;

import java.awt.Label;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class RecycleBin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static Logger logger = LogManager.getLogger(RecycleBin.class.getName());
	private ListData listData = ListData.getInstance();
	private String filePath;
	/**
	 * Create the frame.
	 */
	public RecycleBin(List<News> deletedNews,List<String> deletedTitle,String filePath) {
		this.filePath = filePath;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(300, 50, 1000, 700);
		setTitle("回收站");
		setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().
                getWidth()-1000)/2,(int)(Toolkit.getDefaultToolkit().getScreenSize().
                        getHeight()-700)/2);
		setSize(1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("\u56DE\u6536\u7684\u65B0\u95FB");
		label.setForeground(SystemColor.activeCaptionBorder);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(10, 26, 174, 23);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 600, 1);
		contentPane.add(scrollPane);
//		int count=0;
//		for(News news : deletedNews){
//			
//			System.out.println(count++);
//		}
//		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 86, 600, 300);
		scrollPane_1.setBackground(Color.BLACK);
		scrollPane_1.getVerticalScrollBar().setUI(null);
		
		ListModel<Object> jListModel =  new DefaultComboBoxModel<>(deletedTitle.toArray());
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
		myJlist.setModel(jListModel); 
		myJlist.setBackground(Color.black);
		myJlist.setForeground(Color.lightGray);
		
//		System.out.println("cishu");
		
		myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int index = myJlist.locationToIndex(e.getPoint());    //已选项的下标
            	if(index != -1){
            		myJlist.setSelectedIndex(index);
                    if(e.isMetaDown()) {
                    	JPopupMenu menu = new JPopupMenu();
                        JMenuItem item1 = new JMenuItem("恢复");
                        item1.addMouseListener(new MouseAdapter(){
                        	public void mouseReleased(MouseEvent e) {
                        		logger.info("恢复新闻--"+deletedNews.get(index).getTitle());
//                        		dom4j.restoreNews(deletedNews.get(index));
                        		deletedNews.get(index).setIsDeleted("false");
                        		if(deletedNews.get(index).getTagIts().equals("true")){
                        			listData.classifiedTitle.add(deletedNews.get(index).getTitle());
                        			listData.classifiedNews.add(deletedNews.get(index));
                        		}else{
                        			listData.notClassifiedTitle.add(deletedNews.get(index).getTitle());
                        			listData.notClassifiedNews.add(deletedNews.get(index));
                        		}
                        		deletedTitle.remove(index);//在数据列表中删除该新闻标题
                        		deletedNews.remove(index);//在数据列表中删除该新闻
                        		ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(deletedTitle.toArray());//重新绑定列表模型数据
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
		contentPane.add(scrollPane_1);
		
		JButton button = new JButton("\u8FD4\u56DE\u9996\u9875");
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("返回首页");
			    new LeftBehindChildren();
				LeftBehindChildren.mainFrame.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
		button.setBounds(10, 420, 93, 23);
		contentPane.add(button);
		
		this.addWindowListener(new WindowAdapter() {  
			  
			public void windowClosing(WindowEvent e) {  
				super.windowClosing(e);  
				@SuppressWarnings("unused")
				SaveToXml saveToXml = new SaveToXml(filePath);
			}
		});
	}
}
