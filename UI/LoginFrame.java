package com.whu_zzf.UI;

import com.whu_zzf.Bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private static final String REGISTRATION_CODE = "3818099";
    private JFrame jframe; //登录窗口
    private JTextField usernameField; // 用户名输入框
    private JPasswordField passwordField; // 密码输入框
    private JButton loginButton; // 登录按钮
    private JButton registerButton; // 注册按钮

    public LoginFrame(){
        super("登录界面");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.setLocationRelativeTo(null); // 居中显示
        jframe = this;
        createAndShowGUI();
    }

    // 创建登录界面
    private  void createAndShowGUI() {
        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));

        // 设置字体和颜色
        Font customFont = new Font("楷体", Font.BOLD, 18);
        Color primaryColor = new Color(66, 135, 245);
        Color secondaryColor = new Color(204, 204, 204); // 更浅的颜色用于注册按钮

        // 标题
        JLabel titleLabel = new JLabel("人事管理系统");
        titleLabel.setBounds(50, 30, 300, 30);
        titleLabel.setFont(new Font("楷体", Font.BOLD, 24));
        panel.add(titleLabel);

        // 用户名标签
        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(50, 100, 150, 30);
        usernameLabel.setFont(customFont);
        panel.add(usernameLabel);

        // 用户名输入框
        usernameField = new JTextField();
        usernameField.setBounds(160, 100, 190, 30);
        usernameField.setFont(customFont);
        panel.add(usernameField);

        // 密码标签
        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setBounds(50, 150, 150, 30);
        passwordLabel.setFont(customFont);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 150, 190, 30);
        passwordField.setFont(customFont);
        panel.add(passwordField);

        // 登录按钮
        loginButton = new JButton("登   录");
        loginButton.setBounds(50, 200, 150, 30);
        loginButton.setFont(customFont);
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.WHITE);
        panel.add(loginButton);
        //为登录按钮绑定监听器
        loginButton.addActionListener(this);

        // 注册按钮
        registerButton = new JButton("注   册");
        registerButton.setBounds(200, 200, 150, 30);
        registerButton.setFont(customFont);
        registerButton.setBackground(secondaryColor);
        registerButton.setForeground(Color.BLACK);
        panel.add(registerButton);
        registerButton.addActionListener(this);

        // 添加面板到窗口
        this.add(panel);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button == loginButton) {
            // 独立功能独立方法
            login();
        } else if (button == registerButton) {
            // 注册按钮的逻辑
            // 调用 RegisterDialog 类
            new Register(this);
        }
    }

    // 登录功能
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // 在这里可以进行登录验证
        for (int i = 0; i < User.userList.size(); i++) {
            User user = User.userList.get(i);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // 登录成功，可以打开主界面或其他操作
                JOptionPane.showMessageDialog(this, "登录成功！");
                // 关闭登录界面
                jframe.setVisible(false);
                // 打开主界面
                new EmployeeManager(user.getName());
                return;
            }
            else if (i == User.userList.size() - 1)
                JOptionPane.showMessageDialog(this, "用户名或密码错误，请重新输入！");
        }
    }
}
