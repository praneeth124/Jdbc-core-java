package com.slokam.da.hc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.slokam.da.hc.entity.Patient;


class HcApplicationTests {

	/*
	 * @Test void contextLoads() { }
	 */

	public static void main(String[] args) {
		Object[] values = {2,"asdf",3453};
		
		Patient p = new Patient();
		
		System.out.println(values[0]);
				
	}
}
