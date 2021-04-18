package com.bncl.service;

import java.util.List;

import com.bncl.dto.CommentDto;
import com.bncl.ports.api.ICommentServicePort;
import com.bncl.ports.spi.ICommentPersistencePort;


public class CommentService implements ICommentServicePort {

	private ICommentPersistencePort commentPersistencePort;
	
	public CommentService(ICommentPersistencePort commentPersistencePort) {
		this.commentPersistencePort = commentPersistencePort;
	}
	
	@Override
	public CommentDto save(CommentDto commentDto) {
		return commentPersistencePort.save(commentDto);
	}

	@Override
	public List<CommentDto> findByPostId(Integer postId) {
		return commentPersistencePort.findByPostId(postId);
	}

}
