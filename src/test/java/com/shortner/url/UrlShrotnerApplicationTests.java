package com.shortner.url;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.shortner.url.model.Link;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UrlShrotnerApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shotenTest() {

		ResponseEntity<Link> response = restTemplate.getForEntity(
				"/shortener?fullUrl=abcd", Link.class);
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody().getShortUrl()).isEqualTo("");
		
	}

}
