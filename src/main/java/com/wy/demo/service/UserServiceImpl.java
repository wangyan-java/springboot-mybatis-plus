package com.wy.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wy.demo.entity.User;
import com.wy.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WY
 * @Description:
 * @date 2019/7/25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{


    @Override
    public void save(List<User> pos) {
        this.saveBatch(pos,1000);
    }
}
