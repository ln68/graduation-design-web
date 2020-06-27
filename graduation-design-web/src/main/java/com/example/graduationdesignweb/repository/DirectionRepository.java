package com.example.graduationdesignweb.repository;

import com.example.graduationdesignweb.entity.Course;
import com.example.graduationdesignweb.entity.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectionRepository extends BaseRepository<Direction, Integer> {

}
