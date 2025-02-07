package com.vn2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vn2.dto.BookDto;
import com.vn2.dto.LibraryDto;
import com.vn2.payload.PageResponce;
import com.vn2.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/book")
public class BookController {

	private BookService service;

	@Autowired
	public BookController(BookService service) {
		super();
		this.service = service;
	}
	
	//Saved Books
	@PostMapping
    @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BookDto> saveBooks(@Valid @RequestBody BookDto dto)
	{
		BookDto book=service.createBook(dto);
		return new ResponseEntity(book,HttpStatus.OK);
	}
	
	//Update Books
	@PutMapping("bookId")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<BookDto>updateBook(@RequestBody BookDto dto,@PathVariable Long bookId)
	{
		BookDto updateBook= service.updateBook(dto,bookId);
		return new ResponseEntity(updateBook,HttpStatus.OK);
	}
	
	//Delete Books
	@DeleteMapping("bookId")
	 @PreAuthorize("hasRole('ADMIN')")
	public void deleteBook(@PathVariable("bookId") Long bookId )
	{
	     service.deleteBook(bookId);
	}

	//Get ById
	@GetMapping("{bookId}")
	@PreAuthorize("hasRole('USER')" )
	public ResponseEntity<BookDto> getById(@PathVariable("bookId") Long bookId){
		BookDto dto = service.getbyId(bookId);
		return new ResponseEntity<BookDto>(dto,HttpStatus.OK);
	}
	
   //Fetch All
	
	@GetMapping
	@PreAuthorize("hasRole('USER')" )
	public ResponseEntity<PageResponce> getAll(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "3",required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "ASC",required = false)String sortDir
			){
		
		PageResponce allBooks = service.getAllBooks(pageNumber, pageSize, sortBy, sortDir);
		
		return new ResponseEntity<PageResponce>(allBooks,HttpStatus.OK);
	}
	
}
