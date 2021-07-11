package com.manning.tdd.emailTemplate;

import java.util.Map;

public interface Segment {

	String evaluate(Map<String, String> variables);
}



