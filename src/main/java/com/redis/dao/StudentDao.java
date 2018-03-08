package com.redis.dao;

import com.redis.pojo.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao {
    Student getById(Integer id);
}
