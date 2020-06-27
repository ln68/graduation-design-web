package com.example.graduationdesignweb.entity;

import com.example.graduationdesignweb.repository.*;
import com.example.graduationdesignweb.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(value=false)
public class ManyTest {
    @Autowired
    private CourseService courseService;
   @Autowired
   private GraduateRepository graduateRepository;
   @Autowired
   private TeacherRepository teacherRepository;
   @Autowired
   private CourseRepository courseRepository;
   @Autowired
   private ElectiveRepository electiveRepository;
   @Autowired
   private DirectionRepository directionRepository;
   @Autowired
   private  DirectionGraduateRepository directionGraduateRepository;
    @Test
    public void test_Elective(){
        Course c = courseRepository.findById(1).orElse(null);
        Graduate g = graduateRepository.findById(1).orElse(null);
        Elective e = new Elective();
        e.setCourse(c);
        e.setGraduate(g);
        electiveRepository.save(e);

    }
    @Test
    public void test_init_course() {
        Course c = new Course();
        c.setName( "Java程序设计" );
        courseService.addCourse( c );
        Course c2 = new Course();
        c2.setName( "Web开发技术" );
        courseService.addCourse( c2 );
        Course c3 = new Course();
        c3.setName( "移动终端系统框架" );
        courseService.addCourse( c3 );
    }
    @Test
    public void test_init_direction() {
        Direction d1 = new Direction();
        d1.setName( "移动应用开发" );
        courseService.addDirection( d1 );
        Direction d2 = new Direction();
        d2.setName( "Web/微服务" );
        courseService.addDirection( d2 );
        Direction d3 = new Direction();
        d3.setName( "微信/钉钉等小程序" );
        courseService.addDirection( d3 );
    }
    @Test
    public void test_DirectionGraduate(){
        Direction d1 = directionRepository.findById(1).orElse(null);
        Graduate g = graduateRepository.findById(1).orElse(null);
        DirectionGraduate dir = new DirectionGraduate();
        dir.setDirection(d1);
        dir.setGraduate(g);
       directionGraduateRepository.save( dir );

    }

}