package com.xie.java;

import com.xie.java.annotation.VirtualApi;
import com.xie.java.annotation.Tag;
import com.xie.java.annotation.Topic;

@VirtualApi
@Topic(value = "TopicA")
public interface InterfaceB {

    @Tag(value = "testA")
    void testA(String a);

    @Tag(value = "testB")
    String testB(String a);

}
