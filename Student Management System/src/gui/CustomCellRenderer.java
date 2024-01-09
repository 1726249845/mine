package gui;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
// 自定义表格单元格渲染器，继承自 DefaultTableCellRenderer
public class CustomCellRenderer extends DefaultTableCellRenderer {
    // 重写 getTableCellRendererComponent 方法以自定义单元格的渲染方式
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        // 调用父类的方法设置渲染器的默认状态
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // 设置表格单元格中的文本字体和大小
        setFont(new Font("宋体", Font.PLAIN, 16)); // "宋体"是字体，Font.PLAIN 是字体样式，16 是字体大小
        return this;
    }
}
