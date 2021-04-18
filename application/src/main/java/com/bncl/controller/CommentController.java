package com.bncl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bncl.dto.CommentDto;
import com.bncl.ports.api.ICommentServicePort;
import com.bncl.response.Messages;


@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private ICommentServicePort commmentServicePort;
	
	private final String HOST_POST_URL = "http://localhost:8080/post/checkExistPost";
	
	@GetMapping("/findCommentByPostId/{postId}")
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
	
	@PostMapping("/insertComment")
	public ResponseEntity<?> insertComment(@Valid @RequestBody CommentDto  commentDto) {
		CommentDto stored = null;
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			Boolean valid = false;
			
			if(commentDto.getPostId() != null && StringUtils.isNotBlank(commentDto.getComment())) {
				
				valid = restTemplate.getForObject(HOST_POST_URL.concat("/").concat(commentDto.getPostId().toString()), Boolean.class);
				
				if(valid) {
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
