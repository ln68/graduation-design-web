package com.example.graduationdesignweb.service;

import com.example.graduationdesignweb.entity.Course;
import com.example.graduationdesignweb.entity.Graduate;
import com.example.graduationdesignweb.entity.Teacher;
import com.example.graduationdesignweb.entity.User;
import com.example.graduationdesignweb.repository.CourseRepository;
import com.example.graduationdesignweb.repository.GraduateRepository;
import com.example.graduationdesignweb.repository.TeacherRepository;
import com.example.graduationdesignweb.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private GraduateRepository graduateRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepository userRepository;

    public Course addCourse(Course c) {
        return courseRepository.refresh(courseRepository.save(c));
    }

    public List<Course> listCourses(int tid) {
        return teacherRepository.getOne(tid).getCourses();
    }
    public Course getCourse(int cid, int uid) {
        return courseRepository.find(cid, uid);
    }

    /**
     * 对user声明了persist/remove级联操作
     * 缺少判断该生已经被其他老师选择，可以抛个异常
     * @param g
     * @param tid
     * @return
     */
    public Graduate addGraduate(Graduate g, int tid) {
        Graduate graduate = Optional.ofNullable(graduateRepository.find(g.getUser().getName(), g.getUser().getNumber()))
                .orElseGet(() -> {
                    User u = g.getUser();
                    u.setPassword(encoder.encode(String.valueOf(g.getUser().getNumber())));
                    u.setRole(User.Role.GRADUATE);
                    return g;
                });
      graduate.setTeacher( new Teacher(tid) );
        graduateRepository.save(graduate);
        return graduate;
    }
}