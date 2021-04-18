package com.bncl.ports.api;

import java.util.List;

import com.bncl.dto.CommentDto;


public interface ICommentServicePort {
	
	public CommentDto save(CommentDto commentDto);
	
	public List<CommentDto> findByPostId(Integer postId);

}
