package com.redis.service.impl;

import com.redis.dao.StudentDao;
import com.redis.pojo.Student;
import com.redis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentDao studentDao;
    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public Student getById(Integer id) {

        //从缓存中拿数据
        ValueOperations<String,Object> valueOperations=redisTemplate.opsForValue();
        Student student=(Student) valueOperations.get("user_"+id);
        if (student!=null){
            System.out.println("从缓存中获取数据");
            return student;
        }else {
            System.out.println("从数据库中获取数据");
            Student student1=studentDao.getById(id);
            valueOperations.set("user_"+id,student1);
            return student1;
        }
    }

}
