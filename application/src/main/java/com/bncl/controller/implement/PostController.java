package com.bncl.controller.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.bncl.controller.interfaces.IPostController;
import com.bncl.dto.PostDto;
import com.bncl.response.Messages;


@RestController
@RequestMapping("/post")
public class PostController implements IPostController {
	
	private final String JSONPLACEHOLDER_URL = "https://jsonplaceholder.typicode.com/posts";
	
	@Override
	public ResponseEntity<?> findAllPost() {
		
		RestTemplate restTemplate = new RestTemplate();
		List<PostDto> postDtoList = new ArrayList<PostDto>();
		
		try {
		
			ResponseEntity<List<PostDto>> rateResponse = restTemplate.exchange(
					JSONPLACEHOLDER_URL,
	                HttpMethod.GET, null, new ParameterizedTypeReference<List<PostDto>>() {}
			);

			postDtoList = rateResponse.getBody();
			
			if (!postDtoList.isEmpty()) {
				return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<PostDto>>(HttpStatus.NO_CONTENT);
			}


		} catch (Exception dataAccessException) {
			return Messages.ErrorMessageController("Error al consultar en jsonplaceholder - findAllPost", dataAccessException);
		}
	}
	

	@Override
	public ResponseEntity<?> checkExistPost(@PathVariable Integer postId) {
		RestTemplate restTemplate = new RestTemplate();
		PostDto postDto = null;
		
		try {
		
			postDto = restTemplate.getForObject(JSONPLACEHOLDER_URL.concat("/").concat(postId.toString()), PostDto.class);

			if (postDto != null) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}


		} catch (HttpClientErrorException ex)   {
		    if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
		    	return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		    } else {
		    	return Messages.ErrorMessageController("Error al consultar en jsonplaceholder - checkExistPost", ex);
		    }
		}
	}
	
	/**
	 * Call checkExistPost to validate if a post exists by its id
	 * @param postId
	 * @return
	 */
	public Boolean validatePostId(Integer postId) {
		try {
			
			ResponseEntity<?> response = checkExistPost(postId);
			return (Boolean) response.getBody();		
		
		} catch (Exception dataAccessException) {
			return false;
		}
	}

}
