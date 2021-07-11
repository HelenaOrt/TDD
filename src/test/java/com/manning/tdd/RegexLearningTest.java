package com.manning.tdd;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class RegexLearningTest {

	@Test
	public void testFindStartAndEnd() {
		String haystack = "The needle shop sells needles";
		String regex = "(needle)";
		Matcher matcher = Pattern.compile(regex).matcher(haystack);
		assertTrue(matcher.find());
		assertEquals(4, matcher.start());
		assertEquals(10, matcher.end());

		assertTrue(matcher.find());
		assertEquals(22, matcher.start());
		assertEquals(28, matcher.end());

		assertFalse(matcher.find());
	}

}
