package com.uniq.JWTpractice.Global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.uniq.JWTpractice.Response.ApiResponse;

@ControllerAdvice
public class ExceptionHandle {

	@ExceptionHandler
	public ResponseEntity<ApiResponse> exception(Exception e) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		apiResponse.setError("Oops something went wrong");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
	
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse> userNotFound(UserNotFoundException e) {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
		apiResponse.setData("User not found");
		apiResponse.setError("Invalid Email or password");
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}
}
