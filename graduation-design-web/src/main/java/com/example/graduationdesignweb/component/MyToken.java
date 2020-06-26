package com.example.graduationdesignweb.component;

import com.example.graduationdesignweb.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyToken {
    public static final String AUTHORIZATION = "authorization";//声明成常量，减少硬编码
    public static final String UID = "uid";
    public static final String ROLE = "role";
    private Integer uid;//核心信息传过去
    private User.Role role;
}