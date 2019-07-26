package com.wy.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.demo.entity.User;
import com.wy.demo.vo.UserQueryVo;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User>  {

    Page<User> selectUserList(UserQueryVo vo);

}
