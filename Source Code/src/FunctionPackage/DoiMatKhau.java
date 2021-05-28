package FunctionPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DoiMatKhau extends JFrame{
	private JButton ok;
	private JButton cancel;
	
	JPasswordField matkhaucuTextField;
	JPasswordField matkhaumoiTextField;
	JPasswordField nhaplaiTextField;
	
	public DoiMatKhau() {
		JPanel floor = new JPanel();
		floor.setLayout(new BoxLayout(floor, BoxLayout.Y_AXIS));
		
		JPanel floor1 = new JPanel();
		floor1.setLayout(new BoxLayout(floor1, BoxLayout.X_AXIS));
		JLabel matkhaucuLabel = new JLabel(" Mật khẩu cũ :                  ");
		matkhaucuTextField = new JPasswordField();
		floor1.add(matkhaucuLabel);
		floor1.add(matkhaucuTextField);
		floor1.setMaximumSize(new Dimension(2000, 37));
		floor.add(floor1);
		
		JPanel floor2 = new JPanel();
		floor2.setLayout(new BoxLayout(floor2, BoxLayout.X_AXIS));
		JLabel matkhaumoiLabel = new JLabel(" Mật khẩu mới :                ");
		matkhaumoiTextField = new JPasswordField();
		floor2.setMaximumSize(new Dimension(2000, 37));
		floor2.add(matkhaumoiLabel);
		floor2.add(matkhaumoiTextField);
		floor.add(floor2);
				
		JPanel floor3 = new JPanel();
		floor3.setLayout(new BoxLayout(floor3, BoxLayout.X_AXIS));
		JLabel nhaplaiLabel = new JLabel(" Nhập lại mật khẩu mới : ");
		JPasswordField nhaplaiTextField = new JPasswordField();
		floor3.setMaximumSize(new Dimension(2000, 37));
		floor3.add(nhaplaiLabel);
		floor3.add(nhaplaiTextField);
		floor.add(floor3);
		floor.add(Box.createVerticalGlue());
				
		JPanel floor4 = new JPanel();
		floor4.setLayout(new BoxLayout(floor4, BoxLayout.X_AXIS));
		floor4.add(Box.createHorizontalGlue());
		ok = new JButton("OK");
		ok.setPreferredSize(new Dimension(100, 100));
		ok.setForeground(new Color(227, 140, 0));
		ok.setOpaque(true);		
		cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(100, 100));
		cancel.setForeground(new Color(227, 140, 0));
		cancel.setOpaque(true);
		floor4.add(cancel);
		floor4.add(Box.createRigidArea(new Dimension(20, 0)));
		floor4.add(ok);
		floor.add(floor4);
		
		add(floor);
		setTitle("Change Password");
		setSize(500, 170);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);		
	}
	
	public String getMatKhauCu() {
		return String.valueOf(matkhaucuTextField.getPassword());
	}
	
	public String getMatKhauMoi() {
		return String.valueOf(matkhaumoiTextField.getPassword());
	}
	
	public String getMatKhauMoiNhapLai() {
		return String.valueOf(nhaplaiTextField.getPassword());
	}
	
	public JButton getOK() {
		return ok;
	}
	
	public JButton getCancel() {
		return cancel;
	}
	
	public static void main(String[] args) {
		DoiMatKhau a = new DoiMatKhau();
		
		a.getOK().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					System.out.println(a.getMatKhauMoi());
				}
			}
		});
		
		a.getCancel().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					a.dispose();
				}
			}
		});
	}
				
}
