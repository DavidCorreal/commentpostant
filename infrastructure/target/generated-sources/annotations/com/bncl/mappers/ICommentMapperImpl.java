package com.bncl.mappers;

import com.bncl.dto.CommentDto;
import com.bncl.dto.CommentDto.CommentDtoBuilder;
import com.bncl.entity.Comment;
import com.bncl.entity.Comment.CommentBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-04-18T21:48:40-0500",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.10 (Oracle Corporation)"
)
public class ICommentMapperImpl implements ICommentMapper {

    @Override
    public CommentDto commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDtoBuilder commentDto = CommentDto.builder();

        commentDto.id( comment.getId() );
        commentDto.comment( comment.getComment() );
        commentDto.postId( comment.getPostId() );

        return commentDto.build();
    }

    @Override
    public Comment commentDtoToComment(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        CommentBuilder comment = Comment.builder();

        comment.id( commentDto.getId() );
        comment.comment( commentDto.getComment() );
        comment.postId( commentDto.getPostId() );

        return comment.build();
    }

    @Override
    public List<CommentDto> commentListToCommentDtoList(List<Comment> commentList) {
        if ( commentList == null ) {
            return null;
        }

        List<CommentDto> list = new ArrayList<CommentDto>( commentList.size() );
        for ( Comment comment : commentList ) {
            list.add( commentToCommentDto( comment ) );
        }

        return list;
    }

    @Override
    public List<Comment> commentDtoListToCommentList(List<CommentDto> CommentDtoList) {
        if ( CommentDtoList == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( CommentDtoList.size() );
        for ( CommentDto commentDto : CommentDtoList ) {
            list.add( commentDtoToComment( commentDto ) );
        }

        return list;
    }
}
