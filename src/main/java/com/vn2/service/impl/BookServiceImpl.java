package com.vn2.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vn2.dto.BookDto;
import com.vn2.dto.LibraryDto;
import com.vn2.entity.Book;
import com.vn2.entity.Library;
import com.vn2.exception.ResourceNotFoundException;
import com.vn2.payload.PageResponce;
import com.vn2.repository.BookRepo;
import com.vn2.repository.LibraryRepo;
import com.vn2.service.BookService;

@Service
public class BookServiceImpl implements BookService{

    private	BookRepo repo;
    
    @Autowired
    private LibraryRepo libraryRepo;
    
    private ModelMapper mapper;
    
    @Autowired
	public BookServiceImpl(BookRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

    //Create Book
	@Override
	public BookDto createBook(BookDto dto) {
		
		//Check Library Id Present or Not
		
		Book save = repo.save(dtoToEntity(dto));
		return entityToDto(save);
	}

	//Update Book 
	@Override
	public BookDto updateBook(BookDto dto, Long bookId) {
		Book book = repo.findById(bookId).orElseThrow(
				()->new ResourceNotFoundException("Book", "Id", bookId)
				);
		book.setTitle(dto.getTitle());
		book.setIsbn(dto.getIsbn());
		Book updateBook= repo.save(book);
		return entityToDto(updateBook);
	}

	//Delete Book ById
	@Override
	public void deleteBook(Long bookId) {
		Book book = repo.findById(bookId).orElseThrow(
				()->new ResourceNotFoundException("Book", "Id", bookId)
				);
		repo.deleteById(bookId);
	}
	
    //Fetch Book ById
	@Override
	public BookDto getbyId(Long bookId) {
		Book book = repo.findById(bookId).orElseThrow(
				()->new ResourceNotFoundException("Book", "Id", bookId)
				);
		
		return entityToDto(book);
	}

	//Fetch All Books
	@Override
	public PageResponce getAllBooks(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		       //Sorting Logic
				Sort sort=null;
				if(sortDir.equalsIgnoreCase("asc")) {
					sort=sort.by(sortBy).ascending();
				}else {
					sort=sort.by(sortBy).descending();
				}
				
				//Pagination Logic
				Pageable p = PageRequest.of(pageNumber,pageSize,sort);
			
		        Page<Book> page = repo.findAll(p);
		        
		        List<Book> con = page.getContent();
		        
		         List<BookDto> all = page.stream().map(book->entityToDto(book)).collect(Collectors.toList());
		         
		         PageResponce pResponce=new PageResponce();
		         pResponce.setContentBook(all);
		         pResponce.setPageNumber(page.getNumber());
		         pResponce.setPageSize(page.getSize());
		         pResponce.setTotalElements(page.getTotalElements());
		         pResponce.setTotalPages(page.getTotalPages());
		         pResponce.setLast(page.isLast());
		         
				return pResponce;
	}

//Convert DTO To Entity
	public Book dtoToEntity(BookDto dto) {

		Library lib=libraryRepo.findById(dto.getId()).orElseThrow(
				()->new ResourceNotFoundException("Library", "Id", dto.getId())
				);
		Book book=new Book();
		book.setBookId(dto.getBookId());
		book.setTitle(dto.getTitle());
		book.setIsbn(dto.getIsbn());
		book.setLibrary(lib);
		return book;
	}

//Convert Entity To DTO
	public BookDto entityToDto(Book book) {
		Library lib=libraryRepo.findById(book.getLibrary().getId()).orElseThrow(
				()->new ResourceNotFoundException("Library", "Id", book.getLibrary().getId())
				);
		BookDto dto=new BookDto();
		dto.setBookId(book.getBookId());
		dto.setTitle(book.getTitle());
		dto.setIsbn(book.getIsbn());
		dto.setId(lib.getId());
		return dto;
	}
}
