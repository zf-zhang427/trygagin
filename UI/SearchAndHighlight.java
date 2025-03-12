package com.whu_zzf.UI;


import com.whu_zzf.Bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SearchAndHighlight {
    private JTable table;
    private JTextField searchTextField;
    private DefaultTableModel model;

    public SearchAndHighlight(JTable table, JTextField searchTextField, DefaultTableModel model) {
        this.table = table;
        this.searchTextField = searchTextField;
        this.model = model;
    }

    public void setSearchListener(ActionListener listener) {
        searchTextField.addActionListener(listener);
    }

    public  void searchEmployees(String searchText) {
        if (searchText.isEmpty()) {
            JOptionPane.showMessageDialog(table, "请输入搜索条件！");
            return;
        }
        // 清空表格数据
        model.setRowCount(0);
        // 搜索员工记录
        for (Employee employee : Employee.employeeList) {
            if (matchesSearchCriteria(employee, searchText)) {
                model.addRow(new Object[]{
                        employee.getId(),
                        employee.getName(),
                        employee.getSex(),
                        employee.getAge(),
                        employee.getPhone(),
                        employee.getPosition(),
                        employee.getEntryDate(),
                        employee.getSalary(),
                        employee.getDepartment()
                });
            }
        }
    }

    private boolean matchesSearchCriteria(Employee employee, String searchText) {
        return employee.getName().contains(searchText) ||
                employee.getPhone().contains(searchText) ||
                employee.getPosition().contains(searchText) ||
                employee.getDepartment().contains(searchText);
    }

    public  void highlightMatchingCells() {
        // 如果搜索框中有内容，则高亮显示匹配的单元格
        String searchText = searchTextField.getText();
        if (!searchText.isEmpty()) {
            table.setDefaultRenderer(Object.class, new HighlightingRenderer());
        } else {
            // 如果搜索框为空，则恢复默认的渲染器
            table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());
        }
    }

    private class HighlightingRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value instanceof String && ((String) value).contains(searchTextField.getText())) {
                c.setBackground(Color.YELLOW); // 高亮颜色
            } else {
                c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            }
            return c;
        }
    }
}