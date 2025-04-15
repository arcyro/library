package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface BookRepository extends JpaRepository<Book, Long> {
}
