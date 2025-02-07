package com.vn2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn2.entity.Book;

public interface BookRepo extends JpaRepository<Book,Long>{

}
