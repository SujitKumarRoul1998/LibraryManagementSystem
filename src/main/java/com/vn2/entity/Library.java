package com.vn2.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Library")
public class Library {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "library_id")
	private Long id;
	
	@Column(name ="library_name")
	private String name;
	
	 @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true) 
	 private List<Book> books; 	
	
}
