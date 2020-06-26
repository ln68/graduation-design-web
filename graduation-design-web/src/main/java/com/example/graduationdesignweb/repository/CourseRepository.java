package com.example.graduationdesignweb.repository;

import com.example.graduationdesignweb.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseRepository<Course, Integer> {
    @Query("from Course c where c.id=:cid and c.teacher.id=:tid")
    Course find(@Param("cid") int cid, @Param("tid") int tid);
}