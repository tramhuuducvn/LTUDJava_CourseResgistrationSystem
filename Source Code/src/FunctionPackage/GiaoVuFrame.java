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
	
	private JButton dangxuat;
	private JButton quanlytaikhoan;
	private JButton quanlygiaovu;
	private JButton quanlymonhoc;
	private JButton quanlyhocky;
	private JButton quanlylophoc;
	private JButton quanlydangkyhocphan;
	
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel centerPanel;
	
	public GiaoVuFrame(GiaoVu temp) {
		this.gv = temp;
		setLayout(new BorderLayout());
		northPanel = new JPanel();
		westPanel = new JPanel();
		centerPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH);
		add(westPanel, BorderLayout.WEST);
		add(centerPanel, BorderLayout.CENTER);
		
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
	    
		quanlytaikhoan = new JButton("<html><center> Quản lý tài khoản<br /> . </center></html>");
		quanlytaikhoan.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlytaikhoan.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlytaikhoan.setIcon(NewIcon("img/taikhoan_icon.png", 37, 37));
		
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
		quanlydangkyhocphan.setIcon(NewIcon("img/dangkyhocphan_icon.png", 37, 37));
		
		dangxuat.setForeground(northColor);
		quanlytaikhoan.setForeground(northColor);
		quanlygiaovu.setForeground(northColor);
		quanlymonhoc.setForeground(northColor);
		quanlyhocky.setForeground(northColor);
		quanlylophoc.setForeground(northColor);
		quanlydangkyhocphan.setForeground(northColor);		
		
		northPanel.add(dangxuat);
		northPanel.add(quanlytaikhoan);
		northPanel.add(quanlygiaovu);
		northPanel.add(quanlymonhoc);
		northPanel.add(quanlyhocky);
		northPanel.add(quanlylophoc);
		northPanel.add(quanlydangkyhocphan);		
				
	}
	
	public void dangxuatClickAction(){

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
