package com.booking.hotel.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.booking.hotel.entity.Student;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentRepositoryTest {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private StudentRepository repository;

	@Test
	void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(repository).isNotNull();
	}

	@Test
	void whenSaved_thenFindsByName() {
		Student student = new Student();
		student.setAge(1);
		student.setName("Olgun");
		Student result = repository.save(student);
		assertThat(repository.findByName(student.getName())).isNotNull();
		assertEquals(student.getName(),result.getName());
		assertEquals(student.getAge(),result.getAge());
	}
}
