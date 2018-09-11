package com.xie.context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xieyang on 18/9/11.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        TestA bean = context.getBean(TestA.class);
        System.out.println(bean);
    }
}
