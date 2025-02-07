package com.vn2.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vn2.entity.Book;
import com.vn2.entity.Library;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LibraryDto {

	
	private Long id; 
	
	@NotEmpty(message = "Name Must Not Be Empty")
	@Size(min = 5,max = 30)
    private String name; 
	
//	@JsonIgnore
	private List<Book> books; 
}
