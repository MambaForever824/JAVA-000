package com.geekuniversity.java.week05.homework02.annotation;

import com.geekuniversity.java.week05.homework02.config.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public void add(Student student) {
        System.out.println("Add Student");
    }

    @Override
    public void update(Student student) {
        System.out.println("Update Student");
    }

}