package com.vn2.service;

import com.vn2.dto.BookDto;
import com.vn2.payload.PageResponce;

public interface BookService {

	//Create Book
	BookDto createBook(BookDto dto);
	
	//Update Book
	BookDto updateBook(BookDto dto,Long bookId);
	
	//Delete Book
	void deleteBook(Long bookId);
	
	//Get ById
	BookDto getbyId(Long bookId);
	
	//Get All
	PageResponce getAllBooks(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
}
