package com.vn2.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vn2.dto.LibraryDto;
import com.vn2.entity.Library;
import com.vn2.exception.ResourceNotFoundException;
import com.vn2.payload.PageResponce;
import com.vn2.repository.LibraryRepo;
import com.vn2.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	private LibraryRepo libraryRepo;
	
	private ModelMapper modelMapper;
	
	@Autowired
	public LibraryServiceImpl(LibraryRepo libraryRepo, ModelMapper modelMapper) {
		super();
		this.libraryRepo = libraryRepo;
		this.modelMapper = modelMapper;
	}
	
	//Create Library
	@Override
	public LibraryDto createLibrary(LibraryDto dto) {
	Library save = libraryRepo.save(dtoToEntity(dto));
		return entityToDto(save);
	}
	
	//Update Library
	@Override
	public LibraryDto updateLibrary(LibraryDto dto, Long id) {
		//Check Id Present Or Not
		Library library = libraryRepo.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Library", "id", id)
				);
		library.setName(dto.getName());
		Library updated = libraryRepo.save(library);
		return entityToDto(updated);
	}
	
	//Delete Library
	@Override
	public void deleteLibrary(Long id) {
		//Check Id Present Or Not
		Library library = libraryRepo.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Library", "id", id)
				);
		libraryRepo.deleteById(id);	
	}
	
	//Get ById
	@Override
	public LibraryDto getById(Long id) {
		//Check Id Present Or Not
		Library library = libraryRepo.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Library", "id", id)
				);
		return entityToDto(library);
	}
	
	//Get All
	@Override
	public PageResponce getAllLibrary(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		//Sorting Logic
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=sort.by(sortBy).ascending();
		}else {
			sort=sort.by(sortBy).descending();
		}
		//Pagination Logic
		Pageable p = PageRequest.of(pageNumber,pageSize,sort);
	
        Page<Library> page = libraryRepo.findAll(p);
        
        List<Library> con = page.getContent();
        
         List<LibraryDto> all = page.stream().map(library->entityToDto(library)).collect(Collectors.toList());
         
         PageResponce pResponce=new PageResponce();
         pResponce.setContentLibrary(all);
         pResponce.setPageNumber(page.getNumber());
         pResponce.setPageSize(page.getSize());
         pResponce.setTotalElements(page.getTotalElements());
         pResponce.setTotalPages(page.getTotalPages());
         pResponce.setLast(page.isLast());
         
		return pResponce;
	}
	
//Convert DTO To Entity
	public Library dtoToEntity(LibraryDto dto) {
		Library library=new Library();
		library.setId(dto.getId());
		library.setName(dto.getName());
		library.setBooks(dto.getBooks());
		return library;
	}
	
//Convert Entity To DTO
	public LibraryDto entityToDto(Library library) {
		LibraryDto dto=new LibraryDto();
		dto.setId(library.getId());
		dto.setName(library.getName());
		dto.setBooks(library.getBooks());
		return dto;
	}
}
