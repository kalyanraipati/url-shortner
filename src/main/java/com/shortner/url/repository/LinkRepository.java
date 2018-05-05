package com.shortner.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shortner.url.model.Link;

public interface LinkRepository extends JpaRepository<Link, String> {

	Link findByFullUrl(String fullUrl);

	Link findByShortUrl(String shortUrl);

}
