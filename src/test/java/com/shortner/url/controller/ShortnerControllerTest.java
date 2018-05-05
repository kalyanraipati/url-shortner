package com.shortner.url.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.shortner.url.controller.ShortnerController;
import com.shortner.url.controller.URLNotFoundException;
import com.shortner.url.model.Link;
import com.shortner.url.service.UrlService;

@RunWith(SpringRunner.class)
@WebMvcTest(ShortnerController.class)
public class ShortnerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private UrlService urlService;

	@Test
	public void whenShorten_ShouldReturnLink() throws Exception {
		given(urlService.shorten(anyString())).willReturn(
				new Link("abcd", "xyz"));

		mockMvc.perform(get("/shorten?url=abcd")).andExpect(status().isOk())
				.andExpect(jsonPath("shortUrl").value("xyz"))
				.andExpect(jsonPath("fullUrl").value("abcd"));

		verify(urlService).shorten(anyString());
	}

	@Test
	public void whenExpand_ShouldReturnLink() throws Exception {

		given(urlService.expand(anyString())).willReturn(
				new Link("abcd", "xyz"));

		mockMvc.perform(get("/expand?url=abcd")).andExpect(status().isOk())
				.andExpect(jsonPath("shortUrl").value("xyz"))
				.andExpect(jsonPath("fullUrl").value("abcd"));

		verify(urlService).expand(anyString());

	}

	@Test
	public void whenExpandWrongShortUrl_ShouldThrowNotFoundExeption()
			throws Exception {

		given(urlService.expand(anyString())).willThrow(
				new URLNotFoundException());

		mockMvc.perform(get("/expand?url=abcd")).andExpect(
				status().isNotFound());

		verify(urlService).expand(anyString());

	}
}
