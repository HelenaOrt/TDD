package com.manning.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTemplate {

	private Template template;

	@BeforeEach
	public void setUp() {
		template = new Template("${one}, ${two}, ${three}");
		template.set("one", "1");
		template.set("two", "2");
		template.set("three", "3");
	}

	@Test
	public void multipleVariables() {
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	@Test
	public void unknownVariablesAreIgnored() {
		template.set("doesnotexist", "whatever");
		assertTemplateEvaluatesTo("1, 2, 3");
	}

	@Test
	public void missingValueRaisesException() {
//		assertThrows(MissingValueException.class,
//					 () -> new Template("${foo}").evaluate(),
//					 "No value for ${foo}");

		MissingValueException exception = assertThrows(MissingValueException.class,
													   () -> new Template("${foo}")
															   .evaluate());
		assertEquals("No value for ${foo}", exception.getMessage());
	}

	@Test
	public void variablesGetProcessedJustOnce(){
		template.set("one", "${one}");
		template.set("two", "${three}");
		template.set("three", "${two}");
		assertTemplateEvaluatesTo("${one}, ${three}, ${two}");

	}

	private void assertTemplateEvaluatesTo(String expected) {
		assertEquals(expected, template.evaluate());
	}
}
