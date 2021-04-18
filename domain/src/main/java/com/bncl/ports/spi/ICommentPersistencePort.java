package com.bncl.ports.spi;

import java.util.List;

import com.bncl.dto.CommentDto;


public interface ICommentPersistencePort {

	public CommentDto save(CommentDto commentDto);
	
	public List<CommentDto> findByPostId(Integer postId);
	
}
