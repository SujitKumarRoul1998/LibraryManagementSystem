package com.vn2.dto;

import com.vn2.entity.Library;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDto {
	
	private Long bookId;
	
	@NotEmpty(message = "Title Must Be Valid")
	@Size(min = 3,max = 15)
    private String title; 
    
	@NotBlank(message = "isbn Must Be Valid")
    private String isbn; 

	private Long id ; 
}
