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

import com.vn2.dto.LibraryDto;
import com.vn2.payload.PageResponce;
import com.vn2.service.LibraryService;

@RestController
@RequestMapping("/api/library")
public class LibraryController {

	private LibraryService service;

	@Autowired
	public LibraryController(LibraryService service) {
		super();
		this.service = service;
	}
	
//Create Library
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')" )
	public ResponseEntity<LibraryDto> createLibrary(@RequestBody LibraryDto dto){
		LibraryDto libraryDto = service.createLibrary(dto);
		return new ResponseEntity<LibraryDto>(libraryDto,HttpStatus.CREATED);
	}
	
//Update Library
	
	@PutMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')" )
	public ResponseEntity<LibraryDto> updateLibrary(@RequestBody LibraryDto dto,@PathVariable("id") Long id){
		LibraryDto libraryDto = service.updateLibrary(dto,id);
		return new ResponseEntity<LibraryDto>(libraryDto,HttpStatus.OK);
	}
//Delete library
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ADMIN')" )
	public void deleteLibrary(@PathVariable("id")Long id) {
		service.deleteLibrary(id);
	}
//Fetch ById
	
	@GetMapping("{id}")
	@PreAuthorize("hasRole('USER')" )
	public ResponseEntity<LibraryDto> getById(@PathVariable("id") Long id){
		LibraryDto libraryDto = service.getById(id);
		return new ResponseEntity<LibraryDto>(libraryDto,HttpStatus.OK);
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
		
		PageResponce allLibrary = service.getAllLibrary(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PageResponce>(allLibrary,HttpStatus.OK);
	}
	
}
