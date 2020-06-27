package com.example.graduationdesignweb.controller.vo;

import com.example.graduationdesignweb.entity.Course;
import com.example.graduationdesignweb.entity.Graduate;
import lombok.Data;

import java.util.List;

@Data
public class CourseVO {
    private Course course;
    private List<Graduate> graduates;
}//不是实体类