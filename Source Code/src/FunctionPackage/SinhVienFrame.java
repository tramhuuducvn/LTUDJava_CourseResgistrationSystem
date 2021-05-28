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
	
	private JButton home;
	private JButton dangxuat;
	private JButton thongtintaikhoan;
	private JButton dangkyhocphan;
	private JButton ketquaDKHP;
	
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	
	final Color mainColor = new Color(249, 108, 9);
	final Color borderColor = new Color(200, 126, 74);
	
	public SinhVienFrame(SinhVien temp) {
		this.sv = temp;
		home = new JButton("Home"); home.setForeground(mainColor);
		home.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			homeClicked();
	    		}
	    	}
	    });	
		
		setLayout(new BorderLayout());
		northPanel = new JPanel();
		westPanel = new JPanel();
		centerPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		homeClicked();
		westPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		
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
	    dangxuat.setForeground(mainColor);
	    northPanel.add(dangxuat);
	    
	    thongtintaikhoan = new JButton("<html><center> Thông tin tài khoản<br /> . </center></html>");
	    thongtintaikhoan.setVerticalTextPosition(SwingConstants.BOTTOM);
	    thongtintaikhoan.setHorizontalTextPosition(SwingConstants.CENTER);
	    thongtintaikhoan.setIcon(NewIcon("img/taikhoan_icon.png", 37, 37));
	    thongtintaikhoan.setForeground(mainColor);
	    northPanel.add(thongtintaikhoan);
	    thongtintaikhoan.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			thongtintaikhoanClickAction();
	    		}
	    	}
	    });		
		
		dangkyhocphan = new JButton("<html><center>Đăng ký học phần <br /> . </center></html>");
		dangkyhocphan.setVerticalTextPosition(SwingConstants.BOTTOM);
		dangkyhocphan.setHorizontalTextPosition(SwingConstants.CENTER);
		dangkyhocphan.setIcon(NewIcon("img/dangkyhocphan_icon.jpg", 37, 37));
		dangkyhocphan.setForeground(mainColor);
		northPanel.add(dangkyhocphan);
		dangkyhocphan.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			dangkyhocphanClickAction();
	    		}
	    	}
	    });
		
		ketquaDKHP = new JButton("<html><center>Kết quả <br /> đăng ký học phần</center></html>");
		ketquaDKHP.setVerticalTextPosition(SwingConstants.BOTTOM);
		ketquaDKHP.setHorizontalTextPosition(SwingConstants.CENTER);
		ketquaDKHP.setIcon(NewIcon("img/ketquadkhp_icon.png", 37, 37));
		ketquaDKHP.setForeground(mainColor);
		northPanel.add(ketquaDKHP);
		ketquaDKHP.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			ketquaDKHPClickAction();
	    		}
	    	}
	    });
	}
	
	public void homeClicked() {
		westPanel.removeAll();
		westPanel.setVisible(false);
		centerPanel.removeAll();
		JLabel mainImg = new JLabel();
		mainImg.setIcon(new ImageIcon("img/dynamic_img.gif"));
		centerPanel.add(mainImg, BorderLayout.CENTER);
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
	}
	
	public void thongtintaikhoanClickAction() {			
		centerPanel.removeAll();
		westPanel.removeAll();
		
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel floor = new JPanel();
		floor.setLayout(new GridLayout(3,1));
		
		floor.add(home);
		JButton doimatkhau = new JButton("Đổi mật khẩu");
		doimatkhau.setForeground(mainColor);
		floor.add(doimatkhau);
		doimatkhau.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			DoiMatKhau dmk = new DoiMatKhau();
	    			setEnabled(false);
	    			dmk.getOK().addMouseListener(new MouseAdapter() {
	    		    	@Override
	    		    	public void mouseReleased(MouseEvent e) {
	    		    		if(SwingUtilities.isLeftMouseButton(e)) {
	    		    			
	    		    		}
	    		    	}
	    		    });
	    		}
	    	}
	    });
		
		westPanel.add(floor);			
					
		
		westPanel.add(Box.createVerticalGlue());
		westPanel.setVisible(false);
		westPanel.setVisible(true);
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
//		this.setEnabled(false);			
	}
	
	public void dangkyhocphanClickAction(){
		centerPanel.removeAll();
		westPanel.removeAll();
		
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel floor = new JPanel();
		floor.setLayout(new GridLayout(3,1));
		
		floor.add(home);
		
		westPanel.add(floor);			
		
		westPanel.add(Box.createVerticalGlue());
		westPanel.setVisible(false);
		westPanel.setVisible(true);		
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
	}
	
	public void ketquaDKHPClickAction(){
		centerPanel.removeAll();
		westPanel.removeAll();
		
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel floor = new JPanel();
		floor.setLayout(new GridLayout(3,1));
		
		floor.add(home);
		
		westPanel.add(floor);			
		
		westPanel.add(Box.createVerticalGlue());
		westPanel.setVisible(false);
		westPanel.setVisible(true);		
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
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
