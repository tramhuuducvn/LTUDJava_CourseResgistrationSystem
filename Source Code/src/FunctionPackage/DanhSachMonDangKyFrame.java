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

public class DanhSachMonDangKyFrame extends JFrame{
	final Color borderColor = new Color(200, 126, 74);
	public DanhSachMonDangKyFrame(SinhVien sv, HocKy cur) {
		Set<LichHoc> ds = sv.getDanhSachLichHoc();
		setLayout(new BorderLayout(3, 3));
		
//		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
//		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
//		northPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.setBorder(BorderFactory.createLineBorder(borderColor, 1));
		centerPanel.setLayout(new GridLayout(1,1));
		
		String columns[] = {"Mã", "Mã môn học", "Tên môn học", "Số tín chỉ", "Giáo viên", "Phòng học", "Ca học", "Slots"};
		DefaultTableModel dTable = new DefaultTableModel(columns, 0);
		JTable table = new JTable(dTable) {
			@Override
			public Class getColumnClass(int column) {
				switch(column) {
					case 0:
						return Integer.class;
					case 1: 
						return String.class;
					case 2:
						return String.class;
					case 3:
						return Integer.class;
					case 4:
						return String.class;
					case 5:
						return String.class;
					case 6:
						return Integer.class;
					case 7:
						return Integer.class;
					default:
						return Object.class;
				}
			}
		};
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(120);
		table.getColumnModel().getColumn(1).setMaxWidth(120);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(6).setMinWidth(70);
		table.getColumnModel().getColumn(6).setMaxWidth(70);
		table.getColumnModel().getColumn(7).setMinWidth(50);
		table.getColumnModel().getColumn(7).setMaxWidth(50);
		
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 0);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 3);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 6);
		JTableUtil.setCellsAlignment(table, SwingConstants.CENTER, 7);
		table.setRowHeight(37);		
		JScrollPane tablePane = new JScrollPane(table);
		centerPanel.add(tablePane);
		
		for(LichHoc l : ds) {
			if(cur.equals(l.getHocky())) {
//			String columns[] = {"Mã", "Mã môn học", "Tên môn học", "Số tín chỉ","Giáo viên", "Phòng học", "Ca học", "Slots"};
				Object ob[] = {l.getMathongtin(), l.getMonhoc().getMamonhoc(), l.getMonhoc().getTenmonhoc(), l.getMonhoc().getSotinchi(), l.getGiaovien(), l.getPhonghoc(), l.getCahoc(), l.getSlots()};
				dTable.addRow(ob);
			}
		}
		
		
		setTitle("Danh sách môn học sinh viên " + sv.getMssv() + "-" + sv.getHoten() + " đã đăng ký trong " + cur.toString());
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
				HocKyPK hkpk = new HocKyPK(2020, "HK1");
				HocKy hkht = session.get(HocKy.class, hkpk);
				SinhVien me = session.get(SinhVien.class, "19120477");
				DanhSachMonDangKyFrame main = new DanhSachMonDangKyFrame(me, hkht);
			}
		});
	}
}
