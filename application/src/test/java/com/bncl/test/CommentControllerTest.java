package com.bncl.test;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.bncl.controller.implement.CommentController;
import com.bncl.controller.implement.PostController;
import com.bncl.dto.CommentDto;
import com.bncl.ports.api.ICommentServicePort;


@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
	
	@Mock
	private ICommentServicePort commmentServicePort;
	
	@Mock
	private PostController postController;
	
	@InjectMocks
	private CommentController commentController;
		
	@BeforeEach
	@SuppressWarnings("deprecation")
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void givenCommentNullAndPostIdNullShouldNotSave() {

		CommentDto commentTosave = new CommentDto(null, null, null);

		commentController.insertComment(commentTosave);
		
		verify(commmentServicePort, times(0)).save(commentTosave);
	}
	
	@Test
	public void givenCommentAndPostIdNullShouldNotSave() {

		CommentDto commentTosave = new CommentDto(null, "Comentario de prueba", null);

		commentController.insertComment(commentTosave);
		
		verify(commmentServicePort, times(0)).save(commentTosave);
	}
	
	@Test
	public void givenCommentNullAndPostIdShouldNotSave() {

		CommentDto commentTosave = new CommentDto(null, null, 1);

		commentController.insertComment(commentTosave);
		
		verify(commmentServicePort, times(0)).save(commentTosave);
	
	}
	
	@Test
	public void givenCommentAndPostIdShouldSave() {

		CommentDto commentTosave = new CommentDto(null, "Comentario de prueba", 1);
	        
		Mockito.when(postController.validatePostId(commentTosave.getPostId())).thenReturn(true);
		
		commentController.insertComment(commentTosave);
		
		verify(commmentServicePort, times(1)).save(commentTosave);
	
	}
}
