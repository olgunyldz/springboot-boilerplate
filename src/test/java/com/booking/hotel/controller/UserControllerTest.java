package com.booking.hotel.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.booking.hotel.entity.Student;
import com.booking.hotel.service.IStudentService;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@Mock
	IStudentService studentService;

	@InjectMocks
	StudentController controller;

	@Test
	public void testfindAll() throws Exception {
		List<Student> students = new ArrayList<Student>();
		for (int i = 0; i < 5; i++) {
			Student student = new Student();
			student.setAge(i);
			student.setName("name" + 0);
			student.setRollNum(i);
			students.add(student);
		}

		Mockito.when(studentService.getAllStudents()).thenReturn(students);

		ResponseEntity<List<Student>> result = controller.getAllStudents();
		assertEquals(result.getStatusCode(), result.getStatusCode());
		assertEquals(5, result.getBody().size());
	}
}
