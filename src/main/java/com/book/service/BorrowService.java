package com.book.service;

import com.book.entity.Borrow;

import java.util.List;

public interface BorrowService {

    List<Borrow> getBorrowList();

    void addBorrow(int sid, int bid);

    void returnBorrow(String id);

}
