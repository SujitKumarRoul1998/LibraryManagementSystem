package com.vn2.service;

import java.util.List;

import com.vn2.dto.LibraryDto;
import com.vn2.payload.PageResponce;

public interface LibraryService {

	//Create Library
	LibraryDto createLibrary(LibraryDto dto);
	
	//Update Library
	LibraryDto updateLibrary(LibraryDto dto,Long id);
	
	//Delete Library
	void deleteLibrary(Long id);
	
	//Get ById
	LibraryDto getById(Long id);
	
	//Get All
	PageResponce getAllLibrary(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
}
