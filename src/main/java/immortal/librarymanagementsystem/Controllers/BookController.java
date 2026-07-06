package immortal.librarymanagementsystem.Controllers;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;
import immortal.librarymanagementsystem.Services.BookService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        List<BookResponseDTO> books = bookService.readAllBooks();

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

    @GetMapping("/available")
    public ResponseEntity<List<BookResponseDTO>> readAvailableBooks(){
        List<BookResponseDTO> books = bookService.readAvailableBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/filter/category/{categoryId}")
    public ResponseEntity<List<BookResponseDTO>> filterByCategory(@PathVariable Long categoryId){
        List<BookResponseDTO> bookResponseDTOS = bookService.filterBooksByCategory(categoryId);

        return ResponseEntity.ok(bookResponseDTOS);

    }


    @GetMapping("/filter/author/{authorId}")
    public ResponseEntity<List<BookResponseDTO>> filterByAuthor(@PathVariable Long authorId){
        List<BookResponseDTO> bookResponseDTOS = bookService.filterBooksByAuthor(authorId);

        return ResponseEntity.ok(bookResponseDTOS);

    }

    @GetMapping("/filter/{title}")
    public ResponseEntity<List<BookResponseDTO>> findBookByTitle(@PathVariable String title){
        List<BookResponseDTO> bookResponseDTOS = bookService.findBookByTitle(title);

        return ResponseEntity.ok(bookResponseDTOS);

    }


}
