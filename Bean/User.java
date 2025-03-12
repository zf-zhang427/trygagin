package com.whu_zzf.Bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;// 用户名
    private String password;// 密码
    private String name;// 姓名
    public static ArrayList<User> userList = new ArrayList<>();// 用户数据库
    static{
        // 初始用户数据
        userList.add(new User("admin","123456","管理员"));
    }
    //显示所有用户数据：通过索引遍历for循环
    public static void showUserList(){
        for(int i=0;i<userList.size();i++){
            System.out.println(userList.get(i).toString());
        }
    }


}
