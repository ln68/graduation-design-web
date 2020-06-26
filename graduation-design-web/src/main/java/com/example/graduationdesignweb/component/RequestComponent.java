package com.example.graduationdesignweb.component;

import com.example.graduationdesignweb.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
@Slf4j
public class RequestComponent {
    public int getUid() {
        return (int) RequestContextHolder.currentRequestAttributes()//获取当前线程
                .getAttribute(MyToken.UID, RequestAttributes.SCOPE_REQUEST);//在请求范围内拿键值对，对象转换
    }

    public User.Role getRole() {
        return (User.Role) RequestContextHolder.currentRequestAttributes()
                .getAttribute(MyToken.ROLE, RequestAttributes.SCOPE_REQUEST);
    }

}