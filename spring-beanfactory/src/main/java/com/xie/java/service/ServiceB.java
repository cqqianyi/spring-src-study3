package com.xie.java.service;

import com.xie.java.annotation.Consumer;
import com.xie.java.annotation.Tag;
import com.xie.java.annotation.Topic;

/**
 * Created by xieyang on 18/7/14.
 */
@Consumer
@Topic(value = "TopicB")
public interface ServiceB {

    @Tag(value = "testA")
    void testA(String a);
}
