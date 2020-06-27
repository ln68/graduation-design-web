package com.example.graduationdesignweb.controller;


import com.example.graduationdesignweb.component.MyToken;
import com.example.graduationdesignweb.component.RequestComponent;
import com.example.graduationdesignweb.entity.*;
import com.example.graduationdesignweb.service.CourseService;
import com.example.graduationdesignweb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/teacher/")
public class TeacherController {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;


    @GetMapping("welcome")
    public void getIndex(HttpServletRequest request, @RequestAttribute(MyToken.UID) int uid) {
        log.debug("{}", (int)request.getAttribute( MyToken.UID));
        log.debug("{}", uid);
        log.debug("{}", requestComponent.getUid());
    }

    @GetMapping("index")
    public Map getTeacher() {
        Teacher t = userService.getTeacher(requestComponent.getUid());
        return Map.of(
                "teacher", t,
                "courses", t.getCourses(),
                "graduates", t.getGraduates());
    }

    @PostMapping("courses")//添加课程
    public Map postCourse(@RequestBody Course course) {
        course.setTeacher(new Teacher(requestComponent.getUid()));
        courseService.addCourse(course);
        return Map.of("course", course);
    }
    @PostMapping("directions")//添加方向
    public Map postDirection(@RequestBody Direction direction) {
        direction.setTeacher(new Teacher(requestComponent.getUid()));
        courseService.addDirection(direction);
        return Map.of("direction", direction);
    }
    @GetMapping("directions/{did}")
    public Map getDirection(@PathVariable int did) {
        return Map.of("direction", courseService.getDirection(did, requestComponent.getUid()));
    }
    @GetMapping("courses/{cid}")
    public Map getCourse(@PathVariable int cid) {
        return Map.of("course", courseService.getCourse(cid, requestComponent.getUid()));
    }

    @PatchMapping("settings")//添加学生数量，范围
    public Map patchSettings(@RequestBody Teacher t) {
        Teacher teacher = userService.updateTeacher(t.getQuantity(), t.getRanges(), requestComponent.getUid());
        return Map.of("teacher", teacher);
    }

    @PostMapping("graduates")
    public Map postGraduate(@RequestBody Graduate g) {
        Graduate graduate = courseService.addGraduate(g, requestComponent.getUid());
        return Map.of("graduate", g);
    }

    @PostMapping("Elective")
    public Map Elective(@RequestBody Elective elective){
        courseService.elective(1,1);
        return Map.of("result",1);
    }
}