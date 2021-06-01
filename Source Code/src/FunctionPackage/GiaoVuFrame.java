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
	private HocKy hockyhientai;
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
		this.session = sess;
		this.gv = temp;
		
		Query q = session.createQuery("FROM HocKy WHERE trangthai =: tt");
		q.setParameter("tt", true);
		java.util.List<HocKy> tempList1 = q.list();
		if(!tempList1.isEmpty()) {
			hockyhientai = tempList1.get(0);
		}	
		
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
		
		JButton themgiaovu = new JButton("Thêm giáo vụ");
		themgiaovu.setForeground(mainColor);
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
	    				if(gvDTable.getValueAt(i, 0).toString() == "true") {
		    					session.getTransaction().begin();
		    					String idgv = (String)gvDTable.getValueAt(i, 2);
		    					GiaoVu temp = session.get(GiaoVu.class, idgv);	    					
		    					temp.setHoten((String)(gvDTable.getValueAt(i, 3)));
		    					session.update(temp);
		    					session.getTransaction().commit();	
		    					statusCapNhat.setText("Cập nhật thành công!");
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
	    					i--;
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
		// CenterPanel------------------------------------------------
		centerPanel.setLayout(new BorderLayout(0, 3));
		
		String columns[] = {"Chọn", "Mã môn học", "Tên môn học", "Số tín chỉ"};
		DefaultTableModel mhDTable = new DefaultTableModel(columns, 0);
		JTable mhTable = new JTable(mhDTable) {
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
		mhTable.setPreferredScrollableViewportSize(mhTable.getPreferredSize());
		mhTable.setFillsViewportHeight(true);
		mhTable.getColumnModel().getColumn(0).setMaxWidth(57);
		mhTable.setRowHeight(37);
		Query q1 = session.createQuery("FROM MonHoc");
		java.util.List<MonHoc> monhocList  = q1.list();
		for(MonHoc t : monhocList) {
			Object ob[] = {false, t.getMamonhoc(), t.getTenmonhoc(), t.getSTC()};
			mhDTable.addRow(ob);
		}
		
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setViewportView(mhTable);
		tablePanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.add(tablePanel, BorderLayout.CENTER);
		
		JPanel findPanel = new JPanel();
		findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.X_AXIS));
		JLabel findLabel = new JLabel(" Nhập mã môn học: ");
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
	    			for(int i = 0; i < mhTable.getRowCount(); i++) {
	    				if(ret.equals(mhDTable.getValueAt(i, 1))) {
	    					mhTable.setRowSelectionInterval(i, i);
	    					mhTable.scrollRectToVisible(mhTable.getCellRect(i, 0, true));
	    				}
	    			}
	    		}
	    	}
	    });
		statusCapNhat = new JLabel();
		statusCapNhat.setVisible(false);
		centerPanel.add(statusCapNhat, BorderLayout.SOUTH);
		// WestPanel--------------------------------------------------
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel wfloor = new JPanel();
		wfloor.setLayout(new GridLayout(4,1));		
		wfloor.add(home);		
		
		JButton themmonhoc = new JButton("Thêm môn học");
		themmonhoc.setForeground(mainColor);
		wfloor.add(themmonhoc);
		themmonhoc.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			ThemMonHocFrame themMHF = new ThemMonHocFrame(session);
	    			themMHF.getOk().addMouseListener(new MouseAdapter() {
	    				@Override
	    				public void mouseReleased(MouseEvent e) {
	    					if(SwingUtilities.isLeftMouseButton(e)) {
	    						MonHoc temp = themMHF.getMonHoc();
	    						Object[] ob = {false, temp.getMamonhoc(), temp.getTenmonhoc(), temp.getSotinchi()};
	    						mhDTable.addRow(ob);
	    					}
	    				}
	    			});		
	    		}
	    	}
	    });
		
		
		JButton capnhat = new JButton("Cập nhật");
		capnhat.setForeground(mainColor);
		wfloor.add(capnhat);
		capnhat.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			for(int i = 0; i < mhTable.getRowCount(); i++) {
	    				if(mhDTable.getValueAt(i, 0).equals(true)) {
	    					session.getTransaction().begin();
	    					String id = (String)mhDTable.getValueAt(i, 1);
	    					MonHoc temp = session.get(MonHoc.class, id);	    					
	    					temp.setTenmonhoc((String)(mhDTable.getValueAt(i, 2)));
	    					temp.setSTC((String)(mhDTable.getValueAt(i, 3)));
	    					session.update(temp);
	    					session.getTransaction().commit();		
	    					statusCapNhat.setText("Cập nhật thành công!");
	    					showStatusCapNhat();
	    				}
	    			}
	    		}
	    	}
	    });
		
		JButton xoamonhoc = new JButton("Xóa");xoamonhoc.setForeground(mainColor);
		wfloor.add(xoamonhoc);
		xoamonhoc.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			for(int i = 0; i < mhTable.getRowCount(); i++) {
	    				if(mhDTable.getValueAt(i, 0).equals(true)) {
	    					session.getTransaction().begin();
	    					String id = (String)mhDTable.getValueAt(i, 1);
	    					MonHoc temp = session.get(MonHoc.class, id);	    					
	    					
	    					session.delete(temp);
	    					session.getTransaction().commit();		    					
	    					mhDTable.removeRow(i);
	    					statusCapNhat.setText("Xóa thành công!");
	    					showStatusCapNhat();
	    					i--;
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
	
	public void quanlyhockyClickAction(){
		centerPanel.removeAll();
		westPanel.removeAll();
		// CenterPanel------------------------------------------------
		centerPanel.setLayout(new BorderLayout(0, 3));
		
		String columns[] = {"Chọn", "Năm học", "Mã học kỳ", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"};
		DefaultTableModel dTable = new DefaultTableModel(columns, 0);
		JTable table = new JTable(dTable) {
			@Override
			public Class getColumnClass(int column) {
				switch(column) {
					case 0:
						return Boolean.class;
					case 1: 
						return Integer.class;
					case 2:
						return String.class;
					case 3: 
						return Date.class;
					case 4:
						return Date.class;
					case 5:
						return String.class;
					default:
						return Object.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMaxWidth(57);
		table.setRowHeight(37);
		JTableUtil.setCellsAlignment(table, SwingConstants.LEFT, 1);
		Query q1 = session.createQuery("FROM HocKy");
		java.util.List<HocKy> list  = q1.list();
		for(HocKy t : list) {
			Object ob[] = {false, t.getNamhoc(), t.getMahocky(), t.getNgaybatdau(), t.getNgayketthuc(), t.getTrangThai()};
			dTable.addRow(ob);
		}
		
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setViewportView(table);
		tablePanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.add(tablePanel, BorderLayout.CENTER);
		
		JPanel findPanel = new JPanel();
		findPanel.setLayout(new BoxLayout(findPanel, BoxLayout.X_AXIS));

		JLabel findLabel1 = new JLabel(" Năm học: ");
		findPanel.add(findLabel1);
		JTextField findTextField1 = new JTextField();
		findPanel.add(findTextField1);

		JLabel findLabel2 = new JLabel(" Học kỳ: ");
		findPanel.add(findLabel2);
		JTextField findTextField2 = new JTextField();
		findPanel.add(findTextField2);

		JButton find = new JButton("Tìm kiếm");
		find.setMaximumSize(new Dimension(125, 100));
		findPanel.add(find);
		findPanel.add(Box.createRigidArea(new Dimension(700, 0)));
		centerPanel.add(findPanel, BorderLayout.NORTH);
		find.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			int ret1 = Integer.parseInt(findTextField1.getText());
	    			String ret2 = findTextField2.getText();
	    			boolean flag = false;
	    			for(int i = 0; i < table.getRowCount(); i++) {
	    				if(ret1 == (int)table.getValueAt(i, 1) && ret2.equals(table.getValueAt(i, 2))) {
	    					flag = true;
	    					table.setRowSelectionInterval(i, i);
	    					table.scrollRectToVisible(table.getCellRect(i, 0, true));
	    				}
	    			}
	    			if(flag == false) {
	    				statusCapNhat.setText("Không tìm thấy kết quả!");
	    				showStatusCapNhat();
	    			}
	    		}
	    	}
	    });

		statusCapNhat = new JLabel();
		statusCapNhat.setVisible(false);
		centerPanel.add(statusCapNhat, BorderLayout.SOUTH);
		// WestPanel--------------------------------------------------
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel wfloor = new JPanel();
		wfloor.setLayout(new GridLayout(4,1));		
		wfloor.add(home);		
		
		JButton them = new JButton("Thêm học kỳ");
		them.setForeground(mainColor);
		wfloor.add(them);
		them.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			ThemHocKyFrame themHKF = new ThemHocKyFrame(session);
	    			themHKF.getOk().addMouseListener(new MouseAdapter() {
	    				@Override
	    				public void mouseReleased(MouseEvent e) {
	    					if(SwingUtilities.isLeftMouseButton(e)) {
	    						HocKy temp = themHKF.getHocKy();
	    						Object[] ob = {false, temp.getNamhoc(), temp.getMahocky(), temp.getNgaybatdau(), temp.getNgayketthuc(),temp.getTrangThai()};
	    						dTable.addRow(ob);
	    					}
	    				}
	    			});		
	    		}
	    	}
	    });		
		
		JButton dathocky = new JButton("<html><center>Đặt học kỳ <br /> hiện tại </center></html>");
		dathocky.setForeground(mainColor);
		wfloor.add(dathocky);
		dathocky.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			for(int j = 0; j < table.getRowCount(); j++) {	    				
						dTable.setValueAt("Tắt", j, 5);
					}
	    			int r = table.getSelectedRow();
	    			if(r > -1 && r < table.getRowCount()) {
						if(hockyhientai != null) {
							hockyhientai.setTrangthai(false);
							session.update(hockyhientai);
						}
						session.getTransaction().begin();
						HocKyPK id = new HocKyPK((int)dTable.getValueAt(r, 1), (String)dTable.getValueAt(r, 2));
						hockyhientai = session.get(HocKy.class, id);
						hockyhientai.setTrangthai(true);
						session.saveOrUpdate(hockyhientai);
						
						session.getTransaction().commit();
						dTable.setValueAt("Bật", r, 5);
						statusCapNhat.setText("Đã đặt "+ hockyhientai.toString() +" làm học kỳ hiện tại");
						showStatusCapNhat();
	    			}
	    		}	  
	    	}
	    });
		
		JButton xoa = new JButton("Xóa");
		xoa.setForeground(mainColor);
		wfloor.add(xoa);
		xoa.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			for(int i = 0; i < table.getRowCount(); i++) {
	    				if(dTable.getValueAt(i, 0).toString() == "true") {
	    					session.getTransaction().begin();
	    					HocKyPK id = new HocKyPK((int)dTable.getValueAt(i, 1), (String)dTable.getValueAt(i, 2));
	    					HocKy temp = session.get(HocKy.class, id);
	    					session.delete(temp);
	    					session.getTransaction().commit();	    					
	    					dTable.removeRow(i);
	    					statusCapNhat.setText("Xóa thành công!");
	    					showStatusCapNhat();
	    					i--;
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
	
	public void quanlylophocClickAction(){
		centerPanel.removeAll();
		westPanel.removeAll();
		statusCapNhat = new JLabel();
		//---------------
		centerPanel.setLayout(new BorderLayout(0, 3));
		JPanel cfloor = new JPanel();
		cfloor.setLayout(new BoxLayout(cfloor, BoxLayout.Y_AXIS));
		centerPanel.add(cfloor);
				
		String columns[] = {"Chọn", "MSSV", "Họ tên", "Giới tính", "Lớp", "Email"};
		DefaultTableModel dTable = new DefaultTableModel(columns, 0);
		JTable table = new JTable(dTable) {
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
					case 4:
						return LopHoc.class;
					case 5:
						return String.class;
					default:
						return Object.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setMaxWidth(57);
		table.getColumnModel().getColumn(1).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(150);
		table.getColumnModel().getColumn(2).setMaxWidth(500);
		table.getColumnModel().getColumn(2).setMinWidth(300);
		table.getColumnModel().getColumn(3).setMaxWidth(110);
		table.getColumnModel().getColumn(3).setMinWidth(110);
		table.getColumnModel().getColumn(4).setMaxWidth(150);
		table.getColumnModel().getColumn(4).setMinWidth(150);
		table.setRowHeight(37);
//		JTableUtil.setCellsAlignment(table, SwingConstants.LEFT, 1);		
		JPanel cfloor1 = new JPanel();
		cfloor1.setLayout(new BoxLayout(cfloor1, BoxLayout.X_AXIS));
		cfloor1.setMaximumSize(new Dimension(2000, 37));
		cfloor1.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		cfloor.add(cfloor1);
		JLabel lophocLabel = new JLabel("Danh sách lớp: ");
		cfloor1.add(lophocLabel);
		
		JComboBox<LopHoc> lophoccb = new JComboBox<LopHoc>();
		java.util.Vector<LopHoc> lophocdata = new Vector<LopHoc>(); 
		lophoccb.setMaximumSize(new Dimension(200, 37));
		try {
			java.util.List<LopHoc> lophocList;
			Query q1 = session.createQuery("FROM LopHoc");
			lophocList = q1.list();
			for(LopHoc c : lophocList) {
				lophocdata.add(c);
				lophoccb.addItem(c);
			}
		} catch (HibernateException e) {System.out.println(e.getMessage());}
		cfloor1.add(lophoccb);
		cfloor.add(cfloor1);
		cfloor.add(Box.createVerticalStrut(10));
		JButton xemdssv = new JButton("Xem danh sách sinh viên");
		xemdssv.setMaximumSize(new Dimension(250, 37));
		cfloor1.add(xemdssv);
		
		JLabel thongtinlop = new JLabel("[Lớp:       , sỉ số:         , sỉ số nam:         , sỉ số nữ:       ]");
		thongtinlop.setForeground(new Color(64,177,191));
//		Vector<JComboBox> gioitinhcmblist = new Vector<JComboBox>();
//		Vector<JComboBox> lopcmblist = new Vector<JComboBox>();
//		java.util.Vector<String> gioitinhdata = new Vector<String>();
//		gioitinhdata.add("Nam");
//		gioitinhdata.add("Nữ");
		xemdssv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
//					gioitinhcmblist.clear();;
//					lopcmblist.clear();
					for(int i = table.getRowCount() - 1; i >= 0; i--) {
						dTable.removeRow(i);
					}
					session.getTransaction().begin();
					
					String msl = lophoccb.getSelectedItem().toString();
					LopHoc l = session.get(LopHoc.class, msl);
					session.refresh(l);
					Set<SinhVien> svlist = l.getDanhSachSinhVien();
					int ss = 0;
					session.getTransaction().commit();
					if(svlist != null) {
						ss = svlist.size();
					}
					int ssnam = 0, ssnu = 0;
					if(ss > 0) {
						for(int i = table.getRowCount() - 1; i >= 0; i--) {
							dTable.removeRow(i);
						}		
						
						for(SinhVien st : svlist) {
	//						{"Chọn", "MSSV", "Họ tên", "Giới tính", "Lớp", "Email"};
//							JComboBox<String> cmb1 = new JComboBox<String>(gioitinhdata);
//							JComboBox<LopHoc> cmb2 = new JComboBox<LopHoc>(lophocdata);
//							gioitinhcmblist.add(cmb1);
//							lopcmblist.add(cmb2);
//							table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(cmb1));
//							table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cmb2));
							Object ob[] = {false, st.getMssv(), st.getHoten(), st.getGioitinh(), st.getLop(), st.getEmail()};
							dTable.addRow(ob);
							
							if(st.getGioitinh().equals("Nam")) ssnam++;
							if(st.getGioitinh().equals("Nữ")) ssnu ++;						
						}
						thongtinlop.setText("[Lớp: "+ msl + "___sỉ số: " + ss + "___sỉ số nam: " + ssnam + "___sỉ số nữ: " + ssnu + "]  ");
					}
					else {
						thongtinlop.setText("[Lớp: "+ msl + "___sỉ số: " + ss + "___sỉ số nam: " + ssnam + "___sỉ số nữ: " + ssnu + "]  ");
					}
				}
			}
		});
		cfloor1.add(Box.createHorizontalGlue());
				
		JPanel cfloor2 = new JPanel();
		cfloor2.setLayout(new BoxLayout(cfloor2, BoxLayout.X_AXIS));
		cfloor2.setMaximumSize(new Dimension(2000, 37));
		JLabel findLabel = new JLabel(" Nhập MSSV: ");
		cfloor2.add(findLabel);
		JTextField findTextField = new JTextField();
		cfloor2.add(findTextField);
		JButton find = new JButton("Tìm kiếm");
		find.setMaximumSize(new Dimension(125, 37));
		cfloor2.add(find);
		find.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					boolean flag = false;
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 1).equals(findTextField.getText())) {
							flag = true;
							table.setRowSelectionInterval(i, i);
							table.scrollRectToVisible(table.getCellRect(i, 0, true));
						}
					}
					if(!flag) {
						statusCapNhat.setText("Không tìm thấy sinh viên này!");
						showStatusCapNhat();
					}
				}
			}
		});
		
		cfloor2.add(Box.createHorizontalStrut(100));
		cfloor2.add(thongtinlop);
		cfloor2.add(Box.createHorizontalGlue());
		JButton xoalophoc = new JButton("Xóa lớp học này");
		xoalophoc.setMaximumSize(new Dimension(200, 37));
		cfloor2.add(Box.createHorizontalStrut(100));
		cfloor2.add(xoalophoc);
		xoalophoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					session.getTransaction().begin();
					LopHoc tudo = session.get(LopHoc.class, "100TuDo");
					LopHoc temp = (LopHoc)lophoccb.getSelectedItem();
					Set<SinhVien> svlisttemp = temp.getDanhSachSinhVien();
					if(svlisttemp != null) {
						for(SinhVien sv : svlisttemp) {
							sv.setLop(tudo);
							session.update(sv);
						}
					}
					session.delete(temp);
					session.getTransaction().commit();
//					System.out.println(temp.toString());
					for(int i = table.getRowCount() - 1; i >= 0; i--) {
						dTable.removeRow(i);
					}
					statusCapNhat.setText("Lớp " + temp.toString() + " vừa bị xóa!");
					showStatusCapNhat();
					lophoccb.removeItem(temp);					
				}
			}
		});
		
		cfloor.add(cfloor2);
		
		JScrollPane tablePane = new JScrollPane(table);
		cfloor.add(tablePane);
		cfloor.add(statusCapNhat);
		//------------------------------------------------------------
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel floor = new JPanel();
		floor.setLayout(new GridLayout(7,1));		
		floor.add(home);
		
		JButton themlophoc = new JButton("Thêm lớp học");
		themlophoc.setForeground(mainColor);
		floor.add(themlophoc);
		themlophoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					ThemLopHocFrame themlophocFrame = new ThemLopHocFrame(session);
					themlophocFrame.getOk().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if(SwingUtilities.isLeftMouseButton(e)) {
								LopHoc temp = themlophocFrame.getLophoc();
								lophoccb.addItem(temp);
							}
						}
					});					
				}
			}
		});
		
		JButton xemmonhocsvdk = new JButton("<html><center> Xem học phần <br /> đăng ký </center></html>");
		xemmonhocsvdk.setForeground(mainColor);
		floor.add(xemmonhocsvdk);
		xemmonhocsvdk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 0).equals(true)) {
							String ms = dTable.getValueAt(i, 1).toString();
							SinhVien temp = session.get(SinhVien.class, ms);
//							HocKyPK hkt = new HocKyPK(hockyhientai.getNamhoc(), hockyhientai.getMahocky());
							new DanhSachMonDangKyFrame(temp, hockyhientai);
						}
					}
				}
			}
		});
		
		JButton themsinhvien = new JButton("<html><center> Thêm sinh viên <br /> vào lớp </center></html>");
		themsinhvien.setForeground(mainColor);
		floor.add(themsinhvien);
		themsinhvien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					LopHoc lop = (LopHoc)lophoccb.getSelectedItem();
					ThemSinhVienFrame themSVF = new ThemSinhVienFrame(session, lop);					
					themSVF.getOk().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if(SwingUtilities.isLeftMouseButton(e)) {
								SinhVien sinhvienmoi = themSVF.getSinhVien();
								if(sinhvienmoi.getLop().equals(lop)) {
									Object ob[] = {false, sinhvienmoi.getMssv(), sinhvienmoi.getHoten(), sinhvienmoi.getGioitinh(), sinhvienmoi.getLop(), sinhvienmoi.getEmail()};
									dTable.addRow(ob);
								}
							}
						}
					});
				}
			}
		});
		
		JButton capnhatthongtinsv = new JButton("<html><center> Cập nhật <br /> thông tin SV </center></html>");
		capnhatthongtinsv.setForeground(mainColor);
		floor.add(capnhatthongtinsv);
		capnhatthongtinsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 0).toString() == "true") {
							session.getTransaction().begin();
							
							String ms = dTable.getValueAt(i, 1).toString();
							SinhVien temp = session.get(SinhVien.class, ms);
							LopHoc loptemp = session.get(LopHoc.class, dTable.getValueAt(i, 4).toString());
							
							
							temp.setHoten((String)dTable.getValueAt(i, 2));
							temp.setGioitinh((String)dTable.getValueAt(i, 3));
							temp.setLop(loptemp);
							
							System.out.println((String)dTable.getValueAt(i, 2));
							System.out.println(dTable.getValueAt(i, 2));
//							loptemp.getDanhSachSinhVien().add(temp);
							
//							session.update(loptemp);
							session.update(temp);
							session.getTransaction().commit();
							statusCapNhat.setText("Cập nhật thành công!");
	    					showStatusCapNhat();
						}
					}
				}
			}
		});
		
		JButton datlaimatkhau = new JButton("<html><center> Reset mật khẩu <br /> sinh viên </center></html>");
		datlaimatkhau.setForeground(mainColor);
		floor.add(datlaimatkhau);
		datlaimatkhau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 0).toString() == "true") {
							session.getTransaction().begin();
							SinhVien temp = session.get(SinhVien.class, dTable.getValueAt(i, 1).toString());
							temp.setMatkhau(dTable.getValueAt(i, 1).toString());
							session.save(temp);
							session.getTransaction().commit();
						}
					}
				}
			}
		});
		
		JButton xoasinhvien = new JButton("<html><center> Xóa sinh viên <br /> ra khỏi lớp </center></html>");
		xoasinhvien.setForeground(mainColor);
		floor.add(xoasinhvien);
		xoasinhvien.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {						
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 0).toString() == "true") {
							session.getTransaction().begin();
							String ms = dTable.getValueAt(i, 1).toString();
							SinhVien temp = session.get(SinhVien.class, ms);
							Query qx = session.createQuery("DELETE FROM DangKy WHERE mssv =: ms");
							qx.setParameter("ms", ms);
							session.delete(temp);
							session.getTransaction().commit();
							
							dTable.removeRow(i);
							i--;
							statusCapNhat.setText("Đã xóa các sinh viên được chọn ra khỏi lớp!");
							showStatusCapNhat();
						}
					}					
				}
			}
		});
		
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
		statusCapNhat = new JLabel();
		statusCapNhat.setVisible(false);
		//-------------------------------------------------------------------
		centerPanel.setLayout(new BorderLayout(0, 3));
		JPanel cfloor = new JPanel();
		cfloor.setLayout(new BoxLayout(cfloor, BoxLayout.Y_AXIS));
		centerPanel.add(cfloor);		
		
		String columns[] = {"Chọn", "Mã", "Mã môn học", "Tên môn học", "Số tín chỉ", "Giáo viên", "Phòng học", "Thứ", "Ca học", "Slots"};
		DefaultTableModel dTable = new DefaultTableModel(columns, 0);
		JTable table = new JTable(dTable) {
			@Override
			public Class getColumnClass(int column) {
				switch(column) {
					case 0: 
						return Boolean.class;
					case 1:
						return Integer.class;
					case 2: 
						return String.class;
					case 3:
						return String.class;
					case 4:
						return Integer.class;
					case 5:
						return String.class;
					case 6:
						return String.class;
					case 7:
						return Integer.class;
					case 8:
						return Integer.class;
					case 9:
						return Integer.class;
					default:
						return Object.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
		table.getColumnModel().getColumn(0).setMinWidth(57);
		table.getColumnModel().getColumn(0).setMaxWidth(57);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(2).setMaxWidth(120);
		table.getColumnModel().getColumn(4).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(100);
		table.getColumnModel().getColumn(6).setMinWidth(100);
		table.getColumnModel().getColumn(6).setMaxWidth(100);
		table.getColumnModel().getColumn(7).setMinWidth(50);
		table.getColumnModel().getColumn(7).setMaxWidth(50);
		table.getColumnModel().getColumn(8).setMinWidth(70);
		table.getColumnModel().getColumn(8).setMaxWidth(70);
		table.getColumnModel().getColumn(9).setMinWidth(50);
		table.getColumnModel().getColumn(9).setMaxWidth(50);
		
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 1);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 2);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 4);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 6);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 7);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 8);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 9);
		table.setRowHeight(37);		
		JScrollPane tablePane = new JScrollPane(table);
		
		JPanel cfloor1 = new JPanel();
		cfloor1.setLayout(new BoxLayout(cfloor1, BoxLayout.X_AXIS));
		cfloor1.setMaximumSize(new Dimension(2000, 37));
		cfloor1.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		cfloor.add(cfloor1);
		cfloor.add(Box.createVerticalStrut(10));
		JLabel lb1 = new JLabel("Đăng ký học phần đợt: ");
		cfloor1.add(lb1);
		JComboBox<DotDangKyHocPhan> cb1 = new JComboBox<DotDangKyHocPhan>();
		cfloor1.add(cb1);
		cb1.setMaximumSize(new Dimension(200, 37));
		Query q1 = session.createQuery("FROM DotDangKyHocPhan");
		java.util.List<DotDangKyHocPhan> dotdangkylist = q1.list();
		for(DotDangKyHocPhan t : dotdangkylist) {
			if(t.getHocky().equals(hockyhientai)) {
				cb1.addItem(t);
			}
		}
		JButton xem = new JButton("Xem danh sách học phần");
		xem.setMaximumSize(new Dimension(250, 37));
		cfloor1.add(xem);
		cfloor1.add(Box.createHorizontalGlue());
//		JButton xoaddkhp = new JButton("Xóa đợt đăng ký học phần này");
//		cfloor1.add(xoaddkhp);
		
		JLabel lb2 = new JLabel();
		lb2.setVisible(false);
		lb2.setForeground(new Color(45, 134, 89));
		
		xem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					for(int i = table.getRowCount() - 1; i >= 0; i--) {
						dTable.removeRow(i);
					}
					session.getTransaction().begin();
					int id = ((DotDangKyHocPhan)cb1.getSelectedItem()).getMadot();
					DotDangKyHocPhan temp = session.get(DotDangKyHocPhan.class, id);
					session.refresh(temp);
					Set<LichHoc> hocphanlist = temp.getDanhSachHocPhan();
					if(hocphanlist != null && hocphanlist.isEmpty() == false) {
						for(LichHoc lh : hocphanlist) {
							MonHoc mht = lh.getMonhoc();
							Object ob[] = {false, lh.getMathongtin(), mht.getMamonhoc(), mht.getTenmonhoc(), mht.getSotinchi(), lh.getGiaovien(), lh.getPhonghoc(), lh.getNgaythu(), lh.getCahoc(), lh.getSlots()};
							dTable.addRow(ob);
						}
					}
					
					session.getTransaction().commit();
				    lb2.setText(((DotDangKyHocPhan)cb1.getSelectedItem()).toLabel() + " ");
				    lb2.setVisible(true);				    
				}
			}
		});
		JPanel cfloor2 = new JPanel();
		cfloor2.setLayout(new BoxLayout(cfloor2, BoxLayout.X_AXIS));
		cfloor2.add(lb2);
		cfloor.add(cfloor2);
		cfloor.add(tablePane);
		cfloor.add(statusCapNhat);
		//--------------------------------------------------------------------
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel floor = new JPanel();
		floor.setLayout(new GridLayout(5,1));		
		floor.add(home);
		
		JButton taodotdangky = new JButton("<html><center> Thêm đợt <br> đăng ký mới </center><html>");
		taodotdangky.setForeground(mainColor);
		floor.add(taodotdangky);
		taodotdangky.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					ThemDotDangKyHocPhanFrame themDDKHPFrame = new ThemDotDangKyHocPhanFrame(session, hockyhientai);
					themDDKHPFrame.getOk().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if(SwingUtilities.isLeftMouseButton(e)) {
								DotDangKyHocPhan temp = themDDKHPFrame.getDdkhp();
								cb1.addItem(temp);
							}
						}
					});
				}
			}
		});
		
		JButton themhocphan = new JButton("Thêm học phần");
		themhocphan.setForeground(mainColor);
		floor.add(themhocphan);
		themhocphan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					ThemHocPhanFrame themHPF = new ThemHocPhanFrame(session, hockyhientai);
					themHPF.getOk().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseReleased(MouseEvent e) {
							if(SwingUtilities.isLeftMouseButton(e)) {
								LichHoc lh = themHPF.getLichHoc();
								MonHoc mht = lh.getMonhoc();
								Object ob[] = {false, lh.getMathongtin(), mht.getMamonhoc(), mht.getTenmonhoc(), mht.getSotinchi(), lh.getGiaovien(), lh.getPhonghoc(), lh.getNgaythu(), lh.getCahoc(), lh.getSlots()};
								dTable.addRow(ob);
								
								session.getTransaction().begin();
								ChiTiet ct = new ChiTiet(lh.getMathongtin(), ((DotDangKyHocPhan)cb1.getSelectedItem()).getMadot());
								session.save(ct);
								session.getTransaction().commit();
							}
						}
					});
				}
			}
		});
		
		JButton xoahocphan = new JButton("Xóa học phần");
		xoahocphan.setForeground(mainColor);
		floor.add(xoahocphan);
		xoahocphan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 0).toString() == "true") {
							session.getTransaction().begin();
							int mtt = Integer.parseInt(dTable.getValueAt(i, 1).toString());
							int md = ((DotDangKyHocPhan)cb1.getSelectedItem()).getMadot();
							LichHoc temp = session.get(LichHoc.class, mtt);
							Query q3 = session.createQuery("DELETE FROM ChiTiet WHERE mathongtin =: mtt");
							q3.setParameter("mtt", mtt);
							q3.executeUpdate();
							
							Query q4 = session.createQuery("DELETE FROM DangKy WHERE mathongtin =: mtt");
							q4.setParameter("mtt", mtt);	
							q4.executeUpdate();							
							session.getTransaction().commit();					
							
							session.getTransaction().begin();
							session.delete(temp);							
							session.getTransaction().commit();													
							dTable.removeRow(i);
							i--;
							statusCapNhat.setText("Đã xóa các học phần đã chọn!");
							showStatusCapNhat();
						}
					}
				}
			}
		});
		
		JButton xemdanhsach = new JButton("<html><center> Xem danh sách <br> SV đăng ký </center><html>");
		xemdanhsach.setForeground(mainColor);
		floor.add(xemdanhsach);
		xemdanhsach.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 0).toString() == "true") {
							int mtt = Integer.parseInt(dTable.getValueAt(i, 1).toString());
							LichHoc temp = session.get(LichHoc.class, mtt);
							new XemDanhSachSinhVienDangKyFrame(temp);
						}						
					}
				}
			}
		});
		
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
