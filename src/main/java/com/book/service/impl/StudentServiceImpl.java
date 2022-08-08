package com.book.service.impl;

import com.book.Utils.MybatisUtils;
import com.book.dao.StudentMapper;
import com.book.entity.Student;
import com.book.service.StudentService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Override
    public List<Student> getStudentList() { // 学生信息逻辑
        try (SqlSession session = MybatisUtils.getSession()){
            StudentMapper mapper = session.getMapper(StudentMapper.class);

            return mapper.getStudentList();
        }
    }

}
