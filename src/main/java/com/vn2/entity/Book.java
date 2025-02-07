package com.vn2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Book")
public class Book {

	    @Id 
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    @Column(name = "book_id")
	    private Long bookId;
	    
	    @Column(name = "book_title")
	    private String title; 
	    
	    @Column(name = "book_isbn")
	    private String isbn; 
	 
	    @ManyToOne() 
	    @JoinColumn(name = "library_id", nullable = false) 
	    private Library library; 
 
}
