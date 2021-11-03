package com.booking.hotel.util;

import java.util.Random;

import com.booking.hotel.entity.Student;

public final class TestUtil {
	public static Student createStudent() {
		Student student = new Student();
		student.setName(randomString(10));
		student.setAge(generate(1, 100));
		return student;
	}

	public static int generate(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public static String randomString(int targetStringLength) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();
		return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}
}
