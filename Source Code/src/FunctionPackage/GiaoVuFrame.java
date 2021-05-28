package FunctionPackage;

import POJO.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;
import org.hibernate.query.Query;
import org.hibernate.*;

public class GiaoVuFrame extends JFrame{	
	private GiaoVu gv;	
	private JButton home; 
	
	private JButton dangxuat;
	private JButton thongtintaikhoan;
	private JButton quanlygiaovu;
	private JButton quanlymonhoc;
	private JButton quanlyhocky;
	private JButton quanlylophoc;
	private JButton quanlydangkyhocphan;
	
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	
	final Color mainColor = new Color(249, 108, 9);
	final Color borderColor = new Color(200, 126, 74);
	
//	private BorderLayout mainLayout;
	
	public GiaoVuFrame(GiaoVu temp) {
		this.gv = temp;
		home = new JButton("Home");
		home.setForeground(mainColor);
		home.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			homeClicked();
	    		}
	    	}
	    });
		
//		mainLayout = new BorderLayout();
		setLayout(new BorderLayout(10, 10));
		northPanel = new JPanel();
		westPanel = new JPanel();
		centerPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH); northPanel.setVisible(true);
		add(westPanel, BorderLayout.WEST); westPanel.setVisible(false);
		add(centerPanel, BorderLayout.CENTER); homeClicked();		
		
		initUI();
		
		setTitle("Course Registration System - Teacher");
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
	    
	    thongtintaikhoan = new JButton("<html><center> Thông tin tài khoản<br /> . </center></html>");
	    thongtintaikhoan.setVerticalTextPosition(SwingConstants.BOTTOM);
	    thongtintaikhoan.setHorizontalTextPosition(SwingConstants.CENTER);
	    thongtintaikhoan.setIcon(NewIcon("img/taikhoan_icon.png", 37, 37));
	    thongtintaikhoan.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			thongtintaikhoanClickAction();
	    		}
	    	}
	    });
		
		quanlygiaovu = new JButton("<html><center> Quản lý giáo vụ<br /> . </center></html>");
		quanlygiaovu.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlygiaovu.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlygiaovu.setIcon(NewIcon("img/giaovu_icon.jpg", 37, 37));
		
		quanlymonhoc = new JButton("<html><center> Quản lý môn học<br /> . </center></html>");
		quanlymonhoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlymonhoc.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlymonhoc.setIcon(NewIcon("img/monhoc_icon.png", 37, 37));
		
		quanlyhocky = new JButton("<html><center> Quản lý học kỳ<br /> . </center></html>");
		quanlyhocky.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlyhocky.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlyhocky.setIcon(NewIcon("img/hocky_icon.png", 37, 37));
		
		quanlylophoc = new JButton("<html><center> Quản lý lớp học<br /> . </center></html>");
		quanlylophoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlylophoc.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlylophoc.setIcon(NewIcon("img/lophoc_icon.png", 37, 37));
		
		quanlydangkyhocphan = new JButton("<html><center>Quản lý đăng ký <br /> học phần</center></html>");
		quanlydangkyhocphan.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlydangkyhocphan.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlydangkyhocphan.setIcon(NewIcon("img/quanlydangkyhocphan_icon.png", 37, 37));
		
		dangxuat.setForeground(mainColor);
		thongtintaikhoan.setForeground(mainColor);
		quanlygiaovu.setForeground(mainColor);
		quanlymonhoc.setForeground(mainColor);
		quanlyhocky.setForeground(mainColor);
		quanlylophoc.setForeground(mainColor);
		quanlydangkyhocphan.setForeground(mainColor);		
		
		northPanel.add(dangxuat);
		northPanel.add(thongtintaikhoan);
		northPanel.add(quanlygiaovu);
		northPanel.add(quanlymonhoc);
		northPanel.add(quanlyhocky);
		northPanel.add(quanlylophoc);
		northPanel.add(quanlydangkyhocphan);		
				
	}	
	
	public void homeClicked() {
		westPanel.removeAll();
		westPanel.setVisible(false);
		centerPanel.removeAll();
		JLabel mainImg = new JLabel();
		mainImg.setIcon(new ImageIcon("img/dynamic_img.gif"));
		centerPanel.add(mainImg, BorderLayout.CENTER);
		centerPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
	}
	
	public void thongtintaikhoanClickAction() {			
			centerPanel.removeAll();
			
			westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
			westPanel.setPreferredSize(new Dimension(150, 0));
			westPanel.add(Box.createVerticalGlue());
			JPanel floor = new JPanel();
			floor.setLayout(new GridLayout(3,1));
			
			floor.add(home);
			JButton doimatkhau = new JButton("Đổi mật khẩu");
			doimatkhau.setForeground(mainColor);
			floor.add(doimatkhau);
			
			westPanel.add(floor);			
			westPanel.add(Box.createVerticalGlue());			
			westPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
			westPanel.setVisible(true);			
			
			centerPanel.setVisible(false);
			centerPanel.setVisible(true);
//			this.setEnabled(false);			
	}
	
	public void quanlygiaovuClickAction(){

	}
	public void quanlymonhocClickAction(){

	}
	public void quanlyhockyClickAction(){

	}
	public void quanlylophocClickAction(){

	}
	public void quanlydangkyhocphanClickAction(){

	}
	
	// Functionality----------------------------------
//	private 
	
	
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
				GiaoVuFrame mf = new GiaoVuFrame(new GiaoVu());
			}
		});
	}
}
