package com.whu_zzf.UI;

import com.whu_zzf.Bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Register extends JDialog {

    private static final String REGISTRATION_CODE = "3818099";

    private JFrame parent;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;
    private JTextField registrationCodeField;

    public Register(JFrame parent) {
        super(parent, "注册界面", true);
        this.parent = parent;
        initializeComponents();
        pack(); // 自动调整大小以适应组件
        setLocationRelativeTo(parent); // 居中显示
        setVisible(true);
    }

    private void initializeComponents() {
        setLayout(new GridLayout(4, 2, 5, 5));

        // 用户名输入框
        add(new JLabel("用户名:"));
        registerUsernameField = new JTextField(15); // 限制宽度
        add(registerUsernameField);

        // 密码输入框
        add(new JLabel("密码:"));
        registerPasswordField = new JPasswordField(15); // 限制宽度
        add(registerPasswordField);

        // 注册码输入框
        add(new JLabel("注册码:"));
        registrationCodeField = new JTextField(15); // 限制宽度
        add(registrationCodeField);

        // 注册按钮
        JButton registerButton = new JButton("注册");
        Font font = new Font("楷体", Font.PLAIN, 14); // 重新定义字体
        registerButton.setFont(font.deriveFont(Font.BOLD, 16f)); // 增大字体
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = registerUsernameField.getText();
                char[] passwordChars = registerPasswordField.getPassword();
                String password = new String(passwordChars);
                String registrationCode = registrationCodeField.getText();

                if (registrationCode.equals(REGISTRATION_CODE)) {
                    JOptionPane.showMessageDialog(Register.this, "注册成功！用户名: " + username + ", 密码: " + password);
                    User.userList.add(new User(username, password, "用户"));
                    dispose();
                    User.showUserList();
                } else {
                    JOptionPane.showMessageDialog(Register.this, "注册失败，注册码不正确！");
                }
            }
        });

        add(new JLabel("")); // 左侧空标签
        add(registerButton);
    }
}