package UI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import XmlData.Statistics;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Button;

public class StatisticsBin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton back;       //返回首页
	private JButton button_1;   //相关数量统计
	private JButton button_2;   //农民工子女无法在城市读书的原因统计
	private JButton button_3;   //新闻类型统计
	private JButton button_4;   //报道主题统计
	private JButton button_5;   //新闻消息来源统计
	private JButton button_6;   //媒介呈现形象统计
	private JButton button_7;   //新闻主体统计
	private JButton button_8;   //留守儿童遭性侵性别统计
	private JButton button_9;   //新闻具体种类统计
	private JButton button_10;  //留守儿童遭受暴力性别统计
	private JButton button_11;  //留守儿童犯罪性别统计
	private JButton button_12;  //积极健康形象性别统计
	
	private JPanel panel; 
	
	private Statistics statistics;
	private int[] type;
	private int[] theme;
	private int[] source;
	private int[] showing;
	private int[] reason;
	private int[] mainBody;
	private int[] helpType;
	private int[] sexualAssaultGender;
	private int[] violenceGender;
	private int[] crimeGender;
	private int[] positiveHealthGender;
	private Map<String,Integer> sum = new HashMap<String,Integer>();
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	public StatisticsBin() {
		
		statistics = new Statistics();
		type = statistics.getType();
		theme = statistics.getTheme();
		source = statistics.getSource();
		showing = statistics.getShowing();
		reason = statistics.getReason();
		mainBody = statistics.getMainBody();
		helpType = statistics.getHelpType();
		sexualAssaultGender = statistics.getSexualAssaultGender();
		violenceGender = statistics.getViolenceGender();
		crimeGender = statistics.getCrimeGender();
		positiveHealthGender = statistics.getPositiveHealthGender();
		sum = statistics.getSum();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("三报相关新闻总数量统计图");
		
		back = new JButton("\u8FD4\u56DE\u9996\u9875");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				LeftBehindChildren.mainFrame.setVisible(true);
			}
		});
		back.setBounds(25, 26, 93, 23);
		back.setBackground(Color.WHITE);
		contentPane.add(back);
		
		button_1 = new JButton("\u76F8\u5173\u65B0\u95FB\u6570\u91CF\u7EDF\u8BA1");
		button_1.setBackground(Color.YELLOW);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.removeAll();
				button_1.setBackground(Color.YELLOW);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("三报相关新闻总数量统计图");
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				for (int i = 0;i < 10;i ++)
				{
					dataset.addValue(sum.get(2006+i+""), 2006+i+"", 2006+i+"");
				}
				
				BarChart barchart = new BarChart(dataset,"三报相关新闻总数量统计图");
				panel.add(barchart.getChartPanel());
				panel.validate();
			}
		});
		button_1.setBounds(160, 26, 129, 23);
		contentPane.add(button_1);
		
		button_2 = new JButton("\u519C\u6C11\u5DE5\u5B50\u5973\u65E0\u6CD5\u57CE\u5E02\u8BFB\u4E66\u539F\u56E0\u7EDF\u8BA1");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.YELLOW);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("农民工子女无法城市读书统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("无本地户籍难入公立学校", reason[0]);
				data.setValue("私立学校学费高", reason[1]);
				data.setValue("越来越多小型私立学校被取消办学资格", reason[2]);
				data.setValue("私立学校办学质量没保障", reason[3]);
				data.setValue("其他", reason[4]);
				PieChart piechart = new PieChart(data,"农民工子女无法城市读书原因统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_2.setBounds(336, 26, 225, 23);
		contentPane.add(button_2);
		
		button_3 = new JButton("\u65B0\u95FB\u7C7B\u578B\u7EDF\u8BA1");
		button_3.setBackground(Color.WHITE);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.YELLOW);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("新闻类型统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("纯净新闻", type[0]);
				data.setValue("特稿特写", type[1]);
				data.setValue("评论", type[2]);
				data.setValue("其他", type[3]);
				PieChart piechart = new PieChart(data,"新闻类型统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_3.setBounds(586, 26, 225, 23);
		contentPane.add(button_3);
		
		button_4 = new JButton("\u62A5\u9053\u4E3B\u9898\u7EDF\u8BA1");
		button_4.setBackground(Color.WHITE);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.YELLOW);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("报道主题统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("帮助关爱", theme[0]);
				data.setValue("表彰鼓励", theme[1]);
				data.setValue("留守儿童努力向上", theme[2]);
				data.setValue("建议看法", theme[3]);
				data.setValue("打工父母艰难生活", theme[4]);
				data.setValue("留守儿童性侵", theme[5]);
				data.setValue("留守儿童遭暴力", theme[6]);
				data.setValue("留守儿童犯罪", theme[7]);
				data.setValue("其他", theme[8]);
				PieChart piechart = new PieChart(data,"报道主题统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_4.setBounds(160, 78, 129, 23);
		contentPane.add(button_4);
		
		button_5 = new JButton("\u65B0\u95FB\u62A5\u9053\u6D88\u606F\u6765\u6E90\u7EDF\u8BA1");
		button_5.setBackground(Color.WHITE);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.YELLOW);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("新闻报道来源统计");
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				dataset.addValue(source[0],"记者", "记者");
				dataset.addValue(source[1],"政府", "政府");
				dataset.addValue(source[2],"企业", "企业");
				dataset.addValue(source[3],"事业单位", "事业单位");
				dataset.addValue(source[4],"公益团体", "公益团体");
				dataset.addValue(source[5],"专家学者", "专家学者");
				dataset.addValue(source[6],"其他", "其他");
				BarChart barchart = new BarChart(dataset,"新闻报道消息来源统计图");
				panel.add(barchart.getChartPanel());
				panel.validate();
			}
		});
		button_5.setBounds(336, 78, 188, 23);
		contentPane.add(button_5);
		
		button_6 = new JButton("\u5A92\u4ECB\u5448\u73B0\u5F62\u8C61\u7EDF\u8BA1");
		button_6.setBackground(Color.WHITE);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.YELLOW);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("媒介呈现形象统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("可怜悲惨的形象", showing[0]);
				data.setValue("沐恩幸福的形象", showing[1]);
				data.setValue("积极健康的形象", showing[2]);
				data.setValue("问题儿童的形象", showing[3]);
				data.setValue("其他", showing[4]);
				PieChart piechart = new PieChart(data,"媒介形象统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_6.setBounds(586, 78, 188, 23);
		contentPane.add(button_6);
		
		panel = new JPanel();
		panel.setBounds(24, 200, 787, 452);
		contentPane.add(panel);
		
		button_7 = new JButton("新闻主体统计");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.YELLOW);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("新闻主体统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("政府部门", mainBody[0]);
				data.setValue("企业", mainBody[1]);
				data.setValue("事业单位", mainBody[2]);
				data.setValue("公益团体", mainBody[3]);
				data.setValue("其他", mainBody[4]);
				PieChart piechart = new PieChart(data,"新闻主体统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_7.setBackground(Color.WHITE);
		button_7.setBounds(160, 123, 129, 23);
		contentPane.add(button_7);
		
		button_8 = new JButton("留守儿童遭性侵性别统计");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.YELLOW);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("留守儿童遭性侵性别统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("男", sexualAssaultGender[0]);
				data.setValue("女", sexualAssaultGender[1]);
				PieChart piechart = new PieChart(data,"留守儿童遭性侵性别统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_8.setBackground(Color.WHITE);
		button_8.setBounds(542, 129, 176, 23);
		contentPane.add(button_8);
		
		button_9 = new JButton("新闻具体种类统计");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.YELLOW);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("新闻具体种类统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("单纯一次捐款捐物", helpType[0]);
				data.setValue("旅游活动安排的项目之一", helpType[1]);
				data.setValue("免费开放", helpType[2]);
				data.setValue("设立长期资助项目", helpType[3]);
				data.setValue("其他", helpType[4]);
				PieChart piechart = new PieChart(data,"新闻具体种类统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_9.setBackground(Color.WHITE);
		button_9.setBounds(336, 129, 129, 23);
		contentPane.add(button_9);
		
		button_10 = new JButton("留守儿童遭受暴力性别统计");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.YELLOW);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.WHITE);
				setTitle("留守儿童遭受暴力性别统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("男", violenceGender[0]);
				data.setValue("女", violenceGender[1]);
				PieChart piechart = new PieChart(data,"留守儿童遭受暴力性别统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_10.setBackground(Color.WHITE);
		button_10.setBounds(160, 167, 176, 23);
		contentPane.add(button_10);
		
		button_11 = new JButton("留守儿童犯罪性别统计");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.YELLOW);
				button_12.setBackground(Color.WHITE);
				setTitle("留守儿童犯罪性别统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("男", crimeGender[0]);
				data.setValue("女", crimeGender[1]);
				PieChart piechart = new PieChart(data,"留守儿童犯罪性别统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_11.setBackground(Color.WHITE);
		button_11.setBounds(365, 167, 176, 23);
		contentPane.add(button_11);
		
		button_12 = new JButton("积极健康形象性别统计");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				button_7.setBackground(Color.WHITE);
				button_8.setBackground(Color.WHITE);
				button_9.setBackground(Color.WHITE);
				button_10.setBackground(Color.WHITE);
				button_11.setBackground(Color.WHITE);
				button_12.setBackground(Color.YELLOW);
				setTitle("积极健康形象性别统计");
				DefaultPieDataset data = new DefaultPieDataset();
				data.setValue("男", positiveHealthGender[0]);
				data.setValue("女", positiveHealthGender[1]);
				PieChart piechart = new PieChart(data,"积极健康形象性别统计");
				panel.add(piechart.getPieChartPanel());
				panel.validate();
			}
		});
		button_12.setBackground(Color.WHITE);
		button_12.setBounds(568, 167, 176, 23);
		contentPane.add(button_12);
		
		
	}
}
