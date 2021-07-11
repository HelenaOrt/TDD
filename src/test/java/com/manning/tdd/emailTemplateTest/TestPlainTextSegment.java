package com.manning.tdd.emailTemplateTest;

import com.manning.tdd.emailTemplate.PlainText;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlainTextSegment {

	@Test
	public void plainTextEvaluateAsIs() {
		Map<String, String> variables = new HashMap<>();
		String text = "abc def";
		assertEquals(text, new PlainText(text).evaluate(variables));
	}
}
