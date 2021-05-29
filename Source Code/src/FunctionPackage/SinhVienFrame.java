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
	private Session session;
	
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
	
	public SinhVienFrame(SinhVien temp, Session sess) {
		this.sv = temp;
		this.session = sess;
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
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
            	session.close();
                dispose();
            }
        });
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
		JButton mainImg = new JButton();
		mainImg.setIcon(new ImageIcon("img/dynamic_img_2.gif"));
		mainImg.setEnabled(false);
		centerPanel.setLayout(new GridLayout(1,1));
		centerPanel.add(mainImg);
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
	}
	
	private int demCapNhat = 0;
	public void thongtintaikhoanClickAction() {			
		centerPanel.removeAll();
		westPanel.removeAll();
		//-------------------------------------------------------------
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		JLabel magvLb = new JLabel("Mã số sinh viên: " + sv.getMssv());
		JPanel cfloor = new JPanel(); cfloor.setLayout(new BoxLayout(cfloor, BoxLayout.X_AXIS)); 
		cfloor.add(magvLb);
		centerPanel.add(cfloor);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JLabel emailLb = new JLabel("Email: " + sv.getEmail());
		JPanel afloor = new JPanel(); afloor.setLayout(new BoxLayout(afloor, BoxLayout.X_AXIS)); 
		afloor.add(emailLb);
		centerPanel.add(afloor);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
		
		JPanel floor1 = new JPanel(); floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		floor1.setMaximumSize(new Dimension(500, 37));
		JLabel hotenLabel = new JLabel("Họ tên:    ");
		JTextField hotenTextField = new JTextField(sv.getHoten());
		floor1.add(hotenLabel);
		floor1.add(hotenTextField);
		centerPanel.add(floor1);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		
		JPanel floor2 = new JPanel(); 
		floor2.setLayout(new BoxLayout(floor2, BoxLayout.X_AXIS));
		floor2.setMaximumSize(new Dimension(500, 37));
		JLabel gioitinhLabel = new JLabel("Giới tính: ");
		String[] gt = {"Nam", "Nữ"};
		JComboBox gioitinhCmb = new JComboBox(gt);
		floor2.add(gioitinhLabel); 
		floor2.add(gioitinhCmb);
		centerPanel.add(floor2);
		centerPanel.add(Box.createRigidArea(new Dimension(0, 3)));
		
		JPanel floor3 = new JPanel(); // Lấy danh sách lớp đưa vào combobox
		floor3.setLayout(new BoxLayout(floor3, BoxLayout.X_AXIS));
		floor3.setMaximumSize(new Dimension(500, 37));
		JLabel lophocLabel = new JLabel("Lớp học:   ");
		floor3.add(lophocLabel);
		JComboBox lophoccb = new JComboBox();
		try {
			Query q1 = session.createQuery("FROM LopHoc");
			java.util.List<LopHoc> lophocList = q1.list();
			int k = 0;
			for(LopHoc c : lophocList) {
				if(c.getMalop().equals(sv.getLop().getMalop())) {
					k = lophocList.indexOf(c);
				}
				lophoccb.addItem(c);
			}
			lophoccb.setSelectedIndex(k);
		} catch (HibernateException e) {System.out.println(e.getMessage());}
		floor3.add(lophoccb);
		centerPanel.add(floor3);
		
		centerPanel.add(Box.createVerticalGlue());
		JLabel statusCapNhat = new JLabel("Cập nhật thành công!");
		statusCapNhat.setVisible(false);
		JPanel cnp = new JPanel(); cnp.setLayout(new BoxLayout(cnp, BoxLayout.X_AXIS)); 
		cnp.add(Box.createHorizontalGlue());
		cnp.add(statusCapNhat);
		centerPanel.add(cnp);
		//---------------------------------------------------------------
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel floor = new JPanel();
		floor.setLayout(new GridLayout(3,1));
		//--------------------------------------------------------------------
		floor.add(home);
		//-----------------------------------------------------------------
		JButton capnhat = new JButton("Cập nhật");
		capnhat.setForeground(mainColor);
		floor.add(capnhat);
		capnhat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				session.getTransaction().begin();
				sv.setHoten(hotenTextField.getText());
				sv.setGioitinh((String)gioitinhCmb.getSelectedItem());
				sv.setLop((LopHoc)lophoccb.getSelectedItem());
				
				session.update("SinhVien", sv);
				session.getTransaction().commit();
				statusCapNhat.setVisible(true);
				statusCapNhat.setForeground(new Color(0, 255, 127));
				if(demCapNhat%2 == 1) {
					statusCapNhat.setForeground(new Color(255, 107, 107));
				}
				statusCapNhat.setVisible(true);
				demCapNhat++;
			}
		});
		//---------------------------------------------------------------
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
	    		    			if(dmk.getMatKhauCu().equals(sv.getMatkhau()) && dmk.getMatKhauMoi().equals(dmk.getMatKhauMoiNhapLai())) {
	    		    				session.getTransaction().begin();
    		    					sv.setMatkhau(dmk.getMatKhauMoi());
    		    					session.update("GiaoVu",sv);		
	    		    				dmk.dispose();
		    		    			setEnabled(true);
		    		    			session.getTransaction().commit();
	    		    			}
	    		    			else {
	    		    				dmk.getStatus().setText("Mật khẩu cũ hoặc mới không khớp");
	    		    				dmk.getStatus().setVisible(true);
	    		    			}
	    		    		}
	    		    	}
	    		    });
	    			
	    			dmk.getCancel().addMouseListener(new MouseAdapter() {
	    		    	@Override
	    		    	public void mouseReleased(MouseEvent e) {
	    		    		if(SwingUtilities.isLeftMouseButton(e)) {
	    		    			dmk.dispose();
	    		    			setEnabled(true);
	    		    		}
	    		    	}
	    		    });
	    		}
	    	}
	    });		
		westPanel.add(floor);						
		//-----------------------------------------------------------------------------
		westPanel.add(Box.createVerticalGlue());
		westPanel.setVisible(false);
		westPanel.setVisible(true);
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
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
				HibernateUtil hbn = new HibernateUtil("localhost", "3306", "CourseRegistrationSystem", "tramhuuduc", "19120484@Ubuntu");
				Session session = hbn.getFACTORY().openSession();
				SinhVienFrame st = new SinhVienFrame(new SinhVien("19120484", "Trầm Hữu Đức", "Nam", "SV19120484", "19120484", new LopHoc(), "19120484@student.hcmus.edu.vn"), session);
			}
		});
	}
}
