package com.example.graduationdesignweb.controller;

import com.example.graduationdesignweb.component.EncryptComponent;
import com.example.graduationdesignweb.component.MyToken;
import com.example.graduationdesignweb.entity.User;
import com.example.graduationdesignweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/")
public class LoginController {
    @Value("${my.teacher}")
    private String roleTeacher;
    @Value("${my.student}")
    private String roleStudent;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EncryptComponent encrypt;

    @PostMapping("login")
    public Map login(@RequestBody User login, HttpServletResponse response) {
        User user = Optional.ofNullable(userService.getUser(login.getNumber()))//基于number查人存不存在，不存在执行orElseThrow，
                .filter(u -> encoder.matches(login.getPassword(), u.getPassword()))//若存在，注入进来，将数据库密码与登录填写的密码通过encoder组件进行比较，不对再执行orElseThrow
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.UNAUTHORIZED, "用户名密码错误"));

        MyToken token = new MyToken(user.getId(), user.getRole());//创建Token，可以知道身份是什么
        String auth = encrypt.encryptToken(token);//加密
        response.setHeader(MyToken.AUTHORIZATION, auth);//自定义键值对，真正身份，客户端无法解密
        String roleCode = user.getRole() == User.Role.TEACHER ? roleTeacher : roleStudent;
        return Map.of("role", roleCode);//返回给前端
    }
}