package com.example.graduationdesignweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class User {
    public enum Role {
        GRADUATE, TEACHER
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(unique = true)//唯一约束，防止冲突异常
    private Integer number;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)//序列化时忽略，java对象转user时忽略掉，反序列化时正常
    private String password;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Role role;//角色，用枚举
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
}
//用户登录用的老师那边的id，学生那边的id
//User-1：老师id-1
//User-2：学生id-1