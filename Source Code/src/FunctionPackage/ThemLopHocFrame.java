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

public class ThemLopHocFrame extends JFrame{
	private LopHoc lophoc;
	private JButton ok;
	private JButton cancel;
	
	public ThemLopHocFrame(Session session) {
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));
		
		JPanel floor1 = new JPanel();
		floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		JLabel label1 = new JLabel(" Mã lớp hoc :      ");
		JTextField textField = new JTextField();
		floor1.add(label1);
		floor1.add(textField);
		floor1.setMaximumSize(new Dimension(500, 37));
		floor1.add(Box.createHorizontalStrut(5));
		floor.add(floor1);
		floor.add(Box.createVerticalGlue());
		
		JPanel floor3 = new JPanel();
		floor3.setLayout(new BoxLayout(floor3, BoxLayout.X_AXIS));
		JLabel lb3 = new JLabel();
		lb3.setForeground(new Color(207, 77, 47));
		lb3.setVisible(false);
		floor3.add(lb3);
		floor3.add(Box.createHorizontalGlue());
		
		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100, 100));
		ok.setForeground(new Color(227, 140, 0));
		ok.setOpaque(true);
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					try {
						String malop = textField.getText();
						if(!malop.isBlank()) {
							lophoc = new LopHoc(malop);
							LopHoc temp = session.get(LopHoc.class, malop);
							if(temp != null) {
								lb3.setText("Lớp học đã tồn tại!");
								lb3.setVisible(true);
							}
							else {
								session.getTransaction().begin();
								session.save(lophoc);
								session.getTransaction().commit();
								dispose();
							}
						}
						else {
							lb3.setText("Mã lớp rỗng!");
							lb3.setVisible(true);
						}
						
						
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
		floor3.add(cancel);
		floor3.add(Box.createRigidArea(new Dimension(20, 0)));
		floor3.add(ok);
		floor.add(floor3);
		
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					dispose();
				}
			}
		});
		
		add(floor);
		setTitle("Thêm lớp học mới");
		setSize(520, 172);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
		setVisible(true);
	}
	
	public LopHoc getLophoc() {
		return lophoc;
	}

	public void setLophoc(LopHoc lophoc) {
		this.lophoc = lophoc;
	}

	public JButton getOk() {
		return ok;
	}

	public void setOk(JButton ok) {
		this.ok = ok;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
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
//				HibernateUtil hbn = new HibernateUtil("localhost", "3306", "CourseRegistrationSystem", "tramhuuduc", "19120484@Ubuntu");
//				Session session = hbn.getFACTORY().openSession();
//				ThemLopHocFrame a = new ThemLopHocFrame(session);
//			}
//		});
//	}	
}
