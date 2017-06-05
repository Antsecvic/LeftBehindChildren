package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import Util.CipherUtil;

public class SetPassword extends JDialog {
	
	private File f;	
	private String password;
	private String filePath;
	private boolean ifEncoded;
	
	public SetPassword(JFrame frame,String filePath,File f){
		super(frame,"设置文件密码",true);
		this.f = f;
		this.filePath = filePath;
		this.ifEncoded = false;
		initComponents();	
	}
	
	public void initComponents(){
		JPasswordField jPasswordField1 = new JPasswordField();// 定义密文框
	    JLabel label1 = new JLabel("密码:");
	    JButton button = new JButton("确定");
	    label1.setBounds(49, 10, 40, 20);
	    jPasswordField1.setBounds(90, 10, 121, 20);
	    button.setBounds(194, 40, 80, 20);
	    getContentPane().setLayout(null);
	    getContentPane().add(label1);
	    getContentPane().add(jPasswordField1);
	    getContentPane().add(button);
	    setSize(300, 109);
	    setLocation(300, 200);
	    
	    button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				password = jPasswordField1.getText();
				if(!password.equals("")){
					CipherUtil cipherUtil = new CipherUtil();
					try{
						cipherUtil.encrypt(filePath, f.getPath(), password);
						ifEncoded = true;
					}catch(Exception e1){
						e1.printStackTrace(); 
					}
				}
				dispose();
			}
		});
	    
	    addWindowListener(new WindowAdapter() {  			  
			public void windowClosing(WindowEvent e) { 
				super.windowClosing(e);
				ifEncoded = false;
			}
		});
	}
	public boolean ifEncoded(){
		return ifEncoded;
	}
}
