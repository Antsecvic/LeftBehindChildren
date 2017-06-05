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

import Object.ListData;
import Util.CipherUtil;


public class PasswordDialog extends JDialog {
	
	private File f;	
	private static String password;
	private static String filePath;
	
	public PasswordDialog(JFrame frame,File f){
		super(frame,"输入文件密码",true);
		this.f = f;
		this.filePath = f.getPath();
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
				String[] path = f.getPath().split("\\\\");
				String desPath = f.getParentFile().getPath()+"\\"+"decoded_"+path[path.length-1];
				if(!password.equals("")){
					CipherUtil cipherUtil = new CipherUtil();
					try{
						cipherUtil.decrypt(f.getPath(), desPath, password);
						ListData.getInstance().importFile(desPath);
						filePath = desPath;
					}catch(Exception e1){
						new File(desPath).delete();
						e1.printStackTrace(); 
					}
				}else{
					ListData.getInstance().importFile(f.getPath());
				}
				dispose();
			}
		});
	    
	    addWindowListener(new WindowAdapter() {  			  
			public void windowClosing(WindowEvent e) { 
				super.windowClosing(e);
			}
		});
	}	
	public static String get(){
		return filePath;
	}

}