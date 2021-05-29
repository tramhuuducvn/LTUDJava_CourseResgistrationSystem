package FunctionPackage;

import POJO.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.plaf.*;

import org.hibernate.query.Query;
import org.hibernate.*;

public class ThemSinhVienFrame extends JFrame {
	private SinhVien sv;
	JButton ok;
	JButton cancel;
	public ThemSinhVienFrame(Session session) {
		
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));
		
		JPanel floor1 = new JPanel();
		floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		JLabel mssvLabel = new JLabel(" Mã sinh viên :      ");
		JTextField mssvTextField = new JTextField();
		floor1.add(mssvLabel);
		floor1.add(mssvTextField);
		floor1.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor1);
		
		JPanel floor2 = new JPanel();
		floor2.setLayout(new BoxLayout(floor2, BoxLayout.X_AXIS));
		JLabel hotenLabel = new JLabel(" Họ tên sinh viên :");
		JTextField hotenTextField = new JTextField();
		floor2.setMaximumSize(new Dimension(2000, 37));
		floor2.add(hotenLabel);
		floor2.add(hotenTextField);
		floor.add(floor2);		
		
		JPanel floor3 = new JPanel(); 
		floor3.setLayout(new BoxLayout(floor3, BoxLayout.X_AXIS));
		floor3.setMaximumSize(new Dimension(500, 37));
		JLabel gioitinhLabel = new JLabel("Giới tính:             ");
		String[] gt = {"Nam", "Nữ"};
		JComboBox gioitinhCmb = new JComboBox(gt);
		floor3.add(gioitinhLabel); 
		floor3.add(gioitinhCmb);
		floor.add(floor3);
		
		JPanel floor4 = new JPanel(); // Lấy danh sách lớp đưa vào combobox
		floor4.setLayout(new BoxLayout(floor4, BoxLayout.X_AXIS));
		floor4.setMaximumSize(new Dimension(500, 37));
		JLabel lophocLabel = new JLabel("Lớp học:               ");
		floor4.add(lophocLabel);
		JComboBox lophoccb = new JComboBox();
		try {
			Query q1 = session.createQuery("FROM LopHoc");
			java.util.List<LopHoc> lophocList = q1.list();
			for(LopHoc c : lophocList) {
				lophoccb.addItem(c);
			}
		} catch (HibernateException e) {System.out.println(e.getMessage());}
		floor4.add(lophoccb);
		floor.add(floor4);
		
		floor.add(Box.createVerticalGlue());
		JPanel floor5 = new JPanel();
		floor5.setLayout(new BoxLayout(floor5, BoxLayout.X_AXIS));
		floor5.add(Box.createHorizontalGlue());

		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100, 100));
		ok.setForeground(new Color(227, 140, 0));
		ok.setOpaque(true);
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					try {
						String magv = mssvTextField.getText();
						String hoten = hotenTextField.getText();
						String gioitinh = (String)gioitinhCmb.getSelectedItem();
						LopHoc lop = (LopHoc)lophoccb.getSelectedItem();
						sv = new SinhVien(magv, hoten, gioitinh, lop);
						session.getTransaction().begin();
						session.save(sv);
						session.getTransaction().commit();
						dispose();
					}catch (HibernateException e1) {
						System.out.println(e1.getMessage());
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
		
		add(floor);
		setTitle("Change Password");
		setSize(520, 225);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
		setVisible(true);
	}
	
	public SinhVien getSinhVien() {
		return this.sv;
	}
	
	public JButton getOk() {
		return ok;
	}

	public JButton getCancel() {
		return cancel;
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
				try {
					HibernateUtil hbn = new HibernateUtil("localhost", "3306", "CourseRegistrationSystem", "tramhuuduc", "19120484@Ubuntu");
					Session session = hbn.getFACTORY().openSession();
					ThemSinhVienFrame a = new ThemSinhVienFrame(session);
				}catch(HibernateException e) {
					System.out.println(e.getMessage());
				}
			}
		});
	}
	
}
