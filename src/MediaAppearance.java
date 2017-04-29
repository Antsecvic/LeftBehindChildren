import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MediaAppearance extends JFrame {

	private JPanel contentPane;



	/**
	 * Create the frame.
	 */
	public MediaAppearance() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("媒介呈现形象统计");
		
		JButton button = new JButton("\u8FD4\u56DE\u9996\u9875");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				LeftBehindChildren.mainFrame.setVisible(true);
			}
		});
		button.setBounds(25, 26, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u76F8\u5173\u65B0\u95FB\u6570\u91CF\u7EDF\u8BA1");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				StatisticsBin statis = new StatisticsBin();
				statis.setVisible(true);
			}
		});
		button_1.setBounds(160, 26, 129, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u519C\u6C11\u5DE5\u5B50\u5973\u65E0\u6CD5\u57CE\u5E02\u8BFB\u4E66\u539F\u56E0\u7EDF\u8BA1");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ReasonOfCannotCityStudy reason = new ReasonOfCannotCityStudy();
				reason.setVisible(true);
			}
		});
		button_2.setBounds(336, 26, 225, 23);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u65B0\u95FB\u7C7B\u578B\u7EDF\u8BA1");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NewsType newsType = new NewsType();
				newsType.setVisible(true);
			}
		});
		button_3.setBounds(586, 26, 225, 23);
		contentPane.add(button_3);
		
		JButton button_4 = new JButton("\u62A5\u9053\u4E3B\u9898\u7EDF\u8BA1");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ReportTheme reportTheme = new ReportTheme();
				reportTheme.setVisible(true);
			}
		});
		button_4.setBounds(160, 78, 129, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("\u65B0\u95FB\u62A5\u9053\u6D88\u606F\u6765\u6E90\u7EDF\u8BA1");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ReportSource reportSource = new ReportSource();
				reportSource.setVisible(true);
			}
		});
		button_5.setBounds(336, 78, 188, 23);
		contentPane.add(button_5);
		
		JButton button_6 = new JButton("\u5A92\u4ECB\u5448\u73B0\u5F62\u8C61\u7EDF\u8BA1");
		button_6.setEnabled(false);
		button_6.setBounds(586, 78, 188, 23);
		contentPane.add(button_6);
	}

}
