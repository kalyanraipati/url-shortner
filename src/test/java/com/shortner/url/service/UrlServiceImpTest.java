package com.shortner.url.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.shortner.url.controller.URLNotFoundException;
import com.shortner.url.model.Link;
import com.shortner.url.repository.LinkRepository;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceImpTest {

	private static final String SHORT_URL = "xyz";
	private static final String FULL_URL = "abcd";
	private static final Link LINK = new Link(FULL_URL, SHORT_URL);
	private UrlService urlService;
	@Mock
	private LinkRepository linkRepository;

	@Before
	public void setup() {
		urlService = new UrlServiceImpl(linkRepository);
	}

	@Test
	public void shorten_lookupDatabase_AndReturnLink() {

		when(linkRepository.findByFullUrl(anyString())).thenReturn(LINK);
		Link link = urlService.shorten(FULL_URL);
		Assertions.assertThat(link.getShortUrl()).isEqualTo(SHORT_URL);

		verify(linkRepository).findByFullUrl(anyString());

	}

	@Test
	public void exand_lookupDatabase__AndReturnLink() {
		when(linkRepository.findByShortUrl(anyString())).thenReturn(LINK);
		Link link = urlService.expand(SHORT_URL);
		Assertions.assertThat(link.getFullUrl()).isEqualTo(FULL_URL);
		verify(linkRepository).findByShortUrl(anyString());

	}

	@Test(expected = URLNotFoundException.class)
	public void exand_lookupDatabase_ThrowNotFoundException() {
		when(linkRepository.findByShortUrl(anyString())).thenThrow(
				new URLNotFoundException());
		Link link = urlService.expand(SHORT_URL);
		Assertions.assertThat(link).isNull();
		verify(linkRepository).findByShortUrl(anyString());

	}

	@Test
	public void shorten_whenShortenURL_WhenLookupFails() {
		when(linkRepository.findByFullUrl(anyString())).thenReturn(null);
		Link link = urlService.shorten(FULL_URL);
		verify(linkRepository).findByFullUrl(FULL_URL);
		verify(linkRepository).save(link);
	}

}
