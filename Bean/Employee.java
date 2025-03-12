package com.whu_zzf.Bean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Employee {
//员工信息"ID", "姓名", "性别", "年龄", "电话" ,"职位", "入职日期", "薪水", "部门"
    private int id;
    private String name;
    private String sex;
    private int age;
    private String phone;
    private String position;
    private String entryDate;
    private double salary;
    private String department;

    public static ArrayList<Employee> employeeList = new ArrayList<>();
    static{
        // 初始几位员工数据
        employeeList.add(new Employee(1, "张三", "男", 21, "18667656520", "java工程师", new Date().toLocaleString(), 30000, "部门1"));
        employeeList.add(new Employee(2, "李四", "女", 22, "18667656521", "java工程师", new Date().toLocaleString(), 30000, "部门2"));
        employeeList.add(new Employee(3, "王五", "男", 23, "18667656522", "java工程师", new Date().toLocaleString(), 30000, "部门3"));
        employeeList.add(new Employee(4, "赵六", "男", 24, "18667656523", "java工程师", new Date().toLocaleString(), 30000, "部门4"));
    }
    //展示员工列表
    public static void showEmployeeList()
    {
        //通过索引遍历for循环
        for(int i=0;i<employeeList.size();i++){
            System.out.println(employeeList.get(i).toString());
        }
    }

}
