package com.example.graduationdesignweb.service;

import com.example.graduationdesignweb.entity.*;
import com.example.graduationdesignweb.repository.*;
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
    @Autowired
    private DirectionRepository directionRepository;
    @Autowired
    private ElectiveRepository electiveRepository;

    public Course addCourse(Course c) {
        return courseRepository.refresh(courseRepository.save(c));
    }
    public Direction addDirection(Direction d) {
    return directionRepository.refresh( directionRepository.save( d ));
    }
    public List<Direction> directions(int tid) {
        return teacherRepository.getOne(tid).getDirections();
    }
    public Direction getDirection(int did, int uid) {
        return directionRepository.findById(did).orElse( null );}
    public List<Course> courses(int tid) {
        return teacherRepository.getOne(tid).getCourses();
    }
    public Course getCourse(int cid, int uid) {
        return courseRepository.find(cid, uid);
    }
    public Elective elective(int gid,int cid) {
        Elective Elective =new Elective();
        Course course=courseRepository.findById(cid).orElse(null);
        Graduate graduate=graduateRepository.findById( gid ).orElse( null );
        if(graduate!=null&&course!=null){
            Elective.setGraduate(graduate);
            Elective.setCourse(course);
            electiveRepository.save(Elective);
        }
        return Elective;

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