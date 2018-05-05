package com.shortner.url.service;

import com.shortner.url.model.Link;

public interface UrlService {

	Link shorten(String fullUrl);

	Link expand(String shortUrl);

}
