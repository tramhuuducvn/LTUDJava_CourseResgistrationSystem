package FunctionPackage;

import POJO.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;

import org.hibernate.query.Query;
import org.hibernate.*;
import org.hibernate.exception.*;

public class ThemHocPhanFrame extends JFrame{
	private LichHoc lh;
	JButton ok;
	JButton cancel;
	public ThemHocPhanFrame(Session session, HocKy hk) {		
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));
		JPanel floor1 = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		floor1.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		floor.add(floor1);		
		
		
		JLabel lb1 = new JLabel(" Môn học:");
		JComboBox<MonHoc> cb1 = new JComboBox<MonHoc>();		
		Query<MonHoc> q1 = session.createQuery("FROM MonHoc");
		java.util.List<MonHoc> l1 = q1.list();
		for(MonHoc mh : l1) {
			cb1.addItem(mh);
		}
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 10;
		gbc.gridwidth = 1;
		gbc.gridx = 0; gbc.gridy = 0;
		floor1.add(lb1, gbc);
		gbc.gridwidth = 7;
		gbc.gridx = 2; gbc.gridy = 0;
		floor1.add(cb1, gbc);
		
		
		JLabel lb2 = new JLabel(" Giáo viên:");
		gbc.gridwidth = 1;
		gbc.gridx = 0; gbc.gridy = 1;
		floor1.add(lb2, gbc);
		JTextField textField1 = new JTextField();
		gbc.gridwidth = 7;
		gbc.gridx = 2; gbc.gridy = 1;
		floor1.add(textField1, gbc);
		
		JLabel lb3 = new JLabel(" Phòng học:");
		gbc.gridwidth = 1;
		gbc.gridx = 0; gbc.gridy = 2;
		floor1.add(lb3, gbc);
		JTextField textField2 = new JTextField();
		gbc.gridwidth = 7;
		gbc.gridx = 2; gbc.gridy = 2;
		floor1.add(textField2, gbc);
		
		JLabel lb4 = new JLabel(" Ngày thứ:");
		gbc.gridwidth = 1;
		gbc.gridx = 0; gbc.gridy = 3;
		floor1.add(lb4, gbc);
		Integer[] thu = {2, 3, 4, 5, 6, 7}; 
		JComboBox<Integer> cb4 = new JComboBox<Integer>(thu);
		gbc.gridx = 2; gbc.gridy = 3;
		floor1.add(cb4, gbc);
		
		JLabel lb5 = new JLabel(" Ca học:");
		gbc.gridx = 3; gbc.gridy = 3;
		floor1.add(lb5, gbc);
		Integer[] ca = {1, 2, 3, 4}; 
		JComboBox<Integer> cb5 = new JComboBox<Integer>(ca);
		gbc.gridx = 4; gbc.gridy = 3;
		floor1.add(cb5, gbc);
		
		JLabel lb6 = new JLabel(" Slots:");
		gbc.gridwidth = 1;
		gbc.gridx = 5; gbc.gridy = 3;
		floor1.add(lb6, gbc);
		JTextField textField6 = new JTextField();
		gbc.gridwidth = 3;
		gbc.gridx = 6; gbc.gridy = 3;
		floor1.add(textField6, gbc);

		
		floor.add(Box.createVerticalGlue());
		JPanel floor5 = new JPanel();
		floor5.setLayout(new BoxLayout(floor5, BoxLayout.X_AXIS));
		
		JLabel lb7 = new JLabel("Học phần đã tồn tại!");
		lb7.setForeground(new Color(235,76,100));
		lb7.setVisible(false);
		floor5.add(lb7);
		floor5.add(Box.createHorizontalGlue());

		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100, 100));
		ok.setForeground(new Color(227, 140, 0));
		ok.setOpaque(true);
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {					
					int mathongtin = 1;
					while(session.get(LichHoc.class, mathongtin) != null) {
						mathongtin++;
					}				
					lh = new LichHoc(mathongtin, hk, (MonHoc)cb1.getSelectedItem(), textField1.getText(), textField2.getText(), (Integer)cb4.getSelectedItem(), (Integer)cb5.getSelectedItem() ,Integer.parseInt(textField6.getText()));
					Query<LichHoc> q2 = session.createQuery("FROM LichHoc");
					java.util.List<LichHoc> l2 = q2.list();
					boolean flag = false;
					for(LichHoc c : l2) {
						if(c.getPhonghoc().equals(lh.getPhonghoc()) && c.getNgaythu() == lh.getNgaythu() && c.getCahoc() == lh.getCahoc()) {
							flag = true;
						}
					}
					if(!flag) {
						session.getTransaction().begin();
						session.save(lh);
						session.getTransaction().commit();
						dispose();	
					}
					else {
						lb7.setVisible(true);
					}									
				}
			}
		});		

		cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(100, 100));
		cancel.setForeground(new Color(227, 140, 0));
		cancel.setOpaque(true);
		floor5.add(cancel);
		floor5.add(Box.createRigidArea(new Dimension(20, 0)));
		floor5.add(ok);
		floor.add(floor5);

		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					dispose();
				}
			}
		});
		
		floor5.add(Box.createHorizontalStrut(15));
		
		add(floor);
		setTitle("Thêm học kỳ mới");
		setSize(620, 300);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
		setVisible(true);
	}	
	
	public LichHoc getLichHoc(){
		return this.lh;
	}

	public JButton getOk() {
		return ok;
	}

	public JButton getCancel() {
		return cancel;
	}

//	public static void main(String[] args) {
//		try {
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("GTK+".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//            Enumeration keys = UIManager.getDefaults().keys();
//            while (keys.hasMoreElements()) {
//                Object key = keys.nextElement();
//                Object value = UIManager.get(key);
//                if (value instanceof FontUIResource) {
//                    FontUIResource orig = (FontUIResource) value;
//                    Font font = new Font("TimeNewRoman" , Font.PLAIN, 17);
//                    UIManager.put(key, new FontUIResource(font));
//                }
//            }            
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (java.lang.InstantiationException ex) {
//            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//		EventQueue.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					HibernateUtil hbn = new HibernateUtil("localhost", "3306", "CourseRegistrationSystem", "tramhuuduc", "19120484@Ubuntu");
//					Session session = hbn.getFACTORY().openSession();
//					HocKyPK hkpk = new HocKyPK(2020, "HK1");
//					HocKy hkht = session.get(HocKy.class, hkpk);
//					ThemHocPhanFrame a = new ThemHocPhanFrame(session, hkht);
//				}catch(HibernateException e) {
//					System.out.println(e.getMessage());
//				}
//			}
//		});
//	}
}
