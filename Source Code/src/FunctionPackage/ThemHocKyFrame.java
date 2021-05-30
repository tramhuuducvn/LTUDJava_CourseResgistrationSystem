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

public class ThemHocKyFrame extends JFrame{
	private HocKy hocky;
	JButton ok;
	JButton cancel;
	public ThemHocKyFrame(Session session) {		
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));
		JPanel floor1 = new JPanel();
		floor1.setLayout(new GridLayout(4, 2));
		floor.add(floor1);
		
		JLabel lb1 = new JLabel("Năm học:");
		JTextField textField1 = new JTextField();
		floor1.add(lb1);
		floor1.add(textField1);
		
		JLabel lb2 = new JLabel("Mã học kỳ:");
		String data[] = {"HK1", "HK2"};
		JComboBox cb2 = new JComboBox(data);
		floor1.add(lb2);
		floor1.add(cb2);

		JLabel lb3 = new JLabel("Ngày bắt đầu(yyyy-mm-dd):");
		JTextField textField3 = new JTextField();
		floor1.add(lb3);
		floor1.add(textField3);

		JLabel lb4 = new JLabel("Ngày kết thúc(yyyy-mm-dd):");
		JTextField textField4 = new JTextField();
		floor1.add(lb4);
		floor1.add(textField4);
		
		floor.add(Box.createVerticalGlue());
		JPanel floor5 = new JPanel();
		floor5.setLayout(new BoxLayout(floor5, BoxLayout.X_AXIS));
		
		JLabel lb5 = new JLabel("Học kỳ đã tồn tại!");
		lb5.setForeground(new Color(235,76,100));
		lb5.setVisible(false);
		floor5.add(lb5);
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
						session.getTransaction().begin();
						
						int nam = Integer.parseInt(textField1.getText());
						String mhk = (String)cb2.getSelectedItem();
						String startDay = textField3.getText();
						String endDay = textField4.getText();
						if(startDay.isBlank()) {startDay = String.valueOf(nam) + "-01-01";}
						if(endDay.isBlank()) {endDay = String.valueOf(nam) + "-01-01";}
						HocKyPK check = new HocKyPK(nam, mhk);
						hocky = session.get(HocKy.class, check);
						if(hocky == null) {						
							hocky = new HocKy(nam, mhk, startDay, endDay);
							session.save(hocky);							
							dispose();
						}						
						else {
							lb5.setVisible(true);
						}
						session.getTransaction().commit();
					}
					catch (HibernateException e2) {
						System.out.println(e2.getMessage());
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
	
	public HocKy getHocKy() {
		return this.hocky;
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
					ThemHocKyFrame a = new ThemHocKyFrame(session);
				}catch(HibernateException e) {
					System.out.println(e.getMessage());
				}
			}
		});
	} 
}
