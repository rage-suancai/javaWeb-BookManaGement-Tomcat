package com.book.dao;

import com.book.entity.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    @Select("select * from admin where username = #{username} and password = #{password}")
    User getUser(@Param("username") String username, @Param("password") String password);

}
