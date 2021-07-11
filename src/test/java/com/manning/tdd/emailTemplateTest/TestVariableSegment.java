package com.manning.tdd.emailTemplateTest;

import com.manning.tdd.emailTemplate.MissingValueException;
import com.manning.tdd.emailTemplate.Variable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestVariableSegment {

	private Map<String, String> variables;

	@BeforeEach
	public void setUp() {
		variables = new HashMap<>();
	}

	@Test
	public void variableEvaluatesToItsValue() {
		String name = "myvar";
		String value = "myvalue";
		variables.put(name, value);
		assertEquals(value, new Variable(name).evaluate(variables));
	}

	@Test
	public void missingVariableRaisesException() {
		String name = "myvaar";
		try {
			new Variable(name).evaluate(variables);
			fail("Missing variable value should raise an exception");
		} catch (MissingValueException e) {
			assertEquals("No value for ${" + name + "}", e.getMessage());
		}
	}
}
