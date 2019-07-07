package com.xie.java.service;

import com.xie.java.dao.BMapper;
import com.xie.java.datasource.annotation.DatabaseId;
import com.xie.java.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2018/3/2.
 */

@Service
@DatabaseId("ds1")
public class BServiceImpl implements BService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BMapper bMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        bMapper.insertUser(user);

    }

    @Override
    public User queryById(Integer id) {
        return bMapper.queryById(id);
    }


    @Override
    public List<User> getUsers() {
        return bMapper.listUser();
    }
}
