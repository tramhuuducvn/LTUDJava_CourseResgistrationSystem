package FunctionPackage;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class CheckBoxCellRenderer extends JComboBox implements TableCellRenderer {	 
    public CheckBoxCellRenderer(JComboBox comboBox) { 
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            this.addItem(comboBox.getItemAt(i));
        }
    } 
    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setSelectedItem(value);
 
        return this;
    } 
    @Override
    public void setSelectedItem(Object selecao) {
       this.setSelectedItem(selecao);
    }
    
    private static JComboBox generateBox() {
		String []item = {"A", "B", "C", "D"};
	     JComboBox cbb = new JComboBox(item);
	     DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
	     listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
	     cbb.setSelectedIndex(1);
	     cbb.setRenderer(listRenderer);	     
	     return cbb;
	}
    
    public static void main(String[] args) {
    	JFrame frame = new JFrame("JButtonTable Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        String cols[] = {"mssv", "hoten", "lophoc"};
        Object data[][] = {{"19120484", "Trầm Hữu Đức", "A"}, {"19120484", "Trầm Hữu Đức", "D"}};
        DefaultTableModel dm = new DefaultTableModel(data, cols);
        JTable table = new JTable(dm);
        JComboBox a = generateBox();
        table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(a));
        a.setSelectedIndex(2);
        System.out.println(a.getSelectedItem());
        a.repaint();
        JScrollPane scroll = new JScrollPane(table);        
        table.setPreferredScrollableViewportSize(table.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);//so buttons will fit and not be shown butto..
        scroll.setViewportView(table);
        
        frame.add(scroll);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 400);
//        frame.pack();
        frame.setVisible(true);
    }
}