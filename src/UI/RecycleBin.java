package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Label;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecycleBin extends JFrame {

	private JPanel contentPane;

	
	/**
	 * Create the frame.
	 */
	public RecycleBin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 50, 1000, 700);
		setTitle("ªÿ ’’æ");
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
		contentPane.add(scrollPane_1);
		
		JButton button = new JButton("\u8FD4\u56DE\u9996\u9875");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				LeftBehindChildren.mainFrame.setVisible(true);
			}
		});
		button.setBounds(10, 420, 93, 23);
		contentPane.add(button);
		
	}
}
