package com.book.service.impl;

import com.book.Utils.MybatisUtils;
import com.book.dao.BookMapper;
import com.book.entity.Book;
import com.book.service.BookService;
import org.apache.ibatis.session.SqlSession;

import java.util.*;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    BorrowServiceImpl service = new BorrowServiceImpl();

    @Override
    public List<Book> getActiveBookList() { // 过滤被借图书信息并显示现有图书信息逻辑
        Set<Integer> set = new HashSet<>();
        service.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));

        try (SqlSession session = MybatisUtils.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);

            return mapper.getBookList()
                    .stream()
                    .filter(book -> !set.contains(book.getBid()))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Map<Book, Boolean> getBookList() { // 图书信息和借阅状态逻辑
        try (SqlSession session = MybatisUtils.getSession()){
            Set<Integer> set = new HashSet<>();
            service.getBorrowList().forEach(borrow -> set.add(borrow.getBook_id()));

            Map<Book, Boolean> map = new LinkedHashMap<>();
            BookMapper mapper = session.getMapper(BookMapper.class);

            mapper.getBookList().forEach(book -> map.put(book, set.contains(book.getBid())));
            return map;
        }
    }

    @Override
    public void deleteBook(int bid) { // 删除图书信息逻辑
        try (SqlSession session = MybatisUtils.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);

            mapper.deleteBook(bid);
        }
    }

    @Override
    public void addBook(String title, String desc, double price) { // 录入图书信息逻辑
        try (SqlSession session = MybatisUtils.getSession()){
            BookMapper mapper = session.getMapper(BookMapper.class);

            mapper.addBook(title, desc, price);
        }
    }

}
