package com.vn2.payload;

import java.util.List;

import com.vn2.dto.BookDto;
import com.vn2.dto.LibraryDto;
import com.vn2.entity.Library;

import lombok.Data;

@Data
public class PageResponce {

	private List<LibraryDto> contentLibrary;
	
	private List<BookDto> contentBook;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private Integer totalPages;
	
	private long totalElements;
	
	private boolean last;
}
