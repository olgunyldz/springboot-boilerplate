package com.booking.hotel.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.booking.hotel.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long>  {
	Optional<Student> findByName(String name);
} 