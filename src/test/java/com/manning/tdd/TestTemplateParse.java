package com.manning.tdd;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplateParse {

	@Test
	public void emptyTemplateRendersAsEmptyString() {
		List<String> segments = parse("");
		assertSegments(segments, "");
	}

	@Test
	public void templateOnlyPlainText() {
		List<String> segments = parse("plain text only");
		assertSegments(segments, "plain text only");
	}

	@Test
	public void parsingMultipleVariables(){
		List<String> segments = parse("${a}:${b}:${c}");
		assertSegments(segments, "${a}", ":", "${b}", ":", "${c}");
	}

	private void assertSegments(List<?> actual, Object...expected) {
		assertEquals(expected.length, actual.size());
		assertEquals(Arrays.asList(expected), actual);
	}

	private List<String> parse(String template) {
		return new TemplateParse().parse(template);
	}
}
