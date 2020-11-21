package com.geekuniversity.java.week05.homework02;

import com.geekuniversity.java.week05.homework02.annotation.StudentService;
import com.geekuniversity.java.week05.homework02.config.Klass;
import com.geekuniversity.java.week05.homework02.config.Student;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanTest {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student123 = beanFactory.getBean("student123", Student.class);
        System.out.println(student123.toString());

        Student student100 = beanFactory.getBean("student100", Student.class);
        System.out.println(student100.toString());

        Klass class1 = beanFactory.getBean(Klass.class);
        System.out.println(class1);
        System.out.println("Klass对象AOP代理后的实际类型：" + class1.getClass());
        System.out.println("Klass对象AOP代理后的实际类型是否是Klass子类：" + (class1 instanceof Klass));

        StudentService studentService = beanFactory.getBean(StudentService.class);
        studentService.add(student123);
        studentService.update(student100);
    }

}