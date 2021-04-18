package com.bncl.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bncl.dto.CommentDto;
import com.bncl.ports.api.ICommentServicePort;
import com.bncl.response.Messages;


@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private ICommentServicePort commmentServicePort;
	
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
			return Messages.ErrorMessageController("Error al consultar", dataAccessException);
		}
	}
	
	@PostMapping("/insertComment")
	public ResponseEntity<?> insertComment(@Valid @RequestBody CommentDto  commentDto) {
		CommentDto stored = null;
		try {
			stored = commmentServicePort.save(commentDto);
			return new ResponseEntity<CommentDto>(stored, HttpStatus.CREATED);
			
		} catch (Exception dataAccessException) {
			return Messages.ErrorMessageController("Error saving", dataAccessException);
		}
	}

}
