package com.manning.tdd.emailTemplateTest;

import com.manning.tdd.emailTemplate.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTemplatePerformance {

	private Template template;

	@BeforeEach
	public void setUp() {

		int MAX_VARIABLES = 20;
		int MAX_TEXT = 100;
		StringBuilder text = new StringBuilder();

		for(int i = 0; i<MAX_TEXT; i++){
			text.append(i).append(" ");
		}

		for (int i = 0; i < MAX_VARIABLES; i++) {
			text.append("${variable")
				.append(i)
				.append("},");
		}
		template = new Template(text.toString());
		for(int i = 0; i < MAX_VARIABLES; i++){
			template.set("variable" + i, String.valueOf(i));
		}
	}

	@Test
	public void templateWith100Wordsand20Variables() {
		long expected = 200L;
		long time = System.currentTimeMillis();
		template.evaluate();
		time = System.currentTimeMillis() - time;
		System.out.println(time);
		Assertions.assertTrue(time <= expected);
	}
}
