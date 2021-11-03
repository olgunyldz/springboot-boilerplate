package com.booking.hotel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.booking.hotel.entity.Student;
import com.booking.hotel.repository.StudentRepository;
import com.booking.hotel.util.TestUtil;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	@InjectMocks
	StudentService service;

	@Mock
	StudentRepository dao;

	@Test
	void test() {
		Student student = TestUtil.createStudent();
		ArgumentCaptor<Student> saveArgument = ArgumentCaptor.forClass(Student.class);
		when(dao.save(saveArgument.capture())).thenReturn(student);
		boolean result = service.addStudent(student);
		assertTrue(result);
		verify(dao,times(1)).save(saveArgument.getValue());
	}

}
