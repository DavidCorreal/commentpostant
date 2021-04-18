package com.bncl.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bncl.dto.CommentDto;
import com.bncl.entity.Comment;
import com.bncl.mappers.ICommentMapper;
import com.bncl.ports.spi.ICommentPersistencePort;
import com.bncl.repository.ICommentRepository;


@Service
public class CommentJpaAdapter implements ICommentPersistencePort {

	@Autowired
	private ICommentRepository commentRepository;

	
	@Override
	public CommentDto save(CommentDto commentDto) {
		
		Comment comment = ICommentMapper.INSTANCE.commentDtoToComment(commentDto);
		Comment commentSaved = commentRepository.saveAndFlush(comment);
		
		return ICommentMapper.INSTANCE.commentToCommentDto(commentSaved);
	
	}

	@Override
	public List<CommentDto> findByPostId(Integer postId) {
		
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		return ICommentMapper.INSTANCE.commentListToCommentDtoList(commentList);
	}
	
}
