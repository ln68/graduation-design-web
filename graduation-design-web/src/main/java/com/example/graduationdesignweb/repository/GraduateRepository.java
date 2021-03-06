package com.example.graduationdesignweb.repository;

import com.example.graduationdesignweb.entity.Graduate;
import com.example.graduationdesignweb.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GraduateRepository extends BaseRepository<Graduate, Integer> {
    @Query("from Graduate g where g.user.name=:name and g.user.number=:num")
    Graduate find(@Param("name") String name, @Param("num") int num);
    @Query("select g.teacher from Graduate g  where g.teacher.id=:id")
    Teacher find(@Param("id") int id);
}
