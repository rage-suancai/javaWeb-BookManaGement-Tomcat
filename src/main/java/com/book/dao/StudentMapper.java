package com.book.dao;

import com.book.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StudentMapper {

    @Select("select * from student")
    List<Student> getStudentList();

}
