package com.bncl.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.bncl.dto.CommentDto;
import com.bncl.entity.Comment;


@Mapper
public interface ICommentMapper {
	
	ICommentMapper INSTANCE = Mappers.getMapper(ICommentMapper.class);


	CommentDto commentToCommentDto(Comment comment);
	
	Comment commentDtoToComment(CommentDto commentDto);
	
	List<CommentDto> commentListToCommentDtoList(List<Comment> commentList);
	
	List<Comment> commentDtoListToCommentList(List<CommentDto> CommentDtoList);
	
}
