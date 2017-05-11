package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import XmlData.Dom4j;
import XmlData.News;
import XmlData.SaveToXml;
import XmlData.Tags;

@SuppressWarnings("serial")
public class ClassifiedNewsContent extends JFrame{
	public static Logger logger = LogManager.getLogger(ClassifiedNewsContent.class.getName());
	public JPanel contentPane;
	private JEditorPane textArea;
	private JScrollPane mainBody;
	private List<News> newsList;
	private int position;
	private Tags tags;
	private ListData listData;
	

	public ClassifiedNewsContent(List<News> newsList,int position) {
		this.newsList = newsList;
		this.position = position;
		tags = new Tags();
		listData = ListData.getInstance();
		if(newsList.get(position).getTagIts().equals("true")){
			tags = newsList.get(position).getTags();
		}
		initialize();
	}
	
	//����������ʾ����
	public void showNewsDetails(News news){
		
		if (news.getEncodedContent().equals(""))
		{
			textArea.setContentType("text/html");
			textArea.setText(news.getTitle()+"\n\n"+ getNewsFromUrl(news.getTrueUrl()));
			Font font = new Font("����",Font.BOLD,18);
			textArea.setFont(font);
			textArea.setCaretPosition(0);		//���ù��λ��Ϊ����
		}
		else
		{
			textArea.setContentType("text/plain");
			textArea.setText(news.getTitle()+"\n\n"+news.getEncodedContent());
			Font font = new Font("����",Font.BOLD,18);
			textArea.setFont(font);
			textArea.setEditable(false);
			textArea.setCaretPosition(0);		//���ù��λ��Ϊ����
		}
	}
	
	//��ʼ������Ͱ�ť
	public void initialize(){	
		setTitle("��������");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		setBounds(300, 50, 1000, 700);
		setLocation((int)(Toolkit.getDefaultToolkit().getScreenSize().
                getWidth()-1000)/2,(int)(Toolkit.getDefaultToolkit().getScreenSize().
                        getHeight()-700)/2);
		setSize(1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(UIManager.getBorder("ComboBox.border"));
		setContentPane(contentPane);
		
		
		//��ʾ�������ݵİ��
		textArea=new JEditorPane();
		textArea.setEditable(false);
//		textArea.setContentType("text/html");
		showNewsDetails(newsList.get(position));
		textArea.setBackground(Color.lightGray);
		contentPane.setLayout(null);
		
		mainBody = new JScrollPane(textArea);
		mainBody.setBounds(5, 5, 600, 600);
		mainBody.setBackground(Color.LIGHT_GRAY);
		mainBody.getVerticalScrollBar().setUI(null);
		mainBody.getHorizontalScrollBar().setUI(null);
		
		contentPane.add(mainBody);
				
		//��һƪ��ť
		JButton button = new JButton("��һƪ");
		button.setBounds(631, 22, 93, 23);
		button.setBackground(Color.LIGHT_GRAY);
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
		button_1.setBackground(Color.LIGHT_GRAY);
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
		button_2.setBackground(Color.LIGHT_GRAY);
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
		scrollPane_1.setBounds(611, 72, 400, 1);
		contentPane.add(scrollPane_1);
		
		Label label = new Label("Ϊ������ѡ���ǩ");
		label.setBounds(611, 51, 103, 23);
		label.setForeground(Color.white);
		contentPane.add(label);
		
		//��ʾ��ֽ������ֱ�ֽ��
		Label label_1 = new Label("��ֽ���");
		label_1.setBounds(621, 80, 51, 23);
		label_1.setBackground(Color.gray);
		label_1.setForeground(Color.white);
		contentPane.add(label_1);
		Label location = new Label(newsList.get(position).getLocation());
		location.setBounds(678, 80, 146, 23);
		location.setForeground(Color.white);
		contentPane.add(location);
		
		//��ʾ��ֽ����
		Label label_3 = new Label("��ֽ����");
		label_3.setBounds(621, 115, 51, 23);
		label_3.setBackground(Color.gray);
		label_3.setForeground(Color.white);
		contentPane.add(label_3);
		
		//��ʾ��������
		Label label_2 = new Label("��������");
		label_2.setBounds(621, 150, 51, 23);
		label_2.setBackground(Color.gray);
		label_2.setForeground(Color.white);
		contentPane.add(label_2);
		
		Label label_7 = new Label("��������");
		label_7.setBounds(621, 185, 51, 23);
		label_7.setBackground(Color.gray);
		label_7.setForeground(Color.white);
		contentPane.add(label_7);
		
		Label label_8 = new Label("��������");
		label_8.setBounds(621, 220, 51, 23);
		label_8.setBackground(Color.gray);
		label_8.setForeground(Color.white);
		contentPane.add(label_8);
		
		Label label_9 = new Label("�Ա�");
		label_9.setBounds(621, 255, 27, 23);
		label_9.setBackground(Color.gray);
		label_9.setForeground(Color.white);
		contentPane.add(label_9);
		
		Label label_4 = new Label("���ű�����Ϣ��Դ");
		label_4.setBounds(621, 290, 103, 23);
		label_4.setBackground(Color.gray);
		label_4.setForeground(Color.white);
		contentPane.add(label_4);
		
		Label label_5 = new Label("ý���������");
		label_5.setBounds(621, 325, 79, 23);
		label_5.setBackground(Color.gray);
		label_5.setForeground(Color.white);
		contentPane.add(label_5);
		
		Label label_6 = new Label("ũ����Ů�������ڳ��ж����ԭ��");
		label_6.setBounds(621, 360, 195, 23);
		label_6.setBackground(Color.gray);
		label_6.setForeground(Color.white);
		contentPane.add(label_6);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(615, 531, 400, 2);
		contentPane.add(scrollPane_2);
		
		
		
		
		Label type = new Label(tags.getType());
		type.setBounds(678, 115, 100, 23);
		type.setForeground(Color.white);
		contentPane.add(type);
		
		Label theme = new Label(tags.getTheme());
//		theme.setFont(new Font("����", Font.PLAIN, 11));
		theme.setBounds(678, 150, 100, 23);
		theme.setForeground(Color.white);
		contentPane.add(theme);
		
		Label mainBody = new Label(tags.getMainBody());
//		mainBody.setFont(new Font("����", Font.PLAIN, 11));
		mainBody.setBounds(678, 185, 100, 23);
		mainBody.setForeground(Color.white);
		contentPane.add(mainBody);
		
		Label helpType = new Label(tags.getHelpType());
//		helpType.setFont(new Font("����", Font.PLAIN, 11));
		helpType.setBounds(678, 220, 100, 23);
		helpType.setForeground(Color.white);
		contentPane.add(helpType);
		
		Label gender = new Label(tags.getGender());
//		gender.setFont(new Font("����", Font.PLAIN, 11));
		gender.setBounds(660, 255, 100, 23);
		gender.setForeground(Color.white);
		contentPane.add(gender);
		
		Label source = new Label(tags.getSource());
//		source.setFont(new Font("����", Font.PLAIN, 11));
		source.setBounds(730, 290, 100, 23);
		source.setForeground(Color.white);
		contentPane.add(source);
		
		Label mediaImage = new Label(tags.getShowing());
//		mediaImage.setFont(new Font("����", Font.PLAIN, 11));
		mediaImage.setBounds(703, 325, 150, 23);
		mediaImage.setForeground(Color.white);
		contentPane.add(mediaImage);
		
		Label reason = new Label(tags.getReason());
//		reason.setFont(new Font("����", Font.PLAIN, 11));
		reason.setBounds(820, 360, 150, 23);
		reason.setForeground(Color.white);
		contentPane.add(reason);
		
		JButton reset = new JButton("���ñ�ǩ");
		reset.setBounds(858, 542, 93, 23);
		reset.setBackground(Color.LIGHT_GRAY);
		contentPane.add(reset);
		
		reset.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������								
				String id = newsList.get(position).getID();
				tags.setType("");
				tags.setTheme("");
				tags.setSource("");
				tags.setShowing("");
				tags.setReason("");
				tags.setMainBody("");
				tags.setHelpType("");
				tags.setGender("");
				modifyList();
				dispose();
				NewsContent newsContent = 
						new NewsContent(listData.notClassifiedNews,
								listData.findPosition(listData.notClassifiedNews, id));
				newsContent.setVisible(true);
			}			
		});
		
		this.addWindowListener(new WindowAdapter() {  
			public void windowClosing(WindowEvent e) {  
				super.windowClosing(e);  
				@SuppressWarnings("unused")
				SaveToXml saveToXml = new SaveToXml();
			}
		});
	}
	public void modifyList(){
		Dom4j dom4j = new Dom4j();
		newsList.get(position).setTags(tags);
		newsList.get(position).setTagIts("false");
		dom4j.modifyXml(newsList.get(position));
		listData.notClassifiedTitle.add(newsList.get(position).getTitle());
		listData.notClassifiedNews.add(newsList.get(position));
		listData.classifiedTitle.remove(newsList.get(position).getTitle());
		listData.classifiedNews.remove(newsList.get(position));

	}
	
	// ��urlץȡ�������ݵ�p��ǩ�е�����
		private String getNewsFromUrl(String trueUrl){
			try {
				org.jsoup.nodes.Document doc = Jsoup.connect(trueUrl).get();
				Elements pData = doc.getElementsByTag("p");
				StringBuilder newsContent = new StringBuilder("<html><body>");
				for(org.jsoup.nodes.Element element: pData){
					newsContent.append("\n<p>"+element.text()+"</p>");
				}
				newsContent.append("</body></html>");
				return newsContent.toString();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
}
