package com.example;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
// Spring Boot DevTools 의존성 추가
@SpringBootTest
class BoardWeb1ApplicationTests {

	@Test
	void testMethod() {
		System.out.println("===> testMethod()");
		assertTrue(true);
		assertNull(null);
        assertEquals(12, 12);
	}

}
