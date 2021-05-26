package FunctionPackage;

import POJO.*;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;

public class LoginFrame extends JFrame{
	private String host;
	private String port;
	private String database;
	private String user;
	private String password;	
	
	public LoginFrame(){
		initFrame();
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(640, 480);
		setVisible(true);
	}
	
	public void initFrame() {
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));		
		floor.add(Box.createRigidArea(new Dimension(0,10)));
		
		JPanel floor1 = new JPanel();
		floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		JLabel conntype = new JLabel("Connection Type : MySQL");
		floor1.add(conntype); floor1.add(Box.createHorizontalGlue());
		floor.add(floor1);
		floor.add(Box.createRigidArea(new Dimension(0,15)));
		
		JPanel floor2 = new JPanel();
		floor2.setLayout(new BoxLayout(floor2, BoxLayout.X_AXIS));
		JLabel hostLabel = new JLabel("Host : ");
		JTextField hostTextField = new JTextField();
		hostTextField.setPreferredSize(new Dimension(200, 37));
		JLabel portLabel = new JLabel("Port : ");
		JTextField portTextField = new JTextField();
		floor2.add(hostLabel);
		floor2.add(hostTextField);
		floor2.add(portLabel);
		floor2.add(portTextField);
		floor2.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor2);
		
		JButton dangnhap = new JButton("Login");
		floor.add(dangnhap);
		add(floor);
	}
	
	
	public static void main(String[] args){
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("GTK+".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            Enumeration keys = UIManager.getDefaults().keys();
            while (keys.hasMoreElements()) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof FontUIResource) {
                    FontUIResource orig = (FontUIResource) value;
                    Font font = new Font("TimeNewRoman" , Font.PLAIN, 17);
                    UIManager.put(key, new FontUIResource(font));
                }
            }            
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				LoginFrame mf = new LoginFrame();
			}
		});
		
	}
}
