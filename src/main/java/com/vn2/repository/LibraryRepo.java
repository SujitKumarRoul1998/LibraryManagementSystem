package com.vn2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vn2.entity.Library;

public interface LibraryRepo extends JpaRepository<Library, Long> {

}
