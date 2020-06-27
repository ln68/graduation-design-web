package com.example.graduationdesignweb.repository;


import com.example.graduationdesignweb.entity.Course;
import com.example.graduationdesignweb.entity.Elective;
import com.example.graduationdesignweb.entity.Graduate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectiveRepository extends BaseRepository<Elective, Integer> {
    @Query("select c.course from Elective c where c.graduate.id=:gid")
    List<Course> list(@Param("gid")Integer gid);
}
