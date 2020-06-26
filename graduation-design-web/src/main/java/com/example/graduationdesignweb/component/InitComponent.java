package com.example.graduationdesignweb.component;

import com.example.graduationdesignweb.entity.Teacher;
import com.example.graduationdesignweb.entity.User;
import com.example.graduationdesignweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InitComponent implements InitializingBean {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserService  userService;

    @Override
    public void afterPropertiesSet() throws Exception {
        final int num = 1001;
        User user = userService.getUser (num);//先判断存不存在
        if (user == null) {
            User u = new User();
            u.setName("BO");
            u.setNumber(num);
            u.setRole(User.Role.TEACHER);
            u.setPassword(encoder.encode(String.valueOf(num)));

            Teacher t = new Teacher();
            t.setUser(u);//先要save （User） 然后把user塞到teacher里，才能保存teacher
            userService.addTeacher(t);
        }
    }
}