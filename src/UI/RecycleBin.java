package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import XmlData.Dom4j;
import XmlData.News;

import javax.swing.JList;
import javax.swing.JMenuItem;

import java.awt.CardLayout;
import java.awt.Label;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.event.ActionEvent;

public class RecycleBin extends JFrame {

	private JPanel contentPane;
	Dom4j dom4j = new Dom4j();
	
	/**
	 * Create the frame.
	 */
	public RecycleBin(List<News> deletedNews,List<String> deletedTitle) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		setTitle("回收站");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("\u56DE\u6536\u7684\u65B0\u95FB");
		label.setForeground(SystemColor.activeCaptionBorder);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(10, 26, 174, 23);
		contentPane.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 600, 2);
		contentPane.add(scrollPane);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 86, 600, 300);
		ListModel jListModel =  new DefaultComboBoxModel(deletedTitle.toArray());
		JList myJlist = new JList();
		myJlist.setModel(jListModel); 
//		myJlist.set
		
//		System.out.println("cishu");
		
		myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int index = myJlist.locationToIndex(e.getPoint());    //已选项的下标
            	myJlist.setSelectedIndex(index);
                if(e.isMetaDown()) {
            		//设置右键菜单
            		JPopupMenu menu = new JPopupMenu();
                    JMenuItem item1 = new JMenuItem("恢复");
                    item1.addMouseListener(new MouseAdapter(){
                    	public void mouseReleased(MouseEvent e) {
                    		dom4j.restoreNews(deletedNews.get(index));
                    		
                    	}
                    });
                    menu.add(item1);
                    menu.show(myJlist,e.getX(),e.getY());
                    myJlist.setComponentPopupMenu(menu);//将按钮与右键菜单关联
                	
                }
            }
           
        });
        
        scrollPane_1.setViewportView(myJlist);    //不能直接add
		contentPane.add(scrollPane_1);
		
		JButton button = new JButton("\u8FD4\u56DE\u9996\u9875");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				LeftBehindChildren leftBehindChildren = new LeftBehindChildren();
				leftBehindChildren.mainFrame.setVisible(true);
			}
		});
		button.setBounds(10, 420, 93, 23);
		contentPane.add(button);
		
	}
}
