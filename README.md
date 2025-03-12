# JAVA_EMPLOYEE_SYSTEM
# 登录模块设计

- 模块设计：

  - 按照功能模块进行了划分：UI（用户界面）、Bean（实体类）和 APP（应用程序启动）三个层次。

- 功能：

  - UI层负责用户交互，包括登录界面和注册界面；

  - Bean层负责数据模型，包括用户和员工信息；

  - APP层负责启动应用程序；

    

### 1. User 类

- **属性**：存储用户信息。

  ```java
  private String username;// 用户名
  private String password;// 密码
  private String name;// 姓名
  ```

  

- **静态成员**：一个`ArrayList<User>`类型的`userList`，用于存储用户列表。

  ```java
  public static ArrayList<User> userList = new ArrayList<>();// 用户数据库
  static{
      // 初始用户数据
      userList.add(new User("admin","123456","管理员"));}
  ```

  

- **方法**：提供了一个静态方法`showUserList()`用于展示所有用户的列表。

  ```java
  public static void showUserList(){
      for(int i=0;i<userList.size();i++){
          System.out.println(userList.get(i).toString());}}
  ```



### 2. **Employee 类**

- **属性**：存储员工信息。

  ```java
  private int id;
  private String name;
  private String sex;
  ...
  ```

  

- **静态成员**：一个`ArrayList<Employee>`类型的`employeeList`，用于存储员工列表。

- ```java
  public static ArrayList<Employee> employeeList = new ArrayList<>();
  static{
      employeeList.add(new Employee(1, "张三", "男", 21, "18667656520", "java工程师", new Date().toLocaleString(), 30000, "部门1"));}
  ```

  

- **方法**：提供了一个静态方法`showEmployeeList()`用于展示所有员工的信息。

  ```java
  for(int i=0;i<employeeList.size();i++){
      System.out.println(employeeList.get(i).toString());}
  ```



###  3. **LoginFrame 类**

- **属性：**用户名输入框、密码输入框、登录按钮、注册按钮以绑定事件。
- **继承自**：`JFrame`
- **方法**：实现了登录功能，并提供了注册按钮的功能，注册按钮会触发注册对话框的显示。

####   3-1. **界面设计**

- **登录界面**：设计包含用户名输入框、密码输入框、登录和注册按钮的界面，绑定框存放数据与点击事件

  ```java
  ...
  //用户名标签
  JLabel usernameLabel = new JLabel("用户名：");
  usernameLabel.setBounds(50, 100, 150, 30);
  usernameLabel.setFont(customFont);
  panel.add(usernameLabel);
  // 用户名输入框
  usernameField = new JTextField();
  usernameField.setBounds(160, 100, 190, 30);
  usernameField.setFont(customFont);
  panel.add(usernameField);
  ...
  ```



####   3-2. **事件处理**

- **登录按钮**：当用户点击登录按钮时，触发相应的事件处理逻辑。

  ```java
  loginButton.addActionListener(this);
  ```

  

- **注册按钮**：当用户点击注册按钮时，跳转到注册界面。

  ```java
  registerButton.addActionListener(this);
  ```



####   3-3. **登录验证**

- **读取输入**：获取用户输入的用户名和密码。

- **验证用户信息**：从存储用户信息的地方（本项目中是`User.userList`）查找是否有匹配的用户名和密码。

- **反馈结果**：根据验证结果向用户提供反馈（如登录成功或失败的提示）

  - **登录成功**：关闭登录界面，并根据用户角色（如管理员或普通用户）打开相应的主界面。
  - **登录失败**：提示用户重新输入用户名和密码。

  ```java
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
  ```



### 4. **Register 类**

- **属性**：创建模态对话框，实现用户名输入框、密码输入框、注册码输入框，注册按钮与对应事件
- **继承自**：`JDialog`   
- **方法**：实现注册功能，验证注册码是否正确，并将新用户信息添加到用户列表中。

####   4-1. **界面设计**

- **注册界面**：包含用户名输入框、密码输入框、注册码输入框以及注册按钮的界面。

- **布局**：使用合适的布局管理器来安排界面上的组件位置。

  ```java
  public Register(JFrame parent) {
     super(parent, "注册界面", true);
     this.parent = parent;
     initializeComponents();
     pack(); // 自动调整大小以适应组件
     setLocationRelativeTo(parent); // 居中显示
     setVisible(true);}
  ```



####   4-2. **事件处理**

- **注册按钮**：当用户点击注册按钮时，触发相应的事件处理逻辑。



####   4-3. **注册验证**

- **读取输入**：获取用户输入的用户名、密码和注册码。

- **验证用户信息**：检查用户名是否已被使用、密码是否符合要求、注册码是否正确。

- **反馈结果**：根据验证结果向用户提供反馈（如注册成功或失败的提示）。

  - **成功注册**：将新用户的用户名和密码存储到用户列表中（本项目中是`User.userList`）。
  - **界面切换**：如果注册成功，关闭注册界面，并返回登录界面。

  ```java
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
     JOptionPane.showMessageDialog(Register.this, "注册失败，注册码不正确！");}
  ```

  

### 5. **Act 类**

- **目的**：启动应用程序。



# 用户操作模块设计

- 模块设计：
  - 用于管理员工信息。包括了添加、编辑、删除和搜索员工的功能，并通过一个表格来显示员工列表。

### 1. **EmployeeManager 类**

- **功能**：主窗口类，负责展示所有员工信息，并提供界面执行CRUD（创建、读取、更新、删除）操作。

- 布局：

  - 使用`BorderLayout`布局，顶部放置搜索框和按钮，中间是员工信息表格。
  - 表格使用`DefaultTableModel`作为数据模型，并禁用了单元格编辑。

  ```java
  this.setBounds(100, 100, 800, 600);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.getContentPane().setLayout(new BorderLayout());
  // 创建表格模型
  model = new DefaultTableModel(
     new Object[][]{},
     new String[]{"ID", "姓名", "性别", "年龄", "电话" ,"职位", "入职日期", "薪水", "部门"}
  ){ @Override
     public boolean isCellEditable(int row, int column) {
        return false; // 设置所有单元格为不可编辑 }};
  ```

  

- 事件处理：

  - `ActionListener`接口处理右键菜单项选择事件。

    - 右键点击编辑事件触发`EditEmployee`类。

      ```java
      if ("编辑".equals(item.getText())) {
         int selectedRow = table.getSelectedRow();
         if (selectedRow >= 0) {
            int id = (Integer) model.getValueAt(selectedRow, 0); // 假设ID在第一列
            Employee employee = getEmployeeById(id);
            if (employee != null) {
               new EditEmployee(this, employee);
               refreshEmployeeTable(); // 刷新表格以显示最新信息 
            }}
      ```

      

    - 右键点击删除事件执行deleteEmployeeById方法。

      ```java
      else if ("删除".equals(item.getText())) {
         int selectedRow = table.getSelectedRow();
         if (selectedRow >= 0) {
            int id = (Integer) model.getValueAt(selectedRow, 0); // 假设ID在第一列
            JOptionPane.showMessageDialog(frame, "删除 ID: " + id);
            //删除员工集合的数据并刷新表格
            deleteEmployeeById(id);
            refreshEmployeeTable(); }}
      ```

      ```java
      //通过ID删除员工集合中的对应员工数据
      private static void deleteEmployeeById(int id) {
         for (int i = 0; i < Employee.employeeList.size(); i++) {
            Employee employee = Employee.employeeList.get(i);
            if (employee.getId() == id) {
               Employee.employeeList.remove(i);
               JOptionPane.showMessageDialog(null, "删除员工成功");
               return;   }}}
      ```

      

  - `ActionListener`接口处理按钮点击事件。

    - 搜索按钮触发`SearchAndHighlight`类的搜索逻辑。

    ```java
    if ("搜索".equals(button.getText())) {
       // 创建 SearchAndHighlight 实例
       SearchAndHighlight searchAndHighlight = new SearchAndHighlight(table, nameTextFieldSearch, model);
       searchAndHighlight.searchEmployees(nameTextFieldSearch.getText());
       searchAndHighlight.highlightMatchingCells();}
    ```

    

    - 添加按钮打开`AddEmployee`对话框。

    ```java
    else if ("添加".equals(button.getText())) {
       new AddEmployee(frame, model);
       refreshEmployeeTable();}
    ```

    

### 2. **AddEmployee 类**

#### 2-1. **初始化**

- **构造器**：`AddEmployee` 的构造函数接收一个 `JFrame`（父窗口）和一个 `DefaultTableModel`（表格模型）作为参数。

- **设置对话框属性**：

  - 设置对话框标题为“添加新员工”。
  - 设置对话框大小和位置。
  - 设置关闭操作为 `DISPOSE_ON_CLOSE`，即关闭时释放资源。

- **布局管理**：

  - 使用 `BorderLayout` 作为主布局。
  - 创建一个 `GridLayout` 管理的面板来排列输入字段。
  - 在底部放置按钮面板，包含“添加”和“取消”按钮。

  ```java
  super(parent, "添加新员工", true); // 创建一个模式对话框，标题为“添加新员工”
  setLayout(new BorderLayout()); // 使用BorderLayout作为主要布局管理器
  setSize(300, 400); // 设置对话框的大小
  setLocationRelativeTo(parent); // 让对话框居中显示于父窗口
  setDefaultCloseOperation(DISPOSE_ON_CLOSE); // 关闭对话框时将其隐藏
  ```

  

#### 2-2. **输入字段创建与布局**

- **输入字段**：为员工属性（ID, 姓名, 性别, 年龄, 电话, 职位, 入职日期, 薪水, 部门）创建对应的 `JTextField`。
- **标签与输入字段**：在 `GridLayout` 管理的面板中，每行包含一个标签和一个输入字段。

```java
inputPanel.add(new JLabel("ID:")); // 添加标签
inputPanel.add(idField);           // 添加输入框
```



#### 2-3. **按钮及其事件处理**

- **“添加”按钮**：

  - 当用户点击“添加”按钮时，收集所有输入字段的数据。
  - 创建一个新的 `Employee` 对象，并将其添加到 `Employee.employeeList` 中。
  - 显示成功消息对话框。
  - 关闭当前对话框。
  - 刷新主窗口中的表格以显示最新的员工列表。

  ```java
  Employee.employeeList.add(new Employee(Integer.parseInt(idField.getText()), nameField.getText(), genderField.getText(), Integer.parseInt(ageField.getText()), phoneField.getText(), positionField.getText(), entryDateField.getText(), Double.parseDouble(salaryField.getText()), departmentField.getText()));
  
  //显示添加成功
  JOptionPane.showMessageDialog(null, "添加成功！");
  //关闭窗口
  dispose();
  Employee.showEmployeeList();
  ```

  

### 3. **EditEmployee 类**

#### 3-1. **初始化**

- **构造器**：`EditEmployee` 的构造函数接收一个 `JFrame`（父窗口）和一个 `Employee` 对象作为参数。
- **设置对话框属性**：
  - 设置对话框标题为“编辑员工信息”。
  - 设置对话框大小和位置。
  - 设置关闭操作为 `DISPOSE_ON_CLOSE`，即关闭时释放资源。
- **布局管理**：
  - 使用 `BorderLayout` 作为主布局。
  - 创建一个 `GridLayout` 管理的面板来排列输入字段。
  - 在底部放置按钮面板，包含“保存”和“取消”按钮。

#### 3-2. **输入字段创建与布局**

- **输入字段**：为每个员工属性（ID, 姓名, 性别, 年龄, 电话, 职位, 薪水, 部门）创建对应的 `JTextField` 和 `JComboBox`。

- **标签与输入字段**：在 `GridLayout` 管理的面板中，每行包含一个标签和一个输入字段或下拉选择框。

- **不可编辑字段**：ID 字段是唯一的，并且通常由系统生成，因此将其设置为不可编辑。

  ```java
  idField.setEditable(false); // ID 不可编辑
  ```

  

#### 3-3. **按钮及其事件处理**

- **“保存”按钮**：

  - 当用户点击“保存”按钮时，收集所有输入字段的数据。
  - 对输入数据进行验证，确保数据的有效性（年龄和薪水是否为有效数字、必填字段是否为空）。

  ```java
  // 获取表单输入
  String name = nameField.getText();
  String sex = sexField.getText();
  ...
  if (ageStr.trim().isEmpty()) {// 验证年龄字段是否为空或仅包含空格
     JOptionPane.showMessageDialog(EditEmployee.this, "年龄不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
     return;}
  if (salaryStr.trim().isEmpty()) {// 验证薪水字段是否为空或仅包含空格
     JOptionPane.showMessageDialog(EditEmployee.this, "薪水不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
     return;}
  ```

  

  - 如果验证通过，更新 `Employee` 对象的属性。

  ```java
  // 尝试将年龄字段转换为整数
  int age = Integer.parseInt(ageStr.trim());
  // 尝试将薪水字段转换为浮点数
  double salary = Double.parseDouble(salaryStr.trim());
  // 更新员工对象的属性
  employee.setName(name);
  employee.setSex(sex);
  ...
  ```

  

  - 显示成功消息对话框。
  - 如果验证失败，显示错误消息对话框。

  ```java
  catch (NumberFormatException ex) {
     // 处理非数字输入的情况
     JOptionPane.showMessageDialog(EditEmployee.this, "请输入有效的数字值！", "错误", JOptionPane.ERROR_MESSAGE);} 
  catch (Exception ex) {
     // 处理其他异常
     JOptionPane.showMessageDialog(EditEmployee.this, "更新员工信息时发生错误：" + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);}
  ```

  

  - 关闭当前对话框。
  - 刷新主窗口中的表格以显示最新的员工列表。

- **“取消”按钮**：

  - 当用户点击“取消”按钮时，直接关闭对话框，不保存任何更改。

### 4. **SearchAndHighlight 类**

#### 4-1. **类的初始化**

- **构造器**：`SearchAndHighlight` 的构造函数接收一个 `JTable`（表格）、一个 `JTextField`（搜索文本框）和一个 `DefaultTableModel`（表格模型）作为参数。

#### 4-2. **设置监听器**

- **setSearchListener 方法**：提供一个方法来设置搜索文本框的动作监听器。这个监听器会在用户按下回车键或点击搜索按钮时触发搜索操作。

#### 4-3. **搜索功能**

- **searchEmployees 方法**：

  - 接收搜索文本作为参数。
  - 检查搜索文本是否为空。如果为空，提示用户输入搜索条件。
  - 清空表格中的现有数据。
  - 遍历 `Employee.employeeList`，使用 `matchesSearchCriteria` 方法检查每个员工是否符合搜索条件。
  - 如果符合条件，将该员工的信息添加到表格模型中。

  ```java
  public  void searchEmployees(String searchText) {
     if (searchText.isEmpty()) {
        JOptionPane.showMessageDialog(table, "请输入搜索条件！");
        return;}
     model.setRowCount(0);   // 清空表格数据
     for (Employee employee : Employee.employeeList) {
        if (matchesSearchCriteria(employee, searchText)) {
           model.addRow(new Object[]{
              employee.getId(),
              employee.getName(),
              ...
           });}}}
  ```

  

- **matchesSearchCriteria 方法**：

  - 根据搜索文本检查员工的姓名、电话、职位和部门是否包含搜索文本。
  - 返回布尔值表示是否匹配。

#### 4-4. **高亮显示功能**

- **highlightMatchingCells 方法**：

  - 检查搜索文本框是否有内容。
  - 如果有内容，则设置表格的默认渲染器为 `HighlightingRenderer`，以高亮显示匹配的单元格。
  - 如果没有内容，则恢复默认的渲染器。

  ```java
  String searchText = searchTextField.getText();
  if (!searchText.isEmpty()) {
     table.setDefaultRenderer(Object.class, new HighlightingRenderer());} 
  else {
     // 如果搜索框为空，则恢复默认的渲染器
     table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer());}
  ```

  

- **HighlightingRenderer 类**：

  - 继承自 `DefaultTableCellRenderer`。
  - 重写 `getTableCellRendererComponent` 方法，在渲染单元格时检查单元格内容是否包含搜索文本。
  - 如果包含搜索文本，则将背景颜色设置为黄色（或其他高亮颜色）。
  - 否则，使用默认的背景颜色。

  ```java
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
     Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
     if (value instanceof String && ((String) value).contains(searchTextField.getText())) {
        c.setBackground(Color.YELLOW); // 高亮颜色
     } else {
        c.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
     }
     return c;}
  ```



PS:第一次用md保留自己的项目经历，很多语法用的不熟练感觉很麻烦但是为了能有所记录还是开始写项目文档，以后慢慢锻炼这方面能力吧


