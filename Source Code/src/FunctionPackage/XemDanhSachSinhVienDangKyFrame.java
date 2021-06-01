package FunctionPackage;

import POJO.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;
import javax.swing.table.DefaultTableModel;

import org.hibernate.query.Query;
import org.hibernate.*;

public class XemDanhSachSinhVienDangKyFrame extends JFrame{
	final Color borderColor = new Color(200, 126, 74);
	
	private int demCapNhat = 0;
	JLabel statusCapNhat;	
	public XemDanhSachSinhVienDangKyFrame(LichHoc lichhoc) {
		Set<SinhVien> ds = lichhoc.getDanhSachSinhVien();
		setLayout(new BorderLayout(3, 3));
		
//		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
//		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
//		northPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
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
		centerPanel.add(cfloor2);
		
		
		String columns[] = {"MSSV", "Họ tên", "Giới tính", "Lớp", "Email"};
		DefaultTableModel dTable = new DefaultTableModel(columns, 0);
		JTable table = new JTable(dTable) {
			@Override
			public Class getColumnClass(int column) {
				switch(column) {
					case 0:
						return String.class;
					case 1: 
						return String.class;
					case 2:
						return String.class;
					case 3:
						return LopHoc.class;
					case 4:
						return String.class;
					default:
						return Object.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
		table.getColumnModel().getColumn(0).setMinWidth(150);
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setMinWidth(270);
		table.getColumnModel().getColumn(1).setMaxWidth(270);
		table.getColumnModel().getColumn(2).setMinWidth(90);
		table.getColumnModel().getColumn(2).setMaxWidth(90);
		table.getColumnModel().getColumn(3).setMinWidth(120);
		table.getColumnModel().getColumn(3).setMaxWidth(120);

		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 2);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 3);
		table.setRowHeight(37);		
		JScrollPane tablePane = new JScrollPane(table);
		centerPanel.add(tablePane);
		if(ds != null || ds.isEmpty() == false) {
			for(SinhVien sv : ds) {
	//			{"MSSV", "Họ tên", "Giới tính", "Lớp", "Email"};
				Object ob[] = {sv.getMssv(), sv.getHoten(), sv.getGioitinh(), sv.getLop(), sv.getEmail()};
				dTable.addRow(ob);
			}
		}
		
		statusCapNhat = new JLabel();
		centerPanel.add(statusCapNhat);
		find.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					boolean flag = false;
					for(int i = 0; i < table.getRowCount(); ++i) {
						if(dTable.getValueAt(i, 0).equals(findTextField.getText())) {
							flag = true;
							table.setRowSelectionInterval(i, i);
							table.scrollRectToVisible(table.getCellRect(i, 0, true));
							statusCapNhat.setVisible(false);
						}
					}
					if(!flag) {
						statusCapNhat.setText("Không tìm thấy sinh viên này!");
						statusCapNhat.setForeground(new Color(255, 107, 107));
						statusCapNhat.setVisible(true);
					}
				}
			}
		});
		
		setTitle("Danh sách sinh viên đăng ký học phần mã " + lichhoc.getMathongtin() + "___Môn: " + lichhoc.getMonhoc().getTenmonhoc());
		setSize(970, 670);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
		setVisible(true);
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
//				HocKyPK hkpk = new HocKyPK(2020, "HK1");
//				HocKy hkht = session.get(HocKy.class, hkpk);
				LichHoc lichhoc = session.get(LichHoc.class, 1);
				XemDanhSachSinhVienDangKyFrame main = new XemDanhSachSinhVienDangKyFrame(lichhoc);
			}
		});
	}
}
