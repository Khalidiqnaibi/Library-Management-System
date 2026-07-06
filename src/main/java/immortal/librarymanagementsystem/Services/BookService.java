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

    List<BookResponseDTO> findBookByTitle(String title);
    List<BookResponseDTO> filterBooksByCategory(Long categoryId);
    List<BookResponseDTO> filterBooksByAuthor(Long authorId);
    List<BookResponseDTO> readAvailableBooks();

    BookResponseDTO borrowBook(Long bookId, Long borrowerId);
    void returnBook(Long bookId);
}
