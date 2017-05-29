package UI;

import java.awt.EventQueue;

import org.apache.logging.log4j.*;

import XmlData.News;
//import XmlData.Dom4j;
import XmlData.SaveToXml;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;

public class LeftBehindChildren {
	
	private ListData listData = ListData.getInstance();
	public static JFrame mainFrame;
	public static Logger logger = LogManager.getLogger(LeftBehindChildren.class.getName());
	private static JLabel lblNewLabel = new JLabel("");
	private static String filePath;
	private static boolean openFileTag = false;
	
	private static LeftBehindChildren thisClass = new LeftBehindChildren();
	
	private static ImageIcon[] icons = {new ImageIcon(LeftBehindChildren.class.getResource("/image/image_1.png")),
			new ImageIcon(LeftBehindChildren.class.getResource("/image/image_2.png")),
			new ImageIcon(LeftBehindChildren.class.getResource("/image/image_3.png"))};
	private static int imageIndex = 1;
	private static Thread imageThread = thisClass.new ImageThread();
	
	class ImageThread extends Thread 
	{ 
		@Override
	    public void run() { 
	    	while(true){
				try {
					//每隔3秒更换图片
					sleep(3000);
					lblNewLabel.setIcon(icons[(imageIndex++) % icons.length]);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}			
			}
	    }
	}
	
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
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					logger.info("查看首页");					
					LeftBehindChildren.mainFrame.setVisible(true);					
				} catch (Exception e) {
					logger.error("首页错误");
					e.printStackTrace();
				}
			}
		});
		imageThread.start();
		

	}
	
	
	/**
	 * Initialize the contents of the mainFrame.
	 */
	private void initialize() {
		
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.BLACK);
//		mainFrame.setBounds(0, 0, 1000, 700);
		mainFrame.setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().
                getWidth()-1000)/2,(int)(Toolkit.getDefaultToolkit().getScreenSize().
                        getHeight()-700)/2);
		mainFrame.setSize(1000,700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setTitle("留守儿童舆情调查软件");
		mainFrame.setResizable(false);
		
		// 显示首页图片		
		lblNewLabel.setIcon(new ImageIcon(LeftBehindChildren.class.getResource("/image/image_1.png")));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 568, 325);
		mainFrame.getContentPane().add(lblNewLabel);
		
		// 显示文字“新闻 尚未分类”
		Label label = new Label("\u65B0\u95FB  \u5C1A\u672A\u5206\u7C7B");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setForeground(SystemColor.activeCaptionBorder);
		label.setBounds(10, 326, 130, 23);
		mainFrame.getContentPane().add(label);
		
		// 用做水平分割线
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 355, 568, 1);
		mainFrame.getContentPane().add(scrollPane);
		
		// 用来显示未分类的新闻标题
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 357, 568,310);
		scrollPane_1.setBackground(Color.BLACK);
		scrollPane_1.getVerticalScrollBar().setUI(null);
		scrollPane_1.setBorder(null);

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
        myJlist.setBackground(Color.black);
        myJlist.setForeground(Color.lightGray);
        
        //鼠标监听器
        myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int index = myJlist.locationToIndex(e.getPoint());    //已选项的下标
            	if(index != -1){
            		myJlist.setSelectedIndex(index);
                    if(e.getClickCount() == 1 && e.getButton() == 1){  
                        logger.info("首页点击打开未分类新闻--"+listData.notClassifiedNews.get(index).getTitle());
        				NewsContent newsContent = new NewsContent(listData.notClassifiedNews,index,filePath);
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
		label_1.setFont(new Font("Dialog", Font.BOLD, 18));
		label_1.setBounds(600, 10, 174, 23);
		mainFrame.getContentPane().add(label_1);
		
		// 用做水平分割线
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(600, 39, 370, 1);
		mainFrame.getContentPane().add(scrollPane_2);
		
		// 用来显示已分类的新闻标题
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(600, 41, 370, 537);
		scrollPane_3.setBackground(Color.BLACK);
		scrollPane_3.getVerticalScrollBar().setUI(null);
		scrollPane_3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBorder(null);
		
		
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
        myJlist2.setBackground(Color.black);
        myJlist2.setForeground(Color.lightGray);
        
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
                        ClassifiedNewsContent newsContent = new ClassifiedNewsContent(listData.classifiedNews,index,filePath);
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
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(600, 585, 370, 1);
		mainFrame.getContentPane().add(scrollPane_5);
		
		JButton btnNewButton = new JButton("\u7EDF\u8BA1\u7AD9");
		btnNewButton.setBackground(Color.black);
		btnNewButton.setForeground(Color.white);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("进入统计站，进行三报相关新闻总数量统计");
				StatisticsBin statics = new StatisticsBin();
				statics.setVisible(true);
				mainFrame.setVisible(false);
			}
		});
		btnNewButton.setBounds(600, 596, 93, 23);
		mainFrame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(600, 629, 93, 1);
		mainFrame.getContentPane().add(scrollPane_4);
		
		JButton button = new JButton("\u56DE\u6536\u7AD9");
		button.setBackground(Color.black);
		button.setForeground(Color.white);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.info("进入回收站");
				RecycleBin recycle = new RecycleBin(listData.deletedNews,listData.deletedTitle,filePath);
				recycle.setVisible(true);
				LeftBehindChildren.mainFrame.dispose();
			}
		});
		button.setBounds(600, 640, 93, 23);
		mainFrame.getContentPane().add(button);
		
		mainFrame.addWindowListener(new WindowAdapter() {  
			  
			public void windowClosing(WindowEvent e) {  
				super.windowClosing(e);  
				if(filePath!=null){
					//文件有确定的目录
					SaveToXml saveToXml = new SaveToXml(filePath);
					logger.info("关闭并保存文件"+filePath);
				}else{
					//列表有多个文件，没有确定目录
					SaveToXml saveToXml = new SaveToXml();
					logger.info("关闭三报新闻");
				}
				
			}
		});
		
		
		JButton encodeToSave = new JButton("加密保存");
		encodeToSave.setBackground(Color.black);
		encodeToSave.setForeground(Color.white);
		encodeToSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SaveToXml saveToXml = new SaveToXml(filePath);
				FileNameExtensionFilter filter=new FileNameExtensionFilter("*.xml","xml");  
		        JFileChooser fc=new JFileChooser();  
		        fc.setFileFilter(filter);  
		        fc.setMultiSelectionEnabled(false);  
		        int result=fc.showSaveDialog(null);  
		        if (result==JFileChooser.APPROVE_OPTION) {  
		        	boolean ifEncoded = false;
		            File file=fc.getSelectedFile();  
		            if (!file.getPath().endsWith(".xml")) {  
		                file=new File(file.getPath()+".xml");  
		            }  
					try{
						SetPassword setPassword = new SetPassword(mainFrame,filePath,file);
						setPassword.setVisible(true);
						ifEncoded = setPassword.ifEncoded();
					}catch(Exception e){
						e.printStackTrace();
					}
		            System.out.println("file path="+file.getPath()); 
		            try {  
		                if (!file.exists()&&ifEncoded) {//文件不存在 则创建一个  
		                    file.createNewFile();  
		                }  
		            } catch (IOException e) {  
		                System.err.println("文件创建失败：");  
		                logger.info("加密保存：文件创建失败");
		                e.printStackTrace();  
		            }
		            logger.info("加密保存：保存文件路径为"+file.getPath());
		        } 
			}
		});
		encodeToSave.setBounds(320, 326, 90, 23);
		mainFrame.getContentPane().add(encodeToSave);
		
		JButton merge = new JButton("合并文件");
		merge.setBackground(Color.black);
		merge.setForeground(Color.white);
		merge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SaveToXml saveToXml = new SaveToXml(filePath);
				// 打开文件选择窗口 
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File(".")); 
				chooser.setFileFilter(new javax.swing.filechooser.FileFilter() { 
					public boolean accept(File f) { 
						return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory(); 
					} 
	
					public String getDescription() { 
						return "classify data"; 
					} 
				}); 
				int r = chooser.showOpenDialog(new JFrame()); 
				if (r == JFileChooser.APPROVE_OPTION) { 
					File f=chooser.getSelectedFile();
									
					MergeFile mergeFile = new MergeFile(filePath,f);
					Boolean ifMerge = mergeFile.merge();
					logger.info("合并文件"+filePath+"和"+f.getPath());
					if(ifMerge){
						JOptionPane.showMessageDialog(null, "合并成功。", "合并文件", JOptionPane.INFORMATION_MESSAGE);
						logger.info("合并成功");
					}else{
						JOptionPane.showMessageDialog(null, "合并失败。", "合并文件", JOptionPane.INFORMATION_MESSAGE);
						logger.info("合并失败");
					}
					ListData.getInstance().importFile(filePath);
					listData = ListData.getInstance();
					ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//重新绑定列表模型数据
	        		myJlist.setModel(jList1Model1);//重新绑定列表模型
	        		myJlist.updateUI();//更新列表
	        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//重新绑定列表模型数据
	        		myJlist2.setModel(jList1Model3);//重新绑定列表模型
	        		myJlist2.updateUI();//更新列表
				} else { 
				//没有选择文件 
				} 
			}
		});
		merge.setBounds(410, 326, 90, 23);
		mainFrame.getContentPane().add(merge);
		
		if(!openFileTag){
			encodeToSave.setEnabled(false);
			merge.setEnabled(false);
		}
		
		
		JButton open = new JButton("打开文件");
		open.setBackground(Color.black);
		open.setForeground(Color.white);
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(openFileTag){
					SaveToXml saveToXml = new SaveToXml(filePath);
				}else{
					SaveToXml saveToXml = new SaveToXml();
				}
				// 打开文件选择窗口 
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File(".")); 
				chooser.setFileFilter(new javax.swing.filechooser.FileFilter() { 
					public boolean accept(File f) { 
						return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory(); 
					} 
	
					public String getDescription() { 
						return "classify data"; 
					} 
				}); 

				int r = chooser.showOpenDialog(new JFrame()); 
				if (r == JFileChooser.APPROVE_OPTION) { 
					File f=chooser.getSelectedFile();

					PasswordDialog passwordDialog = new PasswordDialog(mainFrame,f);
					passwordDialog.setVisible(true);

					filePath = passwordDialog.get();
					logger.info("打开文件："+filePath);
					ListData.getInstance().importFile(filePath);
					listData = ListData.getInstance();
					System.out.println(listData.newsList.size());
	        		ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//重新绑定列表模型数据
	        		myJlist.setModel(jList1Model1);//重新绑定列表模型
	        		myJlist.updateUI();//更新列表
	        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//重新绑定列表模型数据
	        		myJlist2.setModel(jList1Model3);//重新绑定列表模型
	        		myJlist2.updateUI();//更新列表
					
					openFileTag = true;
	        		encodeToSave.setEnabled(true);
	        		merge.setEnabled(true);
				} else { 
				//没有选择文件 
				} 
			}
		});		
		open.setBounds(230, 326, 90, 23);
		mainFrame.getContentPane().add(open);
		
		
		
		JButton train = new JButton("培训用户");
		train.setBackground(Color.black);
		train.setForeground(Color.white);
		train.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.info("培训用户");
				// 打开文件选择窗口 
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File(".")); 
				chooser.setFileFilter(new javax.swing.filechooser.FileFilter() { 
					public boolean accept(File f) { 
						return f.getName().toLowerCase().endsWith(".xml") || f.isDirectory(); 
					} 
	
					public String getDescription() { 
						return "classify data"; 
					} 
				}); 
				chooser.setMultiSelectionEnabled(true);
				int r = chooser.showOpenDialog(new JFrame()); 
				if (r == JFileChooser.APPROVE_OPTION) { 
					File[] f=chooser.getSelectedFiles();				
					TrainUser trainUser = new TrainUser(f);
					JOptionPane.showMessageDialog(null, String.valueOf(trainUser.countOfSame()*100) + "%", "一致性", JOptionPane.INFORMATION_MESSAGE);
					logger.info("文件一致性："+String.valueOf(trainUser.countOfSame()*100) + "%");
				} else { 
				//没有选择文件 
				} 
			}
		});
		train.setBounds(500, 326, 90, 23);
		mainFrame.getContentPane().add(train);
		
		JButton all = new JButton("三报新闻");
		all.setBackground(Color.black);
		all.setForeground(Color.white);
		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.info("显示三报新闻");	
				//点击之前都会保存一次文件
				if(openFileTag){
					SaveToXml saveToXml = new SaveToXml(filePath);
				}else{
					SaveToXml saveToXml = new SaveToXml();
				}
				ListData.getInstance().importAllFile();
				listData = ListData.getInstance();
				ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//重新绑定列表模型数据
        		myJlist.setModel(jList1Model1);//重新绑定列表模型
        		myJlist.updateUI();//更新列表
        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//重新绑定列表模型数据
        		myJlist2.setModel(jList1Model3);//重新绑定列表模型
        		myJlist2.updateUI();//更新列表
        		//关闭软件时会检测这个filePath,判断是否有确定目录可以保存，这里有三个文件所以没有不需要filePath
        		filePath = null;
        		
        		openFileTag = false;
        		encodeToSave.setEnabled(false);
        		merge.setEnabled(false);
			}
		});
		all.setBounds(140, 326, 90, 23);
		mainFrame.getContentPane().add(all);
	}
}

