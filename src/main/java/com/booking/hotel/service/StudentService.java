package com.booking.hotel.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.hotel.entity.Student;
import com.booking.hotel.exception.BusinessException;
import com.booking.hotel.repository.StudentRepository;

@Service
public class StudentService implements IStudentService {
	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student getStudentByRollNum(long rollNum) {
		Optional<Student> obj = studentRepository.findById(rollNum);
		return obj.orElseThrow(() -> new BusinessException("ERR00001"));

	}

	@Override
	public List<Student> getAllStudents() {
		List<Student> list = new ArrayList<>();
		studentRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public boolean addStudent(Student student) {
		studentRepository.save(student);
		return true;

	}

	@Override
	public void updateStudent(Student student) {
		studentRepository.save(student);
	}

	@Override
	public void deleteStudent(long rollNum) {
		studentRepository.delete(getStudentByRollNum(rollNum));
	}
}