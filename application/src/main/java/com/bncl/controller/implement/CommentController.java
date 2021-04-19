package com.bncl.controller.implement;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bncl.controller.interfaces.ICommentController;
import com.bncl.dto.CommentDto;
import com.bncl.ports.api.ICommentServicePort;
import com.bncl.response.Messages;


@RestController
@RequestMapping("/comment")
public class CommentController implements ICommentController{
	
	@Autowired
	private ICommentServicePort commmentServicePort;
	
	@Autowired
	private PostController postController;
	

	@Override
	public ResponseEntity<?> findByPostId(@PathVariable Integer postId) {
		List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
		try {
			commentDtoList = commmentServicePort.findByPostId(postId);
			
			if (!commentDtoList.isEmpty()) {
				return new ResponseEntity<List<CommentDto>>(commentDtoList, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<CommentDto>>(HttpStatus.NO_CONTENT);
			}

		} catch (Exception dataAccessException) {
			return Messages.ErrorMessageController("Error al consultar - findByPostId", dataAccessException);
		}
	}
	
	@Override
	public ResponseEntity<?> insertComment(@Valid @RequestBody CommentDto  commentDto) {
		CommentDto stored = null;
			
		try {

			if(commentDto.getPostId() != null && StringUtils.isNotBlank(commentDto.getComment())) {

				if(postController.validatePostId(commentDto.getPostId())) {
					stored = commmentServicePort.save(commentDto);
					return new ResponseEntity<CommentDto>(stored, HttpStatus.CREATED);
				} else {
					return Messages.ErrorMessageController("No se guardó el comentario, No se encontró un post con el ID ".concat(commentDto.getPostId().toString()) );
				}

			} else {
				return Messages.ErrorMessageController("No se guardó el comentario, no se ha encontrado el identificador o comentario del post");
			}
			
		} catch (Exception dataAccessException) {
			return Messages.ErrorMessageController("Error guardando - insertComment", dataAccessException);
		}

	}
	
}
