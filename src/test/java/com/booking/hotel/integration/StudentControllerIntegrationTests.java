package com.booking.hotel.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.booking.hotel.BookingApplication;
import com.booking.hotel.entity.Student;
import com.booking.hotel.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = BookingApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class StudentControllerIntegrationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void registrationWorksThroughAllLayers() throws Exception {
		Student student= new Student();
		student.setAge(12);
		student.setName("Olgun");
		student.setRollNum(1234L);

		mockMvc.perform(post("/student", 42L).contentType("application/json")
				.content(objectMapper.writeValueAsString(student)))
				.andExpect(status().isCreated());

		Student s = studentRepository.findByName("Olgun").get();
		assertThat(s.getName()).isEqualTo("Olgun");
	}
}
