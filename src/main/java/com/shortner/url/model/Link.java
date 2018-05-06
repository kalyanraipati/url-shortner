package com.shortner.url.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Link {

	public Link() {
	}

	@Id
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
