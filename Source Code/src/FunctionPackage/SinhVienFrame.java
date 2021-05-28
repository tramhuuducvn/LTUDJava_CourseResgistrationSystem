package FunctionPackage;

import POJO.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;
import org.hibernate.query.Query;
import org.hibernate.*;

public class SinhVienFrame extends JFrame{
	private SinhVien sv;
	
	private JButton dangxuat;
	private JButton quanlytaikhoan;
	private JButton dangkyhocphan;
	private JButton ketquaDKHP;
	
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	
	public SinhVienFrame(SinhVien temp) {
		this.sv = temp;
		setLayout(new BorderLayout());
		northPanel = new JPanel();
		westPanel = new JPanel();
		centerPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		JLabel mainImg = new JLabel();
		mainImg.setIcon(new ImageIcon("img/dynamic_img.gif"));
		centerPanel.add(mainImg, BorderLayout.CENTER);
		
		initUI();
		
		setTitle("Course Registration System - Student");
		setSize(1370, 840);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private ImageIcon NewIcon(String srcFile, int x, int y) {	
		ImageIcon src = new ImageIcon(srcFile);
		Image scaled = src.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH);
		return new ImageIcon(scaled);
	}
	private void initUI() {
		northPanel.setLayout(new GridLayout(1,7));
		northPanel.setPreferredSize(new Dimension(0, 100));
		Color northColor = new Color(249, 108, 9);
		
		dangxuat = new JButton("<html><center> Đăng Xuất<br /> . </center></html>");
		dangxuat.setIcon(NewIcon("img/logout_icon.png", 37, 37));
		dangxuat.setVerticalTextPosition(SwingConstants.BOTTOM);
	    dangxuat.setHorizontalTextPosition(SwingConstants.CENTER);
	    dangxuat.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			dispose();
	    			new LoginFrame();
	    		}
	    	}
	    });
	    dangxuat.setForeground(northColor);
	    northPanel.add(dangxuat);
	    
	    quanlytaikhoan = new JButton("<html><center> Thông tin tài khoản<br /> . </center></html>");
		quanlytaikhoan.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlytaikhoan.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlytaikhoan.setIcon(NewIcon("img/taikhoan_icon.png", 37, 37));
		quanlytaikhoan.setForeground(northColor);
		northPanel.add(quanlytaikhoan);
		
		dangkyhocphan = new JButton("<html><center>Đăng ký học phần <br /> . </center></html>");
		dangkyhocphan.setVerticalTextPosition(SwingConstants.BOTTOM);
		dangkyhocphan.setHorizontalTextPosition(SwingConstants.CENTER);
		dangkyhocphan.setIcon(NewIcon("img/dangkyhocphan_icon.jpg", 37, 37));
		dangkyhocphan.setForeground(northColor);
		northPanel.add(dangkyhocphan);
		
		ketquaDKHP = new JButton("<html><center>Kết quả <br /> đăng ký học phần</center></html>");
		ketquaDKHP.setVerticalTextPosition(SwingConstants.BOTTOM);
		ketquaDKHP.setHorizontalTextPosition(SwingConstants.CENTER);
		ketquaDKHP.setIcon(NewIcon("img/ketquadkhp_icon.png", 37, 37));
		ketquaDKHP.setForeground(northColor);
		northPanel.add(ketquaDKHP);
	}
	
	public static void main(String[] args) {
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
				SinhVienFrame st = new SinhVienFrame(new SinhVien());
			}
		});
	}
}
