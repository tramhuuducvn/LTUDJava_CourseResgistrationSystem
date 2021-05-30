package FunctionPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public class JTableUtil{
    public static void setCellsAlignment(JTable table, int alignment, int column)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();
        table.getColumnModel().getColumn(column).setCellRenderer(rightRenderer);
    }
}