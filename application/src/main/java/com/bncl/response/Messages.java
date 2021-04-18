package com.bncl.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Messages {

	public static ResponseEntity<?> ErrorMessageController(String message, Exception dataException){
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", message);
		response.put("error", dataException.getMessage() + ": " + dataException.getMessage());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public static ResponseEntity<?> ErrorMessageController(String message){
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", message);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
