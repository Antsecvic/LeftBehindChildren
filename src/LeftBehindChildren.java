import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeftBehindChildren {

	public static JFrame mainFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeftBehindChildren window = new LeftBehindChildren();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LeftBehindChildren() {
		initialize();
	}

	/**
	 * Initialize the contents of the mainFrame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setBounds(300, 50, 1000, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setTitle("���ض�ͯ����������");
		mainFrame.setResizable(false);
		
		// ��ʾ��ҳͼƬ
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LeftBehindChildren.class.getResource("/image/image_1.png")));
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(10, 0, 568, 325);
		mainFrame.getContentPane().add(lblNewLabel);
		
		// ��ʾ���֡����� ��δ���ࡱ
		Label label = new Label("\u65B0\u95FB  \u5C1A\u672A\u5206\u7C7B");
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setForeground(SystemColor.activeCaptionBorder);
		label.setBounds(10, 345, 174, 23);
		mainFrame.getContentPane().add(label);
		
		// ����ˮƽ�ָ���
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 374, 568, 2);
		mainFrame.getContentPane().add(scrollPane);
		
		// �������
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 396, 568,266);
		mainFrame.getContentPane().add(scrollPane_1);
		
		// ��ʾ���֡����� �ѷ��ࡱ
		Label label_1 = new Label("\u65B0\u95FB  \u5DF2\u5206\u7C7B");
		label_1.setForeground(SystemColor.activeCaptionBorder);
		label_1.setFont(new Font("Dialog", Font.BOLD, 20));
		label_1.setBounds(613, 10, 174, 23);
		mainFrame.getContentPane().add(label_1);
		
		// ����ˮƽ�ָ���
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(613, 39, 349, 2);
		mainFrame.getContentPane().add(scrollPane_2);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(613, 65, 349, 508);
		mainFrame.getContentPane().add(scrollPane_3);
		
		JButton btnNewButton = new JButton("\u7EDF\u8BA1\u7AD9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				StatisticsBin statics = new StatisticsBin();
				statics.setVisible(true);
			}
		});
		btnNewButton.setBounds(613, 583, 93, 23);
		mainFrame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(613, 616, 93, 2);
		mainFrame.getContentPane().add(scrollPane_4);
		
		JButton button = new JButton("\u56DE\u6536\u7AD9");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setVisible(false);
				RecycleBin recycle = new RecycleBin();
				recycle.setVisible(true);
			}
		});
		button.setBounds(613, 628, 93, 23);
		mainFrame.getContentPane().add(button);
		
	}
}
