package immortal.librarymanagementsystem.Controllers;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;
import immortal.librarymanagementsystem.Services.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO){
        BookResponseDTO createdBook = bookService.createBook(bookRequestDTO);

        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id){
        BookResponseDTO bookResponseDTO = bookService.readBook(id);

        return ResponseEntity.ok(bookResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> getAllBooks(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size){

        Page<BookResponseDTO> books = bookService.readAllBooks(page,size);

        return ResponseEntity.ok(books);

    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO bookRequestDTO){
        BookResponseDTO bookResponseDTO = bookService.updateBook(id, bookRequestDTO);

        return ResponseEntity.ok(bookResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{bookId}/borrow/{borrowerId}")
    public ResponseEntity<BookResponseDTO> borrowBook(@PathVariable Long bookId , @PathVariable Long borrowerId){

        BookResponseDTO bookResponseDTO = bookService.borrowBook(bookId,borrowerId);

        return ResponseEntity.ok(bookResponseDTO);
    }

    @PutMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId){
        bookService.returnBook(bookId);
        return ResponseEntity.ok("Book returned successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<Page<BookResponseDTO>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Boolean availableOnly,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<BookResponseDTO> books = bookService.searchBook(title, authorId, categoryId, availableOnly,page,size);

        return ResponseEntity.ok(books);
    }


}
