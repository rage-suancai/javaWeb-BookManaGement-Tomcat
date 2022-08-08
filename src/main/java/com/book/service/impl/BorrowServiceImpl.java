package com.book.service.impl;

import com.book.Utils.MybatisUtils;
import com.book.dao.BorrowMapper;
import com.book.entity.Borrow;
import com.book.service.BorrowService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BorrowServiceImpl implements BorrowService {

    @Override
    public List<Borrow> getBorrowList() { // 借阅信息展示逻辑
        try (SqlSession session = MybatisUtils.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);

            return mapper.getBorrowList();
        }
    }

    @Override
    public void addBorrow(int sid, int bid) { // 添加借阅信息逻辑
        try (SqlSession session = MybatisUtils.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);

            mapper.addBorrow(sid, bid);
        }
    }

    @Override
    public void returnBorrow(String id) { // 删除图书借阅信息逻辑
        try (SqlSession session = MybatisUtils.getSession()){
            BorrowMapper mapper = session.getMapper(BorrowMapper.class);

            mapper.deleteBorrow(id);
        }
    }

}
