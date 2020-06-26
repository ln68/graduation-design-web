package com.example.graduationdesignweb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties({"courses", "graduates"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer quantity;
    private Integer ranges;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})//级联操作，先持久化user再持久化teacher一个人对应一个用户，单向关系，在用户那边找不到，由老师映射成User一个用户
    @MapsId//共用主键
    private User user;
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;
    @OneToMany(mappedBy = "teacher" )
    private List<Graduate> graduates;
    @Column(columnDefinition = "timestamp default current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "timestamp default current_timestamp"+" on update current_timestamp",
            insertable = false,
            updatable = false)
    private LocalDateTime updateTime;

    public Teacher(Integer id) {
        this.id = id;
    }
}