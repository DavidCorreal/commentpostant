package com.bncl.controller.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bncl.dto.PostDto;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface IPostController {

	@ApiOperation(
	        value = "Retorna listado de todos los post publicados en https://jsonplaceholder.typicode.com/posts",
	        response = PostDto.class,
	        responseContainer = "List"
	)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Consulta exitosa,  la respuesta contiene información"),
	        @ApiResponse(code = 204, message = "Consulta exitosa,  la respuesta no contiene información")
	})
	@GetMapping(value = "/findAllPost", produces="application/json")
	public ResponseEntity<?> findAllPost();
	
	
	@ApiOperation(
	        value = "Verifica si un post existe por su id, en https://jsonplaceholder.typicode.com/posts/{id-post}",
	        response = Boolean.class,
	        responseContainer = "Object"
	)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Consulta exitosa,  la respuesta contiene información")
	})
	@GetMapping(value = "/checkExistPost/{postId}", produces="application/json")
	public ResponseEntity<?> checkExistPost(@PathVariable Integer postId);
	
}
