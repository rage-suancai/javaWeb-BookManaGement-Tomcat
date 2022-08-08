package com.book.service.impl;

import com.book.Utils.MybatisUtils;
import com.book.dao.UserMapper;
import com.book.entity.User;
import com.book.service.UserService;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpSession;

public class UserServiceImpl implements UserService {

    @Override
    public boolean auth(String username, String password, HttpSession session) { // 验证登录逻辑
        try (SqlSession sqlsession = MybatisUtils.getSession()){
            UserMapper mapper = sqlsession.getMapper(UserMapper.class);

            User user = mapper.getUser(username, password);
            if (user == null) return false;
            session.setAttribute("user", user);
            return true;
        }
    }
}
