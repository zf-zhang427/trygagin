package com.whu_zzf.UI;

import com.whu_zzf.Bean.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddEmployeeDialog 类用于创建一个对话框，允许用户输入新员工的信息并将其添加到表格中。
 */
public class AddEmployee extends JDialog {

    // 文本框用于输入员工信息
    private JTextField idField;
    private JTextField nameField;
    private JTextField genderField;
    private JTextField ageField;
    private JTextField phoneField;
    private JTextField positionField;
    private JTextField entryDateField;
    private JTextField salaryField;
    private JTextField departmentField;

    // 默认表格模型，用于向表格中添加数据
    private DefaultTableModel model;

    /**
     * 构造函数初始化对话框，并设置其属性。
     *
     * @param parent  父窗口
     * @param model  表格模型
     */
    public AddEmployee(JFrame parent, DefaultTableModel model) {
        super(parent, "添加新员工", true); // 创建一个模式对话框，标题为“添加新员工”

        this.model = model; // 设置表格模型引用
        setLayout(new BorderLayout()); // 使用BorderLayout作为主要布局管理器
        setSize(300, 400); // 设置对话框的大小
        setLocationRelativeTo(parent); // 让对话框居中显示于父窗口
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 关闭对话框时将其隐藏

        // 创建一个面板用于放置输入框
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(9, 2)); // 使用GridLayout布局，每行包含两个元素（标签+输入框）

        // 初始化各个输入框
        idField = new JTextField();
        nameField = new JTextField();
        genderField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();
        positionField = new JTextField();
        entryDateField = new JTextField();
        salaryField = new JTextField();
        departmentField = new JTextField();

        // 添加组件到面板上
        inputPanel.add(new JLabel("ID:")); // 添加标签
        inputPanel.add(idField);           // 添加输入框

        inputPanel.add(new JLabel("姓名:"));
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("性别:"));
        inputPanel.add(genderField);

        inputPanel.add(new JLabel("年龄:"));
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("电话:"));
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("职位:"));
        inputPanel.add(positionField);

        inputPanel.add(new JLabel("入职日期:"));
        inputPanel.add(entryDateField);

        inputPanel.add(new JLabel("薪水:"));
        inputPanel.add(salaryField);

        inputPanel.add(new JLabel("部门:"));
        inputPanel.add(departmentField);

        // 将输入面板添加到对话框的中心位置
        add(inputPanel, BorderLayout.CENTER);

        // 创建一个“添加”按钮
        JButton addButton = new JButton("添加");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                //将输入框中的数据添加到Employee集合中
                Employee.employeeList.add(new Employee(Integer.parseInt(idField.getText()), nameField.getText(), genderField.getText(), Integer.parseInt(ageField.getText()), phoneField.getText(), positionField.getText(), entryDateField.getText(), Double.parseDouble(salaryField.getText()), departmentField.getText()));
                //显示添加成功
                JOptionPane.showMessageDialog(null, "添加成功！");
                //关闭窗口
                dispose();
                Employee.showEmployeeList();
            }
        });

        // 创建一个面板用于放置按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton); // 将按钮添加到面板上

        // 将按钮面板添加到对话框的底部
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true); // 显示对话框
    }
}