package com.example.graduationdesignweb.repository;

import com.example.graduationdesignweb.entity.Course;
import com.example.graduationdesignweb.entity.Graduate;
import com.example.graduationdesignweb.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends BaseRepository<Teacher, Integer> {
    @Query("from Teacher t where u.id=:id")
    List<Teacher> list(@Param("name") String name);
}