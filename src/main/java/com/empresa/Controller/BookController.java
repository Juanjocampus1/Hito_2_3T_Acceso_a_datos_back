package com.empresa.Controller;

import com.empresa.Controller.DTO.BookDTO;
import com.empresa.Entities.Book;
import com.empresa.Service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceimpl;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookServiceimpl.findById(id);

        if (bookOptional.isPresent()){
            Book book = bookOptional.get();

            BookDTO bookDTO = BookDTO.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .author(book.getAuthor())
                    .isbn(book.getIsbn())
                    .price(book.getPrice())
                    .stock(book.getStock())
                    .build();
            return ResponseEntity.ok(bookDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {

        List<BookDTO> booklist = bookServiceimpl.findAll()
                .stream()
                .map(book -> BookDTO.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .isbn(book.getIsbn())
                        .price(book.getPrice())
                        .stock(book.getStock())
                        .build())
                .toList();

        return ResponseEntity.ok(booklist);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BookDTO bookDTO) {
        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .isbn(bookDTO.getIsbn())
                .price(bookDTO.getPrice())
                .stock(bookDTO.getStock())
                .build();

        bookServiceimpl.save(book);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bookServiceimpl.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
