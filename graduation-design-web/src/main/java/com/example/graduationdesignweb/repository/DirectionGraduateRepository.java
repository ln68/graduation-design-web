package com.example.graduationdesignweb.repository;

import com.example.graduationdesignweb.entity.Course;
import com.example.graduationdesignweb.entity.Direction;
import com.example.graduationdesignweb.entity.DirectionGraduate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DirectionGraduateRepository extends BaseRepository<DirectionGraduate, Integer> {
    @Query("select d.direction from  DirectionGraduate d where d.graduate.id=:gid")
    List<Direction> list(@Param("gid")Integer gid);
        }
