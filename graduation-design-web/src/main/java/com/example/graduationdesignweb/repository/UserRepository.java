package com.example.graduationdesignweb.repository;

import com.example.graduationdesignweb.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Integer> {
    @Query("from User u where u.number=:num")
    User find(@Param("num") int num);

}