package com.shortner.url.controller;

import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shortner.url.model.Link;
import com.shortner.url.service.UrlService;

@RestController
public class ShortnerController {

	private UrlService urlService;

	public ShortnerController(UrlService urlService) {
		this.urlService = urlService;
	}

	@GetMapping("shorten")
	public Link getShortenUrl(@RequestParam("url") String url) {
		return urlService.shorten(url);
	}

	@GetMapping("expand")
	public Link getExpandUrl(@RequestParam("url") String url) {
		return urlService.expand(url);
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	private void UrlNotFoundExcepion(URLNotFoundException ex) {
	}

}
