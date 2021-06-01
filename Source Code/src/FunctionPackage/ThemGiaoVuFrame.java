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

public class ThemGiaoVuFrame extends JFrame {
	private GiaoVu gv;
	JButton ok;
	JButton cancel;
	public ThemGiaoVuFrame(Session session) {
		
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));
		
		JPanel floor1 = new JPanel();
		floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		JLabel magvLabel = new JLabel(" Mã giáo vụ :      ");
		JTextField magvTextField = new JTextField();
		floor1.add(magvLabel);
		floor1.add(magvTextField);
		floor1.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor1);
		
		JPanel floor2 = new JPanel();
		floor2.setLayout(new BoxLayout(floor2, BoxLayout.X_AXIS));
		JLabel hotenLabel = new JLabel(" Họ tên giáo vụ :");
		JTextField hotenTextField = new JTextField();
		floor2.setMaximumSize(new Dimension(2000, 37));
		floor2.add(hotenLabel);
		floor2.add(hotenTextField);
		floor.add(floor2);
		floor.add(Box.createVerticalGlue());
		
		JPanel floor3 = new JPanel();
		floor3.setLayout(new BoxLayout(floor3, BoxLayout.X_AXIS));
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
						String magv = magvTextField.getText();
						String hoten = hotenTextField.getText();
						gv = new GiaoVu(magv, hoten);
						session.getTransaction().begin();
						session.save(gv);
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
		setTitle("Thêm giáo vụ mới");
		setSize(520, 225);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
		setVisible(true);
	}
	
	public GiaoVu getGiaoVu() {
		return this.gv;
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
//				HibernateUtil hbn = new HibernateUtil("localhost", "3306", "CourseRegistrationSystem", "tramhuuduc", "19120484@Ubuntu");
//				Session session = hbn.getFACTORY().openSession();
//				ThemGiaoVuFrame a = new ThemGiaoVuFrame(session);
//			}
//		});
//	}
	
}
