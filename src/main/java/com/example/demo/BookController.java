package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BookController(BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "book/list";

    }
    @GetMapping("/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("publishers", publisherRepository.findAll());
        model.addAttribute("book", new Book());
        return "book/add";
    }

    @PostMapping("/new")
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/book/list";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam Long id) {
        bookRepository.deleteById(id);
        return "redirect:/book/list";
    }

    @GetMapping("/edit")
    public String showEditBookForm(@RequestParam Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "book/edit";
    }

    @PostMapping("/edit")
    public String editBook(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "redirect:/book/list";
    }
}
