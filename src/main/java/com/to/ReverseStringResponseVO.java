package com.to;

import org.springframework.stereotype.Component;

@Component
public class ReverseStringResponseVO {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
