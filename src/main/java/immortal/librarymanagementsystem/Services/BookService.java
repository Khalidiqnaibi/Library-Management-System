package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;
import immortal.librarymanagementsystem.Repositories.BookRepository;

import java.util.List;

public interface BookService {
    BookResponseDTO readBook(Long id);
    List<BookResponseDTO> readAllBooks();
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBook(Long id);

    List<BookResponseDTO> searchBook(String title,Long authorId,Long categoryId,Boolean availableOnly);

    BookResponseDTO borrowBook(Long bookId, Long borrowerId);
    void returnBook(Long bookId);
}
