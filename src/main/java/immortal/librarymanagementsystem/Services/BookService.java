package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;

import java.util.List;

public interface BookService {
    BookResponseDTO readBook(Long id);
    List<BookResponseDTO> readAllBooks();
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBook(Long id);

//    BookResponseDTO readBookByTitle(String title);
    BookResponseDTO BorrowBook(Long bookId, Long borrowerId);
    void ReturnBook(Long bookId);
}
