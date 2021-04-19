package com.bncl.controller.interfaces;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bncl.dto.CommentDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ICommentController {

	@ApiOperation(
	        value = "Busca los comentarios relacionados con un post por medio del id",
	        response = CommentDto.class,
	        responseContainer = "List"
	)
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Se guardo correctamente"),
	        @ApiResponse(code = 500, message = "No se valido correctamente")
	})
	@GetMapping(value = "/findCommentByPostId/{postId}", produces="application/json")
	public ResponseEntity<?> findByPostId(@PathVariable Integer postId);
	
	
	
	@ApiOperation(
	        value = "Guarda un comentario, si el cuerpo contiene el comentario y el id del post",
	        response = CommentDto.class,
	        responseContainer = "Object"
	)
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Se guardo correctamente"),
	        @ApiResponse(code = 500, message = "No se valido correctamente")
	})
	@PostMapping(value = "/insertComment", produces="application/json")
	public ResponseEntity<?> insertComment(@Valid @RequestBody CommentDto  commentDto);
	
	
}
