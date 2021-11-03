package com.booking.hotel.service;

import java.util.List;

import com.booking.hotel.entity.Student;

public interface IStudentService {
    List<Student> getAllStudents();
    Student getStudentByRollNum(long rollNum);
    boolean addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(long rollNum);
} 
