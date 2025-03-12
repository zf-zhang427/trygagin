package com.whu_zzf.UI;


import com.whu_zzf.Bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class EmployeeManager extends JFrame implements ActionListener{
    private JFrame frame; // 窗口
    private JTable table;  // 表格
    private DefaultTableModel model; // 表格模型: 封装表格数据的对象
    private JTextField nameTextFieldSearch; // 搜索输入框

    public EmployeeManager(String username) {
        super(username+"已登录");
        frame = this;
        initialize();
        this.setVisible(true); // 显示窗口
    }

    private void initialize() {
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        // 输入框和搜索按钮
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nameTextFieldSearch = new JTextField(20);
        JButton btnSearch = new JButton("搜索");
        JButton btnAdd = new JButton("添加");
        topPanel.add(nameTextFieldSearch);
        topPanel.add(btnSearch);
        topPanel.add(btnAdd);

        // 创建表格模型
        model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "姓名", "性别", "年龄", "电话" ,"职位", "入职日期", "薪水", "部门"}
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 设置所有单元格为不可编辑
            }
        };

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setRowHeight(30);

        // 添加数据到表格
        refreshEmployeeTable();

        // 右键菜单
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("编辑");
        JMenuItem deleteItem = new JMenuItem("删除");
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) { // 明确检查是否为鼠标右键
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(table, e.getX(), e.getY());
                    }
                }
            }
        });
        // 绑定事件到菜单项
        editItem.addActionListener(this);
        deleteItem.addActionListener(this);

        // 搜索按钮监听器
        btnSearch.addActionListener(this);
        // 添加按钮监听器
        btnAdd.addActionListener(this);
        frame.getContentPane().add(topPanel, BorderLayout.NORTH);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();//存放所有类型的对象
        //确认为右键菜单按钮
        if (source instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) source;
            //编辑监听器
            if ("编辑".equals(item.getText())) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (Integer) model.getValueAt(selectedRow, 0); // 假设ID在第一列
                    Employee employee = getEmployeeById(id);
                    if (employee != null) {
                        new EditEmployee(this, employee);
                        refreshEmployeeTable(); // 刷新表格以显示最新信息
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "请选择一条记录进行编辑！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
               Employee.showEmployeeList();
            }
            //删除监听器
            else if ("删除".equals(item.getText())) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = (Integer) model.getValueAt(selectedRow, 0); // 假设ID在第一列
                    JOptionPane.showMessageDialog(frame, "删除 ID: " + id);
                    // 删除员工集合的数据并刷新表格
                    deleteEmployeeById(id);
                    refreshEmployeeTable();
                }
            }
        }
        //确认为点击按钮
        else if (source instanceof JButton) {
            JButton button = (JButton) source;
            //搜索监听器
            if ("搜索".equals(button.getText())) {
                // 创建 SearchAndHighlight 实例
                SearchAndHighlight searchAndHighlight = new SearchAndHighlight(table, nameTextFieldSearch, model);
                searchAndHighlight.searchEmployees(nameTextFieldSearch.getText());
                searchAndHighlight.highlightMatchingCells();
            }
            //添加监听器
            else if ("添加".equals(button.getText())) {
                new AddEmployee(frame, model);
                refreshEmployeeTable();
            }
        }
    }
    //通过ID删除员工集合中的对应员工数据
    private static void deleteEmployeeById(int id) {
        for (int i = 0; i < Employee.employeeList.size(); i++) {
            Employee employee = Employee.employeeList.get(i);
            if (employee.getId() == id) {
                Employee.employeeList.remove(i);
                JOptionPane.showMessageDialog(null, "删除员工成功");
                return;
            }
        }
    }
    //通过ID获取员工信息
    private static Employee getEmployeeById(int id) {
        for (Employee employee : Employee.employeeList) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    //更新员工列表
    private void refreshEmployeeTable() {
        // 清空表格数据
        model.setRowCount(0);
        // 在窗口更新员工列表
        //用索引遍历for循环实现

        for (int i = 0; i < Employee.employeeList.size(); i++)
        {
            Employee employee = Employee.employeeList.get(i);
            model.addRow(new Object[]{employee.getId(), employee.getName(), employee.getSex(), employee.getAge(), employee.getPhone(), employee.getPosition(), employee.getEntryDate(), employee.getSalary(), employee.getDepartment()});
        }
    }
}
