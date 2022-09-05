package com.john.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.john.library.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
