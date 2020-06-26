package com.example.graduationdesignweb.service;

import com.example.graduationdesignweb.entity.Teacher;
import com.example.graduationdesignweb.entity.User;
import com.example.graduationdesignweb.repository.TeacherRepository;
import com.example.graduationdesignweb.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public User getUser(int num) {
        return userRepository.find(num);
    }

    public Teacher getTeacher(int tid) {
        return teacherRepository.findById(tid).orElse(null);
    }
    /**
     * 对user声明了persist/remove操作
     * @param teacher
     * @return
     */
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(int quantity, int ranges, int tid) {
        Teacher t = teacherRepository.findById(tid)
                .orElseThrow();
        t.setQuantity(quantity);
        t.setRanges(ranges);
        return t;
    }
}