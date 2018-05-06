package com.shortner.url.repository;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.shortner.url.model.Link;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LinkRepositoryTest {

	@Autowired
	private LinkRepository linkRepository;

	@Autowired
	private TestEntityManager entityManager;

	private static final String SHORT_URL = "xyz";
	private static final String FULL_URL = "abcd";
	private static final Link LINK = new Link(FULL_URL, SHORT_URL);

	@Test
	public void findByFullUrl_shouldFetchUrlMatch() throws InterruptedException {
		linkRepository.save(LINK);
		Link foundLink = linkRepository.findByFullUrl(FULL_URL);
		Assertions.assertThat(foundLink).isEqualToComparingFieldByField(LINK);
	}

	@Test
	public void findByShort_shouldFetchUrlMatch() {
		linkRepository.save(LINK);
		Link foundLink = linkRepository.findByShortUrl(SHORT_URL);
		Assertions.assertThat(foundLink).isEqualToComparingFieldByField(LINK);
	}
}
