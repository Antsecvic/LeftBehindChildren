package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StatisticsBin extends JFrame {

	private JPanel contentPane;
	private JButton back;       //返回首页
	private JButton button_1;   //相关数量统计
	private JButton button_2;   //农民工子女无法在城市读书的原因统计
	private JButton button_3;   //新闻类型统计
	private JButton button_4;   //报道主题统计
	private JButton button_5;   //新闻消息来源统计
	private JButton button_6;   //媒介呈现形象统计
	

	/**
	 * Create the frame.
	 */
	public StatisticsBin() {
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
		button_1.setBackground(Color.WHITE);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				button_1.setBackground(Color.YELLOW);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				setTitle("三报相关新闻总数量统计图");
			}
		});
		button_1.setBounds(160, 26, 129, 23);
		contentPane.add(button_1);
		
		button_2 = new JButton("\u519C\u6C11\u5DE5\u5B50\u5973\u65E0\u6CD5\u57CE\u5E02\u8BFB\u4E66\u539F\u56E0\u7EDF\u8BA1");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.YELLOW);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				setTitle("农民工子女无法城市读书统计");
			}
		});
		button_2.setBounds(336, 26, 225, 23);
		contentPane.add(button_2);
		
		button_3 = new JButton("\u65B0\u95FB\u7C7B\u578B\u7EDF\u8BA1");
		button_3.setBackground(Color.WHITE);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.YELLOW);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				setTitle("新闻类型统计");
			}
		});
		button_3.setBounds(586, 26, 225, 23);
		contentPane.add(button_3);
		
		button_4 = new JButton("\u62A5\u9053\u4E3B\u9898\u7EDF\u8BA1");
		button_4.setBackground(Color.WHITE);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.YELLOW);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.WHITE);
				setTitle("报道主题统计");
			}
		});
		button_4.setBounds(160, 78, 129, 23);
		contentPane.add(button_4);
		
		button_5 = new JButton("\u65B0\u95FB\u62A5\u9053\u6D88\u606F\u6765\u6E90\u7EDF\u8BA1");
		button_5.setBackground(Color.WHITE);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.YELLOW);
				button_6.setBackground(Color.WHITE);
				setTitle("新闻报道来源统计");
			}
		});
		button_5.setBounds(336, 78, 188, 23);
		contentPane.add(button_5);
		
		button_6 = new JButton("\u5A92\u4ECB\u5448\u73B0\u5F62\u8C61\u7EDF\u8BA1");
		button_6.setBackground(Color.WHITE);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_1.setBackground(Color.WHITE);
				button_2.setBackground(Color.WHITE);
				button_3.setBackground(Color.WHITE);
				button_4.setBackground(Color.WHITE);
				button_5.setBackground(Color.WHITE);
				button_6.setBackground(Color.YELLOW);
				setTitle("媒介呈现形象统计");
			}
		});
		button_6.setBounds(586, 78, 188, 23);
		contentPane.add(button_6);
		
	}

}
