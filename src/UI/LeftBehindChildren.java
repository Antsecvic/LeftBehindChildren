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
					//ÿ��3�����ͼƬ
					sleep(3000);
					lblNewLabel.setIcon(icons[(imageIndex++) % icons.length]);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
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
					logger.info("�鿴��ҳ");					
					LeftBehindChildren.mainFrame.setVisible(true);					
				} catch (Exception e) {
					logger.error("��ҳ����");
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
		mainFrame.setTitle("���ض�ͯ����������");
		mainFrame.setResizable(false);
		
		// ��ʾ��ҳͼƬ		
		lblNewLabel.setIcon(new ImageIcon(LeftBehindChildren.class.getResource("/image/image_1.png")));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 568, 325);
		mainFrame.getContentPane().add(lblNewLabel);
		
		// ��ʾ���֡����� ��δ���ࡱ
		Label label = new Label("\u65B0\u95FB  \u5C1A\u672A\u5206\u7C7B");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setForeground(SystemColor.activeCaptionBorder);
		label.setBounds(10, 326, 130, 23);
		mainFrame.getContentPane().add(label);
		
		// ����ˮƽ�ָ���
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 355, 568, 1);
		mainFrame.getContentPane().add(scrollPane);
		
		// ������ʾδ��������ű���
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 357, 568,310);
		scrollPane_1.setBackground(Color.BLACK);
		scrollPane_1.getVerticalScrollBar().setUI(null);
		scrollPane_1.setBorder(null);

		ListModel<Object> jList1Model =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());
        
        // ��д��ȡ�б�λ�õķ�����ʹ����б�հ״�������ѡ�����һ�������
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

        myJlist.setModel(jList1Model);            //��������
        myJlist.setBackground(Color.black);
        myJlist.setForeground(Color.lightGray);
        
        //��������
        myJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int index = myJlist.locationToIndex(e.getPoint());    //��ѡ����±�
            	if(index != -1){
            		myJlist.setSelectedIndex(index);
                    if(e.getClickCount() == 1 && e.getButton() == 1){  
                        logger.info("��ҳ�����δ��������--"+listData.notClassifiedNews.get(index).getTitle());
        				NewsContent newsContent = new NewsContent(listData.notClassifiedNews,index,filePath);
        				newsContent.setVisible(true);
                        LeftBehindChildren.mainFrame.dispose();                        
                    }
                    if(e.isMetaDown()) {
//                		�����Ҽ��˵�
                		JPopupMenu menu = new JPopupMenu();
                        JMenuItem item1 = new JMenuItem("ɾ��");
                      //��������
                        item1.addMouseListener(new MouseAdapter(){
                        	public void mouseReleased(MouseEvent e) {
                        		logger.info("ɾ��δ��������--"+listData.notClassifiedNews.get(index).getTitle());
                        		
//                        		dom4j.deleteNews(listData.notClassifiedNews.get(index));
                        		listData.notClassifiedNews.get(index).setIsDeleted("true");
                        		
                        		listData.deletedNews.add(listData.notClassifiedNews.get(index));
        						listData.deletedTitle.add(listData.notClassifiedNews.get(index).getTitle());
                        		listData.notClassifiedTitle.remove(index);//�������б���ɾ�������ű���
                        		listData.notClassifiedNews.remove(index);//�������б���ɾ��������
                        		ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//���°��б�ģ������
                        		myJlist.setModel(jList1Model1);//���°��б�ģ��
                        		myJlist.updateUI();//�����б�
                        	}
                        });
                        menu.add(item1);
                        menu.show(myJlist,e.getX(),e.getY());
//                        myJlist.setComponentPopupMenu(menu);//����ť���Ҽ��˵�����
                    }
            	}
            }
        });      

        scrollPane_1.setViewportView(myJlist);    //����ֱ��add
		mainFrame.getContentPane().add(scrollPane_1);
		
		// ��ʾ���֡����� �ѷ��ࡱ
		Label label_1 = new Label("\u65B0\u95FB  \u5DF2\u5206\u7C7B");
		label_1.setForeground(SystemColor.activeCaptionBorder);
		label_1.setFont(new Font("Dialog", Font.BOLD, 18));
		label_1.setBounds(600, 10, 174, 23);
		mainFrame.getContentPane().add(label_1);
		
		// ����ˮƽ�ָ���
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(600, 39, 370, 1);
		mainFrame.getContentPane().add(scrollPane_2);
		
		// ������ʾ�ѷ�������ű���
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(600, 41, 370, 537);
		scrollPane_3.setBackground(Color.BLACK);
		scrollPane_3.getVerticalScrollBar().setUI(null);
		scrollPane_3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_3.setBorder(null);
		
		
		ListModel<Object> jList1Model2 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());
		
		// ��д��ȡ�б�λ�õķ�����ʹ����б�հ״�������ѡ�����һ�������
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
		
        myJlist2.setModel(jList1Model2);            //��������
        myJlist2.setBackground(Color.black);
        myJlist2.setForeground(Color.lightGray);
        
      //��������
        myJlist2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	int index = myJlist2.locationToIndex(e.getPoint());    //��ѡ����±�
            	if(index != -1){
            		myJlist2.setSelectedIndex(index);
                    if(e.getClickCount() == 1 && e.getButton() == 1){  
//                        Object obj = myJlist.getModel().getElementAt(index);  //ȡ������
                        logger.info("��ҳ������ѷ�������--"+listData.classifiedNews.get(index).getTitle());
                        ClassifiedNewsContent newsContent = new ClassifiedNewsContent(listData.classifiedNews,index,filePath);
        				newsContent.setVisible(true);
                        LeftBehindChildren.mainFrame.dispose();
                    }
                    if(e.isMetaDown()) {
                		//�����Ҽ��˵�
                		JPopupMenu menu = new JPopupMenu();
                        JMenuItem item1 = new JMenuItem("ɾ��");
                      //��������
                        item1.addMouseListener(new MouseAdapter(){
                        	public void mouseReleased(MouseEvent e) {
                        		logger.info("ɾ���ѷ�������--"+listData.classifiedNews.get(index).getTitle());
                        		
//                        		dom4j.deleteNews(listData.classifiedNews.get(index));
                        		listData.classifiedNews.get(index).setIsDeleted("true");
                        		
                        		listData.deletedNews.add(listData.classifiedNews.get(index));
        						listData.deletedTitle.add(listData.classifiedNews.get(index).getTitle());
                        		listData.classifiedTitle.remove(index);//�������б���ɾ�������ű���
                        		listData.classifiedNews.remove(index);//�������б���ɾ��������
                        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//���°��б�ģ������
                        		myJlist2.setModel(jList1Model3);//���°��б�ģ��
                        		myJlist2.updateUI();//�����б�
                        	}
                        });
                        menu.add(item1);
                        menu.show(myJlist2,e.getX(),e.getY());
//                        myJlist2.setComponentPopupMenu(menu);//����ť���Ҽ��˵�����
                    }
            	}
            }
        });
        
        scrollPane_3.setViewportView(myJlist2);    //����ֱ��add
		mainFrame.getContentPane().add(scrollPane_3);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(600, 585, 370, 1);
		mainFrame.getContentPane().add(scrollPane_5);
		
		JButton btnNewButton = new JButton("\u7EDF\u8BA1\u7AD9");
		btnNewButton.setBackground(Color.black);
		btnNewButton.setForeground(Color.white);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logger.info("����ͳ��վ�����������������������ͳ��");
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
				logger.info("�������վ");
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
					//�ļ���ȷ����Ŀ¼
					SaveToXml saveToXml = new SaveToXml(filePath);
					logger.info("�رղ������ļ�"+filePath);
				}else{
					//�б��ж���ļ���û��ȷ��Ŀ¼
					SaveToXml saveToXml = new SaveToXml();
					logger.info("�ر���������");
				}
				
			}
		});
		
		
		JButton encodeToSave = new JButton("���ܱ���");
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
		                if (!file.exists()&&ifEncoded) {//�ļ������� �򴴽�һ��  
		                    file.createNewFile();  
		                }  
		            } catch (IOException e) {  
		                System.err.println("�ļ�����ʧ�ܣ�");  
		                logger.info("���ܱ��棺�ļ�����ʧ��");
		                e.printStackTrace();  
		            }
		            logger.info("���ܱ��棺�����ļ�·��Ϊ"+file.getPath());
		        } 
			}
		});
		encodeToSave.setBounds(320, 326, 90, 23);
		mainFrame.getContentPane().add(encodeToSave);
		
		JButton merge = new JButton("�ϲ��ļ�");
		merge.setBackground(Color.black);
		merge.setForeground(Color.white);
		merge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				SaveToXml saveToXml = new SaveToXml(filePath);
				// ���ļ�ѡ�񴰿� 
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
					logger.info("�ϲ��ļ�"+filePath+"��"+f.getPath());
					if(ifMerge){
						JOptionPane.showMessageDialog(null, "�ϲ��ɹ���", "�ϲ��ļ�", JOptionPane.INFORMATION_MESSAGE);
						logger.info("�ϲ��ɹ�");
					}else{
						JOptionPane.showMessageDialog(null, "�ϲ�ʧ�ܡ�", "�ϲ��ļ�", JOptionPane.INFORMATION_MESSAGE);
						logger.info("�ϲ�ʧ��");
					}
					ListData.getInstance().importFile(filePath);
					listData = ListData.getInstance();
					ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//���°��б�ģ������
	        		myJlist.setModel(jList1Model1);//���°��б�ģ��
	        		myJlist.updateUI();//�����б�
	        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//���°��б�ģ������
	        		myJlist2.setModel(jList1Model3);//���°��б�ģ��
	        		myJlist2.updateUI();//�����б�
				} else { 
				//û��ѡ���ļ� 
				} 
			}
		});
		merge.setBounds(410, 326, 90, 23);
		mainFrame.getContentPane().add(merge);
		
		if(!openFileTag){
			encodeToSave.setEnabled(false);
			merge.setEnabled(false);
		}
		
		
		JButton open = new JButton("���ļ�");
		open.setBackground(Color.black);
		open.setForeground(Color.white);
		open.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(openFileTag){
					SaveToXml saveToXml = new SaveToXml(filePath);
				}else{
					SaveToXml saveToXml = new SaveToXml();
				}
				// ���ļ�ѡ�񴰿� 
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
					logger.info("���ļ���"+filePath);
					ListData.getInstance().importFile(filePath);
					listData = ListData.getInstance();
					System.out.println(listData.newsList.size());
	        		ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//���°��б�ģ������
	        		myJlist.setModel(jList1Model1);//���°��б�ģ��
	        		myJlist.updateUI();//�����б�
	        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//���°��б�ģ������
	        		myJlist2.setModel(jList1Model3);//���°��б�ģ��
	        		myJlist2.updateUI();//�����б�
					
					openFileTag = true;
	        		encodeToSave.setEnabled(true);
	        		merge.setEnabled(true);
				} else { 
				//û��ѡ���ļ� 
				} 
			}
		});		
		open.setBounds(230, 326, 90, 23);
		mainFrame.getContentPane().add(open);
		
		
		
		JButton train = new JButton("��ѵ�û�");
		train.setBackground(Color.black);
		train.setForeground(Color.white);
		train.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.info("��ѵ�û�");
				// ���ļ�ѡ�񴰿� 
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
					JOptionPane.showMessageDialog(null, String.valueOf(trainUser.countOfSame()*100) + "%", "һ����", JOptionPane.INFORMATION_MESSAGE);
					logger.info("�ļ�һ���ԣ�"+String.valueOf(trainUser.countOfSame()*100) + "%");
				} else { 
				//û��ѡ���ļ� 
				} 
			}
		});
		train.setBounds(500, 326, 90, 23);
		mainFrame.getContentPane().add(train);
		
		JButton all = new JButton("��������");
		all.setBackground(Color.black);
		all.setForeground(Color.white);
		all.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logger.info("��ʾ��������");	
				//���֮ǰ���ᱣ��һ���ļ�
				if(openFileTag){
					SaveToXml saveToXml = new SaveToXml(filePath);
				}else{
					SaveToXml saveToXml = new SaveToXml();
				}
				ListData.getInstance().importAllFile();
				listData = ListData.getInstance();
				ListModel<Object> jList1Model1 =  new DefaultComboBoxModel<>(listData.notClassifiedTitle.toArray());//���°��б�ģ������
        		myJlist.setModel(jList1Model1);//���°��б�ģ��
        		myJlist.updateUI();//�����б�
        		ListModel<Object> jList1Model3 =  new DefaultComboBoxModel<>(listData.classifiedTitle.toArray());//���°��б�ģ������
        		myJlist2.setModel(jList1Model3);//���°��б�ģ��
        		myJlist2.updateUI();//�����б�
        		//�ر����ʱ�������filePath,�ж��Ƿ���ȷ��Ŀ¼���Ա��棬�����������ļ�����û�в���ҪfilePath
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

