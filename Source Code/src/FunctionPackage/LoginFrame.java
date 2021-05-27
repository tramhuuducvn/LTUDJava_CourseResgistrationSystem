package FunctionPackage;

import POJO.*;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;
import org.hibernate.query.Query;
import org.hibernate.*;

public class LoginFrame extends JFrame{	
	private String host;
	private String port;
	private String database;
	private String user;
	private String password;
	
	private String taikhoan;
	private String matkhau;
	
	private JButton login;
	
	public LoginFrame(){
		setTitle("Login to Course Registration System");
		initFrame();		
		setSize(640, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private ImageIcon setSizeImage(String srcFile, int x, int y) {	
		ImageIcon src = new ImageIcon(srcFile);
		Image scaled = src.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
		return new ImageIcon(scaled);
	}
	
	public void initFrame() {
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));		
		floor.add(Box.createRigidArea(new Dimension(0,10)));
		
		Color c = new Color(227, 140, 0);
		JLabel title1 = new JLabel("------- Connection Configuring -------  ");
		title1.setForeground(c);
		floor.add(title1);
		floor.add(Box.createRigidArea(new Dimension(0,15)));
		
		JPanel floor1 = new JPanel();
		floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		JLabel conntype = new JLabel(" Connection Type : MySQL");
		floor1.add(conntype); floor1.add(Box.createHorizontalGlue());
		floor.add(floor1);
		floor.add(Box.createRigidArea(new Dimension(0,15)));
		
		JPanel floor2 = new JPanel();
		floor2.setLayout(new BoxLayout(floor2, BoxLayout.X_AXIS));
		JLabel hostLabel = new JLabel(" Host : ");
		JTextField hostTextField = new JTextField("localhost");
		hostTextField.setPreferredSize(new Dimension(200, 37));
		JLabel portLabel = new JLabel("   Port : ");
		JTextField portTextField = new JTextField("3306");
		floor2.add(hostLabel);
		floor2.add(hostTextField);
		floor2.add(portLabel);
		floor2.add(portTextField);
		floor2.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor2);
		
		JPanel floor23 = new JPanel();
		floor23.setLayout(new BoxLayout(floor23, BoxLayout.X_AXIS));
		JLabel dataLabel = new JLabel(" Default database :         ");
		JTextField dataTextField = new JTextField("CourseRegistrationSystem");
		floor23.add(dataLabel);
		floor23.add(dataTextField);
		floor23.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor23);
		floor.add(Box.createRigidArea(new Dimension(0,15)));
		
		JPanel floor3 = new JPanel();
		floor3.setLayout(new BoxLayout(floor3, BoxLayout.X_AXIS));
		JLabel userLabel = new JLabel(" User database :         ");
		JTextField userTextField = new JTextField("tramhuuduc");
		floor3.add(userLabel);
		floor3.add(userTextField);
		floor3.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor3);
		
		JPanel floor4 = new JPanel();
		floor4.setLayout(new BoxLayout(floor4, BoxLayout.X_AXIS));
		JLabel passLabel = new JLabel(" Password database : ");
		JPasswordField passTextField = new JPasswordField("19120484@Ubuntu");
		floor4.setMaximumSize(new Dimension(2000, 37));
		floor4.add(passLabel);
		floor4.add(passTextField);
		floor.add(floor4);
		floor.add(Box.createRigidArea(new Dimension(0,15)));
		
		JLabel title2 = new JLabel("------- Login Course Registration System -------         ");
		title2.setForeground(c);
		floor.add(title2);
		floor.add(Box.createRigidArea(new Dimension(0,15)));
		
		JPanel floor5 = new JPanel();
		floor5.setLayout(new BoxLayout(floor5, BoxLayout.X_AXIS));
		JLabel taikhoanLabel = new JLabel(" User :         ");
		JTextField taikhoanTextField = new JTextField("GV102");
		floor5.add(taikhoanLabel);
		floor5.add(taikhoanTextField);
		floor5.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor5);
		
		JPanel floor6 = new JPanel();
		floor6.setLayout(new BoxLayout(floor6, BoxLayout.X_AXIS));
		JLabel matkhauLabel = new JLabel(" Password : ");
		JPasswordField matkhauTextField = new JPasswordField("102");
		floor6.setMaximumSize(new Dimension(2000, 37));
		floor6.add(matkhauLabel);
		floor6.add(matkhauTextField);
		floor.add(floor6);
		floor.add(Box.createVerticalGlue());
		
		JPanel floor7 = new JPanel();
		floor7.setLayout(new BoxLayout(floor7, BoxLayout.X_AXIS));
		JLabel status = new JLabel();
		status.setVisible(false);
		floor7.add(status);
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(100, 100));
		login.setForeground(c);
//		login.setBackground(c);
		Icon login_icon = setSizeImage("./img/login_icon.png", 20, 20);
		login.setIcon(login_icon);
		login.setOpaque(true);
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					try {
						host = hostTextField.getText();
						port = portTextField.getText();
						database = dataTextField.getText();
						user = userTextField.getText();
						password = String.valueOf(passTextField.getPassword());
						taikhoan = taikhoanTextField.getText();
						matkhau  = String.valueOf(matkhauTextField.getPassword());
						
						HibernateUtil hbn = new HibernateUtil(host, port, database, user, password);
						Session session = hbn.getFACTORY().openSession();
						
						boolean k = false;
						//------------------------------------------------------------
						Query query = session.createQuery("FROM GiaoVu as gv WHERE gv.taikhoan =: " + taikhoan);
						query.setParameter(taikhoan, taikhoan);
						if(query.list().isEmpty() == false) {
							k = true;
							GiaoVu gv = (GiaoVu) query.list().get(0);
							if(gv.getMatkhau().equals(matkhau)) {
								status.setText(" --- Login Success ---");
								status.setForeground(new Color(9, 117, 10));
								status.setVisible(true);
								
								System.out.println(gv.toString());
								GiaoVuFrame gvm = new GiaoVuFrame(gv);
								dispose();
							}
							else {
								status.setText(" --- Wrong Password ---");
								status.setForeground(new Color(215, 50, 50));
								status.setVisible(true);
							}
						}
						//------------------------------------------------------------
						query = session.createQuery("FROM SinhVien as sv WHERE sv.taikhoan =: " + taikhoan);
						query.setParameter(taikhoan, taikhoan);
						if(query.list().isEmpty() == false) {
							k = true;
							SinhVien sv = (SinhVien) query.list().get(0);
							if(sv.getMatkhau().equals(matkhau)) {
								status.setText(" --- Login Success ---");
								status.setForeground(new Color(9, 117, 10));
								status.setVisible(true);
								
								System.out.println(sv.toString());								
								SinhVienFrame svm = new SinhVienFrame(sv);
								dispose();
							}
							else {
								status.setText(" --- Wrong Password ---");
								status.setForeground(new Color(215, 50, 50));
								status.setVisible(true);
							}
						}
						//-----------------------------------------------------------------------------------
						if(k == false) {
							status.setText(" --- This account doesn't exist, or wrong ---");
							status.setForeground(new Color(215, 50, 50));
							status.setVisible(true);
						}																		
					}
					catch(HibernateException e1) {
						System.out.println(e1.getMessage());
					} 
				}
			}
		});
		
		floor7.add(Box.createHorizontalGlue());
		floor7.add(login);
		floor7.add(Box.createRigidArea(new Dimension(15,0)));
		floor.add(floor7);
		floor.add(Box.createRigidArea(new Dimension(0,15)));
		
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
        } catch (java.lang.InstantiationException ex) {
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
