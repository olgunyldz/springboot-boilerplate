package com.booking.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.booking.hotel.entity.Student;
import com.booking.hotel.service.IStudentService;

import io.swagger.annotations.Api;


@RestController
public class StudentController {
	@Autowired
	private IStudentService studentService;

	@GetMapping("student/{rollNum}")
	public ResponseEntity<Student> getStudentByRollNum(@PathVariable("rollNum") long rollNum) {
		Student student = studentService.getStudentByRollNum(rollNum);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@GetMapping("students")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> list = studentService.getAllStudents();
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}

	@PostMapping("student")
	public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder builder) {
		studentService.addStudent(student);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/student/{rollNum}").buildAndExpand(student.getRollNum()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PutMapping("student")
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		studentService.updateStudent(student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
	}

	@DeleteMapping("student/{rollNum}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("rollNum") long rollNum) {
		studentService.deleteStudent(rollNum);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
