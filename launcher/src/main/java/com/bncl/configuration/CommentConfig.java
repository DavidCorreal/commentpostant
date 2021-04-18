package com.bncl.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bncl.adapters.CommentJpaAdapter;
import com.bncl.ports.api.ICommentServicePort;
import com.bncl.ports.spi.ICommentPersistencePort;
import com.bncl.service.CommentService;


@Configuration
public class CommentConfig {

	@Bean
	public ICommentPersistencePort commentPersistence() {
		return new CommentJpaAdapter();
	}
	
	@Bean
	public ICommentServicePort commentService() {
		return new CommentService(commentPersistence());
	}
}
