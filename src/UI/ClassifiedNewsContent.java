package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import XmlData.BareBonesBrowserLaunch;
import XmlData.Dom4j;
import XmlData.News;
import XmlData.Tags;

@SuppressWarnings("serial")
public class ClassifiedNewsContent extends JFrame{
	public static Logger logger = LogManager.getLogger(ClassifiedNewsContent.class.getName());
	public JPanel contentPane;
	private JTextArea textArea;
	private JScrollPane mainBody;
	private List<News> newsList;
	private int position;
	private JButton showExternalNews = new JButton("�����ⲿ����");
	private Tags tags;
	

	public ClassifiedNewsContent(List<News> newsList,int position) {
		this.newsList = newsList;
		this.position = position;
		tags = new Tags();
		tags = newsList.get(position).getTags();
		initialize();
	}
	
	//����������ʾ����
	public void showNewsDetails(News news){
		
		if (news.getEncodedContent().equals(""))
		{
			showExternalNews.setEnabled(true);
		}
		else
		{
	
			showExternalNews.setEnabled(false);
			textArea.setText(news.getTitle()+"\n\n"+news.getEncodedContent());
		    textArea.setLineWrap(true);                 //�����Զ����й��� 
		    textArea.setWrapStyleWord(true);            // ������в����ֹ���
			Font font = new Font("����",Font.BOLD,20);
			textArea.setFont(font);
			textArea.setEditable(false);
			textArea.setCaretPosition(0);		//���ù��λ��Ϊ����
		}
	}
	
	//��ʼ������Ͱ�ť
	public void initialize(){	
		setTitle("��������");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		
		
		//��ʾ�������ݵİ��
		textArea=new JTextArea(newsList.get(position).getTitle(),20,43);
		textArea.setEditable(false);
		showNewsDetails(newsList.get(position));
		contentPane.setLayout(null);
		
		mainBody = new JScrollPane(textArea);
		mainBody.setBounds(5, 5, 600, 600);
		
		contentPane.add(mainBody);
				
		//��һƪ��ť
		JButton button = new JButton("��һƪ");
		button.setBounds(631, 22, 93, 23);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(position != 0){
					logger.info("�����һƪ������--"+newsList.get(position-1).getTitle());
					showNewsDetails(newsList.get(--position));
				}
			}
		});
		contentPane.add(button);
		
		//��ҳ��ť
		JButton button_1 = new JButton("��ҳ");
		button_1.setBounds(746, 22, 93, 23);
		button_1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				logger.info("������ҳ");
				setVisible(false);
				dispose();
				new LeftBehindChildren();
				LeftBehindChildren.mainFrame.setVisible(true);			
			}
		});
		contentPane.add(button_1);
		
		//��һƪ��ť
		JButton button_2 = new JButton("��һƪ");
		button_2.setBounds(858, 22, 93, 23);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(position != newsList.size()-1){
					logger.info("�����һƪ������--"+newsList.get(position+1).getTitle());
					showNewsDetails(newsList.get(++position));
				}
			}
		});
		contentPane.add(button_2);
				
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(611, 68, 400, 2);
		contentPane.add(scrollPane_1);
		
		Label label = new Label("Ϊ������ѡ���ǩ");
		label.setBounds(611, 51, 103, 23);
		contentPane.add(label);
		
		//��ʾ��ֽ������ֱ�ֽ��
		Label label_1 = new Label("��ֽ���");
		label_1.setBounds(621, 80, 51, 23);
		label_1.setBackground(Color.WHITE);
		contentPane.add(label_1);
		Label location = new Label(newsList.get(position).getLocation());
		location.setBounds(678, 80, 146, 23);
		contentPane.add(location);
		
		//��ʾ��ֽ����
		Label label_3 = new Label("��ֽ����");
		label_3.setBounds(621, 115, 51, 23);
		label_3.setBackground(Color.WHITE);
		contentPane.add(label_3);
		
		//��ʾ��������
		Label label_2 = new Label("��������");
		label_2.setBounds(621, 150, 51, 23);
		label_2.setBackground(Color.WHITE);
		contentPane.add(label_2);
		
		Label label_7 = new Label("��������");
		label_7.setBounds(621, 185, 51, 23);
		label_7.setBackground(Color.WHITE);
		contentPane.add(label_7);
		
		Label label_8 = new Label("��������");
		label_8.setBounds(621, 220, 51, 23);
		label_8.setBackground(Color.WHITE);
		contentPane.add(label_8);
		
		Label label_9 = new Label("�Ա�");
		label_9.setBounds(621, 255, 27, 23);
		label_9.setBackground(Color.WHITE);
		contentPane.add(label_9);
		
		Label label_4 = new Label("���ű�����Ϣ��Դ");
		label_4.setBounds(621, 290, 103, 23);
		label_4.setBackground(Color.WHITE);
		contentPane.add(label_4);
		
		Label label_5 = new Label("ý���������");
		label_5.setBounds(621, 325, 79, 23);
		label_5.setBackground(Color.WHITE);
		contentPane.add(label_5);
		
		Label label_6 = new Label("ũ����Ů�������ڳ��ж����ԭ��");
		label_6.setBounds(621, 360, 195, 23);
		label_6.setBackground(Color.WHITE);
		contentPane.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(615, 531, 400, 2);
		contentPane.add(scrollPane_2);
		
		
		//������ⲿ����
		showExternalNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = newsList.get(position).getTrueUrl();
				BareBonesBrowserLaunch.openURL(url);
			}
		});
		showExternalNews.setBounds(706, 542, 118, 23);
		contentPane.add(showExternalNews);
		
		Label type = new Label(tags.getType());
		type.setBounds(678, 115, 100, 23);
		contentPane.add(type);
		
		Label theme = new Label(tags.getTheme());
//		theme.setFont(new Font("����", Font.PLAIN, 11));
		theme.setBounds(678, 150, 100, 23);
		contentPane.add(theme);
		
		Label mainBody = new Label(tags.getMainBody());
//		mainBody.setFont(new Font("����", Font.PLAIN, 11));
		mainBody.setBounds(678, 185, 100, 23);
		contentPane.add(mainBody);
		
		Label helpType = new Label(tags.getHelpType());
//		helpType.setFont(new Font("����", Font.PLAIN, 11));
		helpType.setBounds(678, 220, 100, 23);
		contentPane.add(helpType);
		
		Label gender = new Label(tags.getGender());
//		gender.setFont(new Font("����", Font.PLAIN, 11));
		gender.setBounds(660, 255, 100, 23);
		contentPane.add(gender);
		
		Label source = new Label(tags.getSource());
//		source.setFont(new Font("����", Font.PLAIN, 11));
		source.setBounds(730, 290, 100, 23);
		contentPane.add(source);
		
		Label mediaImage = new Label(tags.getShowing());
//		mediaImage.setFont(new Font("����", Font.PLAIN, 11));
		mediaImage.setBounds(703, 325, 150, 23);
		contentPane.add(mediaImage);
		
		Label reason = new Label(tags.getReason());
//		reason.setFont(new Font("����", Font.PLAIN, 11));
		System.out.println(tags.getReason());
		reason.setBounds(820, 360, 150, 23);
		contentPane.add(reason);
		
		JButton reset = new JButton("���ñ�ǩ");
		reset.setBounds(858, 542, 93, 23);
		contentPane.add(reset);
		
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������								
				Dom4j dom4j = new Dom4j();
				tags.setType("");
				tags.setTheme("");
				tags.setSource("");
				tags.setShowing("");
				tags.setReason("");
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
				newsList.get(position).setTags(tags);
				newsList.get(position).setTagIts("false");
				dom4j.modifyXml(newsList.get(position));
				dispose();
				ListData listData = new ListData();
				NewsContent newsContent = 
						new NewsContent(listData.notClassifiedNews,
								listData.findPosition(listData.notClassifiedNews, newsList.get(position)));
				newsContent.setVisible(true);
			}			
		});
	}
}