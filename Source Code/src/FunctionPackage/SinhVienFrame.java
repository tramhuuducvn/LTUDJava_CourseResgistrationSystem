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

public class SinhVienFrame extends JFrame{
	private HocKy hockyhientai;
	private SinhVien sv;
	private Date curDate;
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
		
		Query<HocKy> q1 = session.createQuery("FROM HocKy WHERE trangthai =: b");
		q1.setParameter("b", true);
		hockyhientai = q1.getSingleResult();
		curDate = new Date();
		
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
		session.refresh(sv);
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
		statusCapNhat = new JLabel("Cập nhật thành công!");
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
				
				statusCapNhat.setVisible(true);
				showStatusCapNhat();
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
		session.refresh(sv);
		centerPanel.removeAll();
		westPanel.removeAll();
		statusCapNhat = new JLabel();
		//Center ---------------------------------------------------------
		centerPanel.setLayout(new BorderLayout(0, 3));
		JPanel cfloor = new JPanel();
		cfloor.setLayout(new BoxLayout(cfloor, BoxLayout.Y_AXIS));
		centerPanel.add(cfloor);		
		
		cfloor.add(Box.createVerticalStrut(5));
		JPanel cfloor1 = new JPanel();
		cfloor1.setLayout(new BoxLayout(cfloor1, BoxLayout.X_AXIS));
		JLabel lb1 = new JLabel("Hiện tại không có đợt đăng ký nào!");
		lb1.setForeground(new Color(246, 61, 61));
		cfloor1.add(lb1);
		cfloor.add(cfloor1);
		cfloor.add(Box.createVerticalStrut(15));
		
		JPanel cfloor2 = new JPanel();
		cfloor2.setLayout(new BoxLayout(cfloor2, BoxLayout.X_AXIS));
		JLabel lb2 = new JLabel("Danh sách đã đăng ký");
		lb2.setForeground(new Color(253, 105, 1));
		cfloor2.add(lb2);
		cfloor.add(cfloor2);
		cfloor.add(Box.createVerticalStrut(5));
		
		Set<DotDangKyHocPhan> ddkhpList = hockyhientai.getDanhSachDDKHP();
		DotDangKyHocPhan ddkhp = null;
		for(DotDangKyHocPhan c : ddkhpList) {
			if(curDate.after(c.getNgaybatdaudangky()) && curDate.before(c.getNgayketthucdangky())) {
				ddkhp = c;
				break;
			}
		}
		
		if(ddkhp != null) {
			lb1.setText("Học kỳ: " + ddkhp.getHocky().toString() + " Đợt đăng ký " + ddkhp.getSTT() + " kết thúc vào(yyyy-mm-dd): " + ddkhp.getNgayketthucdangky());
		}
		
		String columns[] = {"Chọn", "Mã", "Mã môn học", "Tên môn học", "Số tín chỉ", "Giáo viên", "Phòng học", "Thứ", "Ca học", "Slots"};
		DefaultTableModel dTable1 = new DefaultTableModel(columns, 0);
		JTable table1 = new JTable(dTable1) {
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
		table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
		table1.setFillsViewportHeight(true);

		table1.getColumnModel().getColumn(0).setMinWidth(57);
		table1.getColumnModel().getColumn(0).setMaxWidth(57);
		table1.getColumnModel().getColumn(1).setMinWidth(50);
		table1.getColumnModel().getColumn(1).setMaxWidth(50);
		table1.getColumnModel().getColumn(2).setMinWidth(120);
		table1.getColumnModel().getColumn(2).setMaxWidth(120);
		table1.getColumnModel().getColumn(4).setMinWidth(100);
		table1.getColumnModel().getColumn(4).setMaxWidth(100);
		table1.getColumnModel().getColumn(6).setMinWidth(100);
		table1.getColumnModel().getColumn(6).setMaxWidth(100);
		table1.getColumnModel().getColumn(7).setMinWidth(50);
		table1.getColumnModel().getColumn(7).setMaxWidth(50);
		table1.getColumnModel().getColumn(8).setMinWidth(70);
		table1.getColumnModel().getColumn(8).setMaxWidth(70);
		table1.getColumnModel().getColumn(9).setMinWidth(50);
		table1.getColumnModel().getColumn(9).setMaxWidth(50);

		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 1);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 2);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 4);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 6);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 7);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 8);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 9);
		table1.setRowHeight(37);		
		JScrollPane tablePane1 = new JScrollPane(table1);		
		cfloor.add(tablePane1);
		
		Set<LichHoc> hpddklist = sv.getDanhSachLichHoc();
		if(hpddklist != null && hpddklist.isEmpty() == false) {
			for(LichHoc lh : hpddklist) {
				if(lh.getHocky().equals(hockyhientai)) {
					MonHoc mht = lh.getMonhoc();
					Object ob[] = {false, lh.getMathongtin(), mht.getMamonhoc(), mht.getTenmonhoc(), mht.getSotinchi(), lh.getGiaovien(), lh.getPhonghoc(), lh.getNgaythu(), lh.getCahoc(), lh.getSlots()};
					dTable1.addRow(ob);
				}
			}
		}
		
		cfloor.add(Box.createVerticalStrut(15));
		JPanel cfloor3 = new JPanel();
		cfloor3.setLayout(new BoxLayout(cfloor3, BoxLayout.X_AXIS));
		JLabel lb3 = new JLabel("Danh sách học phần mở");
		lb3.setForeground(new Color(253, 105, 1));
		cfloor3.add(lb3);
		cfloor.add(cfloor3);
		cfloor.add(Box.createVerticalStrut(5));
		
		DefaultTableModel dTable2 = new DefaultTableModel(columns, 0);
		JTable table2 = new JTable(dTable2) {
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
		table2.setPreferredScrollableViewportSize(table2.getPreferredSize());
		table2.setFillsViewportHeight(true);

		table2.getColumnModel().getColumn(0).setMinWidth(57);
		table2.getColumnModel().getColumn(0).setMaxWidth(57);
		table2.getColumnModel().getColumn(1).setMinWidth(50);
		table2.getColumnModel().getColumn(1).setMaxWidth(50);
		table2.getColumnModel().getColumn(2).setMinWidth(120);
		table2.getColumnModel().getColumn(2).setMaxWidth(120);
		table2.getColumnModel().getColumn(4).setMinWidth(100);
		table2.getColumnModel().getColumn(4).setMaxWidth(100);
		table2.getColumnModel().getColumn(6).setMinWidth(100);
		table2.getColumnModel().getColumn(6).setMaxWidth(100);
		table2.getColumnModel().getColumn(7).setMinWidth(50);
		table2.getColumnModel().getColumn(7).setMaxWidth(50);
		table2.getColumnModel().getColumn(8).setMinWidth(70);
		table2.getColumnModel().getColumn(8).setMaxWidth(70);
		table2.getColumnModel().getColumn(9).setMinWidth(50);
		table2.getColumnModel().getColumn(9).setMaxWidth(50);

		JTableUtil.setCellsAlignment(table2, SwingConstants.CENTER, 1);
		JTableUtil.setCellsAlignment(table2, SwingConstants.CENTER, 2);
		JTableUtil.setCellsAlignment(table2, SwingConstants.CENTER, 4);
		JTableUtil.setCellsAlignment(table2, SwingConstants.CENTER, 6);
		JTableUtil.setCellsAlignment(table2, SwingConstants.CENTER, 7);
		JTableUtil.setCellsAlignment(table2, SwingConstants.CENTER, 8);
		JTableUtil.setCellsAlignment(table2, SwingConstants.CENTER, 9);
		table2.setRowHeight(37);		
		JScrollPane tablePane2 = new JScrollPane(table2);
		cfloor.add(tablePane2);
		
		Set<LichHoc> hpMolist = ddkhp.getDanhSachHocPhan();
		if(hpMolist != null && hpMolist.isEmpty() == false) {
			for(LichHoc lh : hpMolist) {
				if(hpddklist.contains(lh) == false) {
					MonHoc mht = lh.getMonhoc();
					Object ob[] = {false, lh.getMathongtin(), mht.getMamonhoc(), mht.getTenmonhoc(), mht.getSotinchi(), lh.getGiaovien(), lh.getPhonghoc(), lh.getNgaythu(), lh.getCahoc(), lh.getSlots()};
					dTable2.addRow(ob);
				}
			}
		}
		
		//West ------------------------------------------------------------
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(150, 0));
		westPanel.add(Box.createVerticalGlue());
		JPanel floor = new JPanel();
		floor.setLayout(new GridLayout(3,1));		
		floor.add(home);
		
		JButton dangky = new JButton("Đăng ký");
		dangky.setForeground(mainColor);
		floor.add(dangky);
		dangky.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			for(int i = 0; i < table2.getRowCount(); ++i) {
	    				if(dTable2.getValueAt(i, 0).equals(true)) {
	    					int mtt = Integer.parseInt(dTable2.getValueAt(i, 1).toString());
	    					LichHoc lh = session.get(LichHoc.class, mtt);
	    					MonHoc mht = lh.getMonhoc();	    					
	    					Object ob[] = {false, lh.getMathongtin(), mht.getMamonhoc(), mht.getTenmonhoc(), mht.getSotinchi(), lh.getGiaovien(), lh.getPhonghoc(), lh.getNgaythu(), lh.getCahoc(), lh.getSlots()};
	    					dTable1.addRow(ob);
	    					
	    					session.getTransaction().begin();
	    					DangKy dk = new DangKy(Integer.parseInt(dTable2.getValueAt(i, 1).toString()), sv.getMssv());
	    					session.save(dk);
	    					session.getTransaction().commit();
	    					dTable2.removeRow(i); 
	    					i--;
	    				}
	    			}
	    		}
	    	}
	    });
		
		JButton huydangky = new JButton("Hủy đăng ký");
		huydangky.setForeground(mainColor);
		floor.add(huydangky);
		huydangky.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseReleased(MouseEvent e) {
	    		if(SwingUtilities.isLeftMouseButton(e)) {
	    			session.refresh(sv);
	    			for(int i = 0; i < table1.getRowCount(); ++i) {
	    				if(dTable1.getValueAt(i, 0).equals(true)) {
	    					int mtt = Integer.parseInt(dTable1.getValueAt(i, 1).toString());
	    					LichHoc lh = session.get(LichHoc.class, mtt);
	    					MonHoc mht = lh.getMonhoc();	    					
	    					Object ob[] = {false, lh.getMathongtin(), mht.getMamonhoc(), mht.getTenmonhoc(), mht.getSotinchi(), lh.getGiaovien(), lh.getPhonghoc(), lh.getNgaythu(), lh.getCahoc(), lh.getSlots()};
	    					dTable2.addRow(ob);
	    					
	    					session.getTransaction().begin();
	    					DangKyPK dkpk = new DangKyPK(Integer.parseInt(dTable1.getValueAt(i, 1).toString()), sv.getMssv());
	    					DangKy dk = session.get(DangKy.class, dkpk);
	    					session.delete(dk);
	    					session.getTransaction().commit();
	    					dTable1.removeRow(i); 
	    					i--;
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
	
	public void ketquaDKHPClickAction(){
		session.refresh(sv);
		centerPanel.removeAll();
		westPanel.removeAll();
		//-----------------------------------------------------------------------------
		centerPanel.setLayout(new BorderLayout(0, 3));
		JPanel cfloor = new JPanel();
		cfloor.setLayout(new BoxLayout(cfloor, BoxLayout.Y_AXIS));
		centerPanel.add(cfloor);		
		
		cfloor.add(Box.createVerticalStrut(5));
		JPanel cfloor1 = new JPanel();
		cfloor1.setLayout(new BoxLayout(cfloor1, BoxLayout.X_AXIS));
		JLabel lb1 = new JLabel("Học kỳ: " + hockyhientai.toString());
		lb1.setForeground(new Color(246, 61, 61));
		cfloor1.add(lb1);
		cfloor.add(cfloor1);
		cfloor.add(Box.createVerticalStrut(15));
		
		JPanel cfloor2 = new JPanel();
		cfloor2.setLayout(new BoxLayout(cfloor2, BoxLayout.X_AXIS));
		JLabel lb2 = new JLabel("Danh sách đã đăng ký");
		lb2.setForeground(new Color(253, 105, 1));
		cfloor2.add(lb2);
		cfloor.add(cfloor2);
		cfloor.add(Box.createVerticalStrut(5));
		
		String columns[] = {"Chọn", "Mã", "Mã môn học", "Tên môn học", "Số tín chỉ", "Giáo viên", "Phòng học", "Thứ", "Ca học", "Slots"};
		DefaultTableModel dTable1 = new DefaultTableModel(columns, 0);
		JTable table1 = new JTable(dTable1) {
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
		table1.setPreferredScrollableViewportSize(table1.getPreferredSize());
		table1.setFillsViewportHeight(true);

		table1.getColumnModel().getColumn(0).setMinWidth(57);
		table1.getColumnModel().getColumn(0).setMaxWidth(57);
		table1.getColumnModel().getColumn(1).setMinWidth(50);
		table1.getColumnModel().getColumn(1).setMaxWidth(50);
		table1.getColumnModel().getColumn(2).setMinWidth(120);
		table1.getColumnModel().getColumn(2).setMaxWidth(120);
		table1.getColumnModel().getColumn(4).setMinWidth(100);
		table1.getColumnModel().getColumn(4).setMaxWidth(100);
		table1.getColumnModel().getColumn(6).setMinWidth(100);
		table1.getColumnModel().getColumn(6).setMaxWidth(100);
		table1.getColumnModel().getColumn(7).setMinWidth(50);
		table1.getColumnModel().getColumn(7).setMaxWidth(50);
		table1.getColumnModel().getColumn(8).setMinWidth(70);
		table1.getColumnModel().getColumn(8).setMaxWidth(70);
		table1.getColumnModel().getColumn(9).setMinWidth(50);
		table1.getColumnModel().getColumn(9).setMaxWidth(50);

		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 1);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 2);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 4);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 6);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 7);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 8);
		JTableUtil.setCellsAlignment(table1, SwingConstants.CENTER, 9);
		table1.setRowHeight(37);		
		JScrollPane tablePane1 = new JScrollPane(table1);		
		cfloor.add(tablePane1);
		
		Set<LichHoc> hpddklist = sv.getDanhSachLichHoc();
		if(hpddklist != null && hpddklist.isEmpty() == false) {
			for(LichHoc lh : hpddklist) {
				if(lh.getHocky().equals(hockyhientai)) {
					MonHoc mht = lh.getMonhoc();
					Object ob[] = {false, lh.getMathongtin(), mht.getMamonhoc(), mht.getTenmonhoc(), mht.getSotinchi(), lh.getGiaovien(), lh.getPhonghoc(), lh.getNgaythu(), lh.getCahoc(), lh.getSlots()};
					dTable1.addRow(ob);
				}
			}
		}
		//-----------------------------------------------------------------------------
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
				SinhVien sv = session.get(SinhVien.class, "19120484");
				SinhVienFrame st = new SinhVienFrame(sv, session);
			}
		});
	}
}
