package com.shortner.url.service;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.shortner.url.controller.URLNotFoundException;
import com.shortner.url.model.Link;
import com.shortner.url.repository.LinkRepository;
import com.shortner.url.repository.LinkRepositoryTest;

@Service
public class UrlServiceImpl implements UrlService {

	private LinkRepository linkRepository;

	public UrlServiceImpl(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	@Override
	public Link shorten(String fullUrl) {
		Link link = linkRepository.findByFullUrl(fullUrl);
		if (link == null) {
			String shortUrl = Hashing.murmur3_32()
					.hashString(fullUrl, StandardCharsets.UTF_8).toString();
			link = new Link(fullUrl, shortUrl);
			linkRepository.save(link);
		}
		return link;
	}

	@Override
	public Link expand(String shortUrl) throws URLNotFoundException {
		Link link = linkRepository.findByShortUrl(shortUrl);
		if(link==null)
			throw new URLNotFoundException();
		return link;
	}

}
