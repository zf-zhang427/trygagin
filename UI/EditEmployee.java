package com.whu_zzf.UI;

import com.whu_zzf.Bean.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditEmployee extends JDialog {
    private Employee employee;
    private JTextField idField, nameField, sexField, ageField, phoneField, positionField, salaryField;
    private JComboBox<String> departmentComboBox;
    private JButton btnSave, btnCancel;

    public EditEmployee(JFrame parent, Employee employee) {
        super(parent, "编辑员工信息", true);
        this.employee = employee;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setSize(400, 300);

        // 设置布局
        JPanel contentPanel = new JPanel(new GridLayout(9, 2));

        // 添加标签和输入框
        contentPanel.add(new JLabel("ID:"));
        idField = new JTextField(String.valueOf(employee.getId()), 10);
        idField.setEditable(false); // ID 不可编辑
        contentPanel.add(idField);

        contentPanel.add(new JLabel("姓名:"));
        nameField = new JTextField(employee.getName(), 10);
        contentPanel.add(nameField);

        contentPanel.add(new JLabel("性别:"));
        sexField = new JTextField(employee.getSex(), 10);
        contentPanel.add(sexField);

        contentPanel.add(new JLabel("年龄:"));
        ageField = new JTextField(String.valueOf(employee.getAge()), 10);
        contentPanel.add(ageField);

        contentPanel.add(new JLabel("电话:"));
        phoneField = new JTextField(employee.getPhone(), 10);
        contentPanel.add(phoneField);

        contentPanel.add(new JLabel("职位:"));
        positionField = new JTextField(employee.getPosition(), 10);
        contentPanel.add(positionField);

        contentPanel.add(new JLabel("薪水:"));
        salaryField = new JTextField(String.valueOf(employee.getSalary()), 10);
        contentPanel.add(salaryField);

        contentPanel.add(new JLabel("部门:"));
        departmentComboBox = new JComboBox<>(new String[]{"技术部", "市场部", "人力资源部"});
        departmentComboBox.setSelectedItem(employee.getDepartment());
        contentPanel.add(departmentComboBox);

        // 添加按钮
        JPanel buttonPanel = new JPanel();
        btnSave = new JButton("保存");
        btnCancel = new JButton("取消");
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 设置按钮监听器
        btnSave.addActionListener(new SaveButtonListener());
        btnCancel.addActionListener(new CancelButtonListener());

        // 设置居中显示
        setLocationRelativeTo(getOwner()); // 居中显示
        setVisible(true);
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // 获取表单输入
                String name = nameField.getText();
                String sex = sexField.getText();
                String ageStr = ageField.getText();
                String phone = phoneField.getText();
                String position = positionField.getText();
                String salaryStr = salaryField.getText();
                String department = (String) departmentComboBox.getSelectedItem();

                // 验证年龄字段是否为空或仅包含空格
                if (ageStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(EditEmployee.this, "年龄不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // 验证薪水字段是否为空或仅包含空格
                if (salaryStr.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(EditEmployee.this, "薪水不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // 尝试将年龄字段转换为整数
                int age = Integer.parseInt(ageStr.trim());
                // 尝试将薪水字段转换为浮点数
                double salary = Double.parseDouble(salaryStr.trim());
                // 更新员工对象的属性
                employee.setName(name);
                employee.setSex(sex);
                employee.setAge(age);
                employee.setPhone(phone);
                employee.setPosition(position);
                employee.setSalary(salary); // 调整为浮点数，确保 Employee 类支持 float 或 double 类型
                employee.setDepartment(department);

                // 显示成功的消息对话框
                JOptionPane.showMessageDialog(EditEmployee.this, "员工信息已更新！");

                // 关闭对话框
                dispose();
            } catch (NumberFormatException ex) {
                // 处理非数字输入的情况
                JOptionPane.showMessageDialog(EditEmployee.this, "请输入有效的数字值！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                // 处理其他异常
                JOptionPane.showMessageDialog(EditEmployee.this, "更新员工信息时发生错误：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}