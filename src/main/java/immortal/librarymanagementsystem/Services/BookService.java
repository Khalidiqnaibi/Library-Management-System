package immortal.librarymanagementsystem.Services;

import immortal.librarymanagementsystem.DTOs.Book.BookRequestDTO;
import immortal.librarymanagementsystem.DTOs.Book.BookResponseDTO;
import immortal.librarymanagementsystem.Repositories.BookRepository;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    BookResponseDTO readBook(Long id);
    Page<BookResponseDTO> readAllBooks(int page , int size);
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);
    void deleteBook(Long id);

    Page<BookResponseDTO> searchBook(String title,Long authorId,Long categoryId,Boolean availableOnly,int page , int size);

    BookResponseDTO borrowBook(Long bookId, Long borrowerId);
    void returnBook(Long bookId);
}
