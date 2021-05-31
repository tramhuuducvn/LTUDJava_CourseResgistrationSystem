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

public class ThemMonHocFrame extends JFrame {
	private MonHoc mh;
	JButton ok;
	JButton cancel;
	public ThemMonHocFrame(Session session) {
		
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));
		
		JPanel floor1 = new JPanel();
		floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		JLabel mamhLabel = new JLabel(" Mã môn học :      ");
		JTextField mamhTextField = new JTextField();
		floor1.add(mamhLabel);
		floor1.add(mamhTextField);
		floor1.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor1);
		
		JPanel floor2 = new JPanel();
		floor2.setLayout(new BoxLayout(floor2, BoxLayout.X_AXIS));
		JLabel tenLabel = new JLabel(" Tên môn học :     ");
		JTextField tenTextField = new JTextField();
		floor2.setMaximumSize(new Dimension(2000, 37));
		floor2.add(tenLabel);
		floor2.add(tenTextField);
		floor.add(floor2);
		
		JPanel floor3 = new JPanel();
		floor3.setLayout(new BoxLayout(floor3, BoxLayout.X_AXIS));
		JLabel stcLabel = new JLabel(" Số tín chỉ :           ");
		JTextField stcTextField = new JTextField();
		floor3.setMaximumSize(new Dimension(2000, 37));
		floor3.add(stcLabel);
		floor3.add(stcTextField);
		floor.add(floor3);
		
		floor.add(Box.createVerticalGlue());
		
		JPanel floor4 = new JPanel();
		floor4.setLayout(new BoxLayout(floor4, BoxLayout.X_AXIS));
		floor4.add(Box.createHorizontalGlue());
		
		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100, 100));
		ok.setForeground(new Color(227, 140, 0));
		ok.setOpaque(true);
		ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					try {
						String mamh = mamhTextField.getText();
						String ten = tenTextField.getText();
						int stc = Integer.parseInt(stcTextField.getText());
						mh = new MonHoc(mamh, ten,stc);
						session.getTransaction().begin();
						session.save(mh);
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
		floor4.add(cancel);
		floor4.add(Box.createRigidArea(new Dimension(20, 0)));
		floor4.add(ok);
		floor.add(floor4);
		
		cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					dispose();
				}
			}
		});
		
		add(floor);
		setTitle("Thêm môn học");
		setSize(520, 225);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                dispose();
            }
        });
		setVisible(true);
	}
	
	public MonHoc getMonHoc() {
		return this.mh;
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
				HibernateUtil hbn = new HibernateUtil("localhost", "3306", "CourseRegistrationSystem", "tramhuuduc", "19120484@Ubuntu");
				Session session = hbn.getFACTORY().openSession();
				ThemMonHocFrame a = new ThemMonHocFrame(session);
			}
		});
	}
	
}