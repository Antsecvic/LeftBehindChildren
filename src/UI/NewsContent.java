package UI;

import XmlData.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Label;
import java.awt.Button;
import java.awt.Choice;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.swing.JEditorPane;

public class NewsContent extends JFrame {
	public static Logger logger = LogManager.getLogger(NewsContent.class.getName());
	public JPanel contentPane;
	private JTextArea textArea;
	private JButton browse;
	private JScrollPane mainBody;
	private List<News> newsList;
	private int position;
	private JButton showExternalNews = new JButton("\u52A0\u8F7D\u5916\u90E8\u65B0\u95FB");

	public NewsContent(List<News> newsList,int position) {
		this.newsList = newsList;
		this.position = position;
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
				LeftBehindChildren window = new LeftBehindChildren();
				window.mainFrame.setVisible(true);				
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
		
		Label label_1 = new Label("��ֽ���");
		label_1.setBounds(621, 80, 51, 23);
		label_1.setBackground(Color.WHITE);
		contentPane.add(label_1);
		
		Label label_3 = new Label("��ֽ����");
		label_3.setBounds(621, 116, 51, 23);
		label_3.setBackground(Color.WHITE);
		contentPane.add(label_3);
		
		Button button_3 = new Button("�����ձ�����һ���ձ���");
		button_3.setBounds(678, 80, 146, 23);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_3.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_3);
		
		Button button_4 = new Button("��������");
		button_4.setBounds(678, 116, 62, 23);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				button_4.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_4);
		
		Button button_5 = new Button("�ظ���д");
		button_5.setBounds(746, 116, 62, 23);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_5.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_5);
		
		Button button_6 = new Button("����");
		button_6.setBounds(814, 116, 62, 23);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_6.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_6);
		
		Button button_7 = new Button("����");
		button_7.setBounds(882, 116, 62, 23);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_7.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_7);
		
		Label label_2 = new Label("��������");
		label_2.setBounds(621, 155, 51, 23);
		label_2.setBackground(Color.WHITE);
		contentPane.add(label_2);
		
		Button button_8 = new Button("�����ذ�");
		button_8.setBounds(678, 155, 62, 23);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_8.setBackground(Color.yellow);
				
			}
		});
		contentPane.add(button_8);
		
		Button button_9 = new Button("���ù���");
		button_9.setBounds(746, 155, 62, 23);
		contentPane.add(button_9);
		
		Button button_10 = new Button("���鿴��");
		button_10.setBounds(678, 184, 62, 23);
		contentPane.add(button_10);
		
		Button button_11 = new Button("���ض�ͯŬ������");
		button_11.setBounds(814, 155, 103, 23);
		contentPane.add(button_11);
		
		Button button_12 = new Button("�򹤸�ĸ��������");
		button_12.setBounds(746, 184, 103, 23);
		contentPane.add(button_12);
		
		Button button_13 = new Button("���ض�ͯ������");
		button_13.setBounds(861, 184, 103, 23);
		contentPane.add(button_13);
		
		Button button_14 = new Button("���ض�ͯ�Ⱪ��");
		button_14.setBounds(678, 213, 103, 23);
		contentPane.add(button_14);
		
		Button button_15 = new Button("���ض�ͯ����");
		button_15.setBounds(787, 213, 86, 23);
		contentPane.add(button_15);
		
		Button button_16 = new Button("����");
		button_16.setBounds(882, 213, 68, 23);
		contentPane.add(button_16);
		
		Label label_4 = new Label("���ű�����Ϣ��Դ");
		label_4.setBounds(621, 278, 103, 23);
		label_4.setBackground(Color.WHITE);
		contentPane.add(label_4);
		
		Button button_17 = new Button("����");
		button_17.setBounds(734, 278, 35, 23);
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_17.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_17);
		
		Button button_18 = new Button("����");
		button_18.setBounds(773, 278, 35, 23);
		contentPane.add(button_18);
		
		Button button_19 = new Button("��ҵ");
		button_19.setBounds(814, 278, 35, 23);
		contentPane.add(button_19);
		
		Button button_20 = new Button("��ҵ��λ");
		button_20.setBounds(855, 278, 62, 23);
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(button_20);
		
		Button button_21 = new Button("��������");
		button_21.setBounds(678, 307, 62, 23);
		contentPane.add(button_21);
		
		Button button_22= new Button("ר��ѧ��");
		button_22.setBounds(746, 307, 62, 23);
		contentPane.add(button_22);
		
		Button button_23 = new Button("�����쵼");
		button_23.setBounds(814, 307, 62, 23);
		contentPane.add(button_23);
		
		Button button_24 = new Button("����");
		button_24.setBounds(882, 307, 62, 23);
		contentPane.add(button_24);
		
		Choice choice = new Choice();
		choice.setBounds(678, 242, 62, 21);
		contentPane.add(choice);
		choice.add("��������");
		choice.add("��������");
		choice.add("��ҵ");
		choice.add("��ҵ��λ");
		choice.add("��������");
		choice.add("����");
		choice.select(0);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(746, 242, 62, 21);
		contentPane.add(choice_1);
		choice_1.add("��������");
		choice_1.add("����һ�ξ�����");
		choice_1.add("���λ���ŵ���Ŀ֮һ");
		choice_1.add("��ѿ���");
		choice_1.add("��������������Ŀ");
		choice_1.add("����");
		choice_1.select(0);
		
		Choice choice_2 = new Choice();
		choice_2.setBounds(814, 242, 62, 21);
		contentPane.add(choice_2);
		
		Label label_5 = new Label("ý���������");
		label_5.setBounds(621, 339, 79, 23);
		label_5.setBackground(Color.WHITE);
		contentPane.add(label_5);
		
		Button button_25 = new Button("�������ҵ�����");
		button_25.setBounds(706, 339, 102, 23);
		button_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_25.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_25);
		
		Button button_26 = new Button("����Ҹ�������");
		button_26.setBounds(824, 339, 102, 23);
		contentPane.add(button_26);
		
		Button button_27 = new Button("��������������");
		button_27.setBounds(678, 368, 102, 23);
		contentPane.add(button_27);
		
		Button button_28 = new Button("�����ͯ������");
		button_28.setBounds(787, 368, 102, 23);
		contentPane.add(button_28);
		
		Button button_29 = new Button("����");
		button_29.setBounds(895, 368, 35, 23);
		contentPane.add(button_29);
		
		Label label_6 = new Label("ũ����Ů�������ڳ��ж����ԭ��");
		label_6.setBounds(621, 403, 195, 23);
		label_6.setBackground(Color.WHITE);
		contentPane.add(label_6);
		
		Button button_30 = new Button("�ޱ��ػ������빫��ѧУ");
		button_30.setBounds(678, 430, 146, 23);
		button_30.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_30.setBackground(Color.yellow);
			}
		});
		contentPane.add(button_30);
		
		Button button_31 = new Button("˽��ѧУѧ�Ѹ�");
		button_31.setBounds(834, 430, 102, 23);
		contentPane.add(button_31);
		
		Button button_32 = new Button("Խ��Խ��С��˽��ѧУ��ȡ����ѧ�ʸ�");
		button_32.setBounds(678, 459, 211, 23);
		contentPane.add(button_32);
		
		Button button_33 = new Button("˽��ѧУ��ѧ����û����");
		button_33.setBounds(679, 488, 145, 23);
		contentPane.add(button_33);
		
		Button button_34 = new Button("����");
		button_34.setBounds(834, 488, 102, 23);
		contentPane.add(button_34);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(615, 531, 400, 2);
		contentPane.add(scrollPane_2);
		
		JButton button_35 = new JButton("���");
		button_35.setBounds(858, 542, 93, 23);
		contentPane.add(button_35);
		
		
		showExternalNews.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String url = newsList.get(position).getTrueUrl();
				BareBonesBrowserLaunch.openURL(url);
			}
		});
		showExternalNews.setBounds(706, 542, 118, 23);
		contentPane.add(showExternalNews);

		choice_2.add("�Ա�");
		choice_2.add("��");
		choice_2.add("Ů");
		choice_2.select(0);
	    
		setTitle("��������");
	}
}
