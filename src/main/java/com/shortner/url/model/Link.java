package com.shortner.url.model;

import java.util.function.IntPredicate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Link {

	@JsonProperty("shortUrl")
	private String shortUrl;

	@JsonProperty("fullUrl")
	private String fullUrl;

	public Link(String fullUrl, String shortUrl) {
		this.fullUrl = fullUrl;
		this.shortUrl = shortUrl;
	}

	public String getShortUrl() {
		return this.shortUrl;
	}

	public String getFullUrl() {
		return this.fullUrl;
	}

}
