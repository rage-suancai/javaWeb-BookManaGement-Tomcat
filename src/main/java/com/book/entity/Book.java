package com.book.entity;

import lombok.Data;

public @Data class Book {
    int bid;
    String title;
    String desc;
    double price;
}
