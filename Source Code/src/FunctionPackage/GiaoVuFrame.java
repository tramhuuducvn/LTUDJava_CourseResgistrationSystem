package FunctionPackage;

import POJO.*;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;
import javax.swing.table.*;
import org.hibernate.query.Query;
import org.hibernate.*;

public class GiaoVuFrame extends JFrame{	
	private GiaoVu gv;
	private Session session;
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
	
	public GiaoVuFrame(GiaoVu temp, Session sess) {
		this.gv = temp;
		this.session = sess;
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
		setLayout(new BorderLayout(7, 7));
		northPanel = new JPanel();
		westPanel = new JPanel();
		centerPanel = new JPanel();
		add(northPanel, BorderLayout.NORTH); northPanel.setVisible(true);
		add(westPanel, BorderLayout.WEST); westPanel.setVisible(false);
		add(centerPanel, BorderLayout.CENTER); homeClicked();		
		westPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		
		initUI();
		
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
            	session.close();
                dispose();
            }
        });
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
		quanlygiaovu.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			quanlygiaovuClickAction();
	    		}
	    	}
	    });
		
		quanlymonhoc = new JButton("<html><center> Quản lý môn học<br /> . </center></html>");
		quanlymonhoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlymonhoc.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlymonhoc.setIcon(NewIcon("img/monhoc_icon.png", 37, 37));
		quanlymonhoc.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			quanlymonhocClickAction();
	    		}
	    	}
	    });
		
		quanlyhocky = new JButton("<html><center> Quản lý học kỳ<br /> . </center></html>");
		quanlyhocky.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlyhocky.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlyhocky.setIcon(NewIcon("img/hocky_icon.png", 37, 37));
		quanlyhocky.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			quanlyhockyClickAction();
	    		}
	    	}
	    });
		
		quanlylophoc = new JButton("<html><center> Quản lý lớp học<br /> . </center></html>");
		quanlylophoc.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlylophoc.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlylophoc.setIcon(NewIcon("img/lophoc_icon.png", 37, 37));
		quanlylophoc.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			quanlylophocClickAction();
	    		}
	    	}
	    });
		
		quanlydangkyhocphan = new JButton("<html><center>Quản lý đăng ký <br /> học phần</center></html>");
		quanlydangkyhocphan.setVerticalTextPosition(SwingConstants.BOTTOM);
		quanlydangkyhocphan.setHorizontalTextPosition(SwingConstants.CENTER);
		quanlydangkyhocphan.setIcon(NewIcon("img/quanlydangkyhocphan_icon.png", 37, 37));
		quanlydangkyhocphan.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			quanlydangkyhocphanClickAction();
	    		}
	    	}
	    });		
		
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
		JButton mainImg = new JButton();
		mainImg.setIcon(new ImageIcon("img/dynamic_img.gif"));
		mainImg.setEnabled(false);
		centerPanel.setLayout(new GridLayout(1,1));
		centerPanel.add(mainImg);
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
	}
	private int demCapNhat = 0;
	JLabel statusCapNhat;
	public void showStatusCapNhat() {
		statusCapNhat.setForeground(new Color(0, 255, 127));
		if(demCapNhat%2 == 1) {
			statusCapNhat.setForeground(new Color(255, 107, 107));
		}
		statusCapNhat.setVisible(true);
		demCapNhat++;
	}
	public void thongtintaikhoanClickAction() {			
			centerPanel.removeAll();
			westPanel.removeAll();
			//---------------------------------------------------
			centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
			centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
			JLabel magvLb = new JLabel("Mã giáo vụ: " + gv.getMagv());
			JPanel cfloor = new JPanel(); cfloor.setLayout(new BoxLayout(cfloor, BoxLayout.X_AXIS)); 
			cfloor.add(magvLb);
			centerPanel.add(cfloor);
			centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));
			
			JPanel floor1 = new JPanel(); floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
			floor1.setMaximumSize(new Dimension(500, 37));
			JLabel hotenLabel = new JLabel("Họ tên:    ");
			JTextField hotenTextField = new JTextField(gv.getHoten());
			floor1.add(hotenLabel);
			floor1.add(hotenTextField);
			centerPanel.add(floor1);
			centerPanel.add(Box.createVerticalGlue());
			
			statusCapNhat = new JLabel("Cập nhật thành công!");
			statusCapNhat.setVisible(false);
			JPanel cnp = new JPanel(); cnp.setLayout(new BoxLayout(cnp, BoxLayout.X_AXIS)); 
			cnp.add(Box.createHorizontalGlue());
			cnp.add(statusCapNhat);
			centerPanel.add(cnp);
			//------------------------------------------------------
			westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
			westPanel.setPreferredSize(new Dimension(150, 0));
			westPanel.add(Box.createVerticalGlue());		
			JPanel floor = new JPanel();
			floor.setLayout(new GridLayout(3,1));
			//-----------------------------------------------------
			floor.add(home);
			//----------------------------------------------------
			
			//-----------------------------------------------------
			JButton capnhat = new JButton("Cập nhật");
			capnhat.setForeground(mainColor);
			floor.add(capnhat);
			capnhat.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased(MouseEvent e) {
					session.getTransaction().begin();
					gv.setHoten(hotenTextField.getText());
					session.update("GiaoVu", gv);
					session.getTransaction().commit();
					showStatusCapNhat();
				}
			});
			//--------------------------------------------------------
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
		    		    			if(dmk.getMatKhauCu().equals(gv.getMatkhau()) && dmk.getMatKhauMoi().equals(dmk.getMatKhauMoiNhapLai())) {
		    		    				session.getTransaction().begin();
	    		    					gv.setMatkhau(dmk.getMatKhauMoi());
	    		    					session.update("GiaoVu",gv);		
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
			//--------------------------------------------------------			
			westPanel.add(floor);			
			westPanel.add(Box.createVerticalGlue());
			westPanel.setVisible(false);
			westPanel.setVisible(true);
			centerPanel.setVisible(false);
			centerPanel.setVisible(true);			
	}
	
	public void quanlygiaovuClickAction(){
		centerPanel.removeAll();
		westPanel.removeAll();
		// CenterPanel------------------------------------------------
		centerPanel.setLayout(new BorderLayout(0, 3));
		
		String columns[] = {"Chọn", "Tài khoản", "Mã giáo vụ", "Họ tên"};
//		Object[][] data = {{false, "abc", "abc"}};
		DefaultTableModel gvDTable = new DefaultTableModel(columns, 0);
		JTable gvTable = new JTable(gvDTable) {
			@Override
			public Class getColumnClass(int column) {
				switch(column) {
					case 0:
						return Boolean.class;
					case 1: 
						return String.class;
					case 2:
						return String.class;
					case 3: 
						return String.class;
					default:
						return Object.class;
				}
			}
		};
//		gvTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JCheckBox()));
		gvTable.setPreferredScrollableViewportSize(gvTable.getPreferredSize());
		gvTable.setFillsViewportHeight(true);
		gvTable.getColumnModel().getColumn(0).setMaxWidth(57);
		gvTable.setRowHeight(37);
		Query q1 = session.createQuery("FROM GiaoVu");
		java.util.List<GiaoVu> giaovuList  = q1.list();
		for(GiaoVu t : giaovuList) {
			Object ob[] = {false, t.getTaikhoan(), t.getMagv(), t.getHoten()};
			gvDTable.addRow(ob);
		}
		
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setViewportView(gvTable);
		tablePanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.add(tablePanel, BorderLayout.CENTER);
		
		JPanel findPanel = new JPanel();
		findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.X_AXIS));
		JLabel findLabel = new JLabel(" Nhập tên tài khoản: ");
		findPanel.add(findLabel);
		JTextField findTextField = new JTextField();
		findPanel.add(findTextField);
		JButton find = new JButton("Tìm kiếm");
		find.setMaximumSize(new Dimension(125, 100));
		findPanel.add(find);
		findPanel.add(Box.createRigidArea(new Dimension(700, 0)));
		centerPanel.add(findPanel, BorderLayout.NORTH);
		find.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			String ret = findTextField.getText();
	    			for(int i = 0; i < gvTable.getRowCount(); i++) {
	    				if(ret.equals(gvDTable.getValueAt(i, 1))) {
	    					gvDTable.setValueAt(true, i, 0);
	    					gvTable.setRowSelectionInterval(i, i);
	    					gvTable.scrollRectToVisible(gvTable.getCellRect(i, 0, true));
	    				}
	    			}
	    		}
	    	}
	    });
		statusCapNhat = new JLabel("Cập nhật thành công!");
		statusCapNhat.setVisible(false);
		centerPanel.add(statusCapNhat, BorderLayout.SOUTH);
		// WestPanel--------------------------------------------------
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel wfloor = new JPanel();
		wfloor.setLayout(new GridLayout(4,1));		
		wfloor.add(home);		
		
		JButton themgiaovu = new JButton("Thêm giáo vụ");themgiaovu.setForeground(mainColor);
		wfloor.add(themgiaovu);
		themgiaovu.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			ThemGiaoVuFrame themGVF = new ThemGiaoVuFrame(session);
	    			themGVF.getOk().addMouseListener(new MouseAdapter() {
	    				@Override
	    				public void mouseReleased(MouseEvent e) {
	    					if(SwingUtilities.isLeftMouseButton(e)) {
	    						GiaoVu temp = themGVF.getGiaoVu();
	    						Object[] ob = {false, temp.getTaikhoan(), temp.getMagv(), temp.getHoten()};
	    						gvDTable.addRow(ob);
	    					}
	    				}
	    			});		
	    		}
	    	}
	    });
		
		
		JButton capnhatgiaovu = new JButton("Cập nhật");capnhatgiaovu.setForeground(mainColor);
		wfloor.add(capnhatgiaovu);
		capnhatgiaovu.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			for(int i = 0; i < gvTable.getRowCount(); i++) {
	    				if(gvDTable.getValueAt(i, 0).equals(true)) {
		    					session.getTransaction().begin();
		    					String idgv = (String)gvDTable.getValueAt(i, 2);
		    					GiaoVu temp = session.get(GiaoVu.class, idgv);	    					
		    					temp.setHoten((String)(gvDTable.getValueAt(i, 3)));
		    					session.update(temp);
		    					session.getTransaction().commit();		    					
		    					showStatusCapNhat();
	    				}
	    			}
	    		}
	    	}
	    });
		
		JButton xoagiaovu = new JButton("Xóa");xoagiaovu.setForeground(mainColor);
		wfloor.add(xoagiaovu);
		xoagiaovu.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			for(int i = 0; i < gvTable.getRowCount(); i++) {
	    				if(gvDTable.getValueAt(i, 0).equals(true)) {
		    					session.getTransaction().begin();
		    					String idgv = (String)gvDTable.getValueAt(i, 2);
		    					GiaoVu temp = session.get(GiaoVu.class, idgv);	    					
		    					temp.setHoten((String)(gvDTable.getValueAt(i, 3)));
		    					session.delete(temp);
		    					session.getTransaction().commit();		    					
		    					gvDTable.removeRow(i);
		    					statusCapNhat.setText("Xóa thành công!");
		    					showStatusCapNhat();
	    				}
	    			}
	    		}
	    	}
	    });
		
		//------------------------------------------------------
		westPanel.add(wfloor);		
		westPanel.add(Box.createVerticalGlue());
		westPanel.setVisible(false);
		westPanel.setVisible(true);			
		centerPanel.setVisible(false);
		centerPanel.setVisible(true);
	}
	
	public void quanlymonhocClickAction(){
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
	
	public void quanlyhockyClickAction(){
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
	
	public void quanlylophocClickAction(){
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
	
	public void quanlydangkyhocphanClickAction(){
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
				HibernateUtil hbn = new HibernateUtil("localhost", "3306", "CourseRegistrationSystem", "tramhuuduc", "19120484@Ubuntu");
				Session session = hbn.getFACTORY().openSession();
				GiaoVuFrame mf = new GiaoVuFrame(new GiaoVu(), session);
			}
		});
	}
}
