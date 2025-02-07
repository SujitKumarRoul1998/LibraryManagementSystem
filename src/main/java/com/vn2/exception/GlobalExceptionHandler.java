package com.vn2.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vn2.payload.ExceptionResponce;

@RestControllerAdvice
public class GlobalExceptionHandler {

	//ResourceNotFoundException Handler Method.
	
	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<ExceptionResponce> resourceNotFoundHandler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		
		ExceptionResponce eResponce=new ExceptionResponce(message,false);
		
		return new ResponseEntity<>(eResponce,HttpStatus.NOT_FOUND);	
	}

    //All Type 	Exception Handler Method
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<ExceptionResponce> allExceptionHandler(Exception ex){
		
		String message = ex.getMessage();
		
		ExceptionResponce eResponce=new ExceptionResponce(message,false);
		
		return new ResponseEntity<>(eResponce,HttpStatus.BAD_REQUEST);	
	}

	//Handle Exception For Validation
	
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		
		Map<String,String> resp=new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error)->{
			
			String fieldName=((FieldError)error).getField();
			String mesage = error.getDefaultMessage();
			resp.put(fieldName, mesage);
		});
		
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
	
}
