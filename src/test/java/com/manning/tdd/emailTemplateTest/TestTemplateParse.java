package com.manning.tdd.emailTemplateTest;

import com.manning.tdd.emailTemplate.PlainText;
import com.manning.tdd.emailTemplate.Segment;
import com.manning.tdd.emailTemplate.TemplateParse;
import com.manning.tdd.emailTemplate.Variable;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTemplateParse {

	@Test
	public void emptyTemplateRendersAsEmptyString() {
		List<Segment> segments = parse("");
		assertSegments(segments, new PlainText(""));
	}

	@Test
	public void templateOnlyPlainText() {
		List<Segment> segments = parse("plain text only");
		assertSegments(segments, new PlainText("plain text only"));
	}

	@Test
	public void parsingMultipleVariables() {
		List<Segment> segments = parse("${a}:${b}:${c}");
		assertSegments(segments, new Variable("a"),
					   new PlainText(":"), new Variable("b"),
					   new PlainText(":"), new Variable("c"));
	}

	@Test
	public void parsingTemplateIntoSegmentObjects() {
		TemplateParse p = new TemplateParse();
		List<Segment> segments = p.parseSegments("a ${b} c ${d}");
		assertSegments(segments,
					   new PlainText("a "), new Variable("b"),
					   new PlainText(" c "), new Variable("d"));
	}

	private void assertSegments(List<?> actual, Object... expected) {
		assertEquals(expected.length, actual.size());
		assertEquals(Arrays.asList(expected), actual);
	}

	private List<Segment> parse(String template) {
		return new TemplateParse().parseSegments(template);
	}
}
