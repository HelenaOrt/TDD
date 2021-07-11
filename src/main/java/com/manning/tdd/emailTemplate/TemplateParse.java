package com.manning.tdd.emailTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParse {

	public List<Segment> parseSegments(String template) {
		List<Segment> segments = new ArrayList<>();
		List<String> strings = parse(template);
		for (String s : strings) {
			if (isVariable(s)) {
				String name = s.substring(2, s.length() - 1);
				segments.add(new Variable(name));
			} else {
				segments.add(new PlainText(s));
			}
		}
		return segments;
	}

	private List<String> parse(String template) {
		List<String> segments = new ArrayList<>();
		int index = collectSegments(segments, template);
		addTrail(segments, template, index);
		addEmptyStringIfTemplateWasEmpty(segments);

		return segments;
	}

	private boolean isVariable(String segment) {
		return segment.startsWith("${") && segment.endsWith("}");
	}

	private int collectSegments(List<String> segments, String src) {
		Pattern pattern = Pattern.compile("\\$\\{[^}]*}");
		Matcher matcher = pattern.matcher(src);
		int index = 0;
		while (matcher.find()) {
			addPrecedingPlainText(segments, src, matcher, index);
			addVariable(segments, src, matcher);
			index = matcher.end();
		}
		return index;
	}

	private void addTrail(List<String> segments, String template, int index) {
		if (index < template.length()) {
			segments.add(template.substring(index));
		}
	}

	private void addVariable(List<String> segments, String src, Matcher matcher) {
		segments.add(src.substring(matcher.start(), matcher.end()));
	}

	private void addPrecedingPlainText(List<String> segments, String src, Matcher matcher,
									   int index) {
		if (index != matcher.start()) {
			segments.add(src.substring(index, matcher.start()));
		}
	}

	private void addEmptyStringIfTemplateWasEmpty(List<String> segments) {
		if (segments.isEmpty()) {
			segments.add("");
		}
	}
}
